
/***************************************************************************
    NWNXStructs.h - Interface for the CNWNXStructs class.
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

#ifndef NWNX_DAMAGE_H
#define NWNX_DAMAGE_H

#include "NWNXLib.h"


#ifdef __cplusplus
extern "C" {
#endif
int Hook_DamageEffectListHandler (CNWSEffectListHandler *pThis, CGameObject *ob, CGameEffect *effect, int iArg);

#ifdef __cplusplus
}


#include "NWNXBase.h"
class CNWNXDamage:public CNWNXBase {
  public:
    CNWNXDamage ();
    virtual ~ CNWNXDamage ();

    bool OnCreate (gline *nwnxConfig, const char *LogDir = NULL);
    char *OnRequest (char *gameObject, char *Request, char *Parameters);
    unsigned long OnRequestObject (char *gameObject, char *Request);

    // bool OnRelease  ();
};
#endif
/*extern void nwn_ExecuteScript(const char *scr, nwn_objid_t oid);
extern void             nwn_DeleteLocalFloat(CNWSScriptVarTable *vt, const char *var_name);
extern void             nwn_DeleteLocalInt(CNWSScriptVarTable *vt, const char *var_name);
extern void             nwn_DeleteLocalLocation(CNWSScriptVarTable *vt, const char *var_name);
extern void             nwn_DeleteLocalObject(CNWSScriptVarTable *vt, const char *var_name);
extern void             nwn_DeleteLocalString(CNWSScriptVarTable *vt, const char *var_name);

extern int32_t          nwn_GetLocalInt(CNWSScriptVarTable *vt, const char *var_name);
extern float            nwn_GetLocalFloat(CNWSScriptVarTable *vt, const char *var_name);
extern CScriptLocation  nwn_GetLocalLocation(CNWSScriptVarTable *vt, const char *var_name);
extern uint32_t         nwn_GetLocalObject(CNWSScriptVarTable *vt, const char *var_name);
extern const char      *nwn_GetLocalString(CNWSScriptVarTable *vt, const char *var_name);

extern void             nwn_SetLocalFloat(CNWSScriptVarTable *vt, const char *var_name, float value);
extern void             nwn_SetLocalInt(CNWSScriptVarTable *vt, const char *var_name, int32_t value);
extern void             nwn_SetLocalLocation(CNWSScriptVarTable *vt, const char *var_name, CScriptLocation * value);
extern void             nwn_SetLocalObject(CNWSScriptVarTable *vt, const char *var_name, uint32_t id);
extern void             nwn_SetLocalString(CNWSScriptVarTable *vt, const char *var_name, const char *value);
*/
#endif /* NWNX_DAMAGE_H */

/* vim: set sw=4: */
