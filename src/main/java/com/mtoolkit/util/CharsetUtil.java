package com.mtoolkit.util;

import java.nio.charset.Charset;

/**
 * A utility class that provides various common operations and constants 
 * related with {@link java.nio.charset.Charset} and its relevant classes.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 26/08/2011
 * @since 	JDK1.5
 * 
 * @see 	java.nio.charset.Charset
 */
public class CharsetUtil {
	
	// ---- public static constants -------------------------------------------
	/**
	 * Eight-bit UCS Transformation Format.
	 */
	public static final Charset UTF_8 = Charset.forName("UTF-8");

	/**
	 * Sixteen-bit UCS Transformation Format whose bytes 
	 * order is identified by an optional byte-order mark.
	 */
	public static final Charset UTF_16 = Charset.forName("UTF-16");

	/**
	 * Sixteen-bit UCS Transformation Format whose byte order is big-endian.
	 */
	public static final Charset UTF_16BE = Charset.forName("UTF-16BE");

	/**
	 * Sixteen-bit UCS Transformation Format whose byte order is little-endian.
	 */
	public static final Charset UTF_16LE = Charset.forName("UTF-16LE");

	/**
	 * Seven-bit ASCII, as known as <tt>ISO646-US</tt> or 
	 * the Basic Latin block of the Unicode character set.
	 */
	public static final Charset US_ASCII = Charset.forName("US-ASCII");

	/**
	 * ISO Latin Alphabet No. 1, as known as <tt>ISO-LATIN-1</tt>.
	 */
	public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	
	/**
	 * Private constructor, not permit to construct the instance.
	 */
	private CharsetUtil() {
	}
	
	// ---- public methods ----------------------------------------------------
	/**
	 * Counts char number of the specified string value.
	 * 
	 * @param  value target string value.
	 * @return ASCII char count.
	 * 
	 * @throws NullPointerException if {@code value} is null.
	 */
	public static int asciiCount(String value) {
		if (value == null) {
			throw new NullPointerException("value");
		}
		
		int count = 0;
		for (int i = 0; i < value.length(); i++) {
			char point = value.charAt(i);
			// char in ASCII table[0, 127] count 1, else count 2. 
			count += (point >= 0 && point <= 127) ? 1 : 2;
		}
		
		return count;
	}

	/**
	 * Transforms a halfwidth string value to fullwidth. 
	 * 
	 * @param  value target value.
	 * 
	 * @return fullwidth string value.
	 */
	public static String toHalfwidth(String value) {
		if (value == null || value.isEmpty()) {
			return value;
		}
		
		char[] array = value.toCharArray();
		for (int i = 0; i < array.length; i++) {
			char point = array[i];
			if (point == ' ') {
				point = '\u3000';
            } else if (point < '\177') {
            	point = (char) (point + 65248);
			}
			array[i] = point;
		}
		
		return String.valueOf(array);
	}
	
	/**
	 * Transforms a fullwidth string value to halfwidth. 
	 * 
	 * @param  value target value.
	 * 
	 * @return halfwidth string value.
	 */
	public static String toFullwidth(String value) {
		if (value == null || value.isEmpty()) {
			return value;
		}
		
		char[] array = value.toCharArray();
		for (int i = 0; i < array.length; i++) {
			char point = array[i];
			if (point == '\u3000') {
                point = ' ';
            } else if (point > '\uFF00' && point < '\uFF5F') {
            	point = (char) (point - 65248);
			}
			array[i] = point;
		}
		
		return String.valueOf(array);
	}
	
}
