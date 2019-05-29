package model;

public interface IPlayer extends IMobile{

	boolean isAlive();
	
	int getDiamondCount();
	int getScore();
	int getLivesRemaining();

	void hit();
	void addDiamond();
	void killEnemy();
}
