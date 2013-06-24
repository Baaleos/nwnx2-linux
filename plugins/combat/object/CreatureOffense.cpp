#include "NWNXCombat.h"
#include "CreatureOffense.h"

#define NDEBUG
#include <assert.h>
#include <iostream>
#include <sstream>
#include <string>

extern CNWNXCombat combat;

// Offense

CreatureOffense::CreatureOffense(Creature *parent, CNWSCreature *o)
    : parent_(nullptr),
      original_(o),
      ab_base(0),
      ab_additional(0),
      offhand_penalty_on(0),
      offhand_penalty_off(0),
      weapon_type(0),
      ab_eff(),
      use_cre_attacks(false),
      attacks(0),
      attacks_offhand(0),
      equips() {}

uint32_t CreatureOffense::ammoAvailable(int32_t num_attacks, bool equip) {
    CNWSItem *it;
    int32_t stacksize = 0;
    uint32_t basetype, equipslot;

    //combat.Log(3, "GetAmmunitionAvailable : Num Attacks: %d, Ranged Type: %d", num_attacks, ranged_type);

    // Determine if there is a weapon that has Unlimited Ammo item
    // property.
    it = nwn_GetItemInSlot(original_, EQUIPMENT_SLOT_RIGHTHAND);
    if ( it && CNWSItem__GetPropertyByTypeExists(it, 61, 0) ) {
	return num_attacks;
    }

    switch(weapon_type) {
    default: return num_attacks;
    case WEAPON_TYPE_BOW:
	equipslot = EQUIPMENT_SLOT_ARROWS;
	basetype  = BASE_ITEM_ARROW;
	break;
    case WEAPON_TYPE_SLING:
	equipslot = EQUIPMENT_SLOT_BULLETS;
	basetype  = BASE_ITEM_BULLET;
	break;
    case WEAPON_TYPE_CROSSBOW:
	equipslot = EQUIPMENT_SLOT_BOLTS;
	basetype  = BASE_ITEM_BOLT;
	break;
    case WEAPON_TYPE_SHURIKEN:
	equipslot = EQUIPMENT_SLOT_RIGHTHAND;
	basetype  = BASE_ITEM_SHURIKEN;
	break;
    case WEAPON_TYPE_DART:
	equipslot = EQUIPMENT_SLOT_RIGHTHAND;
	basetype  = BASE_ITEM_DART;
	break;
    case WEAPON_TYPE_THROWAXE:
	equipslot = EQUIPMENT_SLOT_RIGHTHAND;
	basetype  = BASE_ITEM_THROWINGAXE;
	break;
    }

    it = CNWSInventory__GetItemInSlot(original_->cre_equipment, equipslot);
    CNWSItem *orig_it = it;
    if(orig_it) {
	int ss = orig_it->it_stacksize;
	stacksize = equip ? ss - num_attacks : ss;
    }

    // If num_attacks is zero, there is no item, so we'll have to find one.
    if(stacksize <= 0){
	// This is really moronic it seems like to me and should be changed.
	// This is how the default code is tho: traversing the list for each i

	uint32_t i = 0;
	uint32_t id;
	bool canequip;
	do {
	    id = CItemRepository__FindItemWithBaseItemId(original_->cre_inventory, basetype, i);
	    //combat.Log(3, "GetAmmunitionAvailable : Item to Equip: %X", id);
	    ++i;

	    if ( (it = nwn_GetItemByID(id)) != NULL) {
		canequip = CNWSCreature__CanEquipItem(original_, it, &basetype, 1, 0, 0, 0);
		CNWSCreature__RemoveItemFromRepository(original_, it, 1);
		if(it->it_possessor != original_->obj.obj_id) {
		    CNWSItem__SetPossessor(it, original_->obj.obj_id, 0, 0, 0);
		}
		
		if ( equip ) {
		    CNWSCreature__UnequipItem(original_, orig_it, 0);
		    CItemRepository__AddItem(original_->cre_inventory, &orig_it, -1, -1, 0, 0);
		}
		
		CNWSCreature__EquipItem(original_, equipslot, it, 1, 0);
                stacksize = it->it_stacksize;
		break;
	    }
	} while (id != OBJECT_INVALID);
    }

    if ( !equip ) {
	if (stacksize < 20 && original_->cre_last_ammo_warning >= stacksize + 5 ){
	    original_->cre_last_ammo_warning = stacksize;
	    CNWCCMessageData *msg = CNWCCMessageData_create();
	    CExoArrayList_int32_add(&msg->integers, stacksize);
	    CNWSCreature__SendFeedbackMessage(original_, 0x18, msg, 0);
	}
    }
    
    // Can't have more attacks than the final stacksize.
    num_attacks = std::min(num_attacks, stacksize);

    // If we have nothing to attack with, send out the appropriate messages.
    if ( !equip && num_attacks == 0 ){
	if( original_->cre_is_pc || CNWSCreature__GetIsPossessedFamiliar(original_) ){
	    CNWSCreature__SendFeedbackMessage(original_, 0x19, 0, 0);
	}
	else if( original_->cre_master_id != OBJECT_INVALID ){
	    CNWSCreature *master = nwn_GetCreatureByID(original_->cre_master_id);
	    if( master ) {
		CNWCCMessageData *msg = CNWCCMessageData_create();
		CExoArrayList_uint32_add(&msg->objects, original_->obj.obj_id);
		CNWSCreature__SendFeedbackMessage(master, 0xF1, msg, 0);
	    }
	}
    }

    //combat.Log(3, "GetAmmunitionAvailable : Final Attack Count: %d", num_attacks);
    
    return num_attacks;
}

