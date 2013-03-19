package com.mtoolkit.proxy;

/**
 * Proxy factory interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public interface ProxyFactory {
	
	/**
	 * Creates a proxy object which uses the provided {@link Invoker} to handle all method invocations.
	 * The proxy will be generated using the current thread context class loader.
	 * 
	 * @param  interfaces the interfaces that the proxy should implement.
	 * @param  invoker the handle invoker.
	 * 
	 * @return a proxy object which uses the provided {@link Invoker} to handle all method invocations.
	 * 
	 * @see	   #createInvokeProxy(ClassLoader, Class[], Invoker)
	 */
	public Object createInvokeProxy(Class<?>[] interfaces, Invoker invoker);
	
	/**
	 * Creates a proxy object which uses the provided {@link Invoker} to handle all method invocations.
	 * The proxy will be generated using the specified class loader.
	 * 
	 * @param  classLoader the class loader to use when generating the proxy.
	 * @param  interfaces the interfaces that the proxy should implement.
	 * @param  invoker the handle invoker.
	 * 
	 * @return a proxy object which uses the provided {@link Invoker} to handle all method invocations.
	 */
	public Object createInvokeProxy(ClassLoader classLoader, Class<?>[] interfaces, Invoker invoker);
	
	/**
	 * Creates a proxy object which delegates to the object provided by {@link Provider}.
	 * The proxy will be generated using the current thread context class loader.
	 * 
	 * @param  interfaces the interfaces that the proxy should implement.
	 * @param  provider the delegate provider.
	 * 
	 * @return a proxy object which delegates to the object by the target object provider.
	 * 
	 * @see    #createDelegateProxy(ClassLoader, Class[], Provider)
	 */
	public Object createDelegateProxy(Class<?>[] interfaces, Provider provider);
	
	/**
	 * Creates a proxy object which delegates to the object provided by {@link Provider}.
	 * The proxy will be generated using the specified class loader.
	 * 
	 * @param  classLoader the class loader to use when generating the proxy.
	 * @param  interfaces the interfaces that the proxy should implement.
	 * @param  provider the delegate provider.
	 * 
	 * @return a proxy object which delegates to the object by the target object provider.
	 */
	public Object createDelegateProxy(ClassLoader classLoader, Class<?>[] interfaces, Provider provider);
	
	/**
	 * Creates a proxy object which pass through a {@link Interceptor Intercepter} before eventually reaching 
	 * the <@code target} object. The proxy will be generated using the current thread context class loader.
	 * 
	 * @param  target the target object.
	 * @param  interfaces the interfaces that the proxy should implement.
	 * @param  intercepter the method intercepter.
	 * 
	 * @return a proxy object which pass through a {@link Interceptor Intercepter} before eventually reaching 
	 * 		   the <@code target} object. 
	 * 
	 * @see	   #createInterceptProxy(ClassLoader, Object, Class[], Interceptor)
	 */
	public Object createInterceptProxy(Object target, Class<?>[] interfaces, Interceptor intercepter);
	
	/**
	 * Creates a proxy object which pass through a {@link Interceptor Intercepter} before eventually reaching 
	 * the <@code target} object. The proxy will be generated using the specified class loader.
	 * 
	 * @param  classLoader the class loader to use when generating the proxy.
	 * @param  target the target object.
	 * @param  interfaces the interfaces that the proxy should implement.
	 * @param  intercepter the method intercepter.
	 * 
	 * @return a proxy object which pass through a {@link Interceptor Intercepter} before eventually reaching 
	 * 		   the <@code target} object. 
	 */
	public Object createInterceptProxy(ClassLoader classLoader, Object target, Class<?>[] interfaces, Interceptor intercepter);
	
}