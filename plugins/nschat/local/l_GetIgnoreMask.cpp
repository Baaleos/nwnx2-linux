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
1    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ***************************************************************************/

#include "NWNXChat.h"

extern CNWNXChat chat;

uint32_t Local_GetIgnoreMask(uint32_t from, uint32_t to){
    chat.Log(0, "from_oID=%08lX, to_ID=%08lX, mode: %d\n", from, to); 

    if(chat.ignores.find(to) == chat.ignores.end())
        return 0;

    vector<IgnorePair>::iterator it;
    for (it = chat.ignores[to].begin(); it != chat.ignores[to].end(); it++){
        if(it->first == to)
            return it->second;
    }

    return 0;
}