package org.baaleos.systems.genetics;

public class GeneticsHeartbeat implements Runnable {

	
	
	/**
	 * Run method will kick off the GeneticsLoop on a new thread
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			//NWScript.printString("Genetics Loop!!");
			Include.GeneticsLoop();
			
		
	}

}
