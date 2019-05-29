package model;

import model.mobileElement.Player;

public class ModelFacade implements IModel {
	
	private IMap map;
	
	private IPlayer player;
	
    public ModelFacade(String mapName) {
        super();
        map = new Map(mapName);
        player = new Player(mapName);
    }

	@Override
	public IMap getMap() {
		return this.map;
	}

	@Override
	public IPlayer getPlayer() {
		return this.player;
	}
}
