package org.baaleos.systems.areaManagement.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Region {
	public Region(int regionId){
		RegionId = regionId;
		
	}
	
	public static Region getRegionById(int id){
		if(RegionLookup.containsKey(id)){
			Region storedRegion = RegionLookup.get(id);
			return storedRegion;
		}
		return null;
	}
	
	
	private static HashMap<Integer,Region> RegionLookup = new HashMap<Integer,Region>();
	
	
	public int RegionId;
	public String RegionName;
	public int RegionType;
	
	
	public ArrayList<Area> Areas;
	public int MaxYDepth;
	public int MaxXDepth;
	
	
	
	public int calculateHeight(){
		int i = 0;
		int highest = 0;
		int lowest = 0;
		
		for(Area ar : Areas){
			if(ar.Y > highest){
				highest = ar.Y;
			}
			if(ar.Y < lowest){
				lowest = ar.Y;
			}
		}
		
		i = Math.abs(highest - lowest);
		
		
		return i;
	}
	public int calculateWidth(){
		int i = 0;
		int highest = 0;
		int lowest = 0;
		
		for(Area ar : Areas){
			if(ar.X > highest){
				highest = ar.Y;
			}
			if(ar.X < lowest){
				lowest = ar.Y;
			}
		}
		
		i = Math.abs(highest - lowest);
		
		
		return i;
	}
	
	public boolean isNewAreaCoordinatesAllowed(int x, int y){
		boolean allowed = true;
		int highesty = 0, lowesty =0;
		int highestx = 0, lowestx =0;
		for(Area ar : Areas){
			if(ar.Y > highesty){
				highesty = ar.Y;
			}
			if(ar.Y < lowesty){
				lowesty = ar.Y;
			}
		}
		for(Area ar : Areas){
			if(ar.X > highestx){
				highestx = ar.X;
			}
			if(ar.X < lowestx){
				lowestx = ar.X;
			}
		}
		if(y > highesty){
			highesty = y;
		}
		if(y < lowesty){
			lowesty = y;
		}
		if(y > highestx){
			highestx = x;
		}
		if(y < lowestx){
			lowestx = x;
		}
		
		int depthX = Math.abs(highestx - lowestx);
		int depthY = Math.abs(highesty - lowesty);
		
		if(depthX > MaxXDepth){ return false;}
		if(depthY > MaxYDepth){ return false;}
		
		return allowed;
	}
	
	
}
