package model.motionlessElement;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class Wall extends MotionlessElement {
	
	public Wall(int currentWorld)
	{
		super(new Sprite('W', SpritePosition.WALL.ordinal(), currentWorld), Permeability.BLOCKING);
	}
}
