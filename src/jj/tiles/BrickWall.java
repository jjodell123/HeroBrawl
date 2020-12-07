package jj.tiles;

import java.awt.Color;
import java.awt.Graphics2D;

public class BrickWall extends Wall {
	public BrickWall(int x, int y, int height) {
		super(x, y, height);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(153, 0, 0));
		super.draw(g);
		g.setColor(Color.white);
		for (int i = 0; i < super.getHeight(); i += 20) {
			g.drawLine((int) (super.getX()) + 10, super.getY() + i, (int) (super.getX()) + 10, (super.getY() + i + 10));
			g.drawLine((int) (super.getX()), super.getY() + i, (int) (super.getX()) + 20, super.getY() + i);
			g.drawLine((int) (super.getX()), super.getY() + i + 10, (int) (super.getX()) + 20, super.getY() + i + 10);
			g.drawLine((int) (super.getX()), super.getY() + i + 10, (int) (super.getX()), super.getY() + i + 20);
		}
	}
}
