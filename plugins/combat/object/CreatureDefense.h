#ifndef NWNX_COMBAT_OBJECT_CREATURE_DEFENSE_H
#define NWNX_COMBAT_OBJECT_CREATURE_DEFENSE_H

#include "combat/Defense.h"
class Creature;

class CreatureDefense : public Defense {
    Creature     *parent_;
    CNWSCreature *original_;

    int16_t       concealment;
    int16_t       hp_eff_;
    int16_t       hp_max_;
    bool          hp_update_ = true;
    
    uint16_t      soak;
    CGameEffect*  soak_eff[DAMAGE_POWER_NUM];
    int16_t       soak_stack[DAMAGE_POWER_NUM];    

    int16_t       immunity[DAMAGE_TYPE_NUM];
    int16_t       immunity_base[DAMAGE_TYPE_NUM];

    int16_t       immunity_misc[IMMUNITY_TYPE_NUM];

    int16_t       resist[DAMAGE_TYPE_NUM];
    CGameEffect*  resist_eff[DAMAGE_TYPE_NUM];
    int16_t       resist_stack[DAMAGE_TYPE_NUM];

    Saves         saves_;
    ArmorClass    ac_base;

public:
    CreatureDefense(Creature *p, CNWSCreature *o);
    virtual ~CreatureDefense() {}

    // Defense
    virtual void doDamageMods(DamageResult &res);

    virtual int32_t doDamageImmunity(CNWSCreature *vs, int32_t amount,
                                     uint16_t dmg_flags, bool no_feedback);
    virtual int32_t doDamageReduction(CNWSCreature *vs, int32_t dmg,
                                      uint8_t dmg_power, bool no_feedback);
    virtual int32_t doDamageResistance(CNWSCreature *vs, int32_t amount,
                                       uint16_t dmg_flags, bool no_feedback);

    virtual SaveResult doSavingThrow(uint32_t save_type, uint32_t dc,
                                     uint32_t save_vs_type, const VersusInfo& vs,
                                     bool feedback);

    int8_t getDamageImmunityByFlags(uint16_t flags);
    
    virtual uint32_t getArmorClass(bool dexed, uint32_t situ_flags, bool is_touch);
    virtual int32_t  getConcealment(bool is_ranged);

    virtual bool     canUseDexAC(uint32_t state, bool is_ranged);
    int32_t  getHPCurrent(bool use_temp);
    int32_t  getHPMax();
    uint32_t getHardness();    
    int32_t  getMiscImmunity(uint32_t type);

    int32_t  getSave(uint32_t save, uint32_t save_vs,
                     Attribute::selector select = Attribute::BOTH);
    int32_t  getSaveEffectBonus(uint32_t save, uint32_t save_vs);
    void     modifySave(uint32_t save, uint32_t save_vs, int32_t amount);

    virtual bool isImmune(uint32_t type);

    virtual void     modifyDamageImmunity(uint32_t dmg_type, int32_t amount);
    void modifyMiscImmunity(uint32_t type, int32_t amount);

    virtual void     modifyStackResist(uint32_t dmg_type, int32_t amount) {
        resist_stack[dmg_type] += amount;
    }

    virtual void     modifyStackSoak(uint32_t power, int32_t amount) {
        soak_stack[power] += amount;
    }

    std::string      getString(uint32_t flags);    
    std::string      toString();

    void update();
    void updateArmorClass();
    void updateHitPoints();
    void updateSaves();
    void updateDamageResist();
    void updateDamageResistEffects(CGameEffect *effect, bool remove);
    void updateDamageReductionEffects(CGameEffect *effect, bool remove);
    void updateDamageImmunity();

    friend class Creature;
};

#endif // NWNX_COMBAT_OBJECT_CREATURE_DEFENSE_H
