#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetWeaponAttackBonus(CNWSCreature *cre, CNWSItem *it) {
    C2DA *feat_2da = nwn_GetCached2da(WEAPON_FEAT_2DA);
    if ( !feat_2da ) {
	combat.Log(0, "Error uable to find %s.2da.\n", WEAPON_FEAT_2DA);
	return 0;
    }
    
    uint32_t feat;
    uint32_t ab   = 0;
    uint32_t base = it->it_baseitem;
    uint32_t wpn  = combat.baseitem_to_weapon(base);

    // Epic Weapon Focus - Note this is cummulative WF + EWF bonus.
    if ( (feat = nwn_Get2daInt(feat_2da, "FocusEpic", wpn)) >= 0 &&
	 nwn_GetHasFeat(cre->cre_stats, feat) ) {
	ab += 3;
    }
    // Weapon Focus
    else if ( (feat = nwn_Get2daInt(feat_2da, "Focus", wpn)) >= 0 &&
	      nwn_GetHasFeat(cre->cre_stats, feat) ) {
	ab += 1;
    }
    


    auto canuse = CanUseClassAbilities(cre, CLASS_TYPE_WEAPON_MASTER);
    if ( canuse.first ) {
	int wm = canuse.second;
	
	if ( wm >= 13 ) {
	    ab += 1 + ((wm - 10) / 3);
	    
	    if (wm >= 29) { ab++; }
            if (wm >= 30) { ab += 2; }

	}
	else {
	    ab += 1;
	}
    }

    canuse = CanUseClassAbilities(cre, CLASS_TYPE_MONK);
    if ( canuse.first && GetIsMonkWeapon(it) ) {
	if(nwn_GetHasFeat(cre->cre_stats, FEAT_EPIC_IMPROVED_KI_STRIKE_5))
            ab += 5;
        else if(nwn_GetHasFeat(cre->cre_stats, FEAT_EPIC_IMPROVED_KI_STRIKE_4))
            ab += 4;
        else if(nwn_GetHasFeat(cre->cre_stats, FEAT_KI_STRIKE_3))
            ab += 3;
        else if(nwn_GetHasFeat(cre->cre_stats, FEAT_KI_STRIKE_2))
            ab += 2;
        else if(nwn_GetHasFeat(cre->cre_stats, FEAT_KI_STRIKE))
            ab++;
    }

    /* rogues with the Opportunist feat get to add their base int modifier
     * to attacks with light weapons (including slings, light crossbows,
     * and morningstars) capped by rogue level */
    int rogue = nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_ROGUE);
    if ( rogue >= 25                                       &&
	 cre->cre_stats->cs_ac_armour_base <= 3            &&
	 nwn_GetHasFeat(cre->cre_stats, FEAT_OPPORTUNIST)  &&
	 ( GetIsWeaponLight(cre, it, 0)                    ||
	   it->it_baseitem == BASE_ITEM_LIGHTCROSSBOW      ||
	   it->it_baseitem == BASE_ITEM_MORNINGSTAR        ||
	   it->it_baseitem == BASE_ITEM_SLING ) ) {

	int max = (rogue - 20) / 5;
	int intbonus = (cre->cre_stats->cs_int - 10) / 2;

	if (intbonus > max) { 
	    intbonus = max;
	}

	if ( intbonus > 0 && rogue >= 30 ) {
	    intbonus++;
	}

	ab += intbonus;
    }

    // Enchant Arrow -- Assume that if the AA has the levels, then they have the feats...
    canuse = CanUseClassAbilities(cre, CLASS_TYPE_ARCANE_ARCHER);
    if ( canuse.first ) {
	int aa = canuse.second;

	if ( aa % 2 == 0 ) {
	    ab += (aa / 2);
	}
	else {
	    ab += (aa / 2) + 1;
	}
    }

    return ab;
}
