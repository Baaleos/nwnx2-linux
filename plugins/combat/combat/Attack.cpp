#include <algorithm>
#include <utility>
#include "talib/util/math.h"
#include "Attack.h"

Attack::Attack(CNWSCreature *attacker, CNWSObject *target)
    : attacker_nwn(attacker),
      target_nwn(target),
      object_(NULL),
      vs_(target ? target->obj_vtable->AsNWSCreature(target) : NULL) {

    CNWSCombatRound *cr = attacker->cre_combat_round;
    int cur_att = cr->cr_current_attack;
    CNWSCombatAttackData *data = CNWSCombatRound__GetAttack(cr, cur_att);
        
    data->cad_attack_group = ++cr->cr_attack_group;
    data->cad_target = target->obj_id;
    data->cad_attack_mode = attacker->cre_mode_combat;
    data->cad_attack_type = CNWSCombatRound__GetWeaponAttackType(cr);

    equip_num_ = GetEquipNumFromAttackType(data->cad_attack_type);

    attacker_ = combat.get_creature(attacker->obj.obj_id);

    if ( target_nwn && target_nwn->obj_type == OBJECT_TYPE_CREATURE ) {
        target_ = combat.get_creature(target_nwn->obj_id);
        defense = &target_->defense;
        target_is_cre = true;
    }
    else {
        object_ = SituatedObject(target_nwn);
        defense = &object_;
        target_is_cre = false;
    }

    // Since crit immunity can be a random % chance... cache it so that
    // it's determined once per attack.
    is_crit_immune_ = defense->isImmune(IMMUNITY_TYPE_CRITICAL_HIT);
    
    combat.Log(3, 
               "Attack::update(): Current Attack: %d, Attack Group: %d, "
               "Target: %x (%d), Mode: %d, Attack Type: %d, Equip num\n",
               cur_att, data->cad_attack_group, data->cad_target, target_is_cre,
               data->cad_attack_mode, data->cad_attack_type, equip_num_);

    attack_data_.setAttackData(data, attacker_nwn);
}
    
uint32_t Attack::getIterationPenalty() {
    CNWSCombatRound *cr = attacker_nwn->cre_combat_round;
    int iter_pen = 0;
    int spec_att = attack_data_.getSpecialAttack();
    int atk_type = attack_data_.getAttackType();

    // Deterimine the iteration penalty for an attack.  Not all attack types are
    // have it.
    if ( atk_type == ATTACK_TYPE_OFFHAND ) {
        iter_pen = 5 * attacker_nwn->cre_combat_round->cr_offhand_taken;
        ++cr->cr_offhand_taken;
    }
    // Normally this would have checked for ATTACK_TYPE_EXTRA1 or
    // ATTACK_TYPE_EXTRA1, but those seemed superfluous.
    else if ( cr->cr_current_attack > cr->cr_onhand_atks ) {
        // There is no iteration penalty on whirlwind attacks or great cleave.
        if ( spec_att != FEAT_WHIRLWIND_ATTACK   &&
             spec_att != FEAT_IMPROVED_WHIRLWIND &&
             spec_att != SPECIAL_ATTACK_CLEAVE_GREAT ) {
            iter_pen = 5 * cr->cr_extra_taken;
        }
        ++cr->cr_extra_taken;
    }
    else if ( spec_att != SPECIAL_ATTACK_AOO    &&
              spec_att != SPECIAL_ATTACK_CLEAVE &&
              spec_att != SPECIAL_ATTACK_CLEAVE_GREAT ) {
        int iter = attacker_->offense.getIteration(equip_num_);
        iter_pen = cr->cr_current_attack * iter;
    }
    return iter_pen;
}

int32_t Attack::resolveSpecialAttackAttackBonus() {
    const uint32_t type = attack_data_.getSpecialAttack();

    int adj = 0;
    CNWSItem *weap, *vs_weap;
    int32_t level = 0;
    int32_t align = 0;
    
    switch ( type ) {
    default: combat.Log(3, "NOTICE: Unhandled Special Attack AB: %d", type);
        break;
        
    case SPECIAL_ATTACK_AOO:
        if ( nwn_GetHasFeat(attacker_nwn->cre_stats, FEAT_OPPORTUNIST) ) {
            return 4;
        }
        break;
        
    case SPECIAL_ATTACK_CALLED_SHOT_ARM:
    case SPECIAL_ATTACK_CALLED_SHOT_LEG:
        return -4;
        
    case SPECIAL_ATTACK_CLEAVE: break;
    case SPECIAL_ATTACK_CLEAVE_GREAT: break;
        
    case SPECIAL_ATTACK_DISARM:
    case SPECIAL_ATTACK_DISARM_IMPROVED:
        weap = nwn_GetItemInSlot(attacker_nwn, EQUIPMENT_SLOT_RIGHTHAND);
        vs_weap = nwn_GetItemInSlot(vs_.versus, EQUIPMENT_SLOT_RIGHTHAND);
        
        if ( weap && vs_weap ) {
            adj = 4 * (nwn_GetItemSize(weap) - nwn_GetItemSize(vs_weap));
        }

        if ( type == SPECIAL_ATTACK_DISARM_IMPROVED ) {
            return adj - 4;
        }
        else {
            return adj - 6;
        }
        break;
        
    case SPECIAL_ATTACK_KI_DAMAGE: break;
        
    case SPECIAL_ATTACK_KNOCKDOWN_IMPROVED:
    case SPECIAL_ATTACK_KNOCKDOWN:
        return -4;
        
    case SPECIAL_ATTACK_QUIVERING_PALM:
        break;
        
    case SPECIAL_ATTACK_SAP:
        return -4;
        
    case SPECIAL_ATTACK_SMITE_EVIL:
    case SPECIAL_ATTACK_SMITE_GOOD:
        if ( SPECIAL_ATTACK_SMITE_EVIL ) {
            level = nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                        CLASS_TYPE_PALADIN);
            level += nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                         CLASS_TYPE_DIVINECHAMPION);
            align = 4;
        }
        else if ( SPECIAL_ATTACK_SMITE_GOOD) {
            level = nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                        CLASS_TYPE_BLACKGUARD);
            align = 5;
        }
        
        if ( level >= 35 || vs_.goodevil == align ) {
            return attacker_->getAbilityModifier(ABILITY_CHARISMA);
        }
        break;
        
    case SPECIAL_ATTACK_STUNNING_FIST:
        return -4;
    }

    return 0;
}

