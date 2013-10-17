package com.mtoolkit.cache.callback;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;
import com.mtoolkit.cache.decorator.CacheDecorator;
import com.mtoolkit.cache.support.AbstractCache.SucceedFuture;

public class CallbackCache extends CacheDecorator {

	private String _version;

	public static final String DEFAULT_CACHE_VERSION = "1.0";
	
	// ---- constructors
    public CallbackCache() {
    }
	
	public CallbackCache(Cache cache) {
		this(cache, DEFAULT_CACHE_VERSION);
	}
	
	public CallbackCache(Cache cache, String version) {
	    super(cache);
	    _version = version;
	}

	// ---- put methods
	public boolean put(KeyGenerator key, Object value) {
	    return put(generateKey(key), value);
	}

	public boolean put(KeyGenerator key, ValueLoader<Object> value) {
		return put(key, loadValue(value));
	}
	
	public boolean put(KeyGenerator key, Object value, long expiredTime) {
	    return put(generateKey(key), value, expiredTime);
	}
	
	public boolean put(KeyGenerator key, ValueLoader<Object> value, long expiredTime) {
		return put(key, loadValue(value), expiredTime);
	}
	
	public boolean put(KeyGenerator key, Object value, CasOperation<Object> operation) {
		return put(generateKey(key), value, operation);
	}

	public boolean put(KeyGenerator key, ValueLoader<Object> value, CasOperation<Object> operation) {
		return put(key, loadValue(value), operation);
	}
	
	public boolean put(KeyGenerator key, Object value, long expiredTime, CasOperation<Object> operation) {
		return put(generateKey(key), value, expiredTime, operation);
	}
	
	public boolean put(KeyGenerator key, ValueLoader<Object> value, long expiredTime, CasOperation<Object> operation) {
		return put(key, loadValue(value), expiredTime, operation);
	}
	
	public Future<Boolean> asyncPut(KeyGenerator key, Object value) {
	    return asyncPut(generateKey(key), value);
	}

	public Future<Boolean> asyncPut(KeyGenerator key, ValueLoader<Object> value) {
		return asyncPut(key, loadValue(value));
	}
	
	public Future<Boolean> asyncPut(KeyGenerator key, Object value, long expiredTime) {
	    return asyncPut(generateKey(key), value, expiredTime);
	}
	
	public Future<Boolean> asyncPut(KeyGenerator key, ValueLoader<Object> value, long expiredTime) {
		return asyncPut(key, loadValue(value), expiredTime);
	}
	
	public Future<Boolean> asyncPut(KeyGenerator key, Object value, CasOperation<Object> operation) {
		return asyncPut(generateKey(key), value, operation);
	}

	public Future<Boolean> asyncPut(KeyGenerator key, ValueLoader<Object> value, CasOperation<Object> operation) {
		return asyncPut(key, loadValue(value), operation);
	}
	
	public Future<Boolean> asyncPut(KeyGenerator key, Object value, long expiredTime, CasOperation<Object> operation) {
		return asyncPut(generateKey(key), value, expiredTime, operation);
	}
	
	public Future<Boolean> asyncPut(KeyGenerator key, ValueLoader<Object> value, long expiredTime, CasOperation<Object> operation) {
		return asyncPut(key, loadValue(value), expiredTime, operation);
	}

	// ---- get methods
	public <V> V get(KeyGenerator key) {
		return get(generateKey(key));
	}
	
	public <V> V get(KeyGenerator key, ValueLoader<V> value) {
		String cacheKey = generateKey(key);
		V cacheValue = get(cacheKey);
		if (cacheValue == null) {
			cacheValue = loadValue(value);
			asyncPut(cacheKey, cacheValue);
		}
		return cacheValue;
	}
	
	public <V> V get(KeyGenerator key, ValueLoader<V> value, long expiredTime) {
		String cacheKey = generateKey(key);
		V cacheValue = get(cacheKey);
		if (cacheValue == null) {
			cacheValue = loadValue(value);
			asyncPut(cacheKey, cacheValue, expiredTime);
		}
		return cacheValue;
	}
	
	public <V> V get(KeyGenerator key, ValueLoader<V> value, CasOperation<Object> operation) {
		String cacheKey = generateKey(key);
		V cacheValue = get(cacheKey);
		if (cacheValue == null) {
			cacheValue = loadValue(value);
			asyncPut(cacheKey, cacheValue, operation);
		}
		return cacheValue;
	}
	
	public <V> V get(KeyGenerator key, ValueLoader<V> value, long expiredTime, CasOperation<Object> operation) {
		String cacheKey = generateKey(key);
		V cacheValue = get(cacheKey);
		if (cacheValue == null) {
			cacheValue = loadValue(value);
			asyncPut(cacheKey, cacheValue, expiredTime, operation);
		}
		return cacheValue;
	}
	
