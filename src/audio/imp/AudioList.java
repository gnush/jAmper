package audio.imp;

import java.util.ArrayList;
import java.util.List;

import audio.interfaces.IAudio;
import audio.interfaces.IAudioList;

public class AudioList implements IAudioList{
	private List<IAudio> tracks;
	
	public AudioList() {
		this.tracks = new ArrayList<IAudio>();
	}
	
	public AudioList(List<IAudio> tracks) {
		this.tracks = tracks;
	}

	@Override
	public boolean addTrack(IAudio track) {
		return tracks.add(track);
	}

	@Override
	public void addTrack(IAudio track, int pos) {
		tracks.add(pos, track);
	}

	@Override
	public IAudio getTrack(int pos) {
		return tracks.get(pos);
	}

	@Override
	public List<IAudio> getTracks() {
		return tracks;
	}

	@Override
	public List<IAudio> getTracks(int from, int to) {
		List<IAudio> tmp = new ArrayList<IAudio>();
		
		for(int i = from; i<=to; i++)
			tmp.add(tracks.get(i));
		
		return tracks;
	}

	@Override
	public IAudio setTrack(IAudio track, int pos) {
		return tracks.set(pos, track);
	}
	
	@Override
	public int numberOfTracks() {
		return this.tracks.size();
	}
}
