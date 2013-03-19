/**
 * f-club.cn
 * Copyright (c) 2009-2012 All Rights Reserved.
 */
package com.mtoolkit.spring.config;

import java.util.Properties;

/**
 * 
 * @author michael
 * @version $Id: PropertyLoader.java, v 0.1 2012-8-16 下午1:34:37 michael Exp $
 */
public interface PropertyLoader {

    /**
     * Loads the configure property file.
     * 
     * @param props configure property file.
     */
    public void load(Properties props);

}
