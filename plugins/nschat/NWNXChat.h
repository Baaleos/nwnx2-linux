/***************************************************************************
    Chat plugin for NWNX - interface for the CNWNXChat class.
    (c) 2005-2006 dumbo (dumbo@nm.ru)
	(c) 2006-2007 virusman (virusman@virusman.ru)

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

#ifndef _NWNX_CHAT_H_
#define _NWNX_CHAT_H_

#include "talib/all.h"
#include "NWNXLib.h"

extern int (*CNWSMessage__SendServerToPlayerCCMessage_orig)(CNWSMessage *, uint32_t, uint8_t, CNWCCMessageData *, CNWSCombatAttackData *);
extern int (*CNWSMessage__SendServerToPlayerChatMessage_orig)(CNWSMessage *msg, int8_t, uint32_t, const char **, uint32_t, const char *);

void Hook_SendServerToPlayerCCMessage(CNWSMessage *msg, uint32_t id, int8_t msg_id, CNWCCMessageData *msg_data, CNWSCombatAttackData *attack);
void Hook_SendServerToPlayerChat_Party(CNWSMessage *msg, uint32_t x, uint32_t y, CExoString text);
void Hook_SendServerToPlayerChat_Shout(CNWSMessage *msg, uint32_t x, uint32_t y, CExoString text);
void Hook_SendServerToPlayerChat_Talk(CNWSMessage *msg, uint32_t x, uint32_t y, CExoString text);
void Hook_SendServerToPlayerChat_Tell(CNWSMessage *msg, uint32_t x, uint32_t y, CExoString text);
void Hook_SendServerToPlayerChatMessage(CNWSMessage *msg, int8_t mode, uint32_t from, const char ** text, uint32_t to, const char *xz);

uint32_t Local_GetIgnoreMask(uint32_t from, uint32_t to);
bool Local_GetIsIgnoring(uint32_t from, uint32_t to, int32_t mode);

bool hook_functions();

extern "C" {
#include "nwnx_modules_ext.h"
}

#include <vector>
#include "NWNXBase.h"
typedef std::pair< nwn_objid_t, uint32_t > IgnorePair;
typedef std::map< nwn_objid_t, std::vector<IgnorePair> > IgnoreMap;

class CNWNXChat : public CNWNXBase
{

public:
    CNWNXChat();
    ~CNWNXChat();

    const char* GetConf(const char* key);
    bool HasConf();
    bool HasConf(const char* key);

    bool OnCreate(gline *config, const char* LogDir);
    char* OnRequest(char* gameObject, char* Request, char* Parameters);
    bool OnRelease();

    IgnoreMap ignores;

    // Event structs
    CombatMessage cc_msg;
    ChatMessage chat_msg;
    bool suppress;

    char *lastMsg;
    bool scriptRun;

    // Settings
    int maxMsgLen;
    int processNPC;
    int ignore_silent;

    // Scripts
    char chatScript[17];
    char servScript[17];
    char ccScript[17];

    bool Chat(int8_t mode, nwn_objid_t id, const char *msg, nwn_objid_t to);
    bool CCMessage(nwn_objid_t objID, const int type, const int subtype, CNWCCMessageData* messageData);
    void SendMessage(char* Parameters);

private:
    HANDLE hChatMessage;
    HANDLE hCCMessage;
};

#endif
