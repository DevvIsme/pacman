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
import javax.swing.JOptionPane;

public class MainMenu extends JFrame implements ActionListener {
	public MainMenu() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		Image Logo = new ImageIcon("images/pac-man-logo.jpg").getImage();
		JLabel anh = new JLabel(new ImageIcon(Logo));
		anh.setPreferredSize(new Dimension(500, 300));
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		add(anh, c);

		JButton btnStart = new JButton("Start");
		c.gridx = 0;
		c.gridy = 1;
		btnStart.setPreferredSize(new Dimension(200, 50));
		btnStart.setBackground(Color.cyan);
		btnStart.setFocusPainted(false);
		btnStart.setFont(new Font("Arial", Font.BOLD, 35));
		btnStart.setForeground(Color.white);
		btnStart.addActionListener(this);
		add(btnStart, c);

		JButton btnExit = new JButton("Exit");
		c.gridx = 0;
		c.gridy = 2;
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

	private void Thoat() {
		int confirmed = JOptionPane.showConfirmDialog(
				null, "Are you sure you want to exit the Game?",
				"EXIT ?",
				JOptionPane.YES_NO_OPTION);
		if (confirmed == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "Start":
			new GameScreen();
			this.setVisible(false);
			break;
		case "Exit":
			Thoat();
			break;
		}

	}

}
