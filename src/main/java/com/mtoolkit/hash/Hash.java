package com.mtoolkit.hash;

import java.nio.charset.Charset;

/**
 * Hash function interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 2/1/2012
 * @since 	JDK1.5
 */
public interface Hash {
	
	/**
	 * Calculates a hash using the specified string argument.
	 * The default character is UTF-8 and default seed is -1.
	 * 
	 * @param  data the specified string argument.
	 * 
	 * @return hash value.
	 * 
	 * @throws NullPointerException if the {@code data} is null.
	 */
	public int hash(String data);
	
	/**
	 * Calculates a hash using the specified string argument and character.
	 * The default seed of -1.
	 * 
	 * @param  data    the specified string argument.
	 * @param  charset the specified character argument.
	 * 
	 * @return hash value.
	 * 
	 * @throws NullPointerException if the {@code data} or {@code charset} is null.
	 */
	public int hash(String data, Charset charset);
	
	/**
	 * Calculates a hash using the specified byte array argument.
	 * The default seed of -1.
	 * 
	 * @param  data the specified string argument.
	 * 
	 * @return hash value.
	 * 
	 * @throws NullPointerException if the {@code data} is null.
	 */
	public int hash(byte[] data);
	
	/**
	 * Calculates a hash using the specified byte array argument and provides seed.
	 * 
	 * @param  data the specified string argument.
	 * @param  seed the specified seed value.
	 * 
	 * @return hash value.
	 * 
	 * @throws NullPointerException if the {@code data} is null.
	 */
	public int hash(byte[] data, int seed);
	
	/**
	 * Calculates a hash using the specified byte array argument 
	 * and provides seed from 0 to {@code length}.
	 * 
	 * @param  data the specified string argument.
	 * @param  seed the specified seed value.
	 * 
	 * @return hash value.
	 * 
	 * @throws NullPointerException if the {@code data} is null.
	 * @throws IndexOutOfBoundsException if {@code length} is large than the length of data.
	 */
	public int hash(byte[] data, int length, int seed);
	
	/**
	 * Calculates a hash using the specified byte array argument 
	 * and provides seed from {code from} to {@code to}.
	 * 
	 * @param  data the specified string argument.
	 * @param  from from index.
	 * @param  to 	to index.
	 * @param  seed the specified seed value.
	 * 
	 * @return hash value.
	 * 
	 * @throws NullPointerException 
	 * 		   if the {@code data} is null.
	 * @throws IndexOutOfBoundsException 
	 * 		   if {@code from} or {@code to} is negative or is equal or greater than data.length.
	 * @throws IllegalArgumentException 
	 * 		   if {@code from} is large than {@code to}.
	 */
	public int hash(byte[] data, int from, int to, int seed);
	
}