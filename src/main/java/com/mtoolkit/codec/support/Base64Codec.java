package com.mtoolkit.codec.support;

import org.apache.commons.codec.binary.Base64;

import com.mtoolkit.codec.Codec;
import com.mtoolkit.codec.DecodeException;
import com.mtoolkit.codec.EncodeException;

/**
 * Base64 encode and decode algorithm implementation.
 * 
 * It must depend on <code>apache commons-codec</code> utility packet,
 * you can get it from <a href="">http://commons.apache.org/codec/</a>.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 21/10/2011
 * @since 	JDK1.5
 */
public class Base64Codec implements Codec {

	/**
	 * Encodes binary data using the base64 algorithm.
	 * 
	 * @param  datas source binary data.
	 * 
	 * @throws EncodeException never occurs.
	 */
	@Override
	public byte[] encode(byte[] datas) throws EncodeException {
		if (datas == null || datas.length == 0) {
			return datas;
		}
		
		return Base64.encodeBase64(datas);
	}

	/**
	 * Decodes a base64 binary data to source binary data.
	 * 
	 * @param  datas encoded base64 binary data.
	 * 
	 * @throws EncodeException never occurs.
	 */
	@Override
	public byte[] decode(byte[] datas) throws DecodeException {
		if (datas == null || datas.length == 0) {
			return datas;
		}
		
		return Base64.decodeBase64(datas);
	}

}
