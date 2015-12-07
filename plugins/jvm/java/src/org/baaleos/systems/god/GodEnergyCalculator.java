package org.baaleos.systems.god;
import java.util.Set;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.Scheduler;


public class GodEnergyCalculator  {
	public GodEnergyCalculator(NWObject mod){
		module = mod;
	}
	private static NWObject module;
	private static DoWork worker;
	public static void askModuleToDoWork(NWObject objMod){
		module = objMod;
		worker = new DoWork(module);
		new Thread(worker).start();
		
		
	}
	
	public static class DoWork implements Runnable{
		private NWObject module;
		public DoWork(NWObject mod){
			module = mod;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i =0;i<=50;i++){
				System.out.println("Running from other thread!");
			}
		}
		
	}

	
	
	
}
