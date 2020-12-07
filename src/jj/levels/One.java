package jj.levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import jj.InfoHolder;
import jj.characters.Player;
import jj.tiles.BaseCeiling;
import jj.tiles.BaseGround;
import jj.tiles.BrickGround;
import jj.tiles.BrickWall;
import jj.tiles.Tile;

public class One extends Level {

	static private int x[];
	static private int y[];
	static private Boundaries bound = new Boundaries(0);
	static private InfoHolder info;

	public One() {
		super(0, bound);
		info = new InfoHolder();
		x = info.getGrid(0).getX();
		y = info.getGrid(0).getY();
		super.addToList(new BaseCeiling(x[0], y[0], 200));
		super.addToList(new BrickGround(x[0], y[y.length - 1], x.length));
		super.addToList(new BrickWall(x[0], y[0], y.length));
		super.addToList(new BrickWall(x[x.length - 1], y[0], y.length));

		super.addToList(new BrickGround(x[0], y[15], 20));
		super.addToList(new BaseGround(x[20], y[16], 20));
		super.addToList(new BrickGround(x[15], y[10], 20));
		super.addToList(new BrickGround(x[18], y[23], 40));
		super.addToList(new BaseGround(x[1], y[30], 15));
		super.addToList(new BaseGround(x[16], y[31], 15));
		super.addToList(new BaseGround(x[31], y[32], 15));
		super.addToList(new BaseGround(x[46], y[33], 15));
		super.addToList(new BrickGround(x[58], y[19], 20));
		super.addToList(new BrickGround(x[62], y[27], 20));
		super.addToList(new BaseGround(x[70], y[14], 25));
		super.addToList(new BaseGround(x[85], y[20], 15));
		super.addToList(new BrickGround(x[85], y[33], 2));
		super.addToList(new BrickWall(x[86], y[22], 11));
		super.addToList(new BrickGround(x[95], y[8], 18));
		super.addToList(new BrickWall(x[94], y[34], 6));
		super.addToList(new BaseGround(x[99], y[27], 30));
		super.addToList(new BaseGround(x[129], y[28], 15));
		super.addToList(new BaseGround(x[144], y[27], 10));
		super.addToList(new BrickGround(x[114], y[15], 15));
		super.addToList(new BrickGround(x[135], y[20], 15));
		super.addToList(new BrickGround(x[135], y[9], 45));
		super.addToList(new BaseGround(x[x.length - 21], y[16], 20));
		super.addToList(new BaseGround(x[160], y[19], 18));
		super.addToList(new BrickGround(x[180], y[34], 18));
		super.addToList(new BrickWall(x[180], y[30], 4));
		super.addToList(new BrickGround(x[160], y[30], 20));

	}

	public void move(int speed) {
		super.move(speed);
	}

	public void fall(int time) {
		super.fall(time);
	}

	public void setTileY(int y) {
		super.setTileY(y);
	}

	public void setPlayer(Player p) {
		super.setPlayer(p);
	}

	public void jump(int speed) {
		super.jump(speed);
	}

	public Boundaries getBounds() {
		return super.getBounds();
	}

	public ArrayList<Tile> getList() {
		return super.getList();
	}

	public void draw(Graphics2D g) {
		for (Tile t : super.getList())
			t.draw(g);
	}
}