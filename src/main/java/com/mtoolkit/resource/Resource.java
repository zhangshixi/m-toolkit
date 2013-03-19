package com.mtoolkit.resource;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;

/**
 * Resource interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 09/08/2011
 * @since	JDK1.5
 */
public interface Resource extends Serializable {
	
	/**
	 * Tests whether this file resource exists.
	 * 
	 * @return <code>true</code> if and only if this file resource exists;
	 * 		   <code>false</code> otherwise.
	 */
	public boolean exists();
	
	/**
	 * Tests whether this file resource is opened.
	 * 
	 * @return <code>true</code> if and only if this file resource is opened;
	 * 		   <code>false</code> otherwise. 
	 */
	public boolean isOpen();
	
	/**
	 * Tests whether this file resource is readable.
	 * 
	 * @return <code>true</code> if and only if this file resource is readable;
	 * 		   <code>false</code> otherwise. 
	 */
	public boolean canRead();
	
	/**
	 * Returns the file of this file resource.
	 * 
	 * @return the file of this file resource.
	 */
	public File getFile();
	
	/**
	 * Returns the file name of this file resource.
	 * 
	 * @return the file name of this file resource.
	 */
	public String getName();
	
	/**
	 * Returns the milliseconds time that the resource was last modified.
	 * 
	 * @return a <code>long</code> value representing the time the file was
     *         last modified, measured in milliseconds since the epoch
     *         (00:00:00 GMT, January 1, 1970), or <code>0L</code> if the
     *         file does not exist or if an I/O error occurs.
	 */
	public long lastModified();
	
	/**
	 * Returns the description of this file resource.
	 * 
	 * @return the description of this file resource.
	 */
	public String getDescription();
	
	/**
	 * Returns the URL of this file resource.
	 * 
	 * @return the URL of this file resource.
	 * 
	 * @throws IOException 
	 *         if an I/O error occurs while creating the URL from this file resource.
	 */
	public URL getURL() throws IOException;
	
	/**
	 * Returns the URI of this file resource.
	 * 
	 * @return the URI of this file resource.
	 * 
	 * @throws IOException
	 * 	  	   if an I/O error occurs while creating the URL from this file resource.
	 */
	public URI getURI() throws IOException;
	
	/**
	 * Mirrors the resource to the specified target directory.
	 * 
	 * @param  localDir target directory where mirror to.
	 * 
	 * @throws NullPointerException
	 * 		   if the specified <code>targetDir</code> is null.
	 * @throws IOException
	 * 		   if an I/O error occurs while mirror the resource to the target directory.
	 */
	public <T extends Resource> T mirror(final String localDir) throws IOException;
	
}
