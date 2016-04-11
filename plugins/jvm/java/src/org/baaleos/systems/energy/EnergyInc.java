package org.baaleos.systems.energy;

import java.util.ArrayList;

import org.baaleos.systems.genetics.Include;
import org.nwnx.nwnx2.jvm.NWObject;
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
	
	
	
	public static ArrayList<NWObject> getAllPlayersAndNPCForEnergySystem(){
		ArrayList<NWObject> returnList = new ArrayList<NWObject>();
		
		int iCreaturesAsSimulatedPCs = NWScript.getLocalInt(NWObject.MODULE,Include.CREATURES_AS_SIMULATED_PC_COUNT);
        //iCreaturesAsSimulatedPCs++;
		if(iCreaturesAsSimulatedPCs > 0){
	        for(int i=1;i<=iCreaturesAsSimulatedPCs;i++){
	        	NWObject npc = NWScript.getLocalObject(NWObject.MODULE,Include.SIMULATED_PC+i );
	        	if(npc.valid()){
	        		returnList.add(npc);
	        	}
	        }
        }
		for(NWObject obj : NWScript.getPCs()){
			returnList.add(obj);
		}
		return returnList;
		
	}
	
	
	public static void EnergyHeartbeat(){
		
		while(true){
			for(Energy e : Energy.getEnergyDefinitions()){
				for(NWObject obj : getAllPlayersAndNPCForEnergySystem()){
					if(e.getPlayerCanUse(obj)){
						//Allow them to use the energy, so we recharge them
						e.recharge(obj);
					}
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
