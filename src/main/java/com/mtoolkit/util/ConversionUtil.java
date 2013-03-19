package com.mtoolkit.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

/**
 * A utility class that provides convert a java data type to another.
 * Now, it supports conversion as following:
 * <pre>
 * 		+--------------+--------------+--------------+
 * 		+    METHOD    +     FROM     +      TO      +
 *		+--------------+--------------+--------------+
 *		+  char2Bytes  +     char     +    byte[]    +
 * 		+--------------+--------------+--------------+
 *		+ short2Bytes  +    short     +    byte[]    +
 * 		+--------------+--------------+--------------+
 *		+  int2Bytes   +     int      +    byte[]    +
 *		+--------------+--------------+--------------+
 *		+ float2Bytes  +    float     +    byte[]    +
 *		+--------------+--------------+--------------+
 *		+  long2Bytes  +     long     +    byte[]    +
 *		+--------------+--------------+--------------+
 *		+ double2Bytes +    double    +    byte[]    +
 *		+--------------+--------------+--------------+
 *		+ string2Bytes +    String    +    byte[]    +
 *		+--------------+--------------+--------------+
 *		+ object2Bytes +    Object    +    byte[]    +
 *		+--------------+--------------+--------------+
 * 		+  bytes2Char  +    byte[]    +     char     +
 *		+--------------+--------------+--------------+
 * 		+ bytes2Short  +    byte[]    +    short     +
 *		+--------------+--------------+--------------+
 *		+  bytes2Int   +    byte[]    +     int      +
 *		+--------------+--------------+--------------+
 * 		+ bytes2Float  +    byte[]    +    float     +
 *		+--------------+--------------+--------------+
 *		+  bytes2Long  +    byte[]    +     long     +
 *		+--------------+--------------+--------------+
 *		+ bytes2Double +    byte[]    +    double    +
 *		+--------------+--------------+--------------+
 *		+ bytes2String +    byte[]    +    String    +
 *		+--------------+--------------+--------------+
 *		+ bytes2Object +    byte[]    +    Object    +
 *		+--------------+--------------+--------------+
 *		+  bytes2Hex   +    byte[]    +  Hex String  +
 *		+--------------+--------------+--------------+
 *		+  hex2Bytes   +  Hex String  +    byte[]    +
 *		+--------------+--------------+--------------+
 * </pre>
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 26/8/2011
 * @since 	JDK1.5
 */
public class ConversionUtil {

	/**
	 * Private constructor, not permit to construct the instance.
	 */
    private ConversionUtil() {
    }
    
    /**
     * Converts a <code>char</code> value to a <code>byte array</code>.
     * 
     * @param 	value a char type value.
     * 
     * @return	a byte array that converted from the specified char value.	
     */
    public static byte[] char2Bytes(char value) {
    	return new byte[]{
    			(byte) ((value >> 0) & 0xFF),
    			(byte) ((value >> 8) & 0xFF)
    	};
    }
    
    /**
     * Converts a <code>byte array</code> to a </code>char</code> value.
     * 
     * @param 	value a byte array.
     * 
     * @return  a char value that converted from the specified byte array.
     * 
     * @throws  NullPointerException if <code>value</code> is null.
     * @throws  ArrayIndexOutOfBoundsException if <code>value.length < 2</code>.
     */
    public static char bytes2Char(byte[] value) {
    	return (char) (((value[1] & 0xFF) << 8) |
    				   ((value[0] & 0xFF) << 0));
    }
    
    /**
     * Converts a <code>short</code> value to a <code>byte array</code>.
     * 
     * @param 	value a short type value.
     * 
     * @return 	a byte array that converted from the specified short value.
     */
    public static byte[] short2Bytes(short value) {
    	return new byte[]{
    			(byte) ((value >> 0) & 0xFF),
    			(byte) ((value >> 8) & 0xFF)
    	};
    }

    /**
     * Converts a <code>byte array</code> to a </code>short</code> value.
     * 
     * @param 	value a byte array.
     * 
     * @return	a short value that converted from the specified byte array.
     * 
     * @throws  NullPointerException if <code>value</code> is null.
     * @throws  ArrayIndexOutOfBoundsException if <code>value.length < 2</code>.
     */
    public static short bytes2Short(byte[] value) {
    	return (short) (((value[1] & 0xFF) << 8) | 
    					((value[0] & 0xFF) << 0));
    }

