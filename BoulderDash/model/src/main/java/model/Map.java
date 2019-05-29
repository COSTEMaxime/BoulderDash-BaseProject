package model;

import java.awt.Point;
import java.sql.SQLException;
import java.util.Observable;

import model.dao.BoulderDashDAO;
import model.dao.MapInfo;

public class Map extends Observable implements IMap {

	private String mapName;
	
	private int height;
	private int width;
	private int minimumDiamoundCount;
	
	private IElement[][] elements;
	
	private boolean mapHasChanged = false;
	
	public Map(String mapName)
	{
		this.mapName = mapName;
		loadMap();
	}
	
	private void loadMap()
	{
		try {
			MapInfo mapInfo = BoulderDashDAO.getMapByName(mapName);
			
			height = mapInfo.getHeight();
			width = mapInfo.getWidht();
			minimumDiamoundCount = mapInfo.getMinimumDiamondCount();
			
			elements = new IElement[width][height];
			
			for (int y = 0; y < height; y++)
			{
				for (int x = 0; x < width; x++)
				{						
					IElement newElement = ElementFactory.createElementFromFileSymbol(mapInfo.getData().charAt(x + (y * width)));
					
					if (newElement instanceof IMobile) {
						((IMobile)newElement).setPosition(new Point(x, y));
					}
					
					this.elements[x][y] = newElement;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Observable getObservable() {
		return this;
	}

	@Override
	public IElement getSquareAt(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return null;
		return elements[x][y];
	}

	@Override
	public void setSquareAt(int x, int y, IElement element) {
		if (x < 0 || x >= width || y < 0 || y >= height) return;
		this.elements[x][y] = element;
		this.mapHasChanged = true;
	}

	@Override
	public boolean hasMapChanged() {
		return this.mapHasChanged;
	}

	@Override
	public void setMapHasChnged(boolean hasMapChanged) {
		this.mapHasChanged = hasMapChanged;
	}

	@Override
	public int getMinimumDiamondCount() {
		return this.minimumDiamoundCount;
	}
}
