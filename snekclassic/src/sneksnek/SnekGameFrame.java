package sneksnek;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnekGameFrame extends JFrame implements ActionListener {
	
	JButton resetButton;
	SnekGamePanel game;
	
	SnekGameFrame() {		

		game = new SnekGamePanel();
		this.add(game);
		this.setSize(700, 700);
		this.setBackground(new Color(22, 50, 76));
		this.setLocationRelativeTo(null);
		this.setTitle("Snek Game!");
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setLayout(null);
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resetButton) {
			this.remove(game);
			game = new SnekGamePanel();
			this.add(game);
			SwingUtilities.updateComponentTreeUI(this);
		}
		
	}
}
