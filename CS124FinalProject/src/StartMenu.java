import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JProgressBar;


public class StartMenu extends JFrame {

	private JPanel contentPane, panel, loadPanel;
	JProgressBar progressBar;
	Image titleImage, startImage, helpImage, quitImage;

	public StartMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		
		try {
			titleImage = ImageIO.read(new File("img/Logo.png"));
			startImage = ImageIO.read(new File("img/Start.png"));
			helpImage = ImageIO.read(new File("img/Help.png"));
			quitImage = ImageIO.read(new File("img/Quit.png"));
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(titleImage);
		
		JLabel lblGameTitle = new JLabel("");
		lblGameTitle.setIcon(icon);
		lblGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameTitle.setSize(1000, 400);
		lblGameTitle.setLocation(getWidth()/2-lblGameTitle.getWidth()/2, 100);
		contentPane.add(lblGameTitle);
		
		panel = new JPanel();
		panel.setSize(708, 94);
		panel.setLocation(getWidth()/2-panel.getWidth()/2, 500);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(null);
		
		loadPanel = new JPanel();
		loadPanel.setSize(697, 46);
		loadPanel.setLocation(getWidth()/2-loadPanel.getWidth()/2, 572);
		loadPanel.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(0, 0, 697, 46);
		loadPanel.add(progressBar);
		
	
		JButton btnStartGame, btnHelp, btnQuit;
	
		
		
		
		btnStartGame = new JButton();
		btnStartGame.setBackground(Color.BLACK);
		btnStartGame.setBorder(null);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(panel);
				contentPane.add(loadPanel);
				contentPane.repaint();
				progressBar.setMaximum(100);
				
				
				Timer timer = new Timer();
				timer.schedule(new LoadTimer(progressBar), 0, 20);

				
				contentPane.repaint();
				
			}
		});
		btnStartGame.setBounds(289, 5, 127, 49);
		panel.add(btnStartGame);
		
		btnHelp = new JButton();
		btnHelp.setBackground(Color.BLACK);
		btnHelp.setBorder(null);
		btnHelp.setBounds(81, 5, 127, 49);
		panel.add(btnHelp);
		
		btnQuit = new JButton();
		btnQuit.setBackground(Color.BLACK);
		btnQuit.setBorder(null);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(497, 5, 127, 49);
		panel.add(btnQuit);
		
		

		startImage = startImage.getScaledInstance(btnStartGame.getWidth(), btnStartGame.getHeight(), Image.SCALE_SMOOTH);
		helpImage = helpImage.getScaledInstance(btnHelp.getWidth(),btnHelp.getHeight(), Image.SCALE_SMOOTH);
		quitImage = quitImage.getScaledInstance(btnQuit.getWidth(), btnQuit.getHeight(), Image.SCALE_SMOOTH);
		
		ImageIcon startIcon = new ImageIcon(startImage);
		ImageIcon helpIcon = new ImageIcon(helpImage);
		ImageIcon quitIcon = new ImageIcon(quitImage);
		
		btnStartGame.setIcon(startIcon);
		btnHelp.setIcon(helpIcon);
		btnQuit.setIcon(quitIcon);
		
	}
	class LoadTimer extends TimerTask{
		JProgressBar bar;
		public LoadTimer(JProgressBar b){
			bar = b;
		}
		@Override
		public void run() {
			if(bar.getValue() >= 100){
				MainWindow mw;
				try {
					contentPane.remove(loadPanel);
					contentPane.repaint();
					mw = new MainWindow();
					mw.setVisible(true);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				this.cancel();
			}
			bar.setValue(bar.getValue()+1);
			
		}
		
	}
}
