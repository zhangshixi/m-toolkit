package com.mtoolkit.codec.support;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mtoolkit.codec.EncodeException;

/**
 * SHA - One way encryption algorithm implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 06/04/2012
 * @since 	JDK1.5
 */
public class ShaCodec extends OneWayCodec {

	/** SHA algorithm name */
	public static final String ALGORITHM_NAME = "SHA";
	
	/**
	 * Encodes binary data using the SHA encryption algorithm.
	 * 
	 * @param  datas source binary data.
	 * 
	 * @throws EncodeException if an error occurs while using SHA encoding.
	 */
	@Override
	public byte[] encode(byte[] datas) throws EncodeException {
		if (datas == null || datas.length == 0) {
			return datas;
		}
		
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance(ALGORITHM_NAME);
		} catch (NoSuchAlgorithmException e) {
			throw new EncodeException("Not support SHA encryption algorithm.", e);
		}  
	    sha.update(datas);  
	  
	    return sha.digest();  
	}

}
