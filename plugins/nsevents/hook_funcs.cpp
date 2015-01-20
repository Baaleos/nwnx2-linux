#include "NWNXEvents.h"

#define HOOK(orig, addr, hook, bytes) \
    *(void**)&orig = nx_hook_function((void*)addr, (void*)hook, bytes, NX_HOOK_DIRECT | NX_HOOK_RETCODE)

bool hook_functions(){
    //CServerExoAppInternal::SetPauseState(unsigned char, int)
    HOOK(CServerExoAppInternal__SetPauseState, 0x80adb18, Hook_SetPauseState, 5);

    HOOK(CNWSCreature__AddAttackActions, 0x081188D4, Hook_AddAttackActions, 5);
    //HOOK(CNWSCreature__AddCastSpellActions, 0x0811610C, Hook_AddCastSpellActions, 5);
    HOOK(CNWSCreature__AIActionPickPocket, 0x0810A3F4, Hook_AIActionPickPocket, 5);
    //HOOK(CNWSCreature__PossessFamiliar, 0x0810DE48, Hook_PossessFamiliar, 7);
    HOOK(CNWSObject_dtor, 0x081C8E94, Hook_CNWSObject_dtor, 5);

    HOOK(CNWSCreature__UseFeat, 0x0812A004, Hook_UseFeat, 5);
    HOOK(CNWSCreature__UseItem, 0x0812B958, Hook_UseItem, 9);
    HOOK(CNWSCreature__UseSkill, 0x0812B1D8, Hook_UseSkill, 5);

    HOOK(CNWSDialog__CheckScript, 0x0823CAB0, Hook_CheckScript, 8);
    HOOK(CNWSDialog__HandleReply, 0x0823DEAC, Hook_HandleReply, 5);

    HOOK(CNWSMessage__SendServerToPlayerExamineGui_CreatureData, 0x8073958, Hook_SendServerToPlayerExamineGui_CreatureData, 5);
    HOOK(CNWSMessage__SendServerToPlayerExamineGui_DoorData, 0x8074c50, Hook_SendServerToPlayerExamineGui_DoorData, 5);
    HOOK(CNWSMessage__SendServerToPlayerExamineGui_ItemData, 0x8073f9c, Hook_SendServerToPlayerExamineGui_ItemData, 5);
    HOOK(CNWSMessage__SendServerToPlayerExamineGui_PlaceableData, 0x8074668, Hook_SendServerToPlayerExamineGui_PlaceableData, 5);
    HOOK(CNWSMessage__SendServerToPlayerQuickChatMessage, 0x8068dec, Hook_SendServerToPlayerQuickChatMessage, 5);

    HOOK(CNWSPlayer__PackCreatureIntoMessage, 0x805D910, Hook_PackCreatureIntoMessage, 5);
    HOOK(CNWSPlayer__ValidateCharacter, 0x80580bc, Hook_ValidateCharacter, 5);

    HOOK(CNWVirtualMachineCommands__ReportError, 0x081FB68C, Hook_ReportError, 5);

    nx_hook_function((void *)0x081125b4,
		     (void *)Hook_SetPVPPlayerLikesMe, 5, NX_HOOK_DIRECT);


    return true;
}
