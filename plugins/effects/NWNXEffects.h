/***************************************************************************
    NWNXEffects
    Copyright (C) 2011-2012 jmd ( jmd2028 at gmail dot com )

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

#ifndef NWNX_EFFECTS_H
#define NWNX_EFFECTS_H

#include "NWNXLib.h"
#include "talib/nwn/all.h"
#include "nwnx_modules_ext.h"
#include "hooks.h"

extern int (*CExoArrayList__CGameEffect_Add)(CExoArrayList_ptr *list, CGameEffect *eff);

#define NWNX_EFFECTS_EVENT_APPLY          0
#define NWNX_EFFECTS_EVENT_REMOVE         1

void HandleFunc(CGameObject *ob, char *func, char* value);

int32_t Local_GetEffectImmunity(CNWSCreatureStats *stats, uint8_t type, CNWSCreature *versus);
std::string Local_GetEffectString(CNWSObject *obj);

#include "NWNXBase.h"

class CNWNXEffects:public CNWNXBase {
public:
    CNWNXEffects ();
    virtual ~ CNWNXEffects ();

    bool OnCreate (gline *nwnxConfig, const char *LogDir = NULL);
    char *OnRequest (char *gameObject, char *Request, char *Parameters);
    unsigned long OnRequestObject (char *gameObject, char *Request);

    void EffectEvent(CNWSObject *obj, CGameEffect *eff, bool removal, bool preapply);

    // script variables.    
    EventEffect effect_event;

    bool in_script;

    // store the impact scripts.
    std::map<int, std::string> effect_scripts;
    std::string fallback_script;

private:
    HANDLE hOnEffectEvent;
};

#endif /* NWNX_EFFECTS_H */
