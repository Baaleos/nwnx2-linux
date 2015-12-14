package org.baaleos.systems.god;
import java.util.Set;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.Scheduler;


public class GodEnergyCalculator implements Runnable  {
	public GodEnergyCalculator(NWObject mod){
		//module = mod;
	}
	
	public void run()
	{
		NWScript.printString("Starting While loop");
		int i = 0;
		while(true){
			i++;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scheduler.assign(module, new Runnable() { 
				@Override
	            public void run() { 
				      NWScript.printString("This came from another thread"); 
	            } 
	         });
			Scheduler.flushQueues();
			if(i >= 100)
			{
				break;
			}
		}
		
	}
	
	
	private static final NWObject module = new NWObject(0);
	
	public static void askModuleToDoWork(NWObject objMod){
		GodEnergyCalculator gCalc = new GodEnergyCalculator(objMod);
		Thread t = new Thread(gCalc);
		t.start();
		
		
	}
	
}

	
	
	

