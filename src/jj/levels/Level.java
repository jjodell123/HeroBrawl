package jj.levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import jj.InfoHolder;
import jj.characters.Player;
import jj.tiles.Tile;

public class Level {

	private int x[];
	private int y[];
	private Player player = null;
	private Boundaries bound;
	private int top, bottom;
	private int lastHolder = 0;
	private InfoHolder info;
	private ArrayList<Tile> list = new ArrayList<Tile>();
	private int[] startPoints, startHeight;

	public Level(int map, Boundaries bound) {
		this.bound = bound;
		info = new InfoHolder();
		x = info.getGrid(map).getX();
		y = info.getGrid(map).getY();

		startPoints = info.getStartPoints(map);
		startHeight = info.getStartHeights(map);

		top = (int) bound.getTop();
		bottom = (int) bound.getBottom();
	}

	public void setRec() {
		for (int i = 0; i < getList().size(); i++) {
			getList().get(i).setRec();
		}
	}

	public void move(double speed) {
		for (int i = 0; i < getList().size(); i++) {
			getList().get(i).move(speed);
		}
		// leftSide+=speed;
		bound.move(speed);
	}

	public void setY(int y) {
		for (int i = 0; i < getList().size(); i++) {
			getList().get(i).setTheY(y);
		}
	}

	public void fall(int time) {

		double holder = 0;
		if (time <= 30) {
			holder = (double) -100 / 900 * (time - 30) * (time + 30) - 100;
		} else {
			holder = (-6 * time) + 87;
		}
		for (int i = 0; i < list.size(); i++) {
			if (Tile.isAdjust()) {
				list.get(i).fall(holder - Tile.getAdjust());
				if (i == list.size() - 1) {
					Tile.changeAdjust();
				}
			} else
				list.get(i).fall(holder);
		}
		top += (int) holder - lastHolder;
		bottom += (int) holder - lastHolder;
		lastHolder = (int) holder;
		bound.changeY(top, bottom);
	}

	public void jump(int time) {
		double holder = 0;
		int jumpLength = player.getJumpLength();
		if (time <= jumpLength) {
			holder = (double) -140 / ((jumpLength / 2) * (jumpLength / 2)) * (time - jumpLength / 2)
					* (time - jumpLength / 2) + 140;
		} else {
			holder = (-4 * time) + (jumpLength * 4);
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).jump(holder);
		}
		top += (int) holder - lastHolder;
		bottom += (int) holder - lastHolder;
		lastHolder = (int) holder;
		bound.changeY(top, bottom);
	}

	public void setTileY(int y) {
		lastHolder = y;
		for (int i = 0; i < getList().size(); i++) {
			getList().get(i).setY(y);
		}
	}

	public void doAdjust(int y) {
		for (int i = 0; i < getList().size(); i++) {
			getList().get(i).adjust(y);
		}
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public void addToList(Tile t) {
		list.add(t);
	}

	public Point getStartPoint() {
		int temp = (int) (Math.random() * startPoints.length);
		{
			return new Point(startPoints[temp], startHeight[temp]);
		}
	}

	public int getHolder() {
		return lastHolder;
	}

	public int getStartY() {
		return list.get(0).getY();
	}

	public ArrayList<Tile> getList() {
		return list;
	}

	public Boundaries getBounds() {
		return bound;
	}

	public int[] getX() {
		return x;
	}

	public int[] getY() {
		return y;
	}

	public void draw(Graphics2D g) {
		for (Tile t : list)
			t.draw(g);
		g.drawLine(0, top, 1000, top);
		g.drawLine(0, bottom, 1000, bottom);

	}

}
