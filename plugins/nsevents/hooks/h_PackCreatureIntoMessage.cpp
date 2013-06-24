#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_PackCreatureIntoMessage(CNWSPlayer *pl){
    if (!events.scriptRun && pl){
        events.FireEvent(pl->obj_id, EVENT_TYPE_SAVE_CHAR);
    }
    CNWSPlayer__PackCreatureIntoMessage(pl);
}
