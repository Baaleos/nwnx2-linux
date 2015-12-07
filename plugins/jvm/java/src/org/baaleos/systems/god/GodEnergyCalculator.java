package org.baaleos.systems.god;
import java.util.Set;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.Scheduler;


public class GodEnergyCalculator  {
	public GodEnergyCalculator(NWObject mod){
		module = mod;
	}
	private static NWObject module;
	
	public static void askModuleToDoWork(NWObject objMod){
		Scheduler.assign(module, new DoWork(module) { 
			   public void e() { 
			      NWScript.speakString("hi", 0); 
			      System.out.println("Testing on thread!!");
			   } 
			});
		
		
	}
	
}

	
	
	

