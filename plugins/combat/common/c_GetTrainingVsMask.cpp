#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint64_t GetTrainingVsMask(CNWSCreature *cre) {
#define TVS(flags, feat, race)                  \
    if ( nwn_GetHasFeat(cre->cre_stats, feat) ) \
        flags |= 1 << race

    uint64_t mask = 0;
    TVS(mask, FEAT_BATTLE_TRAINING_VERSUS_ORCS,
        RACIAL_TYPE_HUMANOID_ORC);

    TVS(mask, FEAT_BATTLE_TRAINING_VERSUS_GOBLINS,
        RACIAL_TYPE_HUMANOID_GOBLINOID);

    TVS(mask, FEAT_BATTLE_TRAINING_VERSUS_GIANTS,
        RACIAL_TYPE_GIANT);

    TVS(mask, FEAT_BATTLE_TRAINING_VERSUS_REPTILIANS,
        RACIAL_TYPE_HUMANOID_REPTILIAN);

    
    return mask;
}
