
/***************************************************************************
    NWNXStructs.cpp - Implementation of the CNWNXStructs class.
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
#include "DamageStrCmds.h"
#include "DamageObjCmds.h"


//////////////////////////////////////////////////////////////////////
// Function Signatures
//////////////////////////////////////////////////////////////////////

#define NWNX_DAMAGE_SIG(NAME, SIG) { #NAME, &NAME, SIG }


//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CNWNXDamage::CNWNXDamage() {
    confKey = strdup("DAMAGE");
}


CNWNXDamage::~CNWNXDamage() {
}


char *CNWNXDamage::OnRequest (char *gameObject, char *Request, char *Parameters) {
    const struct StructsStrCommand_s *cmd;

    Log(1, "StrReq: \"%s\"\nParams: \"%s\"\n", Request, Parameters);

    if ((cmd = StructsStrCommandLookup(Request, strlen(Request))) != NULL)
        cmd->func((CGameObject *)gameObject, Parameters);
    else
        Log(0, "Unrecognized string request: \"%s\" \"%s\"\n", Request, Parameters);

    Log(1, "Return: \"%s\"\n", Parameters);

    return NULL;
}


unsigned long CNWNXDamage::OnRequestObject (char *gameObject, char *Request) {
    unsigned long ret = OBJECT_INVALID;
    const struct StructsObjCommand_s *cmd;

    Log(1, "ObjReq: \"%s\"\n", Request);

    if ((cmd = StructsObjCommandLookup(Request, strlen(Request))) != NULL)
        ret = cmd->func((CGameObject *)gameObject);
    else
        Log(0, "Unrecognized object request: \"%s\"\n", Request);

    Log(1, "Return: %08X\n", ret);

    return ret;
}


bool CNWNXDamage::OnCreate (gline *config, const char *LogDir) {
    char log[128];

    sprintf(log, "%s/nwnx_damage.txt", LogDir);

    /* call the base class create function */
    if (!CNWNXBase::OnCreate(config, log))
        return false;

    /* find hook signatures */
    //StructsSearchSignatures();

   /* if (Ref_PushStruct != NULL) {
        unsigned char *p = Ref_PushStruct;
        extern volatile uintptr_t Hook_Struct_Return;

        nx_hook_function(p, (void *)Hook_PushStruct, 5, NX_HOOK_DIRECT);

        Hook_Struct_Return = (uintptr_t)(p + 9);
    }
	*/
    return true;
}

/* vim: set sw=4: */
