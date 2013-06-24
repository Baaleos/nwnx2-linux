#include "NWNXCombat.h"

extern CNWNXCombat combat;

std::pair<uint32_t, bool> GetWeaponCritMultiplier(CNWSCreature *cre, CNWSItem *it) {
    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    C2DA *feats = nwn_GetCached2da(WEAPON_FEAT_2DA);
    bool has_dev = false;
    if ( !props || !feats ) { return std::make_pair(1, has_dev); }

    uint32_t base = it->it_baseitem;
    uint32_t wpn  = combat.baseitem_to_weapon(base);
    uint32_t mult = nwn_Get2daInt(props, "CritMult", wpn);

    // WM Increase Multiplier add +1 - assume if they have the levels
    // they have the feat.
    int32_t feat  = nwn_Get2daInt(feats, "Choice", wpn);
    int wm = nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_WEAPON_MASTER);
    if ( feat > 0 && 
	 wm >= 5  &&
	 nwn_GetHasFeat(cre->cre_stats, feat) ) { 
	++mult;
    }


    feat = nwn_Get2daInt(feats, "CritDev", wpn);
    if ( feat > 0 && nwn_GetHasFeat(cre->cre_stats, feat) ) {
#if TA
	++mult;
#endif
	has_dev = true;
    }


    return std::make_pair(mult, has_dev);
}
