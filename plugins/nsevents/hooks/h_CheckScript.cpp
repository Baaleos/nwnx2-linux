#include "NWNXEvents.h"

extern CNWNXEvents events;

static void local_CheckScript(CNWSDialog *dlg, CDialogNode *node){
    if (!events.scriptRun){
        events.pConversation = dlg;

        //Absolute index. But what is it? Entry, reply or starting entry?
        uint32_t nNodeID = node->index;
        events.nCurrentAbsoluteNodeID = nNodeID;
        events.Log(2, "ConditionalScript: nNodeID=%d\n", nNodeID);

        //Identify node type
        //uint32_t nCurrentNode = dlg->current_node;
        for(int i = 0; i < dlg->entries_len; i++){
            CDialogReply* pEntry = &dlg->entries[i].info;
            for(int j = 0; j < pEntry->nodes_len; j++){
                if(&pEntry->nodes[j] == node){
                    events.nNodeType = ReplyNode;
                    events.nCurrentNodeID = j;
                    events.Log(2, "Reply: %d\n", j);
                    return;
                }
            }
        }
        for(int i = 0; i < dlg->replies_len; i++){
            CDialogReply* pReply = &dlg->replies[i];
            for(int j = 0; j < pReply->nodes_len; j++){
                if(&pReply->nodes[j] == node){
                    events.nNodeType = EntryNode;
                    events.nCurrentNodeID = j;
                    events.Log(2, "Entry: %d\n", j);
                    return;
                }
            }
        }

        for(int i = 0; i < dlg->starts_len; i++){
            if(&dlg->starts[i] == node){
                events.nNodeType = StartingNode;
                events.nCurrentNodeID = i;
                events.Log(2, "Starting Entry: %d\n", i);
                return;
            }
        }
    }
}

int32_t Hook_CheckScript(CNWSDialog *dlg, CNWSObject *obj, const CResRef *resref){
    local_CheckScript(dlg, (CDialogNode *)resref);

    events.UpdateConversationScriptType(Conditional);
    int result = CNWSDialog__CheckScript(dlg, obj, resref);
    events.UpdateConversationScriptType(None);

    return result;
}
