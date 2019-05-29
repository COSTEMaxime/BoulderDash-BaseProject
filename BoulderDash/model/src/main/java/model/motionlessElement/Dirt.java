package model.motionlessElement;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class Dirt extends MotionlessElement {

	public Dirt(int currentWorld)
	{
		super(new Sprite('.', SpritePosition.DIRT.ordinal(), currentWorld), Permeability.BLOCKING);
	}
}
