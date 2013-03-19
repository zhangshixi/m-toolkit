package com.mtoolkit.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * A utility class that provides reflect common operations.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 19/11/2011
 * @since 	JDK1.5
 */
public class ReflectUtil {
	
	/**
     * Private constructor, not permit to construct this instance.
     */
    private ReflectUtil() {
    }
    
    /**
     * Looks for field in the specified class with given field name.
     * 
     * @param  clazz 	 the class of the field to look for.
     * @param  fieldName the name  of the field to look for.
     * 
     * @return the field in the receiver named by the argument.
     * 
     * @throws NullPointerException if {@code clazz} or {@code fieldName} is null.
     * @throws SecurityException 	if access is defined.
     * @throws NoSuchFieldException if the given field does not exist.
     */
    public static Field findField(Class<?> clazz, String fieldName) 
    	   throws NoSuchFieldException {
    	return clazz.getField(fieldName);
    }
    
    /**
     * Gets the value of the represented field in the target object.
     * 
     * @param  target object from which the represented field's value is to be extracted.
     * @param  field  represented field to look for.
     * 
     * @return the value of the represented field in object {@code target};
     * 		   primitive values are wrapped in an appropriate object before being returned.
     * 
     * @throws NullPointerException		
     * 		   if {@code target} or {@code field} is null.
     * @throws IllegalAccessException
     * 		   if the underlying field is inaccessible.
     * @throws IllegalArgumentException	
     * 		   if the specified object is not an instance of the class or interface 
     * 		   declaring the underlying field (or a subclass or implementor thereof).
     * @throws ExceptionInInitializerError 
     * 		   if the initialization provoked by this method fails.
     */
    public static Object getField(Object target, Field field) throws IllegalAccessException {
    	return field.get(target);
    }
    
    /**
     * Sets the given value to the represented field in the target object.
     * 
     * @param  target the object whose field should be modified.
     * @param  field  represented field to look for.
     * @param  value  the new value for the field being modified.
     * 
     * @throws NullPointerException
			   if {@code target} or {@code field} is null.
     * @throws IllegalAccessException
     * 	       if the underlying field is inaccessible.
     * @throws IllegalArgumentException
     * 		   if the specified object is not an instance of the class or interface 
     * 		   declaring the underlying field (or a subclass or implementor thereof), 
     * 		   or if an unwrapping conversion fails.
     * @throws ExceptionInInitializerError 
     * 		   if the initialization provoked by this method fails.
     */
    public static void setField(Object target, Field field, Object value) 
    	   throws IllegalAccessException {
    	field.set(target, value);
    }
    
    /**
     * Looks for the specified object method with the given name.
     *   
     * @param  target	  the target object of the method to look for.
     * @param  methodName the name of the method.
     * 
     * @return the method described by the arguments of the specified class. 
     * 
     * @throws NullPointerException	 if {@code clazz} is null.
     * @throws SecurityException	 if member access is not allowed.
     * @throws NoSuchMethodException if the method could not be found.
     */
    public static Method findMethod(Object target, String methodName) 
    	   throws NoSuchMethodException {
    	return findMethod(target, methodName, ArrayUtil.EMPTY_CLASS_ARRAY);
    }
    
    /**
     * Looks for the specified object method with the given name and argument types.
     * 
     * @param  target	  the target object of the method to look for.
     * @param  methodName the name of the method.
     * @param  argTypes	  the types of the arguments.
     * 
     * @return the method described by the arguments of the specified class. 
     * 
     * @throws NullPointerException	 if {@code clazz} is null.
     * @throws SecurityException	 if member access is not allowed.
     * @throws NoSuchMethodException if the method could not be found.
     */
    public static Method findMethod(Object target, String methodName, Class<?>... argTypes)
    	   throws NoSuchMethodException {
    	return findMethod(target.getClass(), methodName, argTypes);
    }
    
