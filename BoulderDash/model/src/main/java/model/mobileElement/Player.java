package model.mobileElement;

import java.awt.Point;
import java.sql.SQLException;

import model.IPlayer;
import model.Permeability;
import model.Sprite;
import model.UserDirection;
import model.dao.BoulderDashDAO;
import model.dao.PlayerInfo;

public class Player extends MobileElement implements IPlayer {
	
	private static final int TOTAL_LIVES = 3;
	
	private Point basePosition;
	
	private int score = 0;
	private int diamondCount = 0;
	private int livesRemaining = TOTAL_LIVES;
	
	//TODO userDirection
	private UserDirection currentDirection;
	
	public Player(String mapName)
	{
		super(new Sprite(0, 0, 2), Permeability.BLOCKING);
		
		loadPlayer(mapName);
	}
	
	private void loadPlayer(String mapName) {
		
		PlayerInfo playerInfo;
		try {
			playerInfo = BoulderDashDAO.getPlayerByName(mapName);

			this.basePosition = new Point(playerInfo.getStartingPositionX(), playerInfo.getStartingPositionY());
			this.setPosition(new Point(playerInfo.getStartingPositionX(), playerInfo.getStartingPositionY()));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isAlive() {
		return livesRemaining > 0;
	}
	
	@Override
	public void hit() {
		livesRemaining--;
		this.setPosition(new Point(basePosition));
	}

	@Override
	public int getDiamondCount() {
		return diamondCount;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public int getLivesRemaining() {
		return livesRemaining;
	}
	
	@Override
	public void addDiamond() {
		this.diamondCount++;
	}

	@Override
	public void killEnemy() {
		this.score += 200;
	}
}
