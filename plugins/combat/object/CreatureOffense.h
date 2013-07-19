#ifndef NWNX_COMBAT_OBJECT_CREATURE_OFFENSE_H
#define NWNX_COMBAT_OBJECT_CREATURE_OFFENSE_H

#include "consts.h"
#include "combat/Offense.h"
#include <string>

class Creature;

class CreatureOffense : public Offense {
    Creature     *parent_;
    CNWSCreature *original_;

    uint16_t      ab_base;
    uint16_t      ab_additional;
    int16_t       offhand_penalty_on;
    int16_t       offhand_penalty_off;
    uint16_t      weapon_type;
    int16_t       ab_eff[9];

    std::vector<DamageAmount> dmg_eff_[9];

    bool          use_cre_attacks;
    uint32_t      attacks;
    uint32_t      attacks_offhand;

    CombatWeapon  equips[EQUIP_TYPE_NUM];

public:
    CreatureOffense(Creature *parent, CNWSCreature *o);
    virtual ~CreatureOffense() {}
    
    // Offense
    virtual uint32_t     ammoAvailable(int32_t num_attacks, bool equip = false);
    virtual int32_t      getAttackBonus(int32_t equip_num, bool use_base = true, bool use_eff = true);
    virtual int32_t      getTouchAttackBonus(bool is_ranged);
    virtual int8_t       getAttacks() { return attacks; }
    virtual int8_t       getAttacksOffhand() { return attacks_offhand; }
    virtual uint32_t     getCritMultiplier(int32_t equip_num);
    virtual uint32_t     getCritThreat(int32_t equip_num);
    int32_t              getDamageBonus(int32_t equip_num);
    virtual DamageResult getDamageVersus(int32_t equip_num);
    bool getHasDevistatingCritical(int32_t equip_num);
    virtual uint32_t     getIteration(int32_t equip_num) {
        return equips[equip_num].iter;
    }

    virtual int32_t      getMissChance(bool is_ranged);
    const CombatWeapon  *getWeapon(int32_t equip_num);
    virtual bool         hasCreatureAttacks() { return use_cre_attacks; }
    
    bool isEquipValid(int32_t equip_num) {
        return equips[equip_num].id != OBJECT_INVALID;
    }

    void modifyEffectAttackBonus(uint32_t type, int32_t amount);
    void modifyEffectAttacks(int32_t amount, bool is_offhand);
    void modifyEffectDamamge(uint32_t dmgflag, uint32_t atktype, int32_t amount, 
                              bool is_penalty, bool remove);

    CombatWeapon         getWeaponInfo(CNWSItem *it, bool unarmed);
    
    void update();
    void updateAttackBonus();
    void updateAttacks();
    void updateEquips();
    void updateAbility();

    std::string          getString(uint32_t flags);
    std::string          toString();
    void                 log();

    friend class Creature;
};

inline bool CreatureOffense::getHasDevistatingCritical(int32_t equip_num) {
    return equips[equip_num].has_dev_crit;
}
#endif // NWNX_COMBAT_OBJECT_CREATURE_OFFENSE_H
