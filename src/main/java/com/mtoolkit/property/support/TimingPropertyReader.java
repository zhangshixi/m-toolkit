package com.mtoolkit.property.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mlogger.Loggers;
import com.mtoolkit.util.EmptyUtil;

public class TimingPropertyReader extends AbstractPropertyReader {

    private String _filePath;
    
    private long _reloadDelayTime;
    private long _lastModifiedTime;

    private Map<String, String> _propertiesMap;
    private ScheduledExecutorService _reloadService;
    
    private final Loggers logger = Loggers.getLoggers(TimingPropertyReader.class);

    public TimingPropertyReader() {
    }
    
    public void setFile(File file) {
        setFilePath(file.getAbsolutePath());
    }
    
    public void setFilePath(String filePath) {
        _filePath = filePath;
    }

    public void setReloadDelayTime(long reloadDelayTime) {
        this._reloadDelayTime = reloadDelayTime;
    }

    @Override
    public void init() throws IOException {
        if (_reloadDelayTime > 0) {
            logger.info("Build a schedule thread to reload property [{0}] every delay [{1}] seconds.",
                _filePath, String.valueOf(_reloadDelayTime));

            _reloadService = Executors.newSingleThreadScheduledExecutor();
            _reloadService.scheduleWithFixedDelay(
                new ReloadTask(), _reloadDelayTime, _reloadDelayTime, TimeUnit.SECONDS);
        }

        _propertiesMap = reloadProperties();
    }
    
    @Override
    public void destroy() {
        if (_reloadDelayTime > 0) {
            _reloadService.shutdown();
        }
        _propertiesMap.clear();
    }
    
    @Override
    public int getSize() {
        return _propertiesMap.size();
    }

    @Override
    public boolean isEmpty() {
        return _propertiesMap.isEmpty();
    }

    @Override
    public String getValue(String key) {
        return _propertiesMap.get(key);
    }

    @Override
    public Set<String> keySet() {
        return _propertiesMap.keySet();
    }

    @Override
    public List<String> valueList() {
        Set<Entry<String, String>> entrySet = entrySet();
        if (EmptyUtil.isNullEmpty(entrySet)) {
            return Collections.emptyList();
        } else {
            List<String> valueList = new ArrayList<String>(_propertiesMap.size());
            for (Entry<String, String> entry : entrySet) {
                if (entry != null && entry.getValue() != null) {
                    valueList.add(entry.getValue());
                }
            }
            return valueList;
        }
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return _propertiesMap.entrySet();
    }

    @Override
    public boolean containsKey(String key) {
        return _propertiesMap.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return _propertiesMap.containsValue(value);
    }

    private Map<String, String> reloadProperties() throws IOException {
        logger.debug("Reload properties from file path [{0}].", _filePath);

        Properties props = new Properties();
        InputStream input = new FileInputStream(_filePath);
        try {
            props.load(input);
        } finally {
            input.close();
        }

        Set<Entry<Object, Object>> entrySet = props.entrySet();
        if (EmptyUtil.isNullEmpty(entrySet)) {
            logger.warn("The property file [{0}] is empty.", _filePath);
            return Collections.emptyMap();
        } else {
            Map<String, String> returnMap = new HashMap<String, String>(entrySet.size(), 1F);
            for (Entry<Object, Object> entry : props.entrySet()) {
                returnMap.put((String) entry.getKey(), (String) entry.getValue());
            }
            return returnMap;
        }
    }

    private class ReloadTask implements Runnable {

        @Override
        public void run() {
            if (hasModified()) {
                try {
                    _propertiesMap = reloadProperties();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
        }

        private boolean hasModified() {
            File file = new File(_filePath);
            if (file.lastModified() > _lastModifiedTime) {
                _lastModifiedTime = file.lastModified();

                logger.debug("The property file [{0}] was modified, last modify time is [{1}].",
                        _filePath, String.valueOf(_lastModifiedTime));
                return true;
            } else {
                return false;
            }
        }
    }
    
}
