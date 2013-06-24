#include "NWNXChat.h"

extern CNWNXChat chat;

void Hook_SendServerToPlayerChat_Talk(CNWSMessage *msg, uint32_t x, uint32_t y, CExoString text){
    CNWSCreature *cre1 = nwn_GetCreatureByID(x);
    CNWSCreature *cre2 = nwn_GetCreatureByID(y);
    
    // Always send the message if both objects aren't PCs send the message.
    if (cre1 && cre1->cre_is_pc && cre2 && cre2->cre_is_pc
        && !Local_GetIsIgnoring(x, y, 2)){
	CNWSMessage__SendServerToPlayerChat_Talk(msg, x, y, text);
    }
    else {
	CNWSMessage__SendServerToPlayerChat_Talk(msg, x, y, text);
    }
}
