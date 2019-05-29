package model.mobileElement.monster;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class MonsterScore extends Monster {

	public MonsterScore(int currentWorld)
	{
		super(new Sprite('1', SpritePosition.MONSTER_SCORE.ordinal(), currentWorld), Permeability.PENETRABLE);
	}
}
