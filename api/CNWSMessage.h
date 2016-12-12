#ifndef _CNWSMESSAGE_H_
#define _CNWSMESSAGE_H_
#include "nwndef.h"
#include "CNWMessage.h"
#include "CExoArrayList.h"
#include "CExoLocString.h"
#include "CExoString.h"
#include "Vector.h"
#include "CResRef.h"

class CNWSMessage : public CNWMessage
{
public:
	void AddActiveItemPropertiesToMessage(CNWSItem *, CNWSCreature *);
	int AddAreaOfEffectObjectToMessage(CNWSAreaOfEffectObject *);
	int AddDoorAppearanceToMessage(CNWSDoor *);
	void AddItemAppearanceToMessage(CNWSItem *);
	void AddPlaceableAppearanceToMessage(CNWSPlaceable *);
	int AddTriggerGeometryToMessage(CNWSTrigger *);
	int AssignCreatureLists(CExoArrayList<unsigned long> *, CExoArrayList<unsigned long> *);
	void AssignVisualEffectLists(CExoArrayList<CLoopingVisualEffect *> *, CExoArrayList<CLoopingVisualEffect *> *);
	int CompareCreatureLists(CExoArrayList<unsigned long> *, CExoArrayList<unsigned long> *);
	int CompareVisualEffectLists(CExoArrayList<CLoopingVisualEffect *> *, CExoArrayList<CLoopingVisualEffect *> *);
	unsigned long ComputeAppearanceUpdateRequired(CNWSObject *, CLastUpdateObject *);
	int ComputeGameObjectUpdateForCategory(unsigned long, unsigned long, CNWSPlayer *, CNWSObject *, CGameObjectArray *, CNWSPlayerLUOSortedObjectList *, int);
	void ComputeGameObjectUpdateForObject(CNWSPlayer *, CNWSObject *, CGameObjectArray *, unsigned long);
	int ComputeGameObjectUpdateForYourselfToo(CNWSPlayer *, CNWSObject *, CGameObjectArray *, unsigned long);
	int ComputeGameObjectUpdateForYourself(CNWSPlayer *, CNWSObject *, CGameObjectArray *, unsigned long);
	int ComputeInventoryUpdateRequired(CNWSPlayer *, unsigned long, CNWSPlayerInventoryGUI *);
	int ComputeLastUpdate_ActionQueue(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	unsigned short ComputeLastUpdate_AssociateState(CNWSCreature *);
	int ComputeLastUpdate_AutoMap(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	int ComputeLastUpdate_CompareSpellLikeAbility(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	int ComputeLastUpdate_GuiEffectIcons(CNWSCreature *, CExoArrayList<CEffectIconObject *> *, int);
	int ComputeLastUpdate_GuiFeats(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	int ComputeLastUpdate_GuiKnownSpellUses(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	int ComputeLastUpdate_GuiKnownSpells(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	int ComputeLastUpdate_GuiMemorizedSpells(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	int ComputeLastUpdate_GuiNumberMemorizedSpells(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	int ComputeLastUpdate_GuiSkills(CNWSCreature *);
	unsigned long ComputeLastUpdate_PlayerState(CNWSCreature *);
	void ComputeLastUpdate_StoreUpdateSpellLikeAbility(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	void ComputeLastUpdate_WriteSpellLikeAbility(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	unsigned long ComputeNumAutoMapUpdatesRequired(CNWSCreature *, CNWSPlayerLastUpdateObject *, unsigned long *);
	int ComputeQuickbarItemUseCountUpdateRequired(CNWSObject *, CLastUpdateObject *);
	int ComputeRepositoryUpdateRequired(CNWSPlayer *, CExoLinkedListNode *, CExoLinkedListNode *);
	unsigned long ComputeUpdateRequired(CNWSPlayer *, CNWSObject *, CLastUpdateObject *, int);
	int ComputeVisibilityLists(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	CLastUpdateObject * CreateNewLastUpdateObject(CNWSPlayer *, CNWSObject *, unsigned long *, unsigned long *);
	void DeleteLastUpdateObjectsForObject(CNWSPlayer *, unsigned long);
	void DeleteLastUpdateObjectsInOtherAreas(CNWSPlayer *);
	static int GetLocStringServer(unsigned long, CExoLocString, CExoLocString, CExoString &, float &, unsigned char);
	int HandlePlayerToServerAreaMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerBarter_AcceptTrade(CNWSPlayer *);
	int HandlePlayerToServerBarter_AddItem(CNWSPlayer *);
	int HandlePlayerToServerBarter_CloseBarter(CNWSPlayer *);
	int HandlePlayerToServerBarter_LockList(CNWSPlayer *);
	int HandlePlayerToServerBarter_MoveItem(CNWSPlayer *);
	int HandlePlayerToServerBarter_RemoveItem(CNWSPlayer *);
	int HandlePlayerToServerBarter_StartBarter(CNWSPlayer *);
	int HandlePlayerToServerBarter_Window(CNWSPlayer *);
	int HandlePlayerToServerBarter(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerCharListMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerCharacterDownload(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerCharacterSheetMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerChatMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerCheatMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerCutscene(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerDialogMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerDungeonMasterMessage(CNWSPlayer *, unsigned char, int);
	int HandlePlayerToServerGameObjectUpdate(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerGoldMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerGroupInputMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerGroupInputWalkToWaypoint(CNWSPlayer *, CNWSCreature *, unsigned long, float, float, float, unsigned char, int, int, unsigned long);
	int HandlePlayerToServerGuiContainerMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerGuiInventoryMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerGuiQuickbar_SetButton(CNWSPlayer *, unsigned char, unsigned char);
	int HandlePlayerToServerGuiQuickbar(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerInputAbortDriveControl(CNWSPlayer *);
	int HandlePlayerToServerInputCancelGuiTimingEvent(CNWSPlayer *);
	int HandlePlayerToServerInputDriveControl(CNWSPlayer *);
	int HandlePlayerToServerInputMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerInputWalkToWaypoint(CNWSPlayer *);
	int HandlePlayerToServerInventoryMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerJournalMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerLevelUpMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerLoginMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerMapPinChangePin(CNWSPlayer *);
	int HandlePlayerToServerMapPinDestroyMapPin(CNWSPlayer *);
	int HandlePlayerToServerMapPinMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerMapPinSetMapPinAt(CNWSPlayer *);
	int HandlePlayerToServerMessage(unsigned long, unsigned char *, unsigned long);
	int HandlePlayerToServerModuleMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerPVPListOperations(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerParty(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerPlayModuleCharacterList_Start(CNWSPlayer *);
	int HandlePlayerToServerPlayModuleCharacterList_Stop(CNWSPlayer *);
	int HandlePlayerToServerPlayModuleCharacterList(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerPlayerDeath(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerPlayerList(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerPortal(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerQuickChatMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerServerChatMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerServerStatusMessage(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerShutDownServer(CNWSPlayer *, unsigned char);
	int HandlePlayerToServerStoreMessage(CNWSPlayer *, unsigned char);
	int HandleServerAdminToServerMessage(unsigned long, unsigned char *, unsigned long);
	int HasValidString(CExoLocString &, unsigned char);
	int ParseGetBool(unsigned char *, unsigned long, int &);
	int ParseGetString(unsigned char *, unsigned long, CExoString &, unsigned long);
	int ParseToken(char const *, unsigned char **, unsigned long &, int);
	unsigned long ReadOBJECTIDServer();
	unsigned long SelectCategoryForGameObject(CGameObject *, CNWSObject *);
	int SendPlayerToServerGuiInventory_Status(CNWSPlayer *, int, unsigned long);
	int SendServerPlayerItemUpdate_DestroyItem(CNWSPlayer *, unsigned long);
	int SendServerToAllPlayersCreatureUpdate_StripEquippedItems(unsigned long, int, int);
	int SendServerToPlayerAIActionPlaySound(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerActivatePortal(unsigned long, CExoString, CExoString, CExoString, int);
	int SendServerToPlayerAmbientBattleMusicChange(unsigned long, int);
	int SendServerToPlayerAmbientBattleMusicPlay(unsigned long, int);
	int SendServerToPlayerAmbientMusicChangeTrack(unsigned long, int, int);
	int SendServerToPlayerAmbientMusicPlay(unsigned long, int);
	int SendServerToPlayerAmbientMusicSetDelay(unsigned long, int);
	int SendServerToPlayerAmbientSoundLoopChange(unsigned long, int, int);
	int SendServerToPlayerAmbientSoundLoopPlay(unsigned long, int);
	int SendServerToPlayerAmbientSoundVolumeChange(unsigned long, int, int);
	int SendServerToPlayerArea_ChangeDayNight(CNWSPlayer *, int, float);
	int SendServerToPlayerArea_ClientArea(CNWSPlayer *, CNWSArea *, float, float, float, Vector const &, int);
	int SendServerToPlayerArea_RecomputeStaticLighting(unsigned long);
	int SendServerToPlayerArea_VisualEffect(CNWSPlayer *, unsigned short, Vector);
	int SendServerToPlayerArea_Weather(CNWSPlayer *, unsigned char, int);
	int SendServerToPlayerBarterAcceptTrade(unsigned long, unsigned long, int, int);
	int SendServerToPlayerBarterCloseBarter(unsigned long, unsigned long, int);
	int SendServerToPlayerBarterLockList(unsigned long, unsigned long, int, int);
	int SendServerToPlayerBarterReject(unsigned long, unsigned char, unsigned char, unsigned long);
	int SendServerToPlayerBarterStartBarter(unsigned long, unsigned long, unsigned long, unsigned long);
	int SendServerToPlayerCCMessage(unsigned long, unsigned char, CNWCCMessageData *, CNWSCombatAttackData *);
	int SendServerToPlayerCamera_ChangeLocation(CNWSPlayer *, int, float, float, float, int);
	int SendServerToPlayerCamera_LockDistance(CNWSPlayer *, int);
	int SendServerToPlayerCamera_LockPitch(CNWSPlayer *, int);
	int SendServerToPlayerCamera_LockYaw(CNWSPlayer *, int);
	int SendServerToPlayerCamera_Restore(CNWSPlayer *);
	int SendServerToPlayerCamera_SetHeight(CNWSPlayer *, float);
	int SendServerToPlayerCamera_SetMode(CNWSPlayer *, unsigned char);
	int SendServerToPlayerCamera_Store(CNWSPlayer *);
	int SendServerToPlayerCharList(CNWSPlayer *);
	int SendServerToPlayerCharacterDownloadFail(CNWSPlayer *);
	int SendServerToPlayerCharacterDownloadReply(CNWSPlayer *);
	int SendServerToPlayerChatMessage(unsigned char, unsigned long, CExoString, unsigned long, CExoString const &);
	int SendServerToPlayerChatMultiLangMessage(unsigned char, unsigned long, CExoLocString, unsigned long, unsigned char, unsigned long *, unsigned long, int, CResRef const &, int, unsigned long);
	int SendServerToPlayerChatMultiLang_Helper(unsigned long, unsigned char, unsigned long, CExoLocString, unsigned long, unsigned char, int, CResRef const &, int, unsigned long);
	int SendServerToPlayerChatStrRefMessage(unsigned char, unsigned long, unsigned long);
	int SendServerToPlayerChatStrRefStatusMessage(unsigned char, unsigned long, unsigned long);
	int SendServerToPlayerChat_DM_Silent_Shout(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerChat_DM_Talk(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerChat_DM_Whisper(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerChat_Party(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerChat_ServerTell(unsigned long, CExoString);
	int SendServerToPlayerChat_Shout(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerChat_Silent_Shout(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerChat_StrRef(unsigned long, unsigned long, unsigned char, unsigned long);
	int SendServerToPlayerChat_Talk(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerChat_Tell(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerChat_Whisper(unsigned long, unsigned long, CExoString);
	int SendServerToPlayerCheatDebugMode(int);
	int SendServerToPlayerCheatNasty(unsigned long, unsigned long);
	int SendServerToPlayerCheatPonyRide(unsigned long, unsigned long);
	int SendServerToPlayerCheatRainOfCows(unsigned long, unsigned long);
	int SendServerToPlayerCloseStoreInventory(CNWSPlayer *);
	int SendServerToPlayerCombatRoundStarted(CNWSPlayer *);
	int SendServerToPlayerCutscene_BlackScreen(CNWSPlayer *);
	int SendServerToPlayerCutscene_FadeFromBlack(CNWSPlayer *, float);
	int SendServerToPlayerCutscene_FadeToBlack(CNWSPlayer *, float);
	int SendServerToPlayerCutscene_HideGui(CNWSPlayer *, int);
	int SendServerToPlayerCutscene_Status(CNWSPlayer *, int, int);
	int SendServerToPlayerCutscene_StopFade(CNWSPlayer *);
	int SendServerToPlayerDebugInfo_Area(CNWSPlayer *, unsigned long);
	int SendServerToPlayerDebugInfo_Creature(CNWSPlayer *, unsigned long);
	int SendServerToPlayerDebugInfo_Door(CNWSPlayer *, unsigned long);
	int SendServerToPlayerDebugInfo_Item(CNWSPlayer *, unsigned long);
	int SendServerToPlayerDebugInfo_Placeable(CNWSPlayer *, unsigned long);
	int SendServerToPlayerDebugInfo_Trigger(CNWSPlayer *, unsigned long);
	int SendServerToPlayerDestroyDeathGUI(unsigned long);
	int SendServerToPlayerDialogClose(unsigned long);
	int SendServerToPlayerDialogEntry(unsigned long, unsigned long, unsigned long, CExoLocString, unsigned long, unsigned char);
	int SendServerToPlayerDialogReplies(unsigned long, CExoLocString *, unsigned long *, unsigned long, unsigned long, unsigned long, unsigned char, int, unsigned long, int);
	int SendServerToPlayerDialogReplyChosen(unsigned long, unsigned long, unsigned long, CExoLocString, unsigned long, unsigned char, int);
	int SendServerToPlayerDungeonMasterAreaList(CNWSPlayer *);
	int SendServerToPlayerDungeonMasterAreaList(unsigned long);
	int SendServerToPlayerDungeonMasterCreatorLists(CNWSPlayer *);
	int SendServerToPlayerDungeonMasterObjectList(CNWSPlayer *, unsigned long, int);
	int SendServerToPlayerDungeonMasterSearchByIdResult(CNWSPlayer *, unsigned long, int, unsigned long);
	int SendServerToPlayerDungeonMasterSearchByTagResult(CNWSPlayer *, CExoString const &, int, unsigned long);
	int SendServerToPlayerDungeonMasterUpdatePartyList(CNWSPlayer *);
	int SendServerToPlayerDungeonMasterUpdatePartyList(unsigned long);
	int SendServerToPlayerDungeonMastersDifficultyChange(int);
	int SendServerToPlayerExamineGui_CreatureData(CNWSPlayer *, unsigned long);
	int SendServerToPlayerExamineGui_DoorData(CNWSPlayer *, unsigned long);
	int SendServerToPlayerExamineGui_ItemData(CNWSPlayer *, unsigned long);
	int SendServerToPlayerExamineGui_PlaceableData(CNWSPlayer *, unsigned long);
	int SendServerToPlayerExamineGui_TrapData(CNWSPlayer *, unsigned long, CNWSCreature *, int);
	int SendServerToPlayerGUICharacterSheet_NotPermitted(unsigned long, unsigned long);
	int SendServerToPlayerGameObjUpdateFloatyText(CNWSPlayer *, unsigned long, unsigned long);
	int SendServerToPlayerGameObjUpdateVisEffect(CNWSPlayer *, unsigned short, unsigned long, unsigned long, unsigned char, unsigned char, Vector, float);
	int SendServerToPlayerGameObjUpdate_ObjControl(unsigned long, unsigned long);
	int SendServerToPlayerGameObjUpdate(CNWSPlayer *);
	int SendServerToPlayerGameObjUpdate(CNWSPlayer *, unsigned long);
	int SendServerToPlayerGuiContainerObject_Status(CNWSPlayer *, unsigned long, int);
	int SendServerToPlayerGuiQuickbar_SetButton(CNWSPlayer *, unsigned char, int);
	int SendServerToPlayerGuiTimingEvent(CNWSPlayer *, int, unsigned char, unsigned long);
	int SendServerToPlayerInventory_ConfirmDrop(unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_DropCancel(unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_Drop(unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_EquipCancel(unsigned long, unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_Equip(unsigned long, unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_LearnScroll(unsigned long, unsigned long, unsigned char);
	int SendServerToPlayerInventory_PickupCancel(unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_Pickup(unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_RepositoryMoveCancel(unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_RepositoryMove(unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_SelectPage(unsigned long, unsigned char);
	int SendServerToPlayerInventory_SelectPanel(unsigned long, unsigned char);
	int SendServerToPlayerInventory_UnequipCancel(unsigned long, unsigned long, int);
	int SendServerToPlayerInventory_Unequip(unsigned long, unsigned long, int);
	int SendServerToPlayerJournalAddQuest(CNWSPlayer *, CExoString, int, unsigned long, unsigned short, int, unsigned long, unsigned long, CExoLocString, CExoLocString);
	int SendServerToPlayerJournalAddWorldStrref(CNWSPlayer *, unsigned long, unsigned long, unsigned long, unsigned long);
	int SendServerToPlayerJournalAddWorld(CNWSPlayer *, int, CExoString, CExoString, unsigned long, unsigned long);
	int SendServerToPlayerJournalDeleteWorldAll(CNWSPlayer *);
	int SendServerToPlayerJournalDeleteWorldStrref(CNWSPlayer *, unsigned long);
	int SendServerToPlayerJournalDeleteWorld(CNWSPlayer *, int);
	int SendServerToPlayerJournalFullUpdateNotNeeded(CNWSPlayer *, int);
	int SendServerToPlayerJournalFullUpdate(CNWSPlayer *);
	int SendServerToPlayerJournalRemoveQuest(CNWSPlayer *, CExoString);
	int SendServerToPlayerJournalSetQuestPicture(CNWSPlayer *, CExoString, int);
	int SendServerToPlayerJournalUpdated(CNWSPlayer *, int, int, CExoLocString);
	int SendServerToPlayerLevelUp_Begin(unsigned long, CNWSCreature *);
	int SendServerToPlayerLevelUp_Confirmation(unsigned long, int);
	int SendServerToPlayerLoadBar_EndStallEvent(unsigned long, unsigned long);
	int SendServerToPlayerLoadBar_StartStallEvent(unsigned long);
	int SendServerToPlayerLoadBar_UpdateStallEvent(unsigned long, unsigned long);
	int SendServerToPlayerLogin_CharacterQuery(CNWSPlayer *, unsigned char &, int *, unsigned char *, unsigned long &);
	int SendServerToPlayerLogin_Confirm(unsigned long);
	int SendServerToPlayerLogin_Fail(unsigned long, unsigned long);
	int SendServerToPlayerLogin_GetWaypoint(unsigned long);
	int SendServerToPlayerLogin_NeedCharacter(unsigned long);
	int SendServerToPlayerMapPinAdded(CNWSPlayer *, Vector, CExoString, unsigned long);
	int SendServerToPlayerMapPinCreated(CNWSPlayer *, unsigned long, Vector, CExoLocString, int);
	int SendServerToPlayerMapPinEnabled(CNWSPlayer *, unsigned long, int);
	int SendServerToPlayerMessage(unsigned long, unsigned char, unsigned char, unsigned char *, unsigned long);
	int SendServerToPlayerModuleUpdate_Time(CNWSPlayer *, unsigned char, unsigned long, unsigned long, unsigned long, unsigned long, unsigned char, unsigned long);
	int SendServerToPlayerModule_DumpPlayer(CNWSPlayer *);
	int SendServerToPlayerModule_EndGame(unsigned long, CExoString const &);
	int SendServerToPlayerModule_EndStartNewModule(int);
	int SendServerToPlayerModule_ExportReply(CNWSPlayer *);
	int SendServerToPlayerModule_Info(unsigned long);
	int SendServerToPlayerModule_Loading(CNWSPlayer *);
	int SendServerToPlayerModule_SaveGameStatus(unsigned char);
	int SendServerToPlayerModule_SetPauseState(unsigned char, int);
	int SendServerToPlayerModule_StartStartNewModule();
	int SendServerToPlayerOpenStoreInventory(CNWSPlayer *, unsigned long, unsigned char);
	int SendServerToPlayerPVP_Attitude_Change(unsigned long, unsigned long, int);
	int SendServerToPlayerPartyBar_PanelButtonFlash(unsigned long, unsigned char, int);
	int SendServerToPlayerParty_Invite(unsigned long, unsigned long);
	int SendServerToPlayerParty_List(unsigned long, int, unsigned long *, unsigned char, unsigned long);
	int SendServerToPlayerParty_TransferObjectControl(unsigned long, unsigned long, unsigned long);
	int SendServerToPlayerPlaceableUpdate_Useable(CNWSPlaceable *);
	int SendServerToPlayerPlayModuleCharacterListResponse(unsigned long, unsigned long, int);
	int SendServerToPlayerPlayerList_Add(unsigned long, CNWSPlayer *);
	int SendServerToPlayerPlayerList_All(CNWSPlayer *);
	int SendServerToPlayerPlayerList_Delete(unsigned long, CNWSPlayer *);
	int SendServerToPlayerPlayerList_ReauthorizeCDKey(unsigned long, CExoString const &, CExoString const &);
	int SendServerToPlayerPolymorph(CNWSPlayer *, unsigned long, int, int);
	int SendServerToPlayerPopUpGUIPanel(unsigned long, int, int, int, int, CExoString);
	int SendServerToPlayerQuickChatMessage(unsigned long, unsigned short);
	int SendServerToPlayerQuickChat(unsigned long, unsigned long, unsigned short);
	int SendServerToPlayerSafeProjectile(CNWSPlayer *, unsigned long, unsigned long, Vector, Vector, unsigned long, unsigned char, unsigned long, unsigned char, unsigned char, unsigned char);
	int SendServerToPlayerSaveLoad_Status(CNWSPlayer *, unsigned char, unsigned long);
	int SendServerToPlayerServerStatus_Status(unsigned long);
	int SendServerToPlayerSetCustomTokenList(unsigned long);
	int SendServerToPlayerSetCustomToken(unsigned long, int, CExoString const &);
	int SendServerToPlayerShutDownServer(unsigned long, unsigned long);
	int SendServerToPlayerSoundObject_ChangePosition(CNWSPlayer *, unsigned long, Vector);
	int SendServerToPlayerSoundObject_ChangeVolume(CNWSPlayer *, unsigned long, int);
	int SendServerToPlayerSoundObject_Play(CNWSPlayer *, unsigned long);
	int SendServerToPlayerSoundObject_Stop(CNWSPlayer *, unsigned long);
	int SendServerToPlayerStoreConfirmTransaction(CNWSPlayer *, int, unsigned long, int);
	int SendServerToPlayerStringMessage(unsigned long, unsigned char, CExoString);
	int SendServerToPlayerUpdateActiveItemPropertiesUses(CNWSPlayer *, unsigned long, unsigned char, unsigned char, unsigned char *);
	int SendServerToPlayerUpdateActiveItemProperties(CNWSPlayer *, CNWSItem *);
	int SendServerToPlayerUpdateBlackoutEffect(CNWSPlayer *, int);
	int SendServerToPlayerUpdateCharResponse(CNWSPlayer *, unsigned char, CResRef);
	int SendServerToPlayerUpdateFogAmount(unsigned char, unsigned char, unsigned long);
	int SendServerToPlayerUpdateFogColor(unsigned long, unsigned long, unsigned long);
	int SendServerToPlayerUpdateItemName(CNWSPlayer *, CNWSItem *);
	int SendServerToPlayerUpdateSkyBox(int, unsigned long);
	int SendServerToPlayerVoiceChat_Play(CNWSPlayer *, unsigned long, unsigned char);
	int SendServerToPlayerWhirlwindAttackDamage(CNWSPlayer *, CNWSCreature *);
	int SendServerToPlayerWhirlwindAttack(CNWSPlayer *, CNWSCreature *);
	void SendServerToServerAdminBannedList(unsigned long);
	int SendServerToServerAdminMessage(unsigned long, CExoString);
	void SendServerToServerAdminModuleList(unsigned long);
	void SendServerToServerAdminPlayerList(unsigned long);
	void SendServerToServerAdminPortalList(unsigned long);
	void SendServerToServerAdminSaveGameList(unsigned long);
	void SendServerToServerAdminSaveStatus(unsigned long, unsigned char);
	void SendServerToServerAdminServerSettings(unsigned long);
	void SendServerToServerAdminServerStatus(unsigned long);
	CNWSPlayerLUOSortedObjectList * SortObjectsForGameObjectUpdate(CNWSPlayer *, CNWSObject *, CGameObjectArray *, int *);
	void StoreValuesInLastPlayerUpdateObject(CNWSPlayer *, CNWSPlayerLastUpdateObject *, CLastUpdateObject *, unsigned short);
	int StoreValuesInLastUpdateObject(CNWSPlayer *, CLastUpdateObject *, CNWSObject *, unsigned long, unsigned long);
	void StoreValuesInLastUpdatePartyObject(CNWSCreature *, CLastUpdatePartyObject *, CNWSCreature *, unsigned long);
	int TestObjectUpdateDifferences(CNWSPlayer *, CNWSObject *, CLastUpdateObject **, unsigned long *, unsigned long *);
	int TestObjectVisible(CNWSObject *, CNWSObject *);
	int TestPartyObjectUpdateDifferences(CNWSPlayer *, CNWSCreature *, CLastUpdatePartyObject **, unsigned long *);
	void TestPlayerUpdateDifferences(CNWSPlayer *, CNWSPlayerLastUpdateObject *, CLastUpdateObject *, unsigned short &);
	void UpdateLastUpdateActionQueue(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	void UpdateLastUpdateAutoMap(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	void UpdateLastUpdateInventory(CNWSPlayer *, unsigned long, CNWSPlayerInventoryGUI *);
	void UpdateLastUpdateObjectAppearance(CNWSObject *, CLastUpdateObject *, unsigned long);
	void UpdateLastUpdateObject(CNWSPlayer *, CNWSObject *, CLastUpdateObject *, unsigned long);
	void UpdateLastUpdateVisibilityList(CNWSCreature *, CNWSPlayerLastUpdateObject *);
	void UpdateLastUpdate_GuiEffectIcons(CNWSCreature *, CExoArrayList<CEffectIconObject *> *, int);
	void WriteCExoLocStringServer(CExoLocString &, unsigned char);
	void WriteGameObjUpdate_CharacterSheet(CNWSPlayer *, unsigned long);
	int WriteGameObjUpdate_DungeonMasterAIState(CNWSPlayer *);
	void WriteGameObjUpdate_MajorGUIPanels_HenchmanInventoryData(CNWSPlayer *);
	void WriteGameObjUpdate_MajorGUIPanels_Inventory(CNWSPlayer *, CNWSPlayerInventoryGUI *);
	void WriteGameObjUpdate_MajorGUIPanels(CNWSPlayer *);
	void WriteGameObjUpdate_MinorGUIPanels(CNWSPlayer *);
	int WriteGameObjUpdate_PartyAIState(CNWSPlayer *);
	void WriteGameObjUpdate_PlayerUpdate(CNWSPlayer *, CNWSPlayerLastUpdateObject *, CLastUpdateObject *, unsigned short);
	void WriteGameObjUpdate_UpdateAppearance(CNWSObject *, CLastUpdateObject *, unsigned long);
	void WriteGameObjUpdate_UpdateObject(CNWSPlayer *, CNWSObject *, CLastUpdateObject *, unsigned long, unsigned long);
	void WriteGameObjUpdate_UpdateQuickbarItemUseCount(CNWSObject *, CLastUpdateObject *);
	int WriteGameObjUpdate_WorkRemaining(CNWSObject *, CNWSArea *, int, int);
	int WriteGameObjUpdate_WriteInventorySlotAdd(CNWSCreature *, CNWSItem *, unsigned long);
	int WriteGameObjUpdate_WriteInventorySlotDelete(CNWSCreature *, unsigned long);
	int WriteGameObjUpdate_WriteInventorySlotUpdate(unsigned long, unsigned long);
	void WriteGuiEffectIconsUpdate(CNWSCreature *, CExoArrayList<CEffectIconObject *> *, int);
	void WriteOBJECTIDServer(unsigned long);
	void WriteRepositoryUpdate(CNWSPlayer *, CNWSObject *, CItemRepository *, CNWSPlayerLUOInventory *, unsigned char, char, unsigned char);
	void WriteStoreInventoryUpdate(CNWSPlayer *, CNWSStore *);
	~CNWSMessage();
	CNWSMessage();

};
#endif