DamageAmount Attack::resolveSpecialAttackDamage() {
    const uint32_t type = attack_data_.getSpecialAttack();

    int level = 0;
    int feat  = 0;

    int32_t dmg_index = -1;
    DiceRoll roll;
    bool is_penalty = false;
    int align = 0;
        
    switch ( type ) {
    case SPECIAL_ATTACK_AOO: break;
    case SPECIAL_ATTACK_CALLED_SHOT_ARM: break;
    case SPECIAL_ATTACK_CALLED_SHOT_LEG: break;
    case SPECIAL_ATTACK_CLEAVE: break;
    case SPECIAL_ATTACK_CLEAVE_GREAT: break;
    case SPECIAL_ATTACK_DISARM: break;
    case SPECIAL_ATTACK_DISARM_IMPROVED: break;
    case SPECIAL_ATTACK_KI_DAMAGE: break;
    case SPECIAL_ATTACK_KNOCKDOWN: break;
    case SPECIAL_ATTACK_KNOCKDOWN_IMPROVED: break;
    case SPECIAL_ATTACK_QUIVERING_PALM: break;
    case SPECIAL_ATTACK_SAP: break;
        
    case SPECIAL_ATTACK_SMITE_EVIL:
    case SPECIAL_ATTACK_SMITE_GOOD:
        // This is the multiplier for the damage, see below.
        feat = 1 + nwn_GetHasNthFeat(attacker_nwn,
                                     FEAT_EPIC_GREAT_SMITING_1,
                                     FEAT_EPIC_GREAT_SMITING_10);

        if ( SPECIAL_ATTACK_SMITE_EVIL ) {
            level = nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                        CLASS_TYPE_PALADIN);
            level += nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                         CLASS_TYPE_DIVINECHAMPION);
            align = 4;
        }
        else if ( SPECIAL_ATTACK_SMITE_GOOD) {
            level = nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                        CLASS_TYPE_BLACKGUARD);
            align = 5;
        }
        
        if ( level >= 35 || vs_.goodevil == align ) {
            dmg_index = DAMAGE_INDEX_BASE_WEAPON;
            roll.bonus = level * feat;
        }
        
    case SPECIAL_ATTACK_STUNNING_FIST:
        roll.bonus = 4;
        is_penalty = true;
        dmg_index = DAMAGE_INDEX_BASE_WEAPON;
    }

    return DamageAmount(dmg_index, roll, is_penalty);
}

bool Attack::resolveSpecialAttackImpact() {
    const uint32_t type = attack_data_.getSpecialAttack();

    if ( type == SPECIAL_ATTACK_AOO ) {
        return true;
    }
    else if ( type == SPECIAL_ATTACK_CALLED_SHOT_ARM ) {
        if ( attack_data_.getAttackBonus() >
             target_->getSkillRank(SKILL_DISCIPLINE, NULL, false) ) {

            CGameEffect *e = effect_attack(-2, ATTACK_TYPE_MISC);
            e->eff_duration = 24; // 4 rounds
            nwn_SetDurationType(e, DURATION_TYPE_TEMPORARY);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            return true;
        }
    }
    else if ( type == SPECIAL_ATTACK_CALLED_SHOT_LEG ) {
        if ( attack_data_.getAttackBonus() >
             target_->getSkillRank(SKILL_DISCIPLINE, NULL, false) ) {

            CGameEffect *e = effect_ability(ABILITY_DEXTERITY, -2);
            e->eff_duration = 24; // 4 rounds
            nwn_SetDurationType(e, DURATION_TYPE_TEMPORARY);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);

            e = effect_move_speed(-20);
            e->eff_duration = 24; // 4 rounds
            nwn_SetDurationType(e, DURATION_TYPE_TEMPORARY);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            
            return true;
        }
    }
    else if ( type == SPECIAL_ATTACK_CLEAVE ) {
        return true;
    }
    else if ( type == SPECIAL_ATTACK_CLEAVE_GREAT ) {
        return true;
    }
    else if ( type == SPECIAL_ATTACK_DISARM ||
              type == SPECIAL_ATTACK_DISARM_IMPROVED ) {
        if ( attack_data_.getAttackBonus() >
             target_->getSkillRank(SKILL_DISCIPLINE, NULL, false) ) {
            
            CGameEffect *e = effect_disarm();
            nwn_SetDurationType(e, DURATION_TYPE_INSTANT);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            return true;
        }
    }
    else if ( type == SPECIAL_ATTACK_KI_DAMAGE ) {
        return true;
    }
    else if ( type == SPECIAL_ATTACK_KNOCKDOWN ||
              type == SPECIAL_ATTACK_KNOCKDOWN_IMPROVED ) {
        if ( attack_data_.getAttackBonus() >
             target_->getSkillRank(SKILL_DISCIPLINE, NULL, false) ) {
             
            CGameEffect *e = effect_knockdown();
            e->eff_duration = 6; // One round
            nwn_SetDurationType(e, DURATION_TYPE_TEMPORARY);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            return true;
        }
    }
    else if ( type == SPECIAL_ATTACK_QUIVERING_PALM ) {
        // The effects of this are dealt with in resolvePostMeleeDamage
        return true;
    }
    else if ( type == SPECIAL_ATTACK_SAP ) {
        if ( attack_data_.getAttackBonus() >
             target_->getSkillRank(SKILL_DISCIPLINE, NULL, false) ) {
            
            CGameEffect *e = effect_dazed();
            e->eff_duration = 12; // 5 rounds
            nwn_SetDurationType(e, DURATION_TYPE_TEMPORARY);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            return true;
        }
    }
    else if ( type == SPECIAL_ATTACK_SMITE_EVIL ) {
#ifdef TA
        int32_t level = nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                            CLASS_TYPE_PALADIN);
        level += nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                     CLASS_TYPE_DIVINECHAMPION);

        if ( level >= 35 || vs_.goodevil == 4 ) {
            CGameEffect *e = effect_damage_immunity(DAMAGE_TYPE_DIVINE, -10);
            e->eff_duration = 30; // 5 rounds
            nwn_SetDurationType(e, DURATION_TYPE_TEMPORARY);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            return true;
        }
