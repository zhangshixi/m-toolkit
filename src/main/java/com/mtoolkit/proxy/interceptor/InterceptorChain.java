package com.mtoolkit.proxy.interceptor;

import com.mtoolkit.proxy.Interceptor;
import com.mtoolkit.proxy.Provider;
import com.mtoolkit.proxy.ProxyFactory;
import com.mtoolkit.util.ProxyUtil;

/**
 * An ${code InterceptorChain} assists with creating proxies
 * which go through a series of {@link Interceptor interceptors}.
 * 
 * <pre>
 *   MyServiceInterface serviceImpl = ...;
 *   ProxyFactory factory = ...;
 *   Interceptor[] interceptors = ...;
 *   InterceptorChain chain = new InterceptorChain(interceptors);
 *   Provider provider = chain.createProxyProvider(factory, serviceImpl);
 *   MyServiceInterface serviceProxy = (MyServiceInterface)provider.getObject();
 *   serviceProxy.someServiceMethod(...); // This will go through the interceptors!
 * </pre>
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 1/21/2012
 * @since 	JDK1.5
 */
public class InterceptorChain {

	private final Interceptor[] _interceptors;

	public InterceptorChain(Interceptor[] interceptors) {
		_interceptors = interceptors;
	}

	/**
	 * Creates an {@link Provider} which will return a proxy that sends
	 * method invocations through this chain of interceptors and ultimately
	 * arrive at the supplied terminus object. The proxy will support all
	 * interfaces implemented by the terminus object. The thread context
	 * classloader will be used to generate the proxy class.
	 * 
	 * @param  proxyFactory the {@link ProxyFactory} to use to create the proxy
	 * @param  terminus 	the terminus
	 * 
	 * @return an {@link Provider} which will return a proxy that 
	 * 		   sends method invocations through this chain of interceptors 
	 * 		   and ultimately arrive at the supplied terminus object.
	 */
	public Provider createProxyProvider(ProxyFactory proxyFactory, Object terminus) {
		return createProxyProvider(proxyFactory, terminus, null);
	}

	/**
	 * Creates an {@link Provider} which will return a proxy that sends
	 * method invocations through this chain of interceptors and ultimately
	 * arrive at the supplied terminus object. The proxy will support only the
	 * specified interfaces/classes. The thread context classloader will be used
	 * to generate the proxy class.
	 * 
	 * @param proxyFactory the {@link ProxyFactory} to use to create the proxy.
	 * @param terminus 	   the terminus.
	 * @param proxyClasses the interfaces to support.
	 * 
	 * @return an {@link Provider} which will return a proxy that 
	 * 		   sends method invocations through this chain of interceptors 
	 * 		   and ultimately arrive at the supplied terminus object.
	 */
	public Provider createProxyProvider(ProxyFactory proxyFactory, 
										Object terminus, 
										Class<?>[] proxyClasses) {

		return createProxyProvider(proxyFactory, 
								   Thread.currentThread().getContextClassLoader(), 
								   terminus, 
								   proxyClasses);
	}

	/**
	 * Creates an {@link Provider} which will return a proxy that sends
	 * method invocations through this chain of interceptors and ultimately
	 * arrive at the supplied terminus object. The proxy will support only the
	 * specified interfaces/classes. The specified classloader will be used to
	 * generate the proxy class.
	 * 
	 * @param proxyFactory the {@link ProxyFactory} to use to create the proxy.
	 * @param classLoader  the classloader to be used to generate the proxy class.
	 * @param terminus 	   the terminus.
	 * @param proxyClasses the interfaces to support.
	 * 
	 * @return an {@link Provider} which will return a proxy that 
	 * 		   sends method invocations through this chain of interceptors 
	 * 		   and ultimately arrive at the supplied terminus object.
	 */
	public Provider createProxyProvider(ProxyFactory proxyFactory, 
										ClassLoader classLoader, 
										Object terminus, 
										Class<?>[] proxyClasses) {
		
		if (proxyClasses == null || proxyClasses.length == 0) {
			proxyClasses = ProxyUtil.getAllInterfaces(terminus.getClass());
		}
		
		return new ProxyObjectProvider(proxyFactory, classLoader, terminus, proxyClasses);
	}

	// ---- inner Classes ---------------------------------------------------------------
	private class ProxyObjectProvider implements Provider {
		private final ProxyFactory _proxyFactory;
		private final ClassLoader  _classLoader;
		private final Object 	  _terminus;
		private final Class<?>[]  _proxyClasses;

		public ProxyObjectProvider(ProxyFactory proxyFactory,
								   ClassLoader classLoader, 
								   Object terminus, 
								   Class<?>[] proxyClasses) {
			_classLoader  = classLoader;
			_proxyClasses = proxyClasses;
			_terminus 	  = terminus;
			_proxyFactory = proxyFactory;
		}

		public Object getObject() {
			return createProxy(_proxyFactory, _classLoader, _terminus, _proxyClasses);
		}

		private Object createProxy(ProxyFactory proxyFactory,
								   ClassLoader classLoader, 
								   Object terminus, 
								   Class<?>[] proxyClasses) {
			
			Object currentTarget = terminus;
			for (int i = _interceptors.length - 1; i >= 0; --i) {
				currentTarget = proxyFactory.createInterceptProxy(
						classLoader, currentTarget, proxyClasses, _interceptors[i]);
			}
			
			return currentTarget;
		}
	}
	
}