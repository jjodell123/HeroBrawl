package jj.tiles;

public class Grid {

	private int x[];
	private int y[];

	public Grid(int length, int height) {
		x = new int[length];
		y = new int[height];
		for (int i = 0; i < x.length; i++)
			x[i] = (i * 20);
		for (int i = 0; i < y.length; i++)
			y[i] = (i * 20) + 11;
	}

	public int[] getX() {
		return x;
	}

	public int[] getY() {
		return y;
	}
}
