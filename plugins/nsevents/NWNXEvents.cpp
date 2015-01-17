/***************************************************************************
    NWNX Events - Implementation of the CNWNXEvents class.
    (c) 2006 virusman (virusman@virusman.ru)

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

#include "NWNXEvents.h"

extern PLUGINLINK *pluginLink;
int (*CNWSObject_dtor)(CNWSObject *, int8_t) = NULL;

#define MLIT(str, lit) \
    strncmp((str), "" lit, (sizeof (lit)/sizeof(char)) - 1) == 0

CNWNXEvents::CNWNXEvents()
{
    int i;

    for (i = 0; i < NUM_EVENT_TYPES; i++)
        eventScripts[i] = strdup("vir_events");

    confKey = "EVENTS";
    conv_script_type = None;
    scriptRun = false;
    suppressForgetAggression = true;
    last_cmd = -1;
    last_arg_count = -1;
}

CNWNXEvents::~CNWNXEvents()
{
}

const char *CNWNXEvents::GetConf(char* key){
    return (*nwnxConfig)[confKey][key].c_str();
}

bool CNWNXEvents::OnCreate (gline *config, const char* LogDir)
{
    char log[128];
    bool validate = true, startServer = true, enableUnsafe = false;

    // call the base class function
    sprintf (log, "%s/nwnx_nsevents.txt", LogDir);
    if (!CNWNXBase::OnCreate(config,log))
        return false;

    // write copy information to the log file
    Log (0, "NWNX Events version 1.3.3 for Linux.\n");
    Log (0, "(c) 2006-2011 by virusman (virusman@virusman.ru)\n");

    if(nwnxConfig->exists(confKey)) {
        const char *script = GetConf("event_script");
        if(strlen(script)){
            int i;
            for (i = 0; i < NUM_EVENT_TYPES; i++) {
                eventScripts[i]     = strdup(script);
                eventScripts[i][16] = 0;
            }
        }
    }

    // Plugin Events
    if(!pluginLink){
	Log (0, "Plugin link not accessible\n");
    }
    else {
        Log (0, "Plugin link: %08lX\n", pluginLink);

        hEvent = CreateHookableEvent("NWNX/Events/Event");
	hExamineEvent = CreateHookableEvent("NWNX/Events/ExamineEvent");
        hConversationEvent = CreateHookableEvent("NWNX/Events/ConversationEvent");

    }

    return hook_functions();
}

char* CNWNXEvents::OnRequest (char* gameObject, char* Request, char* Parameters)
{
    Log(2,"Request: \"%s\"\n",Request);
    Log(2,"Params:  \"%s\"\n",Parameters);

    //    if (MLIT(Request, "GET_SCRIPT_RETURN_VALUE") && strlen(Parameters) > 2){
    //    sprintf(Parameters, "%d", GetRunScriptReturnValue());
    //}
    if (conv_script_type == Conditional){
        if(MLIT(Request, "GET_NODE_ID") && strlen(Parameters) > 2){
            sprintf(Parameters, "%d", nCurrentNodeID);
        }
        else if (MLIT(Request, "GET_ABSOLUTE_NODE_ID") && strlen(Parameters) > 2){
            sprintf(Parameters, "%d", nCurrentAbsoluteNodeID);
        }
        else if (MLIT(Request, "GET_NODE_TYPE") && strlen(Parameters) > 1){
            sprintf(Parameters, "%d", nNodeType);
        }
        else if (MLIT(Request, "GET_NODE_TEXT") && pConversation){
            int nLocaleID = atoi(Parameters);
            const char *pText = NULL;

            if(nNodeType == StartingNode || nNodeType == EntryNode){
                CDialogReply *pEntry = &pConversation->entries[nCurrentAbsoluteNodeID].info;
                CExoLocString *pNodeText = &pEntry->text;
                pText = nwn_GetCExoLocStringText(pNodeText, nLocaleID);
            }
            else if(nNodeType == ReplyNode){
                CDialogReply *pReply = &pConversation->replies[nCurrentAbsoluteNodeID];
                CExoLocString *pNodeText = &pReply->text;
                pText = nwn_GetCExoLocStringText(pNodeText, nLocaleID);
            }
            else
                return NULL;

            if(pText)
                return strdup(pText);
        }
        else if (MLIT(Request, "SET_NODE_TEXT") && pConversation){
            int nLocaleID;
            char *nLastDelimiter = strrchr(Parameters, '¬');
            CExoLocStringElement *pLangEntry = NULL;

            if (!nLastDelimiter || (nLastDelimiter-Parameters) < 0){
                Log(0, "o nLastDelimiter error\n");
                return NULL;
            }

            if(sscanf(Parameters, "%d¬", &nLocaleID) < 1) {
                Log(0, "o sscanf error\n");
                return NULL;
            }

            if(nNodeType == StartingNode || nNodeType == EntryNode){
                CDialogReply *pEntry = &pConversation->entries[nCurrentAbsoluteNodeID].info;
                CExoLocString *pNodeText = &pEntry->text;
                pLangEntry = nwn_GetCExoLocStringElement(pNodeText, nLocaleID);

            }
            else if(nNodeType == ReplyNode){
                CDialogReply *pReply = &pConversation->replies[nCurrentAbsoluteNodeID];
                CExoLocString *pNodeText = &pReply->text;
                pLangEntry = nwn_GetCExoLocStringElement(pNodeText, nLocaleID);
            }

            if(pLangEntry && pLangEntry->text.text){
                free(pLangEntry->text.text);
                pLangEntry->text.text = strdup(nLastDelimiter+1);
                pLangEntry->text.len = strlen(nLastDelimiter);
            }
            return NULL;
        }
    }
    else if (conv_script_type == Action){
        if (MLIT(Request, "GET_SELECTED_NODE_ID") && strlen(Parameters) > 1){
            sprintf(Parameters, "%d", nSelectedNodeID);
        }
        else if (MLIT(Request, "GET_SELECTED_ABSOLUTE_NODE_ID") && strlen(Parameters) > 1){
            sprintf(Parameters, "%d", nSelectedAbsoluteNodeID);
        }
        else if (MLIT(Request, "GET_SELECTED_NODE_TEXT") && pConversation){
            int nLocaleID = atoi(Parameters);
            CDialogReply *pReply = &pConversation->replies[nSelectedAbsoluteNodeID];
            CExoLocString *pNodeText = &pReply->text;
            const char *pText = nwn_GetCExoLocStringText(pNodeText, nLocaleID);

            if(pText) return strdup(pText);
        }
    }

    if (!scriptRun) {
        if (MLIT(Request, "SET_EVENT_HANDLER_")) {
            int nHandler = atoi(Request + 18);

            if (nHandler < 0 || nHandler >= NUM_EVENT_TYPES) {
                *Parameters = 0;
            }
            else if (nHandler == EVENT_TYPE_ALL) {
                int i;

                for (i = 0; i < NUM_EVENT_TYPES; i++) {
                    if (eventScripts[i] != NULL)
                        free(eventScripts[i]);

                    if (strlen(Parameters) > 1 && strlen(Parameters) <= 16)
                        eventScripts[i] = strdup(Parameters);
                    else
                        eventScripts[i] = NULL;
                }
            }
            else {
                if (eventScripts[nHandler] != NULL) {
                    free(eventScripts[nHandler]);
                    eventScripts[nHandler] = NULL;
                }

                if (strlen(Parameters) > 1 && strlen(Parameters) <= 16)
                    eventScripts[nHandler] = strdup(Parameters);
                else
                    *Parameters = 0;
            }
        }

        return NULL;
    }

    //The following functions are accessible only from event script
    if ((MLIT(Request, "GET_EVENT_ID") || MLIT(Request, "GETEVENTID"))
        && strlen(Parameters) > 1){

        sprintf(Parameters, "%d", event.type);
    }
    else if (MLIT(Request, "GET_EVENT_SUBID") && strlen(Parameters) > 1){
        sprintf(Parameters, "%d", event.subtype);
    }
    else if (MLIT(Request, "GET_EVENT_POSITION") && strlen(Parameters) > 24){
        snprintf(Parameters, strlen(Parameters), "%f~%f~%f", event.loc.x, event.loc.y, event.loc.z);
    }
    else if (MLIT(Request, "BYPASS")){
        int bypass, use_result;
        int num = sscanf(Parameters, "%d~%d", &bypass, &use_result);

        if (num < 1) {
            event.bypass = false;
            event.use_result = false;
            return NULL;
        }
        else if (num == 2){
            event.use_result == !!use_result;
        }

        event.bypass = !!bypass;

    }
    else if (MLIT(Request, "RETURN")){
        event.result = atoi(Parameters);
    }
    return NULL;
}

unsigned long CNWNXEvents::OnRequestObject (char *gameObject, char* Request){
    Log(2,"ObjRequest: \"%s\"\n",Request);

    //The following functions are accessible only from event script
    if (!scriptRun)
        return OBJECT_INVALID;

    if (MLIT(Request, "TARGET")){
        return event.target;
    }
    else if (MLIT(Request, "ITEM")){
        return event.item;
    }

    return OBJECT_INVALID;
}

bool CNWNXEvents::OnRelease (){
    Log (0, "o Shutdown.\n");
    return true;
}

void CNWNXEvents::FireEvent(nwn_objid_t obj, int type, int subtype, nwn_objid_t target, Vector loc, nwn_objid_t item){
    if (type < 0 || type >= NUM_EVENT_TYPES)
        return;

    event.type = type;
    event.subtype;
    event.object = obj;
    event.target = target;
    event.item = item;
    event.loc = loc;
    event.bypass = false;
    event.use_result = false;

    scriptRun = true;

    int notifyRet = NotifyEventHooks(hEvent, (WPARAM)&event, 0);
    // Someone else wants to bypass the event.
    if (notifyRet || eventScripts[type] == NULL){
	scriptRun = false;
        return;
    }

    Log(3, "o EVENTS: Fired event %d (%08lX). Calling '%s'\n", type, obj, eventScripts[type]);

    nwn_ExecuteScript(eventScripts[type], obj);
    scriptRun = false;

    return;
}

void CNWNXEvents::FireExamineEvent(CNWSMessage *msg, CNWSPlayer *pl, nwn_objid_t obj, int obj_type){
    examine_event.msg    = (CNWMessage*)msg;
    examine_event.pc     = pl;
    examine_event.obj    = obj;
    examine_event.type   = obj_type;
    examine_event.bypass = false;
    examine_event.result = 0;

    scriptRun = true;

    int notifyRet = NotifyEventHooks(hExamineEvent, (WPARAM)&examine_event, 0);

    scriptRun = false;
}

void CNWNXEvents::UpdateConversationScriptType(ConvScriptType type){
    conv_script_type = type;
    NotifyEventHooks(hConversationEvent, (WPARAM)&conv_script_type, 0);
}
