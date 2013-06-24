/***************************************************************************
    Copyright (C) 2011 jmd ( jmd2028 at gmail dot com )

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

#ifndef NWNX_COMBAT_H
#define NWNX_COMBAT_H

#include "NWNXLib.h"
#include "nwnx_modules_ext.h"
#include "talib/all.h"
#include "talib/effects/creation.h"

#include "object/Creature.h"
#include "object/SituatedObject.h"
#include "NWNXBase.h"

#include "common.h"
#include "consts.h"
#include "handles.h"
#include "hooks.h"

#include <utility>

bool hook_functions();
char* HandleRequest(CGameObject *ob, const char *request, char *value);

class CNWNXCombat : public CNWNXBase
{
public:
    CNWNXCombat();
    ~CNWNXCombat();
    bool OnCreate(gline *nwnxConfig, const char *LogDir=NULL);
    char* OnRequest (char* gameObject, char* Request, char* Parameters);
    //unsigned long OnRequestObject (char *gameObject, char* Request);
    bool OnRelease();
    const char* GetConf(const char* key);

    bool InitializeEventHandlers();
    bool InitializeTables();
    
    uint32_t baseitem_to_weapon(uint32_t bi) {
		if ( !table_baseitems ) { return 0; }
		return table_baseitems[bi];
    }

    DiceRoll ip_dmg_to_roll(uint32_t ip_dmg);
    
    bool bHooked;

    Creature* get_creature(uint32_t id);

    uint32_t  *table_baseitems;
    DiceRoll  *table_dmg_rolls;
    std::map<uint32_t, CombatMod> modes;
    bool       disable_circle_kick;
    bool       disable_dev_crit;
    bool       disable_damage_mod_msg;
    bool       disable_parry;

private:
    std::map< uint32_t, Creature* > cache_;
};

#endif /* NWNX_COMBAT_H */
