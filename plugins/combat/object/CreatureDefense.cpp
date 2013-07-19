#include "NWNXCombat.h"
#include "CreatureDefense.h"
#include <utility>
#include <sstream>
#include <string>
#include <iomanip>

extern CNWNXCombat combat;

CreatureDefense::CreatureDefense(Creature *p, CNWSCreature *o)
    : parent_(nullptr),
      original_(o),
      concealment(0),
      hp_eff_(0),
      hp_max_(0),
      soak(0) {
    
    std::fill_n(immunity, DAMAGE_TYPE_NUM, 0);
}

std::string CreatureDefense::getString(uint32_t flags) {
    std::ostringstream out;

    if ( NWNX_COMBAT_INFO_ARMOR_CLASS & flags ) {
        out << "\nArmor Class:\n"
            << "    Melee: " << getArmorClass(true, 0, false) << '\n'
            << "    Touch: " << getArmorClass(true, 0, true) << '\n';
    }

    if ( NWNX_COMBAT_INFO_HITPOINTS & flags ) {
        out << "\nHitpoints:\n"
            << "    Current: "   << getHPCurrent(true) << '\n'
            << "    Temporary: " << getHPCurrent(true) - getHPCurrent(false) << '\n'
            << "    Effects: "   << hp_eff_ << '\n'
            << "    Maximum: "   << getHPMax() << '\n';
    }

    if ( NWNX_COMBAT_INFO_CONCEALMENT & flags ) {
        out << "\nConcealment:\n" 
            << "    Versus Melee: " 
            << getConcealment(false) << '\n'
            << "    Versus Ranged: "
            << getConcealment(true) << '\n';
    }   
            
    if ( NWNX_COMBAT_INFO_REDUCTION & flags ) {
        out << "\nSoak:\n"
            << "    Base: " << getHardness() << '\n';
        out << "    Soak Items/Effects:\n";
        for ( int i = 0; i < DAMAGE_POWER_NUM; ++i ) {
            if ( !soak_eff[i] ) { continue; }

            CGameEffect *e = soak_eff[i];
            int32_t amt = e->eff_integers[0];
            int32_t lmt = e->eff_integers[2];
            out << "        " << amt << "/+" << i;
            if ( lmt > 0 ) {
                out << " Limit: " << lmt;
            }
            out << '\n';
        }    
    }

    if ( NWNX_COMBAT_INFO_RESISTANCE & flags ) {
        out << "\nResistance:\n";

        for ( int i = 0; i < DAMAGE_TYPE_NUM; ++i ) {
            out << "    " << DamageIndexToString(i) << ":\n";
            out << "        Base: " << resist[i] << "/-\n";
            out << "        Items/Effects: ";
            if ( !resist_eff[i] ) {
                out << " none" << '\n';
            }
            else { 
                CGameEffect *e = resist_eff[i];
                int32_t eff_resist = e->eff_integers[1];
                int32_t eff_limit  = e->eff_integers[2];
                out << eff_resist << "/-";
                if ( eff_limit > 0 ) {
                    out << " Limit: " << eff_limit;
                }
                out << '\n';
            }
        }
    }
        
    if ( NWNX_COMBAT_INFO_IMMUNITY & flags ) {
        out << "\nImmunity:\n";
        for ( int i = 0; i < DAMAGE_TYPE_NUM; ++i ) {
            out << "  " << DamageIndexToString(i) << ":\n";
            out << "    Base: " << (int32_t)immunity_base[i] << "%\n"
                << "    Effects: " << (int32_t)immunity[i] << "%\n"
                << "    Total: " 
                << (int32_t)CLAMP<int32_t>(immunity_base[i] + immunity[i],
                                           -100, 100)
                << "%\n";
        }
    }

    if ( NWNX_COMBAT_INFO_IMMUNITY_MISC & flags ) {
        out << "\nMiscelaneous Immunity:\n";
        for ( int i = 1; i < IMMUNITY_TYPE_NUM; ++i ) {
            out << nwn_GetMiscImmunityName(i)
                << " : " 
                << getMiscImmunity(i)
                << "%\n";
        }
    }


    return out.str();
}

std::string CreatureDefense::toString() {
    std::ostringstream out;
    out << "Defense:\n";
    out << getString(0xFFFFFFF);
    return out.str();
}

