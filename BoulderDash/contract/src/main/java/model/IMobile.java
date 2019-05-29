package model;

import java.awt.Point;

import fr.exia.showboard.IPawn;

public interface IMobile extends IPawn, IElement{
	
	void setPosition(Point position);
	
	void moveRight();
	void moveLeft();
	void moveUp();
	void moveDown();
}
