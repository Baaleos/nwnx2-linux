#include "AttackData.h"

extern CNWNXCombat combat;

void AttackData::addCCMessage(CNWCCMessageData *msg) {
    CExoArrayList_ptr_add(&attack_->cad_feedback, msg);
}

void AttackData::addEffect(CGameEffect *eff, uint32_t creator) {
    eff->eff_creator = creator;
    CExoArrayList_ptr_add(&attack_->cad_onhit_effs, eff);
}

void AttackData::addOnHitCastSpells(CNWSCreature *attacker, 
                                    CNWSObject *target,
                                    CNWSItem *item,
                                    bool from_target) {

	uint32_t item_id = item->obj.obj_id;
	for ( size_t i = 0; i < item->it_active_ip_len; ++i ) {
		CNWItemProperty& ip = item->it_active_ip[i];
		if ( ip.ip_type != ITEM_PROPERTY_ONHITCASTSPELL ) {
			continue;
		}
		addOnHitCastSpell(attacker,
						  target,
						  &ip,
						  item_id,
						  from_target);
	}

	for ( size_t i = 0; i < item->it_passive_ip_len; ++i ) {
		CNWItemProperty& ip = item->it_passive_ip[i];
		if ( ip.ip_type != ITEM_PROPERTY_ONHITCASTSPELL ) {
			continue;
		}
		addOnHitCastSpell(attacker,
						  target,
						  &ip,
						  item_id,
						  from_target);
	}
}

void AttackData::addOnHitCastSpell(CNWSCreature *attacker, 
								   CNWSObject *target,
								   CNWItemProperty *ip,
								   uint32_t item_id,
								   bool from_target) {

	if ( !attacker || !target || !ip || 
		 ip->ip_type != ITEM_PROPERTY_ONHITCASTSPELL ) {
		return;
	}

	C2DA *tda = (*NWN_Rules)->ru_2das->tda_iprp_onhitspell;
	if ( !tda ) { return; }

	int sp_idx = nwn_Get2daInt(tda, "SpellIndex", ip->ip_subtype);
	combat.Log(3, "OnHit Spell Index: %d\n", sp_idx);
	combat.Log(3, "Spell 2da Length: %d\n",
			   (*NWN_Rules)->ru_spells->spa_len);

	CNWSpell *spell;
	if ( sp_idx < 0 || sp_idx > (*NWN_Rules)->ru_spells->spa_len ) {
		return;
	}
	else {
		spell = &(*NWN_Rules)->ru_spells->spa_spells[sp_idx];
	}

	CNWSSpellScriptData *data = (CNWSSpellScriptData *)malloc(sizeof(CNWSSpellScriptData));
	if ( !data ) { return; }

	data->target_pos.x = 0;
	data->target_pos.y = 0;
	data->target_pos.z = 0;
	data->feat_id      = -1;
	data->item_id      = item_id;
	data->area_id      = attacker->obj.obj_area_id;

	combat.Log(3, "OnHit Spell Impcat: %s\n", spell->sp_impactscript.text);
	data->script.text  = strdup(spell->sp_impactscript.text);
	data->script.len   = strlen(spell->sp_impactscript.text);

	if ( from_target ) {
		data->target_id  = attacker->obj.obj_id;
		data->target_pos = attacker->obj.obj_position;
		data->caster_id  = target->obj_id;
		CExoArrayList_ptr_add(&attack_->cad_onhit_spells2, data);
	}
	else {
		data->target_id  = target->obj_id;
		data->target_pos = target->obj_position;
		data->caster_id  = attacker->obj.obj_id;
		CExoArrayList_ptr_add(&attack_->cad_onhit_spells, data);
	}
}
    
void AttackData::addVisual(uint32_t vfx, uint32_t creator) {
    CGameEffect *eff = effect_visual(vfx, false);
    eff->eff_creator = creator;
	nwn_SetDurationType(eff, DURATION_TYPE_INSTANT);
    CExoArrayList_ptr_add(&attack_->cad_onhit_effs, eff);
}

void AttackData::clearSpecialAttack() {
    attack_->cad_special_attack = 0;
}