// Defense
void CreatureDefense::doDamageMods(DamageResult &res) {
    uint32_t flag = 0;
    for ( size_t i = 0; i < 13; ++i ) {

        if ( i == 12 ) {
            flag = res.wpn->base_dmg_flags;
        }
        else {
            flag = 1 << i;
        }
        res.immunity_adjust[i] = doDamageImmunity(NULL,
                                                  res.damages[i],
                                                  flag,
                                                  true);
    }

    for ( size_t i = 0; i < 13; ++i ) {
        if ( i == 12 ) {
            flag = res.wpn->base_dmg_flags;
        }
        else {
            flag = 1 << i;
        }
        res.resist_adjust[i] = doDamageResistance(NULL,
                                                  res.damages[i],
                                                  flag,
                                                  true);

    }

    res.soak_adjust = doDamageReduction(NULL, 
                                        res.damages[12],
                                        res.wpn->power,
                                        true);
}

SaveResult CreatureDefense::doSavingThrow(uint32_t save_type, uint32_t dc,
                                          uint32_t save_vs_type, const VersusInfo& vs,
                                          bool feedback) {
    return std::make_pair(0, nullptr);
}

// NOTE: Immunity takes the MINIMUM immunity versus multiple flags.
int8_t CreatureDefense::getDamageImmunityByFlags(uint16_t flags) {
    bool found = false;
    int16_t result = 0;
    
    for ( int i = 0; i < DAMAGE_TYPE_NUM; ++i ) {
        if ( (flags >> i) & 1 ) {
            if ( !found ) {
                result = immunity[i] + immunity_base[i];
                found = true;
            }
            else {
                int16_t tot = immunity[i] + immunity_base[i];
                result = std::min(result, tot);
            }
        }
    }

    int8_t res = CLAMP<int16_t>(result, -100, 100);
    combat.Log(3, "getDamageImmunityByFlags; Flags: %x, Result: %d\n",
               flags, res);

    return res;
}

// NOTE: no_feedback should be true when called from an attack.
int32_t CreatureDefense::doDamageImmunity(CNWSCreature *vs, int32_t amount,
                                          uint16_t dmg_flags, bool no_feedback) {
    if ( amount <= 0 ) { return amount; }

    int8_t imm = getDamageImmunityByFlags(dmg_flags);
    if ( imm == 0 ) { return 0; }

    int32_t adj = (amount * imm) / 100;

    if ( !combat.disable_damage_mod_msg ) {
        if ( !no_feedback ) {
            CNWCCMessageData *msg = CNWCCMessageData_create();
            CExoArrayList_int32_add(&msg->integers, 62);
            CExoArrayList_int32_add(&msg->integers, adj);
            CExoArrayList_int32_add(&msg->integers, dmg_flags);
            CExoArrayList_uint32_add(&msg->objects, original_->obj.obj_id);
            
            CNWSCreature__SendFeedbackMessage(original_, 0x40, msg, 0);
            if ( vs ) {
                CNWCCMessageData* copy = CNWCCMessageData_copy(msg);
                CNWSCreature__SendFeedbackMessage(vs, 0x43, copy, 0);
            }
        }
    }
    return adj;
}

int32_t CreatureDefense::doDamageReduction(CNWSCreature *vs, int32_t dmg,
                                           uint8_t dmg_power, bool no_feedback) {
    if ( dmg <= 0 ) { return dmg; }

    CGameEffect *eff = NULL;
    // Not hardness is the holder for DD && Barb soak, etc.
    int32_t amount = getHardness();
    int32_t limit  = 0;

    // This might not be precisely how the engine does it...  This code
    // will use the highest applying soak amount, regardless of if there
    // is an damage limit.  So there would be some cases where a better
    // soak might apply that has a lesser amount but a higher limit or no
    // limit.
    for ( int i = dmg_power; i < DAMAGE_POWER_NUM; ++i ) {
        if ( !soak_eff[i] ) { continue; }

        CGameEffect *e = soak_eff[i];
        int32_t amt = e->eff_integers[0];
        int32_t lmt = e->eff_integers[2];

        if ( amt > amount ) {
            eff    = e;
            amount = amt;
            limit  = lmt;
        }
    }

    // The amount we adjust is going to be the minimum of the soak amount
    // and the damage amount.
    int32_t adj = std::min(dmg, amount);

    combat.Log(3, "doDamageReduction: Amount: %d, Limit: %d, Effect: %d, adj: %d"
               " power: %d\n",
               amount, limit, !!(eff), adj, dmg_power);
    
    // If eff is not NULL, that means there is an effect that is better
    // than any base DD/Barb soak...
    if ( eff ) {
        // If there is a damage limit on this effect than the amount adjusted
        // is the minimum of the two.  If the final adjustment is equal to the
        // limit then the effect must be removed.
        if ( limit > 0 ) {
            adj = std::min(adj, limit);
            if ( (eff->eff_integers[2] -= adj) <= 0 ) {
                nwn_RemoveEffectById(&original_->obj, eff->eff_id);
            }
        }
    }

    if ( !combat.disable_damage_mod_msg ) {
        if ( !no_feedback ) {
            CNWCCMessageData *msg = CNWCCMessageData_create();
            CExoArrayList_int32_add(&msg->integers, 67);
            CExoArrayList_int32_add(&msg->integers, adj);
            CExoArrayList_uint32_add(&msg->objects, original_->obj.obj_id);
        
            CNWSCreature__SendFeedbackMessage(original_, 0x3E, msg, 0);
            if ( vs ) {
                CNWCCMessageData* copy = CNWCCMessageData_copy(msg);
                CNWSCreature__SendFeedbackMessage(vs, 0x3E, copy, 0);
            }
        }
    }
    
    return adj;
}
                                           