    /**
     * Looks for the specified class method with the given name.
     * 
     * @param  clazz	  the class of the method to look for.
     * @param  methodName the name of the method.
     * 
     * @return the method described by the arguments of the specified class. 
     * 
     * @throws NullPointerException	 if {@code clazz} is null.
     * @throws SecurityException	 if member access is not allowed.
     * @throws NoSuchMethodException if the method could not be found.
     */
    public static Method findMethod(Class<?> clazz, String methodName) 
    	   throws NoSuchMethodException {
    	return findMethod(clazz, methodName, ArrayUtil.EMPTY_CLASS_ARRAY);
    }
    
    /**
     * Looks for a Method object which represents the method described 
     * by the arguments of the specified class.
     * 
     * @param  clazz	  the class of the method to look for.	 
     * @param  methodName the name of the method.
     * @param  argTypes   the types of the arguments.
     * 
     * @return the method described by the arguments of the specified class. 
     * 
     * @throws NullPointerException	 if {@code clazz} is null.
     * @throws SecurityException	 if member access is not allowed.
     * @throws NoSuchMethodException if the method could not be found.
     */
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... argTypes) 
    	   throws NoSuchMethodException {
    	return clazz.getMethod(methodName, argTypes);
    }
    
    /**
     * Invokes a named method.
     * 
     * @param  target	  invoke method on this object.
     * @param  methodName get method with this name
     * 
     * @return the value returned by the invoked method.
     * 
     * @throws NoSuchMethodException	 if there is no such accessible method.
     * @throws IllegalArgumentException  if the method and argument is invalid.
     * @throws IllegalAccessException	 if the requested method is not accessible via reflection.
     * @throws InvocationTargetException wraps an exception thrown by the method invoked.
     */
    public static Object invokeMethod(Object target, String methodName)
    	   throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    	return invokeMethod(target, methodName, ArrayUtil.EMPTY_OBJECT_ARRAY);
    }
    
    /**
     * Invokes a named method whose parameter type matches the object type.
     * 
     * @param target	 invoke method on this object.
     * @param methodName get method with this name.
     * @param args		 use this argument.
     * 
     * @return the value returned by the invoked method.
     * 
     * @throws NoSuchMethodException	 if there is no such accessible method.
     * @throws IllegalArgumentException  if the method and argument is invalid.
     * @throws IllegalAccessException	 if the requested method is not accessible via reflection.
     * @throws InvocationTargetException wraps an exception thrown by the method invoked.
     */
    public static Object invokeMethod(Object target, String methodName, Object... args)
    	   throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    	Object[] newArgs = (args == null ? ArrayUtil.EMPTY_OBJECT_ARRAY : args);
    	Class<?>[] argTypes = new Class<?>[newArgs.length];
    	
    	for (int i = 0; i < newArgs.length; i++) {
    		if (newArgs[i] != null) {
    			argTypes[i] = newArgs[i].getClass();
    		}
    	}
    	
    	return invokeMethod(target, methodName, newArgs, argTypes);
    }
    
    /**
     * Invokes a named method whose parameter type matches the object type.
     * 
     * @param  target	  invoke method on this object.
     * @param  methodName get method with this name.
     * @param  args		  use these arguments - treat null as empty array.
     * @param  argTypes	  match these parameters - treat null as empty array.
     * 
     * @return the value returned by the invoked method.
     * 
     * @throws NoSuchMethodException	 if there is no such accessible method.
     * @throws IllegalArgumentException  if the method and argument is invalid.
     * @throws IllegalAccessException	 if the requested method is not accessible via reflection.
     * @throws InvocationTargetException wraps an exception thrown by the method invoked.
     */
    public static Object invokeMethod(Object target, String methodName, Object[] args, Class<?>[] argTypes) 
    	   throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    	return findMethod(target.getClass(), methodName, argTypes).invoke(target, args);
    }
    
    /**
     * Invokes a named static method whose parameter type matches the object type.
     * 
     * @param  clazz	  invoke static method on this class.
     * @param  methodName get method with this name.
     * @param  args		  use these arguments - treat null as empty array.
     * 
     * @return the value returned by the invoked method.
     * 
     * @throws NoSuchMethodException	 if there is no such accessible method.
     * @throws IllegalArgumentException  if the method and argument is invalid.
     * @throws IllegalAccessException	 if the requested method is not accessible via reflection.
     * @throws InvocationTargetException wraps an exception thrown by the method invoked.
     */
    public static Object invokeStaticMethod(Class<?> clazz, String methodName, Object... args)
    	   throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    	Object[] newArgs = (args == null ? ArrayUtil.EMPTY_OBJECT_ARRAY : args);
    	Class<?>[] argTypes = new Class<?>[newArgs.length];
    	
    	for (int i = 0; i < newArgs.length; i++) {
    		if (newArgs[i] != null) {
    			argTypes[i] = newArgs[i].getClass();
    		}
    	}
    	
    	return invokeStaticMethod(clazz, methodName, newArgs, argTypes);
    }
    
    /**
     * Invokes a named static method whose parameter type matches the object type.
     * 
     * @param  clazz	  invoke static method on this class.
     * @param  methodName get method with this name.
     * @param  args		  use these arguments - treat null as empty array.
     * @param  argTypes	  match these parameters - treat null as empty array.
     * 
     * @return the value returned by the invoked method.
     * 
     * @throws NullPointerException		 if the {@code clazz} is null.
     * @throws SecurityException		 if member access is not allowed.
     * @throws NoSuchMethodException	 if there is no such accessible method.
     * @throws IllegalArgumentException  if the method and argument is invalid.
     * @throws IllegalAccessException	 if the requested method is not accessible via reflection.
     * @throws InvocationTargetException wraps an exception thrown by the method invoked.
     */
    public static Object invokeStaticMethod(Class<?> clazz, String methodName, Object[] args, Class<?>[] argTypes) 
    	   throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    	return findMethod(clazz, methodName, argTypes).invoke(null, args);
    }
    
    /**
     * Tests whether the given method is accessible.
     * 
     * @param  method the method witch for tests.
     * 
     * @return {@code true} if and only if the specified method can accessible;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code method} is null.
     */
    public static boolean isAccessibleMethod(Method method) {
    	return method.isSynthetic() && Modifier.isPublic(method.getModifiers());
    }
    
    /**
     * Tests whether the given method is an "equals" method. 
     * 
     * @param  method the method to check.
     * 
     * @return {@code true} if and only if the specified method is "equals" method;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code method} is null.
     * 
     * @see	   Object#equals(Object)
     */
    public static boolean isEqualsMethod(Method method) {
    	if (!"equals".equals(method.getName())) {
    		return false;
    	}
    	
    	Class<?>[] argTypes = method.getParameterTypes();
    	
    	return argTypes.length == 1 && argTypes[0] == Object.class;    	
    }
    
    /**
     * Tests whether the given method is a "hashCode" method. 
     * 
     * @param  method the method to check.
     * 
     * @return {@code true} if and only if the specified method is "hashCode" method;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code method} is null.
     * 
     * @see	   Object#hashCode()
     */
    public static boolean isHashCodeMethod(Method method) {
    	return "hashCode".equals(method.getName()) &&
    		   method.getParameterTypes().length == 0;
    }
    
    /**
     * Tests whether the given method is a "toString" method. 
     * 
     * @param  method the method to check.
     * 
     * @return {@code true} if and only if the specified method is "toString" method;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code method} is null.
     * 
     * @see	   Object#toString()
     */
    public static boolean isToStringMethod(Method method) {
    	return "toString".equals(method.getName()) && 
    		   method.getParameterTypes().length == 0;
    }
    
    /**
     * Tests whether the given field is a "public static final" constant.
     * 
     * @param  field the field to check.
     * 
     * @return {@code true} if and only if the specified field is a "public static final" constant;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code method} is null.
     */
    public static boolean isPublicStaticFinalField(Field field) {
    	int mod = field.getModifiers();
    	return Modifier.isPublic(mod) &&
    		   Modifier.isStatic(mod) &&
    		   Modifier.isFinal (mod)  ;
    }
    
}
