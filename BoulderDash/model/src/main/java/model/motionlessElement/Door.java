package model.motionlessElement;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class Door extends MotionlessElement {

	public Door(int currentWorld)
	{
		super(new Sprite('D', SpritePosition.DOOR.ordinal(), currentWorld), Permeability.BLOCKING);
	}
}
