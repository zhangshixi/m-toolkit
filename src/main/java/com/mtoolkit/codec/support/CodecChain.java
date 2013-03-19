package com.mtoolkit.codec.support;

import com.mtoolkit.codec.Codec;
import com.mtoolkit.codec.DecodeException;
import com.mtoolkit.codec.EncodeException;

/**
 * An ${code CodecChain} assists with creating a Codec instance
 * which go through a series of {@link Codec codecs}.
 * 
 * <pre>
 *   Codec[] codecs = ...;
 *   CodecChain chain = new CodecChain(codecs);
 * </pre>
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 06/04/2012
 * @since 	JDK1.5
 */
public class CodecChain implements Codec {
	
	private Codec[] _codecs;
	
	/**
	 * Creates a CodecChain instance with specified codec array.
	 * 
	 * @param  codecs codec instance array.
	 * 
	 * @throws NullPointerException if {@code codecs} is null.
	 */
	public CodecChain(Codec[] codecs) {
		if (codecs == null) {
			throw new NullPointerException("codecs");
		}
		_codecs = codecs;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @param  datas {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 * 
	 * @throws EncodeException {@inheritDoc}
	 */
	@Override
	public byte[] encode(byte[] datas) throws EncodeException {
		byte[] encodedBytes = datas;
		for (int i = 0; i < _codecs.length; i++) {
			encodedBytes = _codecs[i].encode(encodedBytes);
		}
		
		return encodedBytes;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param  datas {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 * 
	 * @throws DecodeException {@inheritDoc}
	 */
	@Override
	public byte[] decode(byte[] datas) throws DecodeException {
		byte[] decodedBytes = datas;
		for (int i = 0; i < _codecs.length; i++) {
			decodedBytes = _codecs[i].decode(decodedBytes);
		}
		
		return decodedBytes;
	}

}
