#ifndef _NWNX_COMBAT_DEFENSE_H_
#define _NWNX_COMBAT_DEFENSE_H_

#include "talib/accumulator/all.h"

typedef std::pair<uint32_t, CNWCCMessageData*> SaveResult;

class Defense {
public:
    virtual ~Defense() {}

    virtual bool    canUseDexAC(uint32_t state, bool is_ranged) = 0;
    virtual void    doDamageMods(DamageResult &res) = 0;
    virtual int32_t doDamageImmunity(CNWSCreature *vs, int32_t amount,
                                     uint16_t dmg_flags, bool no_feedback) = 0;
    virtual int32_t doDamageReduction(CNWSCreature *vs, int32_t dmg,
                                      uint8_t dmg_power, bool no_feedback) = 0;
    virtual int32_t doDamageResistance(CNWSCreature *vs, int32_t amount,
                                       uint16_t dmg_flags, bool no_feedback) = 0;
    virtual SaveResult doSavingThrow(uint32_t save_type, uint32_t dc,
                                     uint32_t save_vs_type, const VersusInfo& vs,
                                     bool feedback) = 0;

    virtual uint32_t getArmorClass(bool dexed, uint32_t situ_flags, bool is_touch) = 0;
    virtual int32_t  getConcealment(bool is_ranged) = 0;
    virtual int32_t  getHPCurrent(bool use_temp) = 0;
    virtual int32_t  getHPMax() = 0;
    virtual uint32_t getHardness() = 0;
    virtual int32_t  getSave(uint32_t save, uint32_t save_vs, bool base) = 0;
    virtual bool     isImmune(uint32_t type) = 0;
    virtual void     modifyDamageImmunity(uint32_t dmg_type, int32_t amount) = 0;
    
    virtual void     update() = 0;
};


#endif // _NWNX_COMBAT_DEFENSE_H_
