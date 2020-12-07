package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterWalsh extends CharacterType {

	public CharacterWalsh() {
		super("Kevin Walsh", 2.8, 85, 10, 80);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(0, 255, 0));
	}

}
