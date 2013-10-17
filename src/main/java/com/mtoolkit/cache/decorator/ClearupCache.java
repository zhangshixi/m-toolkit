package com.mtoolkit.cache.decorator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;
import com.mtoolkit.cache.support.AbstractCache.SucceedFuture;

/**
 * Auto clear cache decorator. 
 */
public class ClearupCache extends CacheDecorator {
    
    private long _clearInterval;
    private long _lastClearTime;
    
    public static final long DEF_CLEAR_INTERVAL = 60 * 60 * 1000L;
    
    public ClearupCache(Cache cache) {
        this(cache, DEF_CLEAR_INTERVAL);
    }
    
    public ClearupCache(Cache cache, long clearInterval) {
        super(cache);
        _clearInterval = clearInterval;
    }
    
    @Override
    public Cache startup() {
        _lastClearTime = System.currentTimeMillis();
        getCache().startup();
        return this;
    }

    @Override
    public void shutdown() {
        getCache().shutdown();
    }
    
    @Override
    public String getId() {
        return getCache().getId();
    }

    @Override
    public boolean isInitialized() {
        return getCache().isInitialized();
    }
    
    @Override
    public boolean containsKey(String key) {
        if (clearWhenStale()) {
            return false;
        } else {
            return getCache().containsKey(key);
        }
    }

    @Override
    public boolean put(String key, Object value) {
        clearWhenStale();
        return getCache().put(key, value);
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value) {
        clearWhenStale();
        return getCache().asyncPut(key, value);
    }
    
    @Override
    public boolean put(String key, Object value, long expiredTime) {
        clearWhenStale();
        return getCache().put(key, value, expiredTime);
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, long expiredTime) {
        clearWhenStale();
        return getCache().asyncPut(key, value, expiredTime);
    }
    
    @Override
    public boolean put(String key, Object value, CasOperation<Object> operation) {
        clearWhenStale();
        return getCache().put(key, value, operation);
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, CasOperation<Object> operation) {
        clearWhenStale();
        return getCache().asyncPut(key, value, operation);
    }

    @Override
    public boolean put(String key, Object value, long expiredTime, CasOperation<Object> operation) {
        clearWhenStale();
        return getCache().put(key, value, expiredTime, operation);
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, long expiredTime, CasOperation<Object> operation) {
        clearWhenStale();
        return getCache().asyncPut(key, value, expiredTime, operation);
    }
    
    @Override
    public <T> T get(String key) {
        if (clearWhenStale()) {
            return null;
        } else {
            return getCache().get(key);
        }
    }

    @Override
    public <T> Map<String, T> get(String[] keys) {
        if (clearWhenStale()) {
            return Collections.emptyMap();
        } else {
            return getCache().get(keys);
        }
    }

    @Override
    public <T> T remove(String key) {
        if (clearWhenStale()) {
            return null;
        } else {
            return getCache().remove(key);
        }
    }
    
    @Override
    public <T> Future<T> asyncRemove(String key) {
        if (clearWhenStale()) {
            return new SucceedFuture<T>(null);
        } else {
            return getCache().asyncRemove(key);
        }
    }

    @Override
    public <T> List<T> remove(String[] keys) {
        if (clearWhenStale()) {
            return Collections.emptyList();
        } else {
            return getCache().remove(keys);
        }
    }
    
    @Override
    public <T> Future<List<T>> asyncRemove(String[] keys) {
        if (clearWhenStale()) {
            List<T> resultList = Collections.emptyList();
            return new SucceedFuture<List<T>>(resultList);
        } else {
            return getCache().asyncRemove(keys);
        }
    }

    @Override
    public boolean clear() {
        _lastClearTime = System.currentTimeMillis();
        return getCache().clear();
    }
    
    @Override
    public Future<Boolean> asyncClear() {
        _lastClearTime = System.currentTimeMillis();
        return getCache().asyncClear();
    }
    
    @Override
    public long getNumber(String key) {
        // clear unsupported
        return getCache().getNumber(key);
    }
    
    @Override
    public long increase(String key, long value) {
        // clear unsupported
        return getCache().increase(key, value);
    }
    
    @Override
    public Future<Long> asyncIncrease(String key, long value) {
        // clear unsupported
        return getCache().asyncIncrease(key, value);
    }

    @Override
    public long decrease(String key, long value) {
        // clear unsupported
        return getCache().decrease(key, value);
    }
    
    @Override
    public Future<Long> asyncDecrease(String key, long value) {
        // clear unsupported
        return getCache().asyncDecrease(key, value);
    }
    
    // ---- private methods
    private boolean clearWhenStale() {
        long idleTime = System.currentTimeMillis() - _lastClearTime;
        if (idleTime > _clearInterval) {
            clear();
            return true;
        } else {
            return false;
        }
    }
    
}
