package controller;

import java.awt.Point;

import model.ElementFactory;
import model.IElement;
import model.IMobile;
import model.IModel;
import model.Permeability;
import model.SpritePosition;
import model.mobileElement.gravity.Diamond;
import model.mobileElement.gravity.GravityElement;
import model.mobileElement.gravity.Rock;
import model.mobileElement.monster.Monster;
import model.motionlessElement.Air;
import model.motionlessElement.Dirt;
import model.motionlessElement.Door;
import model.motionlessElement.Explosion;
import view.IView;

public class ControllerFacade implements IController, IOrderPerformer {

	private static final int DELAY_MS = 200;
	
    private final IView  view;
    private final IModel model;
    
    private UserOrder currentOrder;

    public ControllerFacade(final IView view, final IModel model) {
        super();
        this.view = view;
        this.model = model;
        
        clearCurrentOrder();
    }

    public IView getView() {
        return this.view;
    }

    public IModel getModel() {
        return this.model;
    }

	@Override
	public void play() throws InterruptedException {
		
		while((model.getPlayer()).isAlive())
		{
			Thread.sleep(DELAY_MS);
						
			model.getMap().setMapHasChnged(false);
			updateMap();
			
			Point currentPlayerPosition = model.getPlayer().getPosition();
			IElement collisionElement = null;
			
			//TODO : frame count
			//TODO : scrolling
			
			switch(currentOrder)
			{
			case RIGHT:
				collisionElement = model.getMap().getSquareAt(currentPlayerPosition.x + 1, currentPlayerPosition.y);
				
				if (canMoveIntoElement(collisionElement)) {
					model.getPlayer().moveRight();
				} else if (collisionElement instanceof Rock && model.getMap().getSquareAt(currentPlayerPosition.x + 2, currentPlayerPosition.y) instanceof Air) {
					
					((IMobile) collisionElement).moveRight();
					model.getMap().setSquareAt(currentPlayerPosition.x + 2, currentPlayerPosition.y, collisionElement);
					model.getMap().setSquareAt(currentPlayerPosition.x + 1, currentPlayerPosition.y, ElementFactory.createElementFromElementType(SpritePosition.AIR));
					model.getPlayer().moveRight();
				} else if (collisionElement instanceof Door && model.getPlayer().getDiamondCount() >= model.getMap().getMinimumDiamondCount()) {
					model.getPlayer().moveRight();
				}

				break;
				
			case LEFT:
				collisionElement = model.getMap().getSquareAt(currentPlayerPosition.x - 1, currentPlayerPosition.y);
				
				if (canMoveIntoElement(collisionElement)) {
					model.getPlayer().moveLeft();
				} else if (collisionElement instanceof Rock && model.getMap().getSquareAt(currentPlayerPosition.x - 2, currentPlayerPosition.y) instanceof Air) {
					
					((IMobile) collisionElement).moveLeft();
					model.getMap().setSquareAt(currentPlayerPosition.x - 2, currentPlayerPosition.y, collisionElement);
					model.getMap().setSquareAt(currentPlayerPosition.x - 1, currentPlayerPosition.y, ElementFactory.createElementFromElementType(SpritePosition.AIR));
					model.getPlayer().moveLeft();
				}
				
				break;
				
			case UP:
				collisionElement = model.getMap().getSquareAt(currentPlayerPosition.x, currentPlayerPosition.y -1);
				
				if (canMoveIntoElement(collisionElement)) {
					model.getPlayer().moveUp();
				}
				
				break;
				
			case DOWN:
				collisionElement = model.getMap().getSquareAt(currentPlayerPosition.x, currentPlayerPosition.y + 1);
				
				if (canMoveIntoElement(collisionElement)) {
					model.getPlayer().moveDown();
				}
				
				break;
				
			case NOP:
			default:
				break;
			}
			
			if (collisionElement instanceof Dirt) {
				model.getMap().setSquareAt(currentPlayerPosition.x, currentPlayerPosition.y, ElementFactory.createElementFromElementType(SpritePosition.AIR));
			} else if (collisionElement instanceof Diamond) {
				model.getPlayer().addDiamond();
				model.getMap().setSquareAt(currentPlayerPosition.x, currentPlayerPosition.y, ElementFactory.createElementFromElementType(SpritePosition.AIR));
			}
			
			if (model.getMap().getSquareAt(model.getPlayer().getX(), model.getPlayer().getY()) instanceof Monster) {
				model.getPlayer().hit();
			}
			
			clearCurrentOrder();
			view.refresh(model.getMap().hasMapChanged());
		}
	}
	
