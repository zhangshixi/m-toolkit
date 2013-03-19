package com.mtoolkit.util;

/**
 * A utility class that provides common operations.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 10/9/2011
 * @since 	JDK1.5
 */
public class CommonUtil {
	
	/**
	 * The default load factor of map.
	 */
	public static final float DEFAULT_MAP_LOAD_FACTOR = 0.75F;
	
	/**
	 * Private instructor, not permit to create this instance.
	 */
	private CommonUtil() {
	}

	/**
	 * Gets the minimal capacity of map with the specified size and 
	 * {@linkplain #DEFAULT_MAP_LOAD_FACTOR default load factor}.
	 *  
	 * @param  size the number of data that will put into the map. 
	 * 
	 * @return the minimal capacity of the map.
	 */
	public static int getMapCapacity(int size) {
		return getMapCapacity(size, DEFAULT_MAP_LOAD_FACTOR);
	}
	
	/**
	 * Gets the minimal capacity of map with the specified size and load factor.
	 * 
	 * @param  size		  the number of data that will put into the map. 
	 * @param  loadFactor the load factor of the map. 
	 * 
	 * @return the minimal capacity of the map.
	 */
	public static int getMapCapacity(int size, float loadFactor) {
		return (int) ((size / loadFactor) + 1);
	}
	
	/**
	 * Gets max range number with specified value and range. Examples:
	 * <pre>
	 * 		getRangeNumber(2, 5) = 5;
	 * 		getRangeNumber(5, 5) = 5;
	 * 		getRangeNumber(7, 5) = 10;
	 * </pre>
	 * 
	 * @param  value current value.
	 * @param  range range value.
	 * 
	 * @return max range number.
	 * 
	 * @throws IllegalArgumentException if {@code value} or {@code range} is non-positive.
	 */
	public static int getRangeNumber(int value, int range) {
		if (value <= 0) {
			throw new IllegalArgumentException("Value must be positive: " + value);
		}
		if (range <= 0) {
			throw new IllegalArgumentException("Range must be positive: " + range);
		}
		
		int quotient  = value / range;
		int remainder = value % range;
		int multiple  = (quotient == 0 && remainder != 0) ? 1 : remainder + 1;
		
		return multiple * range;
	}

}
