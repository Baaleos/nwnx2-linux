package org.nwnx.nwnx2.jvm;

public class Areas {
	
	/*
	 * Creates an area with the resref specified
	 */
	public static final NWObject loadArea(String s){
		NWScript.setLocalString(NWObject.MODULE, "NWNX!AREAS!CREATE_AREA",s);
		return NWScript.getLocalObject(NWObject.MODULE, "NWNX!AREAS!GET_LAST_AREA_ID");
	}
	
	/*
	 * Set an areas name
	 */
	public static void setAreaName(final NWObject area, String name){
		NWScript.setLocalString(area, "NWNX!AREAS!SET_AREA_NAME",name);
	}
	
	public static void destroyArea(final NWObject area){
		String hex = Integer.toHexString(area.getObjectId());
		NWScript.setLocalString(NWObject.MODULE, "NWNX!AREAS!DESTROY_AREA",hex);
	}
	
	
}
