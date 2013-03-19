/**
 * f-club.cn
 * Copyright (c) 2009-2013 All Rights Reserved.
 */
package com.mtoolkit.property.decorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.mtoolkit.property.PropertyReader;
import com.mtoolkit.property.support.AbstractPropertyReader;

public class TrimValuePropertyReader extends AbstractPropertyReader {

    private PropertyReader _reader;
    
    public TrimValuePropertyReader(PropertyReader reader) {
        _reader = reader;
    }
    
    @Override
    public int getSize() {
        return _reader.getSize();
    }

    @Override
    public boolean isEmpty() {
        return _reader.isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return _reader.keySet();
    }

    @Override
    public List<String> valueList() {
        List<String> valueList = _reader.valueList();
        List<String> trimValueList = new ArrayList<String>(valueList.size());
        for (String value : valueList) {
            trimValueList.add(doTrim(value));
        }
        return trimValueList;
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return _reader.entrySet();
    }

    @Override
    public boolean containsKey(String key) {
        return _reader.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return _reader.containsValue(value);
    }

    @Override
    public String getValue(String key) {
        return doTrim(_reader.getValue(key));
    }

    private String doTrim(String value) {
        return value == null ? null : value.trim();
    }
    
}
