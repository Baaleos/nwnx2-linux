#include "NWNXEvents.h"

extern CNWNXEvents events;

int Hook_ExecuteCommand(CNWVirtualMachineCommands *vm, int cmd, int args) {
    events.Log(4, "Last Command: %d, Args: %d", cmd, args);
    events.last_cmd = cmd;
    events.last_arg_count = args;
    return CNWVirtualMachineCommands__ExecuteCommand(vm, cmd, args);
}
