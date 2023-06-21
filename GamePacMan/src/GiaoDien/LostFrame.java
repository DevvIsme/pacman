package GiaoDien;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import PacManGame.Pacman;

public class LostFrame extends JFrame implements ActionListener {

	public LostFrame() {
		
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		Image Logo = new ImageIcon("images/pac-man-logo.jpg").getImage();
		JLabel anh = new JLabel(new ImageIcon(Logo));
		anh.setPreferredSize(new Dimension(500, 200));
		c.gridx = 0;
		c.gridy = 0;
		add(anh, c);

		JLabel Lost = new JLabel("YOU LOST !!! BETTER LUCK NEXTTIME");
		Lost.setFont(new Font("Arial", Font.BOLD, 28));
		Lost.setForeground(Color.yellow);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		add(Lost, c);

		JLabel Score = new JLabel("You Score: " + Pacman.getScore());
		Score.setFont(new Font("Arial", Font.BOLD, 40));
		Score.setForeground(Color.yellow);
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		add(Score, c);

		JButton btnExit = new JButton("Back");
		c.gridx = 0;
		c.gridy = 3;
		btnExit.setPreferredSize(new Dimension(200, 50));
		btnExit.setBackground(Color.red);
		btnExit.setFocusPainted(false);
		btnExit.setFont(new Font("Arial", Font.BOLD, 35));
		btnExit.setForeground(Color.white);
		btnExit.addActionListener(this);
		add(btnExit, c);

		getContentPane().setBackground(Color.BLACK);
		setSize(585, 790);
		setResizable(false);
		setVisible(true);
		setTitle("Pacman");
		Image icon = new ImageIcon("images/gameicon.png").getImage();
		setIconImage(icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "Back") {
			this.setVisible(false);
			new MainMenu();
		}

	}

}
