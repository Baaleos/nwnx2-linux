#include "NWNXCombat.h"

extern CNWNXCombat combat;

CombatMod GetCombatModFavoredEnemey(CNWSCreature *cre) {
    CombatMod mod;

    int ranger = nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_RANGER);
    int hs =  nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_HARPER);
    int lvl = ranger + hs;
    if ( lvl == 0 ) { return mod; }

    int bonus = (lvl / 5) + 1;
    mod.ab = bonus;
    DiceRoll d(0, 0, bonus);

    if ( nwn_GetHasFeat(cre->cre_stats, FEAT_EPIC_BANE_OF_ENEMIES) ) {
        mod.ab += 2;
        d.dice = 2;
        d.sides = 6;
    }

    mod.dmg = DamageAmount(DAMAGE_INDEX_BASE_WEAPON, d, false);

    return mod;
}
