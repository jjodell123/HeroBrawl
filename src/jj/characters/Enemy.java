package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Enemy {

	private int y, length, height;
	private double x;
	private CharacterType chara;
	private boolean left = false;
	private Rectangle shot;
	private boolean doShot, shotDirection;
	private int shotX, shotY, startX;
	private Rectangle rect, fist;
	private String name;

	public Enemy(String name, int x, int y, CharacterType chara) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.chara = chara;
		System.out.println(this.chara.getName());
		length = this.chara.getLength();

		height = this.chara.getHeight();
		rect = new Rectangle(x, y, length, height);
		fist = new Rectangle((int) x - 15, y + 30, 0, 0);
		shot = new Rectangle((int) x + (length / 2) - 3, y + 30, 6, 6);
	}

	public void setRect() {
		rect.setLocation((int) x, y);
		shot.setLocation(shotX, shotY);
	}

	public void move(double speed) {
		x -= speed;
		setRect();
	}

	public void update(int x, int y, boolean hit, boolean left, CharacterType chara) {
		// this.chara=chara;
		this.x = x;
		this.y = y;
		this.left = left;
		if (chara != this.chara) {
			this.chara = chara;
			this.height = chara.getHeight();
			this.length = chara.getLength();
		}
		if (left) {
			fist.setLocation((int) x - 15, y + 30);
		} else {
			fist.setLocation((int) x + length, y + 30);
		}
		if (hit) {
			shotDirection = left;
			shotX = startX = x + (chara.getLength() / 2) - 3;
			shotY = y + 30;
			doShot = true;
		} else if (!doShot) {
			shotX = x + (chara.getLength() / 2) - 3;
			shotY = y + 30;
		}
		setRect();
	}

	public String getName() {
		return name;
	}

	public CharacterType getChara() {
		return chara;
	}

	public void shoot() {
		if (shotDirection) {
			shotX -= 7;
		} else {
			shotX += 7;
		}
		if (Math.abs(startX - shotX) > 500) {
			shotX = (int) x;
			doShot = false;
		}
		shot.setLocation(shotX, shotY);
	}

	public void reset() {
		shotX = (int) x;
		doShot = false;
	}

	public boolean getDoShot() {
		return doShot;
	}

	public Rectangle getShot() {
		return shot;
	}

	public Rectangle getFist() {
		return fist;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fill(shot);
		chara.draw(g);
		g.fillOval((int) x, y, length, height);

		g.fill(fist);

		if (left) {
			g.fillRect((int) x - 10, y + 20, 12, 5);
			g.fillRect((int) x - 6, y + 25, 5, 5);
			if (chara.getName().equals("JJ Bishop"))
				g.fillOval((int) x - 5, y + 30, 12, 20);
		} else {
			g.fillRect((int) x + length - 2, y + 20, 13, 5);
			g.fillRect((int) x + length + 1, y + 25, 5, 5);
			if (chara.getName().equals("JJ Bishop"))
				g.fillOval((int) x + length - 7, y + 30, 12, 20);
		}
	}
}
