package org.baaleos.systems.god.advancedTypes;

import org.baaleos.systems.playerid.IDInc;
import org.nwnx.nwnx2.jvm.NWEffect;
import org.nwnx.nwnx2.jvm.NWObject;
import org.nwnx.nwnx2.jvm.NWScript;
import org.nwnx.nwnx2.jvm.constants.DamagePower;
import org.nwnx.nwnx2.jvm.constants.DamageType;
import org.nwnx.nwnx2.jvm.constants.Duration;
import org.nwnx.nwnx2.jvm.constants.DurationType;

public class NWCreature extends NWObject {

	public NWCreature(int oid) {
		super(oid);
		// TODO Auto-generated constructor stub
	}

	
	public boolean IsValid(){
		return NWScript.getIsObjectValid(this);
	}
	
	public void applyDamage(int type, int amount, int power ){
		NWEffect effect = NWScript.effectDamage(amount, type, power);
		NWScript.applyEffectToObject(DurationType.INSTANT, effect, this, 0.00f);
	}
	public String getUniqueID(){
		return IDInc.getPlayerId(this);
	}
	
}
