
/***************************************************************************
    ExaltReplace.c - Implementation of NWN combat replacement functions
    Copyright (C) 2007 Doug Swarin (zac@intertex.net)

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

// Ported over from Acaos' nwnx_weapons.

#include "NWNXCombat.h"

extern CNWNXCombat combat;

bool GetIsWeaponLight (CNWSCreature *cre, CNWSItem *it, bool finesse) {
    int rel;

    if ( GetIsUnarmedWeapon(it) ) { return true; }

    if ( cre->cre_size < CREATURE_SIZE_TINY ||
	 cre->cre_size > CREATURE_SIZE_HUGE ) {
        return false;
    }

    if (finesse) {
	C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
	if ( !props ) { return false; }

	uint32_t finsize = nwn_Get2daInt(props, "Finesse", combat.baseitem_to_weapon(it->it_baseitem));
	if ( cre->cre_size >= finsize ) { return true; }	
    }
    
    rel = CNWSCreature__GetRelativeWeaponSize(cre, it);

    /* ensure Small beings can finesse Small weapons still */
    if (finesse && cre->cre_size < 3) { return (rel <= 0); }

    return (rel < 0);
}


/* vim: set sw=4: */
