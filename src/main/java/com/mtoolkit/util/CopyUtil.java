package com.mtoolkit.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * <p> A utility class that provides transform an input to an output.
 * The <code>input</code> may be one of the <code>byte array</code>, 
 * <code>input stream</code>, <code>reader</code>, <code>string</code> or <code>file</code>;
 * and the <code>output</code> may be one of the <code>output stream</code>, 
 * <code>writer</code>, <code>file</code>, <code>byte array</code> or <code>string</code>.
 * 
 * <p> You can use it as following:
 * <pre>
 * 		+--------------+--------------+
 * 		+     FROM     +      TO      +
 *		+--------------+--------------+
 *		+    byte[]    + OutputStream +
 * 		+--------------+--------------+
 * 		+    byte[]    +    Writer    +
 * 		+--------------+--------------+
 * 		+ InputStream  + OutputStream +
 * 		+--------------+--------------+
 * 		+ InputStream  +    Writer    +
 * 		+--------------+--------------+
 * 		+    Reader    +    Writer    +
 * 		+--------------+--------------+
 * 		+    Reader    + OutputStream +
 * 		+--------------+--------------+
 * 		+    String    + OutputStream +
 * 		+--------------+--------------+
 * 		+    String    +    Writer    +
 * 		+--------------+--------------+
 * 		+     File     +     File     +
 * 		+--------------+--------------+
 * 		+    byte[]    +     File     +
 * 		+--------------+--------------+
 * 		+     File     +    byte[]    +
 * 		+--------------+--------------+
 * 		+ InputStream  +    byte[]    +
 * 		+--------------+--------------+
 * 		+    Reader    +    String    +
 * 		+--------------+--------------+
 * </pre>
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 26/8/2011
 * @since 	JDK1.5
 */
public class CopyUtil {

	/** default buffer size for transform input to output */
    private static final int DEFAULT_BUFFER_SIZE = 2048;

    /**
     * Private constructor, not permit to construct the instance.
     */
    private CopyUtil() {
    }

    /**
     * Copies byte array to output stream.
     * 
     * @param 	input 		byte array source.
     * @param 	output 		the specified output stream that copy in.
     * 
     * @throws 	IOException if an I/O error occurs while transforming.
     */
    public static void copy(byte[] input, OutputStream output) throws IOException {
        output.write(input);
    }

    /**
     * Copies byte array to output stream with specified charset format.
     * 
     * @param 	input		byte array source.	
     * @param 	output		the specified output stream the copy in.
     * @param 	charset		the specified charset format.
     * 
     * @throws 	IOException if an I/O error occurs while transforming.
     */
    public static void copy(byte[] input, OutputStream output, String charset) throws IOException {
        copy(new ByteArrayInputStream(input), output, charset);
    }

    /**
     * Copies byte array to writer.
     * 
     * @param 	input 		byte array source.
     * @param 	output 		the specified writer that copy in.
     * 
     * @throws 	IOException if an I/O error occurs while transforming.
     */
    public static void copy(byte[] input, Writer output) throws IOException {
        copy(new ByteArrayInputStream(input), output);
    }

    /**
     * Copies byte array to writer with the specified charset format.
     * 
     * @param 	input		byte array source.
     * @param 	output		the specified writer for copy in.
     * @param 	charset		the specified charset format.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static void copy(byte[] input, Writer output, String charset) throws IOException {
        copy(new ByteArrayInputStream(input), output, charset);
    }

    /**
     * Copies input stream to output stream.
     * 
     * @param 	input 		the input stream source.
     * @param 	output		the specified output stream for copy in.
     * 
     * @return	the total number of bytes copy from input stream to output stream.
     * 
     * @throws 	IOException if an I/O error occurs  while transforming.
     */
    public static int copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

        int count = 0;
        int index = 0;
        while ((index = input.read(buffer)) != -1) {
            output.write(buffer, 0, index);
            count += index;
        }

