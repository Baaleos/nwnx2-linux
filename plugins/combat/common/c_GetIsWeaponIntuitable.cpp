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

bool GetIsWeaponIntuitable(CNWSCreature *cre, CNWSItem *it) {
    if ( GetIsUnarmedWeapon(it) ) { return true; }

    return ( it->it_baseitem == BASE_ITEM_CLUB               || 
	     it->it_baseitem == BASE_ITEM_DAGGER             ||
	     it->it_baseitem == BASE_ITEM_LIGHTMACE          || 
	     it->it_baseitem == BASE_ITEM_SICKLE             ||
	     it->it_baseitem == BASE_ITEM_SHORTSPEAR         ||
	     it->it_baseitem == BASE_ITEM_MORNINGSTAR        ||
	     it->it_baseitem == BASE_ITEM_QUARTERSTAFF       ||
	     it->it_baseitem == BASE_ITEM_LIGHTCROSSBOW      ||
	     it->it_baseitem == BASE_ITEM_HEAVYCROSSBOW      ||
	     it->it_baseitem == BASE_ITEM_DART               ||
	     it->it_baseitem == BASE_ITEM_SLING              ||
	     it->it_baseitem == BASE_ITEM_SCIMITAR           ||
	     it->it_baseitem == BASE_ITEM_CEP_HEAVYMACE      ||
	     it->it_baseitem == BASE_ITEM_CEP_ASSASSINDAGGER ||
	     it->it_baseitem == BASE_ITEM_CEP_KATAR          ||
	     it->it_baseitem == 210 /* short spear */        ||
	     it->it_baseitem == 312 /* mace 2 */ );
}


/* vim: set sw=4: */