#else
        if ( vs_.goodevil == 4 ) { return true; }
#endif
    }
    else if ( type == SPECIAL_ATTACK_SMITE_GOOD ) {
#ifdef TA
        int32_t level = nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                            CLASS_TYPE_BLACKGUARD);

        if ( level >= 35 || vs_.goodevil == 5 ) {
            CGameEffect *e = effect_damage_immunity(DAMAGE_TYPE_NEGATIVE, -10);
            e->eff_duration = 30; // 5 rounds
            nwn_SetDurationType(e, DURATION_TYPE_TEMPORARY);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            return true;
        }
#else
        if ( vs_.goodevil == 5 ) { return true; }
#endif
    }
    else if ( type == SPECIAL_ATTACK_STUNNING_FIST ) {
        if ( is_crit_immune_ ) {
            // Immune to criticals message.
            CNWCCMessageData *msg = CNWCCMessageData_create();
            CExoArrayList_int32_add(&msg->integers, 126);
            CExoArrayList_uint32_add(&msg->objects, target_nwn->obj_id);
            attack_data_.addCCMessage(msg);
            return false;
        }


        int dc = 10 + attacker_->getAbilityModifier(ABILITY_WISDOM);
        dc += nwn_GetLevelByClass(attacker_nwn->cre_stats,
                                  CLASS_TYPE_MONK);

        dc += 2 * nwn_GetHasNthFeat(attacker_nwn, 
                                    FEAT_EPIC_IMPROVED_STUNNING_FIST_1,
                                    FEAT_EPIC_IMPROVED_STUNNING_FIST_10);

        //
        if ( !CNWSCreature__SavingThrowRoll(
                 reinterpret_cast<CNWSCreature*>(target_nwn), 
                 SAVING_THROW_FORT, dc, 0, 
                 attacker_nwn->obj.obj_id,
                 1, 0, 1) ) {
            
            CGameEffect *e = effect_stunned();
            e->eff_duration = 18; // 3 Rounds.
            e->eff_integers[1] = 1;
            nwn_SetDurationType(e, DURATION_TYPE_TEMPORARY);
            nwn_SetSubType(e, SUBTYPE_SUPERNATURAL);
            attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            
            return true;
        }
    }
    return false;
}

bool Attack::isOffhand() {
    CNWSCombatRound *cr = attacker_nwn->cre_combat_round;
    return cr->cr_current_attack + 1 > 
        cr->cr_effect_atks + cr->cr_additional_atks + cr->cr_onhand_atks;
}

uint32_t Attack::resolveAmmo(uint32_t attack_count, bool force_equip) {
    return attacker_->offense.ammoAvailable(attack_count, force_equip);
}

uint32_t Attack::resolveArmorClass() {
    if ( !target_is_cre ) {
        return defense->getArmorClass(false, situ_flags_, false);
    }
    
    CNWSCreature *tar = reinterpret_cast<CNWSCreature *>(target_nwn);
    CNWSCreatureStats *stats = tar->cre_stats;
    bool dexed = defense->canUseDexAC(target_state_, attack_data_.isRanged());

    uint32_t dex_ac = 0;
    if ( dexed ) {
        // If this is an attack of opportunity and target has mobility there is a +4 ac bonus.
        // The constant for AoO is probably wrong.
        if ( attack_data_.getSpecialAttack() == SPECIAL_ATTACK_AOO &&
             nwn_GetHasFeat(stats, FEAT_MOBILITY) ) {
            dex_ac += 4;
        }
            
        if ( nwn_GetHasFeat(stats, FEAT_DODGE) ) {
            CNWSCombatRound *cr = tar->cre_combat_round;
            if ( cr->cr_dodge_target == OBJECT_INVALID ) {
                cr->cr_dodge_target = attacker_nwn->obj.obj_id;
            }
                
            if ( cr->cr_dodge_target == attacker_nwn->obj.obj_id &&
                 !CanUseClassAbilities(tar, CLASS_TYPE_MONK).first ) {
                ++dex_ac;
            }
        }
    }

    return defense->getArmorClass(dexed, situ_flags_, false) + dex_ac;
}

