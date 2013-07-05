#include "NWNXCombat.h"

extern CNWNXCombat combat;

ArmorClass GetBaseArmorClass(CNWSCreature *cre) {
    CNWSCreatureStats *stats = cre->cre_stats;

    ArmorClass ac_base = { 
        stats->cs_ac_armour_base,
        stats->cs_ac_natural_base,
        0,
        0,
        stats->cs_ac_shield_base
    };

    // Feat AC is treated as Natural.
    if ( nwn_GetHasFeat(stats, FEAT_EPIC_ARMOR_SKIN) ) {
        ac_base.natural += 2;
    }

    // Size AC is treated as Natural.
    switch(cre->cre_size) {
    case CREATURE_SIZE_TINY:  ac_base.natural += 2; break;
    case CREATURE_SIZE_SMALL: ac_base.natural += 1; break;
    case CREATURE_SIZE_LARGE: ac_base.natural -= 1; break;
    case CREATURE_SIZE_HUGE:  ac_base.natural -= 2; break;
    }


    // Class AC... well Monks don't lose it flatfooted so
    // set it as Natural.
    auto monk = CanUseClassAbilities(cre, CLASS_TYPE_MONK);
    if ( monk.first ) {
        ac_base.natural += nwn_GetAbilityModifier(stats, ABILITY_WISDOM, false);
        ac_base.natural += monk.second / 5;
    }

    // Skill ACs are treated as Dodge... I.e. they won't apply when
    // flatfooted, etc.
    ac_base.dodge += nwn_GetSkillRank(cre, SKILL_TUMBLE, NULL, true) / 5;
    ac_base.dodge += nwn_GetSkillRank(cre, SKILL_CRAFT_ARMOR, NULL, false) / 40;

    int32_t style = nwn_GetLocalInt(&cre->obj.obj_vartable,
                                    "pc_style_fighting");

    int str_req = ( stats->cs_race == RACIAL_TYPE_HALFORC ) ? 28 : 30;

    if ( stats->cs_is_pc                                      &&
         nwn_GetLevelByClass(stats, CLASS_TYPE_FIGHTER) >= 30 && 
         (style == 1 /*fencer*/ || stats->cs_str >= str_req) ) {
        // NOTE: natural not dodge here...
        ac_base.natural += nwn_GetSkillRank(cre, SKILL_DISCIPLINE, NULL, true) / 5;
    }

    if( style == 6 && monk.first ) { // dragonpalm
        ac_base.deflection += monk.second / 6;
    }

    if ( style == 9 /*ninja*/ && monk.second == 0 ) {
        ac_base.deflection += stats->cs_int_mod;
    }

    if ( nwn_GetHasFeat(stats, FEAT_BONE_SKIN_2) ) {
        int pm = nwn_GetLevelByClass(stats, CLASS_TYPE_PALEMASTER);
        pm /= 4;

        ac_base.deflection += 2;

        if ( ( stats->cs_str >= 25 || stats->cs_dex >= 25 ) ||
             ( nwn_GetHasFeat(stats, 2002) && stats->cs_wis >= 25) ) {
            ac_base.deflection += pm;
        }
        else {
            ac_base.deflection += pm * 2;
        }
    }

    auto ranger = CanUseClassAbilities(cre, CLASS_TYPE_RANGER);
    if ( ranger.first ) {
        int wisac = stats->cs_wis_mod;
        if ( wisac <= 20 ) {
            ac_base.natural += CLAMP<int>(wisac, 0, ranger.second / 2);
        }
        else if ( ranger.second > 20 ) {
            ac_base.natural += std::min(wisac, ranger.second);
        }
    }
    
    // No touch on RDD armor...
    if ( nwn_GetHasFeat(stats, FEAT_DRAGON_ARMOR)) {
        int rdd = nwn_GetLevelByClass(stats, CLASS_TYPE_DRAGONDISCIPLE);

        switch (rdd) {
        case  0: ac_base.deflection += 0; break;
        case  1: ac_base.deflection += 1; break;
        case  2: ac_base.deflection += 1; break;
        case  3: ac_base.deflection += 1; break;
        case  4: ac_base.deflection += 1; break;
        case  5: ac_base.deflection += 2; break;
        case  6: ac_base.deflection += 2; break;
        case  7: ac_base.deflection += 2; break;
        case  8: ac_base.deflection += 3; break;
        case  9: ac_base.deflection += 3; break;

        default: ac_base.deflection += 4 + ((rdd - 10) / 5); break;
        }
    }

    return ac_base;
}
