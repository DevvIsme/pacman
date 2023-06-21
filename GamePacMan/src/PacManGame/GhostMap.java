package PacManGame;


import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import GiaoDien.GameScreen;
import GiaoDien.LostFrame;
import GiaoDien.WinnerFrame;

public class GhostMap extends JPanel implements ActionListener, KeyListener {

	private Dimension d;
	private static boolean inGame = false;

	private static final int BLOCK_SIZE = 30;
	private static final int N_BLOCKS = 19;
	private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;

	private int N_GHOSTS = 8;
	private Image Rghost = new ImageIcon("images/Rghost.gif").getImage();
	private Image Cghost = new ImageIcon("images/Cghost.gif").getImage();
	private Image Oghost = new ImageIcon("images/Oghost.gif").getImage();
	private Image Pghost = new ImageIcon("images/Pghost.gif").getImage();
	private int[] dx, dy;
	private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
	private Image[] ghost = { Rghost, Rghost, Cghost, Cghost, Oghost, Oghost, Pghost, Pghost };
	private final int validSpeeds[] = { 1, 2, 3, 4, 6, 8 };
	private int currentSpeed;

	private Timer timer;

	private Pacman pacman = new Pacman();
	private int lives;

	private short[] screenData;

	private final short levelData[] = 
		{19,	26,	26,	18,	26,	26,	18,	26,	18,	18,	18,	26,	18,	26,	26,	18,	26,	26,	22,
		21,	0,	0,	21,	0,	0,	21,	0,	17,	24,	20,	0,	21,	0,	0,	21,	0,	0,	21,
		21,	0,	19,	16,	26,	18,	20,	0,	21,	0,	21,	0,	17,	18,	26,	16,	22,	0,	21,
		21,	0,	17,	28,	0,	25,	20,	0,	21,	0,	21,	0,	17,	28,	0,	25,	20,	0,	21,
		17,	26,	20,	0,	0,	0,	21,	0,	21,	0,	21,	0,	21,	0,	0,	0,	17,	26,	20,
		21,	0,	17,	22,	0,	19,	20,	0,	17,	18,	20,	0,	17,	22,	0,	19,	20,	0,	21,
		21,	0,	25,	16,	26,	24,	16,	18,	16,	16,	16,	18,	16,	24,	26,	16,	28,	0,	21,
		21,	0,	0,	21,	0,	0,	17,	16,	16,	24,	16,	16,	20,	0,	0,	21,	0,	0,	21,
		17,	18,	26,	24,	26,	18,	16,	16,	28,	0,	25,	16,	16,	18,	26,	24,	26,	18,	20,
		17,	20,	0,	0,	0,	17,	16,	20,	0,	0,	0,	17,	16,	20,	0,	0,	0,	17,	20,
		17,	24,	26,	18,	26,	24,	16,	16,	22,	0,	19,	16,	16,	24,	26,	18,	26,	24,	20,
		21,	0,	0,	21,	0,	0,	17,	16,	16,	18,	16,	16,	20,	0,	0,	21,	0,	0,	21,
		21,	0,	19,	16,	26,	18,	16,	24,	16,	16,	16,	24,	16,	18,	26,	16,	22,	0,	21,
		21,	0,	17,	28,	0,	25,	20,	0,	17,	24,	20,	0,	17,	28,	0,	25,	20,	0,	21,
		17,	26,	20,	0,	0,	0,	21,	0,	21,	0,	21,	0,	21,	0,	0,	0,	17,	26,	20,
		21,	0,	17,	22,	0,	19,	20,	0,	21,	0,	21,	0,	17,	22,	0,	19,	20,	0,	21,
		21,	0,	25,	16,	26,	24,	20,	0,	21,	0,	21,	0,	17,	24,	26,	16,	28,	0,	21,
		21,	0,	0,	21,	0,	0,	21,	0,	17,	18,	20,	0,	21,	0,	0,	21,	0,	0,	21,
		25,	26,	26,	24,	26,	26,	24,	26,	24,	24,	24,	26,	24,	26,	26,	24,	26,	26,	28};

