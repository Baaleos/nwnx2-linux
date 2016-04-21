package org.baaleos.systems.god;

import java.util.ArrayList;
import java.util.Hashtable;

import org.baaleos.systems.playerid.IDInc;
import org.baaleos.systems.types.NWPlayer;
import org.nwnx.nwnx2.jvm.NWEffect;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.constants.DurationType;
import org.nwnx.nwnx2.jvm.constants.ObjectType;

public class NWGod extends NWPlayer {

	
	
	
	public NWGod(int oid) throws Exception {
		super(oid);
		
		// TODO Auto-generated constructor stub
		if(this.objectType() != ObjectType.CREATURE)
		{
			throw new Exception("The object being targetted is not a creature");
		}
		if(!getListOfGods().containsKey(this.getUniqueID())){
			getListOfGods().put(this.getUniqueID(), this);
		}
	}

	
	
	private static Hashtable<String,NWGod> ListOfGods;
	public static Hashtable<String,NWGod> getListOfGods(){
		if(ListOfGods == null){
			ListOfGods = new Hashtable();
		}
		return ListOfGods;
	}
	
	
	private String name;
	private String playerName;
	private String UniqueID;
	
	
	public String[] toggles =  {
			"Invulnerable",
			"Immortal",
			"Invisible",
			"Godly Levels",
			"Godly Strength",
			"Godly Dexterity",
			"Godly Wisdom",
			"Godly Intelligence",
			"Godly Charisma",
			"Godly Constitution"
	};
	
	
	private static Hashtable priceDatabase;
	public Hashtable getTogglePrices()
	{
		if(priceDatabase != null){
			return priceDatabase;
		}
		
		priceDatabase = new Hashtable();
		priceDatabase.put("Invulnerable",5);
		priceDatabase.put("Immortal",3);
		priceDatabase.put("Invisible",2);
		priceDatabase.put("Godly Levels",5);
		priceDatabase.put("Godly Strength",1);
		priceDatabase.put("Godly Dexterity",1);
		priceDatabase.put("Godly Wisdom",1);
		priceDatabase.put("Godly Intelligence",1);
		priceDatabase.put("Godly Charisma",1);
		priceDatabase.put("Godly Constitution",1);
		return priceDatabase;
	}

	public void doGodEnergyCalculations()
	{
		if(this.IsValid() == false){ return;}
		if(NWScript.getLocalInt(this,"IS_GOD")== 0)
		{
			return;
		}
		NWEffect effect = NWScript.effectVisualEffect(13, false);
		NWScript.applyEffectToObject(DurationType.TEMPORARY, effect, this, 1.00f);
		int output = 0;
		int consumption = 0;
		int active = 0;
		for(String str : toggles){
			active = 0;
			active = NWScript.getLocalInt(this, str);
			if(active >= 1)
			{
				consumption += active * (int)getTogglePrices().get(str); //Allow multiplier for levels
			}
		}

		
	}
	
}
