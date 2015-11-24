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
	uint32_t                    field_B0;                               /* 0x00B0 */    
    uint32_t                    field_B4;                               /* 0x00B4 */
    CExoString                  mod_scripts[18];                        /* 0x00B8 */
    uint32_t                    field_144;                              /* 0x0144 */    
    uint32_t                    field_148;                              /* 0x0148 */
    uint32_t                    field_14C;                              /* 0x014C */
    uint32_t                    field_150;                              /* 0x0150 */
    CExoArrayList_ptr           mod_lookuptable;                        /* 0x0154 */



    uint32_t                    field_160;                              /* 0x0160 */
    uint32_t                    field_164;                              /* 0x0164 */
    nwn_objid_t                 mod_last_enter;                                 /* 0x016C */
    nwn_objid_t                 mod_last_exit;                                  /* 0x0170 */    
    nwn_objid_t                 mod_last_item_aquired;                          /* 0x0174 */    
    nwn_objid_t                 mod_last_item_aquired_from;                     /* 0x0178 */    
    nwn_objid_t                 mod_last_item_aquired_by;                       /* 0x017C */    
    uint32_t                    field_180;                                      /* 0x0180 */    
    uint32_t                    field_184;                                      /* 0x0184 */
    uint32_t                    mod_last_item_aquired_stack_size;               /* 0x0188 */    
    uint32_t                    field_18C;                                      /* 0x018C */
    uint32_t                    field_190;                                      /* 0x0190 */
    uint32_t                    field_194;                                      /* 0x0194 */
    uint32_t                    field_198;                                      /* 0x0198 */
    uint32_t                    field_19C;                                      /* 0x019C */
    nwn_objid_t                 mod_last_rested;                                /* 0x01A0 */
    uint32_t                    field_1A4;                                      /* 0x01A4 */
    nwn_objid_t                 mod_last_player_died;                           /* 0x01A8 */
    nwn_objid_t                 mod_last_player_dying;                          /* 0x01AC */
    uint32_t                    field_1B0;                                      /* 0x01B0 */
    uint32_t                    field_1B4;                                      /* 0x01B4 */
    uint32_t                    field_1B8;                                      /* 0x01B8 */
    uint32_t                    field_1BC;                                      /* 0x01BC */
    uint32_t                    mod_date_year;                                                  /* 0x01C0 */
    uint32_t                    mod_date_month;                                                 /* 0x01C4 */
    uint32_t                    mod_date_day;                                                   /* 0x01C8 */
    uint32_t                    mod_time_hour;                                                  /* 0x01CC */
    uint32_t                    field_1D0;                                      /* 0x01D0 */
    uint32_t                    field_1D4;                                      /* 0x01D4 */    
    uint8_t                     mod_min_per_hour;                               /* 0x01D8 */
};

#endif /* _NX_NWN_STRUCT_CNWSMODULE_ */

/* vim: set sw=4: */
