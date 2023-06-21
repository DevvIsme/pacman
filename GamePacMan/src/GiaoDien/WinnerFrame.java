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

public class WinnerFrame extends JFrame implements ActionListener {
	public WinnerFrame() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		Image Logo = new ImageIcon("images/pac-man-logo.jpg").getImage();
		JLabel anh = new JLabel(new ImageIcon(Logo));
		anh.setPreferredSize(new Dimension(500, 200));
		c.gridx = 0;
		c.gridy = 0;
		add(anh, c);

		JLabel Win = new JLabel("YOU WIN !!!");
		Win.setFont(new Font("Arial", Font.BOLD, 40));
		Win.setForeground(Color.yellow);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		add(Win, c);

		JButton btnBack = new JButton("Back");
		c.gridx = 0;
		c.gridy = 2;
		btnBack.setPreferredSize(new Dimension(200, 50));
		btnBack.setBackground(Color.red);
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("Arial", Font.BOLD, 35));
		btnBack.setForeground(Color.white);
		btnBack.addActionListener(this);
		add(btnBack, c);

		getContentPane().setBackground(Color.BLACK);
		setResizable(false);
		setSize(585, 790);
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