void Attack::resolveAttackRoll() {
    // If there is a Coup De Grace, automatic hit.  Effects are dealt with in 
    // resolve_post_damage
    if ( attack_data_.isCoupDeGrace() ) {
        attack_data_.setResult(7);
        attack_data_.setAttackBonus(0, 20);
        return;
    }

    uint32_t attack_type = attack_data_.getAttackType();
    uint32_t ac = resolveArmorClass();
    int32_t ab = attacker_->offense.getAttackBonus(equip_num_);
    ab -= getIterationPenalty();
    if ( attack_data_.isSpecialAttack() && target_is_cre ) {
        ab += resolveSpecialAttackAttackBonus();
    }
    
    uint32_t roll = true_random(1, 20);
    attack_data_.setAttackBonus(ab, roll);

    bool is_hit = (roll + ab >= ac || roll == 20) && roll != 1;
    combat.Log(3, "resolveAttackRoll: is hit: %d; Roll: %d, AB: %d, AC: %d\n",
               is_hit, roll, ab, ac);

    // If conceal/misschance, parry, deflect arrow, etc, came into
    // play, no point in continuing.  NOTE: Epic Dodge is done after
    // the damage roll as it's not used unless damage will be done.
    if ( is_hit && resolveMissOpportunities() ) { return; }

    if ( !is_hit ) {
        attack_data_.setResult(4);
        attack_data_.setMissedBy( roll == 1 ? 1 : ac - ab + roll );
        return;
    }
    else {
        attack_data_.setResult(1);
    }

    resolveCriticalRoll(ab, roll, ac);
}

void Attack::resolveCriticalRoll(int32_t ab, int32_t roll, int32_t ac) {
    int32_t threat = attacker_->offense.getCritThreat(equip_num_);
    combat.Log(3, "resolveCriticalRoll: AB: %d, Roll: %d, AC: %d, Threat: %d\n",
               ab, roll, ac, threat);

    // Determine if this is a critical hit.
    // 1. Roll is in threat range.
    // 2. Target AC is less than 20 higher than attackers AB.
    if ( roll > threat && ac - ab <= 20 ) {
        int32_t threat_roll = true_random(1, 20);
        combat.Log(3, "resolveCriticalRoll: Threat Roll: %d\n", threat_roll);
        attack_data_.setCriticalResult(threat_roll, 1);

        if ( threat_roll + ab >= ac ) {
            if ( !is_crit_immune_ ) {
                attack_data_.setResult(3);
            }
            else {
                combat.Log(0, "resolveCriticalRoll: Send Immunity Message\n");
                // Immune to criticals message.
                CNWCCMessageData *msg = CNWCCMessageData_create();
                CExoArrayList_int32_add(&msg->integers, 126);
                CExoArrayList_uint32_add(&msg->objects, target_nwn->obj_id);
                attack_data_.addCCMessage(msg);
            }
        }
    }
}

void Attack::resolveDamage() {
    bool ki_strike = attack_data_.getSpecialAttack() == 882;
    
    // If this is a critical hit determine the multiplier.
    int mult = 1;
    if ( attack_data_.isCriticalHit() ) {
        mult = attacker_->offense.getCritMultiplier(equip_num_);
    }
    
    DamageResult result = attacker_->offense.getDamageVersus(equip_num_);
    combat.Log(3, "resolveDamage: Rolling Damage: Multiplier: %d\n", mult);
    result.roll(mult);

    if ( attack_data_.isSpecialAttack() && target_is_cre ) {
        combat.Log(3, "resolveDamage: Special Attack: %d\n", attack_data_.getSpecialAttack());
        // TODO: Special attack crit
        result.add(resolveSpecialAttackDamage());
    }
    
    // Situation modifiers are add after the roll.
    for ( size_t i = 0; i < COMBAT_SITUATION_NUM; ++i ) {
        CombatMod* mod;
        if ( situ_flags_ & (1 << i) &&
             (mod = attacker_->getSituationalModifier(i)) ) {
            combat.Log(3, "resolveDamage: Situation: %d\n", i);

            result.add(mod->dmg);
        }
    }

    damage_total_ = result.get_total();
    combat.Log(3, "resolveDamage: Pre-mod Total: %d\n", damage_total_);
    result.compactPhysicals();
    defense->doDamageMods(result);
    damage_total_ = result.get_total();
    combat.Log(3, "resolveDamage: Post-mod Total: %d\n", damage_total_);

    CNWSCreature *tar = target_is_cre
        ? reinterpret_cast<CNWSCreature *>(target_nwn)
        : NULL;

    result.finalize();
    
    // Defensive Roll
    if ( tar &&
         defense->getHPCurrent(true) - damage_total_ <= 0 &&
         !(target_state_ & COMBAT_TARGET_STATE_FLATFOOTED) &&
         nwn_GetHasFeat(tar->cre_stats, FEAT_DEFENSIVE_ROLL) ) {
        nwn_DecrementFeatRemainingUses(tar->cre_stats, FEAT_DEFENSIVE_ROLL);
        if ( false ) { /* TODO: target:ReflexSave(total, nwn.SAVING_THROW_TYPE_DEATH, attacker) */
            for ( int i = 0; i < DAMAGE_TYPE_NUM; ++i ) {
                result.damages[i] /= 2;
            }
            damage_total_ = result.get_total();
            combat.Log(3, "resolveDamage: Post Defensive Roll Damage: %d\n",
                       damage_total_);
        }
    }

    // Add the damage result info to the AttackData
    attack_data_.copyDamage(result);
  
    // Epic Dodge : Don't want to use it unless we take damage.
    if ( tar                                        && 
         damage_total_ > 0                          &&
         !tar->cre_combat_round->cr_epic_dodge_used &&
         nwn_GetHasFeat(tar->cre_stats, FEAT_EPIC_DODGE) ) {

        combat.Log(3, "resolveDamage: Epic Dodge Used\n");
        // TODO: Send Epic Dodge Message
        attack_data_.setResult(4);
        tar->cre_combat_round->cr_epic_dodge_used = 1;
    }
    else {
        if ( target_nwn->obj_is_invulnerable ) {
            combat.Log(3, "resolveDamage: Target Invulnerable\n");

            damage_total_ = 0;
        }
        else {
            // Item onhit cast spells are done regardless of whether weapon damage is
            // greater than zero.
            resolveItemCastspell();
        }

        if ( damage_total_ > 0 ) {
            resolveOnhitEffect();

        }
    }

    combat.Log(3, "%s", result.toString().c_str());
}

