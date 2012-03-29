package audio.imp;

import java.net.URISyntaxException;
import java.util.List;

//import javax.swing.SwingUtilities;

import org.gstreamer.Bus;
import org.gstreamer.ClockTime;
import org.gstreamer.ElementFactory;
import org.gstreamer.Gst;
import org.gstreamer.GstObject;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin2;

import audio.interfaces.IAudio;
import audio.interfaces.IAudioController;
import audio.interfaces.IAudioList;

public class AudioController implements IAudioController{
	private PlayBin2 playbin;
	private IAudioList tracks;
	private int currentPosition;
	
	public AudioController() {
		Gst.init();
		playbin = new PlayBin2("PureAudio");
		playbin.setVideoSink(ElementFactory.make("fakesink", "videosink"));
		this.tracks = new AudioList();
		currentPosition = 0;
		
        playbin.getBus().connect(new Bus.EOS() {
            public void endOfStream(GstObject source) {
                System.out.println("Finished playing file");
                Gst.quit();
            }
        });
        
        playbin.getBus().connect(new Bus.ERROR() {
            public void errorMessage(GstObject source, int code, String message) {
                System.out.println("Error occurred: " + message);
                Gst.quit();
            }
        });
        playbin.getBus().connect(new Bus.STATE_CHANGED() {
            public void stateChanged(GstObject source, State old, State current, State pending) {
                if (source == playbin) {
                    System.out.println("Pipeline state changed from " + old + " to " + current);
                }
            }
        });
	}
	
	public AudioController(String[] args){
		Gst.init("PureAudio", args);
		playbin = new PlayBin2("PureAudio");
		playbin.setVideoSink(ElementFactory.make("fakesink", "videosink"));
		tracks = new AudioList();
		currentPosition = 0;
		
		playbin.getBus().connect(new Bus.EOS() {
            public void endOfStream(GstObject source) {
                System.out.println("Finished playing file");
                Gst.quit();
            }
        });
        
        playbin.getBus().connect(new Bus.ERROR() {
            public void errorMessage(GstObject source, int code, String message) {
                System.out.println("Error occurred: " + message);
                Gst.quit();
            }
        });
        playbin.getBus().connect(new Bus.STATE_CHANGED() {
            public void stateChanged(GstObject source, State old, State current, State pending) {
                if (source == playbin) {
                    System.out.println("Pipeline state changed from " + old + " to " + current);
                }
            }
        });
	}
	
	public AudioController(String[] args, List<IAudio> tracks){
		Gst.init("PureAudio", args);
		playbin = new PlayBin2("PureAudio");
		playbin.setVideoSink(ElementFactory.make("fakesink", "videosink"));
		this.tracks = new AudioList(tracks);
		currentPosition = 0;
		
		playbin.getBus().connect(new Bus.EOS() {
            public void endOfStream(GstObject source) {
                System.out.println("Finished playing file");
                Gst.quit();
            }
        });
        
        playbin.getBus().connect(new Bus.ERROR() {
            public void errorMessage(GstObject source, int code, String message) {
                System.out.println("Error occurred: " + message);
                Gst.quit();
            }
        });
        playbin.getBus().connect(new Bus.STATE_CHANGED() {
            public void stateChanged(GstObject source, State old, State current, State pending) {
                if (source == playbin) {
                    System.out.println("Pipeline state changed from " + old + " to " + current);
                }
            }
        });
	}

	@Override
	public void next() throws URISyntaxException {
		currentPosition++;
		stop();
		play();
	}

	@Override
	public void pause() {
		playbin.setState(State.PAUSED);
	}

	@Override
	public void play() throws URISyntaxException {
		if(currentPosition < 0)
			currentPosition += tracks.numberOfTracks();
		
		if(playbin.getState().equals(State.PAUSED)){
			playbin.setState(State.PLAYING);
			return;
		}
		
		if(!Gst.isInitialized())
			Gst.init();
		
		IAudio track = tracks.getTrack(currentPosition % tracks.numberOfTracks()); 
		
		if(track.isFile())
			playbin.setInputFile(track.getResource().getFile());
		else if(track.isURI())
			playbin.setURI(track.getResource().getURI());
		else
			return;
		
		playbin.setState(State.PLAYING);
		
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				Gst.main();
//			}
//		});
		
	}
	
	@Override
	public void stop() {
		playbin.setState(State.NULL);
//		Gst.deinit();
//		Gst.quit();
	}

	@Override
	public void prev() throws URISyntaxException {
		currentPosition--;
		stop();
		play();
	}

	@Override
	public void setVolume(int percentage) {
		this.playbin.setVolumePercent(percentage);
	}

	@Override
	public void setVolume(double value) {
		playbin.setVolume(value);
	}

	@Override
	public void volumeDown() {
		playbin.setVolume(this.getVolume() - 0.05);
	}

	@Override
	public void volumeUp() {
		playbin.setVolume(this.getVolume() + 0.05);
	}

	@Override
	public double getVolume() {
		return playbin.getVolume();
	}
	
	@Override
	public double getVolumePercent() {
		return playbin.getVolumePercent();
	}
	
	@Override
	public ClockTime getLength() {
		return playbin.queryDuration();
	}
	
	@Override
	public ClockTime getCurrentPosition() {
		return playbin.queryPosition();
	}
	
	@Override
	public IAudioList getTracks() {
		return tracks;
	}

	@Override
	public boolean addTrack(IAudio track) {
		return tracks.addTrack(track);
	}
	
	@Override
	public void addTrack(IAudio track, int pos) {
		tracks.addTrack(track, pos);	
	}
}
