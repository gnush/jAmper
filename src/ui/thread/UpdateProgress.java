package ui.thread;

import javax.swing.JSlider;

import audio.interfaces.IAudioController;

public class UpdateProgress extends Thread{
	private IAudioController ctrl;
	private JSlider progress;
	
	public UpdateProgress(IAudioController ctrl, JSlider progress) {
		this.progress = progress;
		this.ctrl = ctrl;
	}
	
	@Override
	public void run() {
		while(progress.getValue() != ctrl.getLength().toSeconds() && ctrl.isPlaying()){
			progress.setValue((int) ctrl.getCurrentPosition().toSeconds());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}