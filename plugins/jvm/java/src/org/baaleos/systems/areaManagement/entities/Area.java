package org.baaleos.systems.areaManagement.entities;

import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class Area {
	public Area(final NWObject obj){
		area = obj;
		X = NWScript.getLocalInt(area, "X");
		Y = NWScript.getLocalInt(area, "Y");
		goesNorth = NWScript.getLocalInt(area, "goesNorth")==1;
		goesSouth = NWScript.getLocalInt(area, "goesSouth")==1;
		goesEast = NWScript.getLocalInt(area, "goesEast")==1;
		goesWest = NWScript.getLocalInt(area, "goesWest")==1;
		RegionId = NWScript.getLocalInt(area, "REGION_ID");
		if(Region.getRegionById(RegionId) != null){
			region = Region.getRegionById(RegionId);	
		}
	}
	public int X;
	public int Y;
	public Region region;
	public int RegionId;
	
	
	
	public boolean goesNorth;
	public boolean goesSouth;
	public boolean goesEast;
	public boolean goesWest;
	
	private NWObject area;
	
	
	public boolean requestNewAreaInDirection(String direction){
		try
		{
			int _x = Integer.valueOf(X);
			int _y = Integer.valueOf(Y);
		switch(direction){
				case "north":
						if(!goesNorth){ throw new Exception("Cannot go north");}
						//Increase Y
						_y++;
				break;
				case "south":
						if(!goesSouth){ throw new Exception("Cannot go south");}
						//Decrease Y
						_y--;
				break;
				case "east":
						if(!goesEast){ throw new Exception("Cannot go east");}
						//Increase X
						_x++;
				break;
				case "west":
						if(!goesWest){ throw new Exception("Cannot go west");}
						//Decrease X
						_x--;
				break;
			}
			System.out.println("New Area Coordinates:"+_x+":"+_y);
			boolean bAllowedArea = region.isNewAreaCoordinatesAllowed(_x, _y);
			if(!bAllowedArea){
				return false;
			}else{
				//Area is allowed by depth rules
				//Does it exist already?
				
				return true;
			}
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	
	
}
