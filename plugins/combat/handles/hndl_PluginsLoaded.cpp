#include "NWNXCombat.h"

extern CNWNXCombat combat;

int Handle_PluginsLoaded(WPARAM p, LPARAM a) {
    if ( !combat.InitializeEventHandlers() ) {
        combat.Log(0, "ERROR: Unable to initialize NWNX event handlers!");
    }
    return 0;
}
