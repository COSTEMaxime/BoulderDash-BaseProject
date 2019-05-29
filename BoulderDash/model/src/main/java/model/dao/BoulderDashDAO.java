package model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoulderDashDAO extends AbstractDAO {
	
	private static final String SQL_MAP_BY_NAME = "{call findMapByName(?)}";
	private static final String SQL_PLAYER_BY_NAME = "{call findPlayerByName(?)}";
    //TODO : find all maps
    private static final String SQL_ALL_MAPS = "{call findAllMaps()}";
    

    public static MapInfo getMapByName(final String name) throws SQLException {
    	MapInfo mapInfo = null;
    	
    	final CallableStatement callStatement = prepareCall(SQL_MAP_BY_NAME);
        callStatement.setString(1, name);
        
        if (callStatement.execute()) {
            ResultSet result = callStatement.getResultSet();
            
            if (result.first()) {
            	mapInfo = new MapInfo(result.getString(1), result.getString(2), result.getInt(3), result.getInt(4), result.getInt(5));
            }
            
            result.close();
        }
        return mapInfo;
    }
    
    public static PlayerInfo getPlayerByName(final String name) throws SQLException {
    	PlayerInfo playerInfo = null;
    	
    	final CallableStatement callStatement = prepareCall(SQL_PLAYER_BY_NAME);
        callStatement.setString(1, name);
        
        if (callStatement.execute()) {
            ResultSet result = callStatement.getResultSet();
            
            if (result.first()) {
            	playerInfo = new PlayerInfo(result.getInt(1), result.getInt(2));
            }
            
            result.close();
        }
        return playerInfo;
    }

    //TODO : get map list
    public static List<String> getAllMaps() throws SQLException {
    	final ArrayList<String> maps = new ArrayList<String>();
    	
        final CallableStatement callStatement = prepareCall(SQL_ALL_MAPS);
        
        if (callStatement.execute()) {
            final ResultSet result = callStatement.getResultSet();
            
            if (!result.first()) {return maps;}
            
            do
            {
            	maps.add(result.getString(1));
            } while (result.next());
            
            
            result.close();
        }
        return maps;
    }
}
