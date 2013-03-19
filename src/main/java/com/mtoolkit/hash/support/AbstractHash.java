package com.mtoolkit.hash.support;

import java.nio.charset.Charset;

import com.mtoolkit.hash.Hash;
import com.mtoolkit.util.CharsetUtil;

/**
 * Abstract hash implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 2/1/2012
 * @since 	JDK1.5
 */
public abstract class AbstractHash implements Hash {
	
	/** default seed value */
	public static final int DEFAULT_SEED = 1;

	/**
	 * {@inheritDoc}
	 * 
	 * @param data {@inheritDoc}
	 * 
	 * @throws NullPointerException {@inheritDoc}
	 */
	@Override
	public int hash(String data) {
		return this.hash(data, CharsetUtil.UTF_8);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param  data    {@inheritDoc}
	 * @param  charset {@inheritDoc}
	 * 
	 * @throws NullPointerException {@inheritDoc}
	 */
	@Override
	public int hash(String data, Charset charset) {
		if (data == null) {
			throw new NullPointerException("data");
		}
		if (charset == null) {
			throw new NullPointerException("charset");
		}
		
		return this.hash(data.getBytes(charset));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param  data {@inheritDoc}
	 * 
	 * @throws NullPointerException {@inheritDoc}
	 */
	@Override
	public int hash(byte[] data) {
		return this.hash(data, DEFAULT_SEED);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param  data {@inheritDoc}
	 * @param  seed {@inheritDoc}
	 * 
	 * @throws NullPointerException {@inheritDoc}
	 */
	@Override
	public int hash(byte[] data, int seed) {
		return this.hash(data, data.length, seed);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param  data   {@inheritDoc}
	 * @param  length {@inheritDoc}
	 * @param  seed   {@inheritDoc}
	 * 
	 * @throws NullPointerException {@inheritDoc}
	 */
	@Override
	public int hash(byte[] data, int length, int seed) {
		return this.hash(data, 0, length, seed);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @param  data {@inheritDoc}
	 * @param  from {@inheritDoc}
	 * @param  to	{@inheritDoc}
	 * @param  seed {@inheritDoc}
	 * 
	 * @throws NullPointerException {@inheritDoc}
	 */
	@Override
	public int hash(byte[] data, int from, int to, int seed) {
		if (data == null) {
			throw new NullPointerException("data");
		}
		if (from < 0 || from > data.length) {
			throw new IndexOutOfBoundsException(
				  "from: " + from + ", data.length:" + data.length);
		}
		if (to < 0 || to > data.length) {
			throw new IndexOutOfBoundsException(
				  "to: " + to + ", data.length:" + data.length);
		}
		if (from > to) {
			throw new IllegalArgumentException("from:" + from + ", to:" + to);
		}
		
		return doHash(data, from, to, seed);
	}
	
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
	 */
	protected abstract int doHash(byte[] data, int from, int to, int seed);

}
