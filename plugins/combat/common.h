#ifndef NWNX_COMBAT_COMMON_H
#define NWNX_COMBAT_COMMON_H

typedef std::pair<int32_t, int32_t> Int32Pair;

std::pair<bool, int32_t> CanUseClassAbilities(CNWSCreature *cre, uint32_t cls);
uint32_t     GetAttackTypeFromEquipNum(uint32_t equip_num);
ArmorClass   GetBaseArmorClass(CNWSCreature *cre);
int          GetBaseConcealment(CNWSCreature *cre);
uint16_t     GetBaseSoak(CNWSCreature *cre);
CombatMod    GetCombatModFavoredEnemy(CNWSCreature *cre);
CombatMod    GetCombatModeMod(CNWSCreature *cre);
CombatMod    GetCombatModSituation(CNWSCreature *cre, uint32_t situ);
Int32Pair    GetDualWieldPenalty(CNWSCreature *cre);
uint32_t     GetEquipNumFromAttackType(uint32_t type);
uint64_t     GetFavoredEnemyMask(CNWSCreature *cre);
int32_t      GetIsMonkWeapon(CNWSItem *it);
bool         GetIsRangedWeapon(CNWSItem *it);
bool         GetIsUnarmedWeapon(CNWSItem *it);
bool         GetIsWeaponLight(CNWSCreature *cre, CNWSItem *it, bool finesse);
bool         GetIsWeaponIntuitable(CNWSCreature *cre, CNWSItem *it);
bool         GetIsWeaponKensei(CNWSCreature *cre, CNWSItem *it);
int32_t      GetSavingThrowVsBonus(CNWSCreature *cre, CNWSObject *vs,
                                   int32_t save, int32_t save_vs);
int8_t       GetSkillFeatBonus(CNWSCreature *cre, uint8_t skill);
uint32_t     GetTargetState(CNWSCreature *attacker, CNWSObject *target);
uint64_t     GetTrainingVsMask(CNWSCreature *cre);
DamageAmount GetUnarmedBaseDamage(CNWSCreature *cre, CNWSItem *it);
uint32_t     GetWeaponAttackAbility(CNWSCreature *cre, CNWSItem *it);
uint32_t     GetWeaponDamageAbility(CNWSCreature *cre, CNWSItem *it);
void         GetWeaponAmmoDamage(CNWSCreature *cre, uint32_t weapon_type,
                                 std::vector<DamageAmount>& dmgs);
uint32_t     GetWeaponAttackBonus(CNWSCreature *cre, CNWSItem *it);
DamageAmount GetWeaponBaseDamage(CNWSCreature *cre, CNWSItem *it);
void         GetWeaponCritDamage(CNWSCreature *cre, CNWSItem *it,
                                 std::vector<DamageAmount>& dmgs);
std::pair<uint32_t, bool> GetWeaponCritMultiplier(CNWSCreature *cre, CNWSItem *it);
uint32_t     GetWeaponCritRange(CNWSCreature *cre, CNWSItem *it);
void         GetWeaponDamage(CNWSCreature *cre, CNWSItem *it,
                             std::vector<DamageAmount>& dmgs);
uint32_t     GetWeaponIteration(CNWSCreature *cre, CNWSItem *it);
uint32_t     GetWeaponPower(CNWSCreature *cre, CNWSItem *it);

#endif // NWNX_COMBAT_COMMON_H
