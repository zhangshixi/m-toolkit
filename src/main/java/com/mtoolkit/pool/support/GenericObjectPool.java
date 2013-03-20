package com.mtoolkit.pool.support;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.mlogger.Loggers;
import com.mtoolkit.pool.ObjectManager;
import com.mtoolkit.pool.ObjectPool;
import com.mtoolkit.pool.ObjectPoolException;

/**
 * Generic object pool implementation.
 * 
 * @param 	<T> object type.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/13/2011
 * @since 	JDK1.5
 */
public class GenericObjectPool<T> implements ObjectPool<T> {

	/** pool configuration */
	private Config _config;
	/** object management */
	private ObjectManager<T> _manager;
	
	/** object pool container */
	private List<TimeObject> _pool;
	/** active object number(borrowed number) */
	private int _activeNumber;
	/** creating number in inner */
	private int _innerCreatingNum;
	/** flag of pool has closed */
	private volatile boolean _close;
	
	/** pool lock */
	private final Lock _lock = new ReentrantLock();
	/** condition that can borrow object from pool */
	private final Condition _borrow = _lock.newCondition(); 
	
	/** logger */
	private static final Loggers LOGGER = Loggers.getLoggers(ObjectPool.class);
	
	// ---- constructors -------------------------------------------------------
	public GenericObjectPool(ObjectManager<T> manager) {
		this(new Config(), manager);
	}
	
	public GenericObjectPool(Config config, ObjectManager<T> manager) {
		checkConfig(config);
		checkManager(manager);
		
		_config  = config;
		_manager = manager;
		
		try {
			doInitPool();
		} catch (ObjectPoolException e) {
			LOGGER.error("Initialize object pool falied", e);
		}
		
		doStartEvictTask();
	}

	// ---- public methods -----------------------------------------------------
	@Override
	public T borrowObject() throws ObjectPoolException {
		// if pool has closed, not promise to borrows.
		checkState();
		
		lock();
		final long startTime = System.currentTimeMillis();
		
		try {
			while (_pool.isEmpty() ||
				  (_activeNumber >= _config._maxActiveSize && _config._maxActiveSize > 0)) {
				switch (_config._exhaustedStrategy) {
				case 0:
					boolean timeout = false;
					try {
						if (_config._maxWaitTime <= 0) {
							_borrow.await();
						} else {
							long waitedTime = System.currentTimeMillis() - startTime;
							if (_config._maxWaitTime > waitedTime) {
								long remaining = _config._maxWaitTime - waitedTime;
								boolean await = _borrow.await(remaining, TimeUnit.MILLISECONDS);
								if (!await) {
									timeout = true;
								} else {
									continue;
								}
							} else {
								timeout = true;
							}
						}
					} catch (InterruptedException e) {
						//propagate to non-interrupted thread.
						_borrow.signal();
						//throw e;
					}
					
					if (timeout) {
						Exception ex = new TimeoutException("Borrow object timeout!");
						throw new ObjectPoolException(ex.getMessage(), ex);
					}
					
					break;
				case 1:
					// break to create a new one.
					break;
				case 2:
					throw new ObjectPoolException("Create new object falied.");
				default:
					// never occurs.
					throw new IllegalArgumentException("Configure error!");
				}
			}
			
			final T element = doExtract().getObject();
			
			if (_config._validateOnBorrow && !_manager.validateObject(element)) {
				_manager.destroyObject(element);
				throw new ObjectPoolException("Validate new object falied.");
			} else {
				_activeNumber++;
				return element;
			}
		} finally {
			unlock();
		}
	}

	@Override
	public void returnObject(T target) throws ObjectPoolException {
		try {
			checkState();
			
			if (_config._validataOnReturn && !_manager.validateObject(target)) {
				_manager.destroyObject(target);
			} else {
				try {
					// need decrement _acticeNumber in finally block.
					doInsert(target, false); 
				} catch (ObjectPoolException e) {
					_manager.destroyObject(target);
				}
			}
		} catch (IllegalStateException e) {
			_manager.destroyObject(target); // pool has closed.
		} catch (ObjectPoolException e) {
			throw e; // destroy object exception.
		} finally {
			lock();
			try {
				_activeNumber--;
				_borrow.signal();
			} finally {
				unlock();
			}
		}
	}

	@Override
	public void invalidObject(T target) throws ObjectPoolException {
		// destroy the target object whether the pool has closed or not.
		lock();
		try {
			_manager.destroyObject(target);
		} finally {
			_activeNumber--;
			_borrow.signal();
			
			unlock();
		}
	}

	@Override
	public int getIdleNumber() {
		checkState();
		
		lock();
		try {
			return _pool.size();
		} finally {
			unlock();
		}
	}

	@Override
	public int getActiveNumber() {
		checkState();
		
		lock();
		try {
			return _activeNumber;
		} finally {
			unlock();
		}
	}

