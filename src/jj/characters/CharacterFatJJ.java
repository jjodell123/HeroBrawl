package jj.characters;

import java.awt.Color;
import java.awt.Graphics2D;

public class CharacterFatJJ extends CharacterType{

	public CharacterFatJJ()
	{
		super("JJ Bishop",2.0,70,20,100);
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(new Color(0,225,225));
	}

}
