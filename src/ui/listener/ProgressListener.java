package ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JSlider;

import audio.interfaces.IAudioController;

public class ProgressListener implements MouseListener{
	private IAudioController ctrl;
	private JSlider sld;
	
	public ProgressListener(IAudioController ctrl, JSlider sld) {
		this.ctrl = ctrl;
		this.sld = sld;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.ctrl.seek((long) this.sld.getValue(), TimeUnit.SECONDS);
		System.out.println(this.sld.getValue());
	}
}
