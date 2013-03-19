package com.mtoolkit.resource.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.mtoolkit.resource.FileResource;

/**
 * A local file system file resource implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 25/08/2011
 * @since 	JDK1.5
 */
public class LocalFileResource 
	   extends AbstractFileResource implements FileResource {

	/** serial version id */
	private static final long serialVersionUID = -6490193547611707582L;

	/** the file of this file resource */
	private File _file;

	/** the path of this file resource */
	private String _path;

	// ---- constructors -------------------------------------------------------
	/**
	 * Creates a file resource by the specified file of the local file system.
	 * 
	 * @param file local file system file.
	 * 
	 * @throws NullPointerException if <code>file</code> is null.
	 */
	public LocalFileResource(final File file) {
		if (file == null) {
			throw new NullPointerException("file");
		}

		_file = file;
		_path = file.getAbsolutePath();
	}

	/**
	 * Creates a file resource by the specified local file system path.
	 * 
	 * @param  path local file system file path.
	 * 
	 * @throws NullPointerException if the file <code>path</code> is null.
	 */
	public LocalFileResource(final String path) {
		if (path == null) {
			throw new NullPointerException("path");
		}

		_path = path;
		_file = new File(path);
	}

	// ---- public methods -----------------------------------------------------
	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean exists() {
		return _file.exists();
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean canRead() {
		return _file.canRead();
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public File getFile() {
		return _file;
	}
	
	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean isOpen() {
		return true;
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public String getName() {
		return _file.getName();
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public long lastModified() {
		return _file.lastModified();
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return LocalFileResource.class.getName() + "[" + _path + "]";
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public URL getURL() throws IOException {
		return _file.toURI().toURL();
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(_file);
	}
	
	/**
	 * @see #getDescription()
	 */
	@Override
	public String toString() {
		return getDescription();
	}
	
	public static void main(String[] args) throws IOException {
		FileResource resource = new LocalFileResource(
				new File("D://test.txt"));
		System.out.println(resource.exists());
		System.out.println(resource.canRead());
		System.out.println(resource.getDescription());
		
		resource.mirror("D://res");
	}
}
