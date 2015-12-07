package org.baaleos.systems.god;

import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class DoWork implements Runnable {

	public DoWork(NWObject module) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			System.out.println("Running the run method!");
			 NWScript.printString("This came from run method");
	}

}
