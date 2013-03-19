package com.mtoolkit.hash.support;

import com.mtoolkit.hash.Hash;

/**
 * Time33 hash function implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 2/1/2012
 * @since 	JDK1.5
 */
public class Time33Hash extends AbstractHash {

	/** singleton instance */
	private static final Hash INSTANCE = new Time33Hash();
	
	/**
	 * Private constructor.
	 */
	private Time33Hash() {
	}
	
	/**
	 * Returns the singleton Time33 hash instance.
	 * 
	 * @return the singleton Time33 hash instance.
	 */
	public static Hash getInstance() {
		return Time33Hash.INSTANCE;
	}
	
	@Override
	public int doHash(byte[] data, int from, int to, int seed) {
		int length = to - from;
		int hash = seed ^ length;
		
		for (int i = 0; i < data.length; i++) {
			hash += hash << 5 * data[i];
		}
		
		return hash;
	}

}