package org.baaleos.systems.god;

import java.util.Hashtable;

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
		//String str = NWScript.getCampaignString("NWNX", "", obj);
		theGod = obj;
	}
	
	private NWObject theGod;
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
	
	public int doGodEnergyCalculations()
	{
		int output = 0;
		int consumption = 0;
		int active = 0;
		for(String str : toggles){
			active = 0;
			active = NWScript.getLocalInt(this.theGod, str);
			if(active >= 1)
			{
				consumption += active * (int)getTogglePrices().get(str); //Allow multiplier for levels
			}
		}

		return output;
	}
	
}
