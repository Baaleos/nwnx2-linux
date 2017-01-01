package org.baaleos.systems.genetics;

import java.util.ArrayList;
import java.util.HashMap;

import org.baaleos.systems.energy.Energy;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class Gene {
	public Gene(int geneID){
		
		
		this.GeneID = geneID;
		Initialize();
	}
private int GeneID;

public int getID(){
	return GeneID;
}

private static final String INHUMAN_POWER_STORAGE_NAME = "INHUMAN_POWER_STORE_NAME_";
private static final  String INHUMAN_POWER_STORAGE_FEATID = "INHUMAN_POWER_STORE_FEATID_";
private static final  String INHUMAN_POWER_STORAGE_IS_PASSIVE = "INHUMAN_POWER_STORE_PASSIVE_";
private static final  String INHUMAN_POWER_STORAGE_EFFECT_TYPE = "INHUMAN_POWER_STORE_EFFECT_TYPE_";
private static final  String INHUMAN_POWER_STORAGE_TIMEOFDAY = "INHUMAN_POWER_STORE_EFFECT_TIME_OF_DAY_";
private static final  String INHUMAN_POWER_STORAGE_NUMBER1 = "INHUMAN_POWER_STORE_EFFECT_NUMBER1_";
private static final  String INHUMAN_POWER_STORAGE_NUMBER2 = "INHUMAN_POWER_STORE_EFFECT_NUMBER2_";
private static final  String INHUMAN_POWER_STORAGE_LEVEL_OF_POWER = "INHUMAN_POWER_STORE_EFFECT_PWR_LVL_";
private static final  String INHUMAN_POWER_STORAGE_ENVIRONMENT_NATURAL = "INHUMAN_POWER_STORE_ENV_NATURAL_";
private static final  String INHUMAN_POWER_STORAGE_ENVIRONMENT_ABOVE_GROUND = "INHUMAN_POWER_STORE_ABOVE_GROUND_";
private static final  String INHUMAN_POWER_STORAGE_ENVIRONMENT_INTERIOR = "INHUMAN_POWER_STORE_INTERIOR_";
private static final  String INHUMAN_POWER_STORAGE_ENVIRONMENT_SURFACE_TYPE = "INHUMAN_POWER_STORE_SURFACE_TYPE_";
private static final  String INHUMAN_POWER_STORAGE_ALWAYS_ACTIVE = "INHUMAN_POWER_STORE_ALWAYS_ACTIVE_";
//private static final  String INHUMAN_POWER_STORAGE_LIGHT_SENSITIVE = "INHUMAN_POWER_STORE_LIGHT_SENSITIVE_";
private static final  String INHUMAN_POWER_STORAGE_DAMAGE_AMOUNT = "INHUMAN_POWER_STORE_DAMAGE_AMOUNT_";
private static final  String INHUMAN_POWER_STORAGE_DAMAGE_TYPE = "INHUMAN_POWER_STORE_DAMAGE_TYPE_";
private static final  String INHUMAN_POWER_STORAGE_VISUAL = "INHUMAN_POWER_VISUAL_";
private static final  String INHUMAN_POWER_STORAGE_COMBAT_ONLY = "INHUMAN_POWER_COMBAT_ONLY_";

private static final  String INHUMAN_POWER_APPEARANCE_CHANGE = "INHUMAN_POWER_APPEARANCE_";
private static final  String INHUMAN_POWER_APPEARANCE_HAIR_COLOR = "INHUMAN_POWER_APPEARANCE_HAIR_";
private static final  String INHUMAN_POWER_APPEARANCE_SKIN_COLOR = "INHUMAN_POWER_APPEARANCE_SKIN_";



	private int SkinColor;
	private int HairColor;
	private int Appearance;

	private void Initialize(){
		
		String sName = NWScript.getLocalString(NWObject.MODULE, INHUMAN_POWER_STORAGE_NAME+GeneID);
		int Feat = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_FEATID+GeneID);
		boolean IsPassive = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_IS_PASSIVE+GeneID)==1;
		int EffectType = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_EFFECT_TYPE+GeneID);
		int TimeOfDay = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_TIMEOFDAY+GeneID);
		int Number1 = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_NUMBER1+GeneID);
		int Number2 = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_NUMBER2+GeneID);
		int LevelOfPower = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_LEVEL_OF_POWER+GeneID);
		int Natural = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_ENVIRONMENT_NATURAL+GeneID);
		int Above = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_ENVIRONMENT_ABOVE_GROUND+GeneID);
		int Interior = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_ENVIRONMENT_INTERIOR+GeneID);
		int Surface = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_ENVIRONMENT_SURFACE_TYPE+GeneID);
		boolean AlwaysActive = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_ALWAYS_ACTIVE+GeneID)==1;
		int DamageAmount = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_DAMAGE_AMOUNT+GeneID);
		int DamageType = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_DAMAGE_TYPE+GeneID);
		int Visual = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_VISUAL+GeneID);
		boolean InCombatOnly = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_STORAGE_COMBAT_ONLY+GeneID)==1;
		int AppearanceChange = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_APPEARANCE_CHANGE+GeneID);
		int AppearanceChangeHair = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_APPEARANCE_HAIR_COLOR+GeneID);
		int AppearanceChangeSkin = NWScript.getLocalInt(NWObject.MODULE, INHUMAN_POWER_APPEARANCE_SKIN_COLOR+GeneID);
		setGeneName(sName);
		setFeatID(Feat);
		setIsPassive(IsPassive);
		setTimeOfDayActive(TimeOfDay);
		setEffectType(EffectType);
		setEffectNumber1(Number1);
		setEffectNumber2(Number2);
		setLevelOfPower(LevelOfPower);
		setEnvironmentNatural(Natural);
		setEnvironmentAboveGround(Above);
		setEnvironmentInterior(Interior);
		setEnvironmentTilesetType(Surface);
		setApplyDamageAmount(DamageAmount);
		setDamageType(DamageType);
		setVisualEffect(Visual);
		setAlwaysActive(AlwaysActive);
		setCombatOnly(InCombatOnly);
		setAppearance(AppearanceChange);
		setHairColor(AppearanceChangeHair);
		setSkinColor(AppearanceChangeSkin);
		//GeneDefinitions.add(this);
		IdToGene.put(GeneID, this);
		NWScript.printString("Stored gene "+this.getGeneName()+" with gene id "+GeneID);
	}
	
	
	//private static ArrayList<Gene> GeneDefinitions = new ArrayList<Gene>();
	private static HashMap<Integer, Gene> IdToGene = new HashMap<Integer, Gene>();
	
	/**
	 * Specify an int value for the Gene you are trying to retrieve
	 * @param i
	 * @return
	 */
	public static final Gene getGeneByID(int i){
		Gene g = (Gene)IdToGene.get(i);
		if(g ==null){
			System.out.print("Gene was not found in collection!!");
			NWScript.printString("Gene was not found in collection!!");
		}
		return g;
	}
	
	/**
	 * Applies a visual effect to the creature when the gene is active.
	 * Set to -1 to disable vfx on the particular gene
	 * Be careful, as it could become easy to stack too many vfx on the creature
	 * Eg: Make the player translucent during the night, like they are phasing through matter.
	 * @return
	 */
	public int getVisualEffect() {
		return VisualEffect;
	}
	
	/**
	 * Applies a visual effect to the creature when the gene is active.
	 * Set to -1 to disable vfx on the particular gene
	 * Be careful, as it could become easy to stack too many vfx on the creature
	 * Eg: Make the player translucent during the night, like they are phasing through matter.
	 * @return
	 */
	public void setVisualEffect(int visualEffect) {
		VisualEffect = visualEffect;
	}
	
	/**
	 * Returns the name for the gene
	 * @return
	 */
	public String getGeneName() {
		return GeneName;
	}
	
	/**
	 * Sets the gene name
	 * Eg: OCF-Fire Delta (if you are in a Sci-Fi module)
	 * or Simply: Immunity to Fire - 20% if you are in a less science setting
	 * @return
	 */
	public void setGeneName(String geneName) {
		GeneName = geneName;
	}
	
	/**
	 * Adds a feat when the creature does not know the feat and the gene is active
	 * Removes the feat when the creature has the feat, but the gene is inactive
	 * Eg: Immune to Fire during the day etc
	 * @return
	 */
	public int getFeatID() {
		return FeatID;
	}
	
	/**
	 * Adds a feat when the creature does not know the feat and the gene is active
	 * Removes the feat when the creature has the feat, but the gene is inactive
	 * Eg: Immune to Fire during the day etc
	 * @return
	 */
	public void setFeatID(int featID) {
		FeatID = featID;
	}
	
	/**
	 * Currently Unused
	 * @return
	 */
	public boolean getIsPassive() {
		return IsPassive;
	}
	
	/**
	 * Currently Unused
	 * @return
	 */
	public void setIsPassive(boolean isPassive) {
		IsPassive = isPassive;
	}
	
	
	public int getEffectType() {
		return EffectType;
	}
	public void setEffectType(int effectType) {
		EffectType = effectType;
	}
	
	
	/**
	 * Currently allows gene to be active at following times,
	 * All the time, (0), Day (1), Night (2)
	 * Day is considered: Dawn, Day
	 * Night is Dusk, Night
	 * @return
	 */
	public int getTimeOfDayActive() {
		return TimeOfDayActive;
	}
	
	/**
	 * Currently allows gene to be active at following times,
	 * All the time, (0), Day (1), Night (2)
	 * Day is considered: Dawn, Day
	 * Night is Dusk, Night
	 * @return
	 */
	public void setTimeOfDayActive(int timeOfDayActive) {
		TimeOfDayActive = timeOfDayActive;
	}
	
	/**
	 * For this gene to be active, should it be Natural(1) or Artifical (0), or Ignore (2)
	 * @return
	 */
	public int getEnvironmentNatural() {
		return EnvironmentNatural;
	}
	
	/**
	 * For this gene to be active, should it be Natural(1) or Artifical (0), or Ignore (2)
	 * @return
	 */
	public void setEnvironmentNatural(int environmentNatural) {
		EnvironmentNatural = environmentNatural;
	}
	
	/**
	 * For this gene to be active, should it be Interior (1) or Exterior (0), or Ignore (2)
	 * @return
	 */
	public int getEnvironmentInterior() {
		return EnvironmentInterior;
	}
	/**
	 * For this gene to be active, should it be Interior (1) or Exterior (0), or Ignore (2)
	 * @return
	 */
	public void setEnvironmentInterior(int environmentInterior) {
		EnvironmentInterior = environmentInterior;
	}
	
	/**
	 * For this gene to be active, should it be Above Ground (1) or Underground (0), or Ignore (2)
	 * @return
	 */
	public int getEnvironmentAboveGround() {
		return EnvironmentAboveGround;
	}
	
	/**
	 * For this gene to be active, should it be Above Ground (1) or Underground (0), or Ignore (2)
	 * @return
	 */
	public void setEnvironmentAboveGround(int environmentAboveGround) {
		EnvironmentAboveGround = environmentAboveGround;
	}
	
	/**
	 * Activates the gene when the player is standing on a specific tiletype:
	 * For things like 'Water' damage, you need to use a Constant value of 6
	 * This covers the following tiletypes:
	 * 6, 11, 17  : All of which are a type of water.
	 * For custom tilesets, that use different surface types, you must modify the IsInWater method in Include.java
	 * Possible cases where you might want to use this is:
	 * +Concealment when in the Snow
	 * or
	 * +Skin changes to White, when standing on snow. (eg: camoflage)
	 * @return
	 */
	public int getEnvironmentTilesetType() {
		return EnvironmentTilesetType;
	}
	
	/**
	 * Activates the gene when the player is standing on a specific tiletype:
	 * For things like 'Water' damage, you need to use a Constant value of 6
	 * This covers the following tiletypes:
	 * 6, 11, 17  : All of which are a type of water.
	 * For custom tilesets, that use different surface types, you must modify the IsInWater method in Include.java
	 * Possible cases where you might want to use this is:
	 * +Concealment when in the Snow
	 * or
	 * +Skin changes to White, when standing on snow. (eg: camoflage)
	 * @return
	 */
	public void setEnvironmentTilesetType(int environmentTilesetType) {
		EnvironmentTilesetType = environmentTilesetType;
	}
	
	/**
	 * Overrides all other conditions - ensures the gene is always active.
	 * @return
	 */
	public boolean getAlwaysActive() {
		return AlwaysActive;
	}
	
	/**
	 * Overrides all other conditions - ensures the gene is always active.
	 * @return
	 */
	public void setAlwaysActive(boolean alwaysActive) {
		AlwaysActive = alwaysActive;
	}
	
	/**
	 * If a damage amount is specified, then the gene will apply damage instead of a duration effect
	 * @return
	 */
	public int getApplyDamageAmount() {
		return ApplyDamageAmount;
	}
	
	/**
	 * If a damage amount is specified, then the gene will apply damage instead of a duration effect
	 * @return
	 */
	public void setApplyDamageAmount(int applyDamageAmount) {
		ApplyDamageAmount = applyDamageAmount;
	}
	
	/**
	 * If a damage amount is specified, then the gene will apply damage instead of a duration effect
	 * the type determined by this setting
	 * @return
	 */
	public int getDamageType() {
		return DamageType;
	}
	
	/**
	 * If a damage amount is specified, then the gene will apply damage instead of a duration effect
	 * the type determined by this setting
	 * @return
	 */
	public void setDamageType(int damageType) {
		DamageType = damageType;
	}
	
	/**
	 * If a damage amount is NOT specified, then the gene will apply an effect constructed with
	 * this number used as the first argument for its constructor
	 * @return
	 */
	public int getEffectNumber1() {
		return EffectNumber1;
	}
	
	/**
	 * If a damage amount is NOT specified, then the gene will apply an effect constructed with
	 * this number used as the first argument for its constructor
	 * @return
	 */
	public void setEffectNumber1(int effectNumber1) {
		EffectNumber1 = effectNumber1;
	}
	
	/**
	 * If a damage amount is NOT specified, then the gene will apply an effect constructed with
	 * this number used as the second argument for its constructor
	 * @return
	 */
	public int getEffectNumber2() {
		return EffectNumber2;
	}
	
	/**
	 * If a damage amount is NOT specified, then the gene will apply an effect constructed with
	 * this number used as the second argument for its constructor
	 * @return
	 */
	public void setEffectNumber2(int effectNumber2) {
		EffectNumber2 = effectNumber2;
	}
	
	/**
	 * Unknown - Consider for removal?
	 * May use this as part of the randomisation for levelling.
	 * Eg: If you are entitled to one level 4 power, then that power is chosen at random from all Level 4 powers of a type.
	 * @return
	 */
	public int getLevelOfPower() {
		return LevelOfPower;
	}
	
	/**
	 * Unknown - Consider for removal?
	 * May use this as part of the randomisation for levelling.
	 * Eg: If you are entitled to one level 4 power, then that power is chosen at random from all Level 4 powers of a type.
	 * @return
	 */
	public void setLevelOfPower(int levelOfPower) {
		LevelOfPower = levelOfPower;
	}
	
	/**
	 * Allows the gene to only be active during combat.
	 * Eg: Allow combat regeneration that consumes large Bio Energy etc
	 * (Accumulate Bio-Energy when not in combat, then use it during comat)
	 * @return
	 */
	public boolean isCombatOnly() {
		return CombatOnly;
	}

	




	/**
	 * Allows the gene to only be active during combat.
	 * Eg: Allow combat regeneration that consumes large Bio Energy etc
	 * (Accumulate Bio-Energy when not in combat, then use it during comat)
	 * @return
	 */
	public void setCombatOnly(boolean combatOnly) {
		CombatOnly = combatOnly;
	}
	
	/**
	 * Returns a list of EnergyCostBindings that this gene has.
	 * Eg: How much Energy is consumed per 7 second tick for this gene.
	 * Note: AlwaysActive genes do not consume energy
	 * @return
	 */
	public ArrayList<EnergyCostBinding> getCostPerHeartbeat() {
		return CostPerHeartbeat;
	}

	//public void setCostPerHeartbeat(ArrayList<EnergyCostBinding> costPerHeartbeat) {
	//	CostPerHeartbeat = costPerHeartbeat;
	//}
	
	
	/**
	 * What skin color does the player have when the gene is active
	 * @return
	 */
	public int getSkinColor() {
		return SkinColor;
	}

	/**
	 * What skin color does the player have when the gene is active
	 * @return
	 */
	public void setSkinColor(int skinColor) {
		SkinColor = skinColor;
	}
	
	/**
	 * What hair color does the player have when the gene is active
	 * @return
	 */
	public int getHairColor() {
		return HairColor;
	}

	/**
	 * What hair color does the player have when the gene is active
	 * @return
	 */
	public void setHairColor(int hairColor) {
		HairColor = hairColor;
	}
	
	/**
	 * What appearance does the player have when the gene is active
	 * @return
	 */
	public int getAppearance() {
		return Appearance;
	}

	public void ConstructEnergyCostStringForGene(){
		String str = "";

		for(EnergyCostBinding ecb: CostPerHeartbeat){
			String token = ecb.getEnergyToCharge().getColorString();
			str += ecb.getAmountToCharge()+" "+token+ecb.getEnergyToCharge().getName()+"</c> per heartbeat."+System.getProperty("line.separator");
		}
		if(str.equals("")){
			str = "No Energy Cost";
		}
		
		//NWObject oPC = NWScript.getPCs()[0];
		//NWScript.sendMessageToPC(oPC, str);
		NWScript.setCustomToken(7003, str);
		
	}
	
	
	/**
	 * What appearance does the player have when the gene is active
	 * @return
	 */
	public void setAppearance(int appearance) {
		Appearance = appearance;
	}
	private String GeneName;
	private int FeatID;
	private boolean IsPassive;
	private int EffectType;
	private int TimeOfDayActive;
	private int EnvironmentNatural;
	private int EnvironmentInterior;
	private int EnvironmentAboveGround;
	private int EnvironmentTilesetType;
	private boolean AlwaysActive;
	private int ApplyDamageAmount;
	private int DamageType;
	private int EffectNumber1;
	private int EffectNumber2;
	private int LevelOfPower;
	private int VisualEffect;
	private boolean CombatOnly;
	private ArrayList<EnergyCostBinding> CostPerHeartbeat = new ArrayList<EnergyCostBinding>();
	
}