void AttackData::copyDamage(const DamageResult& dmg) {
    for ( size_t i = 0; i < 13; ++i ) {
		if ( dmg.damages[i] > 0 ) {
			attack_->cad_damage[i] = dmg.damages[i];
			if ( isHit() ) {
				int vfx = nwn_GetDamageVisualEffect(1 << i, isRanged());
				if ( vfx > 0) {
					addVisual(vfx, attacker_->obj.obj_id);
				}
			}
		}
		else {
			attack_->cad_damage[i] = -1;
		}
    }

    if ( !combat.disable_damage_mod_msg ) {
        for ( size_t i = 0; i < 13; ++i ) {
            if ( dmg.immunity_adjust[i] > 0 ) {
                CNWCCMessageData* msg = CNWCCMessageData_create();
                CExoArrayList_int32_add(&msg->integers, 62);
                CExoArrayList_int32_add(&msg->integers, dmg.immunity_adjust[i]);
                CExoArrayList_int32_add(&msg->integers, 1 << i);
                CExoArrayList_uint32_add(&msg->objects, attack_->cad_target);
                addCCMessage(msg);
            }
        }

        for ( size_t i = 0; i < 13; ++i ) {
            if ( dmg.resist_adjust[i] > 0 ) {
                CNWCCMessageData* msg = CNWCCMessageData_create();
                CExoArrayList_int32_add(&msg->integers, 63);
                CExoArrayList_int32_add(&msg->integers, dmg.resist_adjust[i]);
                CExoArrayList_int32_add(&msg->integers, 0);
                CExoArrayList_uint32_add(&msg->objects, attack_->cad_target);
                addCCMessage(msg);
            }
        }

        if ( dmg.soak_adjust > 0 ) {
            CNWCCMessageData* msg = CNWCCMessageData_create();
            CExoArrayList_int32_add(&msg->integers, 67);
            CExoArrayList_int32_add(&msg->integers, dmg.soak_adjust);
            CExoArrayList_int32_add(&msg->integers, 0);
            CExoArrayList_uint32_add(&msg->objects, attack_->cad_target);
            addCCMessage(msg);
        }
    }

    /* TODO:
    // Any damages above the default 13 NWN damages must be
    // applied as effects.
    for ( size_t i = 13; i < DAMAGE_TYPE_NUM; ++i ) {
        if ( dmg.damages[i] > 0 ) {
            attack_->cad_damage[i] = dmg.damages[i];
        }
    }
    */
}

void AttackData::setAttackBonus(uint32_t ab, uint32_t roll) {
    attack_->cad_attack_mod = ab;
    attack_->cad_attack_roll = roll;
}

void AttackData::setConcealment(uint32_t chance) {
    // Show the modified conceal/miss chance in the combat log.
    attack_->cad_concealment = chance;
}

void AttackData::setCriticalResult(uint32_t threat, uint32_t result) {
    attack_->cad_threat_roll = threat;
    attack_->cad_critical_hit = result;
}

void AttackData::setDeflected(bool val) {
    attack_->cad_attack_deflected = val;
}

void AttackData::setMissedBy(uint32_t ab) {
    attack_->cad_missed = ab;
}

void AttackData::setResult(uint32_t result) {
    attack_->cad_attack_result = result;
}

void AttackData::setSneaks(bool sneak, bool death) {
    attack_->cad_sneak_attack = sneak;
    attack_->cad_death_attack = death;
}

uint32_t AttackData::getAttackBonus() {
    return attack_->cad_attack_mod + attack_->cad_attack_roll;
}

uint32_t AttackData::getAttackRoll() {
    return attack_->cad_attack_roll;
}


uint32_t AttackData::getAttackType() {
    return attack_->cad_attack_type;
}

int32_t AttackData::getResult() {
    return attack_->cad_attack_result;
}

int32_t AttackData::getSpecialAttack() {
    return attack_->cad_special_attack;
}

bool AttackData::isCoupDeGrace() {
    return !!attack_->cad_coupdegrace;
}

bool AttackData::isCriticalHit() {
    return getResult() == 3;
}

bool AttackData::isHit() {
    int r = getResult();
    return r == 1 || r == 3 || r == 5 || r == 6 || r == 7 || r == 10;
}

bool AttackData::isRanged() {
    return !!attack_->cad_ranged_attack;
}

bool AttackData::isSneak() {
    return !!attack_->cad_sneak_attack;
}

bool AttackData::isSpecialAttack() {
    return !!attack_->cad_special_attack;
}
