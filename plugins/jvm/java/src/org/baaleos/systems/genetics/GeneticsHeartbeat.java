package org.baaleos.systems.genetics;

import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.Scheduler;

public class GeneticsHeartbeat implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			//NWScript.printString("Genetics Loop!!");
			Include.GeneticsLoop();
			
		
	}

}
