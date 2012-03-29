package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ui.listener.ProgressListener;
import ui.thread.UpdateProgress;

import model.FileResource;

import audio.imp.Audio;
import audio.imp.AudioController;
import audio.interfaces.IAudio;
import audio.interfaces.IAudioController;

public class SimpleGUI{
	private JFrame f;
	private JPanel buttonPanel;
	private JPanel statusPanel;
	private JButton playButton = new JButton("play");
	private JButton pauseButton = new JButton("pause");
	private JButton stopButton = new JButton("stop");
	private JButton nextButton = new JButton("next");
	private JButton prevButton = new JButton("prev");
	private JSlider volumeSlider;
	private JSlider progressSlider;
	
	public SimpleGUI() {
		
	}
	
	public SimpleGUI(String[] args) {
		final List<IAudio> tracks = new ArrayList<IAudio>();
		tracks.add(new Audio(new FileResource("/home/qwert/Musik/Deichkind/Arbeit nervt/14 - Urlaub vom Urlaub.mp3")));
		tracks.add(new Audio(new FileResource("/home/qwert/Musik/Deichkind/Arbeit nervt/3 - Arbeit nervt.mp3")));
		tracks.add(new Audio(new FileResource("/home/qwert/Musik/Deichkind/Aufstand im Schlaraffenland/3 - Remmidemmi (Yippie Yippie Yeah).mp3")));
		final IAudioController controller = new AudioController(args, tracks);
		
		f = new JFrame("SimpleGui");
		buttonPanel = new JPanel();
		statusPanel = new JPanel();
		
		volumeSlider = new JSlider(JSlider.VERTICAL, 0, 100, controller.getVolumePercent());
		volumeSlider.setMajorTickSpacing(25);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				controller.setVolume(volumeSlider.getValue());
			}
		});
		
		progressSlider = new JSlider();
		progressSlider.setPaintTicks(true);
		progressSlider.setPaintLabels(true);
		progressSlider.addMouseListener(new ProgressListener(controller, progressSlider));
		progressSlider.setValue(0);
		
		// Action Listener
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.play();
					updateProgressSlider(controller);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pause();
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.stop();
			}
		});
		
		prevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.prev();
					updateProgressSlider(controller);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.next();
					updateProgressSlider(controller);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		buttonPanel.setLayout(new GridLayout(1, 5));
		statusPanel.setLayout(new BorderLayout());
		
		buttonPanel.add(playButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);
		
		statusPanel.add(volumeSlider, BorderLayout.EAST);
		statusPanel.add(progressSlider, BorderLayout.CENTER);
		
		f.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		f.getContentPane().add(statusPanel, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new SimpleGUI(args);
	}
	
	private void updateProgressSlider(IAudioController controller){
		progressSlider.setValue(0);
		
		// wait for track to determine length
		try{
			Thread.sleep(60);
		}catch (InterruptedException ie) {}
		
		int length = (int) controller.getLength().toSeconds();
		
		Dictionary<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(0, new JLabel("0:00"));
		labels.put(length, new JLabel(length / 60 + ":" + length % 60));
		
		progressSlider.setLabelTable(labels);
		progressSlider.setMaximum(length);
		UpdateProgress p = new UpdateProgress(controller, progressSlider);
		p.start();
	}
}
