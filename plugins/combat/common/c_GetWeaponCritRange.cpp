#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetWeaponCritRange(CNWSCreature *cre, CNWSItem *it) {
    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    C2DA *feats = nwn_GetCached2da(WEAPON_FEAT_2DA);
    if ( !props || !feats ) { return 1; }

    uint32_t base = it->it_baseitem;
    uint32_t wpn  = combat.baseitem_to_weapon(base);
    uint32_t base_rng = nwn_Get2daInt(props, "CritThreat", wpn);
    uint32_t result = base_rng;
    
    int32_t feat  = nwn_Get2daInt(feats, "CritImp", wpn);
    if ( feat > 0 && nwn_GetHasFeat(cre->cre_stats, feat) ) {
        result += base_rng;
    }


    if ( nwn_HasPropertyType(it, ITEM_PROPERTY_KEEN) ) {
        result += base_rng;
    }

    // WM Ki Critical add base crit range - assume if they have the levels they have the
    // feat.
    feat  = nwn_Get2daInt(feats, "Choice", wpn);
    int wm = nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_WEAPON_MASTER);

    combat.Log(3, "GetWeaponCritRange: WM: %d, Feat: %d\n", wm, feat);
    if ( feat > 0 && 
         wm >= 7  && 
         nwn_GetHasFeat(cre->cre_stats, feat) ) { 
        result += 2;
    }
#if TA
    else if ( (feat = nwn_Get2daInt(feats, "CritOver", wpn)) > 0 &&
              nwn_GetHasFeat(cre->cre_stats, feat) ) {
        ++result;
    }
#endif

    return result;
}
