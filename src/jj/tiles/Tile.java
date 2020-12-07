package jj.tiles;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tile {

	private int y, length, height, lastHolder;
	private double x;
	private static int returnHolder, adjuster = 0;
	private static boolean adjusting = false;
	private int startX, startY;
	private Rectangle tile;

	public Tile(int x, int y, int length, boolean isGround) {
		this.x = x;
		this.y = y;
		startX = x;
		startY = y;
		if (isGround) {
			this.length = length * 20;
			this.height = 20;
			tile = new Rectangle(x, y, this.length, 20);
		} else {
			this.length = 20;
			this.height = length * 20;
			tile = new Rectangle(x, y, 20, length * 20);
		}
	}

	public void move(double speed) {
		x -= speed;
		tile.setLocation((int) x, y);
	}

	public void setTheY(int height) {
		y += height;
		tile.setLocation((int) x, y);
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public void fall(double holder) {
		y += (int) holder - lastHolder;
		if (isAdjust()) {
			y += getAdjust();

		}
		setY((int) holder);
	}

	public void jump(double holder) {
		y += (int) holder - lastHolder;
		if (isAdjust()) {
			y += getAdjust();
			changeAdjust();
		}
		setY((int) holder);

	}

	public void setRec() {
		tile.setLocation((int) x, y);
	}

	public void setY(int newY) {
		lastHolder = newY;
		returnHolder = newY;
		tile.setLocation((int) x, y);
	}

	public static void changeAdjust() {
		adjusting = !adjusting;
	}

	public static boolean isAdjust() {
		return adjusting;
	}

	public static int getAdjust() {
		return adjuster;
	}

	public void adjust(int newY) {
		adjuster = newY;
		y -= newY;
	}

	public double getX() {
		return x;
	}

	public static int getLastHolder() {
		return returnHolder;
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	public int getY() {
		return y;
	}

	public Rectangle getRect() {
		return tile;
	}

	public void draw(Graphics2D g) {
		g.fill(tile);
	}
}
