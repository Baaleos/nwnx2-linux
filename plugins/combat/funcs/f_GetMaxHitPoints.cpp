#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Func_GetMaxHitPoints(CGameObject *ob, char *value) {
    int32_t result = 0;
    Creature *c;
    
    if ( ob->type == OBJECT_TYPE_CREATURE &&
         (c = combat.get_creature(ob->id)) != NULL) {
        result = c->defense.getHPMax();
    }
    snprintf(value, strlen(value), "%d", result);
}
