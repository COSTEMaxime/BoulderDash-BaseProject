package model.dao;

public class PlayerInfo {
	
	private int startingPositionX;
	private int startingPositionY;
	
	public PlayerInfo(final int startingPositionX, final int startingPositionY) {
		this.startingPositionX = startingPositionX;
		this.startingPositionY = startingPositionY;
	}
	
	public int getStartingPositionX() {
		return this.startingPositionX;
	}
	
	public int getStartingPositionY() {
		return this.startingPositionY;
	}

}
