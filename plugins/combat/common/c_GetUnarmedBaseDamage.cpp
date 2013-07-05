#include "NWNXCombat.h"

extern CNWNXCombat combat;

DamageAmount GetUnarmedBaseDamage(CNWSCreature *cre, CNWSItem *it) {
    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    C2DA *feat_2da = nwn_GetCached2da(WEAPON_FEAT_2DA);
    if ( !props || !feat_2da) { return DamageAmount(); }

    uint16_t dmgtype = DAMAGE_TYPE_BLUDGEONING;
    int dice, sides, bonus = 0;
    int monk = nwn_GetLevelByClass(cre->cre_stats, CLASS_TYPE_MONK);
    if ( monk > 0 ) {
        dice = 1;
        bool big;
        switch(cre->cre_size) {
        case CREATURE_SIZE_HUGE:
        case CREATURE_SIZE_LARGE:
        case CREATURE_SIZE_MEDIUM:
            big = true;
            break;
        case CREATURE_SIZE_TINY:
        case CREATURE_SIZE_SMALL:
            big = false;
            break;
        }

        if ( monk >= 16 ) {
            if ( big ) { dice  = 1; sides = 20; }
            else { dice  = 2; sides = 6; }
        }
        else if ( monk >= 12 ) {
            dice = 1;
            if ( big ) { sides = 12; }
            else { sides = 10; }
        }
        else if ( monk >= 8 ) {
            dice = 1;
            if ( big ) { sides = 10; }
            else { sides = 8; }
        }
        else if ( monk >= 4 ) {
            dice = 1;
            if ( big ) { sides = 8; }
            else { sides = 6; }
        }
        else  {
            dice = 1;
            if ( big ) { sides = 6; }
            else { sides = 4; }
        }

    }
    else {
        switch(cre->cre_size) {
        case CREATURE_SIZE_HUGE:
        case CREATURE_SIZE_LARGE:
        case CREATURE_SIZE_MEDIUM:
            dice  = 1;
            sides = 3;
            break;
        case CREATURE_SIZE_TINY:
        case CREATURE_SIZE_SMALL:
            dice  = 1;
            sides = 2;
            break;
        }
    }

    int base    = it ? it->it_baseitem : BASE_ITEM_GLOVES;
    int wpn     = combat.baseitem_to_weapon(base);
    int feat;
    if ( (feat = nwn_Get2daInt(feat_2da, "SpecLeg", wpn)) > 0 &&
         nwn_GetHasFeat(cre->cre_stats, feat) ) {
        bonus += 18;
    }
    else if ( (feat = nwn_Get2daInt(feat_2da, "SpecSup", wpn)) > 0 &&
              nwn_GetHasFeat(cre->cre_stats, feat) ) {
        bonus += 12;
    }
    else if ( (feat = nwn_Get2daInt(feat_2da, "SpecEpic", wpn)) > 0 &&
              nwn_GetHasFeat(cre->cre_stats, feat) ) {
        bonus += 8;
    }
    else if ( (feat = nwn_Get2daInt(feat_2da, "Spec", wpn)) > 0 &&
              nwn_GetHasFeat(cre->cre_stats, feat) ) {
        bonus += 4;
    }

#if TA
    int32_t style = nwn_GetLocalInt(&cre->obj.obj_vartable,
                                    "pc_style_fighting");
    
    if ( style == 8 ) {
        auto canuse = CanUseClassAbilities(cre, CLASS_TYPE_MONK);
        if ( canuse.first ) {
            bonus += canuse.second / 6;
        }
    }
#endif // TA
    
    return DamageAmount(dmgtype, DiceRoll(dice, sides, bonus), false);
}
