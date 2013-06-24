#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Func_GetABVersus(CGameObject *ob, char *value) {
    int32_t result = -1;
    Creature *c;
    uint32_t vs_id;
    int32_t equip_num;

    CGameObject *vs_ob;
    CNWSCreature *vs = NULL;

    if ( sscanf(value, "%d %x", &equip_num, &vs_id) == 2 &&
	 equip_num >= 0                                  &&
	 equip_num < 6                                   &&
	 ob->type == OBJECT_TYPE_CREATURE                &&
	 (c = combat.get_creature(ob->id)) != NULL) {
	
	result = c->offense.getAttackBonus(equip_num);
    }
    
    snprintf(value, strlen(value), "%d", result);    
}
