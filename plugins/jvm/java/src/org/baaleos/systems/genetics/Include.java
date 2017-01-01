package org.baaleos.systems.genetics;

import java.util.ArrayList;

import org.baaleos.systems.energy.Energy;
import org.nwnx.nwnx2.jvm.*;
import org.nwnx.nwnx2.jvm.constants.ColorChannel;
import org.nwnx.nwnx2.jvm.constants.DurationType;
import org.nwnx.nwnx2.jvm.constants.Effect;
import org.nwnx.nwnx2.jvm.constants.*;
import com.sun.org.apache.xerces.internal.impl.Constants;

public class Include {

	private static NWObject theGeneticApplier;
	
	/**
	 * Used to return the genetics creature
	 * This creature runs the heartbeat (pseudo-heartbeat).
	 * @return
	 */
	public static NWObject GetGeneticEffectCreator(){
		return NWScript.getObjectByTag("genetic_unit_test", 1);
	}
	private static final int CONDITION_IGNORE = 2;
	private static final int CONDITION_SURFACE_WATER = 6;
	private static final int DAMAGE_POWER_ENERGY = 6;
	
	
	/**
	 * Generates a Supernatural Effect of the specified effect and subtype
	 * @param EffectID
	 * @param Value1
	 * @param Value2
	 * @return
	 */
	private static NWEffect GetEffectFromID(int EffectID, int Value1, int Value2)
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
	      case Effect.TYPE_DAMAGE_INCREASE: iEffect = NWScript.effectDamageIncrease(Value1, Value2);break;
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
	      //default: iEffect = -1; break;
	      default: return null;
	  }
	  return NWScript.supernaturalEffect(iEffect);
	}
	
	/**
	 * Determines if the creature has the specified genetic effect type
	 * Eg: Invisibility
	 * @param oPC
	 * @param EffectType
	 * @return
	 */
	private static boolean HasEffectAlready(NWObject oPC, Gene theGene){
		
		NWEffect[] ee = NWScript.getEffects(oPC);
		NWObject effectCreator = GetGeneticEffectCreator();
		for(NWEffect e : ee){
			
			int effType = NWScript.getEffectType(e);
			int subType = NWScript.getEffectSubType(e);
			NWObject oCreator = e.creator();
			if(effType == theGene.getEffectType() && oCreator == effectCreator && subType == Subtype.SUPERNATURAL){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Removes effects marked as genetic (created by genetics creature)
	 * and are also of the correct type
	 * @param oPC
	 * @param EffectType
	 */
	private static void RemoveGeneticEffect(NWObject oPC, Gene theGene){
		
		
		NWEffect[] ee = NWScript.getEffects(oPC);
		NWObject effectCreator = GetGeneticEffectCreator();
		String tagOfCreatorObject = NWScript.getTag(effectCreator);
		for(NWEffect e : ee){
			String tagOfCreator = NWScript.getTag(e.creator());
			NWScript.sendMessageToPC(oPC,tagOfCreator+" was the creator of this effect");
			NWScript.sendMessageToPC(oPC,effectCreator+" is what it needed to be");
			int effType = NWScript.getEffectType(e);
			int subType = NWScript.getEffectSubType(e);
			boolean IsGenetic = (effectCreator == e.creator());
			NWScript.sendMessageToPC(oPC,"IsGenetic was "+IsGenetic);
			NWScript.sendMessageToPC(oPC,"Type found: "+effType+" Subtype found:"+subType+" needed was :"+theGene.getEffectType()+" and "+subType);
			
			
			if(IsGenetic && effType == theGene.getEffectType() && subType == Subtype.SUPERNATURAL){
				NWScript.sendMessageToPC(oPC,"A genetic effect has turned off.");
				NWScript.removeEffect(oPC,e);
			}
			
		}
	}
	
	/**
	 * Works - however, seems to have stability issues when called on background threads.
	 * Instead of using this, AssignCommand - to get a creature to create and apply the effect
	 * @param eEffect
	 * @param oCreator
	 */
	private static void SetEffectCreator (NWEffect eEffect, NWObject oCreator) {
		if(oCreator.equals(null)){
			NWScript.printString("Object equals null");
			return;
		}
		//NWScript.printString("Attempting to set Effect Creator as "+Integer.toHexString(oCreator.getObjectId()));
		NWScript.setLocalString(NWObject.MODULE, "NWNX!STRUCTS!SETCREATOR", Integer.toHexString(oCreator.getObjectId()));
		//NWScript.setLocalObject(NWObject.MODULE, "ARGUMENT1", oCreator);
	    //NWScript.executeScript("jvm_seteffcre", oCreator);
	    //NWScript.deleteLocalObject(NWObject.MODULE, "ARGUMENT1");
	}
	
	
	/**
	 * Method is somewhat redundant, now that the Heartbeat is fully executed by the Genetics NPC.
	 * The effects are all created by default by this creature, allowing us to identify effects 
	 * generated by genes
	 * @param effect
	 * @param dType
	 * @param f
	 * @param target
	 */
	private static void ApplyEffectByGeneticCreator(final NWEffect effect,final int dType, final float f, final NWObject target){
		NWEffect effectSupernatural = NWScript.supernaturalEffect(effect);
		NWScript.applyEffectToObject(dType, effect, target, f);
	}
	
	
	/**
	 * Removes genetic appearance data to the player.
	 * Uses the default appearance data stored on the first invocation of the ApplyGeneAppearance method.
	 * called from the heartbeat 
	 * @param oPC
	 * @param theGene
	 */
	private static void RemoveGeneAppearanceData(final NWObject oPC, final Gene theGene){
		
		int CurrentAppearance = NWScript.getAppearanceType(oPC);
		int HairColor = NWScript.getColor(oPC, ColorChannel.HAIR);
		int SkinColor = NWScript.getColor(oPC,ColorChannel.SKIN);
		
		int OriginalAppearance = NWScript.getLocalInt(oPC, "ORIGINAL_APPEARANCE_");
		int OriginalHair = NWScript.getLocalInt(oPC, "ORIGINAL_COLOR_"+ColorChannel.HAIR);
		int OriginalSkin = NWScript.getLocalInt(oPC, "ORIGINAL_COLOR_"+ColorChannel.SKIN);
		
		int GeneColorSkin = theGene.getSkinColor();
		int GeneColorHair = theGene.getHairColor();
		int GeneAppearance = theGene.getAppearance();
		
		
		
		if(CurrentAppearance == GeneAppearance && GeneAppearance > 0){
			NWScript.setCreatureAppearanceType(oPC,OriginalAppearance);
			NWScript.printString("Removing Appearance"+GeneAppearance+" back to "+OriginalAppearance);
		}
		if(HairColor == GeneColorHair && GeneColorHair > 0){
			NWScript.setColor(oPC, ColorChannel.HAIR, OriginalHair);
			NWScript.printString("Removing Hair"+GeneColorHair+" back to "+OriginalHair);
		}
		if(SkinColor == GeneColorSkin && GeneColorSkin > 0){
			NWScript.setColor(oPC, ColorChannel.SKIN, OriginalSkin);
			NWScript.printString("Removing Skin"+GeneColorSkin+" back to "+OriginalSkin);
		}
	}
	
	/**
	 * Applies genetic appearance data to the player.
	 * Also stores their default appearance data, this is used to revert their appearance
	 * When the genes conditions are no longer met.
	 * Eg: Might have dark skin in Night, but that gene would turn off in day
	 * Reverting back to pink/normal skin color.
	 * Note: Will need to update this to use DB storage to account for instances where the 
	 * player will logout and then server restarts while they are transformed.
	 * Eg: We don't want to store their changed appearance over their default.
	 * Update: Should now use setPersistent/getPersistent int to store original values
	 * @param oPC
	 * @param theGene
	 */
	private static void ApplyAppearanceData(final NWObject oPC, final  Gene theGene){
		
		
		int iHairStored = NWScript.getLocalInt(oPC, "HAIR_COLOR_DEFAULT_DONE");
		int iSkinStored = NWScript.getLocalInt(oPC, "SKIN_COLOR_DEFAULT_DONE");
		int iAppearanceStored = NWScript.getLocalInt(oPC, "APPEARANCE_DEFAULT_DONE");
		
		
		
		int CurrentAppearance = NWScript.getAppearanceType(oPC);
		int HairColor = NWScript.getColor(oPC, ColorChannel.HAIR);
		int SkinColor = NWScript.getColor(oPC,ColorChannel.SKIN);
		
		
		if(iHairStored==0){
			int i = Odbc.getPersistentInt(oPC, "HAIR_COLOR_DEFAULT_DONE", "pwdata");
			if(i==0){
				Odbc.setPersistentInt(oPC, "HAIR_COLOR_DEFAULT_DONE",1, 0, "pwdata");
				Odbc.setPersistentInt(oPC, "ORIGINAL_COLOR_"+ColorChannel.HAIR,HairColor,0, "pwdata");
				i = HairColor;
			}else{
				i =  Odbc.getPersistentInt(oPC, "ORIGINAL_COLOR_"+ColorChannel.HAIR, "pwdata");
			}
			//Set the original color
			NWScript.setLocalInt(oPC, "ORIGINAL_COLOR_"+ColorChannel.HAIR, i);
			NWScript.setLocalInt(oPC, "HAIR_COLOR_DEFAULT_DONE",1);
			
		}
		if(iSkinStored==0){
			int i = Odbc.getPersistentInt(oPC, "SKIN_COLOR_DEFAULT_DONE", "pwdata");
			if(i==0){
				Odbc.setPersistentInt(oPC, "SKIN_COLOR_DEFAULT_DONE",1, 0, "pwdata");
				Odbc.setPersistentInt(oPC, "ORIGINAL_COLOR_"+ColorChannel.SKIN,SkinColor,0, "pwdata");
				i = SkinColor;
			}else{
				i =  Odbc.getPersistentInt(oPC, "ORIGINAL_COLOR_"+ColorChannel.SKIN, "pwdata");
			}
			//Set the original color
			NWScript.setLocalInt(oPC, "ORIGINAL_COLOR_"+ColorChannel.SKIN, i);
			NWScript.setLocalInt(oPC, "SKIN_COLOR_DEFAULT_DONE",1);
		}
		if(iAppearanceStored==0){
			
			int i = Odbc.getPersistentInt(oPC, "APPEARANCE_DEFAULT_DONE", "pwdata");
			if(i==0){
				Odbc.setPersistentInt(oPC, "APPEARANCE_DEFAULT_DONE",1, 0, "pwdata");
				Odbc.setPersistentInt(oPC, "ORIGINAL_APPEARANCE_",CurrentAppearance,0, "pwdata");
				i = CurrentAppearance;
			}else{
				i =  Odbc.getPersistentInt(oPC, "ORIGINAL_APPEARANCE_", "pwdata");
			}
			//Set the original color
			NWScript.setLocalInt(oPC, "ORIGINAL_APPEARANCE_", i);
			NWScript.setLocalInt(oPC, "APPEARANCE_DEFAULT_DONE",1);	
		}
		//Set appearance
		if(theGene.getAppearance() > 0 && theGene.getAppearance() != CurrentAppearance){
			NWScript.setCreatureAppearanceType(oPC, theGene.getAppearance());
		}
		
		//Set HairColor
		if(theGene.getHairColor() > 0 && theGene.getHairColor() != HairColor){
			NWScript.setColor(oPC, ColorChannel.HAIR, theGene.getHairColor());
		}
		//Set HairColor
		if(theGene.getSkinColor() > 0 && theGene.getSkinColor() != SkinColor){
			NWScript.setColor(oPC, ColorChannel.SKIN, theGene.getSkinColor());
		}
	}
	
	
	//private static final int  GENE_TIME_DAY = 1;
	//private static final int  GENE_TIME_NIGHT = 2;
	//private static final int  GENE_TIME_BOTH = 0;
	//
	
	/**
	 * For every gene in the genome, we process it individually.
	 * This allows for certain genes to be active and others inactive.
	 * We process based of things like
	 * TimeOfDay, Area Attributes, is the player in water 
	 * (Note: Determining if is in water, is an expensive call: Only do this once per player)
	 * areaAboveGroundOrNot - determine if the player is currently aboveground or not.
	 * Interior - inside or not
	 * Natural or not
	 * @param oPC
	 * @param theGene
	 * @param TimeOfDay
	 * @param oArea
	 * @param iIsInWater
	 * @param areaAboveGroundOrNot
	 * @param interior
	 * @param natural
	 * @param combat
	 */
	private static void HeartbeatProcessGene(final NWObject oPC, final Gene theGene, int TimeOfDay, final NWObject oArea,
											int iIsInWater, int areaAboveGroundOrNot, int interior, int natural, boolean combat){
		
		if(oArea == NWObject.INVALID) { return;} //Don't do anything on this heartbeat until we materialize
		int Apply = 0;
		int Always = 0;
		int FeatID = theGene.getFeatID();
		int visual = -1;
		boolean combatSatisfied = true;
		try{
			Always = theGene.getAlwaysActive() ? 1:0;
			
		}catch(Exception ee){
			NWScript.printString(ee.toString());
			System.out.println(ee.toString());
			return;
		}
		
		if(Always == 1){
			Apply = 5;
			//NWScript.printString("Always active detected!");
		}else
		{
			int geneAboveGround = theGene.getEnvironmentAboveGround();
			int geneInterior = theGene.getEnvironmentInterior();
			int geneNatural = theGene.getEnvironmentNatural();
			int TileType = theGene.getEnvironmentTilesetType();
			int DayNight = theGene.getTimeOfDayActive();
			visual = theGene.getVisualEffect();
			ArrayList<EnergyCostBinding> costBindingList = theGene.getCostPerHeartbeat();
			boolean hasEnergy = false;
			int iSuccess = 0;
			//NWScript.printString("Gene:"+theGene.getID()+" has "+costBindingList.size()+" energy requirements");
			boolean onlyInCombat = theGene.isCombatOnly();
			if(onlyInCombat && !combat){
				combatSatisfied = false; //Save energy for when we are not in combat!
			}
			if(combatSatisfied){
				for(EnergyCostBinding energyCost : costBindingList){
					Energy e = energyCost.getEnergyToCharge();
					int AmountToCharge = energyCost.getAmountToCharge();
					int CurrentEnergy = e.getCurrentAmount(oPC);
					int AfterSub = CurrentEnergy - AmountToCharge;
					if(AfterSub >= 0){
						e.setCurrentAmount(oPC, AfterSub);
						//NWScript.printString("Satisfied cost for "+e.getName()+" energy.");
						iSuccess++;
					}else{
						//NWScript.printString("NOT Satisfied cost for "+e.getName()+" energy.");
					}
				}
			}
			//All Energy costs must be satisfied
			//If there is no costs- then this should auto pass
			if(iSuccess == costBindingList.size()){
				//NWScript.printString("Energy Condition Passed!");
				hasEnergy = true;
			}
			
			
			
			//NWScript.printString("geneAboveGround:"+geneAboveGround);
			if(geneAboveGround == CONDITION_IGNORE){
				geneAboveGround = areaAboveGroundOrNot;
				//NWScript.printString("geneAboveGround:"+geneAboveGround);
			}
			boolean timeSatisfied = false;
			if(DayNight == TimeOfDay || DayNight ==0){
				timeSatisfied = true;
			}
			//NWScript.printString("geneInterior:"+geneInterior);
			if(geneInterior == CONDITION_IGNORE){
				geneInterior = interior;
				//NWScript.printString("geneInterior:"+geneInterior);
			}
			//NWScript.printString("geneNatural:"+geneNatural);
			if(geneNatural == CONDITION_IGNORE){
				geneNatural = natural;
				//NWScript.printString("geneNatural:"+geneNatural);
			}
			//NWScript.printString("onlyInCombat:"+onlyInCombat);
			if(onlyInCombat == false){
				
				combat = onlyInCombat;
				//NWScript.printString("onlyInCombat:"+onlyInCombat);
			}
			
			//NWScript.printString("TileType:"+TileType);
			switch(TileType){
				case CONDITION_IGNORE:
					//Just set to 1
					TileType = 1;
				break;
				case CONDITION_SURFACE_WATER:
					TileType = iIsInWater;//IsInWater(l);
				break;
		}
			Apply = (timeSatisfied) && (geneAboveGround == areaAboveGroundOrNot) && (geneInterior == interior) && (geneNatural == natural) && (TileType >= 1) && (combat == onlyInCombat) && (hasEnergy) ? 5:0;
			NWScript.printString("Apply is equal to "+Apply);
			if(Apply != 5){
				/*NWScript.printString("Above Ground = "+(geneAboveGround == areaLocation));
				NWScript.printString("geneInterior = "+(geneInterior == interior));
				NWScript.printString("geneNatural = "+(geneNatural == natural));
				NWScript.printString("TileType = "+(TileType >= 1));
				NWScript.printString("combat = "+(combat == onlyInCombat));
				NWScript.printString("hasEnergy = "+(hasEnergy));*/
				
			}
		
		}
		
		if(Apply == 5)
		{
			//Conditions met!
			int iDamageToApply = theGene.getApplyDamageAmount();
			NWEffect eEffect;
			NWObject oEffectCreator = oPC;
			if(oEffectCreator.valid()){
				//NWScript.printString("Object is valid!");
			
			}
			if(iDamageToApply > 0){
				//Damage effect
				int iDamageType = theGene.getDamageType();
				
				eEffect = NWScript.effectDamage(iDamageToApply,iDamageType,DAMAGE_POWER_ENERGY); //IRRESISTABLE
				//SetEffectCreator(eEffect, oEffectCreator);
				ApplyEffectByGeneticCreator(eEffect,DurationType.INSTANT, 0.00f, oPC);
				//NWScript.applyEffectToObject(DurationType.INSTANT,eEffect,oPC,0.00f);
				
			}else{
				//NWScript.printString("Attempting to apply effect!");
				if(!HasEffectAlready(oPC,theGene) && theGene.getEffectType() != -1){
					//NWScript.sendMessageToPC(oPC, "Applying Genetic Effect!");
					//WriteTimestampedLogEntry("Does not have effect already: Applying new");
					eEffect = GetEffectFromID(theGene.getEffectType(), theGene.getEffectNumber1(), theGene.getEffectNumber2());
					
					if(eEffect != null){
						NWScript.sendMessageToPC(oPC, "A genetic effect has been activated!");
						if(visual > 0){
							NWEffect visualEffect = NWScript.effectVisualEffect(visual,false);
							eEffect = NWScript.effectLinkEffects(visualEffect,eEffect);
						}
						ApplyEffectByGeneticCreator(eEffect,DurationType.PERMANENT, 0.00f, oPC);
					}
					//NWScript.applyEffectToObject(DurationType.PERMANENT,eEffect,oPC,0.00f);
				}
			}
			
			if(FeatID >= 0){
				if(Funcs.GetKnowsFeat(FeatID, oPC) != 1){
					//We must apply the feat
					Funcs.AddKnownFeat(oPC, FeatID, -1); // Add the feat with no level requirement
				}
			}
			//Apply genetic appearance related stuff
			ApplyAppearanceData(oPC, theGene);
			
		}else{
			//Inactive - maybe remove effects and feats?
			RemoveGeneticEffect(oPC, theGene);
			
			//Remove the genetic appearance related stuff
			RemoveGeneAppearanceData(oPC, theGene);
			if(FeatID >= 0){
				if(Funcs.GetKnowsFeat(FeatID, oPC) == 1){
					//We must remove the feat
					Funcs.RemoveKnownFeat(oPC, FeatID); // Add the feat with no level requirement
				}
			}
		}
		
		
	}
	
	/**
	 * Returns TIME_NIGHT for Dusk or Night (from nwscript)
	 * Returns TIME_DAY for everything else
	 * @return
	 */
	public static int GetCurrentTime()
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
		int TileType = FuncsExt.GetSurface(l);
		if(TileType == 6 || TileType == 11 || TileType == 17){
			return 1;
		}
		return 0;
	}
	
	
	
	/**
	 * Invoked via a call to TestRunner/EventListener from NWScript via:
	 * StoreEnergyCostForGenePassive
	 * Must have set the following locals on module first:
	 * GENEID : Gene being targetted
	 * ENERGYID : Energy ID to charge
	 * AMOUNT : Amount the gene costs
	 * Note: This method can be called multiple times adding additional energy costs to genes.
	 * Eg: Electrical Healing: Might cost Bio and Electrical Energy.
	 * Note: Consider carefully the gene cost vs the players income of energy
	 * @param gene
	 * @param energy
	 * @param amount
	 */
	public static void AddEnergyCostToGenePassive(int gene, int energy, int amount){
		EnergyCostBinding costbinding = new EnergyCostBinding(Energy.getEnergy(energy), amount);
		Gene thegene = Gene.getGeneByID(gene);
		thegene.getCostPerHeartbeat().add(costbinding);
		NWScript.printString(thegene.getGeneName()+" now costs "+amount+" "+energy+" per hb");
	}
	
	
	/**
	 * Process player, or a Creature, treating them as a player
	 * Include timeOfDay, since this is global to the server : no need to grab it per player
	 * @param player
	 * @param timeOfDayCurrent
	 */
	private static void ProcessPlayer(NWObject player, int timeOfDayCurrent){
		Genome genome = new Genome(player);
		//NWScript.printString("Processing "+NWScript.getName(player, false));
		ProcessGenome(player, genome, timeOfDayCurrent);
	}
	
	/**
	 * Feed in the Player, the genome we grabbed for them, and the time of day
	 * @param player
	 * @param genome
	 * @param TimeOfDayCurrent
	 */
	private static void ProcessGenome(NWObject player, Genome genome, int TimeOfDayCurrent){
		
		int i = 1;
		NWObject oArea = NWScript.getArea(player);
		NWLocation l = NWScript.getLocation(player);
		int iIsInWater = IsInWater(l);
		boolean combat = NWScript.getIsInCombat(player);
		int AreaLocation = NWScript.getIsAreaAboveGround(oArea)==true ? 1:0;
		int Interior = NWScript.getIsAreaInterior(oArea) ==true ? 1:0;
		int Natural = NWScript.getIsAreaNatural(oArea) ==true ? 1:0;
		for(Gene g : genome){
			HeartbeatProcessGene(player,g, TimeOfDayCurrent,oArea,iIsInWater,AreaLocation,Interior,Natural, combat);
		}
	}
	public static final String CREATURES_AS_SIMULATED_PC_COUNT = "creature_simulated_pc";
	public static final String SIMULATED_PC = "simulated_pc_creature_";
	
	
	
	/**
	 * Kicked off via NWScript calls to JVM Run_Genetics_HB
	 * In order to keep this method stable, it is kept on the MainLoop thread
	 * It also does not recurse anymore with a while loop
	 * Each invocation of this will cover all players, and all NPC's designated to be included in the genetics system
	 */
	public static void GeneticsLoop(){
		
		//while(true){
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
	       
	        
			
			
			//try {
			//	Thread.sleep(7000);
			//} catch (InterruptedException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		//}
	}
	
}
