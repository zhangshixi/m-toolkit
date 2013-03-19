/**
 * f-club.cn
 * Copyright (c) 2009-2012 All Rights Reserved.
 */
package com.mtoolkit.spring.config;

import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.mlogger.Loggers;

/**
 * 
 * @author michael
 * @version $Id: PropertyConfigurerAdapter.java, v 0.1 2012-8-16 下午1:28:35 michael Exp $
 */
public class PropertyConfigurerAdapter extends PropertyPlaceholderConfigurer {
    
	/** property loader */
    private PropertyLoader _propertyLoader;
    /** logger utility */
    private final Loggers logger = Loggers.getLoggers(PropertyConfigurerAdapter.class);
    
    public PropertyConfigurerAdapter() {
    }
    
    public PropertyConfigurerAdapter(PropertyLoader propertyLoader) {
        _propertyLoader = propertyLoader;
    }
    
    /**
     * Getter method for property <tt>propertyLoader</tt>.
     * 
     * @return property value of propertyLoader
     */
    public PropertyLoader getPropertyLoader() {
        return _propertyLoader;
    }
    
    /**
     * Setter method for property <tt>propertyLoader</tt>.
     * 
     * @param propertyLoader value to be assigned to property propertyLoader
     */
    public void setPropertyLoader(PropertyLoader propertyLoader) {
        _propertyLoader = propertyLoader;
    }
    
    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
    	doRecordPropertiesLog(props);
    	
        super.processProperties(beanFactoryToProcess, props);
        
        if (_propertyLoader != null) {
            _propertyLoader.load(props);
        }
    }

	private void doRecordPropertiesLog(Properties props) {
		logger.debug("Load properties:");
		for (Entry<Object, Object> entry : props.entrySet()) {
			logger.debug("    {0}={1}", entry.getKey(), entry.getValue());
		}
	}
    
}
