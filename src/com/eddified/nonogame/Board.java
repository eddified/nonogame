package com.eddified.nonogame;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int NUM_BOARD_SQUARES_ON_ONE_SIDE = 15;
	private GridController gc;

	public Board() {
		super();
		// setBackground(Color.LIGHT_GRAY);
		gc = new GridController(NUM_BOARD_SQUARES_ON_ONE_SIDE,
				NUM_BOARD_SQUARES_ON_ONE_SIDE);
		this.add(gc.getView());

		setFocusable(true);
		setDoubleBuffered(true);

		// PuzzleDefinition pi = new PuzzleDefinition(4, 4);
	}

	public JMenuBar getJMenuBar() {
		return gc.getMenuBar();
	}
}
