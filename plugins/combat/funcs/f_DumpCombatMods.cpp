
#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Func_DumpCombatMods(CGameObject *ob, char *value) {
    if ( ob->type != OBJECT_TYPE_CREATURE ) { return; }

    auto c = combat.get_creature(ob->id);
    if ( c ) {
	c->log();
    }
}
