/*
 *  NWNeXalt - Empty File
 *  (c) 2007 Doug Swarin (zac@intertex.net)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  $Id$
 *  $HeadURL$
 *
 */

#ifndef _NX_NWN_STRUCT_CNWSMODULE_
#define _NX_NWN_STRUCT_CNWSMODULE_

struct CNWSModule_s {
    uint32_t                    field_00;
    uint32_t                    field_04;
    uint32_t                    field_08;
    uint32_t                    field_0C;
    uint32_t                    field_10;
    uint32_t                    field_14;
    uint32_t                    field_18;
    uint32_t                    field_1C;
    uint32_t                    field_20;
    uint32_t                    field_24;
    uint32_t                    field_28;
    uint32_t                    field_2C;
    uint32_t                    field_30;
    uint32_t                    field_34;

    nwn_objid_t                *mod_areas;
    uint32_t                    mod_areas_len;

	uint32_t                    mod_areas_alloc;                                                /* 0x0040 */
    uint32_t                    field_44;                                                               /* 0x0044 */
    uint32_t                    field_48;                                                               /* 0x0048 */
    CExoLinkedList              mod_PlayerTURDList;                                             /* 0x004C-50 */
    
    CExoLocString               *mod_description;                                               /* 0x0050-54 */
    CExoString                  mod_custom_tlk;                                                 /* 0x0058-5C */

    uint32_t                    field_60;                                                       /* 0x0060 */
    uint32_t                    field_64;                                                       /* 0x0064 */
    CExoString                  mod_current_game;                                               /* 0x0068 */
    uint32_t                    field_70;                                                       /* 0x0070 */
    uint32_t                    field_74;                                                       /* 0x0074 */
    uint32_t                    field_78;                                                       /* 0x0078 */
    uint32_t                    mod_is_demo;                                                    /* 0x007C */
    uint32_t                    mod_1;                                                          /* 0x0080 */
    CExoLocString               mod_name;                                                       /* 0x0084 */
    CExoStringList              mod_haks;                                                               /* 0x008C */
    uint32_t                    field_98;
    uint32_t                    field_9C;
    uint32_t                    field_A0;
    uint32_t                    field_A4;

    CNWSScriptVarTable          mod_vartable;
};

#endif /* _NX_NWN_STRUCT_CNWSMODULE_ */

/* vim: set sw=4: */
