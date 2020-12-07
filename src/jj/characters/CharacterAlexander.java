package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterAlexander extends CharacterType {

	public CharacterAlexander() {
		super("Alexander Puckaber", 2.8, 60, 10, 70);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(224, 224, 224));
	}

}
