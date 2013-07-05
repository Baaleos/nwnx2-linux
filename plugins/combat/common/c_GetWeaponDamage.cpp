#include "NWNXCombat.h"
#include <vector>

extern CNWNXCombat combat;

void GetWeaponDamage(CNWSCreature *cre, CNWSItem *it, std::vector<DamageAmount>& dmgs) {
    dmgs.clear();

    CNWItemProperty *ip;

    DamageAmount d;

    // NOTE: I don't believe any of these are applied as active
    // properties...
    for ( size_t i = 0; i < it->it_active_ip_len; ++i ) {
        ip = reinterpret_cast<CNWItemProperty *>(&it->it_active_ip[i]);
        combat.Log(3, "Type: %d, Subtype: %d, Cost: %d\n", ip->ip_type, ip->ip_subtype, ip->ip_cost_value);
        if ( ip->ip_type == ITEM_PROPERTY_DAMAGE_BONUS ) {
            d = DamageAmount(GetDamageIndexFromFlag(GetDamageFlagFromIPConst(ip->ip_subtype)),
                             combat.ip_dmg_to_roll(ip->ip_cost_value),
                             false);
        }
        else if ( ip->ip_type == ITEM_PROPERTY_DECREASED_DAMAGE ) {
            d = DamageAmount(GetDamageIndexFromFlag(GetDamageFlagFromIPConst(ip->ip_subtype)),
                             combat.ip_dmg_to_roll(ip->ip_cost_value),
                             true);
        }
        else if ( ip->ip_type == ITEM_PROPERTY_ENHANCEMENT_BONUS ) {
            d = DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
                             DiceRoll(0, 0, ip->ip_cost_value),
                             false);
        }
        else if ( ip->ip_type == ITEM_PROPERTY_DECREASED_ENHANCEMENT_MODIFIER ) {
            d = DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
                             DiceRoll(0, 0, ip->ip_cost_value),
                             true);
        }
        else { 
            continue;
        }
        dmgs.push_back(d);
    }
    
    for ( size_t i = 0; i < it->it_passive_ip_len; ++i ) {
        ip = reinterpret_cast<CNWItemProperty *>(&it->it_passive_ip[i]);
        combat.Log(3, "Type: %d, Subtype: %d, Cost: %d\n", ip->ip_type, ip->ip_subtype, ip->ip_cost_value);
        if ( ip->ip_type == ITEM_PROPERTY_DAMAGE_BONUS ) {
            d = DamageAmount(GetDamageIndexFromFlag(GetDamageFlagFromIPConst(ip->ip_subtype)),
                             combat.ip_dmg_to_roll(ip->ip_cost_value),
                             false);
        }
        else if ( ip->ip_type == ITEM_PROPERTY_MONSTER_DAMAGE ) {
            C2DA *tda = nwn_GetCached2da("iprp_monstcost");
            if ( !tda ) { continue; }

            d = DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
                             DiceRoll(nwn_Get2daInt(tda, "NumDice", ip->ip_cost_value),
                                      nwn_Get2daInt(tda, "Die", ip->ip_cost_value),
                                      0),
                             false);
        }
        else if ( ip->ip_type == ITEM_PROPERTY_DECREASED_DAMAGE ) {
            d = DamageAmount(GetDamageIndexFromFlag(GetDamageFlagFromIPConst(ip->ip_subtype)),
                             combat.ip_dmg_to_roll(ip->ip_cost_value),
                             true);
        }
        else if ( ip->ip_type == ITEM_PROPERTY_ENHANCEMENT_BONUS ) {
            d = DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
                             DiceRoll(0, 0, ip->ip_cost_value),
                             false);
        }
        else if ( ip->ip_type == ITEM_PROPERTY_DECREASED_ENHANCEMENT_MODIFIER ) {
            d = DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
                             DiceRoll(0, 0, ip->ip_cost_value),
                             true);
        }
        else { 
            continue;
        }
        dmgs.push_back(d);
    }
}
