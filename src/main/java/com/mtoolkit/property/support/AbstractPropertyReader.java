package com.mtoolkit.property.support;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.mtoolkit.property.PropertyReader;
import com.mtoolkit.util.EmptyUtil;

public abstract class AbstractPropertyReader implements PropertyReader {

    @Override
    public void init() throws IOException {
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    public String getValue(String key, String defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : value;
    }
    
    @Override
    public List<String> valueList() {
        Set<Entry<String, String>> entrySet = entrySet();
        if (EmptyUtil.isNullEmpty(entrySet)) {
            return Collections.emptyList();
        } else {
            List<String> valueList = new ArrayList<String>(entrySet.size());
            for (Entry<String, String> entry : entrySet) {
                if (entry != null && entry.getValue() != null) {
                    valueList.add(entry.getValue());
                }
            }
            return valueList;
        }
    }

    @Override
    public List<String> getKey(String value) {
        if (value == null) {
            throw new NullPointerException("value");
        }

        Set<Entry<String, String>> entrySet = entrySet();
        if (EmptyUtil.isNullEmpty(entrySet)) {
            return Collections.emptyList();
        } else {
            List<String> returnKeys = new LinkedList<String>();
            for (Entry<String, String> entry : entrySet) {
                if (entry == null || entry.getKey() == null) {
                    continue;
                }
                if (value.equals(entry.getValue())) {
                    returnKeys.add(entry.getKey());
                }
            }
            return Collections.unmodifiableList(returnKeys);
        }
    }

    @Override
    public int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }

    @Override
    public int getIntValue(String key, int defaultValue) {
        return getIntegerValue(key, Integer.valueOf(defaultValue)).intValue();
    }

    @Override
    public Integer getIntegerValue(String key, Integer defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : Integer.valueOf(value);
    }

    @Override
    public long getLongValue(String key) {
        return Long.parseLong(getValue(key));
    }

    @Override
    public long getLongValue(String key, long defaultValue) {
        return getLongValue(key, Long.valueOf(defaultValue)).longValue();
    }

    @Override
    public Long getLongValue(String key, Long defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : Long.valueOf(value);
    }

    @Override
    public byte getByteValue(String key) {
        return Byte.parseByte(getValue(key));
    }

    @Override
    public byte getByteValue(String key, byte defaultValue) {
        return getByteValue(key, Byte.valueOf(defaultValue)).byteValue();
    }

    @Override
    public Byte getByteValue(String key, Byte defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : Byte.valueOf(value);
    }

    @Override
    public double getDoubleValue(String key) {
        return Double.parseDouble(getValue(key));
    }

    @Override
    public double getDoubleValue(String key, double defaultValue) {
        return getDoubleValue(key, Double.valueOf(defaultValue)).doubleValue();
    }

    @Override
    public Double getDoubleValue(String key, Double defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : Double.valueOf(value);
    }

    @Override
    public short getShortValue(String key) {
        return Short.parseShort(getValue(key));
    }

    @Override
    public short getShortValue(String key, short defaultValue) {
        return getShortValue(key, Short.valueOf(defaultValue)).shortValue();
    }

    @Override
    public Short getShortValue(String key, Short defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : Short.valueOf(value);
    }

    @Override
    public boolean getBooleanValue(String key) {
        return Boolean.parseBoolean(getValue(key));
    }

    @Override
    public boolean getBooleanValue(String key, boolean defaultValue) {
        return getBooleanValue(key, Boolean.valueOf(defaultValue)).booleanValue();
    }

    @Override
    public Boolean getBooleanValue(String key, Boolean defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : Boolean.valueOf(value);
    }

    @Override
    public float getFloatValue(String key) {
        return Float.parseFloat(getValue(key));
    }

    @Override
    public float getFloatValue(String key, float defaultValue) {
        return getFloatValue(key, Float.valueOf(defaultValue)).floatValue();
    }

    @Override
    public Float getFloatValue(String key, Float defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : Float.valueOf(value);
    }

    @Override
    public BigInteger getBigIntegerValue(String key) {
        return new BigInteger(getValue(key));
    }

    @Override
    public BigInteger getBigIntegerValue(String key, BigInteger defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : new BigInteger(value);
    }

    @Override
    public BigDecimal getBigDecimalValue(String key) {
        return new BigDecimal(getValue(key));
    }

    @Override
    public BigDecimal getBigDecimalValue(String key, BigDecimal defaultValue) {
        String value = getValue(key);
        return value == null ? defaultValue : new BigDecimal(value);
    }
    
}
