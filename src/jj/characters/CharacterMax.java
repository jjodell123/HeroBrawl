package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterMax extends CharacterType {

	public CharacterMax() {
		super("Max ?", 2.6, 68, 10, 60);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(255, 255, 51));
	}

}
