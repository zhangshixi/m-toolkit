package com.mtoolkit.page;

public interface Dialect {
	
    public String getCountSql(final String targetSql);
    
    public String getLimitSql(final String targetSql, final Page<?> page);
    
}