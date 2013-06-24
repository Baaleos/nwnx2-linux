#include "NWNXSolstice.h"

extern CNWNXSolstice solstice;

int Handle_ConversationEvent(WPARAM p, LPARAM a) {
    int32_t event = *(int32_t *)p;
    solstice.Log(0, "Conversatio Type: %d\n", event);
    solstice.in_conditional_script = event == 2;
    return 0;
}
