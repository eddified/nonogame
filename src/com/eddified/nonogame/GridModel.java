package com.eddified.nonogame;

import java.awt.Point;

public class GridModel {
	
	protected int cols;
	protected int rows;
	protected GridSquare[][] values;
	protected GridModelListener listener;

	public GridModel(int numCols, int numRows) {
		this.cols = numCols;
		this.rows = numRows;
		
		this.values = new GridSquare[this.cols][this.rows];
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				this.values[i][j] = GridSquare.UNKNOWN;
			}
		}
	}

	public GridSquare getValueAt(int col, int row) {
		return this.values[col][row];
	}
	public GridSquare getValueAt(Point gridLocation) {
		return this.getValueAt(gridLocation.x, gridLocation.y);
	}
	
	public void setValueAt(int col, int row, GridSquare val) {
		this.values[col][row] = val;
		this.notifyChanged(col, row);
	}

	public void setValueAt(Point gridLocation, GridSquare val) {
		this.setValueAt(gridLocation.x, gridLocation.y, val);
	}
	
	private void notifyChanged(int col, int row) {
		if (this.listener != null) {
			this.listener.notifyChanged(col, row);
		}
	}
	
	protected void notifyChanged(int x1, int y1, int x2, int y2) {
		if (this.listener != null) {
			this.listener.notifyChanged(x1, y1, x2, y2);
		}
	}
	
	public void setValuesAt(Point p1, Point p2, GridSquare val) {
		setValuesAt(p1.x, p1.y, p2.x, p2.y, val);
	}
	public void setValuesAt(int x1, int y1, int x2, int y2, GridSquare val) {
		if (x2 < x1) {
			int tmp = x2;
			x2 = x1;
			x1 = tmp;
		}
		if (y2 < y1) {
			int tmp = y2;
			y2 = y1;
			y1 = tmp;
		}
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				this.values[i][j] = val;
			}
		}
		this.notifyChanged(x1, y1, x2, y2);
	}

	public int getNumRows() {
		return rows;
	}

	public int getNumCols() {
		return cols;
	}
	
	public void setListener(GridModelListener l) {
		this.listener = l;
	}

}
