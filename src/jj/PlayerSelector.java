package jj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import jj.characters.Character;
import jj.characters.CharacterType;

public class PlayerSelector {

	private static ArrayList<CharacterType> allPlayers = new ArrayList<CharacterType>();
	private static Rectangle selector, start;
	private static ArrayList<Rectangle> listRect = new ArrayList<Rectangle>();

	public PlayerSelector() {
		selector = new Rectangle(-50, -50, 39, 59);
		start = new Rectangle(200, 400, 100, 40);
		allPlayers = Character.getAllCharas();
		for (int i = 0; i < allPlayers.size(); i++) {
			if ((i * 40) + 30 < 460) {

				listRect.add(new Rectangle((i * 40) + 30, 40, 35, 55));
			} else
				listRect.add(new Rectangle((i * 40) + 30 - 430, 140, 35, 55));
		}
	}

	public void setSelector(int x, int y) {
		selector.setLocation(x, y);
	}

	public Rectangle getStart() {
		return start;
	}

	public ArrayList<Rectangle> getPlayers() {
		return listRect;
	}

	public CharacterType getChara(int i) {
		return allPlayers.get(i);
	}

	public void draw(Graphics2D g) {

		g.setColor(Color.blue);
		g.fill(selector);
		g.setColor(Color.black);
		for (int i = 0; i < allPlayers.size(); i++) {
			allPlayers.get(i).draw(g);
			if ((i * 40) + 30 < 460) {
				g.fillRect((i * 40) + 30, 40, 35, 55);
				if (i % 2 == 0)
					g.drawString(allPlayers.get(i).getFirstName(), (i * 40) + 40, 130);
				else
					g.drawString(allPlayers.get(i).getFirstName(), (i * 40) + 30, 110);
			} else {
				g.fillRect((i * 40) + 30 - 430, 140, 35, 55);
				if (i % 2 == 0)
					g.drawString(allPlayers.get(i).getFirstName(), (i * 40) + 40 - 430, 230);
				else
					g.drawString(allPlayers.get(i).getFirstName(), (i * 40) + 30 - 430, 210);
			}
		}
		g.setColor(Color.blue);
		g.fill(start);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 25, 25));
		g.drawString("START", 210, 430);

	}
}
