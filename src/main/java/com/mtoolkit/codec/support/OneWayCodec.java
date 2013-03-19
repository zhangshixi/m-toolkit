package com.mtoolkit.codec.support;

import com.mtoolkit.codec.Codec;
import com.mtoolkit.codec.DecodeException;

/**
 * One way encode abstract class for security purpose.  
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 06/04/2012
 * @since 	JDK1.5
 */
public abstract class OneWayCodec implements Codec {

	/**
	 * Decode is not supported in One Way Encryption Algorithm.
     * It will always throws an DecodeException.
     * 
     * @param datas encoded binary data.
     * 
     * @return source binary data.
     * 
     * @throws DecodeException if occurs an error while decoding.
     */
	@Override
	public byte[] decode(byte[] datas) throws DecodeException {
		throw new DecodeException("Not suppported in One Way Encryption Algorithm.");
	}

}
