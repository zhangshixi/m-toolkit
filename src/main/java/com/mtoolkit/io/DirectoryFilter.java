package com.mtoolkit.io;

import java.io.File;
import java.io.FileFilter;

/**
 * A file filter that can only accept the file witch is directory.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 26/11/2011
 * @since 	JDK1.5
 * 
 * @see		java.io.FileFilter
 */
public class DirectoryFilter implements FileFilter {

	/**
     * Tests whether or not the specified file is a directory.
     *
     * @param  file the given file to be tested.
     * 
     * @return {@code true} if and only if the file is directory;
     * 		   {@code false} otherwise.
     */
    @Override
    public boolean accept(File file) {
        return file != null && file.isDirectory();
    }
    
}
