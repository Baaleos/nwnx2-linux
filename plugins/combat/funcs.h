#ifndef NWNX_COMBAT_FUNCS_H
#define NWNX_COMBAT_FUNCS_H

void Func_DumpCombatMods(CGameObject *ob, char *value);
void Func_GetABVersus(CGameObject *ob, char *value);
void Func_GetACVersus(CGameObject *ob, char *value);
void Func_GetCombatInfo(CGameObject *ob, char *value);
void Func_GetMaxHitPoints(CGameObject *ob, char *value);
void Func_GetSkillRank(CGameObject *ob, char *value);
void Func_RegisterModeCombatMod(CGameObject *ob, char *value);
void Func_SendCombatInfo(CGameObject *ob, char *value);
void Func_SendDamageRoll(CGameObject *ob, char *value);

#endif // NWNX_COMBAT_FUNCS_H
