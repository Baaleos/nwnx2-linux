#include "NWNXEvents.h"

extern CNWNXEvents events;

int32_t Hook_SendServerToPlayerExamineGui_DoorData(CNWSMessage *msg, CNWSPlayer *pl, nwn_objid_t obj){
    if (!events.scriptRun && pl){
        events.Log(2, "ExamineDoor: pPlayer=%08lX, oTarget=%08lX\n", pl->obj_id, obj);

        events.FireExamineEvent(msg, pl, obj, OBJECT_TYPE_DOOR);
    }

    if(!events.examine_event.bypass)
        return CNWSMessage__SendServerToPlayerExamineGui_DoorData(msg, pl, obj);
    else
	return events.examine_event.result;
}
