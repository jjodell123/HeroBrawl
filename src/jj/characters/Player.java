package jj.characters;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import jj.levels.Boundaries;
import jj.levels.Level;

public class Player {

	private int x, y, length, height, holderY, time;
	private CharacterType chara;
	private Boundaries bound;
	private Rectangle playerRect, playerBeneathRect, playerTopRect, playerRightRect, playerLeftRect, playerMiddleRect,
			playerCenterRect, playerTestLeftRect, playerTestRightRect, playerTestTopRect;
	private Rectangle fist;
	private int jumpLength;
	private double lastHolder;
	private boolean left = false;
	private boolean hit = false;

	private Rectangle shot;
	private boolean doShot;
	private int shotX, shotY, startX, lastShotX, lastShotY;
	private boolean shootLeft = false;

	private Level map;

	public Player(int xx, int yy, Level map, CharacterType chara) {
		this.chara = chara;
		this.map = map;

		height = chara.getHeight();
		length = chara.getLength();
		this.x = 250 - (length / 2);
		this.y = 250 - height;
		startX = (int) x + (length / 2) - 3;

		shotX = (int) x + (length / 2) - 3;
		shotY = this.y + 20;
		time = 0;
		holderY = y;

		jumpLength = chara.getJumpLength();
		lastHolder = 0;
		playerRect = new Rectangle(x, y, length, height);
		playerBeneathRect = new Rectangle(x, y + height, length, 6);
		playerTopRect = new Rectangle(x, y - 6, length, 6);
		playerLeftRect = new Rectangle(x - (int) chara.getSpeed(), y, 1 + (int) chara.getSpeed(), height);
		playerRightRect = new Rectangle(x + length - 1, y, 1 + (int) chara.getSpeed(), height);
		playerMiddleRect = new Rectangle(x + 3, y - 6, length - 6, height + 12);
		playerCenterRect = new Rectangle(x + length / 2 - 1, y + 10, 2, height - 4);
		playerTestTopRect = new Rectangle(x + length / 2 - 1, y - 6, 2, 6);
		playerTestLeftRect = new Rectangle(x, y + 10, 2, height - 4);
		playerTestRightRect = new Rectangle(x + length - 2, y + 10, 2, height - 4);
		fist = new Rectangle(x - 15, y + 30, 0, 0);
		shot = new Rectangle(shotX, shotY, 6, 6);
	}

	public void move(int speed) {
		x += speed;
	}

	public void fall(int time) {
		double holder = 0;
		if (time <= 30) {
			holder = (double) -100 / 900 * (time - 30) * (time + 30) - 100;
		} else {
			holder = (-6 * time) + 87;
		}
		y = holderY - (int) holder;
		lastHolder = holder;
	}

	public int getNextHolder() {
		double nextHolder;
		if (time <= jumpLength) {
			nextHolder = (double) -140 / ((jumpLength / 2) * (jumpLength / 2)) * (time - jumpLength / 2)
					* (time - jumpLength / 2) + 140;
		} else {
			nextHolder = (-4 * time) + (jumpLength * 4);
		}
		return (int) nextHolder;
	}

	public int getHolder() {
		return (int) lastHolder;
	}

	public int getTheHolder() {
		return holderY;
	}

	public void jump(int time) {
		this.time = time;
		double holder = 0;
		if (time <= jumpLength) {
			holder = (double) -140 / ((jumpLength / 2) * (jumpLength / 2)) * (time - jumpLength / 2)
					* (time - jumpLength / 2) + 140;
		} else {
			holder = (-4 * time) + (jumpLength * 4);
		}
		y = holderY - (int) holder;
		lastHolder = holder;
	}

	public void startShoot() {
		doShot = true;
		lastShotX = getTheX();
		lastShotY = getTheY();
		shootLeft = getDirection();
	}

	public void shoot() {
		if (shootLeft) {
			shotX -= 7;
		} else {
			shotX += 7;
		}
		shotX += lastShotX - getTheX();
		startX += lastShotX - getTheX();
		shotY += lastShotY - getTheY();
		lastShotX = getTheX();
		lastShotY = getTheY();
		if (Math.abs(startX - shotX) > 500) {
			startX = (int) x + (length / 2) - 3;
			shotX = (int) x + (length / 2) - 3;
			shotY = y + 20;
			doShot = false;
		}
		shot.setLocation(shotX, shotY);
	}

	public boolean getDoShot() {
		return doShot;
	}

	public Rectangle getShot() {
		return shot;
	}

	public void setDirection(boolean left) {
		this.left = left;
		if (left) {
			fist.setLocation((int) x - 15, y + 30);
		} else {
			fist.setLocation((int) x + length, y + 30);
		}

	}

	public boolean getDirection() {
		return left;
	}

	public void hit(boolean h) {
		hit = h;
	}

	public boolean getHit() {
		return hit;
	}

	public void adjust(int y) {
		this.y -= y;
		holderY -= y;
		lastHolder -= y;
	}

	public void setY() {
	}

	public void setY(int y) {
		this.y = y - height;
		holderY = this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getLength() {
		return length;
	}

	public void setHolder(int y) {
		holderY = y;
	}

	public int getJumpLength() {
		return jumpLength;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public CharacterType getChara() {
		return chara;
	}

	public Rectangle getPlayerRect() {
		return playerRect;
	}

	public Rectangle getPlayerBeneathRect() {
		return playerBeneathRect;
	}

	public Rectangle getPlayerTopRect() {
		return playerTopRect;
	}

	public Rectangle getPlayerLeftRect() {
		return playerLeftRect;
	}

	public Rectangle getPlayerRightRect() {
		return playerRightRect;
	}

	public Rectangle getPlayerMiddleRect() {
		return playerMiddleRect;
	}

	public Rectangle getPlayerCenterRect() {
		return playerCenterRect;
	}

	public Rectangle getPlayerTestLeftRect() {
		return playerTestLeftRect;
	}

	public Rectangle getPlayerTestRightRect() {
		return playerTestRightRect;
	}

	public Rectangle getPlayerTestTopRect() {
		return playerTestTopRect;
	}

	public int getHeight() {
		return height;
	}

	public int getTheX() {
		return x - (int) (map.getList().get(0).getX());
	}

	public int getTheY() {
		return y - map.getList().get(0).getY();
	}

	public boolean checkCorner() {
		if (x <= bound.getLeft()) {
			return true;
		} else if (x >= bound.getRight()) {
			return true;
		}
		return false;
	}

	public boolean checkYBounds(int direction) {
		if (direction == 0) {
			if (y > bound.getTop() && y < bound.getBottom()) {
				return true;
			}
		} else {
			if (y >= bound.getTop() && y <= bound.getBottom()) {
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics2D g) {
		chara.draw(g);
		g.fill(shot);
		g.fillOval(x, y, length, height);
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