int32_t CreatureOffense::getAttackBonus(int32_t equip_num, bool use_base, bool use_eff) {
    int32_t ab = 0;
    if ( use_base ) {
	ab += ab_base;
	combat.Log(3, "BAB: %d\n", ab_base);
    }

    if ( use_eff ) {
	int32_t eff = ab_eff[GetAttackTypeFromEquipNum(equip_num)];
	ab += eff;
	ab += ab_eff[0];
	combat.Log(3, "Effects: Weapon: %d, Misc: %d\n", eff, ab_eff[0]);
    }

    if ( equip_num == 0 ) {
	combat.Log(3, "Onhand Penalty: %d\n", offhand_penalty_on);
	ab += offhand_penalty_on;
    }
    else if ( equip_num == 1 ) {
	combat.Log(3, "Offhand Penalty: %d\n", offhand_penalty_off);
	ab += offhand_penalty_off;
    }

    // Weapon mods.
    ab += equips[equip_num].ab_mod;
    combat.Log(3, "Weapon AB: %d\n", equips[equip_num].ab_mod);

    int32_t ab_abil = equips[equip_num].ab_ability;
    combat.Log(3, "AB ability modifier: %d\n", ab_abil);
    ab += ab_abil;

    ab += ab_additional;

    // Mode
    ab += parent_->mode_.ab;

    return ab;
}

int32_t CreatureOffense::getTouchAttackBonus(bool is_ranged) {
    // Return AB for what would literally be an unarmed attack...
    // NOTE: This is not default behavior.XS
    return getAttackBonus(2, true, true);
}

uint32_t CreatureOffense::getCritMultiplier(int32_t equip_num) {
    if ( isEquipValid(equip_num) ) {
	return equips[equip_num].crit_mult;
    }

    return 2;
}

uint32_t CreatureOffense::getCritThreat(int32_t equip_num) {
    if ( isEquipValid(equip_num) ) {
	return 21 - equips[equip_num].crit_range;
    }

    return 20;
}

int32_t CreatureOffense::getDamageBonus(int32_t equip_num) {
    if ( !isEquipValid(equip_num) ) { return 0; }
    
    return equips[equip_num].dmg_ability + 
	equips[equip_num].base_dmg_roll.bonus;
}

