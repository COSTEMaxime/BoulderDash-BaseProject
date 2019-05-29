package model;

import java.util.Observable;

public interface IMap {
	
	int getHeight();
	
	int getWidth();
	
	Observable getObservable();

	IElement getSquareAt(int x, int y);

	void setSquareAt(int x, int y, IElement element);
	
	boolean hasMapChanged();
	void setMapHasChnged(boolean hasMapChanged);

	int getMinimumDiamondCount();
}
