/***************************************************************************
    Chat plugin for NWNX - Implementation of the CNWNXChat class.
    (c) 2005-2006 dumbo (dumbo@nm.ru)
	(c) 2006-2010 virusman (virusman@virusman.ru)

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

#include "NWNXChat.h"

extern PLUGINLINK *pluginLink;

#define MLIT(str, lit) \
    strncmp((str), "" lit, (sizeof (lit)/sizeof(char)) - 1) == 0

CNWNXChat::CNWNXChat(){
    confKey = "CHAT";
    strcpy(chatScript,"chat_script");
    strcpy(ccScript,"cc_script");
    processNPC = 0;
    ignore_silent = 0;
    maxMsgLen = 1024;
    scriptRun = false;
}

CNWNXChat::~CNWNXChat(){
}

bool CNWNXChat::HasConf(){
    return nwnxConfig->exists(confKey);
}

bool CNWNXChat::HasConf(const char* key){
    return nwnxConfig->exists(confKey, key);
}

const char* CNWNXChat::GetConf(const char* key){
    return (*nwnxConfig)[confKey][key].c_str();
}

bool CNWNXChat::OnCreate (gline *config, const char* LogDir){
    talib_init();
    
    char log[128];
    bool validate = true, startServer = true;

    // call the base class function
    sprintf (log, "%s/nwnx_nschat.txt", LogDir);
    if (!CNWNXBase::OnCreate(config,log))
        return false;

    // write copy information to the log file
    Log (0, "NWNX Chat version 1.0.0 for Linux.\n");
    Log (0, "(c) 2005-2006 by dumbo (dumbo@nm.ru)\n");
    Log (0, "(c) 2006-2010 virusman (virusman@virusman.ru)\n");

    if(HasConf()) {
        if(HasConf("chat_script")){
            strncpy(chatScript, GetConf("chat_script"), 16);
            chatScript[16] = 0;
        }
      
        if(HasConf("server_script")){
            strncpy(servScript, GetConf("server_script"), 16);
            servScript[16] = 0;
        }

        if(HasConf("cc_script")){
            strncpy(ccScript, GetConf("cc_script"), 16);
            ccScript[16] = 0;
        }

        int maxLen = atoi(GetConf("max_msg_len"));

        if (maxLen) maxMsgLen = maxLen;
        processNPC = atoi(GetConf("processnpc"));
        ignore_silent = atoi(GetConf("ignore_silent"));
    }

    Log (1, "Settings:\n");
    Log (1, "chat_script: %s\n", chatScript);
    Log (1, "server_script: %s\n", servScript);
    Log (1, "cc_script: %s\n", ccScript);
    Log (1, "max_msg_len: %d\n", maxMsgLen);
    Log (1, "processnpc: %d\n", processNPC);
    Log (1, "ignore_silent: %d\n\n", processNPC);

    cc_msg.type = 0;
    cc_msg.subtype = 0;
    cc_msg.to = OBJECT_INVALID;
    cc_msg.msg_data = NULL;
    cc_msg.suppress = false;

    chat_msg.channel = 0;
    chat_msg.to = OBJECT_INVALID;
    chat_msg.from = OBJECT_INVALID;
    chat_msg.msg = NULL;
    chat_msg.suppress = false;

    // Plugin Events
    if(!pluginLink){
	Log (0, "Plugin link not accessible\n");
    }
    else {
        Log (0, "Plugin link: %08lX\n", pluginLink);
                    
        hChatMessage = CreateHookableEvent("NWNX/Chat/ChatMessage");
        hCCMessage = CreateHookableEvent("NWNX/Chat/CCMessage");
    }

    lastMsg = new char[maxMsgLen+13];

    return hook_functions();
}

void CNWNXChat::SendMessage(char* Parameters){
    Log(3, "o SPEAK: %s, OID: %08lX\n", Parameters);
    int oSender, oRecipient, nChannel;
    int nParamLen = strlen(Parameters);
    char *nLastDelimiter = strrchr(Parameters, '¬');

    if (!nLastDelimiter || (nLastDelimiter-Parameters) < 0){
        Log(3, "o nLastDelimiter error\n");
        return;
    }

    if(sscanf(Parameters, "%x¬%x¬%d¬", &oSender, &oRecipient, &nChannel) < 3) {
        Log(3, "o sscanf error\n");
        return;
    }

    int nMessageLen = nParamLen-(nLastDelimiter-Parameters)+1;
    char *sMessage = nLastDelimiter+1;
    Log(3, "o SendMsg(%d, %08lX, '%s', %08lX)\n", nChannel, oSender, sMessage,  oRecipient);

    nwn_SendMessage(nChannel, oSender, sMessage, oRecipient);
}

char* CNWNXChat::OnRequest (char* gameObject, char* Request, char* Parameters){
    CGameObject *obj = (CGameObject *) gameObject;

    if (MLIT(Request, "GETID")){
        CNWSPlayer *pl = nwn_GetPlayerByID(strtol(Parameters, 0, 16));
        sprintf(Parameters, "%d", pl ? pl->pl_id : -1);
        Log(3, "o GETID: ID=%s\n", Parameters);
        return NULL;
    }
    else if (MLIT(Request, "LOGNPC")){
        Log(3, "o LOGNPC: %s\n", Parameters);
        processNPC = atoi(Parameters);
        Log(3, "o processNPC = %d\n", processNPC);
    }
    else if (MLIT(Request, "IGNORESILENT")){
        Log(3, "o IGNORESILENT: %s\n", Parameters);
        ignore_silent = atoi(Parameters);
        Log(3, "o ignore_silent = %d\n", ignore_silent);
    }
    else if (MLIT(Request, "SPEAK")){
        SendMessage(Parameters);
        return NULL;
    }
    else if (MLIT(Request, "IGNORE")){
        uint32_t from, mask;
        if(sscanf(Parameters, "%d-%d", &from, &mask) < 2)
            return NULL;
        
        ignores[obj->id].push_back(std::make_pair(from, mask));
    }
    else if (MLIT(Request, "GETIGNORE")){
        uint32_t from = atoi(Parameters);
        sprintf(Parameters, "%d", Local_GetIgnoreMask(obj->id, from));
    }
  
    if (!scriptRun) return NULL; // all following cmds - only in chat script

    if (MLIT(Request, "TEXT")){
        unsigned int length = strlen(lastMsg);
        char *ret = (char *) malloc(length+1);
        strncpy(ret, lastMsg, length);
        ret[length] = 0;
        return ret;
    }
    else if (MLIT(Request, "TYPE")){
        char *ret = (char *) malloc(32);
        sprintf(ret, "%d", cc_msg.type);
        return ret;
    }
    else if (MLIT(Request, "SUBTYPE")){
        sprintf(Parameters, "%d", cc_msg.subtype);
    }
    else if (MLIT(Request, "LOG"))
        Log(0, "%s", Parameters);
    else if (MLIT(Request, "SUPRESS")){
        suppress = atoi(Parameters);
    }
    return NULL;
}

bool CNWNXChat::OnRelease (){
    delete[] lastMsg;
    Log (0, "o Shutdown.\n");
    return true;
}

bool CNWNXChat::Chat(int8_t mode, nwn_objid_t id, const char *msg, nwn_objid_t to){
    if ( !msg ) {
        Log(3, "o Null String");
        return 0; // disable processing of null-string
    }


    int8_t cmode = mode;
    //Log(3, "o CHAT: mode=%lX, from_oID=%08lX, msg='%s', to_ID=%08lX\n", cmode, id, msg, to);

    chat_msg.to = to;
    chat_msg.from = id;
    chat_msg.channel = mode;
    chat_msg.msg = msg;
    chat_msg.suppress = suppress = false;

    sprintf(lastMsg, "%02d%10d", cmode, to);
    strncat(lastMsg, msg, maxMsgLen);
    
    if(ignore_silent && (cmode == 0xD || cmode == 0xE)){
        Log(3, "o Null String");
        return false;
    }

    scriptRun = true;

    int notifyRet = NotifyEventHooks(hChatMessage, (WPARAM)&chat_msg, 0);
    // Someone else wants to suppress the message.
    if (notifyRet && chat_msg.suppress){
        scriptRun = false;
        return true;
    }

    if ((processNPC && id != 0x7F000000) || (!processNPC && id >> 16 == 0x7FFF) ){
    
        nwn_ExecuteScript(chatScript, id);
    }

    if ( cmode == 5 && id == 0x7F000000) {
        nwn_ExecuteScript(servScript, 0);
    }

    scriptRun = false;
    return suppress;
}

bool CNWNXChat::CCMessage(nwn_objid_t objID, const int type, const int subtype, CNWCCMessageData* messageData){
    cc_msg.to = objID;
    cc_msg.type = type;
    cc_msg.subtype = subtype;
    cc_msg.msg_data = messageData;
    cc_msg.suppress = suppress = false;

    scriptRun = true;

    int notifyRet = NotifyEventHooks(hCCMessage, (WPARAM)&cc_msg, 0);
    if (notifyRet && cc_msg.suppress){
        scriptRun = false;
        return cc_msg.suppress;
    }

    nwn_ExecuteScript(ccScript, objID);
    scriptRun = false;

    return suppress;
}
