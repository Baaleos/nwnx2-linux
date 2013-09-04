#include "NWNXCombat.h"

extern CNWNXCombat combat;

// TODO SPELLS!!
// - Spell bonus vs.
// - Spellcraft
int32_t GetSavingThrowVsBonus(CNWSCreature *cre, CNWSObject *vs,
                              int32_t save, int32_t save_vs) {
    int32_t save_bonus = 0;
    CNWSCreatureStats *stats = cre->cre_stats;
    int rogue, barb, temp;

    switch(save_vs) {
    case SAVING_THROW_TYPE_MIND_SPELLS:
        if ( nwn_GetHasFeat(stats, FEAT_STILL_MIND) ) {
            save_bonus += 2;
        }
        if ( nwn_GetHasFeat(stats, FEAT_LLIIRAS_HEART) ) {
            save_bonus += 2;
        }
        if ( nwn_GetHasFeat(stats, FEAT_HARDINESS_VERSUS_ILLUSIONS) ||
             nwn_GetHasFeat(stats, FEAT_HARDINESS_VERSUS_ENCHANTMENTS) ) {
            save_bonus += 2;
        }
        break;
    case SAVING_THROW_TYPE_POISON:
        if ( nwn_GetHasFeat(stats, FEAT_PRESTIGE_POISON_SAVE_EPIC) ) {
            save_bonus += 5;
            // Assassin gets +1 per 2 levels over 10;
            temp = nwn_GetLevelByClass(stats, CLASS_TYPE_ASSASSIN) - 10;
            if ( temp > 1 ) { save_bonus += temp / 2; }
        }
        else if ( nwn_GetHasFeat(stats, FEAT_PRESTIGE_POISON_SAVE_5) ) {
            save_bonus += 5;
        }
        else if ( nwn_GetHasFeat(stats, FEAT_PRESTIGE_POISON_SAVE_4) ) {
            save_bonus += 4;
        }
        else if ( nwn_GetHasFeat(stats, FEAT_PRESTIGE_POISON_SAVE_3) ) {
            save_bonus += 3;
        }
        else if ( nwn_GetHasFeat(stats, FEAT_PRESTIGE_POISON_SAVE_2) ) {
            save_bonus += 2;
        }
        else if ( nwn_GetHasFeat(stats, FEAT_PRESTIGE_POISON_SAVE_1) ) {
            save_bonus += 1;
        }

        if ( nwn_GetHasFeat(stats, FEAT_HARDINESS_VERSUS_POISONS) ) {
            save_bonus += 2;
        }

        if ( save == FEAT_RESIST_POISON ) {
            if ( nwn_GetHasFeat(stats, FEAT_HARDINESS_VERSUS_POISONS) ) {
                save_bonus += 4;
            }
            if ( nwn_GetHasFeat(stats, FEAT_SNAKE_BLOOD) ) {
                save_bonus += 2;
            }
        }

        break;
    case SAVING_THROW_TYPE_DISEASE:
        if ( nwn_GetHasFeat(stats, FEAT_RESIST_DISEASE) ) {
            save_bonus += 4;
        }
        break;
    case SAVING_THROW_TYPE_FEAR:
        if ( nwn_GetHasFeat(stats, FEAT_FEARLESS) ) {
            save_bonus += 2;
        }
        if ( nwn_GetHasFeat(stats, FEAT_RESIST_NATURES_LURE) ) {
            save_bonus += 2;
        }
        break;
    case SAVING_THROW_TYPE_SONIC:
        break;
    case SAVING_THROW_TYPE_ACID:
        break;
    case SAVING_THROW_TYPE_FIRE:
        break;
    case SAVING_THROW_TYPE_ELECTRICITY:
        break;
    case SAVING_THROW_TYPE_POSITIVE:
        break;
    case SAVING_THROW_TYPE_NEGATIVE:
        if ( nwn_GetHasFeat(stats, FEAT_STRONG_SOUL) ) {
            save_bonus += 2;
        }
        break;
    case SAVING_THROW_TYPE_DEATH:
        if ( nwn_GetHasFeat(stats, FEAT_STRONG_SOUL) ) {
            save_bonus += 2;
        }
        break;
    case SAVING_THROW_TYPE_COLD:
        break;
    case SAVING_THROW_TYPE_DIVINE:
        break;
    case SAVING_THROW_TYPE_TRAP:
        if ( nwn_GetHasFeat(stats, FEAT_DENEIRS_EYE) ) {
            save_bonus += 2;
        }

        if ( nwn_GetHasFeat(stats, FEAT_UNCANNY_DODGE_6) ) {
            save_bonus += 5;
            rogue = nwn_GetLevelByClass(stats, CLASS_TYPE_ROGUE);
            barb = nwn_GetLevelByClass(stats, CLASS_TYPE_BARBARIAN);
            if ( rogue >= barb ) {
                temp = rogue - 19;
            }
            else {
                temp = barb - 20;
            }
            if ( temp > 2 ) {
                save_bonus += temp / 3;
            }
        }
        else if ( nwn_GetHasFeat(stats, FEAT_UNCANNY_DODGE_5) ) {
            save_bonus += 4;
        }
        else if ( nwn_GetHasFeat(stats, FEAT_UNCANNY_DODGE_4) ) {
            save_bonus += 3;
        }
        else if ( nwn_GetHasFeat(stats, FEAT_UNCANNY_DODGE_3) ) {
            save_bonus += 2;
        }
        else if ( nwn_GetHasFeat(stats, FEAT_UNCANNY_DODGE_2) ) {
            save_bonus += 1;
        }
        break;
    case SAVING_THROW_TYPE_SPELL:
        if ( nwn_GetHasFeat(stats, FEAT_HARDINESS_VERSUS_SPELLS) ) {
            save_bonus += 2;
        }
        break;
    case SAVING_THROW_TYPE_GOOD:
        break;
    case SAVING_THROW_TYPE_EVIL:
        break;
    case SAVING_THROW_TYPE_LAW:
        break;
    case SAVING_THROW_TYPE_CHAOS:
        break;
    }

    return save_bonus;
}
