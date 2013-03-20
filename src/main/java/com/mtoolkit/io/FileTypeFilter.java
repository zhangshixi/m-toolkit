package com.mtoolkit.io;

import java.io.File;
import java.io.FileFilter;

/**
 * A file filter that can only accept the given file type format.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 26/11/2011
 * @since 	JDK1.5
 * 
 * @see		java.io.FileFilter
 */
public class FileTypeFilter implements FileFilter {

	/* file type */
	private String _type;

	/**
	 * Creates a instance that can only accept the specified type format.
	 * 
	 * @param  type file type to be accepted.
	 * 
	 * @throws NullPointerException if {@code type} is null.
	 */
	public FileTypeFilter(String type) {
		if (type == null) {
			throw new NullPointerException("type");
		}

		_type = type;
	}

	/**
	 * Tests whether or not the specified file is match the given file type format.
	 * 
	 * @param  file the given file to be tested.
	 * 
	 * @return {@code true} if and only if the file is match the given file type format;
     * 		   {@code false} otherwise.
	 */
	@Override
	public boolean accept(File file) {
		if (file == null) {
			return false;
		}
		
		if (file.isFile()) {
			String name = file.getName();
			String extendName = name.substring(name.lastIndexOf(".") + 1);
			extendName = extendName.toLowerCase();
			if (_type.equalsIgnoreCase(extendName)) {
				return true;
			}
		}
		
		return false;
	}
	
}
