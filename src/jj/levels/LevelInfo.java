package jj.levels;

import jj.tiles.Grid;

public class LevelInfo {

	int map;
	int startPoint[] = new int[3];
	int startHeight[] = new int[3];
	Grid t;
	int x[], y[];

	public LevelInfo(int map, int startPoint[], int startHeight[], int length, int height) {
		this.map = map;
		this.startPoint = startPoint;
		this.startHeight = startHeight;
		t = new Grid(length, height);
		x = t.getX();
		y = t.getY();
	}

	public int getLeftCorner() {
		return x[0] + 250;
	}

	public int getRightCorner() {
		return x[x.length - 1] - 250;
	}

	public int getTopCorner() {
		return y[0] + 250;
	}

	public int getBottomCorner() {
		return y[y.length - 1] - 250;
	}

	public int getMap() {
		return map;
	}

	public int[] getStartPoint() {
		return startPoint;
	}

	public int[] getStartHeight() {
		return startHeight;
	}

	public Grid getGrid() {
		return t;
	}

}
