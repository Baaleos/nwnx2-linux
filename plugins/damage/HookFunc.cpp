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


unsigned char d_jmp_code[] = "\x68\x60\x70\x80\x90"       /* push dword 0x90807060 */
                             "\xc3\x90\x90\x90\x90";//x00 /* ret , nop , nop       */
unsigned char d_ret_code_tov[0x20];



void
d_enable_write (unsigned long location)
{
    char *page;
    page = (char *) location;
    page = (char *) (((int) page + PAGESIZE - 1) & ~(PAGESIZE - 1));
    page -= PAGESIZE;

    if (mprotect (page, PAGESIZE, PROT_WRITE | PROT_READ | PROT_EXEC))
	perror ("mprotect");
}

int intlen = -1;

void
d_redirect (long from, long to, unsigned char *d_ret_code, long len=0)
{
    // enable write to code pages
    d_enable_write (from);
    // copy orig code stub to our "ret_code"
    len = len ? len : sizeof(d_jmp_code)-1; // - trailing 0x00
    intlen = len;
    memcpy ((void *) d_ret_code, (const void *) from, len);
    // make ret code
    *(long *)(d_jmp_code + 1) = from + len;
    memcpy ((char *) d_ret_code + len, (const void *) d_jmp_code, 6);
    // make hook code
    *(long *)(d_jmp_code + 1) = to;
    memcpy ((void *) from, (const void *) d_jmp_code, 6);
}



int CNWSEffectListHandler__OnApplyDamageHook(CNWSEffectListHandler *, CNWSObject *, CGameEffect *, int32_t i)
{
	/*dword oObject1 = pObject1->ObjectID;
	dword oObject2 = pObject2->ObjectID;
	int nResult = 0;
	if(!visibility.TestVisibility(oObject1, oObject2, nResult)){
		nResult = CNWSMessage__TestObjectVisible_orig(pMessage, pObject1, pObject2);
	}
	damage.Log(3, "Visibility check: %x - %x: %d\n", pObject1->ObjectID, pObject2->ObjectID, nResult);
	return nResult;*/
	return 1;
}







int HookFunctions()
{
	ppServThis = 0x0832F1F4;
	pScriptThis = pServThis - 8;

//	*(dword*)&CNWSArea__GetFirstObjectInArea = 0x080D4814;
	//*(dword*)&CNWSEffectListHandler__OnApplyDamage = 0x0816c7e4;

	dword org_OriginalEffectDamageApply = 0x0816c7e4;
	d_redirect (org_OriginalEffectDamageApply, (unsigned long)CNWSEffectListHandler__OnApplyDamageHook, d_ret_code_tov, 12);
	
	
	
    damage.Log(0, "pServThis = %08lX\n", pServThis);

	return (ppServThis && pScriptThis);
}

void InitConstants()
{
	pServThis = *((*(dword**)ppServThis) + 1);
	pServInternal = *((dword*)pServThis + 1);
}