// NOTE: Resistenace takes the MAXIMUM resistance versus multiple flags.
int32_t CreatureDefense::doDamageResistance(CNWSCreature *vs, int32_t amount,
                                            uint16_t dmg_flags, bool no_feedback) {
    if ( amount <= 0 ) { return amount; }

    int16_t base_resist = 0;
    int32_t eff_resist  = 0;
    int32_t eff_limit   = 0;
    
    CGameEffect *eff = NULL;
    
    for ( int i = 0; i < DAMAGE_TYPE_NUM; ++i ) {
        if ( (dmg_flags >> i) & 1 ) {
            base_resist = std::max(base_resist, resist[i]);

            if ( !resist_eff[i] ) { continue; }
            CGameEffect *e = resist_eff[i];
            if ( eff_resist < e->eff_integers[1] ) {
                eff_resist = e->eff_integers[1];
                eff_limit  = e->eff_integers[2];
                eff = e;
            }
        }
    }
    
    int32_t base_adj = std::min((int32_t)base_resist, amount);
    int32_t eff_adj = 0;
    if ( eff ) {
        eff_resist = std::min(eff_resist, amount - base_adj);
        if ( eff_limit > 0 ) {
            eff_resist = std::min(eff_resist, eff_limit);
            if ( (eff->eff_integers[2] -= eff_resist) <= 0 ) {
                nwn_RemoveEffectById(&original_->obj, eff->eff_id);
            }
        }
        eff_adj = eff_resist;
    }

    if ( !combat.disable_damage_mod_msg ) {
        if ( !no_feedback ) {
            CNWCCMessageData *msg = CNWCCMessageData_create();
            CExoArrayList_int32_add(&msg->integers, 63);
            CExoArrayList_int32_add(&msg->integers, base_adj + eff_adj);
            CExoArrayList_uint32_add(&msg->objects, original_->obj.obj_id);
        
            CNWSCreature__SendFeedbackMessage(original_, 0x3F, msg, 0);
            if ( vs ) {
                CNWCCMessageData* copy = CNWCCMessageData_copy(msg);
                CNWSCreature__SendFeedbackMessage(vs, 0x3F, copy, 0);
            }
        }
    }

    return base_adj + eff_adj;

}

bool CreatureDefense::canUseDexAC(uint32_t state, bool is_ranged) {
    // Dexterity modifier, Tumble, and a few other things can only be used
    // given the following conditions:
    // 1. The attacker is not invisible AND
    // 2. The target is not flatfooted AND
    // 3. The attacker is not unseen or the target has blind fight
    //    and the attack is ranged.
    // 4. Having Defensive Awareness 1 or Uncanny Dodge 1 trumps all the
    //    above.
    //
    // This is what the code is doing, this may not be exactly what NWN does,
    // frankly it's a little convoluted.
    
    bool dexed;
    CNWSCreatureStats *stats = original_->cre_stats;

    dexed = ( !(state & COMBAT_TARGET_STATE_ATTACKER_INVIS) &&
              !(state & COMBAT_TARGET_STATE_FLATFOOTED)     &&
              ( !(state & COMBAT_TARGET_STATE_ATTACKER_UNSEEN) ||
                ( nwn_GetHasFeat(stats, FEAT_BLIND_FIGHT) &&
                  is_ranged ) ) );

    dexed = ( dexed                                                      ||
              nwn_GetHasFeat(stats, FEAT_PRESTIGE_DEFENSIVE_AWARENESS_1) ||
              nwn_GetHasFeat(stats, FEAT_UNCANNY_DODGE_1) );

    return dexed;
}

