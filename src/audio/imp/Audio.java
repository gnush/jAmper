package audio.imp;

import java.net.URISyntaxException;

import model.FileResource;
import model.Resource;
import model.URIResource;
import audio.interfaces.IAudio;

public class Audio implements IAudio{
	private Resource res;
	
	public Audio(Resource res) {
		this.res = res;
	}
	
	public Audio(String res, int type) throws URISyntaxException{
		if(type == Resource.FILE)
			this.res = new FileResource(res);
		else if(type == Resource.URI)
			this.res = new URIResource(res);
	}
	
	@Override
	public Resource getResource() {
		return this.res;
	}

	@Override
	public boolean isFile() {
		if(this.res.getType() == Resource.FILE)
			return true;
		return false;
	}

	@Override
	public boolean isURI() {
		if(this.res.getType() == Resource.URI)
			return true;
		return false;
	}

}