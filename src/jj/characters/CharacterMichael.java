package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterMichael extends CharacterType {

	public CharacterMichael() {
		super("Michael Pi", 2.8, 70, 10, 80);
	}

	public void draw(Graphics2D g) {
		g.setColor(new Color(204, 102, 0));
	}
}
