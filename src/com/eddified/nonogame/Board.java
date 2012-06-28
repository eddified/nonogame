package com.eddified.nonogame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_SIZE = 900;

	public Board() {
		super();
		setPreferredSize(new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		
		PuzzleDefinition pi = new PuzzleDefinition(4, 4);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;

	}
}
