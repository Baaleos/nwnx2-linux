
/***************************************************************************
    NWNXFuncs.cpp - Implementation of the CNWNXFuncs class.
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

#include "NWNXDefenses.h"


void Func_GetTrueDamageImmunity (CGameObject *ob, char *value) {
    int damtype, percent = 0, idx = 0;
    const CNWSObject *obj;

    if (ob == NULL                                  ||
        (obj = ob->vtable->AsNWSObject(ob)) == NULL ||
        obj->obj_damage_immunities == NULL) {

        snprintf(value, strlen(value), "0");
        return;
    }

    damtype = atoi(value);

    while (damtype && !(damtype & 1)) {
        idx++;
        damtype >>= 1;
    }

    if (idx <= DAMAGE_TYPE_LAST)
        percent = obj->obj_damage_immunities[idx];

    snprintf(value, strlen(value), "%d", percent);
}


/* vim: set sw=4: */
