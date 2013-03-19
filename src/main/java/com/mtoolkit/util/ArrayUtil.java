package com.mtoolkit.util;

/**
 * A utility class that provides various common operations 
 * and constants of array.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.00, 5/8/2012
 * @since 	JDK1.5
 */
public class ArrayUtil {

    //---- public static final constant ----------------------------------------
    /** the location that not in the array */
    public static final int INDEX_NOT_FOUND = -1;
    
    /** empty {@code boolean} array */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    
    /** empty {@linkplain Boolean} object array */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    
    /** empty {@code byte} array */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    
    /** empty {@linkplain Boolean} object array */
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    
    /** empty {@code char} array */
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    
    /** empty {@linkplain Character} object array */
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
    
    /** empty {@code double} array */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    
    /** empty {@linkplain Double} object array */
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    
    /** empty {@code float} array */
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    
    /** empty {@linkplain Float} object array */
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    
    /** empty {@code long} array */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    
    /** empty {@linkplain Long} object array */
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    
    /** empty {@code int} array */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    
    /** empty {@linkplain Integer} object array */
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    
    /** empty {@code short} array */
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    
    /** empty {@linkplain Short} object array */
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    
    /** empty {@linkplain Object} array */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    
    /** empty {@linkplain Class} object array */
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[0];

    //---- private constructor -------------------------------------------------
    /**
     * Private constructor, not permit to construct the instance.
     */
    private ArrayUtil() {
    }

