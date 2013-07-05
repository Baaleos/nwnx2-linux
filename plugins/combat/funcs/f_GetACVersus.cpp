#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Func_GetACVersus(CGameObject *ob, char *value) {
    int32_t result = -1;
    Creature *c;
    uint32_t vs_id;

    if ( ob->type == OBJECT_TYPE_DOOR ||
         ob->type == OBJECT_TYPE_PLACEABLE ) {
        SituatedObject obj(ob->vtable->AsNWSObject(ob));
        result = obj.getArmorClass(true, 0, false);
    }
    else if ( sscanf(value, "%x", &vs_id) == 1 &&
              (c = combat.get_creature(ob->id)) ) {
        result = c->defense.getArmorClass(true, 0, false);
    }
    
    snprintf(value, strlen(value), "%d", result);
}
