#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetWeaponDamageAbility(CNWSCreature *cre, CNWSItem *it) {
    int32_t abil = nwn_GetAbilityModifier(cre->cre_stats,
                                          ABILITY_STRENGTH,
                                          false);

    if ( !it ) { return abil; }
    
    int32_t mighty = -1;
    CNWItemProperty *ip = NULL;
    if ( it->it_baseitem != BASE_ITEM_DART &&
         GetIsRangedWeapon(it)             &&
         (ip = nwn_GetPropertyByType(it, ITEM_PROPERTY_MIGHTY)) ) {
        mighty = ip->ip_cost_value;
    }

    int32_t style = nwn_GetLocalInt(&cre->obj.obj_vartable,
                                    "pc_style_fighting");

    if ( style == 4 ) { /*warlord*/
        abil *= 2;
    }
    else if ( nwn_GetRelativeWeaponSize(cre, it) > 0) { // Not positive.
        abil += abil / 2;
    }
 
    if ( mighty > 0 && abil > mighty ) { 
        abil = mighty;
    }

    combat.Log(3, "Weapon Damage Ability: %d", abil);
    return CLAMP<int32_t>(abil, 0, 100);
}
