package model;

import java.awt.Image;
import fr.exia.showboard.ISquare;

public interface IElement extends ISquare {

		Permeability getPermeability();
	
		Sprite getSprite();
		
		@Override
		Image getImage();
}
