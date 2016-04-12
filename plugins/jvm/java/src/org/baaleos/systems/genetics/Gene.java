package org.baaleos.systems.genetics;

import java.util.ArrayList;
import java.util.HashMap;

import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;

public class Gene {
	public Gene(int geneID){
		
		
		this.GeneID = geneID;
		Initialize();
	}
	private int GeneID;
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
private static final  String INHUMAN_POWER_STORAGE_LIGHT_SENSITIVE = "INHUMAN_POWER_STORE_LIGHT_SENSITIVE_";
private static final  String INHUMAN_POWER_STORAGE_DAMAGE_AMOUNT = "INHUMAN_POWER_STORE_DAMAGE_AMOUNT_";
private static final  String INHUMAN_POWER_STORAGE_DAMAGE_TYPE = "INHUMAN_POWER_STORE_DAMAGE_TYPE_";
private static final  String INHUMAN_POWER_STORAGE_VISUAL = "INHUMAN_POWER_VISUAL_";
private static final  String INHUMAN_POWER_STORAGE_COMBAT_ONLY = "INHUMAN_POWER_COMBAT_ONLY_";
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
		//GeneDefinitions.add(this);
		IdToGene.put(GeneID, this);
		NWScript.printString("Stored gene "+this.getGeneName()+" with gene id "+GeneID);
	}
	
	
	//private static ArrayList<Gene> GeneDefinitions = new ArrayList<Gene>();
	private static HashMap<Integer, Gene> IdToGene = new HashMap<Integer, Gene>();
	
	public static final Gene getGeneByID(int i){
		Gene g = (Gene)IdToGene.get(i);
		if(g.equals(null)){
			System.out.print("Gene was not found in collection!!");
			NWScript.printString("Gene was not found in collection!!");
		}
		return g;
	}
	
	public int getVisualEffect() {
		return VisualEffect;
	}
	public void setVisualEffect(int visualEffect) {
		VisualEffect = visualEffect;
	}
	public String getGeneName() {
		return GeneName;
	}
	public void setGeneName(String geneName) {
		GeneName = geneName;
	}
	public int getFeatID() {
		return FeatID;
	}
	public void setFeatID(int featID) {
		FeatID = featID;
	}
	public boolean getIsPassive() {
		return IsPassive;
	}
	public void setIsPassive(boolean isPassive) {
		IsPassive = isPassive;
	}
	public int getEffectType() {
		return EffectType;
	}
	public void setEffectType(int effectType) {
		EffectType = effectType;
	}
	public int getTimeOfDayActive() {
		return TimeOfDayActive;
	}
	public void setTimeOfDayActive(int timeOfDayActive) {
		TimeOfDayActive = timeOfDayActive;
	}
	public int getEnvironmentNatural() {
		return EnvironmentNatural;
	}
	public void setEnvironmentNatural(int environmentNatural) {
		EnvironmentNatural = environmentNatural;
	}
	public int getEnvironmentInterior() {
		return EnvironmentInterior;
	}
	public void setEnvironmentInterior(int environmentInterior) {
		EnvironmentInterior = environmentInterior;
	}
	public int getEnvironmentAboveGround() {
		return EnvironmentAboveGround;
	}
	public void setEnvironmentAboveGround(int environmentAboveGround) {
		EnvironmentAboveGround = environmentAboveGround;
	}
	public int getEnvironmentTilesetType() {
		return EnvironmentTilesetType;
	}
	public void setEnvironmentTilesetType(int environmentTilesetType) {
		EnvironmentTilesetType = environmentTilesetType;
	}
	public boolean getAlwaysActive() {
		return AlwaysActive;
	}
	public void setAlwaysActive(boolean alwaysActive) {
		AlwaysActive = alwaysActive;
	}
	public int getApplyDamageAmount() {
		return ApplyDamageAmount;
	}
	public void setApplyDamageAmount(int applyDamageAmount) {
		ApplyDamageAmount = applyDamageAmount;
	}
	public int getDamageType() {
		return DamageType;
	}
	public void setDamageType(int damageType) {
		DamageType = damageType;
	}
	public int getEffectNumber1() {
		return EffectNumber1;
	}
	public void setEffectNumber1(int effectNumber1) {
		EffectNumber1 = effectNumber1;
	}
	public int getEffectNumber2() {
		return EffectNumber2;
	}
	public void setEffectNumber2(int effectNumber2) {
		EffectNumber2 = effectNumber2;
	}
	public int getLevelOfPower() {
		return LevelOfPower;
	}
	public void setLevelOfPower(int levelOfPower) {
		LevelOfPower = levelOfPower;
	}
	public boolean isCombatOnly() {
		return CombatOnly;
	}

	





	public void setCombatOnly(boolean combatOnly) {
		CombatOnly = combatOnly;
	}
	public ArrayList<EnergyCostBinding> getCostPerHeartbeat() {
		return CostPerHeartbeat;
	}

	//public void setCostPerHeartbeat(ArrayList<EnergyCostBinding> costPerHeartbeat) {
	//	CostPerHeartbeat = costPerHeartbeat;
	//}
	
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