	public static int getBlockSize() {
		return BLOCK_SIZE;
	}

	public static int getnBlocks() {
		return N_BLOCKS;
	}

	public static void setInGame(boolean inGame) {
		GhostMap.inGame = inGame;
	}

	public short[] getScreenData() {
		return screenData;
	}

	// Khoi tao ban choi
	public GhostMap() {
		initVariables();
		addKeyListener(this);
		setFocusable(true);
		initGame();
	}

	// Khoi tao cac bien de bat dau tro choi
	private void initVariables() {

		screenData = new short[N_BLOCKS * N_BLOCKS];
		ghost_x = new int[N_GHOSTS];
		ghost_dx = new int[N_GHOSTS];
		ghost_y = new int[N_GHOSTS];
		ghost_dy = new int[N_GHOSTS];
		ghostSpeed = new int[N_GHOSTS];
		dx = new int[4];
		dy = new int[4];
		d = new Dimension(800, 800);
		timer = new Timer(40, this);
		timer.start();
	}

	// Thiet lap cac thuoc tinh cho game moi
	private void initGame() {
		lives = 5;
		Pacman.setScore(0);
		initLevel();
		currentSpeed = 1;
	}
	
	private void initLevel() {

		int i;
		for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
			screenData[i] = levelData[i];
		}
		continueLevel();
	}

	// Khoi tao man choi moi
	private void continueLevel() {

		int dx = 1;
		int random;
		int i;
		for (i = 0; i < N_GHOSTS; i++) {
			if (i >= 6) {
				ghost_y[i] = 16 * BLOCK_SIZE;
				ghost_x[i] = 16 * BLOCK_SIZE;
			} else if (i >= 4) {
				ghost_y[i] = 2 * BLOCK_SIZE;
				ghost_x[i] = 16 * BLOCK_SIZE;
			} else if (i >= 2) {
				ghost_y[i] = 16 * BLOCK_SIZE;
				ghost_x[i] = 2 * BLOCK_SIZE;
			} else {
				ghost_y[i] = 2 * BLOCK_SIZE;
				ghost_x[i] = 2 * BLOCK_SIZE;
			}
			ghost_dy[i] = 0;
			ghost_dx[i] = dx;
			dx = -dx;
			random = (int) (Math.random() * (currentSpeed + 1));

			if (random > currentSpeed) {
				random = currentSpeed;
			}
			ghostSpeed[i] = validSpeeds[random];
		}

		pacman.setPacman_x(7 * BLOCK_SIZE); 
		pacman.setPacman_y(11 * BLOCK_SIZE);
		pacman.setPacmand_x(0);
		pacman.setPacmand_y(0);
		pacman.setReq_dx(0);
		pacman.setReq_dy(0);
		pacman.setDying(false);
	}

	// Ve GamePlay
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, d.width, d.height);

		drawMaze(g2d);
		drawScore(g2d);

		if (inGame) {
			playGame(g2d);
		} else {
			showIntroScreen(g2d);
		}

		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}

	// Ve ma tran
	private void drawMaze(Graphics2D g2d) {

		short i = 0;
		int x, y;

		for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
			for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

				g2d.setColor(Color.BLUE);
				g2d.setStroke(new BasicStroke(5));

				if ((levelData[i] == 0)) {
					g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
				}

				if ((screenData[i] & 1) != 0) {
					g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
				}

				if ((screenData[i] & 2) != 0) {
					g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
				}

				if ((screenData[i] & 4) != 0) {
					g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
				}

				if ((screenData[i] & 8) != 0) {
					g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
				}

				if ((screenData[i] & 16) != 0) {
					g2d.setColor(Color.white);
					g2d.fillOval(x + 11, y + 11, 6, 6);
				}

				i++;
			}
		}
	}

	// Ve bang diem va lives
	private void drawScore(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.yellow);
		String s = "Score: " + Pacman.getScore();
		String s1 = "Lives: ";
		g.drawString(s1, SCREEN_SIZE / 2 - 250, SCREEN_SIZE + 30);
		g.drawString(s, SCREEN_SIZE / 2 + 100, SCREEN_SIZE + 30);
		Image heart = new ImageIcon("images/right.gif").getImage();
		for (int i = 0; i < lives; i++) {
			g.drawImage(heart, i * 28 + 130, SCREEN_SIZE + 10, this);
		}
	}

	private void showIntroScreen(Graphics2D g2d) {

		String start = "Press SPACE to start";
		g2d.setColor(Color.yellow);
		g2d.setFont(new Font("Arial", Font.BOLD, 40));
		g2d.drawString(start, (SCREEN_SIZE) / 4 - 60, 300);
	}

	// Kiem tra tien trinh tro choi
	private void playGame(Graphics2D g2d) {
		if (pacman.isDying()) {

			death();

		} else {

			pacman.movePacman(this.getScreenData());
			pacman.drawPacman(g2d);
			moveGhosts(g2d);
			checkMaze();
		}
	}

	// Khi PacMan chet
	private void death() {

		lives--;
		// So mang =0 thi thua
		if (lives == 0) {
			inGame = false;
			GameScreen.an();
			new LostFrame();
		}
		// neu chua thua thi tiep tuc man choi voi viec thiet lap lai vi tri cho cac
		// nhan vat
		continueLevel();
	}

	// Tao cac huong di chuyen ngau nhien cho con ma
	private void moveGhosts(Graphics2D g2d) {
		
		int pos;
		int count;

		for (int i = 0; i < N_GHOSTS; i++) {
			if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {

				pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);
				count = 0;

				if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
					dx[count] = -1;
					dy[count] = 0;
					count++;
				}

				if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
					dx[count] = 0;
					dy[count] = -1;
					count++;
				}

				if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
					dx[count] = 1;
					dy[count] = 0;
					count++;
				}

				if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
					dx[count] = 0;
					dy[count] = 1;
					count++;
				}
							
				
				count = (int) (Math.random() * count);
				if (count > 3) {
					count = 3;
				}
				ghost_dx[i] = dx[count];
				ghost_dy[i] = dy[count];
				
			}

			ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
			ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);

			drawGhost(g2d, ghost_x[i] + 3, ghost_y[i] + 3, ghost[i]);

			if (pacman.getPacman_x() > (ghost_x[i] - 12) && pacman.getPacman_x() < (ghost_x[i] + 12)
					&& pacman.getPacman_y() > (ghost_y[i] - 12) && pacman.getPacman_y() < (ghost_y[i] + 12)) {
				pacman.setDying(true);
			}
		}
	}

	// Ve con ma dua tren thu vien do hoa, tao do va hinh anh
	private void drawGhost(Graphics2D g2d, int x, int y, Image ma) {
		g2d.drawImage(ma, x, y, this);
	}

	// Kiem tra GhostMap
	private void checkMaze() {

		int i = 0;
		boolean finished = true;
		while (i < N_BLOCKS * N_BLOCKS && finished) {

			if ((screenData[i] & 16) != 0) {
				finished = false;
			}

			i++;
		}
		if (finished) {
			inGame = false;
			GameScreen.an();
			new WinnerFrame();
		}
	}

	

	// DIEU KHIEN NHAN VAT PACMAN
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (inGame) {
			if (key == KeyEvent.VK_LEFT) {
				pacman.setReq_dx(-1);
				pacman.setReq_dy(0);
			} else if (key == KeyEvent.VK_RIGHT) {
				pacman.setReq_dx(1);
				pacman.setReq_dy(0);
			} else if (key == KeyEvent.VK_UP) {
				pacman.setReq_dx(0);
				pacman.setReq_dy(-1);
			} else if (key == KeyEvent.VK_DOWN) {
				pacman.setReq_dx(0);
				pacman.setReq_dy(1);
			} else if (key == KeyEvent.VK_R && timer.isRunning()) {
				inGame = false;
			}
		} else {
			if (key == KeyEvent.VK_SPACE) {
				inGame = true;
				initGame();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
