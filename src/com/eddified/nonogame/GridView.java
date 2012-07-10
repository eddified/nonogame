package com.eddified.nonogame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class GridView extends JComponent implements MouseListener, MouseMotionListener,
		GridModelListener {
	private static final long serialVersionUID = 1L;

	private static final int GRID_SQUARE_ONE_SIDE_PX = 18;
	private static final float GRID_LINE_UNIT_THICKNESS_PX = 1.0f;
	private static final float GRID_LINE_GROUP_EDGE_THICKNESS_PX = 1.7f;
	private static final int GRID_ORIGIN = 0;
	private static final float GRID_LINE_OUTER_BORDER_THICKNESS_PX = 3f;
	private static final int GRID_SQUARE_GROUP_SIZE = 5;

	private GridController controller;
	private GridModel model;

	public GridView() {
		super();
		setOpaque(true);
		setVisible(true);
		setBackground(Color.WHITE);

		// this.setPreferredSize(0, 0);
	}

	public void setModel(GridModel m) {
		this.model = m;
		this.setPreferredSize(new Dimension(this.model.getNumRows()
				* GRID_SQUARE_ONE_SIDE_PX + 1, this.model.getNumCols()
				* GRID_SQUARE_ONE_SIDE_PX + 1));
		this.model.setListener(this);
	}

	public void setController(GridController c) {
		this.controller = c;
		if (this.getMouseListeners().length == 0) {
			if (this.controller != null) {
				this.addMouseListener(this);
				this.addMouseMotionListener(this);
			}
		} else {
			if (this.controller == null) {
				this.removeMouseListener(this);
				this.removeMouseMotionListener(this);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int rows = this.model.getNumRows();
		int cols = this.model.getNumCols();
		int x_extent = cols * GRID_SQUARE_ONE_SIDE_PX;
		int y_extent = rows * GRID_SQUARE_ONE_SIDE_PX;

		Graphics2D g2d = (Graphics2D) g;
		g2d.setBackground(this.getBackground());
		// clear everything, the background shows through
		g2d.clearRect(GRID_ORIGIN, GRID_ORIGIN, this.getWidth(),
				this.getHeight());

		// Draw outer grid border
		g2d.setStroke(new BasicStroke(GRID_LINE_OUTER_BORDER_THICKNESS_PX));
		g2d.setColor(Color.BLACK);
		// rows * GRID_SQUARE_ONE_SIDE_PX really should be equal to
		// this.getWidth(), otherwise we've got problems
		g2d.drawRect(GRID_ORIGIN, GRID_ORIGIN, x_extent, y_extent);

		// draw lines for every row/col, except group lines
		g2d.setStroke(new BasicStroke(GRID_LINE_UNIT_THICKNESS_PX));
		drawUnitLinesInOneDirection(g2d, true, true, rows, x_extent);
		drawUnitLinesInOneDirection(g2d, false, true, cols, y_extent);
		// draw group lines
		g2d.setStroke(new BasicStroke(GRID_LINE_GROUP_EDGE_THICKNESS_PX));
		drawUnitLinesInOneDirection(g2d, true, false, rows, x_extent);
		drawUnitLinesInOneDirection(g2d, false, false, cols, y_extent);

		g2d.setStroke(new BasicStroke(GRID_LINE_UNIT_THICKNESS_PX));
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				GridSquare val = this.model.getValueAt(i, j);
				int x1 = i * GRID_SQUARE_ONE_SIDE_PX;
				int y1 = j * GRID_SQUARE_ONE_SIDE_PX;
				int width = GRID_SQUARE_ONE_SIDE_PX;
				int height = GRID_SQUARE_ONE_SIDE_PX;
				int x2 = x1 + width;
				int y2 = y1 + height;
				switch (val) {
				case FILLED:
					g2d.fillRect(x1, y1, width, height);
					break;
				case MARKED_EMPTY:
					g2d.drawLine(x1, y1, x2, y2);
					g2d.drawLine(x2, y1, x1, y2);
				}
			}
		}
		g2d.dispose();
	}

	/**
	 * @param int numLines The number of rows/columns, NOT the number of grid
	 *        lines
	 */
	private void drawUnitLinesInOneDirection(Graphics2D g2d, boolean x,
			boolean unit, int numLines, int extent) {
		for (int i = 1; i < numLines; i++) {
			if (unit && i % GRID_SQUARE_GROUP_SIZE == 0) {
				// don't draw every GRID_SQUARE_GROUP_SIZEth line, those get
				// special treatment
				continue;
			} else if (!unit && i % GRID_SQUARE_GROUP_SIZE != 0) {
				continue;
			}
			int val = i * GRID_SQUARE_ONE_SIDE_PX;
			if (x) {
				// draw horizontal lines every one square
				g2d.drawLine(GRID_ORIGIN, val, extent, val);
			} else {
				// draw vertical lines
				g2d.drawLine(val, GRID_ORIGIN, val, extent);
			}
		}

	}
	
	private Point getGridLocationAtPixel(Point p) {
		return new Point(p.x / GRID_SQUARE_ONE_SIDE_PX, p.y / GRID_SQUARE_ONE_SIDE_PX);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("mouse clicked");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mouse pressed");
		this.controller.mousePressed(e, this.getGridLocationAtPixel(e.getPoint()));
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouse released");
		this.controller.mouseReleased(e, this.getGridLocationAtPixel(e.getPoint()));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.controller.mouseDragged(e, this.getGridLocationAtPixel(e.getPoint()));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	@Override
	public void notifyChanged(int col, int row) {
		repaint();
	}
	
	@Override
	public void notifyChanged(int x1, int y1, int x2, int y2) {
		repaint();
	}
}
