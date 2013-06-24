#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_SendServerToPlayerQuickChatMessage(CNWSMessage *msg, nwn_objid_t pc, uint16_t chat){
    if (!events.scriptRun && pc){
        events.Log(2, "QuickChat: oPC=%08lX, nChat=%d\n", pc, chat);

        events.FireEvent(pc, EVENT_TYPE_QUICKCHAT, chat);
    }
    if(!events.event.bypass)
        CNWSMessage__SendServerToPlayerQuickChatMessage(msg, pc, chat);
}
