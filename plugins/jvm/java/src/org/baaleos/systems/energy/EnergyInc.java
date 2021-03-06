package org.baaleos.systems.energy;

import java.util.ArrayList;

import org.baaleos.systems.genetics.Include;
import org.nwnx.nwnx2.jvm.*;

public class EnergyInc {

	public static int CreateEnergy(String energyName){
		Energy e = new Energy(energyName);
		NWScript.printString(e.getName()+" created with id "+e.getID());
		return e.getID();
	}
	
	public static void ListEnergyTypes(){
		
		for(Energy e : Energy.getEnergyDefinitions()){
			NWScript.printString("Energy:"+e.getName()+" with ID:"+e.getID());
		}
		
	}
	
	
	
	
	public static void ConstructEnergyListMenuForPlayer(final NWObject obj){
		String str = "";
		ArrayList<Energy> energies = Energy.getEnergyDefinitions();
		if(energies.size() == 0){
			str = "No Energies Loaded!";
		}else{
			boolean bUseEnergy = false;
			for(Energy e : energies){
				if(e.getPlayerCanUse(obj)){
					bUseEnergy = true;
					str += e.getName()+" "+e.getCurrentAmount(obj)+"/"+e.getMaxCapacity(obj)+" ("+e.getPlayerRechargeRate(obj)+" per 5 seconds)"+System.getProperty("line.separator");
				}
			}
			if(!bUseEnergy){
				str = "You are not able to harness any energies at present!";
			}
		}
		
		NWScript.setCustomToken(6660, str);
		
	}
	
	public static int GetEnergyTypeCount(){
		return Energy.getEnergyDefinitions().size();
		
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
