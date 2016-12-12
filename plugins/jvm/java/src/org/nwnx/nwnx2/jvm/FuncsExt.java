package org.nwnx.nwnx2.jvm;



public class FuncsExt {

	public static int GetSurface(NWLocation lLocation) {
	    NWObject oArea = NWScript.getAreaFromLocation(lLocation);
	   // NWScript.printString(NWScript.getName(oArea, false));
	    NWVector vPos = NWScript.getPositionFromLocation(lLocation); 
	    String str = Float.toString(vPos.getX())+"¬"+Float.toString(vPos.getY())+"¬"+Float.toString(vPos.getZ());
	    //NWScript.printString(str);
	    NWScript.setLocalString(oArea, "NWNX!FUNCSEXT!GETSURFACE", str.replace(" ", ""));
	    
	    
	    String sRet = NWScript.getLocalString(oArea, "NWNX!FUNCSEXT!GETSURFACE");
	    //NWScript.printString(sRet);
	    
	    try{
	    	return Integer.valueOf(sRet);
	    }
	    catch(Exception ee){
	    	return 0;
	    }finally
	    {
	    	NWScript.deleteLocalString(oArea, "NWNX!FUNCSEXT!GETSURFACE");
	    }
	}
	
}
