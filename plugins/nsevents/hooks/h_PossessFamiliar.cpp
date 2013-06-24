#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_PossessFamiliar(CNWSCreature *cre) {
    if (!events.scriptRun && cre){
        events.FireEvent(cre->obj.obj_id , EVENT_TYPE_POSSESS_FAMILIAR);
    }

    if(!events.event.bypass)
        CNWSCreature__PossessFamiliar(cre);
}
