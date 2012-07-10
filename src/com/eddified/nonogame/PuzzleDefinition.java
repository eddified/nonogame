package com.eddified.nonogame;

public class PuzzleDefinition {

	private int rows;
	private int cols;
	private int[][] row_clues;
	private int[][] col_clues;

	public PuzzleDefinition(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		row_clues = new int[this.rows][(int) Math.ceil((double)this.cols/2)];
		col_clues = new int[this.cols][(int) Math.ceil((double)this.rows/2)];
		//System.out.println(row_clues[0].length);
		//System.out.println(col_clues[0].length);
	}
	
	public int[] getCluesForRow(int row) {
		
		return null;
	}
	
	public int[] getCluesForCol(int col) {
		
		return null;
	}
	
	public int[][] getCluesForRows() {
		return row_clues;
	}
	
	public int[][] getCluesForCols() {
		return col_clues;
	}
	
	public int getNumRows() {
		return rows;
	}

	public int getNumCols() {
		return cols;
	}
}
