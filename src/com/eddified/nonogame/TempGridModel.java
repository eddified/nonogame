package com.eddified.nonogame;

import java.awt.Point;

public class TempGridModel extends GridModel {
	
	private GridModel model;
	private Point origGridLocation;
	private GridSquare origValue;

	public TempGridModel(GridModel model, Point origGridLocation, GridSquare origValue) {
		super(model.getNumCols(), model.getNumRows());
		this.clear();
		this.model = model;
		this.origGridLocation = origGridLocation;
		this.origValue = origValue;
	}
	
	public GridSquare getValueAt(int col, int row) {
		GridSquare val = super.getValueAt(col, row);
		if (val == GridSquare.NOT_SET) {
			return this.model.getValueAt(col, row);
		}
		return val;
	}
	
	public void clear() {
		this.setValuesAt(0, 0, getNumCols()-1, getNumRows()-1, GridSquare.NOT_SET);
	}
	
	public GridModel stamp() {
		int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = -1, maxY = -1;
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (this.values[i][j] != GridSquare.NOT_SET) {
					this.model.values[i][j] = this.values[i][j];
					minX = Math.min(minX, i);
					minY = Math.min(minY, j);
					maxX = Math.max(maxX, i);
					maxY = Math.max(maxY, j);
				}
			}
		}
		if (maxX != -1)
			this.notifyChanged(minX, minY, maxX, maxY);
		return this.model;
	}

	public void setValuesThrough(Point gridLocation) {
		this.clear();
		this.setValuesAt(this.origGridLocation, gridLocation, this.origValue);
	}
}
