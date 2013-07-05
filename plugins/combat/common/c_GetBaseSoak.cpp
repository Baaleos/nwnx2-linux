#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint16_t GetBaseSoak(CNWSCreature *cre) {
    int16_t barb = nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_BARBARIAN);
    int16_t dd = nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_DWARVENDEFENDER);

    // Barbarians get +1 soak per 3 levels above ten.
    barb -= 10;
    if ( barb >= 0 ) {
        barb = 1 + barb / 3;
    }
    else {
        barb = 0;
    }

    // Dwarven Defender gets +3 soak per four levels.
    dd -= 6;
    if ( dd >= 0 ) {
        dd = 3 + dd / 4;
    }
    else {
        dd = 0;
    }

    int16_t bonus = 0;
    if ( nwn_GetHasFeat(cre->cre_stats, FEAT_EPIC_DAMAGE_REDUCTION_9) ) {
        bonus = 9;
    }
    else if ( nwn_GetHasFeat(cre->cre_stats, FEAT_EPIC_DAMAGE_REDUCTION_6) ) {
        bonus = 6;
    }
    else if ( nwn_GetHasFeat(cre->cre_stats, FEAT_EPIC_DAMAGE_REDUCTION_3) ) {
        bonus = 3;
    }

    combat.Log(3, "Base Soak: %d\n", std::max(barb, dd) + bonus);

    bonus = std::max(barb, dd) + bonus;
    return CLAMP<uint16_t>(bonus, 0, 300);
}
