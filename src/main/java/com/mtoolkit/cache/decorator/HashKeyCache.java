package com.mtoolkit.cache.decorator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;
import com.mtoolkit.hash.Hash;
import com.mtoolkit.hash.support.GenericHash;

/**
 * A decorate cache repository engine that provides use hash 
 * algorithm to hash the cache key before put into the cache repository.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * 
 * @see		com.mtoolkit.hash.Hash
 */
public class HashKeyCache extends CacheDecorator {

	private final Hash _hash;
	
	/** cache key prefix */
	public static final String CACHE_KEY_PREFIX = "cache.hash.key.";
	
	public HashKeyCache(Cache cache) {
		this(cache, GenericHash.getInstance());
	}
	
	public HashKeyCache(Cache cache, Hash hash) {
		super(cache);
		_hash = hash;
	}
	
	@Override
	public boolean containsKey(String key) {
		return getCache().containsKey(hashCodeKey(key));
	}

	@Override
	public boolean put(String key, Object value) {
		return getCache().put(hashCodeKey(key), value);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value) {
		return getCache().asyncPut(hashCodeKey(key), value);
	}
	
	@Override
	public boolean put(String key, Object value, long expiredTime) {
		return getCache().put(hashCodeKey(key), value, expiredTime);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime) {
		return getCache().asyncPut(hashCodeKey(key), value, expiredTime);
	}

	@Override
	public boolean put(String key, Object value, CasOperation<Object> operation) {
		return getCache().put(hashCodeKey(key), value, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, CasOperation<Object> operation) {
		return getCache().asyncPut(hashCodeKey(key), value, operation);
	}

	@Override
	public boolean put(String key, Object value, long expiredTime, CasOperation<Object> operation) {
		return getCache().put(hashCodeKey(key), value, expiredTime, operation);
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime, CasOperation<Object> operation) {
		return getCache().asyncPut(hashCodeKey(key), value, expiredTime, operation);
	}

	@Override
	public <T> T get(String key) {
		return getCache().get(hashCodeKey(key));
	}

	@Override
	public <T> Map<String, T> get(String[] keys) {
		if (keys == null || keys.length == 0) {
			return getCache().get(keys);
		}

		Map<String, String> keyMap = new HashMap<String, String>(keys.length, 1F);
		for (String key : keys) {
			if (key != null && !key.isEmpty()) {
				keyMap.put(hashCodeKey(key), key);
			}
		}

		String[] hashKeys = keyMap.keySet().toArray(new String[keyMap.size()]);
		Map<String, T> values = getCache().get(hashKeys);

		Map<String, T> resultMap = new HashMap<String, T>(keys.length, 1F);
		for (Entry<String, T> entry : values.entrySet()) {
			resultMap.put(keyMap.get(entry.getKey()), entry.getValue());
		}

		return Collections.unmodifiableMap(resultMap);
	}

	@Override
	public <T> T remove(String key) {
		return getCache().remove(hashCodeKey(key));
	}
	
	@Override
	public <T> Future<T> asyncRemove(String key) {
		return getCache().asyncRemove(hashCodeKey(key));
	}

	@Override
	public <T> List<T> remove(String[] keys) {
		if (keys == null || keys.length == 0) {
			return getCache().remove(keys);
		}

		String[] hashKeys = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			hashKeys[i] = hashCodeKey(keys[i]);
		}

		return getCache().remove(hashKeys);
	}
	
	@Override
	public <T> Future<List<T>> asyncRemove(String[] keys) {
		if (keys == null || keys.length == 0) {
			return getCache().asyncRemove(keys);
		}
		
		String[] hashKeys = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			hashKeys[i] = hashCodeKey(keys[i]);
		}
		
		return getCache().asyncRemove(hashKeys);
	}
	
	@Override
	public long getNumber(String key) {
		return getCache().getNumber(hashCodeKey(key));
	}
	
	@Override
	public long increase(String key, long value) {
		return getCache().increase(hashCodeKey(key), value);
	}
	
	@Override
	public Future<Long> asyncIncrease(String key, long value) {
		return getCache().asyncIncrease(hashCodeKey(key), value);
	}
	
	@Override
	public long decrease(String key, long value) {
		return getCache().decrease(hashCodeKey(key), value);
	}
	
	@Override
	public Future<Long> asyncDecrease(String key, long value) {
		return getCache().asyncDecrease(hashCodeKey(key), value);
	}
	
	// ---- private methods
	private String hashCodeKey(String key) {
		if (key == null || key.isEmpty()) {
			return key;
		}
		return CACHE_KEY_PREFIX + _hash.hash(key);
	}

}