	private void updateMap() {
		
		for (int y = model.getMap().getHeight() - 1; y >= 0 ; y--) {
			for (int x = 0; x < model.getMap().getWidth(); x++) {
				
				if (model.getMap().getSquareAt(x, y) instanceof GravityElement) {
					applyGravity(x, y);
				} else if (model.getMap().getSquareAt(x, y) instanceof Explosion) {					
					updateExplosion(x, y);
				}
			}
		}
	}
	
	private void applyGravity(final int x, final int y) {	
		GravityElement currentElement = (GravityElement) model.getMap().getSquareAt(x, y);
		
		if (model.getPlayer().getX() == x && model.getPlayer().getY() == y + 1) {
			if (currentElement.isFalling()) {
				
				model.getPlayer().hit();
				model.getMap().setSquareAt(x, y, ElementFactory.createElementFromElementType(SpritePosition.AIR));
			}
		} else {						
			IElement underneathElement = model.getMap().getSquareAt(x, y + 1);
			
			if (underneathElement != null && underneathElement.getPermeability() == Permeability.PENETRABLE) {
				if (underneathElement instanceof Air || underneathElement instanceof Explosion) {
					
					currentElement.moveDown();
					currentElement.setIsFalling(true);
					
					model.getMap().setSquareAt(x, y + 1, currentElement);
					model.getMap().setSquareAt(x, y, ElementFactory.createElementFromElementType(SpritePosition.AIR));
				} else if (underneathElement instanceof Monster && currentElement.isFalling()) {
					
					generateExplosion(x, y + 1);
					model.getPlayer().killEnemy();
					
					IMobile newDiamond = (IMobile) ElementFactory.createElementFromElementType(SpritePosition.DIAMOND);
					newDiamond.setPosition(new Point(x, y + 1));
					model.getMap().setSquareAt(x, y + 1, newDiamond);
				}
			} else {
				currentElement.setIsFalling(false);
			}
		}
	}
	
	private void updateExplosion(final int x, final int y) {
		Explosion currentElement = (Explosion) model.getMap().getSquareAt(x, y);
		
		if (currentElement.updateExplosion() == false) {
			model.getMap().setSquareAt(x, y, ElementFactory.createElementFromElementType(SpritePosition.AIR));
		}
	}
	
	private void generateExplosion(final int x, final int y) {
		
		model.getMap().setSquareAt(x - 1, y - 1, ElementFactory.createElementFromElementType(SpritePosition.EXPLOSION));
		model.getMap().setSquareAt(x - 1, y, ElementFactory.createElementFromElementType(SpritePosition.EXPLOSION));
		model.getMap().setSquareAt(x - 1, y + 1, ElementFactory.createElementFromElementType(SpritePosition.EXPLOSION));
		model.getMap().setSquareAt(x, y - 1, ElementFactory.createElementFromElementType(SpritePosition.EXPLOSION));
		model.getMap().setSquareAt(x, y + 1, ElementFactory.createElementFromElementType(SpritePosition.EXPLOSION));
		model.getMap().setSquareAt(x + 1, y - 1, ElementFactory.createElementFromElementType(SpritePosition.EXPLOSION));
		model.getMap().setSquareAt(x + 1, y, ElementFactory.createElementFromElementType(SpritePosition.EXPLOSION));
		model.getMap().setSquareAt(x + 1, y + 1, ElementFactory.createElementFromElementType(SpritePosition.EXPLOSION));
	}
	
	private boolean canMoveIntoElement(IElement element) {
		return (element.getPermeability() == Permeability.PENETRABLE || element instanceof Dirt);
	}

	@Override
	public IOrderPerformer getOrderPerformer() {
		return this;
	}

	@Override
	public void orderPerform(final UserOrder userOrder) {
		this.currentOrder = userOrder;
	}
	
	private void clearCurrentOrder() {
		this.currentOrder = UserOrder.NOP;
	}
}
