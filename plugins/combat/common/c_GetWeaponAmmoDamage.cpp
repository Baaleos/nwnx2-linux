#include "NWNXCombat.h"
#include <vector>

extern CNWNXCombat combat;

void GetWeaponAmmoDamage(CNWSCreature *cre, uint32_t weapon_type, std::vector<DamageAmount>& dmgs) {
    uint32_t equipslot = 0;
    
    switch(weapon_type) {
    default: return;
    case WEAPON_TYPE_BOW:
	equipslot = EQUIPMENT_SLOT_ARROWS;
	break;
    case WEAPON_TYPE_SLING:
	equipslot = EQUIPMENT_SLOT_BULLETS;
	break;
    case WEAPON_TYPE_CROSSBOW:
	equipslot = EQUIPMENT_SLOT_BOLTS;
	break;
    }

    CNWSItem *it = nwn_GetItemInSlot(cre, equipslot);
    if ( !it ) { return; }

    CNWItemProperty *ip;
    for ( size_t i = 0; i < it->it_passive_ip_len; ++i ) {
	ip = reinterpret_cast<CNWItemProperty *>(&it->it_passive_ip[i]);
	if ( !ip ) { continue; }
	
	if ( ip->ip_type == ITEM_PROPERTY_DAMAGE_BONUS ) {
	    dmgs.push_back(DamageAmount(GetDamageIndexFromFlag(GetDamageFlagFromIPConst(ip->ip_subtype)),
					combat.ip_dmg_to_roll(ip->ip_cost_value),
					false));
	}
    }
}
