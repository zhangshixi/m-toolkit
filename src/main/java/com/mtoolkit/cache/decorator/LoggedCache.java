package com.mtoolkit.cache.decorator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.mlogger.Loggers;
import com.mtoolkit.cache.Cache;
import com.mtoolkit.cache.CasOperation;

public class LoggedCache extends CacheDecorator {

	private static final Loggers LOGGER = Loggers.getLoggers(Cache.class);
	
	public LoggedCache(Cache cache) {
		super(cache);
	}
	
	@Override
	public Cache startup() {
		if (isInitialized()) {
			LOGGER.warn("Cache:[id={0}] has been initialized.", getId());
		}
		
		getCache().startup();
		
		LOGGER.info("Cache:[id={0}] startup succeed.", getId());
		
		return this;
	}
	
	@Override
	public void shutdown() {
		if (!isInitialized()) {
			LOGGER.warn("Cache:[id={0}] has not been initialized.", getId());
		}
		
		getCache().shutdown();
		
		LOGGER.info("Cache:[id={0}] shutdown succeed.", getId());
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
		boolean result = getCache().containsKey(key);
		LOGGER.debug("Cache contains key test - Argument:[key={0}] - Result:[{1}].", 
		    key, Boolean.valueOf(result));
		return result;
	}

	@Override
	public boolean put(String key, Object value) {
		boolean result = getCache().put(key, value);
		LOGGER.debug("Put value into cache - Argument:[key={0}, value={1}] - Result:[{2}].", 
		    key, value, Boolean.valueOf(result));
		return result;
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value) {
		Future<Boolean> result = getCache().asyncPut(key, value);
		LOGGER.debug("Async put value into cache - Argument:[key={0}, value={1}].", 
		    key, value);
		return result;
	}
	
	@Override
	public boolean put(String key, Object value, long expiredTime) {
		boolean result = getCache().put(key, value, expiredTime);
		LOGGER.debug("Put value into cache - Argument:[key={0}, value={1}, expiredTime={2}] - Result:[{3}].", 
		    key, value, Long.valueOf(expiredTime), Boolean.valueOf(result));
		return result;
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime) {
		Future<Boolean> result = getCache().asyncPut(key, value, expiredTime);
		LOGGER.debug("Async put value into cache - Argument:[key={0}, value={1}, expiredTime[{2}].", 
		    key, value, Long.valueOf(expiredTime));
		return result;
	}
	
	@Override
	public boolean put(String key, Object value, CasOperation<Object> operation) {
		boolean result = getCache().put(key, value, operation);
		LOGGER.debug("Put value into cache - Argument:[key={0}, value={1}, casOperation={2}] - Result:[{3}].", 
		    key, value, operation, Boolean.valueOf(result));
		return result;
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, CasOperation<Object> operation) {
		Future<Boolean> result = getCache().asyncPut(key, value, operation);
		LOGGER.debug("Async put value into cache - Argument:[key={0}, value={1}, casOperation={2}].", 
		    key, value, operation);
		return result;
	}

	@Override
	public boolean put(String key, Object value, long expiredTime, CasOperation<Object> operation) {
		boolean result = getCache().put(key, value, expiredTime, operation);
		LOGGER.debug("Put value into cache - Argument:[key={0}, value={1}, expiredTime={2}, casOperation={3}] - Result:[{4}].", 
			key, value, Long.valueOf(expiredTime), operation, Boolean.valueOf(result));
		return result;
	}
	
	@Override
	public Future<Boolean> asyncPut(String key, Object value, long expiredTime, CasOperation<Object> operation) {
		Future<Boolean> result = getCache().asyncPut(key, value, expiredTime, operation);
		LOGGER.debug("Async put value into cache - Argument:[key={0}, value={1}, expiredTime={2}, casOperation={3}].", 
			key, value, Long.valueOf(expiredTime), operation);
		return result;
	}
	
	@Override
	public <T> T get(String key) {
		T result =  getCache().get(key);
		LOGGER.debug("Get value from cache - Argument:[key={0}] - Result:[{1}].", 
		    key, result);
		return result;
	}

	@Override
	public <T> Map<String, T> get(String[] keys) {
		Map<String, T> results = getCache().get(keys);
		LOGGER.debug("Batch get value from cache - Argument:[key={0}] - Result:[{1}].", 
		    Arrays.toString(keys), results);
		return results;
	}

	@Override
	public <T> T remove(String key) {
		T result = getCache().remove(key);
		LOGGER.debug("Remove value from cache - Argument:[key={0}] - Result:[{1}].", 
		    key, result);
		return result;
	}
	
	@Override
	public <T> Future<T> asyncRemove(String key) {
		Future<T> result =  getCache().asyncRemove(key);
		LOGGER.debug("Async remove value from cache - Argument:[key={0}].", 
		    key);
		return result;
	}

	@Override
	public <T> List<T> remove(String[] keys) {
		List<T> results = getCache().remove(keys);
		LOGGER.debug("Batch remove value from cache - Argument:[key={0}] - Result:[{1}].", 
		    Arrays.toString(keys), results);
		return results;
	}
	
	@Override
	public <T> Future<List<T>> asyncRemove(String[] keys) {
		Future<List<T>> results = getCache().asyncRemove(keys);
		LOGGER.debug("Async batch remove value from cache - Argument:[key={0}].", 
		    Arrays.toString(keys));
		return results;
	}

	@Override
	public boolean clear() {
		boolean result = getCache().clear();
		LOGGER.debug("Clear cache - Result:[{0}].", 
		    result);
		return result;
	}
	
	@Override
	public Future<Boolean> asyncClear() {
		Future<Boolean> result = getCache().asyncClear();
		LOGGER.debug("Async clear cache.");
		return result;
	}
	
	@Override
	public long getNumber(String key) {
		long result = getCache().getNumber(key);
		LOGGER.debug("Get number from cache - Argument:[key={0}] - Result:[{1}].", 
		    key, Long.valueOf(result));
		return result;
	}
	
	@Override
	public long increase(String key, long value) {
		long result = getCache().increase(key, value);
		LOGGER.debug("Increase number into cache - Argument:[key={0}, value={1}] - Result:[{2}].", 
		    key, Long.valueOf(value), result);
		return result;
	}
	
	@Override
	public Future<Long> asyncIncrease(String key, long value) {
		Future<Long> result = getCache().asyncIncrease(key, value);
		LOGGER.debug("Async increase number into cache - Argument:[key={0}, value={1}].", 
		    key, Long.valueOf(value));
		return result;
	}

	@Override
	public long decrease(String key, long value) {
		long result = getCache().decrease(key, value);
		LOGGER.debug("Decrease number into cache - Argument:[key={0}, value={1}] - Result:[{2}].", 
		    key, Long.valueOf(value), result);
		return result;
	}
	
	@Override
	public Future<Long> asyncDecrease(String key, long value) {
		Future<Long> result = getCache().asyncDecrease(key, value);
		LOGGER.debug("Async decrease number into cache - Argument:[key={0}, value={1}].", 
		    key, Long.valueOf(value));
		return result;
	}
	
}