uint32_t CreatureDefense::getArmorClass(bool dexed, uint32_t situ_flags, bool is_touch) {
    CNWSCreatureStats *stats = original_->cre_stats;
    uint32_t ac = 10;
    combat.Log(3, "Base AC: %d\n", ac);

    combat.Log(3, "Mode AC: %d\n", parent_->mode_.ac);
    ac += parent_->mode_.ac;

    // This may all need to be clamped...
    ArmorClass res = {
        stats->cs_ac_armour_bonus - stats->cs_ac_armour_penalty,
        stats->cs_ac_deflection_bonus - stats->cs_ac_deflection_penalty,
        stats->cs_ac_dodge_bonus - stats->cs_ac_dodge_penalty,
        stats->cs_ac_natural_bonus - stats->cs_ac_natural_penalty,
        stats->cs_ac_shield_bonus - stats->cs_ac_shield_penalty,
    };

    combat.Log(3, "Nonversus: Armor %d, Deflection: %d, Dodge:%d, Natural: %d, Shield: %d\n",
               res.armor,
               res.deflection,
               res.dodge,
               res.natural,
               res.shield);
    
    // Armor AC
    combat.Log(3, "Base: Armor %d, Deflection: %d, Dodge:%d, Natural: %d, Shield: %d\n",
               ac_base.armor,
               ac_base.deflection,
               ac_base.dodge,
               ac_base.natural,
               ac_base.shield);

    ac += ac_base.armor;
    ac += ac_base.natural;
    ac += ac_base.shield;

    // Situations
    for ( int i = 0; i < COMBAT_SITUATION_NUM; ++i ) {
        if ( situ_flags & 1 << i ) {
            combat.Log(3, "Situation Mod (%d): %d\n", i, parent_->mod_situ[i].ac);
            ac += parent_->mod_situ[i].ac;
        }
    }

    int32_t dex_mod = nwn_GetDexMod(stats, true);
    if ( dexed ) {
        ac += ac_base.dodge;
        ac += dex_mod;
    }
    // If you have a negative dex mod, you get it regardless.
    else if ( dex_mod < 0 ) {
        combat.Log(3, "Dex Penalty: Dex Mod: %d\n", dex_mod);
        ac += dex_mod;
    }
    
    ac += res.dodge;
    if ( is_touch ) {
        combat.Log(3, "Touch AC: %d\n", ac);
        return ac;
    } 

    ac += res.armor;
    ac += res.deflection + ac_base.deflection;
    ac += res.natural;
    ac += res.shield;

    combat.Log(3, "AC: %d\n", ac);
    return ac;
}

int32_t CreatureDefense::getConcealment(bool is_ranged) {
    int32_t total = 0;
    CGameEffect *e;

    uint8_t vis = original_->cre_vision_type;

    // NOTE: VERSUS stuff.
    
    for ( int i = original_->cre_stats->cs_first_invis_eff;
          i < original_->obj.obj_effects_len;
          ++i ) {
        e = original_->obj.obj_effects[i];
        if ( e->eff_type != EFFECT_TRUETYPE_INVISIBILITY ) {
            break;
        }
        // Not sure exactly when this occurs... maybe darkness.
        if ( ( e->eff_integers[0] == 2 && !(vis & 2) && !(vis & 4) ) ||
             ( e->eff_integers[0] != 2 && !(vis & 1) && !(vis & 4) ) ) {
            total = 50;
            // No point in continuing, 50 is the highest it will ever be.
            break;
        }
    }

    int32_t vs_type = is_ranged
        ? MISS_CHANCE_TRUETYPE_RANGED
        : MISS_CHANCE_TRUETYPE_MELEE;
    
    for ( int i = original_->cre_stats->cs_first_conceal_eff;
          i < original_->obj.obj_effects_len;
          ++i ) {
        e = original_->obj.obj_effects[i];
        if ( e->eff_type != EFFECT_TRUETYPE_CONCEALMENT ) {
            break;
        }

        if ( e->eff_integers[4] == MISS_CHANCE_TRUETYPE_NORMAL ||
             e->eff_integers[4] == vs_type ) {
            total = std::max(total, e->eff_integers[0]);
        }
    }
        
    return std::max((int32_t)concealment, total);
}

int32_t CreatureDefense::getHPCurrent(bool use_temp) {
    return use_temp
        ? original_->obj.obj_hp_cur + original_->obj.obj_hp_temp
        : original_->obj.obj_hp_cur;
}