DamageResult CreatureOffense::getDamageVersus(int32_t equip_num) {
    uint32_t attack_type = GetAttackTypeFromEquipNum(equip_num);
    combat.Log(3, 
	       "getDamageVersus: Equip: %d, Attack Type: %d, Weapon Damage Count: %d "
	       "Effect Misc Count: %d, Effect Attack Type Count: %d\n",
	       equip_num,
	       attack_type,
	       equips[equip_num].damage.size(),
	       dmg_eff_[0].size(),
	       dmg_eff_[attack_type].size());

    return DamageResult(&equips[equip_num],
			&dmg_eff_[0],
			&dmg_eff_[GetAttackTypeFromEquipNum(equip_num)]);
}

int32_t CreatureOffense::getMissChance(bool is_ranged) {
    int32_t total = 0;
    int32_t amount, miss_type;
    
    int32_t vs_type = is_ranged
	? MISS_CHANCE_TRUETYPE_RANGED
	: MISS_CHANCE_TRUETYPE_MELEE;

    int32_t dark_type = original_->cre_vision_type & 2
	? MISS_CHANCE_TRUETYPE_DARKNESS
	: -1;

    for ( int i = original_->cre_stats->cs_first_misschance_eff;
	  i < original_->obj.obj_effects_len;
	  ++i ) {
	CGameEffect *e = original_->obj.obj_effects[i];
	if ( e->eff_type != EFFECT_TRUETYPE_MISS_CHANCE ) {
	    break;
	}

	amount = e->eff_integers[0];
	miss_type = e->eff_integers[1];

	if ( miss_type == MISS_CHANCE_TRUETYPE_NORMAL ||
	     miss_type == vs_type                     ||
	     miss_type == dark_type ) {
	    total = std::max(total, amount);
	}
    }
    
    return total;
}

const CombatWeapon *CreatureOffense::getWeapon(int32_t equip_num) {
    if ( !isEquipValid(equip_num) ) { return NULL; }
    return &equips[equip_num];
}

void CreatureOffense::modifiyEffectAttackBonus(uint32_t type, int32_t amount) {
    if ( type < 0 || type > 8 ) {
	return;
    }
    ab_eff[type] += amount;
}

void CreatureOffense::modifiyEffectDamamge(uint32_t dmgflag, uint32_t atktype, int32_t amount, 
					   bool is_penalty, bool remove = false) {
    uint32_t idx = GetDamageIndexFromFlag(dmgflag);
    DamageAmount amt(idx, combat.ip_dmg_to_roll(amount), is_penalty);

    if ( remove ) {
	auto it = std::find(dmg_eff_[atktype].begin(),
			    dmg_eff_[atktype].end(),
			    amt);
	if ( it != dmg_eff_[atktype].end() ) {
	    dmg_eff_[atktype].erase(it);
	}
    }
    else {
	dmg_eff_[atktype].push_back(amt);
    }
}

void CreatureOffense::update() {
    updateAttackBonus();
    updateEquips();
    updateAttacks();
}

void CreatureOffense::updateAttackBonus() {
    ab_base = CNWSCreatureStats__GetBaseAttackBonus(original_->cre_stats, 0);

    switch(original_->cre_size) {
    case CREATURE_SIZE_TINY:  ab_base += 2;  break;
    case CREATURE_SIZE_SMALL: ab_base += 1;  break;
    case CREATURE_SIZE_LARGE: ab_base -= 1;  break;
    case CREATURE_SIZE_HUGE:  ab_base += -2; break;
    }

    ab_additional = parent_->getSkillRank(SKILL_CRAFT_WEAPON, NULL, false) / 40;
    if ( nwn_GetHasFeat(original_->cre_stats, FEAT_EPIC_PROWESS) ) {
	ab_additional += 1;
    }

    CNWSArea *area = nwn_GetAreaByID(original_->obj.obj_area_id);
    if ( area                   &&
	 area->area_type & 4    &&
	 !(area->area_type & 2) &&
	 !(area->area_type & 1) &&
	 nwn_GetHasFeat(original_->cre_stats, FEAT_NATURE_SENSE) ) {
	ab_base += 2;
    }
}

