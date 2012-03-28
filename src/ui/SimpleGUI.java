package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.FileResource;

import audio.imp.Audio;
import audio.imp.AudioController;
import audio.interfaces.IAudio;
import audio.interfaces.IAudioController;

public class SimpleGUI {
	private JFrame f;
	private JPanel buttonPanel;
	private JPanel statusPanel;
	private JButton playButton = new JButton("play");
	private JButton pauseButton = new JButton("pause");
	private JButton stopButton = new JButton("stop");
	private JButton nextButton = new JButton("next");
	private JButton prevButton = new JButton("prev");
	
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
		
		// Action Listener
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.play();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		prevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		buttonPanel.setLayout(new GridLayout(1, 5));
		statusPanel.setLayout(new BorderLayout());
		
		buttonPanel.add(playButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);
		
		//statusPanel.add();
		
		f.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		f.getContentPane().add(statusPanel, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new SimpleGUI(args);
	}
}
