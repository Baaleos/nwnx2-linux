#ifndef NWNX_COMBAT_OFFENSE_H
#define NWNX_COMBAT_OFFENSE_H

#include "talib/custom/damage.h"

class Offense {
public:
    virtual ~Offense();

    virtual uint32_t     ammoAvailable(int32_t num_attacks, bool equip) = 0;
    virtual int32_t      getAttackBonus(int32_t equip_num, bool use_base = true, bool use_eff = true) = 0;
    virtual int8_t       getAttacks() = 0;
    virtual int8_t       getAttacksOffhand() = 0;
    virtual uint32_t     getCritMultiplier(int32_t equip_num) = 0;
    virtual uint32_t     getCritThreat(int32_t equip_num) = 0;
    virtual DamageResult getDamageVersus(int32_t equip_num) = 0;
    virtual uint32_t     getIteration(int32_t equip_num) = 0;
    virtual int32_t      getMissChance(bool is_ranged) = 0;
    virtual bool         hasCreatureAttacks() = 0;
    virtual bool         isEquipValid(int32_t equip_num) = 0;
    virtual void         update() = 0;
};

inline Offense::~Offense() {}

#endif // NWNX_COMBAT_OFFENSE_H
