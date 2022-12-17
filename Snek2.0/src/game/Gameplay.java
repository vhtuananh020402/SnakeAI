package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Gameplay extends JPanel {

	public Gameplay() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		//border of the title image
		g.setColor(Color.white);
		g.drawRect(24, 10, 840, 55);	// x, y, width, height
		
		g.setColor(Color.white);
		g.drawRect(24, 75, 840, 575);
		g.setColor(Color.black);
		g.fillRect(25, 76, 839, 575);
		
	}
}