    /**
     * Converts a <code>int</code> value to a <code>byte array</code>.
     * 
     * @param 	value a int type value.
     * 
     * @return 	a byte array that converted from the specified int value.
     */
    public static byte[] int2Bytes(int value) {
        return new byte[]{
        		(byte) ((value >> 0 ) & 0xFF),
        		(byte) ((value >> 8 ) & 0xFF),
        		(byte) ((value >> 16) & 0xFF),
        		(byte) ((value >> 24) & 0xFF)
        };
    }

    /**
     * Converts a <code>byte array</code> to a </code>int</code> value.
     * 
     * @param 	value a byte array.
     * 
     * @return	a int value that converted from the specified byte array.
     * 
     * @throws  NullPointerException if <code>value</code> is null.
     * @throws  ArrayIndexOutOfBoundsException if <code>value.length < 4</code>.
     */
    public static int bytes2Int(byte[] value) {
        return (int) (((value[3] & 0xFF) << 24) |
        	    	  ((value[2] & 0xFF) << 16) |
        	          ((value[1] & 0xFF) << 8 ) |
        	          ((value[0] & 0xFF) << 0 ));
    }

    /**
     * Converts a <code>long</code> value to a <code>byte array</code>.
     * 
     * @param 	value a long type value.
     * 
     * @return 	a byte array that converted from the specified long value.
     */
    public static byte[] long2Bytes(long value) {
        return new byte[] {
        		(byte) ((value >> 0 ) & 0xFF),
        		(byte) ((value >> 8 ) & 0xFF),
        		(byte) ((value >> 16) & 0xFF),
        		(byte) ((value >> 24) & 0xFF),
        		(byte) ((value >> 32) & 0xFF),
        		(byte) ((value >> 40) & 0xFF),
        		(byte) ((value >> 48) & 0xFF),
        		(byte) ((value >> 56) & 0xFF)
        };
    }

    /**
     * Converts a <code>byte array</code> to a </code>long</code> value.
     * 
     * @param 	value a byte array.
     * 
     * @return	a long value that converted from the specified byte array.
     * 
     * @throws  NullPointerException if <code>value</code> is null.
     * @throws  ArrayIndexOutOfBoundsException if <code>value.length < 8</code>.
     */
    public static long bytes2Long(byte[] value) {
    	return (((long) (value[7] & 0xFF)) << 56 |
    		    ((long) (value[6] & 0xFF)) << 48 |
    		    ((long) (value[5] & 0xFF)) << 40 |
    		    ((long) (value[4] & 0xFF)) << 32 |
    		    ((long) (value[3] & 0xFF)) << 24 |
    		    ((long) (value[2] & 0xFF)) << 16 |
    		    ((long) (value[1] & 0xFF)) << 8  |
    		    ((long) (value[0] & 0xFF)) << 0  );
    }

    /**
     * Converts a <code>float</code> value to a <code>byte array</code>.
     * 
     * @param 	value a float type value.
     * 
     * @return 	a byte array that converted from the specified float value.
     */
    public static byte[] float2Bytes(float value) {
    	return int2Bytes(Float.floatToIntBits(value));
    }

    /**
     * Converts a <code>byte array</code> to a </code>float</code> value.
     * 
     * @param 	value a byte array.
     * 
     * @return	a float value that converted from the specified byte array.
     * 
     * @throws  NullPointerException if <code>value</code> is null.
     * @throws  ArrayIndexOutOfBoundsException if <code>value.length < 4</code>.
     */
    public static float bytes2Float(byte[] value) {
    	return Float.intBitsToFloat(bytes2Int(value));
    }

    /**
     * Converts a <code>double</code> value to a <code>byte array</code>.
     * 
     * @param 	value a double type value.
     * 
     * @return 	a byte array that converted from the specified double value.
     */
    public static byte[] double2Bytes(double value) {
    	return long2Bytes(Double.doubleToRawLongBits(value));
    }

    /**
     * Converts a <code>byte array</code> to a </code>double</code> value.
     * 
     * @param 	value a byte array.
     * 
     * @return	a double value that converted from the specified byte array.
     * 
     * @throws  NullPointerException if <code>value</code> is null.
     * @throws  ArrayIndexOutOfBoundsException if <code>value.length < 8</code>.
     */
    public static double bytes2Double(byte[] value) {
    	return Double.longBitsToDouble(bytes2Long(value));
    }

    /**
     * Converts a <code>string</code> value to a <code>byte array</code>
     * with default charset format <code>UTF-8</code>.
     * 
     * @param 	value a string type value.
     * 
     * @return 	a byte array that converted from the specified string value.
     * 
     * @throws  NullPointerException if <code>value</code> is null.
     */
    public static byte[] string2Bytes(String value) {
    	return string2Bytes(value, CharsetUtil.UTF_8);
    }
    
