/***************************************************************************
    NWNX Events - interface for the CNWNXEvents class.
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

#ifndef _NWNX_EVENTS_H_
#define _NWNX_EVENTS_H_

#include "talib/nwn/all.h"
#include "NWNXLib.h"

#define EVENT_TYPE_ALL                   0
#define EVENT_TYPE_SAVE_CHAR             1
#define EVENT_TYPE_PICKPOCKET            2
#define EVENT_TYPE_ATTACK                3
#define EVENT_TYPE_USE_ITEM              4
#define EVENT_TYPE_QUICKCHAT             5
#define EVENT_TYPE_EXAMINE               6
#define EVENT_TYPE_USE_SKILL             7
#define EVENT_TYPE_USE_FEAT              8
#define EVENT_TYPE_TOGGLE_MODE           9
#define EVENT_TYPE_CAST_SPELL           10
#define EVENT_TYPE_TOGGLE_PAUSE         11
#define EVENT_TYPE_POSSESS_FAMILIAR     12
#define EVENT_TYPE_VALIDATE_CHARACTER   13
#define EVENT_TYPE_DESTROY_OBJECT       14
#define EVENT_TYPE_PVP_STATE            15

#define NUM_EVENT_TYPES                 16

extern int (*CNWSObject_dtor)(CNWSObject *, int8_t);

bool hook_functions();

void Hook_AddAttackActions(CNWSCreature *cre, nwn_objid_t target, int, int clear_actions, int attack_now);
int Hook_AddCastSpellActions(CNWSCreature *cre, int nSpell, signed int nClassIndex,
			     char nDomainLevel,
			     char nMetaMagic, int a6, Vector vTarget,
			     signed int oTarget, int bTargeted, int a12, int a13,
			     char nProjectilePathType, signed int bInstantSpell,
			     int a16, char a17, char a18);
void Hook_AIActionPickPocket(CNWSCreature* cre, CNWSObjectActionNode *node);
int32_t Hook_CheckScript(CNWSDialog *dlg, CNWSObject *obj, const CResRef *resref);
int32_t Hook_HandleReply(CNWSDialog *dlg, uint32_t a, CNWSObject *obj, uint32_t node_id, int c, uint32_t d);
void Hook_PackCreatureIntoMessage(CNWSPlayer *pl);
void Hook_PossessFamiliar(CNWSCreature *cre);
void Hook_CNWSObject_dtor(CNWSObject *obj, int8_t a);
int32_t Hook_SendServerToPlayerExamineGui_CreatureData(CNWSMessage *msg, CNWSPlayer *pl, nwn_objid_t obj);
int32_t Hook_SendServerToPlayerExamineGui_DoorData(CNWSMessage *msg, CNWSPlayer *pl, nwn_objid_t obj);
int32_t Hook_SendServerToPlayerExamineGui_ItemData(CNWSMessage *msg, CNWSPlayer *pl, nwn_objid_t obj);
int32_t Hook_SendServerToPlayerExamineGui_PlaceableData(CNWSMessage *msg, CNWSPlayer *pl, nwn_objid_t obj);
void Hook_SendServerToPlayerQuickChatMessage(CNWSMessage *msg, nwn_objid_t pc, uint16_t chat);
void Hook_SetPauseState(CServerExoAppInternal *srv, uint8_t unknown, int state);
void Hook_SetPVPPlayerLikesMe(CNWSCreature *cre, nwn_objid_t vs, int state, int a);
void Hook_UseFeat(CNWSCreature *cre, uint16_t feat, uint16_t subfeat, nwn_objid_t target, nwn_objid_t area, Vector *loc);
void Hook_UseItem(CNWSCreature *cre, nwn_objid_t item, uint8_t radial, uint8_t a, nwn_objid_t target, Vector loc, nwn_objid_t area);
void Hook_UseSkill(CNWSCreature *cre, uint8_t skill, uint8_t subskill, nwn_objid_t target, Vector vTarget, nwn_objid_t area, nwn_objid_t item, int arg_24);
int Hook_ValidateCharacter(CNWSPlayer *pl, int *result);
int Hook_ExecuteCommand(CNWVirtualMachineCommands *vm, int cmd, int args);
int Hook_ReportError(CNWVirtualMachineCommands *vm, CExoString *str, int strref);

#include "NWNXBase.h"
#include "nwnx_modules_ext.h"

enum ConvScriptType {None, Action, Conditional};

enum eNodeType {StartingNode, EntryNode, ReplyNode};
static const Vector default_vector = {0,0,0};

class CNWNXEvents : public CNWNXBase
{

public:
    CNWNXEvents();
    ~CNWNXEvents();
    bool OnCreate(gline *config, const char* LogDir);
    const char* GetConf(char *key);
    char* OnRequest(char* gameObject, char* Request, char* Parameters);
    unsigned long OnRequestObject (char *gameObject, char* Request);
    bool OnRelease();

    void FireEvent(nwn_objid_t obj, int type, int subtype = -1, nwn_objid_t target = OBJECT_INVALID,
                   Vector loc = default_vector, nwn_objid_t item = OBJECT_INVALID);
    void FireExamineEvent(CNWSMessage *msg, CNWSPlayer *pl, nwn_objid_t obj, int obj_type);

    void UpdateConversationScriptType(ConvScriptType type);

    char *eventScripts[NUM_EVENT_TYPES];

    Event event;
    EventExamine examine_event;
    ConvScriptType conv_script_type;

    CGameObject *pGameObject;
    //nwn_objid_t nGameObjectID;

    bool scriptRun;

    bool suppressForgetAggression;

    // Conversation Event Info
    CNWSDialog *pConversation;
    int nSelectedNodeID;
    int nSelectedAbsoluteNodeID;
    int nCurrentAbsoluteNodeID;
    int nCurrentNodeID;
    int nNodeType;
    int last_cmd;
    int last_arg_count;
    bool allow_poly_use_item;

private:
    HANDLE hEvent;
    HANDLE hExamineEvent;
    HANDLE hConversationEvent;
};

#endif
