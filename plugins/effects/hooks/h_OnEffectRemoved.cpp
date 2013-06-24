/***************************************************************************
    Copyright (C) 2011-2013 jmd ( jmd2028 at gmail dot com )

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

#include "NWNXEffects.h"

extern CNWNXEffects effects;

// NOTES:
// * The effect being removed is NOT deleted from the effect array
//   until AFTER this function returns.  So any event observer
//   will have to take that fact into account.
// * The result of the NWN function is  almost always 1.  The only
//   time it returns 0 seems to on the removal of EFFECT_TRUETYPE_POLYMORPH
//   for which effect removal fires twice.
int Hook_OnEffectRemoved(CServerAIMaster *ai, CNWSObject *obj, CGameEffect *eff){
    effects.EffectEvent(obj, eff, true, true);

    // NOTE: 
    int result = CServerAIMaster__OnEffectRemoved_orig(ai, obj, eff);

    effects.Log(3, "OnEffectRemoved: Type: %d, Result: %d\n",
                eff->eff_type, result);

    effects.EffectEvent(obj, eff, true, false);

    return result;
}
