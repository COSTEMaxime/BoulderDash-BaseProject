package model.mobileElement.monster;

import model.Permeability;
import model.Sprite;
import model.mobileElement.MobileElement;

public abstract class Monster extends MobileElement {

	public Monster(Sprite sprite, Permeability penetrable) {
		super(sprite, penetrable);
	}

}
