#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Func_RegisterModeCombatMod(CGameObject *ob, char *value) {
    int mode, ab, ac, dmg_type, dice, sides, bonus, penalty, hp;
    if ( sscanf(value, "%d %d %d %d %d %d %d %d %d",
                &mode, &ab, &ac, &dmg_type, &dice, &sides, &bonus,
                &penalty, &hp) < 9 ) {
        snprintf(value, strlen(value), "-1");
        return;
    }
    combat.modes.insert(
        std::make_pair(mode,
                       CombatMod(ab, ac,
                                 DamageAmount(dmg_type,
                                              DiceRoll(dice, sides, bonus),
                                              penalty),
                                 hp)));
                                            
}
