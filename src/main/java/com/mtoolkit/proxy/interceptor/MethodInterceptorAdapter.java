package com.mtoolkit.proxy.interceptor;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.mtoolkit.proxy.Interceptor;
import com.mtoolkit.proxy.Invocation;

/**
 * MethodInterceptor adapter.
 * Transform a {@link org.aopalliance.intercept.MethodInterceptor} 
 * instance to a {@link  com.mtoolkit.proxy.Interceptor} instance.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 3/8/2012
 * @since 	JDK1.5
 */
public class MethodInterceptorAdapter implements Interceptor {

	private MethodInterceptor _interceptor;
	
	/**
	 * Creates a InterceptorAdapter instance with given {@code interceptor}.
	 * 
	 * @param  interceptor MethodInterceptor instance.
	 * 
	 * @throws NullPointerException if {@code interceptor} is null.
	 */
	public MethodInterceptorAdapter(MethodInterceptor interceptor) {
		if (interceptor == null) {
			throw new NullPointerException("interceptor");
		}
		
		_interceptor = interceptor;
	}
	
	@Override
	public Object intercept(final Invocation invocation) throws Throwable {
		
		return _interceptor.invoke(new MethodInvocation() {
			
			@Override
			public Object proceed() throws Throwable {
				return invocation.proceed();
			}
			
			@Override
			public Object getThis() {
				return invocation.getProxy();
			}
			
			@Override
			public AccessibleObject getStaticPart() {
				return null;
			}
			
			@Override
			public Object[] getArguments() {
				return invocation.getArguments();
			}
			
			@Override
			public Method getMethod() {
				return invocation.getMethod();
			}
		});
	}

}