int32_t CreatureDefense::getHPMax() {
    if ( hp_update_ ) updateHitPoints();
    return hp_max_ + hp_eff_ + parent_->mode_.hp;
}

// NOTE: this function can run before a character is fully
// loaded, so it's necessary to make a few concessions to that
// fact.
void CreatureDefense::updateHitPoints() {
    int level = nwn_GetHitDice(original_);
    int base = 0;
    bool not_pc = ( !original_->cre_stats->cs_is_pc ||
                    CNWSCreature__GetIsPossessedFamiliar(original_) );

    int con = std::max(0, original_->cre_stats->cs_con_mod * level);

    int toughness = 0;
    if( nwn_GetHasFeat(original_->cre_stats, FEAT_TOUGHNESS) ) {
        toughness += level;
    }

    int epictough = 20 * nwn_GetHasNthFeat(original_, 
                                           FEAT_EPIC_TOUGHNESS_1,
                                           FEAT_EPIC_TOUGHNESS_10);

    // Pale Master - If they have the level assume they have the feat.
    int pm = 0;
    int pmlevel = nwn_GetLevelByClass(original_->cre_stats, 
                                      CLASS_TYPE_PALEMASTER);
    if( pmlevel >= 5 ) {
        
        if( pmlevel >= 25 ) {
            pm = 18 + (pmlevel / 5) * 20;
        }
        else if( pmlevel >= 15 ) {
            pm = 18 + (pmlevel / 5) * 10;
        }
        else if( pmlevel > 10 ) {
            pm = 18 + (pmlevel - 10) / 5;
        }
        else if( (pmlevel -= 5) > 0 ) {
            pm = pmlevel * 3;
        }
    }
    
    // Base Hitpoints.  NOTE: Non PC characters do not have level stats.
    if( not_pc ) {
        base = original_->obj.obj_hp_max;
        hp_max_ = std::max((int)original_->obj.obj_hp_max, base + con + toughness + epictough + pm);
    }
    else {
        CNWSStats_Level *lvl = nullptr;
        for( int i = 1; i <= level; i++ ){
            if( (lvl = nwn_GetLevelStats(original_->cre_stats, i)) ) { 
                base += lvl->ls_hp;
            }
        }
        // Character not fully loaded.
        if ( base <= 0 ) { return; }
        
        hp_max_ = std::max((int)original_->obj.obj_hp_max, base + con + toughness + epictough + pm);
        original_->obj.obj_hp_max = hp_max_;
    }

    

    if ( hp_max_ <= 0 ) { hp_max_ = 1; }

    if ( !not_pc ) {

    }
    hp_update_ = false;
}


uint32_t CreatureDefense::getHardness() {
    return soak;
}

void CreatureDefense::updateSaves() {
    CNWSCreatureStats *stats = original_->cre_stats;

    // Fortitude
    saves_.fort.base = nwn_GetBaseSavingThrow(original_, SAVING_THROW_FORT);
    saves_.fort.base += stats->cs_con_mod;
    saves_.fort.base += stats->cs_save_fort; // TODO: Check this.
    if ( nwn_GetHasFeat(stats, FEAT_GREAT_FORTITUDE) ) {
        saves_.fort.base += 2;
    }
        
    if ( nwn_GetHasFeat(stats, FEAT_STRONG_SOUL) ) {
        ++saves_.fort.base;
    }

    if ( nwn_GetHasFeat(stats, FEAT_EPIC_FORTITUDE) ) {
        saves_.fort.base += 4;
    }
        
    // Reflex
    saves_.reflex.base = nwn_GetBaseSavingThrow(original_, SAVING_THROW_REFLEX);
    saves_.reflex.base += stats->cs_save_reflex;
    saves_.reflex.base += nwn_GetDexMod(stats, 0);

    if ( nwn_GetHasFeat(stats, FEAT_LIGHTNING_REFLEXES) ) {
        saves_.reflex.base += 2;
    }
        
    if ( nwn_GetHasFeat(stats, FEAT_SNAKE_BLOOD) ) {
        ++saves_.reflex.base;
    }

    if ( nwn_GetHasFeat(stats, FEAT_EPIC_REFLEXES) ) {
        saves_.reflex.base += 4;
    }

    // Will
    saves_.will.base = nwn_GetBaseSavingThrow(original_, SAVING_THROW_WILL);
    saves_.will.base += stats->cs_save_will;
    saves_.will.base += stats->cs_wis_mod;

    if ( nwn_GetHasFeat(stats, FEAT_IRON_WILL) ) {
        saves_.will.base += 2;
    }
    
    if ( nwn_GetHasFeat(stats, FEAT_BULLHEADED) ) {
        ++saves_.will.base;
    }
    
    if ( nwn_GetHasFeat(stats, FEAT_STRONG_SOUL) ) {
        ++saves_.will.base;
    }
    
    if ( nwn_GetHasFeat(stats, FEAT_EPIC_WILL) ) {
        saves_.will.base += 4;
    }

    // Universal
    if ( nwn_GetHasFeat(stats, FEAT_LUCKY) ) {
        ++saves_.will.base;
        ++saves_.reflex.base;
        ++saves_.fort.base;
    }
    
    if ( nwn_GetHasFeat(stats, FEAT_LUCK_OF_HEROES) ) {
        ++saves_.will.base;
        ++saves_.reflex.base;
        ++saves_.fort.base;
    }

    if ( nwn_GetHasFeat(stats, FEAT_PRESTIGE_DARK_BLESSING) ||
         nwn_GetHasFeat(stats, FEAT_DIVINE_GRACE) ) {
        
        saves_.will.base += stats->cs_cha_mod;
        saves_.reflex.base += stats->cs_cha_mod;
        saves_.fort.base += stats->cs_cha_mod;
    }
}

