package com.mtoolkit.cache.decorator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;

public class ReadWriteCache extends CacheDecorator {

    private final ReadWriteLock _readWriteLock;
    
    public ReadWriteCache(Cache cache) {
        this(cache, new ReentrantReadWriteLock());
    }
    
    public ReadWriteCache(Cache cache, ReadWriteLock readWriteLock) {
        super(cache);
        if (readWriteLock == null) {
            throw new NullPointerException("readWriteLock");
        }
        _readWriteLock = readWriteLock;
    }
    
    @Override
    public Cache startup() {
        lockWrite();
        try {
            getCache().startup();
            return this;
        } finally {
            unLockWrite();
        }
    }

    @Override
    public void shutdown() {
        lockWrite();
        try {
            getCache().shutdown();
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public String getId() {
        lockRead();
        try {
            return getCache().getId();
        } finally {
            unLockRead();
        }
    }

    @Override
    public boolean isInitialized() {
        lockRead();
        try {
            return getCache().isInitialized();
        } finally {
            unLockRead();
        }
    }
    
    @Override
    public boolean containsKey(String key) {
        lockRead();
        try {
            return getCache().containsKey(key);
        } finally {
            unLockRead();
        }
    }

    @Override
    public boolean put(String key, Object value) {
        lockWrite();
        try {
            return getCache().put(key, value);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value) {
        lockWrite();
        try {
            return getCache().asyncPut(key, value);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public boolean put(String key, Object value, long expiredTime) {
        lockWrite();
        try {
            return getCache().put(key, value, expiredTime);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, long expiredTime) {
        lockWrite();
        try {
            return getCache().asyncPut(key, value, expiredTime);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public boolean put(String key, Object value, CasOperation<Object> operation) {
        lockWrite();
        try {
            return getCache().put(key, value, operation);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, CasOperation<Object> operation) {
        lockWrite();
        try {
            return getCache().asyncPut(key, value, operation);
        } finally {
            unLockWrite();
        }
    }

    @Override
    public boolean put(String key, Object value, long expiredTime, CasOperation<Object> operation) {
        lockWrite();
        try {
            return getCache().put(key, value, expiredTime, operation);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, long expiredTime, CasOperation<Object> operation) {
        lockWrite();
        try {
            return getCache().asyncPut(key, value, expiredTime, operation);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public <T> T get(String key) {
        lockRead();
        try {
            return getCache().get(key);
        } finally {
            unLockRead();
        }
    }

    @Override
    public <T> Map<String, T> get(String[] keys) {
        lockRead();
        try {
            return getCache().get(keys);
        } finally {
            unLockRead();
        }
    }

    @Override
    public <T> T remove(String key) {
        lockWrite();
        try {
            return getCache().remove(key);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public <T> Future<T> asyncRemove(String key) {
        lockWrite();
        try {
            return getCache().asyncRemove(key);
        } finally {
            unLockWrite();
        }
    }

    @Override
    public <T> List<T> remove(String[] keys) {
        lockWrite();
        try {
            return getCache().remove(keys);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public <T> Future<List<T>> asyncRemove(String[] keys) {
        lockWrite();
        try {
            return getCache().asyncRemove(keys);
        } finally {
            unLockWrite();
        }
    }

    @Override
    public boolean clear() {
        lockWrite();
        try {
            return getCache().clear();
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public Future<Boolean> asyncClear() {
        lockWrite();
        try {
            return getCache().asyncClear();
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public long getNumber(String key) {
        lockRead();
        try {
            return getCache().getNumber(key);
        } finally {
            unLockRead();
        }
    }
    
    @Override
    public long increase(String key, long value) {
        lockWrite();
        try {
            return getCache().increase(key, value);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public Future<Long> asyncIncrease(String key, long value) {
        lockWrite();
        try {
            return getCache().asyncIncrease(key, value);
        } finally {
            unLockWrite();
        }
    }

    @Override
    public long decrease(String key, long value) {
        lockWrite();
        try {
            return getCache().decrease(key, value);
        } finally {
            unLockWrite();
        }
    }
    
    @Override
    public Future<Long> asyncDecrease(String key, long value) {
        lockWrite();
        try {
            return getCache().asyncDecrease(key, value);
        } finally {
            unLockWrite();
        }
    }

    // ---- private methods
    private void lockRead() {
        _readWriteLock.readLock().lock();
    }
    
    private void unLockRead() {
        _readWriteLock.readLock().unlock();
    }
    
    private void lockWrite() {
        _readWriteLock.writeLock().lock();
    }
    
    private void unLockWrite() {
        _readWriteLock.writeLock().unlock();
    }
    
}