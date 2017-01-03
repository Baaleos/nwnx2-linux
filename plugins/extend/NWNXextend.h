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

#ifndef NWNXextend_h_
#define NWNXextend_h_

#include <map>
#include <string>
#include "NWNXBase.h"
#include "NWNXLib.h"
#include "typedefs.h"

class CNWNXextend : public CNWNXBase
{
public:
	CNWNXextend();
	virtual ~CNWNXextend();

	bool OnCreate(gline *nwnxConfig, const char *LogDir=NULL);
	char* OnRequest (char* gameObject, char* Request, char* Parameters);
	unsigned long OnRequestObject (char *gameObject, char* Request);
	int GetConfInteger(const char *key);

	int ScriptResult;

private:
	char* pGameObject;
	StringMap pluginConfig;
};

//extern void nwn_ExecuteScript(const char *scr, nwn_objid_t oid);
//extern void             nwn_DeleteLocalInt(CNWSScriptVarTable *vt, const char *var_name);
//extern void             nwn_DeleteLocalObject(CNWSScriptVarTable *vt, const char *var_name);
//extern void             nwn_DeleteLocalString(CNWSScriptVarTable *vt, const char *var_name);

//extern void             nwn_SetLocalInt(CNWSScriptVarTable *vt, const char *var_name, int32_t value);
//extern void             nwn_SetLocalObject(CNWSScriptVarTable *vt, const char *var_name, uint32_t id);
//extern void             nwn_SetLocalString(CNWSScriptVarTable *vt, const char *var_name, const char *value);
//extern int32_t          nwn_GetLocalInt(CNWSScriptVarTable *vt, const char *var_name);
//extern uint32_t         nwn_GetLocalObject(CNWSScriptVarTable *vt, const char *var_name);
//extern const char      *nwn_GetLocalString(CNWSScriptVarTable *vt, const char *var_name);

#endif
