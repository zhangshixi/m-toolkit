package com.mtoolkit.proxy.interceptor;

import java.io.IOException;

import com.mtoolkit.proxy.Interceptor;
import com.mtoolkit.proxy.Invocation;
import com.mtoolkit.util.ConversionUtil;

/**
 * An intercepter which makes a serialized copy of all parameters and return values. 
 * This is useful when testing remote services to ensure that all parameter/return 
 * types are in fact serializable/deserializable. 
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 1/21/2012
 * @since 	JDK1.5
 */
public class SerializingInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] arguments = invocation.getArguments();
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = doSerialize(arguments[i]);
        }
        
        return doSerialize(invocation.proceed());
	}

	private Object doSerialize(Object original) {
		Object copy = null;
		try {
			byte[] bytes = ConversionUtil.object2Bytes(original);
			copy = ConversionUtil.bytes2Object(bytes);
		} catch (ClassNotFoundException e) {
			throw new UnsupportedOperationException(
				  "Unable to make serialized copy of " +
		          original.getClass().getName() + " object.", e);
		} catch (IOException e) {
			throw new UnsupportedOperationException(
				  "Unable to make serialized copy of " +
	              original.getClass().getName() + " object.", e);
		}
		
		return copy;
	}
	
}

