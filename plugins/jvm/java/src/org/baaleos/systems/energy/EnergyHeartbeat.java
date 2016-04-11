package org.baaleos.systems.energy;

import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.Scheduler;

public class EnergyHeartbeat implements Runnable {

	@Override
	public void run() {
		
			EnergyInc.EnergyHeartbeat();
		
	}

}
