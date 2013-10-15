package com.mtoolkit.cache.callback;

import java.util.Map;

public interface KeyBatchGenerator<T> {
    
    @SuppressWarnings("unchecked")
	public Map<String, T> generateKeys(T... params);
    
}
