package org.baaleos.systems.god.entities;

import java.util.ArrayList;
import java.util.Hashtable;

import org.baaleos.systems.god.NWGod;
import org.baaleos.systems.playerid.*;
import org.baaleos.systems.types.NWCreature;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class NWFollower extends NWCreature{

	public NWFollower(int oid){
		super(oid);
		name = NWScript.getName(this,false);
		tag = NWScript.getTag(this);
		resref = NWScript.getResRef(this);
		System.out.println(NWScript.getName(this,false)+" is now a follower?");
		
	}
	
	private static Hashtable<String,ArrayList<NWObject>> FollowersOfGod;
	public static ArrayList<NWObject> getFollowersOfGod(NWGod god)
	{
		if(FollowersOfGod == null){
			FollowersOfGod = new Hashtable<String,ArrayList<NWObject>>();
		}
		if(god.IsValid() == false){
			return new ArrayList<NWObject>();
		}
		String PlayerID = org.baaleos.systems.playerid.IDInc.getPlayerId(god);
		if(FollowersOfGod.containsKey(PlayerID) == false){
			FollowersOfGod.put(PlayerID, new ArrayList<NWObject>());
		}
		
		return FollowersOfGod.get(PlayerID);
	}
	
	public static void setGod()
	{
		
	}
	

	
	private String name;
	private String tag;
	private String resref;
	public String getName()
	{
		return name;
	}
	public String getTag()
	{
		return tag;
	}
	public String getResRef()
	{
		return resref;
	}
}
