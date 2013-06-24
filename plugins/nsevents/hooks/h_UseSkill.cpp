#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_UseSkill(CNWSCreature *cre, uint8_t skill, uint8_t subskill, nwn_objid_t target, Vector vTarget, nwn_objid_t area, nwn_objid_t item, int arg_24){
    if (!events.scriptRun && cre){
        events.Log(2,
                   "UseSkill: pCreature=%08lX, oTarget=%08lX, oItem=%08lX, vTarget=%f/%f/%f, nSkill=%d\n",
                   cre->obj.obj_id, target, item, vTarget.x, vTarget.y, vTarget.z, skill);

        events.FireEvent(cre->obj.obj_id, EVENT_TYPE_USE_SKILL, skill, target, vTarget, item);
    }
    if(!events.event.bypass)
        CNWSCreature__UseSkill(cre, skill, subskill, target, vTarget, area, item, arg_24);
}
