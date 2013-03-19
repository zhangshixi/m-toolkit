package com.mtoolkit.codec.support;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.mtoolkit.codec.Codec;
import com.mtoolkit.codec.DecodeException;
import com.mtoolkit.codec.EncodeException;
import com.mtoolkit.util.CharsetUtil;

/**
 * DES encode and decode algorithm implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 21/10/2011
 * @since 	JDK1.5
 */
public class DesCodec implements Codec {

	private String  _key;
	private Charset _charset = DEFAULT_CHARSET;
	
	/** minimum DES key length */
	public static final int		MIN_KEY_LENGTH	= 8;
	/** DES algorithm name */
	public static final String  ALGORITHM_NAME  = "DES";
	/** default DES key encoding */
	public static final Charset DEFAULT_CHARSET = CharsetUtil.UTF_8;
	
	/**
	 * Creates a DES codec, it will generate a new random key. 
	 */
	public DesCodec() {
		try {
			_key = newDesKey();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * Creates a DES codec with the specified key.
	 * 
	 * @param key security key.
	 */
	public DesCodec(String key) {
		if (key == null) {
			throw new NullPointerException("key");
		}
		if (key.length() < MIN_KEY_LENGTH) {
			throw new IllegalArgumentException(
				  "given key length:" + key.length() + 
				  ", but allowed min key length:" + MIN_KEY_LENGTH);
		}
		_key = key;
	}
	
	/**
	 * Gets the security key.
	 * 
	 * @return key.
	 */
	public String getKey() {
		return _key;
	}
	
	/**
	 * Gets key charset encoding.
	 * If not set this value, will return {@link #DEFAULT_CHARSET}.
	 * 
	 * @return key charset encoding.
	 */
	public Charset getCharset() {
		return _charset;
	}
	
	/**
	 * Sets key charset encoding.
	 * 
	 * @param  charset key charset encoding.
	 * 
	 * @throws NullPointerException if {@code charset} is null.
	 */
	public void setCharset(Charset charset) {
		if (charset == null) {
			throw new NullPointerException("charset");
		}
		_charset = charset;
	}
	
	/**
	 * Encodes binary data using the DES algorithm.
	 * 
	 * @param  datas source binary data.
	 * 
	 * @throws EncodeException if an error occurs while using DES encoding.
	 */
	@Override
	public byte[] encode(byte[] datas) throws EncodeException {
		if (datas == null || datas.length == 0) {
			return datas;
		}
		
		try {
			Key key = toKey(_key.getBytes(_charset));
			Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			return cipher.doFinal(datas);
		} catch (InvalidKeyException e) {
			throw new EncodeException(e.getMessage(), e);
		} catch (InvalidKeySpecException e) {
			throw new EncodeException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			throw new EncodeException(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			throw new EncodeException(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			throw new EncodeException(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new EncodeException(e.getMessage(), e);
		}
	}

	/**
	 * Decodes a base64 binary data to source binary data.
	 * 
	 * @param  datas encoded DES binary data.
	 * 
	 * @throws EncodeException if an error occurs while using DES decoding.
	 */
	@Override
	public byte[] decode(byte[] datas) throws DecodeException {
		if (datas == null || datas.length == 0) {
			return datas;
		}
		
		try {
			Key key = toKey(_key.getBytes(_charset));
			Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			return cipher.doFinal(datas);
		} catch (InvalidKeyException e) {
			throw new DecodeException(e.getMessage(), e);
		} catch (InvalidKeySpecException e) {
			throw new DecodeException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			throw new DecodeException(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			throw new DecodeException(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			throw new DecodeException(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new DecodeException(e.getMessage(), e);
		}
	}

	// ---- private methods ------------------------------------------
	/**
	 * Generates a new DES key.
	 */
	private String newDesKey() throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM_NAME);
		SecureRandom random = new SecureRandom();
		generator.init(random);
		SecretKey newKey = generator.generateKey();
		
		return new String(newKey.getEncoded(), _charset);
	}
	
	/**
	 * Transform a binary array to a DES key.
	 */
	private Key toKey(byte[] key) throws InvalidKeyException, 
			NoSuchAlgorithmException, InvalidKeySpecException {
		DESKeySpec keySpec = new DESKeySpec(key);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM_NAME);
		SecretKey newKey = factory.generateSecret(keySpec);
		
		/*
		 * When use other encode/decode algorithm, such as AES/Blowfish etc,
		 * use flowing codes to replace the above three line codes.
		 */
		// SecretKey newKey = new SecretKeySpec(key, ALGORITHM_NAME);
		
		return newKey;
	}

}
