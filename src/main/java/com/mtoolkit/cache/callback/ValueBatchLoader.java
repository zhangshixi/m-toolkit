package com.mtoolkit.cache.callback;

import java.util.Map;

public interface ValueBatchLoader<K, V> {

    public Map<K, V> loadValues(K[] keys);

}
