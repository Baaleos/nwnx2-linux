package org.baaleos.systems.god;

import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.constants.ObjectType;

public class God {

	public God(NWObject obj) throws Exception
	{
		if(obj.objectType() != ObjectType.CREATURE)
		{
			throw new Exception("The object being targetted is not a creature");
		}
		//Check DB for anything for our character.
		String str = NWScript.getCampaignString("NWNX", "", obj);
		
	}
	
	private String name;
	private String playerName;
	private String UniqueID;
}
