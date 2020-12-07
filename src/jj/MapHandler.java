package jj;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jj.characters.CharacterType;
import jj.characters.Enemy;
import jj.characters.Player;
import jj.levels.Level;
import jj.levels.One;
import jj.multiplayer.Client;
import jj.tiles.Tile;

public class MapHandler extends JFrame implements Runnable, KeyListener, MouseListener {

	Container con = getContentPane();
	Thread t = new Thread(this);

	int runTime = 15;

	int currentMap, startPoint;

	int lastX = 0, lastY = 0;

	static Level map;

	boolean jump = false, stopJump = false;
	boolean fall = false, stopFall = false;
	boolean doHit = false, hasDoneHit = false, didHit = false;

	int hitTimer = 0;
	boolean left = false, right = false, up = false, down = false;
	boolean hasHit = false;
	boolean redoHolder = false;

	int jumpCount = 0, fallCount = 0;
	int type = 0, jType = 0;
	int intersected = 0, headHit = 0, hitHeight = 0, jumpNum = 0;

	Tile hit;
	PlayerSelector pSel;

	static ArrayList<Enemy> e = new ArrayList<Enemy>();

	// ArrayList<CharacterType> pCharacters=new ArrayList<CharacterType>();
	CharacterType current;
	static boolean playerScreen = false, playScreen = false;

	private static Player p;

	public MapHandler(int mapNumber) {
		super("Map " + mapNumber);
		con.setLayout(new FlowLayout());
		currentMap = mapNumber;
		pSel = new PlayerSelector();
		
		playerScreen = true;
		addKeyListener(this);
		addMouseListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		t.start();
	}

