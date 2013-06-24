#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_UseItem(CNWSCreature *cre, nwn_objid_t item, uint8_t radial, uint8_t a, nwn_objid_t target, Vector loc, nwn_objid_t area){
    if (!events.scriptRun && cre){
        events.Log(2,
                   "UseItem: oPC=%08lX, oTarget=%08lX, oItem=%08lX, vTarget=%f/%f/%f, nRadial=%d\n",
                   cre->obj.obj_id, target, item, loc.x, loc.y, loc.z, radial);
        
        events.FireEvent(cre->obj.obj_id, EVENT_TYPE_USE_ITEM, radial, target, loc, item);
    }

    if(!events.event.bypass)
        CNWSCreature__UseItem(cre, item, radial, a, target, loc, area);
}

