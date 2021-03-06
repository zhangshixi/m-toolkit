package com.mtoolkit.cache.decorator;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;

import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;

/**
 * Weak reference cache decorator.
 */
public class WeakCache extends CacheDecorator {

    private int _hardLinksNumber;
    private LinkedList<Object> _avoidGcHardLinks;
    private ReferenceQueue<Object> _gcEntryQueue;
    
    public static final int DEF_SIZE = 256;
    
    public WeakCache(Cache cache) {
        this(cache, DEF_SIZE);
    }
    
    public WeakCache(Cache cache, int size) {
        super(cache);
        _hardLinksNumber = size;
        _avoidGcHardLinks = new LinkedList<Object>();
        _gcEntryQueue = new ReferenceQueue<Object>();
    }
    
    public int getSize() {
        return _hardLinksNumber;
    }
    
    public void setSize(int size) {
        _hardLinksNumber = size;
    }

    @Override
    public boolean put(String key, Object value) {
        gcWeakEntries(false);
        return getCache().put(key, new WeakEntry(key, value, _gcEntryQueue));
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value) {
        gcWeakEntries(true);
        return getCache().asyncPut(key, new WeakEntry(key, value, _gcEntryQueue));
    }
    
    @Override
    public boolean put(String key, Object value, long expiredTime) {
        gcWeakEntries(false);
        return getCache().put(key, new WeakEntry(key, value, _gcEntryQueue), expiredTime);
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, long expiredTime) {
        gcWeakEntries(true);
        return getCache().asyncPut(key, new WeakEntry(key, value, _gcEntryQueue), expiredTime);
    }
    
    @Override
    public boolean put(String key, Object value, CasOperation<Object> operation) {
        gcWeakEntries(false);
        return getCache().put(key, new WeakEntry(key, value, _gcEntryQueue), operation);
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, CasOperation<Object> operation) {
        gcWeakEntries(true);
        return getCache().asyncPut(key, new WeakEntry(key, value, _gcEntryQueue), operation);
    }

    @Override
    public boolean put(String key, Object value, long expiredTime, CasOperation<Object> operation) {
        gcWeakEntries(false);
        return getCache().put(key, new WeakEntry(key, value, _gcEntryQueue), expiredTime, operation);
    }
    
    @Override
    public Future<Boolean> asyncPut(String key, Object value, long expiredTime, CasOperation<Object> operation) {
        gcWeakEntries(true);
        return getCache().asyncPut(key, new WeakEntry(key, value, _gcEntryQueue), expiredTime, operation);
    }

    @Override
    public <T> T get(String key) {
        WeakReference<T> waekReference = getCache().get(key);
        if (waekReference == null) {
            return null;
        }
        
        T value = waekReference.get();
        if (value == null) {
            getCache().asyncRemove(key);
        } else {
            _avoidGcHardLinks.addFirst(value);
            if (_avoidGcHardLinks.size() > _hardLinksNumber) {
                _avoidGcHardLinks.removeLast();
            }
        }
        
        return value;
    }

    @Override
    public <T> Map<String, T> get(String[] keys) {
        Map<String, WeakReference<T>> weakReferenceMap = getCache().get(keys);
        if (weakReferenceMap == null || weakReferenceMap.isEmpty()) {
            return Collections.emptyMap();
        }
        
        Set<String> removeKeySet = new HashSet<String>(); 
        Map<String, T> resultMap = new HashMap<String, T>();
        for (Entry<String, WeakReference<T>> entry : weakReferenceMap.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                continue;
            }
            
            T value = entry.getValue().get();
            if (value == null) {
                removeKeySet.add(entry.getKey());
            } else {
                resultMap.put(entry.getKey(), value);
            }
        }
        
        getCache().asyncRemove(removeKeySet.toArray(new String[0]));
        
        return resultMap;
    }

    @Override
    public <T> T remove(String key) {
        gcWeakEntries(false);
        return getCache().remove(key);
    }
    
    @Override
    public <T> Future<T> asyncRemove(String key) {
        gcWeakEntries(true);
        return getCache().asyncRemove(key);
    }
    
    @Override
    public <T> List<T> remove(String[] keys) {
        gcWeakEntries(false);
        return getCache().remove(keys);
    }
    
    @Override
    public <T> Future<List<T>> asyncRemove(String[] keys) {
        gcWeakEntries(true);
        return getCache().asyncRemove(keys);
    }

    @Override
    public boolean clear() {
        gcWeakEntries(false);
        _avoidGcHardLinks.clear();
        
        return super.clear();
    }
    
    @Override
    public Future<Boolean> asyncClear() {
        gcWeakEntries(true);
        _avoidGcHardLinks.clear();
        
        return super.asyncClear();
    }
    
    @Override
    public long getNumber(String key) {
        // weak reference unsupported
        return getCache().getNumber(key);
    }

    @Override
    public long increase(String key, long value) {
        // weak reference unsupported
        return getCache().increase(key, value);
    }
    
    @Override
    public Future<Long> asyncIncrease(String key, long value) {
        // weak reference unsupported
        return getCache().asyncIncrease(key, value);
    }
    
    @Override
    public long decrease(String key, long value) {
        // weak reference unsupported
        return getCache().decrease(key, value);
    }
    
    // ---- private methods
    private void gcWeakEntries(boolean async) {
        WeakEntry gcEntry = null;
        List<String> gcKeyList = new LinkedList<String>();
        
        while ((gcEntry = (WeakEntry) _gcEntryQueue.poll()) != null) {
            gcKeyList.add(gcEntry._key);
        }
        
        String[] removeKeys = gcKeyList.toArray(new String[0]);
        if (async) {
            asyncRemove(removeKeys);
        } else {
            remove(removeKeys);
        }
    }
    
    // ---- inner classes
    private static class WeakEntry extends WeakReference<Object> {
        
        private final String _key;
        
        private WeakEntry(String key, Object value, ReferenceQueue<Object> gcQueue) {
            super(value, gcQueue);
            _key = key;
        }
       
    }
    
}
