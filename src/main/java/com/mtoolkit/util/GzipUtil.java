package com.mtoolkit.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * A utility class that can used <code>gzip</code> algorithm 
 * to compress or uncompress the given byte array.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 15/9/2011
 * @since 	JDK1.5
 * 
 * @see		java.util.zip.GZIPInputStream
 * @see		java.util.zip.GZIPOutputStream
 */
public class GzipUtil {
	
	/**
     * Private constructor, not permit to construct the instance.
     */
    private GzipUtil() {
    }

	/**
	 * Compresses the given byte array with <code>gzip<code> algorithm.
	 * 
	 * @param  byteDatas compress byte array.
	 * 	
	 * @return compressed byte array.
	 * 
	 * @throws IOException if an I/O error occurs whiling compressing.
	 */
	public static byte[] gzip(byte[] byteDatas) throws IOException {
		final int length = byteDatas.length;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream(length);
		GZIPOutputStream output = null;
		try {
			output = new GZIPOutputStream(out, length);
			output.write(byteDatas);
		} finally {
			if (output != null) {
				output.close();
			}
		}
		
		return out.toByteArray();
	}

	/**
	 * Uncompresses the given byte array with <code>gzip<code> algorithm.
	 * 
	 * @param  byteDatas uncompress byte array.
	 * 	
	 * @return uncompressed byte array.
	 * 
	 * @throws IOException if an I/O error occurs whiling uncompressing.
	 */
	public static byte[] unGzip(byte[] byteDatas) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		int r = -1;
		byte[] buf = new byte[1024];
		GZIPInputStream input = null;
		
		try {
			input = new GZIPInputStream(new ByteArrayInputStream(byteDatas));
			while ((r = input.read(buf)) != -1) {
				output.write(buf, 0, r);
			}
		} finally {
			if (input != null) {
				input.close();
			}
		}
		
		return output.toByteArray();
	}

}
