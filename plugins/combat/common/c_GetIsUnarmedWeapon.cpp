#include "NWNXCombat.h"

extern CNWNXCombat combat;

bool GetIsUnarmedWeapon(CNWSItem *it) {
    if ( !it ) { return true; }
        
    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    if ( !props ) { return false; }
    
    return nwn_Get2daInt(props, "Type", combat.baseitem_to_weapon(it->it_baseitem)) == 8;
}
