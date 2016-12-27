
/***************************************************************************
    NWNXFuncs.cpp - Implementation of the CNWNXFuncs class.
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

#include "NWNXDefenses.h"

void Local_AdjustCombatHitDamage (CNWSCreature *attacker, CNWSCreature *target, int16_t *damages, int crit) {


    int i, iDmg, iChangedDamage;

    if (target == NULL            ||
        target->cre_stats == NULL ||
        target->obj.obj_type != 5)
        return;

	//char * cData = new char[25];
	char * cData = malloc(50 * sizeof(char));
	char * script = malloc(12 * sizeof(char));
	script= "nwnx_damages";
	for (i = 0; i < 13; i++) {
		sprintf( cData, "damage_%d", i );
		iDmg = damages[11+i];
		nwn_SetLocalInt(target->obj->GetScriptVarTable(), cData, iDmg);
    }	
	nwn_ExecuteScript(script,target->obj.obj_id);	
		
    
    for (i = 0; i < 13; i++) {
        sprintf( cData, "damage_%d", i );
		iDmg = damages[11+i];
		iChangedDamage = nwn_GetLocalInt(target->obj->GetScriptVarTable(), cData);
		if(iDmg != iChangedDamage){
				damages[11+i] = iChangedDamage;
		}
    }
	
	free(cData);
	free(script);
}


/* vim: set sw=4: */
