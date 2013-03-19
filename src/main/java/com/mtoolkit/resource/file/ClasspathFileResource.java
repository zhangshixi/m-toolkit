package com.mtoolkit.resource.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * TODO
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 21/06/2012
 * @since 	JDK1.5
 */
public class ClasspathFileResource extends AbstractFileResource {

	/** serial version UID */
	private static final long serialVersionUID = -634470668851777518L;
	
	private String _path;
	private Class<?> _clazz;
	private ClassLoader _classLoader;
	
	// ---- constructors -------------------------------------------------------
	public ClasspathFileResource(String path) {
		this(path, (ClassLoader) null);
	}
	
	public ClasspathFileResource(String path, Class<?> clazz) {
		_path = path;
		_clazz = clazz;
	}
	
	public ClasspathFileResource(String path, ClassLoader classLoader) {
		_path = path;
		_classLoader = classLoader;
	}

	// ---- public methods -----------------------------------------------------
	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean exists() {
		return getFile().exists();
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean canRead() {
		return getFile().canRead();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public File getFile() {
		try {
			if (!"".equals(getURL().getProtocol())) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		return getFile().getName();
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public long lastModified() {
		return getFile().lastModified();
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

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream input = null;
		if (_clazz != null) {
			input = _clazz.getResourceAsStream(_path);
		} else {
			input = _classLoader.getResourceAsStream(_path);
		}
		
		if (input == null) {
			throw new FileNotFoundException(getDescription()  + " does not exist");
		}
		
		return input;
	}

	@Override
	public URL getURL() throws FileNotFoundException {
		URL url = null;
		if (_clazz != null) {
			url = _clazz.getResource(_path);
		} else {
			url = _classLoader.getResource(_path);
		}
		
		if (url == null) {
			throw new FileNotFoundException(getDescription()  + " does not exist");
		}
		
		return url;
	}
	
	public ClassLoader getClassLoader() {
		return _classLoader;
	}
	
}
