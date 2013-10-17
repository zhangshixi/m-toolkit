package com.mtoolkit.cache.decorator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;

/**
 * Abstract decorate cache class, children can extends this class
 * and decorate the given repository resources.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 */
public abstract class CacheDecorator implements Cache {

	/** cache engine */
	private Cache _cache;
	/** cache initialize flag */
    private volatile boolean _initialized;
	
    public CacheDecorator() {
    }
	
	/**
	 * Creates a decorate cache repository engine with the specified cache.
	 * 
	 * @param cache cache repository engine.
	 */
	public CacheDecorator(Cache cache) {
	    setCache(cache);
	}
	
	public void setCache(Cache cache) {
	    checkCache(cache);
	    _cache = cache;
	    _initialized = true;
	}
	
	/**
	 * Return the current cache repository engine.
	 * 
	 * @return current cache repository engine.
	 */
	public Cache getCache() {
		return _cache;
	}

	
	@Override
	public Cache startup() {
	    getCache().startup();
		return this;
	}

	@Override
	public void shutdown() {
	    getCache().shutdown();
		_initialized = false;
	}
	
	@Override
	public String getId() {
		return getCache().getId();
	}

	@Override
	public boolean isInitialized() {
		return _initialized && getCache().isInitialized();
	}
	
	@Override
	public boolean containsKey(String key) {
	    checkStates();
		return getCache().containsKey(key);
	}

	@Override
	public boolean put(String key, Object value) {
	    checkStates();
		return getCache().put(key, value);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value) {
	    checkStates();
		return getCache().asyncPut(key, value);
	}
	
	@Override
	public boolean put(String key, Object value, long expiredTime) {
	    checkStates();
		return getCache().put(key, value, expiredTime);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime) {
	    checkStates();
		return getCache().asyncPut(key, value, expiredTime);
	}
	
	@Override
	public boolean put(String key, Object value, CasOperation<Object> operation) {
	    checkStates();
		return getCache().put(key, value, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, CasOperation<Object> operation) {
	    checkStates();
		return getCache().asyncPut(key, value, operation);
	}

	@Override
	public boolean put(String key, Object value, long expiredTime, CasOperation<Object> operation) {
	    checkStates();
		return getCache().put(key, value, expiredTime, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime, CasOperation<Object> operation) {
	    checkStates();
		return getCache().asyncPut(key, value, expiredTime, operation);
	}
	
	@Override
	public <T> T get(String key) {
	    checkStates();
		return getCache().get(key);
	}

	@Override
	public <T> Map<String, T> get(String[] keys) {
	    checkStates();
		return getCache().get(keys);
	}

	@Override
	public <T> T remove(String key) {
	    checkStates();
		return getCache().remove(key);
	}
	
	@Override
	public <T> Future<T> asyncRemove(String key) {
	    checkStates();
		return getCache().asyncRemove(key);
	}

	@Override
	public <T> List<T> remove(String[] keys) {
	    checkStates();
		return getCache().remove(keys);
	}
	
	@Override
	public <T> Future<List<T>> asyncRemove(String[] keys) {
	    checkStates();
		return getCache().asyncRemove(keys);
	}

	@Override
	public boolean clear() {
	    checkStates();
		return getCache().clear();
	}
	
	@Override
	public Future<Boolean> asyncClear() {
	    checkStates();
		return getCache().asyncClear();
	}
	
	@Override
	public long getNumber(String key) {
	    checkStates();
		return getCache().getNumber(key);
	}
	
	@Override
	public long increase(String key, long value) {
	    checkStates();
		return getCache().increase(key, value);
	}
	
	@Override
	public Future<Long> asyncIncrease(String key, long value) {
	    checkStates();
		return getCache().asyncIncrease(key, value);
	}

	@Override
	public long decrease(String key, long value) {
	    checkStates();
		return getCache().decrease(key, value);
	}
	
	@Override
	public Future<Long> asyncDecrease(String key, long value) {
	    checkStates();
		return getCache().asyncDecrease(key, value);
	}
	
	protected void checkCache(Cache cache) {
	    if (cache == null) {
	        throw new NullPointerException("cache");
	    }
	}
	
	protected void checkStates() {
        if (!_initialized) {
            throw new IllegalStateException("CacheDecorator has not initialized.");
        }
    }
	
}