void CreatureOffense::updateAbility() {
    for ( size_t i = 0; i < EQUIP_TYPE_NUM; ++i ) {
	uint32_t id = equips[i].id;
	equips[i].ab_ability  = GetWeaponAttackAbility(original_,
						       nwn_GetItemByID(id));
	equips[i].dmg_ability = GetWeaponDamageAbility(original_,
						       nwn_GetItemByID(id));
    }
}

void CreatureOffense::updateAttacks() {
    int base_iter = 5;

    // If there is an ohand weapon go by whatever iteration it has.
    if ( isEquipValid(0) ) {
	base_iter = equips[0].iter;
    }
    // If there is no onhand weapon, the creature is unarmed, so if it
    // can use monk abilities it's iteration is 3.
    else if ( CanUseClassAbilities(original_, CLASS_TYPE_MONK).first ) {
	base_iter = 3;
    }

    attacks = (ab_base + base_iter - 1) / base_iter;
    if ( base_iter == 5 ) {
	 attacks = CLAMP<uint32_t>(attacks, 1, 4);
    }
    else {
	attacks = CLAMP<uint32_t>(attacks, 1, MAXIMUM_ATTACKS);
    }

    attacks_offhand = 0;
    if ( isEquipValid(1) ) {
	attacks_offhand = 1;
	
	// Note: checking for this feat will return true if the
	// creature has the ranger dual-wield feat.
	if ( nwn_GetHasFeat(original_->cre_stats,
			    FEAT_IMPROVED_TWO_WEAPON_FIGHTING) ) {
	    ++attacks_offhand;
	}
    }
    
#if TA
    int32_t style = nwn_GetLocalInt(&original_->obj.obj_vartable,
				    "pc_style_fighting");
    
    CNWSItem* lh = nwn_GetItemInSlot(original_,
				     EQUIPMENT_SLOT_LEFTHAND);
    
    if ( !isEquipValid(0) ) {
	if ( style == 7 ) { // sunfist
	    attacks++;
	}
	if ( nwn_GetKnowsFeat(original_->cre_stats, 2001) ) {
	    attacks++;
	}
    }
    else if ( style == 3 && lh && // Spartan
	      ( lh->it_baseitem == BASE_ITEM_SMALLSHIELD ||
                lh->it_baseitem == BASE_ITEM_LARGESHIELD ||
                lh->it_baseitem == BASE_ITEM_TOWERSHIELD) ) {
	attacks++;
    }
    
    int ranger = nwn_GetLevelByClass(original_->cre_stats, CLASS_TYPE_RANGER);
    int monk   = nwn_GetLevelByClass(original_->cre_stats, CLASS_TYPE_MONK);
    if ( attacks_offhand > 0 && ranger >= 40 && monk == 0 ) {
	attacks_offhand++;
    }
    
#endif

}

