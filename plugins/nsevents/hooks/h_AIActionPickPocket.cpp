#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_AIActionPickPocket(CNWSCreature* cre, CNWSObjectActionNode *node){
    if (!events.scriptRun && cre){
        nwn_objid_t target = (nwn_objid_t)node->param[0];
        Vector v;

        events.Log(2, "AIActionPickPocket: Thief: %08lX, Target: %08lX\n",
                   cre->obj.obj_id, target);

        events.FireEvent(cre->obj.obj_id, EVENT_TYPE_PICKPOCKET, -1, target, v);

    }
    if(!events.event.bypass)
        CNWSCreature__AIActionPickPocket(cre, (CNWSAction*)node);
}