// NOTE: This does not work as the default NWN engine which only applies
// the first OnHitCastSpell property that it finds.  This will use all 
// OnHitCastSpell properties on weapons, shields, and armor.
void Attack::resolveItemCastspell() {
    const CombatWeapon *weap = attacker_->offense.getWeapon(equip_num_);
    CNWSItem *item = nwn_GetItemByID(weap->id);
    if ( !target_is_cre ) { return; } 

    CNWSCreature *tar = reinterpret_cast<CNWSCreature *>(target_nwn);

    // Attacker...
    if ( item ) {
        attack_data_.addOnHitCastSpells(attacker_nwn,
                                        target_nwn,
                                        item,
                                        false);
    }

    // Target...
    item = nwn_GetItemInSlot(tar, EQUIPMENT_SLOT_LEFTHAND);
    if ( item && ( item->it_baseitem == BASE_ITEM_SMALLSHIELD ||
                   item->it_baseitem == BASE_ITEM_LARGESHIELD ||
                   item->it_baseitem == BASE_ITEM_TOWERSHIELD ) ) {

        attack_data_.addOnHitCastSpells(attacker_nwn,
                                        target_nwn,
                                        item,
                                        true);
    }

    item = nwn_GetItemInSlot(tar, EQUIPMENT_SLOT_CHEST);
    if ( item ) {
        attack_data_.addOnHitCastSpells(attacker_nwn,
                                        target_nwn,
                                        item,
                                        true);
    }
}

bool Attack::resolveMissChance() {
    uint32_t mc = attacker_->offense.getMissChance(target_nwn);
    uint32_t conc = defense->getConcealment(attack_data_.isRanged());
    
    // If concealment and mis-chance are less than or equal to zero
    // there is no chance of them affecting the outcome of an attack.
    if ( conc <= 0 && mc <= 0 ) { return false; }

    int32_t chance, attack_result;
    bool result = true;
    
    // Deterimine which of miss chance and concealment is higher.
    // attack_result is a magic number for the NWN combat engine that
    // determines which combat messages are sent to the player.
    if ( mc < conc ) {
        chance = conc;
        attack_result = 8;
    }
    else {
        chance = mc;
        attack_result = 9;
    }

    int32_t adj = chance;
    // The attacker has two possible chances to over come miss chance / concealment
    // if they posses the blind fight feat.  If not they only have one chance to do so.
    if ( true_random(1, 100) <= chance ) {
        result = false;
    }
    
    if ( nwn_GetHasFeat(attacker_nwn->cre_stats, FEAT_BLIND_FIGHT) ) {
        if ( !result || true_random(1, 100) <= chance ) {
            result = false;
        }
        adj = floor((chance * chance) / 100);
    }

    if ( result ) {
        attack_data_.setResult(attack_result);
        attack_data_.setMissedBy(1);
    }
    attack_data_.setConcealment(adj);

    return result;
}

bool Attack::resolveDeflectArrow() {
    CNWSCreature *tar = (CNWSCreature*)target_nwn;

    if ( tar->cre_combat_round->cr_deflect_arrow           &&
         attack_data_.isRanged()                           &&
         !(target_state_ & COMBAT_TARGET_STATE_FLATFOOTED) &&
         nwn_GetHasFeat(tar->cre_stats, FEAT_DEFLECT_ARROWS) ) {

        CNWSItem *on = nwn_GetItemInSlot(tar, EQUIPMENT_SLOT_RIGHTHAND);
        CNWSItem *off = nwn_GetItemInSlot(tar, EQUIPMENT_SLOT_LEFTHAND);

        if ( !on || ( nwn_GetRelativeWeaponSize(tar, on) <= 0 &&
                      !GetIsRangedWeapon(on)                  &&
                      !off ) ) {

            /* Not sure that these calculations are ever even used.
               on = nwn_GetItemInSlot(attacker_nwn, EQUIPMENT_SLOT_RIGHTHAND);
               on = on->it_baseitem;
               bool bow = ( on->it_baseitem == nwn.BASE_ITEM_DART        ||
               on->it_baseitem == nwn.BASE_ITEM_THROWINGAXE ||
               on->it_baseitem == nwn.BASE_ITEM_SHURIKEN );

               CNWSCreature__CalculateProjectileTimeToTarget(attacker_nwn,
               tar->obj.obj_position,
               bow);
            */
            tar->cre_combat_round->cr_deflect_arrow = 0;
            attack_data_.setDeflected(true);
            // TODO: Check result!
            attack_data_.setResult(2);
            return true;
        }
    }

    return false;
}

