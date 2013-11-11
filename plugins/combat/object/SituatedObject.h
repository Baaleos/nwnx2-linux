#ifndef NWNX_COMBAT_OBJECT_SITUATED_H
#define NWNX_COMBAT_OBJECT_SITUATED_H

#include "NWNXLib.h"
#include "talib/nwn/all.h"
#include "combat/Defense.h"
#include "talib/accumulator/all.h"

class SituatedObject : public Defense {
public:
    SituatedObject() {}
    explicit SituatedObject (CNWSObject *obj) : obj_(obj) {}

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

    virtual bool     canUseDexAC(uint32_t state, bool is_ranged) {
        return false;
    }
    virtual uint32_t getArmorClass(bool dexed, uint32_t situ_flags, bool is_touch);
    virtual int32_t  getConcealment(bool is_ranged);
    virtual int32_t  getHPCurrent(bool use_temp);
    virtual int32_t  getHPMax();
    virtual uint32_t getHardness();
    virtual int32_t  getSave(uint32_t save, uint32_t save_vs,
                             Attribute::selector select = Attribute::BOTH);
    virtual bool     isImmune(uint32_t type);
    virtual void     modifyDamageImmunity(uint32_t dmg_type, int32_t amount);
        
    virtual void     update() {}
private:
    CNWSObject *obj_;
};

#endif // NWNX_COMBAT_OBJECT_SITUATED_H
