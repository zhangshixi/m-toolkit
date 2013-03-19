package com.mtoolkit.service;

/**
 * Application server monitor interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 2/1/2012
 * @since 	JDK1.5
 */
public interface Service {
	
	/**
     * Startup the application service with the specified arguments.
     *  
     * @param  args start parameters.
     * 
     * @throws Exception if an error occurs whiling start service.
     */
    public void startup(String[] args) throws Exception;

    /**
     * Shutdown the application service.
     * 
     * @throws Exception if an error occurs whiling stop service.
     */
    public void shutdown() throws Exception;
    
}

