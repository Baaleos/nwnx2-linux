#include "NWNXEvents.h"

extern CNWNXEvents events;

int Hook_ValidateCharacter(CNWSPlayer *pl, int *res){
    int result = 0;

    if (!events.scriptRun && pl){
        events.FireEvent(pl->obj_id, EVENT_TYPE_VALIDATE_CHARACTER);
    }

    if (!events.event.bypass)
        result = CNWSPlayer__ValidateCharacter(pl, res);
    else if(events.event.use_result){
        if (events.event.use_result){
            *res = 0;
            result = events.event.result;
        }
        else {
            *res = 1;
            result = 0;
        }
    }

    return result;
}
