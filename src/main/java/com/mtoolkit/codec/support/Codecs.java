package com.mtoolkit.codec.support;

import java.nio.charset.Charset;

import com.mtoolkit.codec.Codec;
import com.mtoolkit.codec.DecodeException;
import com.mtoolkit.codec.EncodeException;
import com.mtoolkit.util.CharsetUtil;

import static com.mtoolkit.util.ConversionUtil.*;

/**
 * A Codecs is a helper class that can provides encode/decode 
 * the binary data to string value with specified character.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 06/14/2012
 * @since 	JDK1.5
 */
public class Codecs {
	
	/** default character encoding/decoding */
	public static final Charset DEFAULT_CHARSET = CharsetUtil.UTF_8;
	
	/**
     * Private constructor, not permit to construct the instance.
     */
	private Codecs(Codec codec) {
	}
	
	/**
	 * Encodes the string value use Base64 encryption algorithm with default character.
	 * 
	 * @param  source string value for encode.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} is null.
	 * 
	 * @see	   #DEFAULT_CHARSET
	 */
	public static String base64Encode(String source) throws EncodeException {
		return base64Encode(source, DEFAULT_CHARSET);
	}
	
	/**
	 * Decodes the string value use Base64 encryption algorithm with default character.
	 * 
	 * @param  source string value for decode.
	 * 
	 * @return decoded string value.
	 * 
	 * @throws DecodeException if occurs exception while decoding.
	 * @throws NullPointerException if {@code source} is null.
	 * 
	 * @see	   #DEFAULT_CHARSET
	 */
	public static String base64Decode(String source) throws DecodeException {
		return base64Decode(source, DEFAULT_CHARSET);
	}
	
	/**
	 * Encodes the string value use Base64 encryption algorithm with specified character.
	 * 
	 * @param  source string value for encode.
	 * @param  charset character.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} or {@code charset} is null.
	 */
	public static String base64Encode(String source, Charset charset) throws EncodeException {
		byte[] encodedBytes = new Base64Codec().encode(source.getBytes(charset)); 
		return new String(encodedBytes, charset);
	}
	
	/**
	 * Decodes the string value use Base64 encryption algorithm with default character.
	 * 
	 * @param  source string value for decode.
	 * @param  charset character.
	 * 
	 * @return decoded string value.
	 * 
	 * @throws DecodeException if occurs exception while decoding.
	 * @throws NullPointerException if {@code source} or {@code charset} is null.
	 */
	public static String base64Decode(String source, Charset charset) throws DecodeException {
		byte[] decodedBytes = new Base64Codec().decode(source.getBytes(charset)); 
		return new String(decodedBytes, charset);
	}
	
	/**
	 * Encodes the string value use SHA encryption algorithm with default character.
	 * 
	 * @param  source string value for encode.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} is null.
	 * 
	 * @see	   #DEFAULT_CHARSET
	 */
	public static String shaEncode(String source) throws EncodeException {
		return shaEncode(source, DEFAULT_CHARSET);
	}
	
	/**
	 * Encodes the string value use SHA encryption algorithm with specified character.
	 * 
	 * @param  source string value for encode.
	 * @param  charset character.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} or {@code charset} is null.
	 */
	public static String shaEncode(String source, Charset charset) throws EncodeException {
		byte[] encodedBytes = new ShaCodec().encode(source.getBytes(charset)); 
		return bytes2Hex(encodedBytes);
	}

	/**
	 * Encodes the string value use MD5 encryption algorithm with default character.
	 * 
	 * @param  source string value for encode.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} is null.
	 * 
	 * @see	   #DEFAULT_CHARSET
	 */
	public static String md5Encode(String source) throws EncodeException {
		return md5Encode(source, DEFAULT_CHARSET);
	}
	
	/**
	 * Encodes the string value use MD5 encryption algorithm with specified character.
	 * 
	 * @param  source string value for encode.
	 * @param  charset character.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} or {@code charset} is null.
	 */
	public static String md5Encode(String source, Charset charset) throws EncodeException {
		byte[] encodedBytes = new Md5Codec().encode(source.getBytes(charset)); 
		return bytes2Hex(encodedBytes);
	}
	
	/**
	 * Encodes the string value use SHA and Base64 encryption algorithm with default character.
	 * 
	 * @param  source string value for encode.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} is null.
	 * 
	 * @see	   #DEFAULT_CHARSET
	 */
	public static String shaBase64Encode(String source) throws EncodeException {
		return shaBase64Encode(source, DEFAULT_CHARSET);
	}
	
	/**
	 * Encodes the string value use SHA and Base64 encryption algorithm with specified character.
	 * 
	 * @param  source string value for encode.
	 * @param  charset character.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} or {@code charset} is null.
	 */
	public static String shaBase64Encode(String source, Charset charset) throws EncodeException {
		byte[] encodedBytes = new ShaCodec().encode(source.getBytes(charset)); 
		encodedBytes = new Base64Codec().encode(encodedBytes);
		
		return new String(encodedBytes, charset);
	}
	
	/**
	 * Encodes the string value use MD5 and Base64 encryption algorithm with default character.
	 * 
	 * @param  source string value for encode.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} is null.
	 * 
	 * @see	   #DEFAULT_CHARSET
	 */
	public static String md5Base64Encode(String source) throws EncodeException {
		return md5Base64Encode(source, DEFAULT_CHARSET);
	}
	
	/**
	 * Encodes the string value use MD5 and Base64 encryption algorithm with specified character.
	 * 
	 * @param  source string value for encode.
	 * @param  charset character.
	 * 
	 * @return encoded string value.
	 * 
	 * @throws EncodeException if occurs exception while encoding.
	 * @throws NullPointerException if {@code source} or {@code charset} is null.
	 */
	public static String md5Base64Encode(String source, Charset charset) throws EncodeException {
		byte[] encodedBytes = new Md5Codec().encode(source.getBytes(charset)); 
		encodedBytes = new Base64Codec().encode(encodedBytes);
		
		return new String(encodedBytes, charset);
	}
	
	public static String desEncode(String key, String source) throws EncodeException {
		return desEncode(key, source,DEFAULT_CHARSET);
	}
	
	public static String desEncode(String key, String source, Charset charset) throws EncodeException {
		byte[] encodedBytes = new DesCodec(key).encode(source.getBytes(charset));
		return bytes2Hex(encodedBytes);
	}
	
	public static String desDecode(String key, String source) throws DecodeException {
		return desDecode(key, source, DEFAULT_CHARSET);
	}
	
	public static String desDecode(String key, String source, Charset charset) throws DecodeException {
		byte[] decodedBytes = new DesCodec(key).decode(hex2Bytes(source));
		return new String(decodedBytes, charset);
	}

}
