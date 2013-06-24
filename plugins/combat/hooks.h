#ifndef NWNX_COMBAT_HOOKS_H
#define NWNX_COMBAT_HOOKS_H

extern int (*CNWSCreature__EquipItem_orig)(CNWSCreature *, uint32_t, CNWSItem *, int32_t, int32_t);
extern int (*CNWSCreature__UnequipItem_orig)(CNWSCreature *, CNWSItem *, int32_t);
extern int (*CNWSCreatureStats__GetSkillRank_orig)(CNWSCreatureStats *, uint8_t, CNWSObject *, int32_t);

void    Hook_AddAttackOfOpportunity(CNWSCombatRound *cr, uint32_t target);
uint8_t Hook_GetCreatureDamageDice(CNWSCreatureStats *stats, uint8_t n);
uint8_t Hook_GetCreatureDamageDie(CNWSCreatureStats *stats, uint8_t n);
int32_t Hook_DoDamageImmunity(CNWSObject *obj, CNWSCreature *vs, int32_t amount,
			      uint16_t flags, int32_t no_feedback, int32_t from_attack);
int32_t Hook_DoDamageReduction(CNWSObject *obj, CNWSCreature *vs, int32_t amount,
			       uint8_t power, int32_t no_feedback, int32_t from_attack);
int32_t Hook_DoDamageResistance(CNWSObject *obj, CNWSCreature *vs, int32_t amount,
				uint16_t flags, int32_t no_feedback, int32_t from_attack,
				int32_t a);

int32_t Hook_EquipItem(CNWSCreature *, uint32_t, CNWSItem *, int32_t, int32_t);
int32_t Hook_ExecuteCommandDoTouchAttack(CNWVirtualMachineCommands *vmc, int32_t cmd, int32_t args);
int32_t Hook_UnequipItem(CNWSCreature *, CNWSItem *, int32_t);

int32_t Hook_GetArmorClass(CNWSCreature *cre);
int32_t Hook_GetArmorClassVersus(CNWSCreatureStats *target, CNWSCreature *attacker, int32_t touch);
int     Hook_GetCriticalHitMultiplier(CNWSCreatureStats *attacker, int offhand);
int     Hook_GetCriticalHitRoll(CNWSCreatureStats *attacker, int offhand);
int32_t Hook_GetEffectImmunity(CNWSCreatureStats *stats, uint8_t type, CNWSCreature *versus);
void    Hook_GetIsFlurryWeapon();
int     Hook_GetMaxHitpoints (CNWSCreature *cre, int32_t dunno);
int32_t Hook_GetMeleeAttackBonus(CNWSCreatureStats *stats, int offhand, int a, int touch);
int32_t Hook_GetMeleeDamageBonus(CNWSCreatureStats *stats, int32_t offhand, uint8_t a);
int32_t Hook_GetRangedAttackBonus(CNWSCreatureStats *stats, int32_t use_base_eff, int32_t use_dex);
int32_t Hook_GetRangedDamageBonus(CNWSCreatureStats *stats);
int8_t  Hook_GetSkillRank(CNWSCreatureStats *stats, uint8_t skill, CNWSObject *vs, int32_t a);
int32_t Hook_GetTotalEffectBonus(CNWSCreature *cre, uint8_t eff_switch , CNWSObject *vs, int elemental,
				 int32_t is_crit, uint8_t save, uint8_t save_vs, uint8_t skill, uint8_t ability,
				 int32_t is_offhand);
uint8_t Hook_GetUnarmedDamageDice(CNWSCreatureStats *stats);
uint8_t Hook_GetUnarmedDamageDie(CNWSCreatureStats *stats);
int32_t Hook_GetWeaponAttackType(CNWSCombatRound *cr);
bool    Hook_GetWeaponFinesse(CNWSCreatureStats *stats, CNWSItem *item);
void    Hook_InitializeNumberOfAttacks(CNWSCombatRound *combat_round);
void    Hook_ResolveDamageShields(CNWSCreature *target, CNWSCreature *attacker);
void    Hook_ResolveMeleeAttack(CNWSCreature *attacker, CNWSObject *obj, int attack_count, int a);
void    Hook_ResolveRangedAttack(CNWSCreature *attacker, CNWSObject *obj, int attack_count, int a);
int32_t Hook_SavingThrowRoll(CNWSCreature *cre, uint8_t save_type, uint16_t dc, uint8_t save_vs_type, 
			     uint32_t versus_id, int32_t send_feedback, uint16_t feat, int32_t from_combat);

#endif // NWNX_COMBAT_HOOKS_H
