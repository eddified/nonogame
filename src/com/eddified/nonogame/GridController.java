package com.eddified.nonogame;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class GridController {

	private GridModel model;
	private TempGridModel tempModel;
	private GridView view;

	public GridController(int rows, int cols) {
		this.model = new GridModel(rows, cols);
		this.view = new GridView();
		this.view.setController(this);
		this.view.setModel(this.model);
	}

	public Component getView() {
		return this.view;
	}

	public void mousePressed(MouseEvent e, Point gridLocation) {
		// System.out.println("pressed " + col + ", " + row + " - "
		// + e.getButton());
		GridSquare newVal = GridSquare.NOT_SET;
		switch (e.getButton()) {
		case MouseEvent.BUTTON1: // left mouse button
			if (this.model.getValueAt(gridLocation) == GridSquare.FILLED) {
				newVal = GridSquare.UNKNOWN;
			} else {
				newVal = GridSquare.FILLED;
			}
			break;
		case MouseEvent.BUTTON3: // right mouse button
			if (this.model.getValueAt(gridLocation) == GridSquare.MARKED_EMPTY) {
				newVal = GridSquare.UNKNOWN;
			} else {
				newVal = GridSquare.MARKED_EMPTY;
			}
			break;
		}

		if (newVal != GridSquare.NOT_SET) {
			this.tempModel = new TempGridModel(this.model, gridLocation, newVal);
			this.model.setListener(null);
			this.view.setModel(this.tempModel);
			this.tempModel.setValueAt(gridLocation, newVal);
		}
	}

	public void mouseDragged(MouseEvent e, Point gridLocation) {
		this.tempModel.setValuesThrough(gridLocation);

	}

	public void mouseReleased(MouseEvent e, Point gridLocation) {
		this.tempModel.stamp();
		this.tempModel = null;
		this.model.setListener(null);
		this.view.setModel(this.model);
	}

	public JMenuBar getMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.getAccessibleContext().setAccessibleDescription(
				"Actions related to Files");
		bar.add(file);
		JMenuItem save = new JMenuItem("Save");
		save.setMnemonic(KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.META_MASK));
		save.getAccessibleContext().setAccessibleDescription("Save current Nonogram to xml file");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridController.this.save();
			}
		});
		file.add(save);
		
		return bar;
	}
	
	public void save() {
		System.out.println("TODO: save");
	}
}
