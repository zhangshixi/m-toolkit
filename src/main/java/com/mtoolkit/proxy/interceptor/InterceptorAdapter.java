package com.mtoolkit.proxy.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.mtoolkit.proxy.Interceptor;
import com.mtoolkit.proxy.Invocation;


/**
 * Interceptor adapter.
 * Transform a {@link  com.mtoolkit.proxy.Interceptor} instance 
 * to a {@link org.aopalliance.intercept.MethodInterceptor} instance.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 3/8/2012
 * @since 	JDK1.5
 */
public class InterceptorAdapter implements MethodInterceptor {
	
	/** interceptor */
	private Interceptor _interceptor;
	
	/**
	 * Creates a MethodInterceptorAdapter instance with given {@code interceptor}.
	 * 
	 * @param  interceptor Interceptor instance.
	 * 
	 * @throws NullPointerException if {@code interceptor} is null.
	 */
	public InterceptorAdapter(Interceptor interceptor) {
		if (interceptor == null) {
			throw new NullPointerException("interceptor");
		}
		
		_interceptor = interceptor;
	}

	@Override
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		
		return _interceptor.intercept(new Invocation() {
			
			@Override
			public Object proceed() throws Throwable {
				return invocation.proceed();
			}
			
			@Override
			public Object getProxy() {
				return invocation.getThis();
			}
			
			@Override
			public Method getMethod() {
				return invocation.getMethod();
			}
			
			@Override
			public Object[] getArguments() {
				return invocation.getArguments();
			}
		});
	}
	
}
