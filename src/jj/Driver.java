package jj;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jj.multiplayer.ServerStarter;

public class Driver extends JFrame implements Runnable, MouseListener {
	Container con = getContentPane();
	Thread t = new Thread(this);

	private static boolean startMap = false, homeScreen = true, startServer = false, hasStarted = false;
	String name = "jj";
	private Rectangle startRect, nameRect;
	InfoHolder info;
	Initializer init;

	private boolean nameRectClicked = false, startRectClicked = false;

	public Driver() {
		// Sets name to Hero Brawl
		super("Hero Brawl");

		// Adds listeners and sets layout
		addMouseListener(this);
		con.setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		info = new InfoHolder();
		init = new Initializer();

		// Buttons for the page
		startRect = new Rectangle(150, 350, 200, 40);
		nameRect = new Rectangle(50, 75, 80, 25);

		// Starts the thread
		t.start();
	}

	public void run() {
		try {
			while (true) {
				// Delay between frames, make it based on computer in the future
				Thread.sleep(67);

				// Starts a server
				if (startServer && !hasStarted) {
					hasStarted = true;
					new ServerStarter(name);
					startServer = false;

				}
				// Begins game and closes load screen
				if (startMap) {
					homeScreen = false;
					MapHandler handler = new MapHandler(info.getMap());
					handler.setSize(500, 500);

					handler.setVisible(true);
					startMap = false;
					this.dispose();

				}
				repaint();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Draws page
	public void paint(Graphics gr) {
		Image i = createImage(getSize().width, getSize().height);
		Graphics2D g = (Graphics2D) i.getGraphics();
		if (homeScreen) {
			g.setColor(Color.blue);
			g.fillRect(0, 0, 500, 500);

			g.setColor(Color.red);

			g.fill(startRect);
			g.fill(nameRect);
			g.setColor(Color.white);
			// g.drawLine(p.x, p.y, 100, p.y);
			g.setFont(new Font("Arial", 20, 20));
			g.drawString("Start/Load Server", 175, 375);
		}
		g.dispose();
		gr.drawImage(i, 0, 0, this);
	}

	public static void main(String[] args) {
		Driver frame = new Driver();
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (nameRect.contains(e.getPoint())) {
			nameRectClicked = true;
		}
		if (startRect.contains(e.getPoint()) && !name.equals("")) {
			startRectClicked = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (nameRect.contains(e.getPoint()) && nameRectClicked) {
			try {
				name = JOptionPane.showInputDialog(null, "Enter an in game name");
			} catch (NullPointerException err) {
			}
		} else if (startRect.contains(e.getPoint()) && !name.equals("") && startRectClicked) {
			startServer = true;
		} else if (startRect.contains(e.getPoint()) && startRectClicked) {
			JOptionPane.showMessageDialog(null, "Please select a name");
		} else {
			startRectClicked = false;
			nameRectClicked = false;
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public static void startMap() {
		startMap = true;
	}

}
