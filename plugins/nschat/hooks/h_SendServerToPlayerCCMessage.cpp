#include "NWNXChat.h"

extern CNWNXChat chat;

void Hook_SendServerToPlayerCCMessage(CNWSMessage *msg, uint32_t id, int8_t msg_id, CNWCCMessageData *msg_data, CNWSCombatAttackData *attack){
    CNWSPlayer *pl = nwn_GetPlayerByPlayerID(id);
    bool suppress = false;

    if(pl){
        nwn_objid_t obj = pl->obj_id;
        int subtype = msg_id == 11 ? msg_data->integers.data[9] : 0;
        chat.Log(3, "PlayerID=%d, ObjectID=%08lX, MessageID=%d, MessageSubID=%d\n", id, obj, msg_id, subtype);
        if (!chat.scriptRun)
            suppress = chat.CCMessage(obj, msg_id, subtype, msg_data);
    }

    if (!suppress)
        CNWSMessage__SendServerToPlayerCCMessage(msg, id, msg_id, msg_data, attack);
}
