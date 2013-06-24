#include "NWNXCombat.h"

extern CNWNXCombat combat;

CombatMod GetCombatModeMod(CNWSCreature *cre) {
    if ( !cre ) { return CombatMod(); }
    uint32_t mode = cre->cre_mode_combat;

    auto it = combat.modes.find(mode);
    if ( it != combat.modes.end() ) {
	return it->second;
    }

    switch (mode) {
    default: return CombatMod();
    case COMBAT_MODE_POWER_ATTACK:
	return CombatMod(-5, 0,
			 DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
				      DiceRoll(0, 0, 5),
				      false),
			 0);
    case COMBAT_MODE_IMPROVED_POWER_ATTACK:
	return CombatMod(-10, 0, 
			 DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
				      DiceRoll(0, 0, 10),
				      false),
			 0);
    case COMBAT_MODE_FLURRY_OF_BLOWS:
	return CombatMod(-2, 0, DamageAmount(), 0);
    case COMBAT_MODE_RAPID_SHOT:
	return CombatMod(-2, 0, DamageAmount(), 0);
    case COMBAT_MODE_EXPERTISE:
	return CombatMod(-5, 5, DamageAmount(), 0);
    case COMBAT_MODE_IMPROVED_EXPERTISE:
	return CombatMod(-10, 10, DamageAmount(), 0);
    case COMBAT_MODE_DIRTY_FIGHTING:
	return CombatMod(0, 0,
			 DamageAmount(DAMAGE_INDEX_BASE_WEAPON,
				      DiceRoll(1, 4, 0), 
				      false),
			 0);
    }
}
