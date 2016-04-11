package org.nwnx.nwnx2.jvm;

import java.util.ArrayList;
import java.util.List;

import org.baaleos.systems.energy.EnergyHeartbeat;
import org.baaleos.systems.energy.EnergyInc;
import org.baaleos.systems.genetics.GeneticsHeartbeat;
import org.baaleos.systems.genetics.Include;
import org.baaleos.systems.god.GodEnergyCalculator;
import org.baaleos.systems.server.StaticContainer;
import org.nwnx.nwnx2.jvm.ScheduledEvery.Policy;
import org.nwnx.nwnx2.jvm.constants.*;

public class TestRunner {

	public static void init() {
		System.out.println("Initializing TestRunner. This class runs various sanity tests and benchmarks.");
		System.out.println("If anything in this class makes your server crash, something is wrong and NEEDs to be fixed!");
		System.out.println("You need to load a module that has at least one event firing on a creature sometime.");
		System.out.println("Initializing the TestRunner!!");
		
		
			
		NWObject.setObjectInvalidIsNull(true);
		System.out.println("Passed the Object Invalid Is Null");
		NWObject.registerObjectHandler(new NWObject.ObjectHandler() {
		    public NWObject handleObjectClass(NWObject obj, boolean valid,
		            int objectType, String resRef, String tag) {
		        return obj;
		    }
		});
		
		System.out.println("Registered Object Handler");
		NWEffect.registerEffectHandler(new NWEffect.EffectHandler() {
		    public NWEffect handleEffectClass(NWEffect eff) {
		        return eff;
		    }
		});
		System.out.println("Registered Effect Handler");
		NWItemProperty.registerItemPropertyHandler(new NWItemProperty.ItemPropertyHandler() {
		    public NWItemProperty handleItemPropertyClass(NWItemProperty prp) {
		        return prp;
		    }
		});
		System.out.println("Registered Item Property Handler");
		ResMan.addResManListener(new ResManListener() {
			@Override
			public byte[] demandRes(String resRef) {
				System.out.println("Demand ResRef: " + resRef);
				if (resRef.equals("resmantest.2da")) {
					System.out.println("Returning our own 2da table!");
					return "2DA V2.0\r\n\r\n     A B\r\n0 a1 b1\r\n1 a2 b2\r\n".getBytes();
				}
				return null;
			}
		});
		System.out.println("Registered DemandRes handler");

		Scheduler.addSchedulerListener(new SchedulerListener() {

			@Override
			public void postFlushQueues(int remainingTokens) {
			}

			@Override
			public void missedToken(NWObject objSelf, String token) {
			}

			@Override
			public void event(NWObject objSelf, String event) {
				
				try{
					if(event.equals("OnModuleLoad")){
						long startbench = System.currentTimeMillis();
						String modName = "";
						//Start genetics Loop
					}
					if(event.equals("OnModuleLoadGenetics")){
						NWObject.setObjectInvalidIsNull(true);
						//ScheduledAnon ;//anon = ScheduledAnon
						//ScheduledEvery every = new ScheduledEvery(7000, null, Policy.AS_AVAILABLE);
						System.out.println("Starting HB");
						objSelf.assign(new GeneticsHeartbeat());
						System.out.println("Finished HB");  
						
						
						//objToUse.assign(new GeneticsHeartbeat());
						//NWObject objRunner = Include.GetGeneticEffectCreator();
						String s = NWScript.getName(objSelf, false);
						System.out.println(s+" is running event");
						//objSelf.assign(new GeneticsHeartbeat());
						Scheduler.flushQueues();
					}
					if(event.equals("Run_Genetics_HB")){
						NWObject.setObjectInvalidIsNull(true);
						//ScheduledAnon ;//anon = ScheduledAnon
						//ScheduledEvery every = new ScheduledEvery(7000, null, Policy.AS_AVAILABLE);
						System.out.println("Starting HB");
						objSelf.assign(new GeneticsHeartbeat());
						System.out.println("Finished HB");  
						
						
						//objToUse.assign(new GeneticsHeartbeat());
						//NWObject objRunner = Include.GetGeneticEffectCreator();
						//String s = NWScript.getName(objSelf, false);
						//System.out.println(s+" is running event");
						//objSelf.assign(new GeneticsHeartbeat());
						Scheduler.flushQueues();
					}
					if(event.startsWith("SetupEnergy_")){
						NWObject.setObjectInvalidIsNull(true);
						String energyName = event.replace("SetupEnergy_", "");
						EnergyInc.CreateEnergy(energyName);
					}
					if(event.equals("ListEnergyTypes")){
						NWObject.setObjectInvalidIsNull(true);
						
						EnergyInc.ListEnergyTypes();
					}
					if(event.equals("StartEnergyHB")){
						NWObject.setObjectInvalidIsNull(true);
						(new Thread(new EnergyHeartbeat())).start();
						//EnergyInc.ListEnergyTypes();
					}
					String name = NWScript.getName(objSelf, false);
					int objType = NWScript.getObjectType(objSelf);
					System.out.println("event on " + objSelf.getObjectId() + ": " + event + ", name = " + name + ", type = " + objType);
					
					

					if (objType == ObjectType.CREATURE) {
						switch(event)
						{
							case "client_enter":
								
								//Player Enter server
								StaticContainer.onPlayerEnter(objSelf);
								break;
						}


						
					}
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
				
			}

			@Override
			public void context(NWObject objSelf) {
			}
		});
	}

	@SuppressWarnings("unused")
	private static void setup() {
		System.out.println("Test Runner Class is running!!");
	}

	@SuppressWarnings("unused")
	private static void shutdown() {
		System.out.println("Shutdown! Byebye, thanks for all the fish!");
	}
}