void CreatureOffense::updateEquips() {
    // Initialize all weapons to invalid and wield type to unarmed.
    use_cre_attacks = false;
    weapon_type = WEAPON_TYPE_UNARMED;
    for ( int i = 0; i < EQUIP_TYPE_NUM; ++i ) {
	equips[i].id = OBJECT_INVALID;
    }

    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    if ( !props ) {
	combat.Log(0, "ERROR: Unable to find %s.2da!\n", WEAPON_PROP_2DA);
	return;
    }

    CNWSItem *it;
    int weaps = 0;

    if ( (it = nwn_GetItemInSlot(original_, EQUIPMENT_SLOT_RIGHTHAND)) &&
	 combat.baseitem_to_weapon(it->it_baseitem) != 0 ) {
	
	// Determine the onhand weapon wield type, this is used in the
	// combat engine to check if we can load more ammunition among
	// other things.
	int wpn = combat.baseitem_to_weapon(it->it_baseitem);
	weapon_type = nwn_Get2daInt(props, "Type", wpn);
	
	weaps++;
	equips[EQUIP_TYPE_ONHAND] = getWeaponInfo(it, false);
	GetWeaponAmmoDamage(original_, weapon_type, equips[EQUIP_TYPE_ONHAND].damage);
    }

    // Can only dual-wield if, the offhand is equipped, and
    // the onhand and offhand are both weapons.
    if ( weaps > 0                                                    &&
	 (it = nwn_GetItemInSlot(original_, EQUIPMENT_SLOT_LEFTHAND)) &&
	 combat.baseitem_to_weapon(it->it_baseitem) != 0) {
	weaps++;
	equips[EQUIP_TYPE_OFFHAND] = getWeaponInfo(it, false);

	// Calculate offhand penalty.
	auto dual = GetDualWieldPenalty(original_);
	offhand_penalty_on = dual.first;
	offhand_penalty_off = dual.second;
    }
    else {
	offhand_penalty_on = 0;
	offhand_penalty_off = 0;
    }

    // Can only use unarmed attacks if no weapon is equipped onhand
    // Bracers do not count, only gloves.
    if ( weaps == 0                                               &&
	 (it = nwn_GetItemInSlot(original_, EQUIPMENT_SLOT_ARMS)) &&
	 combat.baseitem_to_weapon(it->it_baseitem) != 0 ) {
	equips[EQUIP_TYPE_UNARMED] = getWeaponInfo(it, true);
	weaps++;
    }

    // Can only use creature attacks if no other weapon is equipped.
    if ( weaps == 0 ) {
	if ( (it = nwn_GetItemInSlot(original_, EQUIPMENT_SLOT_CWEAPON_L)) ) {
	    weaps++;
	    equips[EQUIP_TYPE_CREATURE_1] = getWeaponInfo(it, true);
	    use_cre_attacks = true;
	}
	if ( (it = nwn_GetItemInSlot(original_, EQUIPMENT_SLOT_CWEAPON_R)) ) {
	    weaps++;
	    equips[EQUIP_TYPE_CREATURE_2] = getWeaponInfo(it, true);
	    use_cre_attacks = true;
	}
	if ( (it = nwn_GetItemInSlot(original_, EQUIPMENT_SLOT_CWEAPON_B)) ) {
	    weaps++;
	    equips[EQUIP_TYPE_CREATURE_3] = getWeaponInfo(it, true);
	    use_cre_attacks = true;
	}
    }

    // If there are no weapons equipped or unarmed is invalid then
    // the default unarmed equip has to be updated.  For general combat
    // in the former case and for touch attacks in the latter.
    if ( weaps == 0 || !isEquipValid(EQUIP_TYPE_UNARMED) ) {
	equips[EQUIP_TYPE_UNARMED] = getWeaponInfo(NULL, true);
    }
}

