package com.mtoolkit.proxy.factory;

import com.mlogger.Loggers;
import com.mtoolkit.proxy.Interceptor;
import com.mtoolkit.proxy.Invoker;
import com.mtoolkit.proxy.Provider;
import com.mtoolkit.proxy.ProxyFactory;

/**
 * Abstract proxy factory implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public abstract class AbstractProxyFactory implements ProxyFactory {
	
	/** logger */
	protected static final Loggers LOGGER = Loggers.getLoggers(ProxyFactory.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @param interfaces {@inheritDoc}
	 * @param invoker 	 {@inheritDoc}
	 * 
	 * @return {@inheritDoc} 
	 */
	@Override
	public Object createInvokeProxy(Class<?>[] interfaces, Invoker invoker) {
		return createInvokeProxy(getCurrentClassLoader(), interfaces, invoker);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param interfaces {@inheritDoc}
	 * @param provider   {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public Object createDelegateProxy(Class<?>[] interfaces, Provider provider) {
		return createDelegateProxy(getCurrentClassLoader(), interfaces, provider);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param target 	  {@inheritDoc}
	 * @param interfaces  {@inheritDoc}
	 * @param intercepter {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public Object createInterceptProxy(Object target, Class<?>[] interfaces, Interceptor intercepter) {
		return createInterceptProxy(getCurrentClassLoader(), target, interfaces, intercepter);
	}

	// ---- private method ----------------------------------------------------------------------------
	/**
	 * Gets the current thread context class loader.
	 * 
	 * @return the current thread context class loader. 
	 */
	private ClassLoader getCurrentClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

}

