package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterLawton extends CharacterType {

	public CharacterLawton() {
		super("Lawton ?", 2.6, 60, 10, 60);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(255, 153, 204));
	}

}
