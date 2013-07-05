/***************************************************************************
    Copyright (C) 2011-2012 jmd ( jmd2028 at gmail dot com )

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
***************************************************************************/

#include "NWNXCombat.h"

int (*CNWSCreature__EquipItem_orig)(CNWSCreature *, uint32_t, CNWSItem *, int32_t, int32_t) = NULL;
int (*CNWSCreature__UnequipItem_orig)(CNWSCreature *, CNWSItem *, int32_t) = NULL;

int (*CNWSCreatureStats__GetSkillRank_orig)(CNWSCreatureStats *, uint8_t, CNWSObject *, int32_t) = NULL;


#define HOOK(orig, addr, hook, bytes)                                   \
    *(void**)&orig = nx_hook_function((void*)addr, (void*)hook, bytes, NX_HOOK_DIRECT | NX_HOOK_RETCODE)
    

bool hook_functions(){

    HOOK(CNWSCombatRound__AddAttackOfOpportunity, 0x080E31E0, Hook_AddAttackOfOpportunity, 5);
    HOOK(CNWSCombatRound__InitializeNumberOfAttacks, 0x080E2260, Hook_InitializeNumberOfAttacks, 5);

    //HOOK(CNWSCreature__GetMaxHitPoints, 0x0812E25C, Hook_GetMaxHitpoints, 5);
    HOOK(CNWSCreature__ResolveMeleeAttack, 0x080E9930, Hook_ResolveMeleeAttack, 5);
    HOOK(CNWSCreature__ResolveRangedAttack, 0x080E6FE4, Hook_ResolveRangedAttack, 5);

    //HOOK(CNWSCreature__SavingThrowRoll, 0x080F0A90, Hook_SavingThrowRoll, 5);

    HOOK(CNWSCreature__EquipItem_orig, 0x0811b64c, Hook_EquipItem, 5);
    HOOK(CNWSCreature__UnequipItem_orig, 0x0811b7b0, Hook_UnequipItem, 5);

    //HOOK(CNWSCreatureStats__GetArmorClassVersus, 0x0814088C, Hook_GetArmorClassVersus, 5);
    HOOK(CNWSCreatureStats__GetCriticalHitRoll, 0x0814C31C, Hook_GetCriticalHitRoll, 5);
    HOOK(CNWSCreatureStats__GetCriticalHitMultiplier, 0x0814C4A0, Hook_GetCriticalHitMultiplier, 5);
    //HOOK(CNWSCreatureStats__GetSkillRank_orig, 0x08152F5C, Hook_GetSkillRank, 5);
    //HOOK(CNWSCreatureStats__GetWeaponFinesse, 0x08155CF4, Hook_GetWeaponFinesse, 5);

    HOOK(CNWSObject__DoDamageImmunity, 0x081CDC4C, Hook_DoDamageImmunity, 5);
    HOOK(CNWSObject__DoDamageReduction, 0x081CBD74, Hook_DoDamageReduction, 5);
    HOOK(CNWSObject__DoDamageResistance, 0x081CC7BC, Hook_DoDamageResistance, 5);

    HOOK(CNWSCreature__GetTotalEffectBonus, 0x08132298, Hook_GetTotalEffectBonus, 5);
    HOOK(CNWSCreatureStats__GetEffectImmunity, 0x0815FF10, Hook_GetEffectImmunity, 5);

    nx_hook_function((void*)0x0812e19c,
                     (void*)Hook_GetArmorClass,
                     5, NX_HOOK_DIRECT);
    
    nx_hook_function((void*)0x0814425c,
                     (void*)Hook_GetCreatureDamageDice,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x08144368,
                     (void*)Hook_GetCreatureDamageDie,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x08143068,
                     (void*)Hook_GetMeleeAttackBonus,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x08143524,
                     (void*)Hook_GetMeleeDamageBonus,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x0814374c,
                     (void*)Hook_GetRangedAttackBonus,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x08143b5c,
                     (void*)Hook_GetRangedDamageBonus,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x08143f88,
                     (void*)Hook_GetUnarmedDamageDice,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x0814408c,
                     (void*)Hook_GetUnarmedDamageDie,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x080e38dc,
                     (void*)Hook_GetWeaponAttackType,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x08209e94,
                     (void*)Hook_ExecuteCommandDoTouchAttack,
                     5, NX_HOOK_DIRECT);

    nx_hook_function((void*)0x080efcac,
                     (void*)Hook_ResolveDamageShields,
                     5, NX_HOOK_DIRECT);

    // Look into cleaning this up...
    unsigned char *pDM = (unsigned char *)0x080f9965;
    nx_hook_enable_write(pDM, 24);
    memcpy(pDM, "\xe9\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90\x90", 24); //JMP NOP NOP ...
    *((uint32_t *)(pDM + 1)) = (uint32_t)Hook_GetIsFlurryWeapon - (uint32_t)(pDM + 5);

    return true;
}
