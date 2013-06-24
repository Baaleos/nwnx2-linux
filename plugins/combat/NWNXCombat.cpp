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

#include "NWNXCombat.h"

extern PLUGINLINK *pluginLink;

CNWNXCombat::CNWNXCombat() {
    confKey = strdup("COMBAT");
}

CNWNXCombat::~CNWNXCombat() { }

bool CNWNXCombat::OnCreate(gline *config, const char *LogDir) {
    char log[128];
    sprintf(log, "%s/nwnx_combat.txt", LogDir);

    // call the base class function
    if (!CNWNXBase::OnCreate(config,log))
        return false;
    
    Log(0, "NWNX Combat v0.1\n");
    Log(0, "(c) by jmd (jmd2028 at gmail dot com, 2011-2013)\n");

    Log(0, "CreatureDefense Size: %d\n", sizeof(CreatureDefense));
    Log(0, "CreatureOffense Size: %d\n", sizeof(CreatureOffense));

    // Plugin Events
    if( !pluginLink ){
		Log (0, "Plugin link not accessible.\n");
        return false;
    }
    else {
		Log (0, "Plugin link: %08lX\n", pluginLink);
    }

    HANDLE handlePluginsLoaded = HookEvent("NWNX/Core/PluginsLoaded", Handle_PluginsLoaded);
    if (!handlePluginsLoaded) {
        Log(0, "Cannot hook plugins loaded event!\n");
		return false;
    }

    bHooked = hook_functions();
    talib_init();
    
    Log(0,"* Module loaded successfully.\n");
    return true;
}

char* CNWNXCombat::OnRequest (char *gameObject, char* Request, char* Parameters) {

    Log(2, "(S) Request: \"%s\"\n", Request);
    Log(3, "(S) Params:  \"%s\"\n", Parameters);

    if (!gameObject) { return NULL; }
    
    CGameObject *ob = reinterpret_cast<CGameObject *>(gameObject);

    return HandleRequest(ob, Request, Parameters);
}

bool CNWNXCombat::OnRelease() {
	for ( auto it = cache_.begin(); it != cache_.end(); ++it ) {
		delete it->second;
	}
	delete[] table_dmg_rolls;
	delete[] table_baseitems;
    Log (0, "o Shutdown..\n");
    return true;
}

DiceRoll CNWNXCombat::ip_dmg_to_roll(uint32_t ip_dmg) {
    if ( !table_dmg_rolls ) { return DiceRoll(); }
    return table_dmg_rolls[ip_dmg];
}

bool CNWNXCombat::InitializeEventHandlers() {
    bool result = true;

    HANDLE handleItemPropEvent = HookEvent("NWNX/Items/ItemPropEvent", Handle_ItemPropEvent);
    if ( !handleItemPropEvent ) {
        Log(0, "Cannot hook NWNX/Items/ItemPropEvent!\n");
		result = false;
    }

    HANDLE handleEffectEvent = HookEvent("NWNX/Effects/EffectEvent",
										 Handle_EffectEvent);
    if ( !handleEffectEvent ) {
        Log(0, "Cannot hook NWNX/Effects/EffectEvent!\n");
		result = false;
    }
    
    return result;
}

bool CNWNXCombat::InitializeTables() {
    C2DA *props = nwn_GetCached2da("wpnprops");

    if ( props == NULL ) {
		Log(0, "ERROR: unable to load %s.2da.\n", WEAPON_PROP_2DA);
		return false;
    }
    
    int bi_len = (*NWN_Rules)->ru_baseitems->bitemarray_len;
    table_baseitems = new uint32_t[bi_len];
    std::fill_n(table_baseitems, bi_len, 0);
    
    for ( int i = 0; i < nwn_Get2daRowCount(props); ++i ) {
		int bi = nwn_Get2daInt(props, "Baseitems", i);
		table_baseitems[bi] = i;
    }

    C2DA *dmg = nwn_GetCached2da("iprp_damagecost");
    if ( dmg == NULL ) {
		Log(0, "ERROR: unable to load %s.2da.\n", "iprp_damagecost");
		return false;
    }

    int rows = nwn_Get2daRowCount(dmg);
    table_dmg_rolls = new DiceRoll[rows];
    for ( int i = 0; i < rows; ++i ) {
		table_dmg_rolls[i] = DiceRoll(nwn_Get2daInt(dmg, "NumDice", i),
									  nwn_Get2daInt(dmg, "Die", i),
									  0);
    }
}


Creature* CNWNXCombat::get_creature(uint32_t id) {
    CNWSCreature *cre = nwn_GetCreatureByID(id);
	
    auto it = cache_.find(id);
    if ( it == cache_.end() && cre ) {
		Log(3, "Creature ID: %X.  Not found, attempting insert.\n", id);
		auto c = new Creature(cre);
		c->setParent(c, cre);
		cache_.insert( make_pair(id, c) );
		c->update();
		return c;
    }
    else if ( !cre ) {
		Log(3, "Creature does not exist removing: %X.\n", id);
		return NULL;
    }
    it->second->setParent(it->second, cre);
    return it->second;
}
