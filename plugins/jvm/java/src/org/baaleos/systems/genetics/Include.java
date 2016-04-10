package org.baaleos.systems.genetics;

import org.nwnx.nwnx2.jvm.NWEffect;
import org.nwnx.nwnx2.jvm.NWLocation;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.NWVector;
import org.nwnx.nwnx2.jvm.Scheduler;
import org.nwnx.nwnx2.jvm.constants.Effect;
import org.nwnx.nwnx2.jvm.constants.DurationType;
import com.sun.org.apache.xerces.internal.impl.Constants;

public class Include {

	private static NWObject theGeneticApplier;
	private static NWObject GetGeneticEffectCreator(){
		return NWScript.getObjectByTag("genetic_unit_test", 1);
	}
	private static final int CONDITION_IGNORE = 2;
	private static final int CONDITION_SURFACE_WATER = 6;
	private static final int DAMAGE_POWER_ENERGY = 6;
	
	
	static NWEffect GetEffectFromID(int EffectID, int Value1, int Value2)
	{
	   NWEffect iEffect = null;
	   switch(EffectID)
	   {
	      case Effect.TYPE_ARCANE_SPELL_FAILURE: iEffect = NWScript.effectSpellFailure(Value1, Value2); break;
	      case Effect.TYPE_BLINDNESS: iEffect = NWScript.effectBlindness(); break;
	      case Effect.TYPE_CHARMED: iEffect = NWScript.effectCharmed();break;
	      case Effect.TYPE_CONCEALMENT: iEffect = NWScript.effectConcealment(Value1, Value2); break;
	      case Effect.TYPE_CONFUSED: iEffect = NWScript.effectConfused(); break;
	      case Effect.TYPE_CUTSCENEGHOST: iEffect = NWScript.effectCutsceneGhost(); break;
	      case Effect.TYPE_HASTE: iEffect = NWScript.effectHaste(); break;
	      case Effect.TYPE_IMMUNITY: iEffect = NWScript.effectImmunity(Value1); break;
	      case Effect.TYPE_IMPROVEDINVISIBILITY: iEffect = NWScript.effectInvisibility(Effect.TYPE_IMPROVEDINVISIBILITY); break;
	      case Effect.TYPE_INVISIBILITY: iEffect = NWScript.effectInvisibility(Effect.TYPE_INVISIBILITY); break;
	      case Effect.TYPE_MISS_CHANCE: iEffect = NWScript.effectMissChance(Value1, Value2); break;
	      case Effect.TYPE_MOVEMENT_SPEED_DECREASE: iEffect = NWScript.effectMovementSpeedDecrease(Value1); break;
	      case Effect.TYPE_MOVEMENT_SPEED_INCREASE: iEffect = NWScript.effectMovementSpeedIncrease(Value2); break;
	      case Effect.TYPE_POLYMORPH: iEffect = NWScript.effectPolymorph(Value1, Value2==1);   break;
	      case Effect.TYPE_REGENERATE: iEffect = NWScript.effectRegenerate(Value1, Value2); break;
	      case Effect.TYPE_SANCTUARY: iEffect = NWScript.effectSanctuary(Value1); break;
	      case Effect.TYPE_SLOW: iEffect = NWScript.effectSlow();break;
	      case Effect.TYPE_TEMPORARY_HITPOINTS: iEffect = NWScript.effectTemporaryHitpoints(Value1); break;
	      case Effect.TYPE_TRUESEEING: iEffect = NWScript.effectTrueSeeing(); break;
	      case Effect.TYPE_ULTRAVISION: iEffect = NWScript.effectUltravision(); break;
	      case Effect.TYPE_VISUALEFFECT: iEffect = NWScript.effectVisualEffect(Value1, Value2==1); break;
	      case Effect.TYPE_DAMAGE_IMMUNITY_INCREASE: iEffect = NWScript.effectDamageImmunityIncrease(Value1, Value2); break;
	      case Effect.TYPE_DAMAGE_IMMUNITY_DECREASE: iEffect = NWScript.effectDamageImmunityDecrease(Value1, Value2); break;
	      //default: iEffect; break;
	  }
	  return iEffect;
	}
	
	
	static boolean HasEffectAlready(NWObject oPC, int EffectType){
		
		NWEffect[] ee = NWScript.getEffects(oPC);
		for(NWEffect e : ee){
			
			int effType = NWScript.getEffectType(e);
			NWObject oCreator = e.creator();
			if(effType == EffectType && oCreator == GetGeneticEffectCreator()){
				return true;
			}
		}
		return false;
	}
	
