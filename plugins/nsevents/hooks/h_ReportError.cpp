#include "NWNXEvents.h"

extern CNWNXEvents events;

int Hook_ReportError(CNWVirtualMachineCommands *vm, CExoString *str, int strref) {
    char *error = nwn_GetStringByStrref(strref);
    events.Log(0, "%s: %s; Last Command: %d, Args: %d\n", error, str->text, events.last_cmd, events.last_arg_count);
    free(error);
    return CNWVirtualMachineCommands__ReportError(vm, str, strref);
}
