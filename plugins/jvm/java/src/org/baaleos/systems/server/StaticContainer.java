package org.baaleos.systems.server;

import java.util.ArrayList;
import java.util.List;

import org.baaleos.systems.playerid.IDInc;
import org.baaleos.systems.types.NWCreature;
import org.baaleos.systems.types.NWPlayer;
import org.nwnx.nwnx2.jvm.NWObject;

public class StaticContainer {
	private static List<NWPlayer> PlayerContainer;
	
	public static List<NWCreature> getPlayersAsCreatures()
	{
		if(PlayerContainer == null)
		{
			PlayerContainer = new ArrayList<NWPlayer>();
		}
		ArrayList<NWCreature> tempList = new ArrayList<NWCreature>();
		for(NWPlayer p : PlayerContainer){
			tempList.add(p);
		}
		return tempList;
	}
	public static List<NWPlayer> getPlayers()
	{
		if(PlayerContainer == null)
		{
			PlayerContainer = new ArrayList<NWPlayer>();
		}
		return PlayerContainer;
	}
	public static void onPlayerEnter(NWObject player)
	{
		NWCreature cre = new NWCreature(player);
		IDInc.getPlayerId(cre);
		PlayerContainer.add(new NWPlayer(player.getObjectId()));
	}
	
	
}
