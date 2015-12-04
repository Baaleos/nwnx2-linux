package org.baaleos.systems.playerid;

import java.util.UUID;

import org.baaleos.systems.db.DBInc;
import org.baaleos.systems.god.advancedTypes.NWCreature;
import org.baaleos.systems.god.advancedTypes.NWPlayer;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class IDInc {

	public static String getPlayerId(NWCreature nwCreature)
	{
		String strId = "";
		
		if(NWScript.getIsPC(nwCreature)==false){
			
			return NWScript.getTag(nwCreature)+"_"+NWScript.getResRef(nwCreature)+"_"+NWScript.getName(nwCreature, false);
		}

		String localID = NWScript.getLocalString(getIDHolder(nwCreature), "PLAYER_ID");
		if(localID.length() <= 1){
			//Empty String - So we need a new ID
			localID = generateGuid();
			NWScript.setLocalString(getIDHolder(nwCreature),"PLAYER_ID",localID);
			//Store the persistent value in the db
			//Assume the creature is a player
			setPlayerDBID((NWPlayer) nwCreature,localID);
		}
		
		if(localID == null || localID.equals(""))
		{
			//No ID yet?
			//BUG!!
		}
		strId = localID;
		
		
		
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
	
	public static Object getPlayerDBID(NWPlayer player)
	{
		String playerName = NWScript.getPCPlayerName(player);
		String characterName = NWScript.getName(player, false);
		try{
		Object ID = DBInc.doQuery("SELECT PlayerID from PlayerIDs where Player = '"+playerName+"' and CharacterName = '"+characterName+"';",
				"ID");
		return ID;
		}catch(Exception ee){
			return "";
		}
	}
	
	public static void setPlayerDBID(NWPlayer player, String ID)
	{
		String playerName = NWScript.getPCPlayerName(player);
		String characterName = NWScript.getName(player, false);
		
		DBInc.doUpdateQuery("INSERT into PlayerIDs (Player,CharacterName,PlayerID) values ('"+playerName+"','"+characterName+"','"+ID+"')");
	}

	
	
}
