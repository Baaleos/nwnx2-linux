#include "NWNXCombat.h"

extern CNWNXCombat combat;

int32_t GetIsMonkWeapon(CNWSItem *it) {
    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    if ( !it || !props ) { return false; }
    
    return nwn_Get2daInt(props, "Monk", combat.baseitem_to_weapon(it->it_baseitem));
}
