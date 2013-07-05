#include "NWNXCombat.h"

extern CNWNXCombat combat;

int32_t Hook_DoDamageReduction(CNWSObject *obj, CNWSCreature *vs, int32_t amount,
                               uint8_t power, int32_t no_feedback, int32_t from_attack) {

    if ( !obj ) { return amount; }

    int32_t adj = 0;
    Creature *c;
    if ( obj->obj_type == OBJECT_TYPE_CREATURE &&
         (c = combat.get_creature(obj->obj_id)) ) {
        adj = c->defense.doDamageReduction(vs, amount, power, no_feedback);
    }
    else { 
        SituatedObject ob(obj);
        adj = ob.doDamageReduction(vs, amount, power, no_feedback);
    }

    return amount - adj;
}
