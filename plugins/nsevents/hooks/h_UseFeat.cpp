#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_UseFeat(CNWSCreature *cre, uint16_t feat, uint16_t subfeat, nwn_objid_t target, nwn_objid_t area, Vector *loc){
    Vector v = {0,0,0};
    if (loc){
        v.x = loc->x;
        v.y = loc->y;
        v.z = loc->z;
    }

    if (!events.scriptRun && cre){
        events.Log(2,
                   "UseFeat: pCreature=%08lX, oTarget=%08lX, vTarget=%f/%f/%f, nFeat=%d\n",
                   cre->obj.obj_id, target, v.x, v.y, v.z, feat);
        
        events.FireEvent(cre->obj.obj_id, EVENT_TYPE_USE_FEAT, feat, target, v);
    }

    if(!events.event.bypass){
	events.Log(2, "UseFeat: Call Original\n");
        CNWSCreature__UseFeat(cre, feat, subfeat, target, area, loc);
    }
}
