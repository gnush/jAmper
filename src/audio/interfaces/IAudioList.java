package audio.interfaces;

import java.util.List;

public interface IAudioList {
	/**
	 * 
	 * @return a List containing all tracks hold by this
	 */
	public List<IAudio> getTracks();
	
	/**
	 * 
	 * @param from start index
	 * @param to end index
	 * @return the tracks within the specified range
	 */
	public List<IAudio> getTracks(int from, int to);
	
	public IAudio getTrack(int pos);
	
	/**
	 * Adds a track to the end of the list
	 * @param track the track to be added
	 * @return true if successful
	 */
	public boolean addTrack(IAudio track);
	
	/**
	 * Adds a track after the specified position
	 * @param track track to be added
	 * @param pos position after the track will be added
	 * @return true if successful
	 */
	public void addTrack(IAudio track, int pos);
	/**
	 * Set the track at the given position
	 * @param track track to be set
	 * @param pos position where the track will be set
	 * @return true if successful
	 */
	public IAudio setTrack(IAudio track, int pos);
}