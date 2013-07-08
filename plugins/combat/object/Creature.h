#ifndef NWNX_COMBAT_OBJECT_CREATURE_H
#define NWNX_COMBAT_OBJECT_CREATURE_H

#include "NWNXLib.h"
#include "talib/all.h"
#include "talib/accumulator/all.h"

#include "CreatureOffense.h"
#include "CreatureDefense.h"

class Creature {
    friend class CreatureOffense;
    friend class CreatureDefense;
    
public:
    Creature (CNWSCreature *cre)
        : offense(this, cre),
          defense(this, cre),
          mod_favored_enemy(),
          mod_situ(),
          mod_training_vs(),
          fe_mask(0),
          training_vs_mask(0),
          original_(cre),
          mode_() {
    }
    ~Creature() {}

    int8_t       getAbilityEffectModifier(uint8_t ability);
    int8_t       getAbilityModifier(uint8_t type, bool armor_check = false);
    bool         hasTrainingVersus(uint16_t race);
    void         modifiyAbilityEffect(uint8_t ability, int32_t amount);
    CombatMod   *getSituationalModifier(uint32_t index);

    int8_t       getSkillRank(uint8_t skill, CNWSObject *vs, bool base);
    int8_t       getSkillEffectModifier(uint8_t skill);
    void         modifiySkillEffect(uint8_t skill, int32_t amount);

    void         setParent(Creature *parent, CNWSCreature *cre) {
        original_ = cre;
        offense.parent_ = parent;
        defense.parent_ = parent;
        offense.original_ = cre;
        defense.original_ = cre;
    }
    void         log();
    std::string  toString();
    void         update();
    void         updateMode();

    CreatureOffense offense;
    CreatureDefense defense;
    CombatMod       mod_favored_enemy;
    CombatMod       mod_situ[COMBAT_SITUATION_NUM];
    CombatMod       mod_training_vs;
    int16_t         skill_eff[SKILL_LAST+1];
    int16_t         ability_eff[6];

private:
    std::string  getCombatModString();

    uint32_t      effective_level;    
    uint64_t      fe_mask; // favored enemy mask
    uint64_t      training_vs_mask; // Training vs race
    CNWSCreature *original_;
    CombatMod     mode_;
};

inline int8_t Creature::getAbilityModifier(uint8_t type, bool armor_check) {
    return nwn_GetAbilityModifier(original_->cre_stats,
                                  type, armor_check);
}

#endif // NWNX_COMBAT_OBJECT_CREATURE_H
