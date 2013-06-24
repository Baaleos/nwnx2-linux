#include "NWNXCombat.h"

extern CNWNXCombat combat;

CombatMod GetCombatModSituation(CNWSCreature *cre, uint32_t situ) {
    CombatMod mod;

    int sneak = 0;
    int nth = 0;

    switch(situ) {
    default: return mod;
    case SITUATION_INDEX_COUPDEGRACE: 
	// Nothing...
	break;
    case SITUATION_INDEX_SNEAK_ATTACK:
	if ( (nth = nwn_GetHasNthFeat(cre,
				      FEAT_SNEAK_ATTACK_11,
				      FEAT_SNEAK_ATTACK_20)) > 0 ) {
	    sneak = nth + 10;
	}	
	else if ( (nth = nwn_GetHasNthFeat(cre,
					   FEAT_SNEAK_ATTACK_2,
					   FEAT_SNEAK_ATTACK_10)) > 0 ) {
	    sneak = nth + 1;
	}
	else if ( nwn_GetHasFeat(cre->cre_stats, FEAT_SNEAK_ATTACK) ) {
	    sneak = 1;
	}

	combat.Log(3, "Sneak Attack: %d\n", sneak);

	if ( (nth = nwn_GetHasNthFeat(cre,
				      FEAT_EPIC_IMPROVED_SNEAK_ATTACK_1,
				      FEAT_EPIC_IMPROVED_SNEAK_ATTACK_10)) > 0 ) {
	    combat.Log(3, "Improved Sneak Attack: %d\n", nth);
	    sneak += nth;
	}

	if ( (nth = nwn_GetHasNthFeat(cre,
				      FEAT_BLACKGUARD_SNEAK_ATTACK_4D6,
				      FEAT_BLACKGUARD_SNEAK_ATTACK_15D6)) > 0 ) {
	    combat.Log(3, "Blackguard Sneak Attack: %d\n", nth + 3);
	    sneak += nth + 3;
	}
	else if ( (nth = nwn_GetHasNthFeat(cre,
					   FEAT_BLACKGUARD_SNEAK_ATTACK_1D6,
					   FEAT_BLACKGUARD_SNEAK_ATTACK_3D6)) > 0 ) {
	    sneak += nth;
	    combat.Log(3, "Blackguard Sneak Attack: %d\n", nth);
	}

	break;

    case SITUATION_INDEX_DEATH_ATTACK:
	if ( (nth = nwn_GetHasNthFeat(cre,
				      FEAT_PRESTIGE_DEATH_ATTACK_9,
				      FEAT_PRESTIGE_DEATH_ATTACK_20)) > 0 ) {
	    sneak += nth + 8;
	}
	else if ( (nth = nwn_GetHasNthFeat(cre,
					   FEAT_PRESTIGE_DEATH_ATTACK_6,
					   FEAT_PRESTIGE_DEATH_ATTACK_8)) > 0 ) {
	    sneak += nth + 5;
	}
	else if ( (nth = nwn_GetHasNthFeat(cre,
					   FEAT_PRESTIGE_DEATH_ATTACK_1,
					   FEAT_PRESTIGE_DEATH_ATTACK_5)) > 0 ) {
	    sneak += nth;
	}

	combat.Log(3, "Death Attack: %d\n", sneak);
	break;
    }

    if ( sneak > 0 ) {
	mod.dmg = DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
			       DiceRoll(sneak, 6, 0),
			       false);
    }


    return mod;
}
