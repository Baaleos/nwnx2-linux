/*
 *  NWNeXalt - Empty File
 *  (c) 2012 jmd
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

#ifndef _NX_NWN_STRUCT_CNWSSPELLSCRIPTDATA_
#define _NX_NWN_STRUCT_CNWSSPELLSCRIPTDATA_

struct CNWSSpellScriptData_s {
    uint32_t     spell_id;
    uint32_t     feat_id;
    nwn_objid_t  caster_id;       /* 08 */
    nwn_objid_t  target_id;       /* 0C */
    nwn_objid_t  item_id;
    Vector       target_pos;
    CExoString   script;
    nwn_objid_t  area_id;
    uint32_t     field_2C;
};

#endif /* _NX_NWN_STRUCT_CNWSSPELLSCRIPTDATA_ */

/* vim: set sw=4: */
