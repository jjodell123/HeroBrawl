package jj.tiles;

import java.awt.Color;
import java.awt.Graphics2D;

public class BaseGround extends Ground {

	public BaseGround(int x, int y, int length) {
		super(x, y, length);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(96, 96, 96));
		super.draw(g);
		g.setColor(Color.white);
		for (int i = 0; i < super.getLength(); i += 20) {
			g.drawLine((int) (super.getX()) + i, super.getY(), (int) (super.getX()) + i, (super.getY() + 20));
			g.drawLine((int) (super.getX()) + i, super.getY() + 20, (int) (super.getX()) + i + 20, (super.getY() + 20));
		}
	}
}
