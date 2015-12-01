package org.baaleos.systems.playerid;

import java.util.UUID;

import org.baaleos.systems.db.DBInc;
import org.baaleos.systems.god.advancedTypes.NWPlayer;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class IDInc {

	public static String getPlayerId(NWPlayer player)
	{
		String strId = "";
		
		if(NWScript.getIsPC(player)==false){
			
			return NWScript.getTag(player)+"_"+NWScript.getResRef(player)+"_"+NWScript.getName(player, false);
		}
		
		
		String localID = NWScript.getLocalString(getIDHolder(player), "PLAYER_ID");
		if(localID.length() <= 1){
			//Empty String - So we need a new ID
			localID = generateGuid();
			NWScript.setLocalString(getIDHolder(player),"PLAYER_ID",localID);
			//Store the persistent value in the db
			
		}
		
		if(localID == null)
		{
			//No ID yet?
		}
		
		
		
		
		return strId;
	}
	
	public static String generateGuid()
	{
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
	}
	
	
	public static NWObject getIDHolder(NWObject player)
	{
		NWObject obj = NWScript.getItemPossessedBy(player, "player_id_holder");
		if(obj != null)	{
			return obj;
		}
		return NWScript.createItemOnObject("id_holder", player, 1, "player_id_holder");
	}
	
	public static Object getPlayerDBID(NWObject player)
	{
		String playerName = NWScript.getPCPlayerName(player);
		String characterName = NWScript.getName(player, false);
		Object ID = DBInc.doQuery("SELECT ID from PlayerIDs where Player = '"+playerName+"' and CharacterName = '"+characterName+"';",
				"ID");
		return ID;
	}
	
	public static void setPlayerDBID(NWObject player, String ID)
	{
		String playerName = NWScript.getPCPlayerName(player);
		String characterName = NWScript.getName(player, false);
		
		DBInc.doUpdateQuery("INSERT into PlayerIDs (Player,CharacterName");
	}

	
	
}
