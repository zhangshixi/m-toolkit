package com.mtoolkit.resource.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A file resource implementation with the specified url file path.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 09/09/2011
 * @since 	JDK1.5
 */
public class UrlFileResource extends AbstractFileResource {

	/** serial version id */
	private static final long serialVersionUID = 3110023795539617553L;

	/** the url of this url resource */
	private URL _url;
	
	/** the path of this url resource */
	private String _urlPath;
	
	// ---- constructors -------------------------------------------------------
	/**
	 * Creates a file resource by the specified url. 
	 * 
	 * @param  url the url of url resource.
	 * 
	 * @throws NullPointerException	if the <code>url</code> is null.
	 */
	public UrlFileResource(final URL url) {
		if (url == null) {
			throw new NullPointerException("url");
		}
		
		_url = url;
		_urlPath = url.getPath();
	}
	
	/**
	 * Creates a file resource by the specified url path.
	 * 
	 * @param  urlPath the path of the url resource.
	 * 
	 * @throws NullPointerException	 if the <code>urlPath</code> is null.
	 * @throws MalformedURLException if the string specifies an unknown protocol.
	 * 
	 * @see	   java.net.URL#URL(java.lang.String)
	 */
	public UrlFileResource(final String urlPath) throws MalformedURLException {
		if (urlPath == null) {
			throw new NullPointerException("urlPath");
		}
		
		_url = new URL(urlPath);
		_urlPath = urlPath;
	}
	
	// ---- public methods -----------------------------------------------------
	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean exists() {
		return false;
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean canRead() {
		return true;
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
		final String file = _url.getFile();
		final int index = file.lastIndexOf('/');		
		
		return index < 0 ? file : file.substring(index);			
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public long lastModified() {
		return -1L;
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return UrlFileResource.class.getName() + "[" + _urlPath + "]";
	}

	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public URL getURL() throws IOException {
		return _url;
	}
	
	/**
	 * {@inheritDoc} 
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return _url.openConnection().getInputStream();
	}
	
	/**
	 * @see #getDescription()
	 */
	@Override
	public String toString() {
		return getDescription();
	}
	
}
