#include "NWNXCombat.h"

extern CNWNXCombat combat;

std::pair<int32_t, int32_t> GetDualWieldPenalty(CNWSCreature *cre) {
    CNWSItem *on = nwn_GetItemInSlot(cre, EQUIPMENT_SLOT_RIGHTHAND);
    CNWSItem *off = nwn_GetItemInSlot(cre, EQUIPMENT_SLOT_LEFTHAND);
    int32_t ab_on = 0, ab_off = 0;
    bool off_is_weap;
    int32_t on_bi, off_bi;

    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    
    bool is_dual = ( on && combat.baseitem_to_weapon(on->it_baseitem) != 0 &&
                     off && combat.baseitem_to_weapon(off->it_baseitem) != 0 &&
                     props );

    if ( !is_dual ) { return std::make_pair(0, 0); }

    int on_wpn = combat.baseitem_to_weapon(on->it_baseitem);
    int rel = CNWSCreature__GetRelativeWeaponSize(cre, on);

    if ( rel <= -1 || nwn_Get2daInt(props, "Double", on_wpn) ) {
        ab_on  = -4;
        ab_off = -8;
    }
    else {
        ab_on  = -6;
        ab_off = -10;
    }

    if ( nwn_GetHasFeat(cre->cre_stats, 374) ) { // Range Dual Wield
        ab_on += 2;
        ab_off += 6;
    }
    else {
        if ( nwn_GetHasFeat(cre->cre_stats, FEAT_TWO_WEAPON_FIGHTING) ) {
            ab_on += 2;
            ab_off += 2;
        }
        if ( nwn_GetHasFeat(cre->cre_stats, FEAT_TWO_WEAPON_FIGHTING) ) {
            ab_off += 4;
        }
    }
      
    return std::make_pair(ab_on, ab_off);
}
