#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetWeaponPower(CNWSCreature *cre, CNWSItem *it) {
    uint16_t result = 0;
    for ( int i = 0; i < it->it_passive_ip_len; ++i ) {
        CNWItemProperty *ip = static_cast<CNWItemProperty *>(&it->it_passive_ip[i]);
        if ( ip->ip_type == ITEM_PROPERTY_ENHANCEMENT_BONUS ) {
            result = std::max(result, ip->ip_cost_value);
        }
    }

    return result;
}
