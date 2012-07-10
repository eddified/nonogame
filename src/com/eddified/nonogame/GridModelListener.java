package com.eddified.nonogame;

public interface GridModelListener {

	public void notifyChanged(int col, int row) ;

	public void notifyChanged(int x1, int y1, int x2, int y2);

}
