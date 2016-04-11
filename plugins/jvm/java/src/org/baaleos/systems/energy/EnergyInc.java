package org.baaleos.systems.energy;

import org.nwnx.nwnx2.jvm.NWScript;

public class EnergyInc {

	public static void CreateEnergy(String energyName){
		Energy e = new Energy(energyName);
		NWScript.printString(e.getName()+" created with id "+e.getID());
	}
	
	
}
