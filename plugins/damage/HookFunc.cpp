/***************************************************************************
    Events plugin for NWNX  - hooks implementation
    (c) 2006 virusman (virusman@virusman.ru)

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

#include <sys/types.h>
#include <sys/mman.h>
#include <dlfcn.h>
#include <stdarg.h>

#include <limits.h>		/* for PAGESIZE */
#ifndef PAGESIZE
#define PAGESIZE 4096
#endif

#include "NWNXDamage.h"
#include "HookFunc.h"


extern CNWNXDamage damage;


typedef unsigned long dword;
typedef unsigned short int word;
typedef unsigned char byte;

//int (*CNWSArea__GetFirstObjectInArea)(CNWSArea *pArea, dword *pObjID);

//int (*CNWSEffectListHandler__OnApplyDamage)(CNWSEffectListHandler *, CNWSObject *, CGameEffect *, int );
// 0816c7e4 T CNWSEffectListHandler::OnApplyDamage(CNWSObject *, CGameEffect *, int)

dword ppServThis = 0;
dword pServThis = 0; //g_pAppManager
dword pScriptThis = 0;
dword pServInternal = 0;


int HookFunctions()
{
	ppServThis = 0x0832F1F4;
	pScriptThis = pServThis - 8;

//	*(dword*)&CNWSArea__GetFirstObjectInArea = 0x080D4814;
	//*(dword*)&CNWSEffectListHandler__OnApplyDamage = 0x0816c7e4;

    damage.Log(0, "pServThis = %08lX\n", pServThis);

	return (ppServThis && pScriptThis);
}

void InitConstants()
{
	pServThis = *((*(dword**)ppServThis) + 1);
	pServInternal = *((dword*)pServThis + 1);
}

