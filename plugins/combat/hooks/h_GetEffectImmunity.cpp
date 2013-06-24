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

#include "talib/util/math.h"
#include "NWNXCombat.h"

extern CNWNXCombat combat;

int32_t Hook_GetEffectImmunity(CNWSCreatureStats *stats, uint8_t type, CNWSCreature *versus) {
    auto c = combat.get_creature(stats->cs_original->obj.obj_id);
    if ( !c ) { return true; }					  
    return c->defense.isImmune(type);
}
