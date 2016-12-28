
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

#include "NWNXDamage.h"


int Hook_DamageEffectListHandler (CNWSEffectListHandler *, CNWSObject *cre, CGameEffect *effect, int iArg) {
    
	int i, iDmg, iChangedDamage;

    if (cre == NULL            ||
        cre->cre_stats == NULL ||
        cre->obj.obj_type != 5)
        return CNWSEffectListHandler__OnApplyDamage(cre,effect,iArg);

	//char * cData = new char[25];
	char * cData = malloc(50 * sizeof(char));
	char * script = malloc(12 * sizeof(char));
	char * damager = malloc(11 * sizeof(char));
	CNWSObject *creator = effect->eff_creator;
	CNWSScriptVarTable *vt;
	vt = &(((CNWSObject *)cre)->obj_vartable);
	script= "nwnx_damages";
	damager= "dmg_creator";
	vt.SetObject(CExoString( damager ),effect->eff_creator.obj_id);
	for (i=0; i< 12; i++) 
		{
			sprintf( cData, "damage_%d", i );
			int iNum = effect->eff_integers[i];
			vt.SetInt(CExoString( cData ),iNum,0);
						
		}
	nwn_ExecuteScript(script,cre->obj.obj_id);
	vt.DestroyObject(CExoString( damager ));
	for (i=0; i< 12; i++) 
		{
			sprintf( cData, "damage_%d", i );
			int nDamAmount = vt.GetInt( CExoString( cData ) );
			effect->eff_integers[i] = nDamAmount;
		}
	
	free(cData);
	free(script);
	free(damager);
    return CNWSEffectListHandler__OnApplyDamage(cre,effect,iArg);
}


/* vim: set sw=4: */
