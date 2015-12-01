package org.baaleos.systems.god.entities;

import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class Follower {

	public Follower(NWObject objInGame){
		name = NWScript.getName(objInGame,false);
		tag = NWScript.getTag(objInGame);
		resref = NWScript.getResRef(objInGame);
		System.out.println(NWScript.getName(objInGame,false)+" is now a follower?");
		followerObject = objInGame;
	}
	
	NWObject followerObject;
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
