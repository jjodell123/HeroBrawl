package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterPhillips extends CharacterType {

	public CharacterPhillips() {
		super("Jon Phillips", 2.0, 70, 10, 70);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(204, 0, 0));
	}
}
