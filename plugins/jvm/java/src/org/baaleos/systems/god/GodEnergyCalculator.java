package org.baaleos.systems.god;
import java.util.Set;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.Scheduler;


public class GodEnergyCalculator implements Runnable  {
	public GodEnergyCalculator(){
		//module = mod;
	}
	
	public void run()
	{
		NWScript.printString("Starting While loop");
	
		while(true){
			Scheduler.assign(module, new Runnable() { 
				@Override
	            public void run() { 
				      NWScript.printString("This came from another thread"); 
	            } 
	         });
			Scheduler.flushQueues();
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		
	}
	
	
	private static final NWObject module = new NWObject(0);
	
	public static void askModuleToDoWork(NWObject objMod){
		GodEnergyCalculator gCalc = new GodEnergyCalculator();
		Thread t = new Thread(gCalc);
		t.start();
		
		
	}
	
}

	
	
	

