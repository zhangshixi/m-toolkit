package com.mtoolkit.visit.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mtoolkit.visit.StoreStrategy;
import com.mtoolkit.visit.VisitManager;

/**
 * Visit manager with specified count number.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 1/31/2012
 * @since 	JDK1.5
 */
public class CountingVisitManager implements VisitManager {
	
	/** store strategy */
	private StoreStrategy _strategy;
	/** count number */
	private int _countNum = DEFAULT_COUNT_NUMBER;
	
	private ExecutorService _visitService;
	private ExecutorService _storeService;
	private Map<String, Object> _keyLockMap;
	private Map<String, Integer> _resourceMap;
	
	/** default count number */
	public static final int DEFAULT_COUNT_NUMBER = 10;
	
	/** synchronized lock */
	private final Object _syncLock = new Object();
	
	// ---- constructors ---------------------------------------------------------
	public CountingVisitManager(StoreStrategy strategy) {
		this(strategy, DEFAULT_COUNT_NUMBER);
	}
	
	public CountingVisitManager(StoreStrategy strategy, int countNum) {
		if (strategy == null) {
			throw new NullPointerException("strategy");
		}
		if (countNum <= 0) {
			throw new IllegalArgumentException(
				  "Count number must be more than 0: " + countNum);
		}
		
		_strategy = strategy;
		_countNum = countNum;
		
		init();
	}
	
	// ---- public methods -------------------------------------------------------
	@Override
	public void increase(String key, int value) {
		_visitService.execute(new IncrementTask(key, value));
	}

	@Override
	public void decrease(String key, int value) {
		_visitService.execute(new DecrementTask(key, value));
	}

	public void destory() {
		if (_visitService != null) {
			_visitService.shutdown();
		}
		if (_storeService != null) {
			_storeService.shutdown();
		}
		
		synchronized (_syncLock) {
			for (Entry<String, Integer> entry : _resourceMap.entrySet()) {
				doStore(entry.getKey(), entry.getValue().intValue());
			}
		}
	}
	
	// ---- private methods ----------------------------------------------
	private void init() {
		_visitService = Executors.newCachedThreadPool();
		_storeService = Executors.newSingleThreadExecutor();
		
		_keyLockMap = new HashMap<String, Object>();
		_resourceMap = new HashMap<String, Integer>();
	}
	
	private void doStore(String key, int value) {
		// already got the key lock.
		_storeService.submit(new StoreTask(key, value));
	}
	
	private Object getKeyLock(String key) {
		Object keyLock = null;
		synchronized (_syncLock) {
			keyLock = _keyLockMap.get(key);
			if (keyLock == null) {
				keyLock = new Object();
				_keyLockMap.put(key, keyLock);
			}
		}
		
		return keyLock;
	}
	
	private Object removeKeyLock(String key) {
		synchronized (_syncLock) {
			return _keyLockMap.remove(key);
		}
	}
	
	// ---- private inner classes ------------------------------------------------
	private class IncrementTask implements Runnable {
		
		private String _key;
		private int    _value;
		private Object _keyLock;
		
		public IncrementTask(String key, int value) {
			_key   = key;
			_value = value;
		}

		@Override
		public void run() {
			if (_value >= _countNum) {
				doStore(_key, _value);
			} else {
				_keyLock = getKeyLock(_key);
				synchronized (_keyLock) {
					if (_resourceMap.containsKey(_key)) {
						int total = _resourceMap.get(_key).intValue() + _value;
						if (total >= _countNum) {
							doStore(_key, total);
							
							_resourceMap.remove(_key);
							removeKeyLock(_key);
						} else {
							_resourceMap.put(_key, Integer.valueOf(total));
						}
					} else {
						_resourceMap.put(_key, Integer.valueOf(_value));
					}
				}
			}
		}
	}
	
	private class DecrementTask implements Runnable {
		
		private String _key;
		private int    _value;
		private Object _keyLock;
		
		public DecrementTask(String key, int value) {
			_key   = key;
			_value = value;
		}

		@Override
		public void run() {
			_keyLock = getKeyLock(_key);
			synchronized (_keyLock) {
				if (_resourceMap.containsKey(_key)) {
					int total = _resourceMap.get(_key).intValue() - _value;
					if (total > 0) {
						_resourceMap.put(_key, Integer.valueOf(total));
					} else {
						_resourceMap.remove(_key);
						_resourceMap.remove(_key);
						removeKeyLock(_key);
					}
				} else {
					removeKeyLock(_key);
				}
			}
		}
	}
	
	private class StoreTask implements Callable<Void> {

		private String _key;
		private int    _value;
		
		public StoreTask(String key, int value) {
			_key   = key;
			_value = value;
		}

        @Override
        public Void call() throws Exception {
            _strategy.store(_key, _value);
            return null;
        }
		
	}
	
}

