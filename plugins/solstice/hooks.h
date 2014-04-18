#pragma once

int     Hook_RunScriptStart(CVirtualMachine *vm, CExoString *script, nwn_objid_t id, int a);
void    Hook_RunScriptEnd();
void    Hook_RunScriptSituationStart(CVirtualMachine *vm, void *script, uint32_t obj, int a);
void    Hook_RunScriptSituationEnd();
int8_t  Hook_GetFeatRemainingUses(CNWSCreatureStats *stats, uint16_t feat);
int8_t  Hook_GetFeatTotalUses(CNWSCreatureStats *stats, uint16_t feat);
int     Hook_GetMaxHitpoints (CNWSCreature  *cre, int32_t dunno);
int     Hook_GetRunScriptReturnValue(CVirtualMachine *vm, int* a, void** b);
void    Hook_SetCombatMode(CNWSCreature *cre, int8_t mode, int32_t change);
int32_t Hook_ToggleMode(CNWSCreature *cre, uint8_t mode);
void    Hook_AddAttackOfOpportunity(CNWSCombatRound *cr, uint32_t target);
int32_t Hook_LoadModuleStart(CNWSModule *mod, void *a2);
void    Hook_ResolveRangedAttack(CNWSCreature *attacker, CNWSObject *obj, int attack_count, int a);
void    Hook_ResolveMeleeAttack(CNWSCreature *attacker, CNWSObject *obj, int attack_count, int a);
void    Hook_InitializeNumberOfAttacks(CNWSCombatRound *combat_round);
void    Hook_UpdateCombatInformation(CNWSCreatureStats *cre);

int32_t Hook_OnApplyDamageImmunityDecrease(CNWSEffectListHandler *, CNWSObject *, CGameEffect *, int32_t);
int32_t Hook_OnApplyDamageImmunityIncrease(CNWSEffectListHandler *, CNWSObject *, CGameEffect *, int32_t);
int32_t Hook_OnRemoveDamageImmunityIncrease(CNWSEffectListHandler *, CNWSObject *, CGameEffect *);
int32_t Hook_OnRemoveDamageImmunityDecrease(CNWSEffectListHandler *, CNWSObject *, CGameEffect *);

extern int (*CNWSModule__LoadModuleStartNext)(CNWSModule *mod, void *a2);
extern int (*CNWSCreatureStats__GetSkillRank_orig)(CNWSCreatureStats *, uint8_t, CNWSObject *, int32_t);
extern void (*CNWSCreature__ResolveRangedAttack_orig)(CNWSCreature *, CNWSObject *, int, int);
extern void (*CNWSCreature__ResolveMeleeAttack_orig)(CNWSCreature *, CNWSObject *, int, int);
extern void (*CNWSCreatureStats__UpdateCombatInformation_orig)(CNWSCreatureStats *);

extern int (*CNWSEffectListHandler__OnApplyDamageImmunityDecrease_orig)(CNWSEffectListHandler *, CNWSObject *, CGameEffect *, int32_t);
extern int (*CNWSEffectListHandler__OnApplyDamageImmunityIncrease_orig)(CNWSEffectListHandler *, CNWSObject *, CGameEffect *, int32_t);
extern int (*CNWSEffectListHandler__OnRemoveDamageImmunityIncrease_orig)(CNWSEffectListHandler *, CNWSObject *, CGameEffect *);
extern int (*CNWSEffectListHandler__OnRemoveDamageImmunityDecrease_orig)(CNWSEffectListHandler *, CNWSObject *, CGameEffect *);