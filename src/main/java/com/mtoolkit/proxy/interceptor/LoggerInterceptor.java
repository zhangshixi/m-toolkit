package com.mtoolkit.proxy.interceptor;

import java.util.Arrays;

import com.mlogger.Loggers;
import com.mtoolkit.proxy.Interceptor;
import com.mtoolkit.proxy.Invocation;

/**
 * Method logger intercepter.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 1/21/2012
 * @since 	JDK1.5
 */
public class LoggerInterceptor implements Interceptor {
	
	/** method default slow execute time */
	public static final long DEFAULT_SLOW_EXECUTE_TIME = 500L;

	/** method slow execute time */
	private long _slowExecuteTime = DEFAULT_SLOW_EXECUTE_TIME;
	
	/** logger */
	private static final Loggers LOGGER = Loggers.getLoggers(Interceptor.class);

	/**
	 * Sets the method slow execute time.
	 * 
	 * @param slowExecuteTime method slow execute time.
	 */
	public void setSlowExecuteTime(long slowExecuteTime) {
		_slowExecuteTime = slowExecuteTime;
	}
	
	/**
	 * Gets the method slow execute time. If not sets the value, 
	 * will return the default value {@link #DEFAULT_SLOW_EXECUTE_TIME}.
	 * 
	 * @return the method slow execute time.
	 * 
	 * @see    #DEFAULT_SLOW_EXECUTE_TIME
	 */
	public long getSlowExecuteTime() {
		return _slowExecuteTime;
	}
	
	/**
	 * Intercepts the target method invocation to record the method invoke logs.
	 * 
	 * @param  invocation the method invocation.
	 * 
	 * @return the return value of invocation after intercepts.
	 * 
	 * @throws Throwable any exception or error that was thrown whiling intercept.
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		logBefore(invocation);

        Object result = doExecute(invocation);

        logAfter(invocation);
        
		return result;
	}
	
	// ---- private methods -------------------------------------------------------------
	private void logBefore(Invocation invocation) {
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();

        LOGGER.debug("*********** Start of execute method [{0}] ***********", methodName);
        LOGGER.debug("Method parameters are {0}.", Arrays.toString(arguments));
    }
	
	private Object doExecute(Invocation invocation) throws Throwable {
        Object result = null;
        long startTime = System.currentTimeMillis();
        try {
            result = invocation.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage(), throwable);
            throw throwable;
        }
        long endTime = System.currentTimeMillis();

        logExecuteTime(endTime - startTime);

        return result;
    }
	
	private void logAfter(Invocation invocation) {
        LOGGER.debug("************ End of execute method [{0}] ************", 
        		 	 invocation.getMethod().getName());
    }
	
	private void logExecuteTime(long executeTime) {
        if (executeTime <= _slowExecuteTime) {
            LOGGER.debug("Method of execution time is {0}ms.", executeTime);
        } else {
            LOGGER.warn("Method of execution time of {0}ms is too slow!", executeTime);
        }
    }
	
}