    //---- pack methods --------------------------------------------------------
    /**
     * Packs {@code boolean} array to {@linkplain Boolean} object array.
     * 
     * @param  array {@code boolean} array.
     * 
     * @return {@linkplain Boolean} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Boolean[] pack(boolean[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }

        final Boolean[] packAry = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            packAry[i] = Boolean.valueOf(array[i]);
        }

        return packAry;
    }

    /**
     * Packs {@code byte} array to {@linkplain Byte} object array.
     * 
     * @param  array {@code byte} array.
     * 
     * @return {@linkplain Byte} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Byte[] pack(byte[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }

        final Byte[] packAry = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            packAry[i] = Byte.valueOf(array[i]);
        }

        return packAry;
    }

    /**
     * Packs {@code char} array to {@linkplain Character} object array.
     * 
     * @param  array {@code char} array.
     * 
     * @return {@linkplain Character} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Character[] pack(char[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }

        final Character[] packAry = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            packAry[i] = Character.valueOf(array[i]);
        }

        return packAry;
    }

    /**
     * Packs {@code double} array to {@linkplain Double} object array.
     * 
     * @param  array {@code double} array.
     * 
     * @return {@linkplain Double} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Double[] pack(double[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }

        final Double[] packAry = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            packAry[i] = Double.valueOf(array[i]);
        }

        return packAry;
    }

    /**
     * Packs {@code float} array to {@linkplain Float} object array.
     * 
     * @param  array {@code float} array.
     * 
     * @return {@linkplain Float} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Float[] pack(float[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }

        final Float[] packAry = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            packAry[i] = Float.valueOf(array[i]);
        }

        return packAry;
    }

    /**
     * Packs {@code long} array to {@linkplain Long} object array.
     * 
     * @param  array {@code long} array.
     * 
     * @return {@linkplain Long} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Long[] pack(long[] array) {
        if (array == null) {
            throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }

        final Long[] packAry = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            packAry[i] = Long.valueOf(array[i]);
        }

        return packAry;
    }

    /**
     * Packs {@code int} array to {@linkplain Integer} object array.
     * 
     * @param  array {@code int} array.
     * 
     * @return {@linkplain Integer} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Integer[] pack(int[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        final Integer[] packAry = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            packAry[i] = Integer.valueOf(array[i]);
        }

        return packAry;
    }

    /**
     * Packs {@code short} array to {@linkplain Short} object array.
     * 
     * @param  array {@code short} array.
     * 
     * @return {@linkplain Short} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Short[] pack(short[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }

        final Short[] packAry = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            packAry[i] = Short.valueOf(array[i]);
        }

        return packAry;
    }

    //---- unpack methods ------------------------------------------------------
    /**
     * Unpacks {@linkplain Boolean} object array to {@code boolean} array.
     * 
     * @param array {@linkplain Boolean} array.
     * 
     * @return {@code boolean} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean[] unpack(Boolean[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }

        final boolean[] unpackAry = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            unpackAry[i] = array[i].booleanValue();
        }

        return unpackAry;
    }

    /**
     * Unpacks {@linkplain Byte} object array to {@code byte} array.
     * 
     * @param array {@linkplain Byte} array.
     * 
     * @return {@code byte} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static byte[] unpack(Byte[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }

        final byte[] unpackAry = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            unpackAry[i] = array[i].byteValue();
        }

        return unpackAry;
    }

    /**
     * Unpacks {@linkplain Character} object array to {@code char} array.
     * 
     * @param array {@linkplain Character} array.
     * 
     * @return {@code char} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static char[] unpack(Character[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }

        final char[] unpackAry = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            unpackAry[i] = array[i].charValue();
        }

        return unpackAry;
    }

    /**
     * Unpacks {@linkplain Double} object array to {@code double} array.
     * 
     * @param array {@linkplain Double} array.
     * 
     * @return {@code double} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static double[] unpack(Double[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }

        final double[] unpackAry = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            unpackAry[i] = array[i].doubleValue();
        }

        return unpackAry;
    }

    /**
     * Unpacks {@linkplain Float} object array to {@code float} array.
     * 
     * @param array {@linkplain Float} array.
     * 
     * @return {@code float} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static float[] unpack(Float[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }

        final float[] unpackAry = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            unpackAry[i] = array[i].floatValue();
        }

        return unpackAry;
    }

    /**
     * Unpacks {@linkplain Long} object array to {@code long} array.
     * 
     * @param array {@linkplain Long} array.
     * 
     * @return {@code long} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static long[] unpack(Long[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_LONG_ARRAY;
        }

        final long[] unpackAry = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            unpackAry[i] = array[i].longValue();
        }

        return unpackAry;
    }

    /**
     * Unpacks {@linkplain Integer} object array to {@code int} array.
     * 
     * @param array {@linkplain Integer} array.
     * 
     * @return {@code int} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static int[] unpack(Integer[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }

        final int[] unpackAry = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            unpackAry[i] = array[i].intValue();
        }

        return unpackAry;
    }

    /**
     * Unpacks {@linkplain Short} object array to {@code short} array.
     * 
     * @param array {@linkplain Short} array.
     * 
     * @return {@code short} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static short[] unpack(Short[] array) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        if (array.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }

        final short[] unpackAry = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            unpackAry[i] = array[i].shortValue();
        }

        return unpackAry;
    }

    //---- contains methods ----------------------------------------------------
    /**
     * Tests whether the given boolean {@code array} contains the specified {@code value}. 
     * 
     * @param  array given {@code boolean} array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean contains(boolean[] array, boolean value) {
        if (array == null) {
        	throw new NullPointerException("array");
        }

        for (boolean element : array) {
            if (element == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests whether the given byte {@code array} contains the specified {@code value}. 
     * 
     * @param  array given {@code byte} array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean contains(byte[] array, byte value) {
    	if (array == null) {
        	throw new NullPointerException("array");
        }

        for (byte element : array) {
            if (element == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests whether the given char {@code array} contains the specified {@code value}. 
     * 
     * @param  array given {@code char} array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean contains(char[] array, char value) {
    	if (array == null) {
        	throw new NullPointerException("array");
        }

        for (char element : array) {
            if (element == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests whether the given double {@code array} contains the specified {@code value}. 
     * 
     * @param  array given {@code double} array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean contains(double[] array, double value) {
    	if (array == null) {
        	throw new NullPointerException("array");
        }

        for (double element : array) {
            if (element == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests whether the given float {@code array} contains the specified {@code value}. 
     * 
     * @param  array given {@code float} array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean contains(float[] array, float value) {
    	if (array == null) {
        	throw new NullPointerException("array");
        }

        for (float element : array) {
            if (element == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests whether the given long {@code array} contains the specified {@code value}. 
     * 
     * @param  array given {@code long} array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean contains(long[] array, long value) {
    	if (array == null) {
        	throw new NullPointerException("array");
        }

        for (long element : array) {
            if (element == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests whether the given int {@code array} contains the specified {@code value}. 
     * 
     * @param  array given {@code int} array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean contains(int[] array, int value) {
    	if (array == null) {
        	throw new NullPointerException("array");
        }

        for (int element : array) {
            if (element == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests whether the given short {@code array} contains the specified {@code value}. 
     * 
     * @param  array given {@code short} array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean contains(short[] array, short value) {
    	if (array == null) {
        	throw new NullPointerException("array");
        }

        for (short element : array) {
            if (element == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Tests whether the given type object {@code array} contains the specified {@code value}. 
     * 
     * @param  array given object array.
     * @param  value tests value.
     * 
     * @return {@code true} if and only if the array contains the value;
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static <T> boolean contains(T[] array, T value) {
    	if (array == null) {
        	throw new NullPointerException("array");
        }

        for (T element : array) {
            if (element.equals(value)) {
                return true;
            }
        }

        return false;
    }

    //---- add methods ---------------------------------------------------------
    /**
     * Adds the given {@code value} to the {@code index} of the specified boolean {@code array}.
     * 
     * @param  array {@code boolean} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static boolean[] add(boolean[] array, int index, boolean value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final boolean[] result = new boolean[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    /**
     * Adds the given {@code value} to the {@code index} of the specified byte {@code array}.
     * 
     * @param  array {@code byte} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.
     */
    public static byte[] add(byte[] array, int index, byte value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final byte[] result = new byte[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    /**
     * Adds the given {@code value} to the {@code index} of the specified char {@code array}.
     * 
     * @param  array {@code char} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.
     */
    public static char[] add(char[] array, int index, char value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final char[] result = new char[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    /**
     * Adds the given {@code value} to the {@code index} of the specified double {@code array}.
     * 
     * @param  array {@code double} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.
     */
    public static double[] add(double[] array, int index, double value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final double[] result = new double[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    /**
     * Adds the given {@code value} to the {@code index} of the specified float {@code array}.
     * 
     * @param  array {@code float} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.
     */
    public static float[] add(float[] array, int index, float value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final float[] result = new float[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    /**
     * Adds the given {@code value} to the {@code index} of the specified long {@code array}.
     * 
     * @param  array {@code long} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.
     */
    public static long[] add(long[] array, int index, long value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final long[] result = new long[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    /**
     * Adds the given {@code value} to the {@code index} of the specified int {@code array}.
     * 
     * @param  array {@code int} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.
     */
    public static int[] add(int[] array, int index, int value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final int[] result = new int[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    /**
     * Adds the given {@code value} to the {@code index} of the specified short {@code array}.
     * 
     * @param  array {@code short} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.
     */
    public static short[] add(short[] array, int index, short value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final short[] result = new short[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    /**
     * Adds the given {@code value} to the {@code index} of the specified object {@code array}.
     * 
     * @param  array {@code object} array witch adds to.
     * @param  index add index, begin with {@code 0}.
     * @param  value add value.
     * 
     * @return {@code true} if and only if add value succeed; 
     * 		   {@code false} otherwise.
     * 
     * @throws NullPointerException if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.
     */
    public static Object[] add(Object[] array, int index, Object value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (index < 0 || index > array.length) {
    		throw new ArrayIndexOutOfBoundsException(
    			  "Index: " + index + ", array.length: " + array.length);
    	}
    	
    	final Object[] result = new Object[array.length + 1];
    	System.arraycopy(array, 0, result, 0, index);
    	result[index] = value;
    	System.arraycopy(array, index, result, index + 1, array.length - index);
    	
        return result;
    }

    //---- addElement methods --------------------------------------------------
    /**
     * Adds the given {@code value} to the end of the specified boolean {@code array}. 
     * 
     * @param  array {@code boolean} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code boolean} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean[] addElement(boolean[] array, boolean value) {
        if (array == null) {
            throw new NullPointerException("array");
        }

        final boolean[] result = new boolean[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    /**
     * Adds the given {@code value} to the end of the specified byte {@code array}. 
     * 
     * @param  array {@code byte} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code byte} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static byte[] addElement(byte[] array, byte value) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

        final byte[] result = new byte[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    /**
     * Adds the given {@code value} to the end of the specified char {@code array}. 
     * 
     * @param  array {@code char} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code char} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static char[] addElement(char[] array, char value) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

        final char[] result = new char[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    /**
     * Adds the given {@code value} to the end of the specified double {@code array}. 
     * 
     * @param  array {@code double} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code double} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static double[] addElement(double[] array, double value) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

        final double[] result = new double[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    /**
     * Adds the given {@code value} to the end of the specified float {@code array}. 
     * 
     * @param  array {@code float} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code float} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static float[] addElement(float[] array, float value) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

        final float[] result = new float[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    /**
     * Adds the given {@code value} to the end of the specified long {@code array}. 
     * 
     * @param  array {@code long} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code long} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static long[] addElement(long[] array, long value) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

        final long[] result = new long[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    /**
     * Adds the given {@code value} to the end of the specified int {@code array}. 
     * 
     * @param  array {@code int} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code int} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static int[] addElement(int[] array, int value) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

        final int[] result = new int[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    /**
     * Adds the given {@code value} to the end of the specified short {@code array}. 
     * 
     * @param  array {@code short} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code short} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static short[] addElement(short[] array, short value) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

        final short[] result = new short[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    /**
     * Adds the given {@code value} to the end of the specified object {@code array}. 
     * 
     * @param  array {@code object} array witch adds to.
     * @param  value add element value.
     * 
     * @return added {@code object} array.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Object[] addElement(Object[] array, Object value) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

        final Object[] result = new Object[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[array.length] = value;

        return result;
    }

    //---- remove methods ------------------------------------------------------
    /**
     * Removes the element at the {@code index} of the specified boolean {@code array}.
     * 
     * @param  array {@code boolean} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code boolean} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static boolean[] remove(boolean[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final boolean[] result = new boolean[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }
    
    /**
     * Removes the element at the {@code index} of the specified byte {@code array}.
     * 
     * @param  array {@code byte} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code byte} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static byte[] remove(byte[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final byte[] result = new byte[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }

    /**
     * Removes the element at the {@code index} of the specified char {@code array}.
     * 
     * @param  array {@code char} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code char} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static char[] remove(char[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final char[] result = new char[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }

    /**
     * Removes the element at the {@code index} of the specified double {@code array}.
     * 
     * @param  array {@code double} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code double} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static double[] remove(double[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final double[] result = new double[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }

    /**
     * Removes the element at the {@code index} of the specified float {@code array}.
     * 
     * @param  array {@code float} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code float} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static float[] remove(float[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final float[] result = new float[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }

    /**
     * Removes the element at the {@code index} of the specified long {@code array}.
     * 
     * @param  array {@code long} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code long} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static long[] remove(long[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final long[] result = new long[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }

    /**
     * Removes the element at the {@code index} of the specified int {@code array}.
     * 
     * @param  array {@code int} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code int} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static int[] remove(int[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final int[] result = new int[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }

    /**
     * Removes the element at the {@code index} of the specified short {@code array}.
     * 
     * @param  array {@code short} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code short} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static short[] remove(short[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final short[] result = new short[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }

    /**
     * Removes the element at the {@code index} of the specified object {@code array}.
     * 
     * @param  array {@code object} array witch removes from.
     * @param  index remove index, begin with {@code 0}.
     * 
     * @return removed {@code object} array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code index} is negative of larger than {@code array.length}.  
     */
    public static Object[] remove(Object[] array, int index) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
                  "Index:" + index + ", array.length:" + array.length);
        }

        final Object[] result = new Object[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        }

        return result;
    }

    //---- removeElement methods -----------------------------------------------
    /**
     * Removes the given element {@code value} from the specified boolean {@code array}.
     *  
     * @param  array {@code boolean} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code boolean} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static boolean[] removeElement(boolean[] array, boolean value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    /**
     * Removes the given element {@code value} from the specified byte {@code array}.
     *  
     * @param  array {@code byte} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code byte} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static byte[] removeElement(byte[] array, byte value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    /**
     * Removes the given element {@code value} from the specified char {@code array}.
     *  
     * @param  array {@code char} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code char} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static char[] removeElement(char[] array, char value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    /**
     * Removes the given element {@code value} from the specified double {@code array}.
     *  
     * @param  array {@code double} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code double} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static double[] removeElement(double[] array, double value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    /**
     * Removes the given element {@code value} from the specified float {@code array}.
     *  
     * @param  array {@code float} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code float} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static float[] removeElement(float[] array, float value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    /**
     * Removes the given element {@code value} from the specified long {@code array}.
     *  
     * @param  array {@code long} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code long} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static long[] removeElement(long[] array, long value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    /**
     * Removes the given element {@code value} from the specified int {@code array}.
     *  
     * @param  array {@code int} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code int} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static int[] removeElement(int[] array, int value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    /**
     * Removes the given element {@code value} from the specified short {@code array}.
     *  
     * @param  array {@code short} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code short} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static short[] removeElement(short[] array, short value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    /**
     * Removes the given element {@code value} from the specified object {@code array}.
     *  
     * @param  array {@code object} array witch removes from.
     * @param  value remove value.
     * 
     * @return removed {@code object} value. if not found the given 
     * 		   {@code value} in the {@code array}, return {@code array}.
     * 
     * @throws NullPointerException if {@code array} is null.
     */
    public static Object[] removeElement(Object[] array, Object value) {
    	int index = indexOf(array, value);
    	return index == INDEX_NOT_FOUND ? array : remove(array, index);
    }

    //---- addAll methods ------------------------------------------------------
    /**
     * Adds all of the {@code value} to the specified boolean {@code array}.
     * 
     * @param  array {@code boolean} array witch add to.
     * @param  value {@code boolean} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static boolean[] addAll(boolean[] array, boolean[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final boolean[] result = new boolean[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }
    
    /**
     * Adds all of the {@code value} to the specified byte {@code array}.
     * 
     * @param  array {@code byte} array witch add to.
     * @param  value {@code byte} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static byte[] addAll(byte[] array, byte[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final byte[] result = new byte[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }

    /**
     * Adds all of the {@code value} to the specified char {@code array}.
     * 
     * @param  array {@code char} array witch add to.
     * @param  value {@code char} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static char[] addAll(char[] array, char[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final char[] result = new char[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }

    /**
     * Adds all of the {@code value} to the specified double {@code array}.
     * 
     * @param  array {@code double} array witch add to.
     * @param  value {@code double} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static double[] addAll(double[] array, double[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final double[] result = new double[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }

    /**
     * Adds all of the {@code value} to the specified float {@code array}.
     * 
     * @param  array {@code float} array witch add to.
     * @param  value {@code float} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static float[] addAll(float[] array, float[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final float[] result = new float[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }

    /**
     * Adds all of the {@code value} to the specified long {@code array}.
     * 
     * @param  array {@code long} array witch add to.
     * @param  value {@code long} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static long[] addAll(long[] array, long[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final long[] result = new long[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }

    /**
     * Adds all of the {@code value} to the specified int {@code array}.
     * 
     * @param  array {@code int} array witch add to.
     * @param  value {@code int} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static int[] addAll(int[] array, int[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final int[] result = new int[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }

    /**
     * Adds all of the {@code value} to the specified short {@code array}.
     * 
     * @param  array {@code short} array witch add to.
     * @param  value {@code short} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static short[] addAll(short[] array, short[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final short[] result = new short[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }

    /**
     * Adds all of the {@code value} to the specified object {@code array}.
     * 
     * @param  array {@code object} array witch add to.
     * @param  value {@code object} array witch for added.
     * 
     * @return added array.
     * 
     * @throws NullPointerException if {@code array} or {@code value} is null.
     */
    public static Object[] addAll(Object[] array, Object[] value) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
    	if (value == null) {
    		throw new NullPointerException("value");
    	}
    	
    	final Object[] result = new Object[array.length + value.length];
    	System.arraycopy(array, 0, result, 0, array.length);
    	System.arraycopy(value, 0, result, array.length, value.length);
    	
        return result;
    }

    //---- subArray methods ----------------------------------------------------
    /**
     * Splits the range of the specified boolean {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static boolean[] subArray(boolean[] array, int from, int to) {
        if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final boolean[] subArray = new boolean[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    /**
     * Splits the range of the specified byte {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static byte[] subArray(byte[] array, int from, int to) {
    	if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final byte[] subArray = new byte[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    /**
     * Splits the range of the specified char {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static char[] subArray(char[] array, int from, int to) {
    	if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final char[] subArray = new char[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    /**
     * Splits the range of the specified double {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static double[] subArray(double[] array, int from, int to) {
    	if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final double[] subArray = new double[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    /**
     * Splits the range of the specified float {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static float[] subArray(float[] array, int from, int to) {
    	if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final float[] subArray = new float[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    /**
     * Splits the range of the specified long {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static long[] subArray(long[] array, int from, int to) {
    	if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final long[] subArray = new long[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    /**
     * Splits the range of the specified int {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static int[] subArray(int[] array, int from, int to) {
    	if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final int[] subArray = new int[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    /**
     * Splits the range of the specified short {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static short[] subArray(short[] array, int from, int to) {
    	if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final short[] subArray = new short[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    /**
     * Splits the range of the specified object {@code array} into a new array.
     * 
     * @param  array source array.
     * @param  from  from index(include).
     * @param  to 	 to index(exclude).
     * 
     * @return sub array that from {@code from} index(include) to {@code to} index(exclude).
     * 
     * @throws NullPointerException
     * 		   if {@code array} is null. 
     * @throws ArrayIndexOutOfBoundsException
     * 		   if {@code from} or {@code to} is negative 
     * 		   or larger than {@code array.length}.
     * @throws IllegalArgumentException
     * 		   if {@code to} less then {@code from}.
     */
    public static Object[] subArray(Object[] array, int from, int to) {
    	if (array == null) {
            throw new NullPointerException("array");
        }
        if (from < 0 || from >= array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "from: " + from + ", array.length: " + array.length);
        }
        if (to < 0 || to > array.length) {
        	throw new ArrayIndexOutOfBoundsException(
        		  "to: " + from + ", array.length: " + array.length);
        }
        if (to < from) {
        	throw new IllegalArgumentException(
        		  "to(" + to + ") < from(" + from + ")");
        }

        final int size = to - from;
        final Object[] subArray = new Object[size];
        System.arraycopy(array, from, subArray, 0, size);

        return subArray;
    }

    //---- reverse methods -----------------------------------------------------
    /**
     * Reverses the specified boolean {@code array}.
     * 
     * @param  array {@code boolean} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static boolean[] reverse(boolean[] array) {
        if (array == null) {
            throw new NullPointerException("array");
        }

        final boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    /**
     * Reverses the specified byte {@code array}.
     * 
     * @param array {@code byte} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static byte[] reverse(byte[] array) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

    	final byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    /**
     * Reverses the specified char {@code array}.
     * 
     * @param array {@code char} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static char[] reverse(char[] array) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

    	final char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    /**
     * Reverses the specified double {@code array}.
     * 
     * @param array {@code double} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static double[] reverse(double[] array) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

    	final double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    /**
     * Reverses the specified float {@code array}.
     * 
     * @param array {@code float} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static float[] reverse(float[] array) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

    	final float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    /**
     * Reverses the specified long {@code array}.
     * 
     * @param array {@code long} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static long[] reverse(long[] array) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

    	final long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    /**
     * Reverses the specified int {@code array}.
     * 
     * @param array {@code int} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static int[] reverse(int[] array) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

    	final int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    /**
     * Reverses the specified short {@code array}.
     * 
     * @param array {@code short} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static short[] reverse(short[] array) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

    	final short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    /**
     * Reverses the specified object {@code array}.
     * 
     * @param array {@code object} array.
     * 
     * @return reversed array.
     * 
     * @throws NullPointerException if the {@code array} is null.
     */
    public static Object[] reverse(Object[] array) {
    	if (array == null) {
            throw new NullPointerException("array");
        }

    	final Object[] result = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
        	result[i] = array[array.length - i - 1];
        }
        
        return result;
    }

    //---- indexOf methods -----------------------------------------------------
    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the boolean {@code array}. 
     * 
     * @param  array {@code boolean} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(boolean[], boolean, int)
     */
    public static int indexOf(boolean[] array, boolean value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the byte {@code array}. 
     * 
     * @param  array {@code byte} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(byte[], byte, int)
     */
    public static int indexOf(byte[] array, byte value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the char {@code array}. 
     * 
     * @param  array {@code char} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(char[], char, int)
     */
    public static int indexOf(char[] array, char value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the double {@code array}. 
     * 
     * @param  array {@code double} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(double[], double, int)
     */
    public static int indexOf(double[] array, double value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the float {@code array}. 
     * 
     * @param  array {@code float} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(float[], float, int)
     */
    public static int indexOf(float[] array, float value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the int {@code array}. 
     * 
     * @param  array {@code int} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(int[], int, int)
     */
    public static int indexOf(int[] array, int value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the long {@code array}. 
     * 
     * @param  array {@code long} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(long[], long, int)
     */
    public static int indexOf(long[] array, long value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the short {@code array}. 
     * 
     * @param  array {@code short} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(short[], short, int)
     */
    public static int indexOf(short[] array, short value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the first time in the object {@code array}. 
     * 
     * @param  array {@code object} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #indexOf(Object[], Object, int)
     */
    public static int indexOf(Object[] array, Object value) {
        return indexOf(array, value, 0);
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the boolean {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code boolean} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(boolean[] array, boolean value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the byte {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code byte} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(byte[] array, byte value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the char {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code char} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(char[] array, char value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the double {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code double} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(double[] array, double value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the float {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code float} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(float[] array, float value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the int {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code int} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(int[] array, int value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the long {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code long} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(long[] array, long value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the short {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code short} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(short[] array, short value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the first time in the object {@code array} begin with {@code fromIndex}. 
     * 
     * @param  array 	 {@code object} array for query.
     * @param  value 	 queries element value.
     * @param  fromIndex the index of the first element (inclusive) to queries.
     * 
     * @return the index of the specified element appeared for the first time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code fromIndex} is negative of larger than {@code array.length}.
     */
    public static int indexOf(Object[] array, Object value, int fromIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (fromIndex < 0 || fromIndex >= array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "fromfIndex: " + fromIndex + ", array length: " + array.length);
        }

        for (int i = fromIndex; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    
    //---- lastIndexOf methods -------------------------------------------------
    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the boolean {@code array}. 
     * 
     * @param  array {@code boolean} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(boolean[], boolean, int)
     */
    public static int lastIndexOf(boolean[] array, boolean value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the byte {@code array}. 
     * 
     * @param  array {@code byte} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(byte[], byte, int)
     */
    public static int lastIndexOf(byte[] array, byte value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the char {@code array}. 
     * 
     * @param  array {@code char} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(char[], char, int)
     */
    public static int lastIndexOf(char[] array, char value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the double {@code array}. 
     * 
     * @param  array {@code double} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(double[], double, int)
     */
    public static int lastIndexOf(double[] array, double value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the float {@code array}. 
     * 
     * @param  array {@code float} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(float[], float, int)
     */
    public static int lastIndexOf(float[] array, float value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the int {@code array}. 
     * 
     * @param  array {@code int} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(int[], int, int)
     */
    public static int lastIndexOf(int[] array, int value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the long {@code array}. 
     * 
     * @param  array {@code long} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(long[], long, int)
     */
    public static int lastIndexOf(long[] array, long value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the short {@code array}. 
     * 
     * @param  array {@code short} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(short[], short, int)
     */
    public static int lastIndexOf(short[] array, short value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} 
     * appeared for the last time in the object {@code array}. 
     * 
     * @param  array {@code object} array for query.
     * @param  value queries element value.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException if {@code array} is null.
     * 
     * @see	   #lastIndexOf(Object[], Object, int)
     */
    public static int lastIndexOf(Object[] array, Object value) {
        return lastIndexOf(array, value, array.length);
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the boolean {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code boolean} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(boolean[] array, boolean value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the byte {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code byte} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(byte[] array, byte value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the char {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code char} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(char[] array, char value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the double {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code double} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(double[] array, double value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the float {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code float} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(float[] array, float value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the int {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code int} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(int[] array, int value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the long {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code long} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(long[] array, long value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the short {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code short} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(short[] array, short value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value == array[i]) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    /**
     * Queries the index of the specified element {@code value} appeared for 
     * the last time in the object {@code array} ends with {@code endIndex}. 
     * 
     * @param  array 	 {@code object} array for query.
     * @param  value 	 queries element value.
     * @param  endIndex the index of the last element (exclusive) to queries.
     * 
     * @return the index of the specified element appeared for the last time;
     * 		   return {@code -1} if not found the element in the specified array.
     * 
     * @throws NullPointerException 
     * 		   if {@code array} is null.
     * @throws ArrayIndexOutOfBoundsException 
     * 		   if {@code endIndex} is negative of larger than {@code array.length}.
     */
    public static int lastIndexOf(Object[] array, Object value, int endIndex) {
    	if (array == null) {
    		throw new NullPointerException("array");
    	}
        if (endIndex < 0 || endIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(
            	  "endIndex: " + endIndex + ", array length: " + array.length);
        }

        for (int i = endIndex - 1; i >= 0; i--) {
            if (value.equals(array[i])) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }
    
}