    /**
     * Converts a <code>string</code> value to a <code>byte array</code> 
     * with the specified charset format.
     * 
     * @param 	value a string type value.
     * 
     * @return 	a byte array that converted from the specified string value.
     * 
     * @throws  NullPointerException if <code>value</code> or <code>charset</code> is null.
     */
    public static byte[] string2Bytes(String value, Charset charset) {
    	return value.getBytes(charset);
    }

    /**
     * Converts a <code>byte array</code> to a </code>string</code> value
     * with default charset format <code>UTF-8</code>.
     * 
     * @param 	value a byte array.
     * 
     * @return	a string value that converted from the specified byte array.
     * 
     * @throws  NullPointerException if <code>value</code> is null.
     */
    public static String bytes2String(byte[] value) {
        return bytes2String(value, CharsetUtil.UTF_8);
    }

    /**
     * Converts a <code>byte array</code> to a </code>string</code> value
     * with the specified charset format.
     * 
     * @param 	value a byte array.
     * 
     * @return	a string value that converted from the specified byte array.
     * 
     * @throws  NullPointerException if <code>value</code> or <code>charset</code> is null.
     */
    public static String bytes2String(byte[] value, Charset charset) {
    	return new String(value, charset);
    }
    
    /**
     * Converts a <code>object</code> value to a <code>byte array</code>.
     * 
     * @param 	value a object type value.
     * 
     * @return 	a byte array that converted from the specified object value.
     * 
     * @throws 	IOExceptoin if an I/O error occurs while converting object to byte array.
     */
    public static byte[] object2Bytes(Object value) throws IOException {
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
    	
    	ObjectOutputStream out = null;
    	try {
    		out = new ObjectOutputStream(output);
    		out.writeObject(value);
    	} finally {
    		if (out != null) {
    			out.close();
    		}
    	}
		
		return output.toByteArray();
    }

    /**
     * Converts a <code>byte array</code> to a </code>object</code> value.
     * 
     * @param 	value a byte array.
     * 
     * @return	a object value that converted from the specified byte array.
     * 
     * @throws 	IOException 
     * 			if an I/O error occurs while converting object to byte array.
     * @throws 	ClassNotFoundException 
     * 			if not found the target object class.
     * @throws 	NotSerializableException 
     * 			if the target object is not implemented {@link java.io.Serializable} interface.
     * @throws  NullPointerException if <code>value</code> is null.
     */
    @SuppressWarnings("unchecked")
    public static <T> T bytes2Object(byte[] value) throws IOException, ClassNotFoundException {    	
    	ObjectInputStream input = null;
    	try {
    		input = new ObjectInputStream(new ByteArrayInputStream(value));
	    	return (T) input.readObject();
    	} finally {
    		if (input != null) {
    			input.close();
    		}
    	}
    }
    
    /** hex digit character array */
    private static final char[] HEX_DIGITS = { 
    	'0', '1', '2', '3', '4', '5', '6', '7', 
    	'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' 
    };
    /**
     * Converts a <code>hex string</code> value to a <code>byte array</code>.
     * 
     * @param  value a hex string type value.
     * 
     * @return a byte array that converted from the specified string value.
     * 
     * @throws NullPointerException if <code>value</code> is null.
     */
    public static byte[] hex2Bytes(String value) {
    	int length = value.length();  
        byte[] bytes = new byte[length >> 1];  
 
        for (int i = 0, j = 0; j < length; i++) {
        	byte high = (byte) (Character.digit(value.charAt(j++), 16) & 0xFF);
        	byte low  = (byte) (Character.digit(value.charAt(j++), 16) & 0xFF);
            
            bytes[i] = (byte) (high << 4 | low);  
        }  
 
        return bytes;  
    }
    
    /**
     * Converts a <code>byte array</code> to a </code>hex string</code> value.
     * 
     * @param  value a byte array.
     * 
     * @return a hex string value that converted from the specified byte array.
     * 
     * @throws NullPointerException if <code>value</code> is null.
     */
    public static String bytes2Hex(byte[] value) {
    	int length = value.length;
    	char[] hex = new char[length << 1];
    	
        for (int i = 0, j = 0; i < length; i++) {  
        	hex[j++] = HEX_DIGITS[value[i] & 0xF0 >>> 4];
        	hex[j++] = HEX_DIGITS[value[i] & 0x0F];
        }  
        
    	return new String(hex);
    }

}
