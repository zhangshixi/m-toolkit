package com.mtoolkit.property;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public interface PropertyReader {

    public void init() throws IOException;
    
    public void destroy();
    
    public int getSize();
    
    public boolean isEmpty();

    public Set<String> keySet();

    public List<String> valueList();

    public Set<Entry<String, String>> entrySet();

    public boolean containsKey(String key);

    public boolean containsValue(String value);

    public List<String> getKey(String value);
    
    public String getValue(String key);
    
    public String getValue(String key, String defaultValue);

    public int getIntValue(String key);
    
    public int getIntValue(String key, int defaultValue);

    public Integer getIntegerValue(String key, Integer defaultValue);

    public long getLongValue(String key);

    public long getLongValue(String key, long defaultValue);

    public Long getLongValue(String key, Long defaultValue);

    public byte getByteValue(String key);

    public byte getByteValue(String key, byte defaultValue);

    public Byte getByteValue(String key, Byte defaultValue);

    public double getDoubleValue(String key);

    public double getDoubleValue(String key, double defaultValue);

    public Double getDoubleValue(String key, Double defaultValue);

    public short getShortValue(String key);

    public short getShortValue(String key, short defaultValue);

    public Short getShortValue(String key, Short defaultValue);

    public boolean getBooleanValue(String key);

    public boolean getBooleanValue(String key, boolean defaultValue);

    public Boolean getBooleanValue(String key, Boolean defaultValue);

    public float getFloatValue(String key);

    public float getFloatValue(String key, float defaultValue);

    public Float getFloatValue(String key, Float defaultValue);

    public BigInteger getBigIntegerValue(String key);

    public BigInteger getBigIntegerValue(String key, BigInteger defaultValue);

    public BigDecimal getBigDecimalValue(String key);

    public BigDecimal getBigDecimalValue(String key, BigDecimal defaultValue);
    
}
