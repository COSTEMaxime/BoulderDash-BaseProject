package model;

import java.awt.Image;

public abstract class Element implements IElement {

	private Sprite sprite;
	
	private Permeability permeability;
	
	private int currentImageIndex = 0;
	
	public Element(Sprite sprite, Permeability permeability)
	{
		this.sprite = sprite;
		this.permeability = permeability;
	}
	
	@Override
	public Permeability getPermeability()
	{
		return permeability;
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public Image getImage() {
		if (++currentImageIndex > sprite.getImageCount() - 1)
		{
			currentImageIndex = 0;
		}
		
		return sprite.getImage(currentImageIndex);
	}

}
