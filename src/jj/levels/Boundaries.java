package jj.levels;

import jj.InfoHolder;
import jj.PlayerInfo;

public class Boundaries {

	InfoHolder info;
	private static int theTop = 0;
	private int left, right, top, bottom;

	public Boundaries(int map) {
		info = new InfoHolder();
		left = info.getLeftCorner(map);
		right = info.getRightCorner(map);
		top = info.getTopCorner(map) - PlayerInfo.getHeight();
		bottom = info.getBottomCorner(map) - PlayerInfo.getHeight();
	}

	public void move(double speed) {
		right -= speed;
		left -= speed;
	}

	public void changeY(int t, int b) {
		theTop += t - top;
		top = t;
		bottom = b;
	}

	public int getTheTop() {
		return theTop;
	}

	public int getRight() {
		return right;
	}

	public int getLeft() {
		return left;
	}

	public int getTop() {
		return top;
	}

	public int getBottom() {
		return bottom;
	}
}
