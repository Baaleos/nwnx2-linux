#include "NWNXEvents.h"

extern CNWNXEvents events;

static void local_handle_reply(CNWSDialog *dlg, uint32_t node_id){
    events.pConversation = dlg;
    //Get SelectedNode
    events.nSelectedNodeID = node_id;
    events.nSelectedAbsoluteNodeID = -1;
     
    int32_t current_node = dlg->current_node;
    if(dlg->entries_len > current_node){
        CDialogReply *pCurrentNode = &dlg->entries[current_node].info;
        if(pCurrentNode->nodes_len > node_id){
            CDialogNode *pCurrentReply = &pCurrentNode->nodes[node_id];
	    if ( pCurrentReply ) {
		events.nSelectedAbsoluteNodeID = pCurrentReply->index;
	    }
        }
    }
    events.Log(2, "ConversationNodeSelect: nSelectedNode=%d, nAbsSelectedNode=%d\n",
	       events.nSelectedNodeID,
	       events.nSelectedAbsoluteNodeID);	
}

int32_t Hook_HandleReply(CNWSDialog *dlg, uint32_t a, CNWSObject *obj, uint32_t node_id, int c, uint32_t d){
    local_handle_reply(dlg, node_id);

    events.UpdateConversationScriptType(Action);
    int32_t result = CNWSDialog__HandleReply(dlg, a, obj, node_id, c, d);
    events.UpdateConversationScriptType(None);

    return result;
}
