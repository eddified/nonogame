package com.eddified.nonogame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Nonogame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Nonogame() {
		super();
		Board board = new Board();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		//setLocationRelativeTo(null);
		setTitle("Nonogram");
		setJMenuBar(board.getJMenuBar());
		add(board);
		pack();
		setResizable(false);
		setVisible(true);
		
		repaint();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Nonogame();
			}
		});
	}

}
