package model;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class FileResource extends Resource{
	private File res;
	
	public FileResource(String res) {
		this.res = new File(res);
	}
	
	@Override
	public int getType() {
		return Resource.FILE;
	}

	@Override
	public File getFile() {
		return this.res;
	}

	@Override
	public URI getURI() throws URISyntaxException {
		return this.res.toURI();
	}
}
