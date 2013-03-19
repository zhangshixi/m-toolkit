package com.mtoolkit.util;

import java.util.LinkedList;
import java.util.List;

/**
 * An utility class that provides some helpful proxy utility methods.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 02/04/2012
 * @since 	JDK1.5
 */
public class ProxyUtil {
	
	 /**
     * <p>Gets an array of {@link Class} objects representing all interfaces 
     * implemented by the given class and its superclasses.</p>
     * <p/>
     * 
     * <p>The order is determined by looking through each interface in turn 
     * as declared in the source file and following its hierarchy up. 
     * Then each superclass is considered in the same way. 
     * Later duplicates are ignored, so the order is maintained.</p>
     * <p/>
     *
     * @param  clazz the class to look up, may be {@code null}.
     * 
     * @return an array of {@link Class} objects representing all interfaces 
     * 		   implemented by the given class and its superclasses or 
     * 		   <code>null</code> if input class is null.
     */
    public static Class<?>[] getAllInterfaces(Class<?> clazz) {
        List<Class<?>> interfaces = doGetAllInterfaces(clazz, new LinkedList<Class<?>>());
        
        return interfaces == null 
        		? null 
        		: (Class[]) interfaces.toArray(new Class[interfaces.size()]);
    }

    /**
     * Returns the class name as you would expect to see it in Java code.
     * 
     * <p>
     * 		<b>Examples:</b> 
     * 		<ul> <li>getJavaClassName(Object[].class) == "Object[]"</li> 
     * 			 <li>getJavaClassName(Object[][].class) == "Object[][]"</li> 
     * 			 <li>getJavaClassName(Integer.TYPE) == "int"</li> 
     * 		</ul>
     * </p>
     *
     * @param  clazz the class.
     * 
     * @return the class' name as you would expect to see it in Java code.
     */
    public static String getJavaClassName(Class<?> clazz) {
        if(clazz.isArray()) {
            return getJavaClassName(clazz.getComponentType()) + "[]";
        }
        
        return clazz.getName();
    }
    
    // ---- private methods ---------------------------------------------------------------
    private static List<Class<?>> doGetAllInterfaces(Class<?> clazz, List<Class<?>> list) {
        if(clazz == null) {
            return null;
        }
        
        while(clazz != null) {
            Class<?>[] interfaces = clazz.getInterfaces();
            
            for(int i = 0; i < interfaces.length; i++) {
                if(!list.contains(interfaces[i])) {
                    list.add(interfaces[i]);
                }
                doGetAllInterfaces(interfaces[i], list);
            }
            
            clazz = clazz.getSuperclass();
        }
        
        return list;
    }

}
