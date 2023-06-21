package GiaoDien;

import java.awt.BorderLayout;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import PacManGame.GhostMap;

public class GameScreen extends JFrame {

	Image icon = new ImageIcon("images/gameicon.png").getImage();
	private static JFrame frame;
	private static GhostMap pm;
	private GSButton btn;

	public GameScreen() {
		frame = new JFrame("Pacman");
		pm = new GhostMap();
		btn = new GSButton();
		
		frame.setLayout(new BorderLayout());
		frame.add(btn, BorderLayout.NORTH);
		
		pm.setFocusable(true);
		btn.setFocusable(false);
		
		frame.add(pm, BorderLayout.CENTER);
		frame.setSize(585, 790);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setIconImage(icon);			
	}

	public static void an() {
		frame.setVisible(false);
	}

}