bool Attack::resolveParry() {
    CNWSCreature *tar = reinterpret_cast<CNWSCreature*>(target_nwn);
    
    if ( attack_data_.getAttackRoll() == 20           ||
         tar->cre_mode_combat != COMBAT_MODE_PARRY    ||
         !tar->cre_combat_round->cr_parry_actions     ||
         attacker_nwn->cre_combat_round->cr_round_paused ||
         attack_data_.isRanged() ) {
        return false;
    }

    // Can not Parry when using a Ranged Weapon.
    CNWSItem *on = nwn_GetItemInSlot(tar, EQUIPMENT_SLOT_RIGHTHAND);
    if ( on && GetIsRangedWeapon(on) ) {
        return false;
    }

    int32_t roll = true_random(1, 20); //+ target:GetSkillRank(nwn.SKILL_PARRY)
    --tar->cre_combat_round->cr_parry_actions;

    int32_t attack_roll = attack_data_.getAttackBonus();
    if ( roll >= attack_roll ) {
        if ( roll - 10 > attack_roll ) {
            CNWSCombatRound__AddParryAttack(tar->cre_combat_round,
                                            attacker_nwn->obj.obj_id);
        }
        attack_data_.setResult(2);
        return true;
    }
    CNWSCombatRound__AddParryIndex(tar->cre_combat_round);
    return false;
}

bool Attack::resolveMissOpportunities() {
    if ( resolveMissChance() ) { return true; }
    if ( !target_is_cre ) { return false; }
    if ( resolveDeflectArrow() ) { return true; }

    if ( combat.disable_parry ) {
        return false;
    }
    else {
        return resolveParry();
    }
}

void Attack::resolveOnhitEffect(){
    CNWSCreature__ResolveOnHitEffect(attacker_nwn, target_nwn, isOffhand(),
                                     attack_data_.isCriticalHit());
}

void Attack::resolveNoAmmo() {
    CNWSCombatRound *cr = attacker_nwn->cre_combat_round;
    CNWSCombatRound__SetRoundPaused(cr, 0, OBJECT_INVALID);
    CNWSCombatRound__SetPauseTimer(cr, 0, 0);
    nwn_SetAnimation(attacker_nwn, 1);
}

void Attack::resolvePreRoll() {
    if ( !attack_data_.isCoupDeGrace() ) {
        CNWSCreature__ResolveCachedSpecialAttacks(attacker_nwn);
    }

    // Determine if able to use special attack (if one has been used).
    if ( attack_data_.isSpecialAttack() ) {
        int32_t sa = attack_data_.getSpecialAttack();
        if ( sa < 1115 && nwn_GetFeatRemainingUses(attacker_nwn->cre_stats, sa) == 0 ) {
            attack_data_.clearSpecialAttack();
        }
        else {
            nwn_DecrementFeatRemainingUses(attacker_nwn->cre_stats, sa);
        }
    }
}

