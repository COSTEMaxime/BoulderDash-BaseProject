package model.mobileElement.gravity;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class Rock extends GravityElement {
	
	public Rock(int currentWorld)
	{
		super(new Sprite('O', SpritePosition.ROCK.ordinal(), currentWorld), Permeability.BLOCKING);
	}
}