int32_t CreatureDefense::getSave(uint32_t save, uint32_t save_vs, bool base) {
    int32_t result = 0;

    switch(save) {
    case SAVING_THROW_REFLEX: result = saves_.reflex.base; break;
    case SAVING_THROW_FORT:   result = saves_.fort.base;   break;
    case SAVING_THROW_WILL:   result = saves_.will.base;   break;
    }

    if ( !base ) {
        result += getSaveEffectBonus(save, save_vs);
    }
    return save;
}

int32_t CreatureDefense::getSaveEffectBonus(uint32_t save, uint32_t save_vs) {
    int32_t save_bonus = 0;

    switch (save) {
    case SAVING_THROW_REFLEX:
        save_bonus = saves_.reflex.effects[0];
        if ( save_vs != 0 ) {
            save_bonus += saves_.reflex.effects[save_vs];
        }
        break;
    case SAVING_THROW_FORT:
        save_bonus = saves_.fort.effects[0];
        if ( save_vs != 0 ) {
            save_bonus += saves_.fort.effects[save_vs];
        }
        break;

    case SAVING_THROW_WILL:
        save_bonus = saves_.will.effects[0];
        if ( save_vs != 0 ) {
            save_bonus += saves_.will.effects[save_vs];
        }
        break;
    }

    return CLAMP<int32_t>(save_bonus, -20, 20);
}

void CreatureDefense::modifyDamageImmunity(uint32_t dmg_type, int32_t amount) {
    combat.Log(3, "modifyDamageImmunity: Damage: %d; Amount: %d\n",
               dmg_type, amount);

    immunity[dmg_type] += amount;
}

void CreatureDefense::modifyMiscImmunity(uint32_t type, int32_t amount) {
    if ( amount == 0 ) { amount = 100; }
    immunity_misc[type] += amount;
}

int32_t CreatureDefense::getMiscImmunity(uint32_t type) {
    int32_t result = 0;
    CNWSCreatureStats *stats = original_->cre_stats;

    if ( type == IMMUNITY_TYPE_CRITICAL_HIT ) {
        if ( stats->cs_race == RACIAL_TYPE_UNDEAD                        ||
             stats->cs_race == RACIAL_TYPE_CONSTRUCT                     ||
             nwn_GetLevelByClass(stats, CLASS_TYPE_DRAGONDISCIPLE) >= 40 ||
             nwn_GetHasFeat(stats, FEAT_DEATHLESS_MASTERY) ) { 
            result = 100;
        }
        else if ( stats->cs_is_pc ) {
            int str = stats->cs_str * 2;
            int dex = stats->cs_dex; 
            if ( dex < 30 ) { dex = 0; }

            result = str + dex;
        }
        result;
    }
    else if ( type == IMMUNITY_TYPE_SNEAK_ATTACK ) { 
        int pm  = nwn_GetLevelByClass(stats, CLASS_TYPE_PALEMASTER);
        int rdd = nwn_GetLevelByClass(stats, CLASS_TYPE_DRAGONDISCIPLE);

        if ( stats->cs_race == RACIAL_TYPE_UNDEAD    ||
             stats->cs_race == RACIAL_TYPE_CONSTRUCT ||
             rdd >= 30                               ||
             pm  >= 30 ) {
            result = 100;
        }
    }
    return CLAMP<int32_t>(result + immunity_misc[type], 0, 100);
}