void Attack::resolvePostDamage(bool is_ranged) {
    combat.Log(3, "Attack::resolvePostDamage: Total %d, HP: %d\n",
               damage_total_, defense->getHPCurrent(true));

    bool is_killing = damage_total_ > defense->getHPCurrent(true);
    
    if ( !target_is_cre ) { 
        if ( !target_nwn->obj_is_invulnerable ) {
            attack_data_.setKillingBlow();
            return;
        }
        else { 
            return;
        }
    }

    CNWSCreature *tar = reinterpret_cast<CNWSCreature*>(target_nwn);

    // Coup de Grace
    if ( !tar->cre_is_immortal                      &&
         !target_nwn->obj_is_invulnerable           &&
         (situ_flags_ & SITUATION_FLAG_COUPDEGRACE) ) {

        CGameEffect *e = effect_death(false, false);
        e->eff_integers[0] = 0;
        e->eff_integers[1] = 1;
        attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
        attack_data_.setKillingBlow();
        is_killing = true;
    }

    // Weapon Ineffective Message

    if ( !combat.disable_dev_crit ) {
        // Devistating Critical
        // If the target is already dead, don't bother dev criting...
        if ( !is_killing                                              &&
             !tar->cre_is_immortal                                    &&
             !target_nwn->obj_is_invulnerable                         &&
             attack_data_.isCriticalHit()                             &&
             attacker_->offense.getHasDevistatingCritical(equip_num_) &&
             !is_crit_immune_ ) {

            if ( !CNWSCreature__SavingThrowRoll(
                     tar, 1,
                     attacker_nwn->cre_stats->cs_str_mod + 10 + (nwn_GetHitDice(attacker_nwn) / 2),
                     0,
                     attacker_nwn->obj.obj_id,
                     1, 0, 1) ) {
            
                CGameEffect *e = effect_death(true, true);
                nwn_SetDurationType(e, DURATION_TYPE_INSTANT);
                attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
                attack_data_.setResult(10);
            }
            else {
                CNWCCMessageData *msg = CNWCCMessageData_create();
                msg->type = 2;
                CExoArrayList_int32_add(&msg->integers, 257);
                CExoArrayList_uint32_add(&msg->objects, target_nwn->obj_id);
                attack_data_.addCCMessage(msg);
            }
        }
    }
    
    // No more ranged events...
    if ( is_ranged ) { return; }

    int32_t specatt = attack_data_.getSpecialAttack();

    // Crippling Strike
    if ( !is_killing                                 &&
         (situ_flags_ & SITUATION_FLAG_SNEAK_ATTACK) &&
         nwn_GetHasFeat(attacker_nwn->cre_stats, FEAT_CRIPPLING_STRIKE) ) {
        CGameEffect *e = effect_ability(ABILITY_STRENGTH, -2);
        nwn_SetDurationType(e, DURATION_TYPE_PERMANENT);
        attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
    }

    if ( !is_killing &&
         (situ_flags_ & SITUATION_FLAG_DEATH_ATTACK) ) {
        int32_t dc = nwn_GetLevelByClass(attacker_nwn->cre_stats, CLASS_TYPE_ASSASSIN);
        dc += 10 + attacker_nwn->cre_stats->cs_int_mod;
        if ( !CNWSCreature__SavingThrowRoll(tar, SAVING_THROW_FORT, dc, 20,
                                            attacker_nwn->obj.obj_id,
                                            1, 0, 1) ) {
            int32_t dmg = true_random(1, 6) + nwn_GetHitDice(attacker_nwn);
            CNWSCreature__ApplyOnHitDeathAttack(attacker_nwn, target_nwn, dmg);
        }
    }
    
    // Quivering Palm
    if ( !tar->cre_is_immortal &&
         !target_nwn->obj_is_invulnerable ) {

        uint32_t level = 0;
        if ( !is_killing                                     &&
             damage_total_ > 0                               &&
             specatt == SPECIAL_ATTACK_QUIVERING_PALM        &&
             !is_crit_immune_                                &&
             (level = nwn_GetHitDice(attacker_nwn)) > nwn_GetHitDice(tar) ) {

            if ( !CNWSCreature__SavingThrowRoll(tar, SAVING_THROW_FORT,
                                                attacker_nwn->cre_stats->cs_wis_mod + 10 + (level / 2),
                                                0,
                                                attacker_nwn->obj.obj_id,
                                                1, 0, 1) ) {
                CGameEffect *e = effect_death(true, true);
                nwn_SetDurationType(e, DURATION_TYPE_INSTANT);
                attack_data_.addEffect(e, attacker_nwn->obj.obj_id);
            }
        }
    }

    int8_t totatt = CNWSCombatRound__GetTotalAttacks(attacker_nwn->cre_combat_round);

    if ( !combat.disable_circle_kick ) {
        // Circle Kick
        if ( !is_killing                                         &&
             !specatt == 0                                       &&
             totatt < 50                                         &&
             attack_data_.getAttackType() == ATTACK_TYPE_UNARMED &&
             nwn_GetHasFeat(attacker_nwn->cre_stats, FEAT_CIRCLE_KICK) ) {
        
            float maxrange = CNWSCreature__MaxAttackRange(attacker_nwn,
                                                          OBJECT_INVALID,
                                                          0, 1);
        
            uint32_t t = CNWSCreature__GetNearestEnemy(attacker_nwn,
                                                       maxrange,
                                                       target_nwn->obj_id,
                                                       1, 0);

            combat.Log(3, "Circle Kick: Max distance %.2f, New target: %x\n",
                       maxrange, t);

            if ( nwn_GetCreatureByID(t) ) {
                attacker_nwn->cre_combat_round->cr_new_target = t;
                CNWSCombatRound__AddCircleKickAttack(attacker_nwn->cre_combat_round, t);
                attacker_nwn->cre_passive_attack_beh = 1;
                --attacker_nwn->cre_combat_round->cr_num_circle_kicks;
            }
        }
    }
    
    // Cleave
    if ( is_killing                         &&
         specatt != FEAT_WHIRLWIND_ATTACK   &&
         specatt != FEAT_IMPROVED_WHIRLWIND &&
         totatt < 50 ) {

        if ( nwn_GetHasFeat(attacker_nwn->cre_stats, FEAT_GREAT_CLEAVE) ) {
            float maxrange = CNWSCreature__MaxAttackRange(attacker_nwn,
                                                          OBJECT_INVALID,
                                                          0, 1);
            uint32_t t = CNWSCreature__GetNearestEnemy(attacker_nwn,
                                                       maxrange,
                                                       target_nwn->obj_id,
                                                       1, 0);

            combat.Log(3, "Great Cleave: Max distance %.2f, New target: %x\n",
                       maxrange, t);

            if ( nwn_GetCreatureByID(t) ) {
                attacker_nwn->cre_combat_round->cr_new_target = t;
                CNWSCombatRound__AddCleaveAttack(attacker_nwn->cre_combat_round,
                                                 t, 0);
                attacker_nwn->cre_passive_attack_beh = 1;
            }

        }
        else if ( nwn_GetHasFeat(attacker_nwn->cre_stats, FEAT_CLEAVE) &&
                  attacker_nwn->cre_combat_round->cr_num_cleaves > 0 ) {
            float maxrange = CNWSCreature__MaxAttackRange(attacker_nwn,
                                                          OBJECT_INVALID,
                                                          0, 1);
            uint32_t t = CNWSCreature__GetNearestEnemy(attacker_nwn,
                                                       maxrange,
                                                       target_nwn->obj_id,
                                                       1, 0);

            combat.Log(3, "Cleave: Max distance %.2f, New target: %x\n",
                       maxrange, t);

            if ( nwn_GetCreatureByID(t) ) {
                attacker_nwn->cre_combat_round->cr_new_target = t;
                CNWSCombatRound__AddCleaveAttack(attacker_nwn->cre_combat_round,
                                                 t, 0);
                attacker_nwn->cre_passive_attack_beh = 1;
                --attacker_nwn->cre_combat_round->cr_num_cleaves;

            }
        }
    }
}

