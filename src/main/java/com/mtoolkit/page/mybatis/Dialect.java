package com.mtoolkit.page.mybatis;

import com.mtoolkit.page.Page;

public interface Dialect {
	
    public String getCountSql(final String targetSql);
    
    public String getLimitSql(final String targetSql, final Page<?> page);
    
}