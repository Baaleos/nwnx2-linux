package org.baaleos.systems.types;

import org.baaleos.systems.playerid.IDInc;
import org.nwnx.nwnx2.jvm.NWScript;

public class NWPlayer extends NWCreature{

	public NWPlayer(int oid) {
		super(oid);
		// TODO Auto-generated constructor stub
	}
	public NWPlayer(NWCreature cre) {
		super(cre.oid());
		// TODO Auto-generated constructor stub
	}
	
	public String getUniqueId(){
		return IDInc.getPlayerId(this);
	}
	
	
}
