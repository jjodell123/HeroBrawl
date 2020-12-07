package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterHelana extends CharacterType {

	public CharacterHelana() {
		super("Helana Upshaw", 2.8, 70, 10, 60);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(102, 0, 204));
	}

}
