/***************************************************************************
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

#include "NWNXextend.h"
#include "Hooks.h"

extern CNWNXdamage damage;


#define HOOK(orig, addr, hook, bytes) \
    *(void**)&orig = nx_hook_function((void*)addr, (void*)hook, bytes, NX_HOOK_DIRECT | NX_HOOK_RETCODE)


	
	
int (*CServerExoAppInternal__RemovePCFromWorld_orig)(CServerExoAppInternal *app,  CNWSPlayer *player) = NULL;
int (*CNWSEffectListHandler__OnApplyDamage_orig)(CNWSEffectListHandler *handler, CNWSObject *obj, CGameEffect *eff, int arg) = NULL;



int Hook_OnPlayerLeave(CServerExoAppInternal *app,  CNWSPlayer *player){
	CGameObject *pPlayerGameObject = CNWSPlayer__GetGameObject(player);
	CNWSObject *pPlayerObject;

	if(pPlayerGameObject != NULL && pPlayerGameObject->vtable != NULL) {
		pPlayerObject = pPlayerGameObject->vtable->AsNWSObject(pPlayerGameObject);
	}
		
	nwn_ExecuteScript((char*)"onplayer_leave", pPlayerObject->obj_id);
	//free(script);
	return CServerExoAppInternal__RemovePCFromWorld_orig(app,player);
}





// Damage hook 
int Hook_OnDamage(CNWSEffectListHandler *handler, CNWSObject *obj, CGameEffect *effect, int arg){
	
	int i;
	CNWSCreature *cre;
	CGameObject *ob = (CGameObject *)CServerExoAppInternal__GetGameObject((*NWN_AppManager)->app_server->srv_internal,obj->obj_id);
	
	if (ob == NULL || (cre = ob->vtable->AsNWSCreature(ob)) == NULL || cre->cre_stats == NULL) {
        return CNWSEffectListHandler__OnApplyDamage_orig(handler, obj,effect,arg);
    }
	

	char * cData = (char*)malloc(10 * sizeof(char));

	CNWSScriptVarTable *vt;
	vt = &(((CNWSObject *)ob)->obj_vartable);
	
	CExoString *dmgr = (CExoString *) malloc(sizeof(CExoString));
    dmgr->text = (char*)"dmg_creator";
    dmgr->len = strlen(dmgr->text)+1;
		
	CNWSScriptVarTable__SetObject(vt,dmgr,effect->eff_creator);
	extend.Log(0,"432\n");
	for (i=0; i< 12; i++) 
		{
			sprintf( cData, "damage_%d", i );
			CExoString *dmgVar = (CExoString *) malloc(sizeof(CExoString));
			dmgVar->text = cData;//(char*)"damage_"+i;
			dmgVar->len = strlen(dmgVar->text);
			CNWSScriptVarTable__SetInt(vt, (CExoString *)dmgVar, effect->eff_integers[i],0);			
		}
	nwn_ExecuteScript("nwnx_damages",obj->obj_id);
	CNWSScriptVarTable__DestroyObject(vt, (CExoString *)dmgr);
	for (i=0; i< 12; i++) 
		{
			sprintf( cData, "damage_%d", i );
			CExoString *dmgVar = (CExoString *) malloc(sizeof(CExoString));
			dmgVar->text = cData;//(char*)"damage_"+i;
			dmgVar->len = strlen(dmgVar->text);
			int nDamAmount = CNWSScriptVarTable__GetInt(vt,(CExoString *)dmgVar);
			effect->eff_integers[i] = nDamAmount;
		}
	free(cData);
	return CNWSEffectListHandler__OnApplyDamage_orig(handler, obj,effect,arg);
}


int InitHooks() {
	*(unsigned long*)&CNWMessage__WriteSHORT = 0x80c3ddc;
	*(unsigned long*)&CNWSMessage__WriteOBJECTIDServer = 0x8052434;
	*(unsigned long*)&CNWSPlayer__GetGameObject = 0x0805e8b8;
	*(unsigned long*)&C2DA__GetINTEntry = 0x082bd77c;
	*(unsigned long*)&C2DA__GetCExoStringEntry = 0x082bedbc;

	*(unsigned long*)&CNWSItem__GetPropertyByTypeExists = 0x081a2a6c;

	*(unsigned long*)&CServerExoAppInternal__RemovePCFromWorld = 0x080a4c94;
	*(unsigned long*)&CNWSEffectListHandler__OnApplyDamage = 0x0816c7e4;
	*(unsigned long*)&CServerExoAppInternal__GetGameObject= 0x080b02fc;
	*(unsigned long*)&CNWSScriptVarTable__SetObject=0x081f41e0;
	*(unsigned long*)&CNWSScriptVarTable__DestroyObject=0x081f43f0;
	*(unsigned long*)&CNWSScriptVarTable__SetInt=0x081f3454;
	*(unsigned long*)&CNWSScriptVarTable__GetInt=0x081f3fc8;
	
	
	
	HOOK(CNWSEffectListHandler__OnApplyDamage_orig, CNWSEffectListHandler__OnApplyDamage, Hook_OnDamage, 6);
	HOOK(CServerExoAppInternal__RemovePCFromWorld_orig, CServerExoAppInternal__RemovePCFromWorld, Hook_OnPlayerLeave, 6);
	
	

	return true;
}