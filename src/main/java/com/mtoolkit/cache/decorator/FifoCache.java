package com.mtoolkit.cache.decorator;

import java.util.LinkedList;
import java.util.concurrent.Future;

import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;

/**
 * FIFO (first in, first out) cache decorator
 */
public class FifoCache extends CacheDecorator {

	private int _size;
    private LinkedList<String> _keyList;
    
    public static final int DEF_SIZE = 1024;

    public FifoCache() {
    }
    
    public FifoCache(Cache cache) {
        this(cache, DEF_SIZE);
    }
    
    public FifoCache(Cache cache, int size) {
    	super(cache);
    	_size = size;
    }
    
    public int getSize() {
        return _size;
    }
    
    public void setSize(int size) {
        _size = size;
    }
    
    @Override
    public Cache startup() {
        super.startup();
        _keyList = new LinkedList<String>();
        return this;
    }
    
    @Override
    public void shutdown() {
        super.shutdown();
        _size = 0;
        _keyList = null;
    }

    @Override
	public boolean put(String key, Object value) {
    	cycleKeyList(key, false);
    	return getCache().put(key, value);
    }
    
    @Override
	public Future<Boolean> asyncPut(String key, Object value) {
    	cycleKeyList(key, true);
		return getCache().asyncPut(key, value);
	}
    
    @Override
	public boolean put(String key, Object value, long expiredTime) {
    	cycleKeyList(key, false);
    	return getCache().put(key, value, expiredTime);
    }
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime) {
		cycleKeyList(key, true);
		return getCache().asyncPut(key, value, expiredTime);
	}
	
	@Override
	public boolean put(String key, Object value, CasOperation<Object> operation) {
		cycleKeyList(key, false);
		return getCache().put(key, value, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, CasOperation<Object> operation) {
		cycleKeyList(key, true);
		return getCache().asyncPut(key, value, operation);
	}
	
	@Override
	public boolean put(String key, Object value, long expiredTime, CasOperation<Object> operation) {
		cycleKeyList(key, false);
		return getCache().put(key, value, expiredTime, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime, CasOperation<Object> operation) {
		cycleKeyList(key, true);
		return getCache().asyncPut(key, value, expiredTime, operation);
	}

	@Override
    public boolean clear() {
		boolean result = super.clear();
    	_keyList.clear();
    	return result;
	}
	
    @Override
    public Future<Boolean> asyncClear() {
    	Future<Boolean> result = super.asyncClear();
    	_keyList.clear();
    	return result;
    }
	
	@Override
	public long increase(String key, long value) {
		cycleKeyList(key, false);
		return getCache().increase(key, value);
	}
	
	@Override
	public Future<Long> asyncIncrease(String key, long value) {
		cycleKeyList(key, true);
		return getCache().asyncIncrease(key, value);
	}

	@Override
	public long decrease(String key, long value) {
		cycleKeyList(key, false);
		return getCache().decrease(key, value);
	}
	
	@Override
	public Future<Long> asyncDecrease(String key, long value) {
		cycleKeyList(key, true);
		return getCache().asyncDecrease(key, value);
	}
    
    // ---- private methods
    private void cycleKeyList(String key, boolean async) {
        _keyList.addLast(key);
        if (_keyList.size() > _size) {
        	String oldestKey = _keyList.removeFirst();
        	if (async) {
        		asyncRemove(oldestKey);
        	} else {
        		remove(oldestKey);
        	}
        }
    }

}
