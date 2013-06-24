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

#include "talib/accumulator/all.h"
#include "talib/custom/versus_info.h"
#include "NWNXEffects.h"

extern CNWNXEffects effects;

int32_t Local_GetEffectImmunity(CNWSCreatureStats *stats, uint8_t type, CNWSCreature *versus) {
    if ( !stats || !stats->cs_original ) { return 0; }
    
    EffectImmunityAcc acc(stats->cs_original, VersusInfo(versus), type);
    int32_t res = acc.get_result();

    return res;
}