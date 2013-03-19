package com.mtoolkit.visit.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mtoolkit.visit.StoreStrategy;
import com.mtoolkit.visit.VisitManager;

/**
 * Visit manager with specified fixed time.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 1/31/2012
 * @since 	JDK1.5
 */
public class TimingVisitManager implements VisitManager {

	/** store strategy */
	private StoreStrategy _strategy;
	/** delay time */
	private int _delayTime = DEFAULT_DELAY_TIME;
	
	private ExecutorService _visitService;
	private ScheduledExecutorService _storeService;
	private Map<String, Integer> _resourceMap;
	
	/** default delay time */
	public static final int DEFAULT_DELAY_TIME = 60;

	/** synchronized lock */
	private final Object _syncLock = new Object();
	
	// ---- constructors ---------------------------------------------------------
	public TimingVisitManager(StoreStrategy strategy) {
		this(strategy, DEFAULT_DELAY_TIME);
	}
	
	public TimingVisitManager(StoreStrategy strategy, int delayTime) {
		if (strategy == null) {
			throw new NullPointerException("strategy");
		}
		if (delayTime <= 0) {
			throw new IllegalArgumentException(
				  "Delay time must be more than 0: " + delayTime);
		}
		
		_strategy = strategy;
		_delayTime = delayTime;
		
		init();
	}
	
	// ---- public methods -----------------------------------------------
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
		
		try {
            doStore();
        } catch (Exception e) {
            throw new RuntimeException("Store exception.", e);
        }
	}
	
	// ---- private methods ------------------------------------------------------
	private void init() {
		_visitService = Executors.newCachedThreadPool();
		_storeService = Executors.newSingleThreadScheduledExecutor();
		_storeService.scheduleWithFixedDelay(
				new StoreTask(), _delayTime, _delayTime, TimeUnit.SECONDS);
		
		_resourceMap = new HashMap<String, Integer>();
	}
	
	private void doStore() throws Exception {
		Map<String, Integer> copy = null;
		synchronized (_syncLock) {
			copy = new HashMap<String, Integer>(_resourceMap);
			_resourceMap.clear();
		}
		
		for (Entry<String, Integer> entry : copy.entrySet()) {
			_strategy.store(entry.getKey(), entry.getValue().intValue());
		}
	}
	
	// ---- private inner classes ------------------------------------------------
	private class IncrementTask implements Runnable {

		private String _key;
		private int    _value;
		
		public IncrementTask(String key, int value) {
			_key   = key;
			_value = value;
		}
		
		@Override
		public void run() {
			synchronized (_syncLock) {
				if (_resourceMap.containsKey(_key)) {
					int total = _resourceMap.get(_key).intValue() + _value;
					_resourceMap.put(_key, total);
				} else {
					_resourceMap.put(_key, Integer.valueOf(_value));
				}
			}
		}
	}
	
	private class DecrementTask implements Runnable {
		
		private String _key;
		private int    _value;
		
		public DecrementTask(String key, int value) {
			_key   = key;
			_value = value;
		}

		@Override
		public void run() {
			synchronized (_syncLock) {
				if (_resourceMap.containsKey(_key)) {
					int total = _resourceMap.get(_key).intValue() - _value;
					if (total > 0) {
						_resourceMap.put(_key, total);
					} else {
						_resourceMap.remove(_key);
					}
				} else {
					_resourceMap.put(_key, Integer.valueOf(_value));
				}
			}
		}
	}
	
	private class StoreTask implements Runnable {

		@Override
		public void run() {
			try {
                doStore();
            } catch (Exception e) {
                throw new RuntimeException("Store exception.", e);
            }
		}
	}
	
}

