#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_CNWSObject_dtor(CNWSObject *obj, int8_t a) {
    events.FireEvent(obj->obj_id,
                     EVENT_TYPE_DESTROY_OBJECT);
    CNWSObject_dtor(obj, a);
}

