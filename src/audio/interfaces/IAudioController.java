package audio.interfaces;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.gstreamer.ClockTime;

public interface IAudioController {
	public void play() throws URISyntaxException;
	public void stop();
	public void pause();
	public void next() throws URISyntaxException;
	public void prev() throws URISyntaxException;
	
	/**
	 * 
	 * @param percentage from 0 to 100
	 */
	public void setVolume(int percentage);
	
	/**
	 * 
	 * @param value from 0.0 to 1.0
	 */
	public void setVolume(double value);
	
	/**
	 * increase volume by 0.05
	 */
	public void volumeUp();
	
	/**
	 * decrease volume by 0.05
	 */
	public void volumeDown();
	public double getVolume();
	public double getVolumePercent();
	public ClockTime getLength();
	public ClockTime getCurrentPosition();
	
	public boolean addTrack(IAudio track);
	public void addTrack(IAudio track, int pos);
	public IAudioList getTracks();
	
	public boolean seek(long pos, TimeUnit t);
	public boolean seek(ClockTime pos);
}