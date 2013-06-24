#include "NWNXEvents.h"

extern CNWNXEvents events;

int32_t Hook_SendServerToPlayerExamineGui_CreatureData(CNWSMessage *msg, CNWSPlayer *pl, nwn_objid_t obj){
    if (!events.scriptRun && pl){
        events.Log(2, "ExamineCreature: pPlayer=%08lX, oTarget=%08lX\n", pl->obj_id, obj);

        events.FireExamineEvent(msg, pl, obj, OBJECT_TYPE_CREATURE);
    }

    if(!events.examine_event.bypass)
        return CNWSMessage__SendServerToPlayerExamineGui_CreatureData(msg, pl, obj);
    else
	return events.examine_event.result;
}
