package model.mobileElement;

import java.awt.Point;

import model.Element;
import model.IMobile;
import model.Permeability;
import model.Sprite;

public abstract class MobileElement extends Element implements IMobile {

	private Point position;
	
	public MobileElement(final Sprite sprite, final Permeability permeability) {
		super(sprite, permeability);
		
		this.position = new Point(0, 0);
	}

	@Override
	public int getX() {
		return position.x;
	}

	@Override
	public int getY() {
		return position.y;
	}

	@Override
	public Point getPosition() {
		return position;
	}
	
	@Override
	public void setPosition(Point position) {
		this.position = position;
	}
	
	@Override
	public void moveRight() {
		this.position.x ++;
	}
	
	@Override
	public void moveLeft() {
		this.position.x --;
	}
	
	@Override
	public void moveUp() {
		this.position.y --;
	}
	
	@Override
	public void moveDown() {
		this.position.y ++;
	}
}
