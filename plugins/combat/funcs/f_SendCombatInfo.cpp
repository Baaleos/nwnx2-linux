#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Func_SendCombatInfo(CGameObject *ob, char *value) {
    uint32_t flags = 0;
    auto c = combat.get_creature(ob->id);
    if ( c && sscanf(value, "%d", &flags) == 1 &&
         flags != NWNX_COMBAT_INFO_INVALID ) {

        std::string out = c->defense.getString(flags);
        if ( !out.empty() ) {
            nwn_SendMessage(5, ob->id, out.c_str(), ob->id);
        }

        out = c->offense.getString(flags);
        if ( !out.empty() ) {
            nwn_SendMessage(5, ob->id, out.c_str(), ob->id);
        }

        out = c->toString();
        if ( !out.empty() ) {
            nwn_SendMessage(5, ob->id, out.c_str(), ob->id);
        }

    }
}
