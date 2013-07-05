/***************************************************************************
    Copyright (C) 2011-2013 jmd (jmd2028 at gmail dot com)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
***************************************************************************/

#include "NWNXCombat.h"

extern CNWNXCombat combat;

void Hook_InitializeNumberOfAttacks(CNWSCombatRound *combat_round) {
    CNWSCreature* pc = combat_round->cr_original;
    CNWSItem* rh = CNWSInventory__GetItemInSlot(pc->cre_equipment, EQUIPMENT_SLOT_RIGHTHAND);
    CNWSItem* lh = CNWSInventory__GetItemInSlot(pc->cre_equipment, EQUIPMENT_SLOT_LEFTHAND);

    auto c = combat.get_creature(pc->obj.obj_id);
    int attacks = c->offense.getAttacks();    
    int offhand_attacks = c->offense.getAttacksOffhand();
    
    combat_round->cr_extra_taken = 0;
    combat_round->cr_offhand_taken = 0;

    int rbi = rh ? rh->it_baseitem : -1;
    int lbi = lh ? lh->it_baseitem : -1;

    if ( ( rbi == BASE_ITEM_HEAVYCROSSBOW ||
           rbi == BASE_ITEM_LIGHTCROSSBOW ) &&
         !CNWSCreatureStats__HasFeat(pc->cre_stats, FEAT_RAPID_RELOAD) ) {
        attacks = 1;
    }
    else if ( pc->cre_stats->cs_override_atks ) {
        /* appearently some kind of attack override */
        attacks = pc->cre_stats->cs_override_atks;
    }

    // The default NWN code sets onhand attacks to 1
    if ( pc->cre_slowed && attacks > 1 ) {
        attacks -= 1;
    }
    
    // Dirty Fighting
    if ( pc->cre_mode_combat == 10 ) {
        CNWSCreature__SetCombatMode(pc, 0, 1);
        combat_round->cr_onhand_atks = 1;
        combat_round->cr_offhand_atks = 0;
        return;
    }
    // Rapid Shot
    else if ( pc->cre_mode_combat == 6  && 
              ( rbi == BASE_ITEM_LONGBOW ||
                rbi == BASE_ITEM_SHORTBOW ) ){
        combat_round->cr_additional_atks = 1;
    }
    // flurry.
    // Flurry can only be toggled on IFF the monk weapon reqs are met.
    else if ( pc->cre_mode_combat == 5 ) {
        combat_round->cr_additional_atks = 1;
    }

    if ( pc->cre_hasted ) {
        combat_round->cr_additional_atks++;
    }
    
    combat_round->cr_onhand_atks = attacks;
    combat_round->cr_offhand_atks = offhand_attacks;
    
    combat.Log(3, "on hand: %d, off hand: %d, additonal: %d, mode: %d",
               combat_round->cr_onhand_atks,
               combat_round->cr_offhand_atks,
               combat_round->cr_additional_atks,
               pc->cre_mode_combat);

}

