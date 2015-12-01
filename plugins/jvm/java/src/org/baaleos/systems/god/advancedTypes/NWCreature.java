package org.baaleos.systems.god.advancedTypes;

import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class NWCreature extends NWObject {

	public NWCreature(int oid) {
		super(oid);
		// TODO Auto-generated constructor stub
	}

	
	public boolean IsValid(){
		return NWScript.getIsObjectValid(this);
	}
}
