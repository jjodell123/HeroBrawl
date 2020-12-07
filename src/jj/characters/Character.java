package jj.characters;

import java.util.ArrayList;

public class Character {

	public static CharacterType flash, starter;
	public static ArrayList<CharacterType> charas = new ArrayList<CharacterType>();
	private static CharacterType currentChara;

	public Character(CharacterType thisChara) {
		currentChara = thisChara;
	}

	public static void loadCharacters() {
		charas.add(new CharacterJJ());
		charas.add(new CharacterBrennon());
		charas.add(new CharacterAlex());
		charas.add(new CharacterDeclan());
		charas.add(new CharacterPhillips());
		charas.add(new CharacterMichael());
		charas.add(new CharacterAlexander());
		charas.add(new CharacterFatJJ());
		charas.add(new CharacterWalsh());
		charas.add(new CharacterEathan());
		charas.add(new CharacterCrowley());
		charas.add(new CharacterHelana());
		charas.add(new CharacterMax());
		charas.add(new CharacterLawton());
	}

	public static CharacterType parseCharacterType(String s) {
		for (int i = 0; i < charas.size(); i++) {
			if (charas.get(i).getName().equals(s))
				return charas.get(i);
		}
		return null;
	}

	public static ArrayList<CharacterType> getAllCharas() {
		return charas;
	}

	public int getHeight() {
		return currentChara.getHeight();
	}

	public int getLength() {
		return currentChara.getLength();
	}

	public double getSpeed() {
		return currentChara.getSpeed();
	}

	public int getJumpLength() {
		return currentChara.getJumpLength();
	}

}
