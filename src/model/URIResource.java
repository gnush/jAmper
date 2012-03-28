package model;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class URIResource extends Resource{
	private URI res;

	public URIResource(String res) throws URISyntaxException {
		this.res = new URI(res);
	}
	
	@Override
	public int getType() {
		return Resource.URI;
	}

	@Override
	public File getFile() {
		return new File(this.res);
	}

	@Override
	public URI getURI() throws URISyntaxException {
		return this.res;
	}

}