bool CreatureDefense::isImmune(uint32_t type) {
    int32_t perc = getMiscImmunity(type);
    if ( perc == 0 ) { return false; }

    int32_t rand = true_random(1,100);

    combat.Log(3, "isImmune: Percent: %d; Roll: %d\n", perc, rand);

    return rand <= perc;
}
    
void CreatureDefense::modifySave(uint32_t save, uint32_t save_vs, int32_t amount) {
    combat.Log(3, "modifySave: Save: %d; Save VS: %d; Amount: %d\n",
               save, save_vs, amount);

    if ( save == SAVING_THROW_REFLEX || save == SAVING_THROW_ALL ) {
        saves_.reflex.effects[save_vs] += amount;
    }
    
    if ( save == SAVING_THROW_FORT || save == SAVING_THROW_ALL ) {
        saves_.fort.effects[save_vs] += amount;
    }

    if ( save == SAVING_THROW_WILL || save == SAVING_THROW_ALL ) {
        saves_.will.effects[save_vs] += amount;
    }
}

void CreatureDefense::update() {
    updateArmorClass();
    updateHitPoints();
    updateSaves();
    updateDamageResist();
    updateDamageResistEffects(nullptr, false);
    updateDamageReductionEffects(nullptr, false);
    updateDamageImmunity();

    soak        = GetBaseSoak(original_);
    concealment = GetBaseConcealment(original_);
}

void CreatureDefense::updateArmorClass() {
    ac_base = GetBaseArmorClass(original_);
}

void CreatureDefense::updateDamageImmunity() {
    std::fill_n(immunity_base, DAMAGE_POWER_NUM, 0);

    CNWSCreatureStats *stats = original_->cre_stats;

    if ( nwn_GetHasFeat(stats, FEAT_DRAGON_IMMUNE_FIRE) ) {
        immunity_base[DAMAGE_INDEX_FIRE] = 100;
    }
    
    int dd = nwn_GetLevelByClass(stats, CLASS_TYPE_DWARVENDEFENDER);
    if ( stats->cs_is_pc && dd >= 30 ) {
        int mod = 2 + ((dd - 30) / 5) * 2;
        immunity_base[DAMAGE_INDEX_BLUDGEONING] = mod;
        immunity_base[DAMAGE_INDEX_SLASHING]    = mod;
        immunity_base[DAMAGE_INDEX_PIERCING]    = mod;
    }
}

void CreatureDefense::updateDamageReductionEffects(CGameEffect *effect, bool remove) {
    std::fill_n(soak_eff, DAMAGE_POWER_NUM, nullptr);
    
    int32_t eff_type, amount, power, limit;
    CGameEffect *eff;

    if ( !remove && effect ) {
        power  = effect->eff_integers[1];
        if ( power != 6 ) {
            if ( power > 6 ) { --power; }
            soak_eff[power] = effect;
        }
    }

    for ( int i = original_->cre_stats->cs_first_dmgred_eff;
          i < original_->obj.obj_effects_len; ++i ) {
        if ( !(eff = original_->obj.obj_effects[i]) ||
             eff->eff_type != EFFECT_TRUETYPE_DAMAGE_REDUCTION ) {
            break;
        }

        if ( remove && eff == effect ) { continue; }
        
        amount = eff->eff_integers[0];
        power  = eff->eff_integers[1];
        limit  = eff->eff_integers[2];

        if ( power == 6 ) { 
            continue;
        }
        else if ( power > 6 ) {
            --power;
        }

        combat.Log(3, "Damage Reduction Effect: Index %d, Amount: %d, Power: %d, Limit: %d\n",
                   i, amount, power, limit);
        
        if ( !soak_eff[power] ) {
            soak_eff[power] = eff;
            continue;
        }
        
        int32_t camount = soak_eff[power]->eff_integers[0];
        int32_t climit  = soak_eff[power]->eff_integers[2];
        
        // If the resist amount is higher, set the resist effect list to the effect index.
        // If they are equal prefer the one with the highest damage limit.
        if ( amount > camount || ( amount == camount && limit > climit ) ) {
            soak_eff[power] = eff;
        }
    }
}

