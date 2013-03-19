package com.mtoolkit.codec;

/**
 * Encode and decode interface for security purpose.  
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 21/10/2011
 * @since 	JDK1.5
 */
public interface Codec {

    /**
     * Encodes the specified binary byte data.
     * 
     * @param  datas source binary data.
     * 
     * @return encoded binary data.
     * 
     * @throws EncodeException if an error occurs while encoding.
     */
    public byte[] encode(byte[] datas) throws EncodeException;

    /**
     * Decoded the specified encoded binary data.
     * 
     * @param datas encoded binary data.
     * 
     * @return source binary data.
     * 
     * @throws DecodeException if occurs an error while decoding.
     */
    public byte[] decode(byte[] datas) throws DecodeException;
    
}
