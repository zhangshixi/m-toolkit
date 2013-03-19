package com.mtoolkit.property.support;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.mtoolkit.util.EmptyUtil;

public class CommonPropertyReader extends AbstractPropertyReader {

    private Properties _prop;
    
    public CommonPropertyReader(Properties prop) {
        if (prop == null) {
            throw new NullPointerException("prop");
        }
        _prop = prop;
    }
    
    @Override
    public int getSize() {
        return _prop.size();
    }

    @Override
    public boolean isEmpty() {
        return _prop.isEmpty();
    }

    @Override
    public String getValue(String key) {
        return _prop.getProperty(key);
    }

    @Override
    public Set<String> keySet() {
        Set<Object> keySet = _prop.keySet();
        if (EmptyUtil.isNullEmpty(keySet)) {
            return Collections.emptySet();
        } else {
            Set<String> returnKeySet = new HashSet<String>();
            for (Object key : keySet) {
                returnKeySet.add((String) key);
            }
            return returnKeySet;
        }
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<Object, Object>> entrySet = _prop.entrySet();
        if (EmptyUtil.isNullEmpty(entrySet)) {
            return Collections.emptySet();
        } else {
            Set<Entry<String, String>> returnEntrySet = new HashSet<Entry<String, String>>();
            for (Entry<Object, Object> entry : entrySet) {
                returnEntrySet.add(
                    new SimpleEntry<String, String>(
                            (String) entry.getKey(), (String) entry.getValue()));
            }
            return returnEntrySet;
        }
    }

    @Override
    public boolean containsKey(String key) {
        return _prop.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return _prop.containsValue(value);
    }

}
