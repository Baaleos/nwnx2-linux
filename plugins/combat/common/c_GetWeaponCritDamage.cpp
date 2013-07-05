#include "NWNXCombat.h"
#include <vector>

extern CNWNXCombat combat;

// TODO: determine damages, overwhelming critical, and Thundering Rage...
void GetWeaponCritDamage(CNWSCreature *cre, CNWSItem *it, std::vector<DamageAmount>& dmgs) {
    dmgs.clear();

    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    C2DA *feat_2da = nwn_GetCached2da(WEAPON_FEAT_2DA);

    if ( !feat_2da || !props || !it ) { return; }

    CNWItemProperty *ip = NULL;
    if ( (ip = nwn_GetPropertyByType(it, ITEM_PROPERTY_MASSIVE_CRITICALS)) ) {
        dmgs.push_back(
            DamageAmount(
                DAMAGE_INDEX_BASE_WEAPON,
                combat.ip_dmg_to_roll(ip->ip_cost_value),
                false));
    }

    int base     = it->it_baseitem;
    int wpn      = combat.baseitem_to_weapon(base);
    int32_t feat = nwn_Get2daInt(feat_2da, "CritOver", wpn);

    if ( nwn_GetHasFeat(cre->cre_stats, feat) ) {
        dmgs.push_back(
            DamageAmount(
                DAMAGE_INDEX_BASE_WEAPON,
                DiceRoll(nwn_Get2daInt(props, "CritMult", wpn) - 1, 
                         6, 
                         0),
                false));
    }
}
