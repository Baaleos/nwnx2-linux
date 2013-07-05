#include "NWNXCombat.h"

extern CNWNXCombat combat;

static const int EPIC_FOCUS_BONUS = 10;
static const int FOCUS_BONUS = 3;

int8_t GetSkillFeatBonus(CNWSCreature *cre, uint8_t skill) {
#define FOCUS(creature, feat, epic_feat)                                \
    nwn_GetHasFeat(creature->cre_stats, epic_feat )                     \
        ? EPIC_FOCUS_BONUS + FOCUS_BONUS                                \
        : nwn_GetHasFeat(cre->cre_stats, feat) ? FOCUS_BONUS : 0
    
    int32_t res = 0;
    
    switch (skill) {
    default: return res;
    case SKILL_ANIMAL_EMPATHY:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_ANIMAL_EMPATHY,
                     FEAT_EPIC_SKILL_FOCUS_ANIMAL_EMPATHY);
        break;
        
    case SKILL_CONCENTRATION:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_CONCENTRATION,
                     FEAT_EPIC_SKILL_FOCUS_CONCENTRATION);
        break;

    case SKILL_DISABLE_TRAP:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_DISABLE_TRAP,
                     FEAT_EPIC_SKILL_FOCUS_DISABLETRAP);
        break;
        
    case SKILL_DISCIPLINE:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_DISCIPLINE,
                     FEAT_EPIC_SKILL_FOCUS_DISCIPLINE);
        break;
        
    case SKILL_HEAL:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_HEAL,
                     FEAT_EPIC_SKILL_FOCUS_HEAL);
        break;
        
    case SKILL_HIDE:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_HIDE,
                     FEAT_EPIC_SKILL_FOCUS_HIDE);
        break;
        
    case SKILL_LISTEN:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_LISTEN,
                     FEAT_EPIC_SKILL_FOCUS_LISTEN);
        break;
        
    case SKILL_LORE:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_LORE,
                     FEAT_EPIC_SKILL_FOCUS_LORE);
        break;
        
    case SKILL_MOVE_SILENTLY:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_MOVE_SILENTLY,
                     FEAT_EPIC_SKILL_FOCUS_MOVESILENTLY);
        break;
        
    case SKILL_OPEN_LOCK:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_OPEN_LOCK,
                     FEAT_EPIC_SKILL_FOCUS_OPENLOCK);
        break;
        
    case SKILL_PARRY:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_PARRY,
                     FEAT_EPIC_SKILL_FOCUS_PARRY);
        break;
        
    case SKILL_PERFORM:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_PERFORM,
                     FEAT_EPIC_SKILL_FOCUS_PERFORM);
        break;
        
    case SKILL_PERSUADE:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_PERSUADE,
                     FEAT_EPIC_SKILL_FOCUS_PERSUADE);
        break;
        
    case SKILL_PICK_POCKET:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_PICK_POCKET,
                     FEAT_EPIC_SKILL_FOCUS_PICKPOCKET);
        break;
        
    case SKILL_SEARCH:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_SEARCH,
                     FEAT_EPIC_SKILL_FOCUS_SEARCH);
        break;
        
    case SKILL_SET_TRAP:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_SET_TRAP,
                     FEAT_EPIC_SKILL_FOCUS_SETTRAP);
        break;
        
    case SKILL_SPELLCRAFT:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_SPELLCRAFT,
                     FEAT_EPIC_SKILL_FOCUS_SPELLCRAFT);
        break;
        
    case SKILL_SPOT:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_SPOT,
                     FEAT_EPIC_SKILL_FOCUS_SPOT);
        break;
        
    case SKILL_TAUNT:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_TAUNT,
                     FEAT_EPIC_SKILL_FOCUS_TAUNT);
        break;
        
    case SKILL_USE_MAGIC_DEVICE:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_USE_MAGIC_DEVICE,
                     FEAT_EPIC_SKILL_FOCUS_USEMAGICDEVICE);
        break;
        
    case SKILL_APPRAISE:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_APPRAISE,
                     FEAT_EPIC_SKILL_FOCUS_APPRAISE);
        break;
        
    case SKILL_TUMBLE:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_TUMBLE,
                     FEAT_EPIC_SKILL_FOCUS_TUMBLE);
        break;
        
    case SKILL_CRAFT_TRAP:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_CRAFT_TRAP,
                     FEAT_EPIC_SKILL_FOCUS_CRAFT_TRAP);
        break;
        
    case SKILL_BLUFF:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_BLUFF,
                     FEAT_EPIC_SKILL_FOCUS_BLUFF);
        break;
        
    case SKILL_INTIMIDATE:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_INTIMIDATE,
                     FEAT_EPIC_SKILL_FOCUS_INTIMIDATE);
        break;
        
    case SKILL_CRAFT_ARMOR:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_CRAFT_ARMOR,
                     FEAT_EPIC_SKILL_FOCUS_CRAFT_ARMOR);
        break;
        
    case SKILL_CRAFT_WEAPON:
        res += FOCUS(cre, FEAT_SKILL_FOCUS_CRAFT_WEAPON,
                     FEAT_EPIC_SKILL_FOCUS_CRAFT_WEAPON);
        break;
        
    case SKILL_RIDE:
        break;
    }
    
    return res;
#undef FOCUS
}
