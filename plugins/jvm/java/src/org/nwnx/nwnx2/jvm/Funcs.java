package org.nwnx.nwnx2.jvm;

public class Funcs {
	
	
	

	private static int NWNXFuncsZero (NWObject oObject, String sFunc) {
	    NWScript.setLocalString(oObject, sFunc, "          ");
	    return Integer.valueOf(NWScript.getLocalString(oObject, sFunc));
	}
	
	private static int NWNXFuncsOne (NWObject oObject, String sFunc, int nVal1) {
	    NWScript.setLocalString(oObject, sFunc, String.valueOf(nVal1) + "          ");
	    return Integer.valueOf(NWScript.getLocalString(oObject, sFunc));
	}
	
	private static int NWNXFuncsTwo (NWObject oObject, String sFunc, int nVal1, int nVal2) {
		NWScript.setLocalString(oObject, sFunc, String.valueOf(nVal1) + " " + String.valueOf(nVal2) + "          ");
	    return Integer.valueOf(NWScript.getLocalString(oObject, sFunc));
	}
	
	private static int NWNXFuncsThree (NWObject oObject, String sFunc, int nVal1, int nVal2, int nVal3) {
		NWScript.setLocalString(oObject, sFunc, String.valueOf(nVal1) + " " + String.valueOf(nVal2) +
	      " " + String.valueOf(nVal3) + "          ");
	    return Integer.valueOf(NWScript.getLocalString(oObject, sFunc));
	}
	
	public static int SetAbilityScore (NWObject oCreature, int nAbility, int nValue) {
	    return NWNXFuncsTwo(oCreature, "NWNX!FUNCS!SETABILITYSCORE", nAbility, nValue);
	}
	
	public static int ModifyAbilityScore (NWObject oCreature, int nAbility, int nValue) {
	    return NWNXFuncsTwo(oCreature, "NWNX!FUNCS!MODIFYABILITYSCORE", nAbility, nValue);
	}
	

	public static int  GetKnowsFeat (int nFeatId, NWObject oCreature) {
	    return NWNXFuncsOne(oCreature, "NWNX!FUNCS!GETKNOWSFEAT", nFeatId);
	}
	
	public static int  AddKnownFeat (NWObject oCreature, int nFeat, int nLevel) {
	    if (nLevel == 0)
	        nLevel = NWScript.getHitDice(oCreature);
	
	    if (nLevel > 0)
	        return NWNXFuncsTwo(oCreature, "NWNX!FUNCS!ADDKNOWNFEATATLEVEL", nLevel, nFeat);
	
	    return NWNXFuncsOne(oCreature, "NWNX!FUNCS!ADDKNOWNFEAT", nFeat);
	}

	public static int  RemoveKnownFeat (NWObject oCreature, int nFeat) {
	    return NWNXFuncsOne(oCreature, "NWNX!FUNCS!REMOVEKNOWNFEAT", nFeat);
	}


	public static int  GetTotalKnownFeats (NWObject oCreature) {
	    return NWNXFuncsZero(oCreature, "NWNX!FUNCS!GETTOTALKNOWNFEATS");
	}

	public static int  GetKnownFeat (NWObject oCreature, int nIndex) {
	    return NWNXFuncsOne(oCreature, "NWNX!FUNCS!GETKNOWNFEAT", nIndex);
	}

	public static int  SetKnownFeat (NWObject oCreature, int nIndex, int nFeat) {
	    return NWNXFuncsTwo(oCreature, "NWNX!FUNCS!SETKNOWNFEAT", nIndex, nFeat);
	}
	
	public static String GetPortrait (NWObject oCreature) {
		NWScript.setLocalString(oCreature, "NWNX!FUNCS!GETPORTRAIT", "                    ");
	    return NWScript.getLocalString(oCreature, "NWNX!FUNCS!GETPORTRAIT");
	}
	
	public static int SetPortrait (NWObject oCreature, String sPortrait) {
		NWScript.setLocalString(oCreature, "NWNX!FUNCS!SETPORTRAIT", sPortrait);
	    return Integer.valueOf(NWScript.getLocalString(oCreature, "NWNX!FUNCS!SETPORTRAIT"));
	}
	
	
	public static int GetSoundset (NWObject oCreature) {
	    return NWNXFuncsZero(oCreature, "NWNX!FUNCS!GETSOUNDSET");
	}
	
	public static int SetSoundset (NWObject oCreature, int nSoundset) {
	    return NWNXFuncsOne(oCreature, "NWNX!FUNCS!SETSOUNDSET", nSoundset);
	}

	
}