void Attack::resolveSituations() {
    if ( !target_is_cre ) { return; }

    CNWSCreature *tar = reinterpret_cast<CNWSCreature *>(target_nwn);
    uint32_t flags = 0;
    float x = attacker_nwn->obj.obj_position.x - target_nwn->obj_position.x;
    float y = attacker_nwn->obj.obj_position.y - target_nwn->obj_position.y;
    float z = attacker_nwn->obj.obj_position.z - target_nwn->obj_position.z;

    // Save some time by not sqrt'ing to get magnitude
    target_distance_ = x * x + y * y + z * z;

    if ( target_distance_ <= 100 ) {
        // Coup De Grace
        if ( (target_state_ & COMBAT_TARGET_STATE_ASLEEP) &&
             (nwn_GetHitDice(tar) <= 10) ) {
            flags |= SITUATION_FLAG_COUPDEGRACE;
            attack_data_.setCoupDeGrace(true);
        }

        // In order for a sneak attack situation to be possiblle the attacker must
        // be able to do some amount of sneak damage.
        CombatMod *mod = attacker_->getSituationalModifier(SITUATION_INDEX_DEATH_ATTACK);
        bool death = ( mod && mod->dmg.roll.isValid() );
      
        mod = attacker_->getSituationalModifier(SITUATION_INDEX_SNEAK_ATTACK);
        bool sneak = ( mod && mod->dmg.roll.isValid() );

        // Sneak Attack & Death Attack
        if ( (sneak || death) && 
             ( (target_state_ & COMBAT_TARGET_STATE_ATTACKER_UNSEEN) ||
               (target_state_ & COMBAT_TARGET_STATE_FLATFOOTED)      ||
               (target_state_ & COMBAT_TARGET_STATE_FLANKED) ) ) {
            if ( !defense->isImmune(IMMUNITY_TYPE_SNEAK_ATTACK) ) {
                // Death Attack.  If it's a Death Attack it's also a sneak attack.
                if ( death ) {
                    flags |= SITUATION_FLAG_DEATH_ATTACK;
                    flags |= SITUATION_FLAG_SNEAK_ATTACK;
                    attack_data_.setSneaks(true, true);
                }

                // Sneak Attack
                if ( sneak ) { 
                    flags |= SITUATION_FLAG_SNEAK_ATTACK;
                    attack_data_.setSneaks(true, false);
                }
            }
            else { 
                // Immune to sneaks message.
                CNWCCMessageData *msg = CNWCCMessageData_create();
                CExoArrayList_int32_add(&msg->integers, 134);
                CExoArrayList_uint32_add(&msg->objects, target_nwn->obj_id);
                attack_data_.addCCMessage(msg);
            }
        }
    }
    situ_flags_ = flags;
}

void Attack::resolveSpecialAttack() {
    // Special attacks only apply when the target is a creature
    // and damage is greater than zero.
    if ( target_is_cre && damage_total_ > 0 ) {
        uint32_t sa = attack_data_.getSpecialAttack();

        if ( !resolveSpecialAttackImpact() ) {
            // If the special attack failed because it wasn't
            // applicable or the targets skill check (for example)
            // was success full set the attack result to 5.
            attack_data_.setResult(5);
        }
    }
    else {
        // If the target is not a creature or no damage was dealt set attack result to 6.
        attack_data_.setResult(6);
    }
}

void Attack::resolveTargetState() {
    target_state_ = GetTargetState(attacker_nwn, target_nwn);
}

void Attack::update() {
    CNWSCombatRound *cr = attacker_nwn->cre_combat_round;
    int cur_att = ++cr->cr_current_attack;
    CNWSCombatAttackData *data = CNWSCombatRound__GetAttack(cr, cur_att);

    data->cad_attack_group = cr->cr_attack_group;
    data->cad_target = target_nwn->obj_id;
    data->cad_attack_mode = attacker_nwn->cre_mode_combat;
    data->cad_attack_type = CNWSCombatRound__GetWeaponAttackType(cr);

    equip_num_ = GetEquipNumFromAttackType(data->cad_attack_type);
    is_crit_immune_ = defense->isImmune(IMMUNITY_TYPE_CRITICAL_HIT);
    
    combat.Log(3, 
               "Attack::update(): Current Attack: %d, Attack Group: %d, "
               "Target: %x, Mode: %d, Attack Type: %d, Equip num\n",
               cur_att, data->cad_attack_group, data->cad_target,
               data->cad_attack_mode, data->cad_attack_type, equip_num_);

    attack_data_.setAttackData(data, attacker_nwn);
}
