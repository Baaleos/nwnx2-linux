#include "NWNXCombat.h"

extern CNWNXCombat combat;

int Handle_Event(WPARAM p, LPARAM a) {
    Event *ev = reinterpret_cast<Event*>(p);
    if ( ev && ev->type == 14 ) {
        combat.removeCreature(ev->object);
    }
    return 0;
}
