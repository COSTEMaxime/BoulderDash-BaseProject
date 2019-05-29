package model.mobileElement.monster;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class MonsterDiamond extends Monster {

	public MonsterDiamond(int currentWorld)
	{
		super(new Sprite('2', SpritePosition.MONSTER_DIAMOND.ordinal(), currentWorld), Permeability.PENETRABLE);
	}
}
