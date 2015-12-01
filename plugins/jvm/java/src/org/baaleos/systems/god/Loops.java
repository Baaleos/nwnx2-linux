package org.baaleos.systems.god;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

import org.baaleos.systems.server.StaticContainer;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;
public class Loops {
	public static void doModuleEnergyCalculations(){
		
		
	}
	
	private static void energyWork() throws Exception{
		

        Set<String> keys = NWGod.getListOfGods().keySet();
        for(String key: keys){
           NWGod god = NWGod.getListOfGods().get(key);
           if(god.IsValid()){
        	   god.doGodEnergyCalculations();
           }
        }

	}
}
