package com.mtoolkit.resource.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import com.mtoolkit.resource.FileResource;
import com.mtoolkit.resource.Resource;
import com.mtoolkit.util.CopyUtil;

/**
 * Abstract implementation class of {@link FileResource}.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 15/08/2011
 * @since 	JDK1.5
 */
public abstract class AbstractFileResource implements FileResource {

	/** serial version id */
	private static final long serialVersionUID = -8867967325547979295L;

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public boolean canRead() {
		return false;
	}

	@Override
	public File getFile() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public long lastModified() {
		return -1L;
	}

	@Override
	public String getDescription() {
		return null;
	}
	
	@Override
	public URI getURI() throws IOException {
		try {
			return getURL().toURI();
		} catch (URISyntaxException e) {
			throw new IOException(e.getMessage(), e);
		}
	}

    @Override
    @SuppressWarnings("unchecked")
	public <T extends Resource> T mirror(String localDir) throws IOException {
		if (localDir == null) {
			throw new NullPointerException("localDir");
		}
 		
		final File mirrorDir = new File(localDir);
		if (!mirrorDir.exists()) {
			throw new FileNotFoundException(
				  "Local mirror path is not existed: " + mirrorDir);
		}
		if (!mirrorDir.isDirectory()) {
			throw new FileNotFoundException(
				  "Local mirror path is not a directory: " + mirrorDir);
		}
		if (!mirrorDir.canWrite()) {
			throw new IOException(
				  "Local mirror directory can not be writed: " + mirrorDir);
		}
		
		final File mirrorFile = new File(
				mirrorDir.getAbsolutePath() + File.separator + getName());
		OutputStream output = null;
		try {
			output = new FileOutputStream(mirrorFile);
			CopyUtil.copy(getInputStream(), output);
		} finally {
			if (output != null) {
				output.close();
			}
		}
		
		return (T) new LocalFileResource(mirrorFile);
	}
	
	/**
	 * @see #getDescription()
	 */
	@Override
	public String toString() {
		return getDescription();
	}
	
}
