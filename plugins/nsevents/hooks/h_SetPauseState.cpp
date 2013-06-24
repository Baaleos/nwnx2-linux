#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_SetPauseState(CServerExoAppInternal *srv, uint8_t unknown, int state){
    events.Log(3, "SetPauseState: State: %d, Unknown: %d\n",
	       state, unknown);

    if (!events.scriptRun){
        events.FireEvent(0, EVENT_TYPE_TOGGLE_PAUSE, state);
    }

    if(!events.event.bypass)
        CServerExoAppInternal__SetPauseState(srv, unknown, state);
}
