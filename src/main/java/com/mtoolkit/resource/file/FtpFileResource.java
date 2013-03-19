package com.mtoolkit.resource.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;

import org.apache.commons.net.ftp.FTPClient;

/**
 * A remote ftp file resource implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 25/08/2011
 * @since 	JDK1.5
 */
public class FtpFileResource extends AbstractFileResource {
	
	/* serial version id */
	private static final long serialVersionUID = -3515057042606353190L;
	
	/* the pathname of the remote ftp file resource */
	private String 		_pathname;
	private String 		_username;
	private String 		_password;
	private InetAddress _ftpHost;
	private FTPClient 	_ftpClient;
	
	/**
	 * Creates a file resource by the specified file of the remote ftp server.
	 * 
	 * @param pathname the pathname of the remote ftp file.
	 * 
	 * @throws NullPointerException if <code>file</code> is null.
	 */
	public FtpFileResource(final String pathname) {
		if (pathname == null) {
			throw new NullPointerException("pathname");
		}

		_pathname = pathname;
	}
	
	public void init(InetAddress ftpHost, String username, String password)
		throws SocketException, IOException {
		
		_ftpClient = new FTPClient();
		_ftpClient.connect(ftpHost);
		_ftpClient.login(username, password);
	}
	
	public void destory() throws IOException{
		_ftpClient.logout();
		_ftpClient.disconnect();
	}
	
	@Override
	public URL getURL() throws IOException {
		final StringBuilder buf = new StringBuilder();
		buf.append("ftp://")
		   .append(_username)
		   .append(":")
		   .append(_password)
		   .append("@")
		   .append(_ftpHost)
		   .append(_pathname);
		
		return new URL(buf.toString());
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		return getURL().openStream();
	}

}
