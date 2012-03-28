package audio.interfaces;

import model.Resource;

/**
 * Represents an single Audio Track.
 * 
 * @author qwert
 *
 */
public interface IAudio {

	/**
	 * 
	 * @return resource associated with the object.
	 */
	public Resource getResource();
	
	/**
	 * 
	 * @return true if the associated resource is an URL
	 */
	public boolean isURI();
	
	/**
	 * 
	 * @return true if the associated resource is a file.
	 */
	public boolean isFile();
}