package game;

import java.awt.Color;

import javax.swing.JFrame;

public class GameMainFrame {
	public GameMainFrame() {
		// create the main Frame of the game
		JFrame f = new JFrame();
		
		
		Gameplay gameplay = new Gameplay();
		f.add(gameplay);

		
		f.setTitle("Snake Game");
		f.setBounds(100, 10, 905, 700);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBackground(Color.DARK_GRAY);	// https://stackoverflow.com/questions/2742270/jframe-setbackground-not-working-why	
		f.setVisible(true);
	}
}