	static void RemoveGeneticEffect(NWObject oPC, int EffectType){
		
		NWEffect[] ee = NWScript.getEffects(oPC);
		for(NWEffect e : ee){
			
			int effType = NWScript.getEffectType(e);
			boolean IsGenetic = (GetGeneticEffectCreator() == e.creator());
			if(IsGenetic && effType == EffectType){
				NWScript.removeEffect(oPC,e);
			}
			
		}
	}
	
	public static void SetEffectCreator (NWEffect eEffect, NWObject oCreator) {
		if(oCreator.equals(null)){
			NWScript.printString("Object equals null");
			return;
		}
		NWScript.printString("Attempting to set Effect Creator as "+Integer.toHexString(oCreator.getObjectId()));
		NWScript.setLocalString(NWObject.MODULE, "NWNX!STRUCTS!SETCREATOR", Integer.toHexString(oCreator.getObjectId()));
		//NWScript.setLocalObject(NWObject.MODULE, "ARGUMENT1", oCreator);
	    //NWScript.executeScript("jvm_seteffcre", oCreator);
	    //NWScript.deleteLocalObject(NWObject.MODULE, "ARGUMENT1");
	}
	
	public static void ApplyEffectByGeneticCreator(final NWEffect effect,final int dType, final float f, final NWObject target){
		
		NWObject obj = GetGeneticEffectCreator();
		Scheduler.assign(obj, new Runnable() { 
			  public void run() { 
				   	NWScript.applyEffectToObject(dType, effect, target, f);
				  } 
				});
		//Scheduler.flushQueues();
		
	}
	
	
	
	public static void HeartbeatProcessGene(NWObject oPC, Gene theGene, int TimeOfDay, NWObject oArea,
											int iIsInWater, int areaLocation, int interior, int natural){
		
		if(oArea == NWObject.INVALID) { return;} //Don't do anything on this heartbeat until we materialize
		int Apply = 0;
		
		
		if(theGene.getAlwaysActive()){
			Apply = 5;
			NWScript.printString("Always active detected!");
		}else{
			int geneAboveGround = theGene.getEnvironmentAboveGround();
			int geneInterior = theGene.getEnvironmentInterior();
			int geneNatural = theGene.getEnvironmentNatural();
			int TileType = theGene.getEnvironmentTilesetType();
			if(geneAboveGround == CONDITION_IGNORE){
				geneAboveGround = areaLocation;
			}
			if(geneInterior == CONDITION_IGNORE){
				geneInterior = interior;
			}
			if(geneNatural == CONDITION_IGNORE){
				geneNatural = natural;
			}
			switch(TileType){
				case CONDITION_IGNORE:
					//Just set to 1
					TileType = 1;
				break;
				case CONDITION_SURFACE_WATER:
					TileType = iIsInWater;//IsInWater(l);
				break;
		}
		Apply = (geneAboveGround == areaLocation) && (geneInterior == interior) && (geneNatural == natural) && (TileType >= 1) ? 5:0;
		NWScript.printString("Apply is equal to "+Apply);
		
		}
		
		if(Apply == 5){
			//Conditions met!
			int iDamageToApply = theGene.getApplyDamageAmount();
			NWEffect eEffect;
			NWObject oEffectCreator = oPC;
			if(oEffectCreator.valid()){
				NWScript.printString("Object is valid!");
			
			}
			if(iDamageToApply > 0){
				//Damage effect
				int iDamageType = theGene.getDamageType();
				
				eEffect = NWScript.effectDamage(iDamageToApply,iDamageType,DAMAGE_POWER_ENERGY); //IRRESISTABLE
				//SetEffectCreator(eEffect, oEffectCreator);
				ApplyEffectByGeneticCreator(eEffect,DurationType.INSTANT, 0.00f, oPC);
				//NWScript.applyEffectToObject(DurationType.INSTANT,eEffect,oPC,0.00f);
				
			}else{
				NWScript.printString("Attempting to apply effect!");
				if(!HasEffectAlready(oPC,theGene.getEffectType())){
					//WriteTimestampedLogEntry("Does not have effect already: Applying new");
					eEffect = GetEffectFromID(theGene.getEffectType(), theGene.getEffectNumber1(), theGene.getEffectNumber2());
					//SetEffectCreator (eEffect, oEffectCreator);
					ApplyEffectByGeneticCreator(eEffect,DurationType.PERMANENT, 0.00f, oPC);
					//NWScript.applyEffectToObject(DurationType.PERMANENT,eEffect,oPC,0.00f);
				}
			}
			
		}else{
			//Inactive - maybe remove effects and feats?
			RemoveGeneticEffect(oPC, theGene.getEffectType());
		}
		
		
	}
	
	
	static int GetCurrentTime()
	{
	   if(NWScript.getIsNight() || NWScript.getIsDusk())
	   {
	       return TIME_NIGHT;
	   }
	   return TIME_DAY;
	}
	private static final int TIME_DAY = 1;
	private static final int TIME_NIGHT = 2;
	private static final int TIME_BOTH = 0;
	
	
	private static int IsInWater(NWLocation l){
		int TileType = GetSurface(l);
		if(TileType == 6 || TileType == 11 || TileType == 17){
			return 1;
		}
		return 0;
	}
	
	
	static int GetSurface(NWLocation lLocation) {
	    NWObject oArea = NWScript.getAreaFromLocation(lLocation);
	    NWVector vPos = NWScript.getPositionFromLocation(lLocation);

	    NWScript.setLocalString(oArea, "NWNX!FUNCSEXT!GETSURFACE", Float.toString(vPos.getX())+"?"+Float.toString(vPos.getY())+"?"+Float.toString(vPos.getZ()));
	    String sRet = NWScript.getLocalString(oArea, "NWNX!FUNCSEXT!GETSURFACE");
	    NWScript.deleteLocalString(oArea, "NWNX!FUNCSEXT!GETSURFACE");
	    try{
	    return Integer.valueOf(sRet);
	    }
	    catch(Exception ee){
	    	return 0;
	    }
	}
	
	
	