void CreatureDefense::updateDamageResist() {
    uint16_t start, stop;
    
    std::fill_n(resist, DAMAGE_TYPE_NUM, 0);
    
    start = FEAT_EPIC_ENERGY_RESISTANCE_ACID_1;
    stop  = FEAT_EPIC_ENERGY_RESISTANCE_ACID_10;
    resist[DAMAGE_INDEX_ACID] = 10 * nwn_GetHasNthFeat(original_, start, stop);

    if ( resist[DAMAGE_INDEX_ACID] == 0 &&
         nwn_GetHasFeat(original_->cre_stats, FEAT_RESIST_ENERGY_ACID) ) {
        resist[DAMAGE_INDEX_ACID] = 5;
    }
    
    start = FEAT_EPIC_ENERGY_RESISTANCE_COLD_1;
    stop  = FEAT_EPIC_ENERGY_RESISTANCE_COLD_10;
    resist[DAMAGE_INDEX_COLD] = 10 * nwn_GetHasNthFeat(original_, start, stop);

    if ( resist[DAMAGE_INDEX_COLD] == 0 &&
         nwn_GetHasFeat(original_->cre_stats, FEAT_RESIST_ENERGY_COLD) ) {
        resist[DAMAGE_INDEX_COLD] = 5;
    }
    
    start = FEAT_EPIC_ENERGY_RESISTANCE_ELECTRICAL_1;
    stop  = FEAT_EPIC_ENERGY_RESISTANCE_ELECTRICAL_10;
    resist[DAMAGE_INDEX_ELECTRICAL] = 10 * nwn_GetHasNthFeat(original_, start, stop);

    if ( resist[DAMAGE_INDEX_ELECTRICAL] == 0 &&
         nwn_GetHasFeat(original_->cre_stats, FEAT_RESIST_ENERGY_ELECTRICAL) ) {
        resist[DAMAGE_INDEX_ELECTRICAL] = 5;
    }
    
    start = FEAT_EPIC_ENERGY_RESISTANCE_FIRE_1;
    FEAT_EPIC_ENERGY_RESISTANCE_FIRE_10;
    resist[DAMAGE_INDEX_FIRE] = 10 * nwn_GetHasNthFeat(original_, start, stop);

    if ( resist[DAMAGE_INDEX_FIRE] == 0 &&
         nwn_GetHasFeat(original_->cre_stats, FEAT_RESIST_ENERGY_FIRE) ) {
        resist[DAMAGE_INDEX_FIRE] = 5;
    }
    
    start = FEAT_EPIC_ENERGY_RESISTANCE_SONIC_1;
    stop  = FEAT_EPIC_ENERGY_RESISTANCE_SONIC_10;
    resist[DAMAGE_INDEX_SONIC] = 10 * nwn_GetHasNthFeat(original_, start, stop);

    if ( resist[DAMAGE_INDEX_SONIC] == 0 &&
         nwn_GetHasFeat(original_->cre_stats, FEAT_RESIST_ENERGY_SONIC) ) {
        resist[DAMAGE_INDEX_SONIC] = 5;
    }
}

void CreatureDefense::updateDamageResistEffects(CGameEffect *effect, bool remove) {
    int32_t eff_type, amount, dmg_flag, idx, limit;
    int32_t cur_eff;
    CGameEffect *e;

    std::fill_n(resist_eff, DAMAGE_TYPE_NUM, nullptr);

    if ( !remove && effect ) {
        dmg_flag = effect->eff_integers[0];
        idx = GetDamageIndexFromFlag(dmg_flag);
        resist_eff[idx] = effect;
    }

    for ( int i = original_->cre_stats->cs_first_dmgresist_eff;
          i < original_->obj.obj_effects_len; ++i ) {
        if ( !(e = original_->obj.obj_effects[i]) ||
             e->eff_type != EFFECT_TRUETYPE_DAMAGE_RESISTANCE ) {
            break;
        }

        if ( remove && e == effect ) { continue; }

        dmg_flag = e->eff_integers[0];
        amount   = e->eff_integers[1];
        limit    = e->eff_integers[2];

        idx = GetDamageIndexFromFlag(dmg_flag);
        
        if ( !resist_eff[idx] ) {
            resist_eff[idx] = e;
            continue;
        }
        
        int32_t camount = resist_eff[idx]->eff_integers[1];
        int32_t climit  = resist_eff[idx]->eff_integers[2];
        
        // If the resist amount is higher, set the resist effect list to the effect index.
        // If they are equal prefer the one with the highest damage limit.
        if ( amount > camount || ( amount == camount && limit > climit ) ) {
            resist_eff[idx] = e;
        }
    }
}
