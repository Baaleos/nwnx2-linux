#include "NWNXCombat.h"

extern CNWNXCombat combat;

int Handle_EffectEvent(WPARAM p, LPARAM a) {
    EventEffect *ev = (EventEffect *)p;
    if ( !ev->obj || !ev->eff ||
         ev->obj->obj_type != OBJECT_TYPE_CREATURE ) {
        return 0;
    }

    auto c = combat.get_creature(ev->obj->obj_id);
    if ( !c ) { return 0; }

    CGameEffect *eff = ev->eff;
    switch (eff->eff_type) {
    default: return 0;
    case EFFECT_TRUETYPE_ABILITY_INCREASE:
        if ( !ev->preapply ) {
            c->modifyAbilityEffect(
                eff->eff_integers[0],
                ev->is_remove ? -eff->eff_integers[1] : eff->eff_integers[1]);
            c->offense.updateAbility();
        }
        break;
    case EFFECT_TRUETYPE_ABILITY_DECREASE:
        if ( !ev->preapply ) {      
            c->modifyAbilityEffect(
                eff->eff_integers[0],
                ev->is_remove ? eff->eff_integers[1] : -eff->eff_integers[1]);
            c->offense.updateAbility();
        }
        break;
    case EFFECT_TRUETYPE_DAMAGE_IMMUNITY_INCREASE:
        if ( !ev->preapply ) {
            c->defense.modifyDamageImmunity(
                GetDamageIndexFromFlag(eff->eff_integers[0]),
                ev->is_remove ? -eff->eff_integers[1] : eff->eff_integers[1]);
        }
        break;

    case EFFECT_TRUETYPE_DAMAGE_IMMUNITY_DECREASE:
        if ( !ev->preapply ) {
            c->defense.modifyDamageImmunity(
                GetDamageIndexFromFlag(eff->eff_integers[0]),
                ev->is_remove ? eff->eff_integers[1] : -eff->eff_integers[1]);
        }
        break;

    case EFFECT_TRUETYPE_DAMAGE_REDUCTION:
        if ( !ev->preapply ) {
            c->defense.updateDamageReductionEffects(eff, ev->is_remove);
        }
        break;

    case EFFECT_TRUETYPE_DAMAGE_RESISTANCE:
        if ( !ev->preapply ) {
            c->defense.updateDamageResistEffects(eff, ev->is_remove);
        }
        break;

    case EFFECT_TRUETYPE_SAVING_THROW_INCREASE:
        if ( !ev->preapply                               &&
             eff->eff_integers[3] == RACIAL_TYPE_INVALID &&
             eff->eff_integers[4] == 0                   &&
             eff->eff_integers[5] == 0 ) {

            c->defense.modifySave(
                eff->eff_integers[1],
                eff->eff_integers[2],
                ev->is_remove ? -eff->eff_integers[0] : eff->eff_integers[0]);
        }
        break;
    case EFFECT_TRUETYPE_SAVING_THROW_DECREASE:
        if ( !ev->preapply                               &&
             eff->eff_integers[3] == RACIAL_TYPE_INVALID &&
             eff->eff_integers[4] == 0                   &&
             eff->eff_integers[5] == 0 ) {
            
            c->defense.modifySave(
                eff->eff_integers[1],
                eff->eff_integers[2],
                ev->is_remove ? eff->eff_integers[0] : -eff->eff_integers[0]);
        }
        break;

    case EFFECT_TRUETYPE_SKILL_INCREASE:
        if ( !ev->preapply ) {
            c->modifySkillEffect(
                eff->eff_integers[0],
                ev->is_remove ? -eff->eff_integers[1] : eff->eff_integers[1]);
        }
        break;
    case EFFECT_TRUETYPE_SKILL_DECREASE:
        if ( !ev->preapply ) {      
            c->modifySkillEffect(
                eff->eff_integers[0],
                ev->is_remove ? eff->eff_integers[1] : -eff->eff_integers[1]);
        }
        break;

    case EFFECT_TRUETYPE_ATTACK_INCREASE:
        if ( !ev->preapply                               &&
             eff->eff_integers[2] == RACIAL_TYPE_INVALID &&
             eff->eff_integers[3] == 0                   &&
             eff->eff_integers[4] == 0 ) {
            
            c->offense.modifyEffectAttackBonus(
                eff->eff_integers[1],
                ev->is_remove ? -eff->eff_integers[0] : eff->eff_integers[0]);
        }
        break;
    case EFFECT_TRUETYPE_ATTACK_DECREASE:
        if ( !ev->preapply                               &&
             eff->eff_integers[2] == RACIAL_TYPE_INVALID &&
             eff->eff_integers[3] == 0                   &&
             eff->eff_integers[4] == 0 ) {
            
            c->offense.modifyEffectAttackBonus(
                eff->eff_integers[1],
                ev->is_remove ? eff->eff_integers[0] : -eff->eff_integers[0]);
        }
        break;
    case EFFECT_TRUETYPE_DAMAGE_INCREASE:
        if ( !ev->preapply                               &&
             !nwn_GetItemByID(eff->eff_creator)          &&
             eff->eff_integers[2] == RACIAL_TYPE_INVALID &&
             eff->eff_integers[3] == 0                   &&
             eff->eff_integers[4] == 0 ) {

            c->offense.modifyEffectDamamge(
                eff->eff_integers[1],
                eff->eff_integers[5],
                eff->eff_integers[0],
                false,
                ev->is_remove);         
        }
        break;

    case EFFECT_TRUETYPE_DAMAGE_DECREASE:
        if ( !ev->preapply                               &&
             !nwn_GetItemByID(eff->eff_creator)          &&
             eff->eff_integers[2] == RACIAL_TYPE_INVALID &&
             eff->eff_integers[3] == 0                   &&
             eff->eff_integers[4] == 0 ) {

            c->offense.modifyEffectDamamge(
                eff->eff_integers[1],
                eff->eff_integers[5],
                eff->eff_integers[0],
                true,
                ev->is_remove);         

        }

        break;

    case EFFECT_TRUETYPE_IMMUNITY: 
        if ( !ev->preapply                               &&
             eff->eff_integers[1] == RACIAL_TYPE_INVALID &&
             eff->eff_integers[2] == 0                   &&
             eff->eff_integers[3] == 0 ) {
            c->defense.modifyMiscImmunity(
                eff->eff_integers[0],
                eff->eff_integers[4]);
        }
        break;
#if TA
    case TA_EFF_STACK_DMG_REDUCTION_BONUS:
        if ( ev->preapply ) {
            ev->suppress = true;
        }
        else {
            c->defense.modifyStackSoak(
                eff->eff_integers[0],
                ev->is_remove ? -eff->eff_integers[1] : eff->eff_integers[1]);
        }
        break;

    case TA_EFF_STACK_DMG_REDUCTION_PENALTY:
        if ( ev->preapply ) {
            ev->suppress = true;
        }
        else {
            c->defense.modifyStackSoak(
                eff->eff_integers[0],
                ev->is_remove ? eff->eff_integers[1] : -eff->eff_integers[1]);
        }
        break;

    case TA_EFF_STACK_DMG_RESIST_BONUS:
        if ( ev->preapply ) {
            ev->suppress = true;
        }
        else {
            c->defense.modifyStackResist(
                GetDamageIndexFromFlag(eff->eff_integers[0]),
                ev->is_remove ? -eff->eff_integers[1] : eff->eff_integers[1]);
        }
        break;
    case TA_EFF_STACK_DMG_RESIST_PENALTY:
        if ( ev->preapply ) {
            ev->suppress = true;
        }
        else {
            c->defense.modifyStackResist(
                GetDamageIndexFromFlag(eff->eff_integers[0]),
                ev->is_remove ? eff->eff_integers[1] : -eff->eff_integers[1]);
        }
        break;

        // case TA_EFF_DAMAGE_BONUS:
        //      ev->suppress = true;
        //      break;

        // case TA_EFF_DAMAGE_PENALTY:
        //      ev->suppress = true;
        //      break;
#endif
    }
    
    return 1;
}
