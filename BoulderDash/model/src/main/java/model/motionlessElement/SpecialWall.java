package model.motionlessElement;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class SpecialWall extends MotionlessElement {

	public SpecialWall (int currentWorld)
	{
		super(new Sprite('S', SpritePosition.SPECIAL_WALL.ordinal(), currentWorld), Permeability.BLOCKING);
	}
}