	public static void ProcessPlayer(NWObject player, int timeOfDayCurrent){
		Genome genome = new Genome(player);
		NWScript.printString("Processing "+NWScript.getName(player, false));
		ProcessGenome(player, genome, timeOfDayCurrent);
	}
	
	public static void ProcessGenome(NWObject player, Genome genome, int TimeOfDayCurrent){
		
		int i = 1;
		NWObject oArea = NWScript.getArea(player);
		NWLocation l = NWScript.getLocation(player);
		int iIsInWater = IsInWater(l);
		int AreaLocation = NWScript.getIsAreaAboveGround(oArea)==true ? 1:0;
		int Interior = NWScript.getIsAreaInterior(oArea) ==true ? 1:0;
		int Natural = NWScript.getIsAreaNatural(oArea) ==true ? 1:0;
		for(Gene g : genome){
			HeartbeatProcessGene(player,g, TimeOfDayCurrent,oArea,iIsInWater,AreaLocation,Interior,Natural);
		}
	}
	private static final String CREATURES_AS_SIMULATED_PC_COUNT = "creature_simulated_pc";
	private static final String SIMULATED_PC = "simulated_pc_creature_";
	public static void GeneticsLoop(){
		
		while(true){
			NWObject[] obj = NWScript.getPCs();
			int TimeOfDayCurrent = GetCurrentTime();
			for(NWObject PC: obj){
				ProcessPlayer(PC, TimeOfDayCurrent);
			}
			
			int iCreaturesAsSimulatedPCs = NWScript.getLocalInt(NWObject.MODULE,CREATURES_AS_SIMULATED_PC_COUNT);
	        //iCreaturesAsSimulatedPCs++;
			if(iCreaturesAsSimulatedPCs > 0){
		        for(int i=1;i<=iCreaturesAsSimulatedPCs;i++){
		        	NWObject npc = NWScript.getLocalObject(NWObject.MODULE,SIMULATED_PC+i );
		        	if(npc.valid()){
		        		ProcessPlayer(npc, TimeOfDayCurrent);
		        	}
		        }
	        	
	        }
	       
	        
			
			
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