	public <K, V> Map<K, V> gets(K[] params, KeyBatchGenerator<K> key, ValueBatchLoader<K, V> value) {
	    Map<String, K> keyMap = generateKeys(params, key);
	    if (keyMap == null || keyMap.isEmpty()) {
	        return Collections.emptyMap();
	    }
	    
	    String[] queryKeys = keyMap.keySet().toArray(new String[0]);
	    Map<K, String> unCachedKeys = new HashMap<K, String>();
	    
	    // query from cache
	    Map<String, V> cachedValues = get(queryKeys);
	    for (String k : queryKeys) {
	        if (!cachedValues.containsKey(k)) {
	            unCachedKeys.put(keyMap.get(k), k);
	        }
	    }
	    
	    // query from loader
	    Map<K, V> loadedValues = null;
	    if (!unCachedKeys.isEmpty()) {
	        @SuppressWarnings("unchecked")
            K[] loadKeys = (K[]) unCachedKeys.keySet().toArray();
	        loadedValues = value.loadValues(loadKeys);
	        
	        // put to cache
	        for (Entry<K, V> entry : loadedValues.entrySet()) {
                asyncPut(unCachedKeys.get(entry.getKey()), entry.getValue());
            }
	    }
	    
	    // build result
	    Map<K, V> resultMap = new HashMap<K, V>();
	    for (Entry<String, V> entry : cachedValues.entrySet()) {
	        resultMap.put(keyMap.get(entry.getKey()), entry.getValue());
	    }
	    if (loadedValues != null) {
	        for (Entry<K, V> entry : loadedValues.entrySet()) {
	            resultMap.put(entry.getKey(), entry.getValue());
	        }
	    }
	    
	    return resultMap;
	}
	
	// ---- remove methods
	public <T> T remove(KeyGenerator key) {
		return remove(generateKey(key));
	}
	
	public <T> Future<T> asyncRemove(KeyGenerator key) {
	    return asyncRemove(generateKey(key));
	}
	
	public <T> List<T> removes(Object[] params, KeyBatchGenerator<Object> key) {
	    Map<String, Object> keyMap = generateKeys(params, key);
	    if (keyMap == null || keyMap.isEmpty()) {
	        return Collections.emptyList();
	    } else {
	        String[] cacheKeys = keyMap.keySet().toArray(new String[0]);
	        return remove(cacheKeys);
	    }
	}
	
	public <T> Future<List<T>> asyncRemoves(Object[] params, KeyBatchGenerator<Object> key) {
	    Map<String, Object> keyMap = generateKeys(params, key);
	    if (keyMap == null || keyMap.isEmpty()) {
	        List<T> result = Collections.emptyList();
            return new SucceedFuture<List<T>>(result);
        } else {
            String[] cacheKeys = keyMap.keySet().toArray(new String[0]);
            return asyncRemove(cacheKeys);
        }
	}
	
    // --- number operation
    public long getNumber(KeyGenerator key) {
        return getNumber(generateKey(key));
    }
    
    public long increase(KeyGenerator key, long value) {
        return increase(generateKey(key), value);
    }
    
    public long increase(KeyGenerator key, ValueLoader<Long> value) {
        return increase(generateKey(key), loadValue(value).longValue());
    }
    
    public Future<Long> asyncIncrease(KeyGenerator key, long value) {
        return asyncIncrease(generateKey(key), value);
    }
    
    public Future<Long> asyncIncrease(KeyGenerator key, ValueLoader<Long> value) {
        return asyncIncrease(generateKey(key), loadValue(value).longValue());
    }

    public long decrease(KeyGenerator key, long value) {
        return decrease(generateKey(key), value);
    }
    
    public long decrease(KeyGenerator key, ValueLoader<Long> value) {
        return decrease(generateKey(key), loadValue(value).longValue());
    }
    
    public Future<Long> asyncDecrease(KeyGenerator key, long value) {
        return asyncDecrease(generateKey(key), value);
    }
    
    public Future<Long> asyncDecrease(KeyGenerator key, ValueLoader<Long> value) {
        return asyncDecrease(generateKey(key), loadValue(value).longValue());
    }
    
    // ---- reload methods
    public boolean reload() {
        return false;
    }
    
    public boolean asyncReload() {
        return false;
    }
    
	// ---- private methods
	private <T> String generateKey(KeyGenerator key) {
        return buildVersionCacheKey(key.generateKey());
    }
	
	private <T> Map<String, T> generateKeys(T[] param, KeyBatchGenerator<T> key) {
	    Map<String, T> keyMap = key.generateKeys(param);
	    if (keyMap == null || keyMap.isEmpty()) {
	        return Collections.emptyMap();
	    } else {
	        Map<String, T> resultMap = new HashMap<String, T>(keyMap.size(), 1F);
	        for (Entry<String, T> entry : keyMap.entrySet()) {
	            resultMap.put(buildVersionCacheKey(entry.getKey()), entry.getValue());
	        }
	        return resultMap;
	    }
	}
	
	private String buildVersionCacheKey(String key) {
	    return _version + "-" + key;
	}
	
	private <T> T loadValue(ValueLoader<T> value) {
	    return value.loadValue();
	}
	
}