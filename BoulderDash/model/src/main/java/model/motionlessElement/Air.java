package model.motionlessElement;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class Air extends MotionlessElement {

	public Air(int currentWorld)
	{
		super(new Sprite(' ', SpritePosition.AIR.ordinal(), currentWorld), Permeability.PENETRABLE);
	}
}
