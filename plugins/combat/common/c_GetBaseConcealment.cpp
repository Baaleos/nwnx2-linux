#include "NWNXCombat.h"

extern CNWNXCombat combat;

int GetBaseConcealment(CNWSCreature *cre) {
    int sc = nwn_GetHasNthFeat(cre, 
                               FEAT_EPIC_SELF_CONCEALMENT_10,
                               FEAT_EPIC_SELF_CONCEALMENT_50);

    int rogue = nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_ROGUE);
    int rogue_bonus = 0;
    if ( rogue >= 30 && sc > 0 ) {
        int percent = 0;

        switch (sc) {
        case 1:  percent =  5; break;
        case 2:  percent =  9; break;
        case 3:  percent = 12; break;
        case 4:  percent = 14; break;
        default: percent = 15; break;
        }

        percent = std::min(percent, rogue - 20);
        rogue_bonus = (nwn_GetSkillRank(cre, SKILL_HIDE, NULL, false) * percent) / 100;
        
    }
    return sc * 10 + rogue_bonus;
}
