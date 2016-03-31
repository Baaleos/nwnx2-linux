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

#include "HookFunc.h"
#include "CGameObjectArray.h"
#include "NWNStructures.h"
#include "NWNXDamage.h"
#include "AssemblyHelper.cpp"

extern CNWNXDamage damage;
AssemblyHelper asmhelp;


CNWSModule *(*CServerExoApp__GetModule)(void *pServerExo);
void (*CNWSArea__CNWSArea)(void *pArea, CResRef res, int a3, dword ObjID);
int (*CNWSArea__LoadArea)(void *pArea, int flag);
void (*CExoArrayList__Add)(void *pArray, dword nObjID);
void (*CExoArrayList__Remove)(void *pArray, dword nObjID);
void (*CNWSArea__Destructor)(void *pArea, int flag);
void *(*CServerExoAppInternal__GetGameObject)(void *pServerExoAppInternal, dword nObjID);
void *(*GetAreaByGameObjectID)(void *pServerExoAppInternal, dword nObjID);
CGameObjectArray *(*CServerExoApp__GetObjectArray)(void *pServerExo);
void (*CExoArrayList_unsigned_long___Add)(CExoArrayList *pArray, unsigned long nElement);
void (*CExoArrayList_unsigned_long___Remove)(CExoArrayList *pArray, unsigned long nElement);
int (*CExoArrayList_unsigned_long___IndexOf)(CExoArrayList *pArray, unsigned long nElement);
CExoLinkedList *(*pGetPlayerList)(void *pServerExo);
void *(*pGetServerMessage)(void *pServerExo);
void (*SendServerToPlayerDungeonMasterAreaList)(void *pMessage, void *pPlayer);
int (*CNWSArea__GetFirstObjectInArea)(CNWSArea *pArea, dword *pObjID);
int (*CNWSArea__GetNextObjectInArea)(CNWSArea *pArea, dword *pObjID);

dword ppServThis = 0;
dword pServThis = 0; //g_pAppManager
dword pScriptThis = 0;
dword pServInternal = 0;


int HookFunctions()
{
	ppServThis = 0x0832F1F4;
	pScriptThis = pServThis - 8;
	*(dword*)&CServerExoApp__GetModule = 0x080B1ADC;
	*(dword*)&CNWSArea__CNWSArea = 0x080CBD30;
	*(dword*)&CNWSArea__LoadArea = 0x080CDFDC;
	*(dword*)&CExoArrayList__Add = 0x0805EEE0;
	*(dword*)&CNWSArea__Destructor = 0x080CC244;
	*(dword*)&CServerExoAppInternal__GetGameObject = 0x080B02FC;
	*(dword*)&GetAreaByGameObjectID = 0x080B0484;
	*(dword*)&CExoArrayList__Remove = 0x0805EE88;
	*(dword*)&CServerExoApp__GetObjectArray = 0x080B1D84;
	*(dword*)&CExoArrayList_unsigned_long___Add = 0x0805EEE0;
	*(dword*)&CExoArrayList_unsigned_long___Remove = 0x0805EE88;
	*(dword*)&CExoArrayList_unsigned_long___IndexOf = 0x080986C4;

	*(dword*)&pGetPlayerList = 0x080B1F2C;  //CServerExoApp::GetPlayerList(void)
	*(dword*)&pGetServerMessage = 0x080B1F54;  //CServerExoApp::GetNWSMessage(void)
	*(dword*)&SendServerToPlayerDungeonMasterAreaList = 0x08075960;  //CNWSMessage::SendServerToPlayerDungeonMasterAreaList(CNWSPlayer *)

	*(dword*)&CNWSArea__GetFirstObjectInArea = 0x080D4814;
	*(dword*)&CNWSArea__GetNextObjectInArea = 0x080D4858;

    areas.Log(0, "pServThis = %08lX\n", pServThis);

	return (ppServThis && pScriptThis);
}

void InitConstants()
{
	pServThis = *((*(dword**)ppServThis) + 1);
	pServInternal = *((dword*)pServThis + 1);
}

