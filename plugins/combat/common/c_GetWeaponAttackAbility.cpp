#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetWeaponAttackAbility(CNWSCreature *cre, CNWSItem *it) {
    CNWSCreatureStats *stats = cre->cre_stats;
    uint32_t abil = ABILITY_STRENGTH;
    int8_t mod = nwn_GetAbilityModifier(cre->cre_stats, ABILITY_STRENGTH, false);
    
    if ( GetIsRangedWeapon(it) ) {
        mod = nwn_GetAbilityModifier(cre->cre_stats, ABILITY_DEXTERITY, false);
    }
    // Finesse
    else if ( GetIsWeaponLight(cre, it, true) &&
              nwn_GetHasFeat(cre->cre_stats, FEAT_WEAPON_FINESSE) ) {
        mod = std::max(mod, nwn_GetAbilityModifier(stats, ABILITY_DEXTERITY, false));
    }

    if ( nwn_GetHasFeat(stats, 2002) && //Intuitive Strike.
         GetIsWeaponIntuitable(cre, it) ) {

        mod = std::max(mod, nwn_GetAbilityModifier(stats, ABILITY_WISDOM, false));
    }

    if ( cre->cre_is_poly ) {
        if ( nwn_GetLevelByClass(stats, CLASS_TYPE_SHIFTER) > 1 || 
             nwn_GetLevelByClass(stats, CLASS_TYPE_DRUID) > 1 ) {

            mod = std::max(mod, nwn_GetAbilityModifier(stats, ABILITY_WISDOM, false));
        }           
        else if ( nwn_GetLevelByClass(stats, CLASS_TYPE_PALEMASTER) > 10 ) {
            mod = std::max(mod, nwn_GetAbilityModifier(stats, ABILITY_CHARISMA, false));
            mod = std::max(mod, nwn_GetAbilityModifier(stats, ABILITY_INTELLIGENCE, false));
        }
    }

    combat.Log(3, "Obj: %X, Item: %X, Attack Ability Modifier: %d\n",
               cre->obj.obj_id, 
               it ? it->obj.obj_id : OBJECT_INVALID,
               mod);
    
    return mod;
}
