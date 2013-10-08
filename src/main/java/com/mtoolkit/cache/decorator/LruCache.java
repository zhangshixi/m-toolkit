package com.mtoolkit.cache.decorator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;

/**
 * LRU (least recently used) cache decorator
 */
public class LruCache extends CacheDecorator {

	private int _size;
    private String _eldestKey;
    private Map<String, String> _keyMap;
    
    public static final int DEF_SIZE = 1024;

    public LruCache(Cache cache) {
        this(cache, DEF_SIZE);
    }
    
    public LruCache(Cache cache, int size) {
    	super(cache);
    	setSize(DEF_SIZE);
    }

    public int getSize() {
        return _size;
    }
    
    public void setSize(final int size) {
        _size = size;
        _keyMap = new LinkedHashMap<String, String>(size, .75F, true) {
        	
			private static final long serialVersionUID = -2640083324397315910L;
			
			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<String, String> eldest) {
				boolean tooLarge = size() > size;
		        if (tooLarge) {
		        	_eldestKey = eldest.getKey();
		        }
		        return tooLarge;
			}
        };
    }

    @Override
	public boolean put(String key, Object value) {
    	cycleKeyMap(key, false);
		return getDelegateCache().put(key, value);
	}
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value) {
    	cycleKeyMap(key, true);
    	return getDelegateCache().asyncPut(key, value);
    }
	
	@Override
	public boolean put(String key, Object value, long expiredTime) {
		cycleKeyMap(key, false);
		return getDelegateCache().put(key, value, expiredTime);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime) {
		cycleKeyMap(key, true);
		return getDelegateCache().asyncPut(key, value, expiredTime);
	}

	@Override
	public boolean put(String key, Object value, Date expiredDate) {
	    cycleKeyMap(key, false);
	    return getDelegateCache().put(key, value, expiredDate);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, Date expiredDate) {
		cycleKeyMap(key, true);
		return getDelegateCache().asyncPut(key, value, expiredDate);
	}
	
	@Override
	public boolean put(String key, Object value, CasOperation<Object> operation) {
		cycleKeyMap(key, false);
		return getDelegateCache().put(key, value, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, CasOperation<Object> operation) {
	    cycleKeyMap(key, true);
	    return getDelegateCache().asyncPut(key, value, operation);
	}
	
	@Override
	public boolean put(String key, Object value, long expiredTime, CasOperation<Object> operation) {
	    cycleKeyMap(key, false);
	    return getDelegateCache().put(key, value, expiredTime, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime, CasOperation<Object> operation) {
		cycleKeyMap(key, true);
		return getDelegateCache().asyncPut(key, value, expiredTime, operation);
	}
	
	@Override
	public boolean put(String key, Object value, Date expiredDate, CasOperation<Object> operation) {
		cycleKeyMap(key, false);
		return getDelegateCache().put(key, value, expiredDate, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, Date expiredDate, CasOperation<Object> operation) {
		cycleKeyMap(key, true);
		return getDelegateCache().asyncPut(key, value, expiredDate, operation);
	}
	
    @Override
    public boolean clear() {
    	boolean result = super.clear();
    	_keyMap.clear();
    	return result;
    }
    
    @Override
    public Future<Boolean> asyncClear() {
    	Future<Boolean> result = super.asyncClear();
    	_keyMap.clear();
    	return result;
    }
    
    @Override
    public long increase(String key, long value) {
        cycleKeyMap(key, false);
        return getDelegateCache().increase(key, value);
    }
    
    @Override
    public Future<Long> asyncIncrease(String key, long value) {
        cycleKeyMap(key, true);
        return getDelegateCache().asyncIncrease(key, value);
    }

    @Override
    public long decrease(String key, long value) {
        cycleKeyMap(key, false);
        return getDelegateCache().decrease(key, value);
    }
    
    @Override
    public Future<Long> asyncDecrease(String key, long value) {
        cycleKeyMap(key, true);
        return getDelegateCache().asyncDecrease(key, value);
    }
    
    // ---- private methods
    private void cycleKeyMap(String key, boolean async) {
    	_keyMap.put(key, key);
    	if (_eldestKey != null) {
    		if (async) {
    			asyncRemove(_eldestKey);
    		} else {
    			remove(_eldestKey);
    		}
    	    _eldestKey = null;
    	}
    }

}
