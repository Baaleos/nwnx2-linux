#include "NWNXEvents.h"

extern CNWNXEvents events;
volatile uint32_t buffer;

void Hook_AddAttackActions(CNWSCreature *cre, nwn_objid_t target, int a, int clear_actions, int attack_now){
    Vector v;

    if (!events.scriptRun){
        asm("mov 0x1C(%ebp), %eax");
        asm("mov %eax, buffer");

        events.Log(2,
                   "Attack: oPC=%08lX, oTarget=%08lX, nSubID=%d\n",
                   cre->obj.obj_id, target, buffer);

        events.FireEvent(cre->obj.obj_id, EVENT_TYPE_ATTACK, buffer, target, v);
    }

    if(!events.event.bypass)
        CNWSCreature__AddAttackActions(cre, target, a, clear_actions, attack_now);
}
