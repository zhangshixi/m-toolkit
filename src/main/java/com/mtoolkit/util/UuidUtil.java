package com.mtoolkit.util;

import java.util.UUID;

/**
 * UUID generator utility class that provides use UUID strategy 
 * to generate a random string witch represents a 128-bit value.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 28/9/2011
 * @since 	JDK1.5
 */
public class UuidUtil {

    /**
     * Private instructor, not permit to create this instance.
     */
    private UuidUtil() {
    }

    /**
     * Generates a unique random uuid string that replace "-" with "".
     * 
     * @return new uuid string.
     * 
     * @see	   #newUuidString(boolean)
     */
    public static String newUuidString() {
        return newUuidString(true);
    }
    
    /**
     * Generates a unique random uuid string. 
     * 
     * @param  replace whether or not character "-" with "".
     * 		   {@code true} replace, {@code false} not replace.
     * 
     * @return new uuid string. 
     */
    public static String newUuidString(boolean replace) {
    	String uuid = UUID.randomUUID().toString();
    	
    	if (replace) {
    		uuid = uuid.replace("-", "");
    	}
    	
        return uuid;
    }
}