	@Override
	public void clear() throws ObjectPoolException {
		// if pool has closed, it also cleared.
		checkState();

		// need create list instance after locked.
		List<TimeObject> elements = null;

		lock();
		try {
			elements = new ArrayList<TimeObject>(_pool);
			_innerCreatingNum += _pool.size(); // TODO need?
			_pool.clear();
		} finally {
			unlock();
		}
		
		doDestroy(elements);
	}

	@Override
	public void close() throws ObjectPoolException {
		// if pool has closed, not need close again.
		checkState();
		
		// need create list instance after locked.
		List<TimeObject> elements = null;
		
		lock();
		try {
			doStopEvictTask();
			elements = new ArrayList<TimeObject>(_pool);
			_pool.clear();
			_close = true;
		} finally {
			unlock();
		}
		
		doDestroy(elements);
	}
	
	// ---- private methods ----------------------------------------------------
	private void lock() {
		_lock.lock();
	}
	
	private void unlock() {
		_lock.unlock();
	}

	private void checkConfig(Config config) {
		if (config == null) {
			throw new NullPointerException("config");
		}
		
	}
	
	private void checkManager(ObjectManager<T> manager) {
		if (manager == null) {
			throw new NullPointerException("manager");
		}
	}
	
	private void doInitPool() throws ObjectPoolException {
		if (_config._initializeSize <= 0) {
			return;
		}
		
		for (int i = 0; i < _config._initializeSize; i++) {
			doInsert(_manager.createObject(), false);
		}
	}

	private void doStartEvictTask() {
		EvictionTask.start(
				new EvictionThread(), 
				_config._evictDelayTime, 
				_config._evictDelayTime);
	}
	
	private void doStopEvictTask() {
		EvictionTask.stop();
	}
	
	private static class EvictionTask {
		
		private static ScheduledExecutorService _evictThreadPool;
		
		private static void start(EvictionThread thread, long delay, long period) {
			_evictThreadPool = Executors.newSingleThreadScheduledExecutor();
			_evictThreadPool.scheduleWithFixedDelay(
					thread, delay, period, TimeUnit.MILLISECONDS);
		}
		
