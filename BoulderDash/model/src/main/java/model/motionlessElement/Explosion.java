package model.motionlessElement;

import model.Permeability;
import model.Sprite;
import model.SpritePosition;

public class Explosion extends MotionlessElement {

	private static final int EXPLOSION_TICKS = 10;
	
	private int remainingTicks;
	
	public Explosion(int currentWorld) {
		super(new Sprite('E', SpritePosition.EXPLOSION.ordinal(), currentWorld), Permeability.PENETRABLE);
		
		remainingTicks = EXPLOSION_TICKS;
	}

	public boolean updateExplosion() {
		return remainingTicks-- > 0;
	}
}
