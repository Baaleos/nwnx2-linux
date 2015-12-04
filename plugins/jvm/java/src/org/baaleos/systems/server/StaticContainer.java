package org.baaleos.systems.server;

import java.util.ArrayList;
import java.util.List;

import org.nwnx.nwnx2.jvm.NWObject;

public class StaticContainer {
	private static List<NWObject> PlayerContainer;
	
	public static List<NWObject> getPlayers()
	{
		if(PlayerContainer == null)
		{
			PlayerContainer = new ArrayList<NWObject>();
		}
		return PlayerContainer;
	}
	public static void onPlayerEnter(NWObject player)
	{
		
	}
	
	
}
