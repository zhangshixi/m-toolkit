package com.mtoolkit.hash.support;

import com.mtoolkit.hash.Hash;

/**
 * Murmur hash function implementation.
 * 
 * This is a very fast, non-cryptographic hash suitable for general 
 * hash-based lookup.  
 * See http://murmurhash.googlepages.com/ for more details.
 * 
 * <p>The C version of MurmurHash 2.0 found at that site was ported
 * to Java by Andrzej Bialecki (ab at getopt org).</p>
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 2/1/2012
 * @since 	JDK1.5
 */
public class MurmurHash extends AbstractHash {

	/** singleton instance */
	private static final Hash INSTANCE = new MurmurHash();

	/**
	 * Private constructor.
	 */
	private MurmurHash() {
	}

	/**
	 * Returns the singleton Murmur hash instance.
	 * 
	 * @return the singleton Murmur hash instance.
	 */
	public static Hash getInstance() {
		return MurmurHash.INSTANCE;
	}

	@Override
	public int doHash(byte[] data, int from, int to, int seed) {
		int m = 0x5bd1e995;
		int r = 24;
		int length = to - from;

		int h = seed ^ length;

		int len_4 = length >> 2;

		for (int i = 0; i < len_4; i++) {
			int i_4 = i << 2;
			int k = data[i_4 + 3];
			k = k << 8;
			k = k | (data[i_4 + 2] & 0xff);
			k = k << 8;
			k = k | (data[i_4 + 1] & 0xff);
			k = k << 8;
			k = k | (data[i_4 + 0] & 0xff);
			k *= m;
			k ^= k >>> r;
			k *= m;
			h *= m;
			h ^= k;
		}

		// avoid calculating modulo
		int len_m = len_4 << 2;
		int left = length - len_m;

		if (left != 0) {
			if (left >= 3) {
				h ^= (int) data[length - 3] << 16;
			}
			if (left >= 2) {
				h ^= (int) data[length - 2] << 8;
			}
			if (left >= 1) {
				h ^= (int) data[length - 1];
			}

			h *= m;
		}

		h ^= h >>> 13;
		h *= m;
		h ^= h >>> 15;

		return h;
	}

}
