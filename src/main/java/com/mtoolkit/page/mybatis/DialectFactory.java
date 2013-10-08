package com.mtoolkit.page.mybatis;

public abstract class DialectFactory {
	
	private DialectFactory() {
	}
	
	public static Dialect newDialect(String className) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = Class.forName(className);
		return (Dialect) clazz.newInstance();
	}
	
}
