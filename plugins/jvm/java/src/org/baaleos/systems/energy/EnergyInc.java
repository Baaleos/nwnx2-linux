package org.baaleos.systems.energy;

import org.nwnx.nwnx2.jvm.NWScript;

public class EnergyInc {

	public static void CreateEnergy(String energyName){
		Energy e = new Energy(energyName);
		NWScript.printString(e.getName()+" created with id "+e.getID());
	}
	
	public static void ListEnergyTypes(){
		
		for(Energy e : Energy.getEnergyDefinitions()){
			NWScript.printString("Energy:"+e.getName()+" with ID:"+e.getID());
		}
		
	}
}
