#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Func_GetSkillRank(CGameObject *ob, char *value) {
    int32_t result = 0;
    Creature *c;

    int skill;
    uint32_t vs_id;
    int base;
    CGameObject *vs;

    if ( sscanf(value, "%d %x %d", &skill, &vs_id, &base) == 3 &&
         ob->type == OBJECT_TYPE_CREATURE                      &&
         (c = combat.get_creature(ob->id)) != NULL ) {
        
        vs = nwn_GetObjectByID(vs_id);
        result = c->getSkillRank(skill, 
                                 vs ? vs->vtable->AsNWSObject(vs) : NULL,
                                 !!base);
    }
    
    snprintf(value, strlen(value), "%d", result);
}
