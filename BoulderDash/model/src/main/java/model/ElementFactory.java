package model;

import model.mobileElement.Player;
import model.mobileElement.gravity.Diamond;
import model.mobileElement.gravity.Rock;
import model.mobileElement.monster.MonsterDiamond;
import model.mobileElement.monster.MonsterScore;
import model.motionlessElement.Air;
import model.motionlessElement.Amoeba;
import model.motionlessElement.Dirt;
import model.motionlessElement.Door;
import model.motionlessElement.Explosion;
import model.motionlessElement.SpecialWall;
import model.motionlessElement.Wall;

public class ElementFactory {
	
	public static int CurrentWorld = 0;
	
	public static IElement createElementFromElementType(SpritePosition elementType)
	{
		IElement newElement = null;
		
		switch (elementType)
		{
			case AIR :
				newElement = new Air(ElementFactory.CurrentWorld);
				break;
			case DIRT :
				newElement = new Dirt(ElementFactory.CurrentWorld);
				break;
			case AMOEBA :
				newElement = new Amoeba(ElementFactory.CurrentWorld);
				break;
			case DOOR :
				newElement = new Door(ElementFactory.CurrentWorld);
				break;
			case EXPLOSION :
				newElement = new Explosion(ElementFactory.CurrentWorld);
				break;
			case ROCK :
				newElement = new Rock(ElementFactory.CurrentWorld);
				break;
			case SPECIAL_WALL :
				newElement = new SpecialWall(ElementFactory.CurrentWorld);
				break;
			case UNDEFINED :
			case WALL :
				newElement = new Wall(ElementFactory.CurrentWorld);
				break;
			case DIAMOND :
				newElement = new Diamond(ElementFactory.CurrentWorld);
				break;
			case MONSTER_SCORE :
				newElement = new MonsterScore(ElementFactory.CurrentWorld);
				break;
			case MONSTER_DIAMOND :
				newElement = new MonsterDiamond(ElementFactory.CurrentWorld);
				break;
		}
		
		if (newElement == null)
		{
			System.err.println("Symbol '" + elementType + "' is not currently supported");
		}
		
		return newElement;
	}
	
	public static IElement createElementFromFileSymbol(char fileSymbol)
	{
		IElement newElement = null;
		
		switch (fileSymbol)
		{
			case ' ' :
				newElement = new Air(ElementFactory.CurrentWorld);
				break;
			case '.' :
				newElement = new Dirt(ElementFactory.CurrentWorld);
				break;
			case 'A' :
				newElement = new Amoeba(ElementFactory.CurrentWorld);
				break;
			case 'D' :
				newElement = new Door(ElementFactory.CurrentWorld);
				break;
			case 'E' :
				newElement = new Explosion(ElementFactory.CurrentWorld);
				break;
			case 'O' :
				newElement = new Rock(ElementFactory.CurrentWorld);
				break;
			case 'S' :
				newElement = new SpecialWall(ElementFactory.CurrentWorld);
				break;
			case 'W' :
			case 'U' :
				newElement = new Wall(ElementFactory.CurrentWorld);
				break;
			case 'X' :
				newElement = new Diamond(ElementFactory.CurrentWorld);
				break;
			case '1' :
				newElement = new MonsterScore(ElementFactory.CurrentWorld);
				break;
			case '2' :
				newElement = new MonsterDiamond(ElementFactory.CurrentWorld);
				break;
		}
		
		if (newElement == null)
		{
			System.err.println("Symbol '" + fileSymbol + "' is not currently supported");
		}
		
		return newElement;
	}
}
