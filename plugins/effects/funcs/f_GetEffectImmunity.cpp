/***************************************************************************
    Copyright (C) 2013 jmd (jmd2028 at gmail dot com)

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

#include "NWNXEffects.h"

extern CNWNXEffects effects;

void Func_GetEffectImmunity(CGameObject *ob, char* value) {
    CNWSCreature *cre = NULL;
    int type;
    uint32_t versus_id;
    CGameObject *vs_ob = NULL;
    CNWSCreature *vs = NULL;
    if ( sscanf(value, "%d %x", &type, &versus_id) != 2 ||
         (cre = ob->vtable->AsNWSCreature(ob)) == NULL  ||
         cre->cre_stats == NULL ) {
        snprintf(value, strlen(value), "0");
        return;
    }

    if ( versus_id != OBJECT_INVALID &&
         (vs_ob = nwn_GetObjectByID(versus_id)) != NULL ) {
        vs = vs_ob->vtable->AsNWSCreature(vs_ob);
    }

    int32_t result = Local_GetEffectImmunity(cre->cre_stats, type, vs);
    snprintf(value, strlen(value), "%d", result);
}
