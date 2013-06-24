#include "NWNXChat.h"

#define HOOK(orig, addr, hook, bytes) \
    *(void**)&orig = nx_hook_function((void*)addr, (void*)hook, bytes, NX_HOOK_DIRECT | NX_HOOK_RETCODE)

bool hook_functions(){
    /*
    *(void**)&CNWSMessage__SendServerToPlayerChat_Party = 
        nx_hook_function((void*)0x08068FE4,
                         (void*)Hook_SendServerToPlayerChat_Party,
                         5,
                         NX_HOOK_DIRECT | NX_HOOK_RETCODE);
    
    *(void**)&CNWSMessage__SendServerToPlayerChat_Shout = 
        nx_hook_function((void*)0x08069710,
                         (void*)Hook_SendServerToPlayerChat_Shout,
                         5,
                         NX_HOOK_DIRECT | NX_HOOK_RETCODE);

    *(void**)&CNWSMessage__SendServerToPlayerChat_Talk =
        nx_hook_function((void*)0x0807FE10,
                         (void*)Hook_SendServerToPlayerChat_Talk,
                         5,
                         NX_HOOK_DIRECT | NX_HOOK_RETCODE);
    
    *(void**)&CNWSMessage__SendServerToPlayerChat_Tell = 
        nx_hook_function((void*)0x080694AC,
                         (void*)Hook_SendServerToPlayerChat_Tell,
                         5,
                         NX_HOOK_DIRECT | NX_HOOK_RETCODE);
    */

    HOOK(CNWSMessage__SendServerToPlayerCCMessage, 0x08078AB8, Hook_SendServerToPlayerCCMessage, 5);
    HOOK(CNWSMessage__SendServerToPlayerChatMessage, 0x0806839C, Hook_SendServerToPlayerChatMessage, 5);

    return true;
}
