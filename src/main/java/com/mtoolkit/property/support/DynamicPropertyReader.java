package com.mtoolkit.property.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.mlogger.Loggers;
import com.mtoolkit.util.EmptyUtil;

public class DynamicPropertyReader extends AbstractPropertyReader {

    private String _filePath;
    
    private long _lastModifiedTime;
    
    private Map<String, String> _propertiesMap;
    
    private final Loggers logger = Loggers.getLoggers(DynamicPropertyReader.class);
    
    public DynamicPropertyReader() {
    }
    
    public void setFile(File file) {
        setFilePath(file.getAbsolutePath());
    }
    
    public void setFilePath(String filePath) {
        _filePath = filePath;
    }

    @Override
    public void init() throws IOException {
        super.init();
        checkProperties();
    }

    @Override
    public String getValue(String key) {
        checkProperties();
        return _propertiesMap.get(key);
    }

    @Override
    public Set<String> keySet() {
        checkProperties();
        return _propertiesMap.keySet();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        checkProperties();
        return _propertiesMap.entrySet();
    }

    @Override
    public boolean containsKey(String key) {
        checkProperties();
        return _propertiesMap.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        checkProperties();
        return _propertiesMap.containsValue(value);
    }

    @Override
    public int getSize() {
        checkProperties();
        return _propertiesMap.size();
    }

    @Override
    public boolean isEmpty() {
        checkProperties();
        return _propertiesMap.isEmpty();
    }
    
    private void checkProperties() {
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
            return true;
        } else {
            return false;
        }
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
    
}
