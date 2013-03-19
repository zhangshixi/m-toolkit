package com.mtoolkit.codec.support;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mtoolkit.codec.EncodeException;

/**
 * MD5 - One way encryption algorithm implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 06/04/2012
 * @since 	JDK1.5
 */
public class Md5Codec extends OneWayCodec {

	/** MD5 algorithm name */
	public static final String ALGORITHM_NAME = "MD5";
	
	/**
	 * Encodes binary data using the MD5 encryption algorithm.
	 * 
	 * @param  datas source binary data.
	 * 
	 * @throws EncodeException if an error occurs while using MD5 encoding.
	 */
	@Override
	public byte[] encode(byte[] datas) throws EncodeException {
		if (datas == null || datas.length == 0) {
			return datas;
		}
		
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance(ALGORITHM_NAME);
		} catch (NoSuchAlgorithmException e) {
			throw new EncodeException("Not support MD5 encryption algorithm.", e);
		}  
	    md5.update(datas);  
	  
	    return md5.digest();  
	}

}
