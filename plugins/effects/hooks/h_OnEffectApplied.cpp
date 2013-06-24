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
// * The effect being applied is NOT inserted into the effect array
//   until AFTER this function returns.  So any event observer
//   will have to take that fact into account.
int Hook_OnEffectApplied(CServerAIMaster *ai, CNWSObject *obj, CGameEffect *eff, int a){
    int result;

    effects.EffectEvent(obj, eff, false, true);
    
    if (effects.effect_event.suppress){
        result = effects.effect_event.delete_eff;
    }
    else {
        result = CServerAIMaster__OnEffectApplied_orig(ai, obj, eff, a);
    }

    effects.Log(3, "OnEffectApplied: Type: %d, Result: %d\n",
                eff->eff_type, result);

    if ( result == 0 ) {
        effects.EffectEvent(obj, eff, false, false);
    }

    return result;
}
