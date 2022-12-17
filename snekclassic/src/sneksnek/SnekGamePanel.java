package sneksnek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class SnekGamePanel extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 100;
	static final int x[] = new int[UNIT_SIZE];
	static final int y[] = new int[UNIT_SIZE];
	int bodyParts = 6;
	int applesEaten = 0;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;

	SnekGamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if (running) {
			// draw grid
			for(int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; ++i) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
			}
			// draw apple
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			// draw snek
			for (int i = 0; i < bodyParts; ++i) {
				if (i == 0) {
					g.setColor(new Color(0, 128, 128));
					g.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);
				} else {
					g.setColor(new Color(30, 161, 161));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
		} else {
			gameOver(g);
		}
		
	}
	
	public void newApple() {
		Boolean checkAppleSpawn;
		do {
			checkAppleSpawn = false;
			appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
			appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
			for (int i = 0; i < bodyParts; ++i) {
				if ((x[i] == appleX) && (y[i] == appleY)) {
					checkAppleSpawn = true;
					break;
				}
			}
		} while (checkAppleSpawn);
	}
	
	public void move() {
		for (int i = bodyParts; i > 0; --i) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		switch(direction) {
		case 'U' :
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D' :
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L' :
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R' :
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	public void checkCollisions() {
		// check if head collides with body
		for(int i = bodyParts; i > 0; --i) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// check if head collides with top wall
		if (y[0] < 0) {
			running = false;
		}
		// check if head collides with bottom wall
		if (y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		// check if head collides with left wall
		if (x[0] < 0) {
			running = false;
		}
		// check if head collides with right wall
		if (x[0] > SCREEN_WIDTH) {
			running = false;
		}
		// stop timer 
		if (!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		if (!running) {
			// Game Over!
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans", Font.BOLD, 70));
			FontMetrics metricsOver = getFontMetrics(g.getFont());
			g.drawString("Game Over!", (SCREEN_WIDTH - metricsOver.stringWidth("Game Over!")) / 2, SCREEN_HEIGHT / 2);
			// Score
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans", Font.BOLD, 40));
			FontMetrics metricsScore = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metricsScore.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}

}
