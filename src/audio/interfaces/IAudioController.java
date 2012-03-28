package audio.interfaces;

public interface IAudioController {
	public void play(IAudio track);
	public void pause();
	public void next();
	public void prev();
	public void setVolume(int percentage);
	public void setVolume(double value);
	public void volumeUp();
	public void volumeDown();
}