#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Func_GetCombatInfo(CGameObject *ob, char *value) {
    auto c = combat.get_creature(ob->id);
    if ( c ) {
	nwn_SendMessage(5, ob->id, c->toString().c_str(), ob->id);
    }
}
