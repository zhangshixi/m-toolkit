package com.mtoolkit.spring;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mlogger.Loggers;

/**
 * Abstract class that implements {@link ApplicationContextAware}.
 * Any object that wishes to be notified of the {@link ApplicationContext} 
 * can implements this class that it runs in.
 * It also be implemented if an object needs to get managed bean or 
 * access file resources from spring application context.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.00, 07/18/2012
 * @since 	JDK1.5
 */
public abstract class SpringContextAware implements ApplicationContextAware {
	
	/** spring application context */
	private ApplicationContext _applicationContext;
	
	private final Loggers logger = Loggers.getLoggers(SpringContextAware.class);
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) 
			throws BeansException {
		_applicationContext = applicationContext;
	}
	
	/**
	 * Gets spring application context.
	 * 
	 * @return spring application context.
	 */
	public ApplicationContext getApplicationContext() {
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
	public Object getBean(String name) {
		try {
			return _applicationContext.getBean(name);
		} catch(BeansException e) {
			logger.error("The bean could not be obtained.", e);
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
	public File getFileResource(String location) throws IOException {
		return _applicationContext.getResource(location).getFile();
	}
	
}
