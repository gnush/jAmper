package model;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class Resource {
	public final static int FILE = 1;
	public final static int URI = 2;
	
	public abstract int getType();
	public abstract File getFile();
	public abstract URI getURI() throws URISyntaxException;
}