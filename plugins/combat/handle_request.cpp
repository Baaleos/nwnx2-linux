#include "NWNXCombat.h"
#include "funcs.h"
#include "talib/util/timer.h"

extern CNWNXCombat combat;

char* HandleRequest(CGameObject *ob, const char *request, char *value) {
#define M(str, lit)							\
    strncmp((str), "" lit, (sizeof (lit)/sizeof(char)) - 1) == 0

    if( M(request, "UPDATE") ){
	auto c = combat.get_creature(ob->id);
	if ( c ) { 
	    uint64_t start = ClockGetTime();
	    c->update();
	    uint64_t stop = ClockGetTime();
	    combat.Log(3, "Update Time: %d\n", stop-start);
	}
    }
    if( M(request, "UPDATEMODE") ){
	auto c = combat.get_creature(ob->id);
	if ( c ) { c->updateMode(); }
    }
    else if ( M(request, "INIT_TABLES") ) {
	combat.InitializeTables();
    }
    else if ( M(request, "DUMPCOMBATMODS") ) {
	Func_DumpCombatMods(ob, value);
    }
    else if ( M(request, "GETISMONKWEAPON") ) {
	CNWSItem *it = nwn_GetItemByID(ob->id);
	int res = 0;
	if ( it ) {
	    res = GetIsMonkWeapon(it);
	}
	snprintf(value, strlen(value), "%d", res);
    }
    else if ( M(request, "GETISRANGED") ) {
	CNWSItem *it = nwn_GetItemByID(ob->id);
	int res = 0;
	if ( it ) {
	    res = GetIsRangedWeapon(it);
	}
	snprintf(value, strlen(value), "%d", res);

    }
    else if ( M(request, "GETISUNAMRED") ) {
	CNWSItem *it = nwn_GetItemByID(ob->id);
	int res = 0;
	if ( it ) {
	    res = GetIsUnarmedWeapon(it);
	}
	snprintf(value, strlen(value), "%d", res);
    }
    else if ( M(request, "GETABVERSUS") ) {
	Func_GetABVersus(ob, value);
    }
    else if ( M(request, "GETACVERSUS") ) {
	Func_GetACVersus(ob, value);
    }
    else if ( M(request, "GETMAXHITPOINTS") ) {
	Func_GetMaxHitPoints(ob, value);
    }
    else if ( M(request, "GETSKILLRANK") ) {
	Func_GetSkillRank(ob, value);
    }
    else if ( M(request, "GETCOMBATINFO") ) {
	Func_GetCombatInfo(ob, value);
    }
    else if ( M(request, "REGISTERMODEMOD") ) {
	Func_RegisterModeCombatMod(ob, value);
    }
    else if ( M(request, "SENDCOMBATINFO") ) {
	Func_SendCombatInfo(ob, value);
    }
    else if ( M(request, "SENDDAMAGEROLL") ) {
	Func_SendDamageRoll(ob, value);
    }

    return NULL;
#undef M
}