		private static void stop() {
			if (_evictThreadPool == null) {
				return;
			}
			
			_evictThreadPool.shutdown();
			try {
				if (!_evictThreadPool.awaitTermination(2, TimeUnit.SECONDS)) {
					_evictThreadPool.shutdownNow();
				}
			} catch (InterruptedException e) {
				_evictThreadPool.shutdownNow();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private static class EvictionThread implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private void checkState() {
		if (_close) {
			throw new IllegalStateException("Object pool is closed");
		}
	}

	private void doInsert(Object target, boolean decrActiveNum) throws ObjectPoolException {
		// TODO Auto-generated method stub
		
	}
	
	private TimeObject doExtract() throws ObjectPoolException {
		if (_pool.isEmpty()) {
			return new TimeObject(_manager.createObject());
		} else {
			return _pool.remove(0);
		}
	}
	
	private void doDestroy(List<TimeObject> elements) throws ObjectPoolException {
		if (elements == null || elements.isEmpty()) {
			return;
		}
		
		ObjectPoolException destryException = null;
		for (TimeObject element : elements) {
			try {
				_manager.destroyObject(element.getObject());
			} catch (ObjectPoolException e) {
				// continue to destroy the next
				destryException = e;
			}
		}
		
		elements.clear();
		elements = null;
		
		if (destryException != null) {
			throw destryException;
		}
	}
	
	private class TimeObject {
		
		private T 	 _target;
		private long _createTime;
		
		public TimeObject(T target) {
			this(target, System.currentTimeMillis());
		}
		
		public TimeObject(T target, long createTime) {
			_target 	= target;
			_createTime = createTime;
		}
		
		public T getObject() {
			return _target;
		}
		
		public long getCreateTime() {
			return _createTime;
		}
	}
	
	// ---- object pool configuration class ------------------------------------------
	/**
	 * Object pool configuration.
	 */
	public static class Config {
		
		private int 	_initializeSize		   = DEFAULT_INITIALIZE_SIZE;
		private int 	_maxIdleSize		   = DEFAULT_MAX_IDLE_SIZE;
		private int 	_maxActiveSize		   = DEFAULT_MAX_ACTIVE_SIZE;
		private long	_maxWaitTime		   = DEFAULT_MAX_WAIT_TIME;
		private boolean _validateOnBorrow	   = DEFAULT_VALIDATE_ON_BORROW;
		private boolean _validataOnReturn	   = DEFAULT_VALIDATE_ON_RETURN;
		private boolean _lifoStrategy		   = DEFAULT_LIFO_STRATEGY;
		private int 	_exhaustedStrategy;
		
		private long 	_evictDelayTime		   = DEFAULT_EVICT_DELAY_TIME;
		private int		_minIdleSize		   = DEFAULT_MIN_IDLE_SIZE;
		private long	_minEvictIdleTime	   = DEFAULT_MIN_EVICT_IDLE_TIME;
		private boolean _validateOnEviction	   = DEFAULT_VALIDATE_ON_EVICTION;
		private long	_softMinEvictIdleTime  = DEFAULT_SOFT_MIN_EVICT_IDLE_TIME;
		private int		_validateNumOnEviction = DEFAULT_PER_NUM_VALIDATE_ON_EVICTION;
		
		
		public static final int		DEFAULT_INITIALIZE_SIZE				 = 4;
		public static final int		DEFAULT_MAX_IDLE_SIZE				 = 8; 
		public static final int		DEFAULT_MAX_ACTIVE_SIZE				 = 8;
		public static final long	DEFAULT_MAX_WAIT_TIME				 = -1;
		public static final boolean DEFAULT_VALIDATE_ON_BORROW			 = false;
		public static final boolean DEFAULT_VALIDATE_ON_RETURN			 = false;
		public static final boolean DEFAULT_LIFO_STRATEGY 				 = false;
		
		public static final long 	DEFAULT_EVICT_DELAY_TIME 			 = -1L;
		public static final int 	DEFAULT_MIN_IDLE_SIZE 				 = 0;
		public static final long 	DEFAULT_MIN_EVICT_IDLE_TIME 		 = -1L;
		public static final boolean DEFAULT_VALIDATE_ON_EVICTION 		 = false;
		public static final long 	DEFAULT_SOFT_MIN_EVICT_IDLE_TIME 	 = 30 * 60 * 1000L;
		public static final int 	DEFAULT_PER_NUM_VALIDATE_ON_EVICTION = -1;
		
		public Config() {
		}
		
		public int getInitializeSize() {
			return _initializeSize;
		}
		
		public void setInitializeSize(int initializeSize) {
			_initializeSize = initializeSize;
		}
		
		public int getMaxIdleSize() {
			return _maxIdleSize;
		}
		
		public void setMaxIdleSize(int maxIdleSize) {
			_maxIdleSize = maxIdleSize;
		}
		
		public int getMaxActiveSize() {
			return _maxActiveSize;
		}
		
		public void setMaxActiveSize(int maxActiveSize) {
			_maxActiveSize = maxActiveSize;
		}
		
		public long getMaxWaitTime() {
			return _maxWaitTime;
		}
		
		public void setMaxWaitTime(long maxWaitTime) {
			_maxWaitTime = maxWaitTime;
		}
		
		public boolean isValidateOnBorrow() {
			return _validateOnBorrow;
		}
		
		public void setValidateOnBorrow(boolean validateOnBorrow) {
			_validateOnBorrow = validateOnBorrow;
		}
		
		public boolean isValidataOnReturn() {
			return _validataOnReturn;
		}
		
		public void setValidataOnReturn(boolean validataOnReturn) {
			_validataOnReturn = validataOnReturn;
		}
		
		public boolean isLifoStrategy() {
			return _lifoStrategy;
		}
		
		public void setLifoStrategy(boolean lifoStrategy) {
			_lifoStrategy = lifoStrategy;
		}
		
		public int getExhaustedStrategy() {
			return _exhaustedStrategy;
		}
		
		public void setExhaustedStrategy(int exhaustedStrategy) {
			_exhaustedStrategy = exhaustedStrategy;
		}
		
		
		public long getEvictDelayTime() {
			return _evictDelayTime;
		}
		
		public void setEvictDelayTime(long evictDelayTime) {
			_evictDelayTime = evictDelayTime;
		}
		
		public int getMinIdleSize() {
			return _minIdleSize;
		}
		
		public void setMinIdleSize(int minIdleSize) {
			_minIdleSize = minIdleSize;
		}
		
		public long getMinEvictIdleTime() {
			return _minEvictIdleTime;
		}
		
		public void setMinEvictIdleTime(long minEvictIdleTime) {
			_minEvictIdleTime = minEvictIdleTime;
		}
		
		public boolean isValidateOnEviction() {
			return _validateOnEviction;
		}
		
		public void setValidateOnEviction(boolean validateOnEviction) {
			_validateOnEviction = validateOnEviction;
		}
		
		public long getSoftMinEvictIdleTime() {
			return _softMinEvictIdleTime;
		}
		
		public void setSoftMinEvictIdleTime(long softMinEvictIdleTime) {
			_softMinEvictIdleTime = softMinEvictIdleTime;
		}
		
		public int getValidateNumOnEviction() {
			return _validateNumOnEviction;
		}
		
		public void setValidateNumOnEviction(int validateNumOnEviction) {
			_validateNumOnEviction = validateNumOnEviction;
		}
	}

}