	public void moveStart(int x, int y) {
		// p.move(x);
		map.move(x);
		map.setY(250 - y);
		lastX = x;
		lastY = y;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(runTime);
				if (playScreen) {
					if (fall)
						fall();
					if (jump)
						doJump();

					p.hit(false);
					if (doHit) {
						p.startShoot();
						p.hit(true);
						doHit = false;
					}
					if (p.getDoShot())
						p.shoot();
					if (hitTimer > 0) {
						p.hit(false);
						hitTimer = 0;
						didHit = false;
					}
					testShot();
					testHit();
					sendKeys();
					if (jumpNum == 1)
						regulateJump();

					Client.update(p.getTheX(), p.getTheY(), p.getHit(), p.getDirection(), p.getChara());

				}

				repaint();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void kill() {
		p.setX(-100000);
		playScreen = false;
		playerScreen = true;
	}

	public void testShot() {
		for (int i = 0; i < e.size(); i++) {
			if (e.get(i).getDoShot()) {
				e.get(i).shoot();
			}
		}
	}

	public void regulateJump() {
		jump = true;
		fall = false;
		jumpNum = 2;
	}

	public void sendKeys() {

		if (left) {
			map.move(-1 * p.getChara().getSpeed());
			p.setDirection(true);
			checkFall();
			if (hasHit) {
				hasHit = false;
				for (Tile t : map.getList()) {
					if (p.getPlayerLeftRect().intersects(t.getRect()) || p.getPlayerRightRect().intersects(t.getRect()))
						hasHit = true;
				}
			}
			int temp = testWallHit();
			if (temp != 0)
				map.move(-1 * p.getChara().getSpeed());

			if (temp == 1) {
				repositionPlayer(1);
			} else if (temp == 2)
				repositionPlayer(2);
		}
		if (right) {
			map.move(p.getChara().getSpeed());
			p.setDirection(false);
			checkFall();
			if (hasHit) {
				hasHit = false;
				for (Tile t : map.getList()) {
					if (p.getPlayerLeftRect().intersects(t.getRect()) || p.getPlayerRightRect().intersects(t.getRect()))
						hasHit = true;
				}
			}
			int temp = testWallHit();
			if (temp == 1) {
				repositionPlayer(1);
			} else if (temp == 2)
				repositionPlayer(2);
		}
		if (up) {
			if (!fall && jumpNum == 0) {
				jumpNum = 1;
			}
		} else if (!up && jumpNum == 2 && isGrounded()) {
			jumpNum = 0;
		} else if (!up && jump) {
			stopJump = true;
		}
		if (down) {

		}
	}

	public void repositionPlayer(int side) {
		/*
		 * if(p.checkCorner()) { if(side==1) p.setX(hit.getX()+hit.getLength()); else
		 * if(side==2) p.setX(hit.getX()-p.getLength()); } else {
		 */
		// System.out.println("move "+hit.getX()+" "+hit.getLength()+" "+p.getX());
		if (side == 1)
			map.move((hit.getX() + hit.getLength() - p.getX()));// .setX(hit.getX()+hit.getLength());
		else if (side == 2)
			map.move((hit.getX() - p.getLength() - p.getX())); // }
	}

	public int testWallHit() {
		for (int i = 0; i < map.getList().size(); i++) {
			if (!hasHit) {
				if (map.getList().get(i).getRect().intersects(p.getPlayerLeftRect())
						&& !map.getList().get(i).getRect().intersects(p.getPlayerMiddleRect())) {
					hasHit = true;
					hit = map.getList().get(i);
					return 1;
				} else if (map.getList().get(i).getRect().intersects(p.getPlayerRightRect())
						&& !map.getList().get(i).getRect().intersects(p.getPlayerMiddleRect())) {
					hasHit = true;
					hit = map.getList().get(i);
					return 2;
				}
			} else {
				if ((map.getList().get(i).getRect().intersects(p.getPlayerLeftRect()))
						&& !map.getList().get(i).getRect().intersects(p.getPlayerTestTopRect())) {
					hasHit = true;
					hit = map.getList().get(i);
					return 1;
				} else if ((map.getList().get(i).getRect().intersects(p.getPlayerRightRect()))
						&& !map.getList().get(i).getRect().intersects(p.getPlayerTestTopRect())) {
					hasHit = true;
					hit = map.getList().get(i);
					return 2;
				}
			}
		}
		return 0;
	}

	public void testHit() {
		for (int i = 0; i < e.size(); i++) {
			if (e.get(i).getShot().intersects(p.getPlayerRect()) && e.get(i).getDoShot()) {
				kill();
				// run for a test
			}
		}
	}

	public void doJump() {

		if (stopJump)
			doStopJump();
		if (headHit() && jumpCount < p.getJumpLength() / 2) {
			map.setTileY(0);
			map.setY(p.getY() - (headHit + hitHeight));
			jump = false;
			fall = true;
			jumpCount = 0;
		} else if (isGrounded() && jumpCount > p.getJumpLength() / 2) {
			jump = false;
			jumpCount = 0;
			map.setTileY(0);
			map.setY((p.getY() + p.getHeight()) - intersected);
		}
		if (jump) {
			jump();
		}
	}

	public void jump() {
		redoHolder = true;
		jType = 2;
		jumpCount++;
		map.jump(jumpCount);

	}

	public void doStopJump() {
		int temp = p.getJumpLength() / 2;
		if (jumpCount < temp) {
			jumpCount = (p.getJumpLength() - jumpCount);
		}
		stopJump = false;
	}

	public boolean headHit() {
		for (int i = 0; i < map.getList().size(); i++) {
			if (map.getList().get(i).getRect().intersects(p.getPlayerTopRect())) {
				hitHeight = map.getList().get(i).getHeight();
				headHit = map.getList().get(i).getY();
				return true;
			}
		}
		return false;
	}

	public void fall() {

		fallCount++;
		map.fall(fallCount);
		if (isGrounded()) {
			fallCount = 0;
			fall = false;
			{
				map.setTileY(0);
				map.setY((p.getY() + p.getHeight()) - intersected);
			}
			type = 0;

		}
	}

	public void checkFall() {
		if (jump)
			return;
		else if (!isGrounded())
			fall = true;
	}

	public boolean isGrounded() {
		for (int i = 0; i < map.getList().size(); i++) {
			if (p.getChara().getSpeed() <= 4 && map.getList().get(i).getRect().intersects(p.getPlayerBeneathRect())
					&& (map.getList().get(i).getRect().intersects(p.getPlayerCenterRect())
							|| !(map.getList().get(i).getRect().intersects(p.getPlayerLeftRect())
									|| map.getList().get(i).getRect().intersects(p.getPlayerRightRect())))) {
				intersected = map.getList().get(i).getY();
				return true;
			} else if (p.getChara().getSpeed() > 4
					&& map.getList().get(i).getRect().intersects(p.getPlayerBeneathRect())
					&& ((map.getList().get(i).getRect().intersects(p.getPlayerTestLeftRect())
							&& map.getList().get(i).getRect().intersects(p.getPlayerRightRect()))
							|| (map.getList().get(i).getRect().intersects(p.getPlayerTestRightRect())
									&& map.getList().get(i).getRect().intersects(p.getPlayerLeftRect()))
							|| !(map.getList().get(i).getRect().intersects(p.getPlayerLeftRect())
									|| map.getList().get(i).getRect().intersects(p.getPlayerRightRect())))) {
				intersected = map.getList().get(i).getY();
				return true;
			}
		}
		return false;
	}

	public static boolean isPlayScreen() {
		return playScreen;
	}

	public static void addEnemy(String name, int x, int y, CharacterType chara) {
		e.add(new Enemy(name, (int) (map.getList().get(0).getX()) + x, map.getList().get(0).getY() + y, chara));
	}

	public static void adjustEnemy(String name, int x, int y, boolean hit, boolean tempDir, CharacterType chara) {
		for (int i = 0; i < e.size(); i++) {
			if (e.get(i).getName().equals(name))
				e.get(i).update((int) (map.getList().get(0).getX()) + x, map.getList().get(0).getY() + y, hit, tempDir,
						chara);
		}
	}

	public void paint(Graphics gr) {
		Image i = createImage(getSize().width, getSize().height);
		Graphics2D g = (Graphics2D) i.getGraphics();
		if (playScreen) {
			map.draw(g);
			p.draw(g);
			for (Enemy en : e)
				en.draw(g);
		} else if (playerScreen) {
			pSel.draw(g);
		}
		g.dispose();
		gr.drawImage(i, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void keyTyped(KeyEvent j) {

	}

	public void keyPressed(KeyEvent j) {

		// up
		if (j.getKeyCode() == 87) {
			up = true;
		}
		// down
		if (j.getKeyCode() == 83) {
			down = true;
		}
		// left
		if (j.getKeyCode() == 65) {
			left = true;

		}
		// right
		if (j.getKeyCode() == 68) {
			right = true;

		}
		// space
		if (j.getKeyCode() == 32 && !p.getDoShot()) {
			doHit = true;
			hasDoneHit = true;
		}

	}

	public void keyReleased(KeyEvent j) {

		// up
		if (j.getKeyCode() == 87) {
			up = false;
		}
		// down
		if (j.getKeyCode() == 83) {
			kill();
		}
		// left
		if (j.getKeyCode() == 65) {
			left = false;
		}
		// right
		if (j.getKeyCode() == 68) {
			right = false;
		}
		// space
		if (j.getKeyCode() == 32) {
			hasDoneHit = false;
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (playScreen) {
			for (int i = 0; i < map.getList().size(); i++) {
				if (map.getList().get(i).getRect().contains(e.getPoint()))
					System.out.println(map.getList().get(i).getStartX() + " " + map.getList().get(i).getStartY());
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		if (playerScreen) {
			ArrayList<Rectangle> temp = pSel.getPlayers();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).contains(e.getPoint())) {
					pSel.setSelector(temp.get(i).x - 2, temp.get(i).y - 2);
					current = pSel.getChara(i);
				}

			}
			if (pSel.getStart().contains(e.getPoint())) {
				if (current == null)
					JOptionPane.showMessageDialog(null, "Select a character");
				else {
					if (currentMap == 0)
						map = new One();
					playerScreen = false;
					Point tempP = map.getStartPoint();
					p = new Player(tempP.x, tempP.y, map, current);
					map.setPlayer(p);
					moveStart(tempP.x, tempP.y);
					playScreen = true;
					for (Enemy en : this.e)
						en.reset();
					Client.update(p.getTheX(), p.getTheY(), p.getHit(), p.getDirection(), p.getChara());
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
}
//to DO:
//make it where when you fall, then headhit, you don't bounce through
//MAYBE- fix the offset on jump and fall
