package PacManGame;

import java.awt.Graphics2D;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pacman extends JPanel {

	private final int PACMAN_SPEED = 3;

	private Image down = new ImageIcon("images/down.gif").getImage();
	private Image up = new ImageIcon("images/up.gif").getImage();
	private Image left = new ImageIcon("images/left.gif").getImage();
	private Image right = new ImageIcon("images/right.gif").getImage();

	private boolean dying = false;

	private int pacman_x, pacman_y, pacmand_x, pacmand_y;
	private int req_dx, req_dy;

	private static int score;

	public static void setScore(int score) {
		Pacman.score = score;
	}

	public static int getScore() {
		return score;
	}

	public boolean isDying() {
		return dying;
	}

	public void setDying(boolean dying) {
		this.dying = dying;
	}

	public int getPacman_x() {
		return pacman_x;
	}
	
	public void setPacman_x(int pacman_x) {
		this.pacman_x = pacman_x;
	}

	public int getPacman_y() {
		return pacman_y;
	}

	public void setPacman_y(int pacman_y) {
		this.pacman_y = pacman_y;
	}

	public void setPacmand_x(int pacmand_x) {
		this.pacmand_x = pacmand_x;
	}

	public void setPacmand_y(int pacmand_y) {
		this.pacmand_y = pacmand_y;
	}

	public void setReq_dx(int req_dx) {
		this.req_dx = req_dx;
	}

	public void setReq_dy(int req_dy) {
		this.req_dy = req_dy;
	}

	public void movePacman(short[] screenData) {

		int pos;
		short ch;

		if (pacman_x % GhostMap.getBlockSize() == 0 && pacman_y % GhostMap.getBlockSize() == 0) {
			pos = pacman_x / GhostMap.getBlockSize() + GhostMap.getnBlocks() * (int) (pacman_y / GhostMap.getBlockSize());
			
			ch = screenData[pos];

			if ((ch & 16) != 0) {
				screenData[pos] = (short) (ch & 15);
				score++;
			}

			if (req_dx != 0 || req_dy != 0) 
			{
				if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0) 
					|| (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
					|| (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
					|| (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) 
				{
					pacmand_x = req_dx;
					pacmand_y = req_dy;
				}
			}

			// Check for standstill
			if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
					|| (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
					|| (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
					|| (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
				pacmand_x = 0;
				pacmand_y = 0;
			}
		}
		pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
		pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
	}

	// Class PACMAN
	public void drawPacman(Graphics2D g2d) {

		if (req_dx == -1 && req_dy == 0) {
			g2d.drawImage(left, pacman_x + 3, pacman_y + 4, this);
		} else if (req_dx == 1 && req_dy == 0) {
			g2d.drawImage(right, pacman_x + 3, pacman_y + 4, this);
		} else if (req_dy == -1 && req_dx == 0) {
			g2d.drawImage(up, pacman_x + 3, pacman_y + 4, this);
		} else if (req_dy == 1 && req_dx == 0) {
			g2d.drawImage(down, pacman_x + 3, pacman_y + 4, this);
		} else
			g2d.drawImage(right, pacman_x + 3, pacman_y + 4, this);

	}

}