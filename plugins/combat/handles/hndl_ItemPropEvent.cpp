#include "NWNXCombat.h"

extern CNWNXCombat combat;

int Handle_ItemPropEvent(WPARAM p, LPARAM a) {
    EventItemprop *ip_event = (EventItemprop *)p;

    combat.Log(5, "Creature: %X, Item: %X, ItempProperty Type: %d", 
               ip_event->obj->obj.obj_id,
               ip_event->item->obj.obj_id,
               ip_event->ip->ip_type);
    
    // if ( ip_event->preapply && ip_event->ip->ip_type == ITEM_PROPERTY_DAMAGE_BONUS ) {
    //  combat.Log(3, "Supressing Damage Bonus.");
    //  ip_event->suppress = true;
    //  return 1;
    // }

    return 0;
}
