package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterEathan extends CharacterType {

	public CharacterEathan() {
		super("Eathan Grantham", 2.9, 88, 14, 90);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(255, 128, 0));
	}

}
