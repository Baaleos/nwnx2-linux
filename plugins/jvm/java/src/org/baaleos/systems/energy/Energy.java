package org.baaleos.systems.energy;

import java.util.ArrayList;

import org.nwnx.nwnx2.jvm.Color;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class Energy {

	
	
	public static final String ENERGY_NAME_TO_ID = "nrg_name_to_id_";
	public static final String ENERGY_ID_TO_NAME = "nrg_id_to_name_";
	public static final String ENERGY_COUNT_MODULE = "nrg_count_module";
	public static final String ENERGY_MAX_CAPACITY_PER_USER = "nrg_max_cap_usr_";
	public static final String ENERGY_CURRENT_AMOUNT_PER_USER = "nrg_current_amount_per_usr_";
	public static final String ENERGY_PLAYER_ENTITLED_TO_WIELD = "nrg_ply_has_";
	public static final String ENERGY_PLAYER_RECHARGE_RATE = "nrg_ply_recharge_rate_";
	public Energy(String sName){
		
		int CurrentEnergies  = NWScript.getLocalInt(NWObject.MODULE, ENERGY_COUNT_MODULE);
		CurrentEnergies++;
		NWScript.setLocalInt(NWObject.MODULE, ENERGY_COUNT_MODULE, CurrentEnergies);
		NWScript.setLocalInt(NWObject.MODULE, ENERGY_NAME_TO_ID+sName, CurrentEnergies);
		NWScript.setLocalString(NWObject.MODULE, ENERGY_ID_TO_NAME+CurrentEnergies, sName);	
		this.setID(CurrentEnergies);
		this.setName(sName);
		moduleEnergyDefinitions.add(this);
	}
	
	
	public int getMaxCapacity(final NWObject obj){
		return NWScript.getLocalInt(obj, ENERGY_MAX_CAPACITY_PER_USER+this.getID());
	}
	public int getCurrentAmount(final NWObject obj){
		return NWScript.getLocalInt(obj, ENERGY_CURRENT_AMOUNT_PER_USER+this.getID());
	}
	public void setCurrentAmount(final NWObject obj, int iAmount){
		NWScript.setLocalInt(obj, ENERGY_CURRENT_AMOUNT_PER_USER+this.getID(),iAmount);
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
	private int Red;
	private int Green;
	private int Blue;
	public static boolean getExists(String name){
		return NWScript.getLocalInt(NWObject.MODULE, ENERGY_NAME_TO_ID+name) != 0;
	}
	public static boolean getExists(int ID){
		return NWScript.getLocalString(NWObject.MODULE, ENERGY_NAME_TO_ID+String.valueOf(ID)).trim() != "";
	}
	
	public void recharge(final NWObject player){
		int iRecharge = getPlayerRechargeRate(player);
		int iMax = getMaxCapacity(player);
		int iCurrent = getCurrentAmount(player);
		if(iCurrent == iMax){
			return;
		}
		iCurrent += iRecharge;
		if(iCurrent >= iMax){
			iCurrent = iMax;
		}
		//NWScript.printString(NWScript.getName(player, false)+" is regenerating "+this.getName());
		setCurrentAmount(player,iCurrent);
	}
	
	
	public boolean getPlayerCanUse(final NWObject obj){
		return NWScript.getLocalInt(obj, ENERGY_PLAYER_ENTITLED_TO_WIELD+this.getID()) == 1;
	}
	public int getPlayerRechargeRate(final NWObject obj){
		return NWScript.getLocalInt(obj, ENERGY_PLAYER_RECHARGE_RATE+this.getID());
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
	
	public String getColorString(){
		return new Color(Red,Green,Blue).stringRep;
	}
	
	public void setColor(int r,int g, int b){
		Red = r;
		Green = g;
		Blue = b;
	}
	
}
