package com.mtoolkit.resource.directory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;

import com.mtoolkit.resource.DirectoryResource;
import com.mtoolkit.resource.FileResource;
import com.mtoolkit.resource.Resource;

/**
 * TODO
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 21/06/2012
 * @since 	JDK1.5
 */
public class AbstractDirectoryResource implements DirectoryResource {

	/** serial version UID */
	private static final long serialVersionUID = 1126858141524092163L;

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canRead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long lastModified() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getURL() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getURI() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public <T extends Resource> T mirror(String targetDir) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Iterator<FileResource> fileResourceIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
