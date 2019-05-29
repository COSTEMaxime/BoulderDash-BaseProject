package model.mobileElement.gravity;

import model.Permeability;
import model.Sprite;
import model.mobileElement.MobileElement;

public abstract class GravityElement extends MobileElement {

	public GravityElement(final Sprite sprite, final Permeability permeability) {
		super(sprite, permeability);	
	}

	boolean isFalling = false;
	
	public final boolean isFalling()
	{
		return isFalling;
	}
	
	public void setIsFalling(final boolean isFalling)
	{
		this.isFalling = isFalling;
	}
}