        return count;
    }

    /**
     * Copies input stream to output stream with specified charset format. 
     * 
     * @param 	input		the input stream source.
     * @param 	output		the specified output stream for copy in. 
     * @param 	charset		the specified charset format.
     * 
     * @return 	the total number of chars copy from input stream to output stream.
     * 
     * @throws 	IOException if an I/O occurs while transforming.
     */
    public static int copy(InputStream input, OutputStream output, String charset) throws IOException {
        return copy(new InputStreamReader(input, charset), output);
    }

    /**
     * Copies input stream to writer.
     * 
     * @param 	input		the input stream source.
     * @param 	output		the specified writer for copy in.
     * 
     * @return	the total number of chars copy from input stream to writer.
     * 
     * @throws 	IOException if an I/O error occurs while transforming.
     */
    public static int copy(InputStream input, Writer output) throws IOException {
        return copy(new InputStreamReader(input), output);
    }

    /**
     * Copies input stream to writer with specified charset format.
     * 
     * @param 	input		the input stream source.
     * @param 	output		the specified writer for copy in.
     * @param 	charset		the specified charset format.
     * 
     * @return	the total number of chars copy from input stream to writer.
     * 
     * @throws 	IOException if an I/O error occurs while transforming. 
     */
    public static int copy(InputStream input, Writer output, String charset) throws IOException {
        return copy(new InputStreamReader(input, charset), output);
    }

    /**
     * Copies reader to writer.
     * 
     * @param 	input		the reader source.		
     * @param 	output		the specified writer for copy in.
     * 
     * @return	the total number of characters copy from reader to writer.
     * 
     * @throws 	IOException if an I/O error occurs while transforming.
     */
    public static int copy(Reader input, Writer output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];

        int count = 0;
        int index = 0;
        while ((index = input.read(buffer)) != -1) {
            output.write(buffer, 0, index);
            count += index;
        }

        return count;
    }

    /**
     * Copies reader to output stream.
     * 
     * @param 	input		the reader source.
     * @param 	output		the specified output stream for copy in.
     * 
     * @return	the total number of characters copy from reader to output.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static int copy(Reader input, OutputStream output) throws IOException {
        return copy(input, new OutputStreamWriter(output));
    }

    /**
     * Copies reader to output stream with specified charset format.
     * 
     * @param 	input		the reader source.
     * @param 	output		the specified output stream for copy in.
     * @param 	charset		the specified charset format.
     * 
     * @return	the total number of characters copy from reader to output stream.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static int copy(Reader input, OutputStream output, String charset) throws IOException {
        return copy(input, new OutputStreamWriter(output, charset));
    }

    /**
     * Copies a string to output stream. 
     * 
     * @param 	input		the string source.
     * @param 	output		the specified output stream for copy in.
     * 
     * @return	the total number of characters copy from the string to output stream.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static int copy(String input, OutputStream output) throws IOException {
        return copy(new StringReader(input), output);
    }

    /**
     * Copies a string to output stream with specified charset format.
     * 
     * @param 	input		the string source.
     * @param 	output		the specified output stream for copy in.
     * @param	charset		the specified charset format.
     * 
     * @return	the total number of characters copy from the string to output stream.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static int copy(String input, OutputStream output, String charset) throws IOException {
        return copy(new StringReader(input), new OutputStreamWriter(output, charset));
    }

    /**
     * Copies a string to writer.
     * 
     * @param 	input		the string source.
     * @param 	output		the specified writer for copy in.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static void copy(String input, Writer output) throws IOException {
        output.write(input);
    }

    /**
     * Copies a file to another file.
     * 
     * @param 	input		the file source.
     * @param 	output		the specified file for copy in.
     * 
     * @return	the total number of bytes copy from the file to another.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static int copy(File input, File output) throws IOException {
        return copy(new FileInputStream(input), new FileOutputStream(output));
    }

    /**
     * Copies a byte array to file.
     * 
     * @param 	input		the byte array source.
     * @param 	output		the specified file for copy in.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static void copy(byte[] input, File output) throws IOException {
        copy(input, new FileOutputStream(output));
    }

    /**
     * Copies a file content to byte array.
     * 
     * @param 	input		the file source.
     * 
     * @return	the byte array of the specified file contents.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static byte[] copyToByteArray(File input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(new FileInputStream(input), output);

        return output.toByteArray();
    }

    /**
     * Copies a input stream to byte array.
     * 
     * @param 	input		the input stream source.
     * 
     * @return	the byte array read from the specified input stream.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static byte[] copyToByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);

        return output.toByteArray();
    }

    /**
     * Copies a reader to string.
     * 
     * @param 	input		the reader source.
     * 
     * @return	the string read from the specified reader stream.
     * 
     * @throws 	IOException	if an I/O error occurs while transforming.
     */
    public static String copyToString(Reader input) throws IOException {
        StringWriter output = new StringWriter();
        copy(input, output);

        return output.toString();
    }
}
