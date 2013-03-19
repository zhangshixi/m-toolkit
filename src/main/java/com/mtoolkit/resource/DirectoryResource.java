package com.mtoolkit.resource;

import java.util.Iterator;

/**
 * Directory resource interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 08/15/2011
 * @since 	JDK1.5
 */
public interface DirectoryResource extends Resource {

	/**
	 * Returns an iterator over the file resource in the directory.
	 * 
	 * @return an iterator over the file resource in the directory.
	 */
	public Iterator<FileResource> fileResourceIterator();

}
