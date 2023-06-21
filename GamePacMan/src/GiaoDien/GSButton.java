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
import javax.swing.JLabel;
import javax.swing.JPanel;

import PacManGame.GhostMap;

public class GSButton extends JPanel implements ActionListener {
	public GSButton() {

		setLayout(new GridBagLayout());
		setBackground(Color.black);

		GridBagConstraints c = new GridBagConstraints();

		JButton btnBack = new JButton("Back");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(3, 3, 3, 3);
		btnBack.setBackground(Color.cyan);
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("Arial", Font.BOLD, 25));
		btnBack.setForeground(Color.white);
		btnBack.setPreferredSize(new Dimension(95, 50));
		btnBack.setFocusable(false);
		btnBack.addActionListener(this);
		add(btnBack, c);

		Image Logo = new ImageIcon("images/images.png").getImage();
		JLabel label = new JLabel(new ImageIcon(Logo));
		c.gridx = 1;
		c.gridy = 0;
		label.setPreferredSize(new Dimension(335, 100));
		add(label, c);

		JButton btnRes = new JButton("Restart");
		c.gridx = 2;
		c.gridy = 0;
		btnRes.setBackground(Color.cyan);
		btnRes.setFocusPainted(false);
		btnRes.setFont(new Font("Arial", Font.BOLD, 25));
		btnRes.setForeground(Color.white);
		btnRes.setPreferredSize(new Dimension(120, 50));
		btnRes.setFocusable(false);
		btnRes.setBackground(Color.green);
		btnRes.addActionListener(this);
		add(btnRes, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "Back") {
			GameScreen.an();
			new MainMenu();
		}
		if (cmd == "Restart") {
			GhostMap.setInGame(false);
		}
	}

}