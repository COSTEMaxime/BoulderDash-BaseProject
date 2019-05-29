package model.motionlessElement;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class Amoeba extends MotionlessElement {

	public Amoeba(int currentWorld)
	{
		super(new Sprite('A', SpritePosition.AMOEBA.ordinal(), currentWorld), Permeability.BLOCKING);
	}
}
