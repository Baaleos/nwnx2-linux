package org.baaleos.systems.energy;

import java.util.ArrayList;

import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class Energy {

	
	
	public static final String ENERGY_NAME_TO_ID = "nrg_name_to_id_";
	public static final String ENERGY_ID_TO_NAME = "nrg_id_to_name_";
	public static final String ENERGY_COUNT_MODULE = "nrg_count_module";
	public static final String ENERGY_MAX_CAPACITY_PER_USER = "nrg_max_cap_usr_";
	public static final String ENERGY_CURRENT_AMOUNT_PER_USER = "nrg_current_amount_per_usr_";
	public Energy(String sName){
		
		int CurrentEnergies  = NWScript.getLocalInt(NWObject.MODULE, ENERGY_COUNT_MODULE);
		CurrentEnergies++;
		NWScript.setLocalInt(NWObject.MODULE, ENERGY_COUNT_MODULE, CurrentEnergies);
		NWScript.setLocalInt(NWObject.MODULE, ENERGY_NAME_TO_ID+sName, CurrentEnergies);
		NWScript.setLocalString(NWObject.MODULE, ENERGY_ID_TO_NAME+CurrentEnergies, sName);	
		this.setID(CurrentEnergies );
		this.setName(sName);
	}
	
	
	public int getMaxCapacity(final NWObject obj){
		return NWScript.getLocalInt(obj, ENERGY_MAX_CAPACITY_PER_USER+this.getID());
	}
	public int getCurrentAmount(final NWObject obj){
		return NWScript.getLocalInt(obj, ENERGY_CURRENT_AMOUNT_PER_USER+this.getID());
	}
	
	private static final ArrayList<Energy> moduleEnergyDefinitions = new ArrayList<Energy>();
	public static final ArrayList<Energy> getEnergyDefinitions(){
		return moduleEnergyDefinitions;
	}
	public static Energy getEnergy(String name){
		for(Energy e : moduleEnergyDefinitions){
			if(e.getName() == name){
				return e;
			}
		}
		return new Energy(name);
	}
	public static Energy getEnergy(int id){
		for(Energy e : moduleEnergyDefinitions){
			if(e.getID() == id){
				return e;
			}
		}
		String name = NWScript.getLocalString(NWObject.MODULE, ENERGY_ID_TO_NAME+id);
		return new Energy(name);
	}
	
	
	private int ID;
	private String name;
	
	public static boolean getExists(String name){
		return NWScript.getLocalInt(NWObject.MODULE, ENERGY_NAME_TO_ID+name) != 0;
	}
	public static boolean getExists(int ID){
		return NWScript.getLocalString(NWObject.MODULE, ENERGY_NAME_TO_ID+String.valueOf(ID)).trim() != "";
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
