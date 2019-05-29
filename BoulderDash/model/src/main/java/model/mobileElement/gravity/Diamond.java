package model.mobileElement.gravity;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class Diamond extends GravityElement {

	public Diamond(final int currentWorld)
	{
		super(new Sprite('X', SpritePosition.DIAMOND.ordinal(), currentWorld), Permeability.PENETRABLE);
	}
}
