#include "NWNXCombat.h"

extern CNWNXCombat combat;

CombatMod GetCombatModTrainingVs(CNWSCreature *cre) {
    CombatMod mod;

    mod.ab = 1;
    mod.ac = 4;
    
    return mod;
}
