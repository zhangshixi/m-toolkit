package com.mtoolkit.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * File resource interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 09/08/2011
 * @since	JDK1.5
 */
public interface FileResource extends Resource {

	/**
	 * Returns an input stream that reads from this open file resource.
	 * 
	 * @return an input stream that reads from this open file resource.
	 * 
	 * @throws IOException if an I/O error occurs while creating the input stream.
	 */
	public InputStream getInputStream() throws IOException;
	
}
