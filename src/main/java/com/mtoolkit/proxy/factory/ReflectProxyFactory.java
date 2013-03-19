package com.mtoolkit.proxy.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.mtoolkit.proxy.Interceptor;
import com.mtoolkit.proxy.Invocation;
import com.mtoolkit.proxy.Invoker;
import com.mtoolkit.proxy.Provider;

/**
 * Java reflect proxy factory implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/8/2011
 * @since 	JDK1.5
 */
public class ReflectProxyFactory extends AbstractProxyFactory {

	/**
	 * {@inheritDoc}
	 * 
	 * @param classLoader {@inheritDoc}
	 * @param interfaces  {@inheritDoc}
	 * @param invoker	  {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public Object createInvokeProxy(
		   ClassLoader classLoader, Class<?>[] interfaces, Invoker invoker) {
		return Proxy.newProxyInstance(
			   classLoader, interfaces, new InvokeInvocationHandler(invoker));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param classLoader {@inheritDoc}
	 * @param interfaces  {@inheritDoc}
	 * @param provider	  {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public Object createDelegateProxy(
		   ClassLoader classLoader, Class<?>[] interfaces, Provider provider) {
		return Proxy.newProxyInstance(
			   classLoader, interfaces, new DelegateInvocationHandler(provider));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param classLoader {@inheritDoc}
	 * @param interfaces  {@inheritDoc}
	 * @param intercepter {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public Object createInterceptProxy(
		   ClassLoader classLoader, Object target, Class<?>[] interfaces, Interceptor intercepter) {
		return Proxy.newProxyInstance(
			   classLoader, interfaces, new InterceptInvocationHandler(target, intercepter));
	}
	
	// ---- inner classes --------------------------------------------------------------------------
	private class InvokeInvocationHandler implements InvocationHandler {
		
		private Invoker _invoker;
		
		public InvokeInvocationHandler(Invoker invoker) {
			_invoker = invoker;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return _invoker.invoke(proxy, method, args);
		}
		
	}
	
	public class DelegateInvocationHandler implements InvocationHandler {

		private Provider _provider;
		
		public DelegateInvocationHandler(Provider provider) {
			_provider = provider;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return method.invoke(_provider.getObject(), args);
		}

	}

	private class InterceptInvocationHandler implements InvocationHandler {
		
		private Object 		_target;
		private Interceptor _intercepter;
		
		public InterceptInvocationHandler(Object target, Interceptor intercepter) {
			_target 	 = target;
			_intercepter = intercepter;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return _intercepter.intercept(new InterceptInvocation(_target, method, args));
		}
		
	}
	
	private class InterceptInvocation implements Invocation {
		
		private Object 	 _target;
		private Method 	 _method;
		private Object[] _args;
		
		public InterceptInvocation(Object target, Method method, Object[] args) {
			_target = target;
			_method = method;
			_args	= args;
		}

		@Override
		public Object getProxy() {
			return _target;
		}

		@Override
		public Method getMethod() {
			return _method;
		}

		@Override
		public Object[] getArguments() {
			return _args;
		}

		@Override
		public Object proceed() throws Throwable {
			try {
				return _method.invoke(_target, _args);
			} catch (InvocationTargetException e) {
				throw e.getTargetException();
			}
		}
		
	}
	
}