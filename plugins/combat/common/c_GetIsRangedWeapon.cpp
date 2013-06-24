#include "NWNXCombat.h"

extern CNWNXCombat combat;

bool GetIsRangedWeapon(CNWSItem *it) {
    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    if ( !it || !props ) { return false; }
    
    return !!nwn_Get2daInt(props, "Ranged", combat.baseitem_to_weapon(it->it_baseitem));
}
