#ifndef NWNX_COMBAT_ATTACK_H
#define NWNX_COMBAT_ATTACK_H

#include "NWNXCombat.h"
#include "talib/accumulator/all.h"
#include "combat/Offense.h"
#include "combat/Defense.h"
#include "combat/AttackData.h"
#include "object/Creature.h"
#include "object/SituatedObject.h"

#include <tuple>

extern CNWNXCombat combat;

class Attack {
public:
    Attack(CNWSCreature *attacker, CNWSObject *target);
    ~Attack() { }

    // Public interface exposed in combat.h
    bool     isHit();
    bool     isSpecialAttack();
    void     resolveAttackRoll();
    void     resolveDamage();
    uint32_t resolveAmmo(uint32_t attack_count,
			 bool force_equip = false);
    void     resolveNoAmmo();
    void     resolvePostDamage(bool is_ranged);
    void     resolvePreRoll();
    void     resolveSituations();
    void     resolveSpecialAttack();
    void     resolveTargetState();
    void     update();

private:
    uint32_t     resolveArmorClass();
    void         resolveCriticalRoll(int32_t ab, int32_t roll,
				     int32_t ac);
    bool         resolveDeflectArrow();
    void         resolveItemCastspell();
    bool         resolveMissOpportunities();
    bool         resolveMissChance();
    void         resolveOnhitEffect();

    bool         resolveParry();
    int32_t      resolveSpecialAttackAttackBonus();
    DamageAmount resolveSpecialAttackDamage();
    bool         resolveSpecialAttackImpact();

    uint32_t     getIterationPenalty();
    bool         isOffhand();


    Creature         *attacker_;
    Creature         *target_;
    Defense          *defense;
    CNWSCreature     *attacker_nwn;
    CNWSObject       *target_nwn;
    SituatedObject    object_;
    VersusInfo        vs_;
    bool              target_is_cre;

    AttackData        attack_data_;

    uint32_t          target_state_;
    uint32_t          situ_flags_;
    uint32_t          equip_num_;
    float             target_distance_;

    int32_t           damage_total_;
    bool              is_crit_immune_;
};

inline bool Attack::isHit() { 
    return attack_data_.isHit();
}

inline bool Attack::isSpecialAttack() { 
    return attack_data_.isSpecialAttack();
}

#endif // NWNX_COMBAT_ATTACK_H
