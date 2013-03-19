package com.mtoolkit.spring;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mlogger.Loggers;

/**
 * Final class that implements {@link ApplicationContextAware} and {@link DisposableBean}.
 * 
 * It holds spring application context after application started, 
 * any object that wishes to get the {@link ApplicationContext} or managed beans 
 * or access file resources from spring application context can use this utility.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.00, 07/18/2012
 * @since 	JDK1.5
 */
public final class SpringContextHolder implements ApplicationContextAware, DisposableBean {
	
	/** spring application context */
	private static ApplicationContext _applicationContext;
	
	private static final Loggers LOGGER = Loggers.getLoggers(SpringContextHolder.class);
    
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		_applicationContext = applicationContext;
	}
	
	@Override
    public void destroy() throws Exception {
	    clear();
    }
	
	/**
	 * Gets spring application context.
	 * 
	 * @return spring application context.
	 */
	public static ApplicationContext getApplicationContext() {
	    assertContextInitialized();
		return _applicationContext;
	}
	
	/**
	 * Gets bean instance from application context with specified bean name.
	 * Return null if the bean could not be obtained.
	 * 
	 * @param name bean name.
	 * 
	 * @return specified bean instance.
	 */
	@SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
	    assertContextInitialized();
		try {
			return (T) _applicationContext.getBean(name);
		} catch(BeansException e) {
		    LOGGER.error("Could not be obtain spring managed bean by name [{0}]", e, name);
			return null;
		}
	}
	
	/**
	 * Gets the file resource from application context with specified location.
	 * 
	 * @param location file location in application context.
	 * 
	 * @return file resource.
	 * 
	 * @throws IOException 
	 * 		   if the resource cannot be resolved as absolute file path, 
	 * 		   i.e. if the resource is not available in a file system.
	 */
	public static File getFileResource(String location) throws IOException {
	    assertContextInitialized();
		return _applicationContext.getResource(location).getFile();
	}

	/**
	 * Clear the holder.
	 */
	public static void clear() {
	    _applicationContext = null;
	}
	
	/**
	 * Assert spring application context has initialized.
	 */
	private static void assertContextInitialized() {
	    if (_applicationContext == null) {
	        throw new IllegalStateException("Spring application context not initialized!");
	    }
	}
	
}