CombatWeapon CreatureOffense::getWeaponInfo(CNWSItem *it, bool unarmed) {
    CombatWeapon weap;
    DamageAmount dmg;
    
    if ( !it ) {
	weap.id = OBJECT_INVALID;
	if ( unarmed ) {
	    dmg                 = GetUnarmedBaseDamage(original_, it);
	    weap.base_dmg_flags = dmg.type;
	    weap.base_dmg_roll  = dmg.roll;
	    weap.iter           = GetWeaponIteration(original_, it);
	    weap.ab_ability     = GetWeaponAttackAbility(original_, it);
	    weap.dmg_ability    = GetWeaponDamageAbility(original_, it);
	    weap.crit_range     = 1;
	    weap.crit_mult      = 2;
	    weap.power          = 0;
	}
    }
    else {
	weap.id          = it->obj.obj_id;
	weap.ab_mod      = GetWeaponAttackBonus(original_, it);
	weap.ab_ability  = GetWeaponAttackAbility(original_, it);
	weap.dmg_ability = GetWeaponDamageAbility(original_, it);
	weap.iter        = GetWeaponIteration(original_, it);
	weap.crit_range  = GetWeaponCritRange(original_, it);
	
	auto crit = GetWeaponCritMultiplier(original_, it);
	weap.crit_mult   = crit.first;
	weap.has_dev_crit    = crit.second;
	
	GetWeaponCritDamage(original_, it, weap.crit_dmg);
	GetWeaponDamage(original_, it, weap.damage);

	if ( unarmed ) {
	    dmg = GetUnarmedBaseDamage(original_, it);
	}
	else {
	    dmg = GetWeaponBaseDamage(original_, it);
	}
	
	weap.power = GetWeaponPower(original_, it);
	weap.base_dmg_flags = dmg.type;
	weap.base_dmg_roll  = dmg.roll;
    }
    return weap;
}

std::string CreatureOffense::getString(uint32_t flags) {
    std::ostringstream out;

    if ( NWNX_COMBAT_INFO_ATTACKS & flags ) {
	out << "Weapon Wield Type: " << weapon_type <<  std::endl;
	if ( use_cre_attacks ) {
	    out << "Using Creature Attacks: true\n";
	}
	else {
	    out << "Using Creature Attacks: false\n";
	}
    
    
	out << "Attacks: " << attacks << std::endl;
	if ( attacks_offhand > 0 ) {
	    out << "Offhand Attacks: " << attacks_offhand <<  std::endl;
	    out << "Dual-wield Penalty: on: "
		<< offhand_penalty_on
		<< "; off: " << offhand_penalty_off << '\n';
	}
    }
    if ( NWNX_COMBAT_INFO_WEAPONS & flags ) {
	out << "\nWeapons:\n";
	for ( int i = 0; i < EQUIP_TYPE_NUM; ++i ) {
	    out << "Equip: " << i <<  std::endl;
	    out << "  ID: 0x" << std::hex << equips[i].id <<  std::endl << std::dec;

	    if ( !isEquipValid(i) ) {
		continue;
	    }
	
	    out << "  Full AB: " << getAttackBonus(i) << '\n';
	    out << "  Attack Ability: " << equips[i].ab_ability << '\n';
	    out << "  Attack Bonus: " << equips[i].ab_mod << '\n';
	    out << "  Damage Ability: " << equips[i].dmg_ability << '\n';
	    out << "  Base Damage Flags: " << equips[i].base_dmg_flags << '\n';
	    out << "  Base Damage Roll: " << equips[i].base_dmg_roll << '\n';

	    if ( equips[i].crit_dmg.size() > 0 ) {
		out << "  Critical Damage: ";
		for ( auto& dmg : equips[i].crit_dmg ) {
		    out << dmg << "; ";
		}
		out << std::endl;
	    }

	    if ( equips[i].damage.size() > 0 ) {
		out << "  Damage (" 
		    << equips[i].damage.size()
		    << "):\n";
		for ( auto& dmg : equips[i].damage ) {
		    out << "    " << dmg << '\n';
		}
		out << std::endl;
	    }
	
	    out << "  Critical Range: " 
		<< 21 - equips[i].crit_range 
		<< "-20" << '\n';

	    out << "  Critical Multiplier: " << equips[i].crit_mult <<  std::endl;
	    out << "  Iteration: " << equips[i].iter <<  std::endl;
	}

    }
    return out.str();
}


std::string CreatureOffense::toString() {
    std::ostringstream out;

    out << "BAB: " << ab_base << std::endl;
    out << getString(0xFFFFFFFF);

    return out.str();
}

void CreatureOffense::log() {
    combat.Log(0, "%s", (char*)toString().c_str());
}
