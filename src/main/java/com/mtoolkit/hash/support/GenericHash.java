package com.mtoolkit.hash.support;

import com.mtoolkit.hash.Hash;

/**
 * Generic hash function implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 2/2/2012
 * @since 	JDK1.5
 */
public class GenericHash extends AbstractHash {

	/** singleton instance */
	private static final Hash INSTANCE = new GenericHash();
	
	/**
	 * Private constructor.
	 */
	private GenericHash() {
	}
	
	/**
	 * Returns the singleton generic hash instance.
	 * 
	 * @return the singleton generic hash instance.
	 */
	public static Hash getInstance() {
		return GenericHash.INSTANCE;
	}
	
	@Override
	public int doHash(byte[] data, int from, int to, int seed) {
		int length = to - from;
		int hash = seed ^ length;
		int multiplier = 1;
		
		for (int i = 0; i < data.length; i++) {
			hash += data[i] * multiplier;
			
			int shifted = multiplier << 5;
			multiplier = shifted - multiplier;
		}
		
		return hash;
	}

}

