#include "NWNXChat.h"

extern CNWNXChat chat;

void Hook_SendServerToPlayerChatMessage(CNWSMessage *msg, int8_t mode, uint32_t from, const char ** text, uint32_t to, const char *xz){
    const char *mesg = *text;
    chat.Log(0, "o CHAT: mode=%lX, from_oID=%08lX, msg='%s', to_ID=%08lX, %d\n", mode, from, mesg, to, chat.scriptRun);
    bool suppress = false;
    if (!chat.scriptRun)
        suppress = chat.Chat(mode, from, mesg, to);
        
    if(!suppress) {
        CNWSMessage__SendServerToPlayerChatMessage(msg, mode, from, text, to, xz);
    }
}
