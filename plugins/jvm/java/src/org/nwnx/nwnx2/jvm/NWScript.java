// Generated on Mon Nov 23 10:59:00 +0000 2015 from /var/lib/jenkins/jobs/nwnx_linux/workspace/plugins/jvm/build/funcs.nss

package org.nwnx.nwnx2.jvm;

public class NWScript {
  public NWScript() { }

    /**
     * Attack oAttackee.
     * @param bPassive If this is TRUE, attack is in passive mode.
     */
    public native static synchronized void actionAttack(NWObject oAttackee, boolean bPassive) throws NWNotInContextException;

    /**
     * The action subject will fake casting a spell at lLocation; the conjure and
     * cast animations and visuals will occur, nothing else.
     * @param nSpell
     * @param lTarget
     * @param nProjectilePathType PROJECTILE_PATH_TYPE_*
     */
    public native static synchronized void actionCastFakeSpellAtLocation(int nSpell, NWLocation lTarget, int nProjectilePathType) throws NWNotInContextException;

    /**
     * The action subject will fake casting a spell at oTarget; the conjure and cast
     * animations and visuals will occur, nothing else.
     * @param nSpell
     * @param oTarget
     * @param nProjectilePathType PROJECTILE_PATH_TYPE_*
     */
    public native static synchronized void actionCastFakeSpellAtObject(int nSpell, NWObject oTarget, int nProjectilePathType) throws NWNotInContextException;

    /**
     * Cast spell nSpell at lTargetLocation.
     * @param nSpell SPELL_*
     * @param lTargetLocation
     * @param nMetaMagic METAMAGIC_*
     * @param bCheat If this is TRUE, then the executor of the action doesn't have to be
     * able to cast the spell.
     * @param nProjectilePathType PROJECTILE_PATH_TYPE_*
     * @param bInstantSpell If this is TRUE, the spell is cast immediately; this allows
     * the end-user to simulate
     * a high-level magic user having lots of advance warning of impending trouble.
     */
    public native static synchronized void actionCastSpellAtLocation(int nSpell, NWLocation lTargetLocation, int nMetaMagic, boolean bCheat, int nProjectilePathType, boolean bInstantSpell) throws NWNotInContextException;

    /**
     * This action casts a spell at oTarget.
     * @param nSpell SPELL_*
     * @param oTarget Target for the spell
     * @param nMetamagic METAMAGIC_*
     * @param bCheat If this is TRUE, then the executor of the action doesn't have to be
     * able to cast the spell.
     * @param nDomainLevel TBD - SS
     * @param nProjectilePathType PROJECTILE_PATH_TYPE_*
     * @param bInstantSpell If this is TRUE, the spell is cast immediately. This allows
     * the end-user to simulate a high-level magic-user having lots of advance
     * warning of impending trouble
     */
    public native static synchronized void actionCastSpellAtObject(int nSpell, NWObject oTarget, int nMetaMagic, boolean bCheat, int nDomainLevel, int nProjectilePathType, boolean bInstantSpell) throws NWNotInContextException;

    /**
     * Cause the action subject to close oDoor
     */
    public native static synchronized void actionCloseDoor(NWObject oDoor) throws NWNotInContextException;

    /**
     * Counterspell oCounterSpellTarget.
     */
    public native static synchronized void actionCounterSpell(NWObject oCounterSpellTarget) throws NWNotInContextException;

    /**
     * Equip oItem into nInventorySlot.
     * @param nInventorySlot INVENTORY_SLOT_*
     * * No return value, but if an error occurs the log file will contain
     * "ActionEquipItem failed."
     * 
     * Note:
     * If the creature already has an item equipped in the slot specified, it will be
     * unequipped automatically by the call to ActionEquipItem.
     * 
     * In order for ActionEquipItem to succeed the creature must be able to equip the
     * item oItem normally. This means that:
     * 1) The item is in the creature's inventory.
     * 2) The item must already be identified (if magical).
     * 3) The creature has the level required to equip the item (if magical and ILR is on).
     * 4) The creature possesses the required feats to equip the item (such as weapon proficiencies).
     */
    public native static synchronized void actionEquipItem(NWObject oItem, int nInventorySlot) throws NWNotInContextException;

    /**
     * The creature will equip the melee weapon in its possession that can do the
     * most damage. If no valid melee weapon is found, it will equip the most
     * damaging range weapon. This function should only ever be called in the
     * EndOfCombatRound scripts, because otherwise it would have to stop the combat
     * round to run simulation.
     * @param oVersus You can try to get the most damaging weapon against oVersus
     * @param bOffHand
     */
    public native static synchronized void actionEquipMostDamagingMelee(NWObject oVersus, boolean bOffHand) throws NWNotInContextException;

    /**
     * The creature will equip the range weapon in its possession that can do the
     * most damage.
     * If no valid range weapon can be found, it will equip the most damaging melee
     * weapon.
     * @param oVersus You can try to get the most damaging weapon against oVersus
     */
    public native static synchronized void actionEquipMostDamagingRanged(NWObject oVersus) throws NWNotInContextException;

    /**
     * The creature will equip the armour in its possession that has the highest
     * armour class.
     */
    public native static synchronized void actionEquipMostEffectiveArmor() throws NWNotInContextException;

    /**
     * Makes a player examine the object oExamine. This causes the examination
     * pop-up box to appear for the object specified.
     */
    public native static synchronized void actionExamine(NWObject oExamine) throws NWNotInContextException;

    /**
     * The action subject will follow oFollow until a ClearAllActions() is called.
     * @param oFollow this is the object to be followed
     * @param fFollowDistance follow distance in metres
     * * No return value
     */
    public native static synchronized void actionForceFollowObject(NWObject oFollow, float fFollowDistance) throws NWNotInContextException;

    /**
     * Force the action subject to move to lDestination.
     */
    public native static synchronized void actionForceMoveToLocation(NWLocation lDestination, boolean bRun, float fTimeout) throws NWNotInContextException;

    /**
     * Force the action subject to move to oMoveTo.
     */
    public native static synchronized void actionForceMoveToObject(NWObject oMoveTo, boolean bRun, float fRange, float fTimeout) throws NWNotInContextException;

    /**
     * Give oItem to oGiveTo
     * If oItem is not a valid item, or oGiveTo is not a valid object, nothing will
     * happen.
     */
    public native static synchronized void actionGiveItem(NWObject oItem, NWObject oGiveTo) throws NWNotInContextException;

    /**
     * Use oPlaceable.
     */
    public native static synchronized void actionInteractObject(NWObject oPlaceable) throws NWNotInContextException;

    /**
     * The subject will jump to lLocation instantly (even between areas).
     * If lLocation is invalid, nothing will happen.
     */
    public native static synchronized void actionJumpToLocation(NWLocation lLocation) throws NWNotInContextException;

    /**
     * Jump to an object ID, or as near to it as possible.
     */
    public native static synchronized void actionJumpToObject(NWObject oToJumpTo, boolean bWalkStraightLineToPoint) throws NWNotInContextException;

    /**
     * The action subject will lock oTarget, which can be a door or a placeable
     * object.
     */
    public native static synchronized void actionLockObject(NWObject oTarget) throws NWNotInContextException;

    /**
     * Causes the action subject to move away from lMoveAwayFrom.
     */
    public native static synchronized void actionMoveAwayFromLocation(NWLocation lMoveAwayFrom, boolean bRun, float fMoveAwayRange) throws NWNotInContextException;

    /**
     * Cause the action subject to move to a certain distance away from oFleeFrom.
     * @param oFleeFrom This is the object we wish the action subject to move away from.
     * If oFleeFrom is not in the same area as the action subject, nothing will
     * happen.
     * @param bRun If this is TRUE, the action subject will run rather than walk
     * @param fMoveAwayRange This is the distance we wish the action subject to put
     * between themselves and oFleeFrom
     * * No return value, but if an error occurs the log file will contain
     * "ActionMoveAwayFromObject failed."
     */
    public native static synchronized void actionMoveAwayFromObject(NWObject oFleeFrom, boolean bRun, float fMoveAwayRange) throws NWNotInContextException;

    /**
     * The action subject will move to lDestination.
     * @param lDestination The object will move to this location.  If the location is
     * invalid or a path cannot be found to it, the command does nothing.
     * @param bRun If this is TRUE, the action subject will run rather than walk
     * * No return value, but if an error occurs the log file will contain
     * "MoveToPoint failed."
     */
    public native static synchronized void actionMoveToLocation(NWLocation lDestination, boolean bRun) throws NWNotInContextException;

    /**
     * Cause the action subject to move to a certain distance from oMoveTo.
     * If there is no path to oMoveTo, this command will do nothing.
     * @param oMoveTo This is the object we wish the action subject to move to
     * @param bRun If this is TRUE, the action subject will run rather than walk
     * @param fRange This is the desired distance between the action subject and oMoveTo
     * * No return value, but if an error occurs the log file will contain
     * "ActionMoveToObject failed."
     */
    public native static synchronized void actionMoveToObject(NWObject oMoveTo, boolean bRun, float fRange) throws NWNotInContextException;

    /**
     * Cause the action subject to open oDoor
     */
    public native static synchronized void actionOpenDoor(NWObject oDoor) throws NWNotInContextException;

    /**
     * Pause the current conversation.
     */
    public native static synchronized void actionPauseConversation() throws NWNotInContextException;

    /**
     * Pick up oItem from the ground.
     * * No return value, but if an error occurs the log file will contain
     * "ActionPickUpItem failed."
     */
    public native static synchronized void actionPickUpItem(NWObject oItem) throws NWNotInContextException;

    /**
     * Cause the action subject to play an animation
     * @param nAnimation ANIMATION_*
     * @param fSpeed Speed of the animation
     * @param fDurationSeconds Duration of the animation (this is not used for Fire and
     * Forget animations)
     */
    public native static synchronized void actionPlayAnimation(int nAnimation, float fSpeed, float fDurationSeconds) throws NWNotInContextException;

    /**
     * Put down oItem on the ground.
     * * No return value, but if an error occurs the log file will contain
     * "ActionPutDownItem failed."
     */
    public native static synchronized void actionPutDownItem(NWObject oItem) throws NWNotInContextException;

    /**
     * The action subject will generate a random location near its current location
     * and pathfind to it.  ActionRandomwalk never ends, which means it is neccessary
     * to call ClearAllActions in order to allow a creature to perform any other action
     * once ActionRandomWalk has been called.
     * * No return value, but if an error occurs the log file will contain
     * "ActionRandomWalk failed."
     */
    public native static synchronized void actionRandomWalk() throws NWNotInContextException;

    /**
     * The creature will rest if not in combat and no enemies are nearby.
     * @param bCreatureToEnemyLineOfSightCheck TRUE to allow the creature to rest if enemies
     * are nearby, but the creature can't see the enemy.
     * FALSE the creature will not rest if enemies are
     * nearby regardless of whether or not the creature
     * can see them, such as if an enemy is close by,
     * but is in a different room behind a closed door.
     */
    public native static synchronized void actionRest(boolean bCreatureToEnemyLineOfSightCheck) throws NWNotInContextException;

    /**
     * Resume a conversation after it has been paused.
     */
    public native static synchronized void actionResumeConversation() throws NWNotInContextException;

    /**
     * Sit in oChair.
     * Note: Not all creatures will be able to sit and not all
     * objects can be sat on.
     * The object oChair must also be marked as usable in the toolset.
     * 
     * For Example: To get a player to sit in oChair when they click on it,
     * place the following script in the OnUsed event for the object oChair.
     * void main()
     * {
     * object oChair = OBJECT_SELF;
     * AssignCommand(GetLastUsedBy(),ActionSit(oChair));
     * }
     */
    public native static synchronized void actionSit(NWObject oChair) throws NWNotInContextException;

    /**
     * Add a speak action to the action subject.
     * @param sStringToSpeak String to be spoken
     * @param nTalkVolume TALKVOLUME_*
     */
    public native static synchronized void actionSpeakString(String sStringToSpeak, int nTalkVolume) throws NWNotInContextException;

    /**
     * Causes the creature to speak a translated string.
     * @param nStrRef Reference of the string in the talk table
     * @param nTalkVolume TALKVOLUME_*
     */
    public native static synchronized void actionSpeakStringByStrRef(int nStrRef, int nTalkVolume) throws NWNotInContextException;

    /**
     * Starts a conversation with oObjectToConverseWith @param this will cause their
     * OnDialog event to fire.
     * @param oObjectToConverseWith
     * @param sDialogResRef If this is blank, the creature's own dialogue file will be used
     * @param bPrivateConversation
     * Turn off bPlayHello if you don't want the initial greeting to play
     */
    public native static synchronized void actionStartConversation(NWObject oObjectToConverseWith, String sDialogResRef, boolean bPrivateConversation, boolean bPlayHello) throws NWNotInContextException;

    /**
     * Take oItem from oTakeFrom
     * If oItem is not a valid item, or oTakeFrom is not a valid object, nothing
     * will happen.
     */
    public native static synchronized void actionTakeItem(NWObject oItem, NWObject oTakeFrom) throws NWNotInContextException;

    /**
     * Unequip oItem from whatever slot it is currently in.
     */
    public native static synchronized void actionUnequipItem(NWObject oItem) throws NWNotInContextException;

    /**
     * The action subject will unlock oTarget, which can be a door or a placeable
     * object.
     */
    public native static synchronized void actionUnlockObject(NWObject oTarget) throws NWNotInContextException;

    /**
     * Use nFeat on oTarget.
     * @param nFeat FEAT_*
     * @param oTarget
     */
    public native static synchronized void actionUseFeat(int nFeat, NWObject oTarget) throws NWNotInContextException;

    /**
     * Runs the action "UseSkill" on the current creature
     * Use nSkill on oTarget.
     * @param nSkill SKILL_*
     * @param oTarget
     * @param nSubSkill SUBSKILL_*
     * @param oItemUsed Item to use in conjunction with the skill
     */
    public native static synchronized void actionUseSkill(int nSkill, NWObject oTarget, int nSubSkill, NWObject oItemUsed) throws NWNotInContextException;

    /**
     * Do nothing for fSeconds seconds.
     */
    public native static synchronized void actionWait(float fSeconds) throws NWNotInContextException;

    /**
     * Try to send oTarget to a new server defined by sIPaddress.
     * @param oTarget
     * @param sIPaddress this can be numerical "192.168.0.84" or alphanumeric
     * "www.bioware.com". It can also contain a port "192.168.0.84:5121" or
     * "www.bioware.com:5121"; if the port is not specified, it will default to
     * 5121.
     * @param sPassword login password for the destination server
     * @param sWaypointTag if this is set, after portalling the character will be moved
     * to this waypoint if it exists
     * @param bSeamless if this is set, the client wil not be prompted with the
     * information window telling them about the server, and they will not be
     * allowed to save a copy of their character if they are using a local vault
     * character.
     */
    public native static synchronized void activatePortal(NWObject oTarget, String sIPaddress, String sPassword, String sWaypointTag, boolean bSeemless) throws NWNotInContextException;

    /**
     * Add oHenchman as a henchman to oMaster
     * If oHenchman is either a DM or a player character, this will have no effect.
     */
    public native static synchronized void addHenchman(NWObject oMaster, NWObject oHenchman) throws NWNotInContextException;

    /**
     * ***********************  START OF ITEM PROPERTY FUNCTIONS  **********************
     * 
     * adds an item property to the specified item
     * Only temporary and permanent duration types are allowed.
     */
    public native static synchronized void addItemProperty(int nDurationType, NWItemProperty ipProperty, NWObject oItem, float fDuration) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * Add a journal quest entry to oCreature.
     * @param szPlotID the plot identifier used in the toolset's Journal Editor
     * @param nState the state of the plot as seen in the toolset's Journal Editor
     * @param oCreature
     * @param bAllPartyMembers If this is TRUE, the entry will show up in the journal of
     * everyone in the party
     * @param bAllPlayers If this is TRUE, the entry will show up in the journal of
     * everyone in the world
     * @param bAllowOverrideHigher If this is TRUE, you can set the state to a lower
     * number than the one it is currently on
     */
    public native static synchronized void addJournalQuestEntry(String szPlotID, int nState, NWObject oCreature, boolean bAllPartyMembers, boolean bAllPlayers, boolean bAllowOverrideHigher) throws NWNotInContextException;

    /**
     * Add oPC to oPartyLeader's party.  This will only work on two PCs.
     * @param oPC player to add to a party
     * @param oPartyLeader player already in the party
     */
    public native static synchronized void addToParty(NWObject oPC, NWObject oPartyLeader) throws NWNotInContextException;

    /**
     * Adjust the alignment of oSubject.
     * @param oSubject
     * @param nAlignment:
     * -> ALIGNMENT_LAWFUL/ALIGNMENT_CHAOTIC/ALIGNMENT_GOOD/ALIGNMENT_EVIL: oSubject's
     * alignment will be shifted in the direction specified
     * -> ALIGNMENT_ALL: nShift will be added to oSubject's law/chaos and
     * good/evil alignment values
     * -> ALIGNMENT_NEUTRAL: nShift is applied to oSubject's law/chaos and
     * good/evil alignment values in the direction which is towards neutrality.
     * e.g. If oSubject has a law/chaos value of 10 (i.e. chaotic) and a
     * good/evil value of 80 (i.e. good) then if nShift is 15, the
     * law/chaos value will become (10+15)=25 and the good/evil value will
     * become (80-25)=55
     * Furthermore, the shift will at most take the alignment value to 50 and
     * not beyond.
     * e.g. If oSubject has a law/chaos value of 40 and a good/evil value of 70,
     * then if nShift is 15, the law/chaos value will become 50 and the
     * good/evil value will become 55
     * @param nShift this is the desired shift in alignment
     * @param bAllPartyMembers when TRUE the alignment shift of oSubject also has a
     * diminished affect all members of oSubject's party (if oSubject is a Player).
     * When FALSE the shift only affects oSubject.
     * * No return value
     */
    public native static synchronized void adjustAlignment(NWObject oSubject, int nAlignment, int nShift, boolean bAllPartyMembers) throws NWNotInContextException;

    /**
     * Adjust how oSourceFactionMember's faction feels about oTarget by the
     * specified amount.
     * Note: This adjusts Faction Reputation, how the entire faction that
     * oSourceFactionMember is in, feels about oTarget.
     * * No return value
     * Note: You can't adjust a player character's (PC) faction towards
     * NPCs, so attempting to make an NPC hostile by passing in a PC object
     * as oSourceFactionMember in the following call will fail:
     * AdjustReputation(oNPC,oPC,-100);
     * Instead you should pass in the PC object as the first
     * parameter as in the following call which should succeed:
     * AdjustReputation(oPC,oNPC,-100);
     * Note: Will fail if oSourceFactionMember is a plot object.
     */
    public native static synchronized void adjustReputation(NWObject oTarget, NWObject oSourceFactionMember, int nAdjustment) throws NWNotInContextException;

    /**
     * Change the ambient day track for oArea to nTrack.
     * @param oArea
     * @param nTrack
     */
    public native static synchronized void ambientSoundChangeDay(NWObject oArea, int nTrack) throws NWNotInContextException;

    /**
     * Change the ambient night track for oArea to nTrack.
     * @param oArea
     * @param nTrack
     */
    public native static synchronized void ambientSoundChangeNight(NWObject oArea, int nTrack) throws NWNotInContextException;

    /**
     * Play the ambient sound for oArea.
     */
    public native static synchronized void ambientSoundPlay(NWObject oArea) throws NWNotInContextException;

    /**
     * Set the ambient day volume for oArea to nVolume.
     * @param oArea
     * @param nVolume 0 - 100
     */
    public native static synchronized void ambientSoundSetDayVolume(NWObject oArea, int nVolume) throws NWNotInContextException;

    /**
     * Set the ambient night volume for oArea to nVolume.
     * @param oArea
     * @param nVolume 0 - 100
     */
    public native static synchronized void ambientSoundSetNightVolume(NWObject oArea, int nVolume) throws NWNotInContextException;

    /**
     * Stop the ambient sound for oArea.
     */
    public native static synchronized void ambientSoundStop(NWObject oArea) throws NWNotInContextException;

    /**
     * Convert fAngle to a vector
     */
    public native static synchronized NWVector angleToVector(float fAngle) throws NWNotInContextException;

    /**
     * Apply eEffect at lLocation.
     */
    public native static synchronized void applyEffectAtLocation(int nDurationType, NWEffect eEffect, NWLocation lLocation, float fDuration) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Apply eEffect to oTarget.
     */
    public native static synchronized void applyEffectToObject(int nDurationType, NWEffect eEffect, NWObject oTarget, float fDuration) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Use this in an OnDialog script to start up the dialog tree.
     * @param sResRef if this is not specified, the default dialog file will be used
     * @param oObjectToDialog if this is not specified the person that triggered the
     * event will be used
     */
    public native static synchronized int beginConversation(String sResRef, NWObject oObjectToDialog) throws NWNotInContextException;

    /**
     * Sets the screen to black.  Can be used in preparation for a fade-in (FadeFromBlack)
     * Can be cleared by either doing a FadeFromBlack, or by calling StopFade.
     * @param oCreature creature controlled by player that should see black screen
     */
    public native static synchronized void blackScreen(NWObject oCreature) throws NWNotInContextException;

    /**
     * Remove oPlayer from the server.
     */
    public native static synchronized void bootPC(NWObject oPlayer) throws NWNotInContextException;

    /**
     * Make oObjectToChangeFaction join the faction of oMemberOfFactionToJoin.
     * NB. ** This will only work for two NPCs **
     */
    public native static synchronized void changeFaction(NWObject oObjectToChangeFaction, NWObject oMemberOfFactionToJoin) throws NWNotInContextException;

    /**
     * Make oCreatureToChange join one of the standard factions.
     * ** This will only work on an NPC **
     * @param nStandardFaction STANDARD_FACTION_*
     */
    public native static synchronized void changeToStandardFaction(NWObject oCreatureToChange, int nStandardFaction) throws NWNotInContextException;

    /**
     * Clear all the actions of the caller.
     * * No return value, but if an error occurs, the log file will contain
     * "ClearAllActions failed.".
     * @param bClearCombatState if true, this will immediately clear the combat state
     * on a creature, which will stop the combat music and allow them to rest,
     * engage in dialog, or other actions that they would normally have to wait for.
     */
    public native static synchronized void clearAllActions(boolean bClearCombatState) throws NWNotInContextException;

    /**
     * Clear all personal feelings that oSource has about oTarget.
     */
    public native static synchronized void clearPersonalReputation(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * duplicates the item and returns a new object
     * oItem @param item to copy
     * oTargetInventory @param create item in this object's inventory. If this parameter
     * is not valid, the item will be created in oItem's location
     * bCopyVars @param copy the local variables from the old item to the new one
     * * returns the new item
     * * returns OBJECT_INVALID for non-items.
     * * can only copy empty item containers. will return OBJECT_INVALID if oItem contains
     * other items.
     * * if it is possible to merge this item with any others in the target location,
     * then it will do so and return the merged object.
     */
    public native static synchronized NWObject copyItem(NWObject oItem, NWObject oTargetInventory, boolean bCopyVars) throws NWNotInContextException;

    /**
     * Creates a new copy of an item, while making a single change to the appearance of the item.
     * Helmet models and simple items ignore iIndex.
     * iType                            iIndex                      iNewValue
     * ITEM_APPR_TYPE_SIMPLE_MODEL      [Ignored]                   Model #
     * ITEM_APPR_TYPE_WEAPON_COLOR      ITEM_APPR_WEAPON_COLOR_*    1-4
     * ITEM_APPR_TYPE_WEAPON_MODEL      ITEM_APPR_WEAPON_MODEL_*    Model #
     * ITEM_APPR_TYPE_ARMOR_MODEL       ITEM_APPR_ARMOR_MODEL_*     Model #
     * ITEM_APPR_TYPE_ARMOR_COLOR       ITEM_APPR_ARMOR_COLOR_*     0-175
     */
    public native static synchronized NWObject copyItemAndModify(NWObject oItem, int nType, int nIndex, int nNewValue, boolean bCopyVars) throws NWNotInContextException;

    /**
     * Duplicates the object specified by oSource.
     * ONLY creatures and items can be specified.
     * If an owner is specified and the object is an item, it will be put into their inventory
     * If the object is a creature, they will be created at the location.
     * If a new tag is specified, it will be assigned to the new object.
     */
    public native static synchronized NWObject copyObject(NWObject oSource, NWLocation locLocation, NWObject oOwner, String sNewTag) throws NWNotInContextException;

    /**
     * Create an item with the template sItemTemplate in oTarget's inventory.
     * @param nStackSize This is the stack size of the item to be created
     * @param sNewTag If this string is not empty, it will replace the default tag from the template
     * * Return value: The object that has been created.  On error, this returns
     * OBJECT_INVALID.
     * If the item created was merged into an existing stack of similar items,
     * the function will return the merged stack object. If the merged stack
     * overflowed, the function will return the overflowed stack that was created.
     */
    public native static synchronized NWObject createItemOnObject(String sItemTemplate, NWObject oTarget, int nStackSize, String sNewTag) throws NWNotInContextException;

    /**
     * Create an object of the specified type at lLocation.
     * @param nObjectType OBJECT_TYPE_ITEM, OBJECT_TYPE_CREATURE, OBJECT_TYPE_PLACEABLE,
     * OBJECT_TYPE_STORE, OBJECT_TYPE_WAYPOINT
     * @param sTemplate
     * @param lLocation
     * @param bUseAppearAnimation
     * @param sNewTag - if this string is not empty, it will replace the default tag from the template
     */
    public native static synchronized NWObject createObject(int nObjectType, String sTemplate, NWLocation lLocation, boolean bUseAppearAnimation, String sNewTag) throws NWNotInContextException;

    /**
     * Creates a square Trap object.
     * @param nTrapType The base type of trap (TRAP_BASE_TYPE_*)
     * @param lLocation The location and orientation that the trap will be created at.
     * @param fSize The size of the trap. Minimum size allowed is 1.0f.
     * @param sTag The tag of the trap being created.
     * @param nStandardFaction The faction of the trap (STANDARD_FACTION_*).
     * @param sOnDisarmScript The OnDisarm script that will fire when the trap is disarmed.
     * If "" no script will fire.
     * @param sOnTrapTriggeredScript The OnTrapTriggered script that will fire when the
     * trap is triggered.
     * If "" the default OnTrapTriggered script for the trap
     * type specified will fire instead (as specified in the
     * traps.2da).
     */
    public native static synchronized NWObject createTrapAtLocation(int nTrapType, NWLocation lLocation, float fSize, String sTag, int nStandardFaction, String sOnDisarmScript, String sOnTrapTriggeredScript) throws NWNotInContextException;

    /**
     * Creates a Trap on the object specified.
     * @param nTrapType The base type of trap (TRAP_BASE_TYPE_*)
     * @param oObject The object that the trap will be created on. Works only on Doors and Placeables.
     * @param nStandardFaction The faction of the trap (STANDARD_FACTION_*).
     * @param sOnDisarmScript The OnDisarm script that will fire when the trap is disarmed.
     * If "" no script will fire.
     * @param sOnTrapTriggeredScript The OnTrapTriggered script that will fire when the
     * trap is triggered.
     * If "" the default OnTrapTriggered script for the trap
     * type specified will fire instead (as specified in the
     * traps.2da).
     * Note: After creating a trap on an object, you can change the trap's properties
     * using the various SetTrap* scripting commands by passing in the object
     * that the trap was created on (i.e. oObject) to any subsequent SetTrap* commands.
     */
    public native static synchronized void createTrapOnObject(int nTrapType, NWObject oObject, int nStandardFaction, String sOnDisarmScript, String sOnTrapTriggeredScript) throws NWNotInContextException;

    /**
     * Changes the current Day/Night cycle for this player to night
     * @param oPlayer which player to change the lighting for
     * @param fTransitionTime how long the transition should take
     */
    public native static synchronized void dayToNight(NWObject oPlayer, float fTransitionTime) throws NWNotInContextException;

    /**
     * Decrement the remaining uses per day for this creature by one.
     * @param oCreature creature to modify
     * @param nFeat constant FEAT_*
     */
    public native static synchronized void decrementRemainingFeatUses(NWObject oCreature, int nFeat) throws NWNotInContextException;

    /**
     * Decrement the remaining uses per day for this creature by one.
     * @param oCreature creature to modify
     * @param nSpell constant SPELL_*
     */
    public native static synchronized void decrementRemainingSpellUses(NWObject oCreature, int nSpell) throws NWNotInContextException;

    /**
     * This will remove ANY campaign variable. Regardless of type.
     * Note that by normal database standards, deleting does not actually removed the entry from
     * the database, but flags it as deleted. Do not expect the database files to shrink in size
     * from this command. If you want to 'pack' the database, you will have to do it externally
     * from the game.
     */
    public native static synchronized void deleteCampaignVariable(String sCampaignName, String sVarName, NWObject oPlayer) throws NWNotInContextException;

    /**
     * Delete oObject's local float variable sVarName
     */
    public native static synchronized void deleteLocalFloat(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Delete oObject's local integer variable sVarName
     */
    public native static synchronized void deleteLocalInt(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Delete oObject's local location variable sVarName
     */
    public native static synchronized void deleteLocalLocation(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Delete oObject's local object variable sVarName
     */
    public native static synchronized void deleteLocalObject(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Delete oObject's local string variable sVarName
     */
    public native static synchronized void deleteLocalString(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * This will delete the entire campaign database if it exists.
     */
    public native static synchronized void destroyCampaignDatabase(String sCampaignName) throws NWNotInContextException;

    /**
     * Destroy oObject (irrevocably).
     * This will not work on modules and areas.
     */
    public native static synchronized void destroyObject(NWObject oDestroy, float fDelay) throws NWNotInContextException;

    /**
     * Perform nDoorAction on oTargetDoor.
     */
    public native static synchronized void doDoorAction(NWObject oTargetDoor, int nDoorAction) throws NWNotInContextException;

    /**
     * The caller performs nPlaceableAction on oPlaceable.
     * @param oPlaceable
     * @param nPlaceableAction PLACEABLE_ACTION_*
     */
    public native static synchronized void doPlaceableObjectAction(NWObject oPlaceable, int nPlaceableAction) throws NWNotInContextException;

    /**
     * Only if we are in a single player game, AutoSave the game.
     */
    public native static synchronized void doSinglePlayerAutoSave() throws NWNotInContextException;

    /**
     * Does a single attack on every hostile creature within 10ft. of the attacker
     * and determines damage accordingly.  If the attacker has a ranged weapon
     * equipped, this will have no effect.
     * ** NOTE ** This is meant to be called inside the spell script for whirlwind
     * attack, it is not meant to be used to queue up a new whirlwind attack.  To do
     * that you need to call ActionUseFeat(FEAT_WHIRLWIND_ATTACK, oEnemy)
     * @param int bDisplayFeedback TRUE or FALSE, whether or not feedback should be
     * displayed
     * @param int bImproved If TRUE, the improved version of whirlwind is used
     */
    public native static synchronized void doWhirlwindAttack(boolean bDisplayFeedback, boolean bImproved) throws NWNotInContextException;

    /**
     * Create an AC Decrease effect.
     * @param nValue
     * @param nACModifyType AC_*
     * @param nDamageType DAMAGE_TYPE_*
     * * Default value for nDamageType should only ever be used in this function prototype.
     */
    public native static synchronized NWEffect effectACDecrease(int nValue, int nACModifyType, int nDamageType) throws NWNotInContextException;

    /**
     * Create an AC Increase effect
     * @param nValue size of AC increase
     * @param nACModifyType AC_*_BONUS
     * @param nDamageType DAMAGE_TYPE_*
     * * Default value for nDamageType should only ever be used in this function prototype.
     */
    public native static synchronized NWEffect effectACIncrease(int nValue, int nACModifyType, int nDamageType) throws NWNotInContextException;

    /**
     * Create an Ability Decrease effect.
     * @param nAbility ABILITY_*
     * @param nModifyBy This is the amount by which to decrement the ability
     */
    public native static synchronized NWEffect effectAbilityDecrease(int nAbility, int nModifyBy) throws NWNotInContextException;

    /**
     * Create an Ability Increase effect
     * @param bAbilityToIncrease ABILITY_*
     */
    public native static synchronized NWEffect effectAbilityIncrease(int nAbility, int nModifyBy) throws NWNotInContextException;

    /**
     * Create an Appear effect to make the object "fly in".
     * @param nAnimation determines which appear and disappear animations to use. Most creatures
     * only have animation 1, although a few have 2 (like beholders)
     */
    public native static synchronized NWEffect effectAppear(int nAnimation) throws NWNotInContextException;

    /**
     * Create an Area Of Effect effect in the area of the creature it is applied to.
     * If the scripts are not specified, default ones will be used.
     */
    public native static synchronized NWEffect effectAreaOfEffect(int nAreaEffectId, String sOnEnterScript, String sHeartbeatScript, String sOnExitScript) throws NWNotInContextException;

    /**
     * Create an Attack Decrease effect.
     * @param nPenalty
     * @param nAttackBonus ATTACK_BONUS_*
     */
    public native static synchronized NWEffect effectAttackDecrease(int nPenalty, int nAttackBonus) throws NWNotInContextException;

    /**
     * Create an Attack Increase effect
     * @param nBonus size of attack bonus
     * @param nAttackBonus ATTACK_BONUS_*
     */
    public native static synchronized NWEffect effectAttackIncrease(int nBonus, int nAttackBonus) throws NWNotInContextException;

    /**
     * Create a Beam effect.
     * @param nBeamVisualEffect VFX_BEAM_*
     * @param oEffector the beam is emitted from this creature
     * @param nCreaturePart BODY_NODE_*
     * @param bMissEffect If this is TRUE, the beam will fire to a random vector near or
     * past the target
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nBeamVisualEffect is
     * not valid.
     */
    public native static synchronized NWEffect effectBeam(int nBeamVisualEffect, NWObject oEffector, int nCreaturePart, boolean bMissEffect) throws NWNotInContextException;

    /**
     * Create a Blindness effect.
     */
    public native static synchronized NWEffect effectBlindness() throws NWNotInContextException;

    /**
     * Create a Charm effect
     */
    public native static synchronized NWEffect effectCharmed() throws NWNotInContextException;

    /**
     * Create a Concealment effect.
     * @param nPercentage 1-100 inclusive
     * @param nMissChanceType MISS_CHANCE_TYPE_*
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nPercentage < 1 or
     * nPercentage > 100.
     */
    public native static synchronized NWEffect effectConcealment(int nPercentage, int nMissChanceType) throws NWNotInContextException;

    /**
     * Create a Confuse effect
     */
    public native static synchronized NWEffect effectConfused() throws NWNotInContextException;

    /**
     * Create a Curse effect.
     * @param nStrMod strength modifier
     * @param nDexMod dexterity modifier
     * @param nConMod constitution modifier
     * @param nIntMod intelligence modifier
     * @param nWisMod wisdom modifier
     * @param nChaMod charisma modifier
     */
    public native static synchronized NWEffect effectCurse(int nStrMod, int nDexMod, int nConMod, int nIntMod, int nWisMod, int nChaMod) throws NWNotInContextException;

    /**
     * Returns an effect that is guaranteed to dominate a creature
     * Like EffectDominated but cannot be resisted
     */
    public native static synchronized NWEffect effectCutsceneDominated() throws NWNotInContextException;

    /**
     * Creates a cutscene ghost effect, this will allow creatures
     * to pathfind through other creatures without bumping into them
     * for the duration of the effect.
     */
    public native static synchronized NWEffect effectCutsceneGhost() throws NWNotInContextException;

    /**
     * Returns an effect that when applied will paralyze the target's legs, rendering
     * them unable to walk but otherwise unpenalized. This effect cannot be resisted.
     */
    public native static synchronized NWEffect effectCutsceneImmobilize() throws NWNotInContextException;

    /**
     * returns an effect that is guaranteed to paralyze a creature.
     * this effect is identical to EffectParalyze except that it cannot be resisted.
     */
    public native static synchronized NWEffect effectCutsceneParalyze() throws NWNotInContextException;

    /**
     * Create a Damage effect
     * @param nDamageAmount amount of damage to be dealt. This should be applied as an
     * instantaneous effect.
     * @param nDamageType DAMAGE_TYPE_*
     * @param nDamagePower DAMAGE_POWER_*
     */
    public native static synchronized NWEffect effectDamage(int nDamageAmount, int nDamageType, int nDamagePower) throws NWNotInContextException;

    /**
     * Create a Damage Decrease effect.
     * @param nPenalty
     * @param nDamageType DAMAGE_TYPE_*
     */
    public native static synchronized NWEffect effectDamageDecrease(int nPenalty, int nDamageType) throws NWNotInContextException;

    /**
     * Create a Damage Immunity Decrease effect.
     * @param nDamageType DAMAGE_TYPE_*
     * @param nPercentImmunity
     */
    public native static synchronized NWEffect effectDamageImmunityDecrease(int nDamageType, int nPercentImmunity) throws NWNotInContextException;

    /**
     * Creates a Damage Immunity Increase effect.
     * @param nDamageType DAMAGE_TYPE_*
     * @param nPercentImmunity
     */
    public native static synchronized NWEffect effectDamageImmunityIncrease(int nDamageType, int nPercentImmunity) throws NWNotInContextException;

    /**
     * Create a Damage Increase effect
     * @param nBonus DAMAGE_BONUS_*
     * @param nDamageType DAMAGE_TYPE_*
     * NOTE! You *must* use the DAMAGE_BONUS_* constants! Using other values may
     * result in odd behaviour.
     */
    public native static synchronized NWEffect effectDamageIncrease(int nBonus, int nDamageType) throws NWNotInContextException;

    /**
     * Create a Damage Reduction effect
     * @param nAmount amount of damage reduction
     * @param nDamagePower DAMAGE_POWER_*
     * @param nLimit How much damage the effect can absorb before disappearing.
     * Set to zero for infinite
     */
    public native static synchronized NWEffect effectDamageReduction(int nAmount, int nDamagePower, int nLimit) throws NWNotInContextException;

    /**
     * Create a Damage Resistance effect that removes the first nAmount points of
     * damage of type nDamageType, up to nLimit (or infinite if nLimit is 0)
     * @param nDamageType DAMAGE_TYPE_*
     * @param nAmount
     * @param nLimit
     */
    public native static synchronized NWEffect effectDamageResistance(int nDamageType, int nAmount, int nLimit) throws NWNotInContextException;

    /**
     * Create a Damage Shield effect which does (nDamageAmount + nRandomAmount)
     * damage to any melee attacker on a successful attack of damage type nDamageType.
     * @param nDamageAmount an integer value
     * @param nRandomAmount DAMAGE_BONUS_*
     * @param nDamageType DAMAGE_TYPE_*
     * NOTE! You *must* use the DAMAGE_BONUS_* constants! Using other values may
     * result in odd behaviour.
     */
    public native static synchronized NWEffect effectDamageShield(int nDamageAmount, int nRandomAmount, int nDamageType) throws NWNotInContextException;

    /**
     * Create a Darkness effect.
     */
    public native static synchronized NWEffect effectDarkness() throws NWNotInContextException;

    /**
     * Create a Daze effect
     */
    public native static synchronized NWEffect effectDazed() throws NWNotInContextException;

    /**
     * Create a Deaf effect
     */
    public native static synchronized NWEffect effectDeaf() throws NWNotInContextException;

    /**
     * Create a Death effect
     * @param bSpectacularDeath if this is TRUE, the creature to which this effect is
     * applied will die in an extraordinary fashion
     * @param bDisplayFeedback
     */
    public native static synchronized NWEffect effectDeath(boolean bSpectacularDeath, boolean bDisplayFeedback) throws NWNotInContextException;

    /**
     * Create a Disappear effect to make the object "fly away" and then destroy
     * itself.
     * @param nAnimation determines which appear and disappear animations to use. Most creatures
     * only have animation 1, although a few have 2 (like beholders)
     */
    public native static synchronized NWEffect effectDisappear(int nAnimation) throws NWNotInContextException;

    /**
     * Create a Disappear/Appear effect.
     * The object will "fly away" for the duration of the effect and will reappear
     * at lLocation.
     * @param nAnimation determines which appear and disappear animations to use. Most creatures
     * only have animation 1, although a few have 2 (like beholders)
     */
    public native static synchronized NWEffect effectDisappearAppear(NWLocation lLocation, int nAnimation) throws NWNotInContextException;

    /**
     * Create a Disease effect.
     * @param nDiseaseType DISEASE_*
     */
    public native static synchronized NWEffect effectDisease(int nDiseaseType) throws NWNotInContextException;

    /**
     * Create a Dispel Magic All effect.
     * If no parameter is specified, USE_CREATURE_LEVEL will be used. This will
     * cause the dispel effect to use the level of the creature that created the
     * effect.
     */
    public native static synchronized NWEffect effectDispelMagicAll(int nCasterLevel) throws NWNotInContextException;

    /**
     * Create a Dispel Magic Best effect.
     * If no parameter is specified, USE_CREATURE_LEVEL will be used. This will
     * cause the dispel effect to use the level of the creature that created the
     * effect.
     */
    public native static synchronized NWEffect effectDispelMagicBest(int nCasterLevel) throws NWNotInContextException;

    /**
     * Create a Dominate effect
     */
    public native static synchronized NWEffect effectDominated() throws NWNotInContextException;

    /**
     * Create an Entangle effect
     * When applied, this effect will restrict the creature's movement and apply a
     * (-2) to all attacks and a -4 to AC.
     */
    public native static synchronized NWEffect effectEntangle() throws NWNotInContextException;

    /**
     * Returns an effect of type EFFECT_TYPE_ETHEREAL which works just like EffectSanctuary
     * except that the observers get no saving throw
     */
    public native static synchronized NWEffect effectEthereal() throws NWNotInContextException;

    /**
     * Create a Frighten effect
     */
    public native static synchronized NWEffect effectFrightened() throws NWNotInContextException;

    /**
     * Create a Haste effect.
     */
    public native static synchronized NWEffect effectHaste() throws NWNotInContextException;

    /**
     * Create a Heal effect. This should be applied as an instantaneous effect.
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nDamageToHeal < 0.
     */
    public native static synchronized NWEffect effectHeal(int nDamageToHeal) throws NWNotInContextException;

    /**
     * Create a Hit Point Change When Dying effect.
     * @param fHitPointChangePerRound this can be positive or negative, but not zero.
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if fHitPointChangePerRound is 0.
     */
    public native static synchronized NWEffect effectHitPointChangeWhenDying(float fHitPointChangePerRound) throws NWNotInContextException;

    /**
     * Create an Immunity effect.
     * @param nImmunityType IMMUNITY_TYPE_*
     */
    public native static synchronized NWEffect effectImmunity(int nImmunityType) throws NWNotInContextException;

    /**
     * Create an Invisibility effect.
     * @param nInvisibilityType INVISIBILITY_TYPE_*
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nInvisibilityType
     * is invalid.
     */
    public native static synchronized NWEffect effectInvisibility(int nInvisibilityType) throws NWNotInContextException;

    /**
     * Create a Knockdown effect
     * This effect knocks creatures off their feet, they will sit until the effect
     * is removed. This should be applied as a temporary effect with a 3 second
     * duration minimum (1 second to fall, 1 second sitting, 1 second to get up).
     */
    public native static synchronized NWEffect effectKnockdown() throws NWNotInContextException;

    /**
     * Link the two supplied effects, returning eChildEffect as a child of
     * eParentEffect.
     * Note: When applying linked effects if the target is immune to all valid
     * effects all other effects will be removed as well. This means that if you
     * apply a visual effect and a silence effect (in a link) and the target is
     * immune to the silence effect that the visual effect will get removed as well.
     * Visual Effects are not considered "valid" effects for the purposes of
     * determining if an effect will be removed or not and as such should never be
     * packaged *only* with other visual effects in a link.
     */
    public native static synchronized NWEffect effectLinkEffects(NWEffect eChildEffect, NWEffect eParentEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Create a Miss Chance effect.
     * @param nPercentage 1-100 inclusive
     * @param nMissChanceType MISS_CHANCE_TYPE_*
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nPercentage < 1 or
     * nPercentage > 100.
     */
    public native static synchronized NWEffect effectMissChance(int nPercentage, int nMissChanceType) throws NWNotInContextException;

    /**
     * Create a Modify Attacks effect to add attacks.
     * @param nAttacks maximum is 5, even with the effect stacked
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nAttacks > 5.
     */
    public native static synchronized NWEffect effectModifyAttacks(int nAttacks) throws NWNotInContextException;

    /**
     * Create a Movement Speed Decrease effect.
     * @param nPercentChange - range 0 through 99
     * eg.
     * 0 = no change in speed
     * 50 = 50% slower
     * 99 = almost immobile
     */
    public native static synchronized NWEffect effectMovementSpeedDecrease(int nPercentChange) throws NWNotInContextException;

    /**
     * Create a Movement Speed Increase effect.
     * @param nPercentChange - range 0 through 99
     * eg.
     * 0 = no change in speed
     * 50 = 50% faster
     * 99 = almost twice as fast
     */
    public native static synchronized NWEffect effectMovementSpeedIncrease(int nPercentChange) throws NWNotInContextException;

    /**
     * Create a Negative Level effect.
     * @param nNumLevels the number of negative levels to apply.
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nNumLevels > 100.
     */
    public native static synchronized NWEffect effectNegativeLevel(int nNumLevels, boolean bHPBonus) throws NWNotInContextException;

    /**
     * Create a Paralyze effect
     */
    public native static synchronized NWEffect effectParalyze() throws NWNotInContextException;

    /**
     * returns an effect that will petrify the target
     * * currently applies EffectParalyze and the stoneskin visual effect.
     */
    public native static synchronized NWEffect effectPetrify() throws NWNotInContextException;

    /**
     * Create a Poison effect.
     * @param nPoisonType POISON_*
     */
    public native static synchronized NWEffect effectPoison(int nPoisonType) throws NWNotInContextException;

    /**
     * Create a Polymorph effect.
     */
    public native static synchronized NWEffect effectPolymorph(int nPolymorphSelection, boolean bLocked) throws NWNotInContextException;

    /**
     * Create a Regenerate effect.
     * @param nAmount amount of damage to be regenerated per time interval
     * @param fIntervalSeconds length of interval in seconds
     */
    public native static synchronized NWEffect effectRegenerate(int nAmount, float fIntervalSeconds) throws NWNotInContextException;

    /**
     * Create a Resurrection effect. This should be applied as an instantaneous effect.
     */
    public native static synchronized NWEffect effectResurrection() throws NWNotInContextException;

    /**
     * Create a Sanctuary effect.
     * @param nDifficultyClass must be a non-zero, positive number
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nDifficultyClass <= 0.
     */
    public native static synchronized NWEffect effectSanctuary(int nDifficultyClass) throws NWNotInContextException;

    /**
     * Create a Saving Throw Decrease effect.
     * @param nSave SAVING_THROW_* (not SAVING_THROW_TYPE_*)
     * SAVING_THROW_ALL
     * SAVING_THROW_FORT
     * SAVING_THROW_REFLEX
     * SAVING_THROW_WILL
     * @param nValue size of the Saving Throw decrease
     * @param nSaveType SAVING_THROW_TYPE_* (e.g. SAVING_THROW_TYPE_ACID )
     */
    public native static synchronized NWEffect effectSavingThrowDecrease(int nSave, int nValue, int nSaveType) throws NWNotInContextException;

    /**
     * Create a Saving Throw Increase effect
     * @param nSave SAVING_THROW_* (not SAVING_THROW_TYPE_*)
     * SAVING_THROW_ALL
     * SAVING_THROW_FORT
     * SAVING_THROW_REFLEX
     * SAVING_THROW_WILL
     * @param nValue size of the Saving Throw increase
     * @param nSaveType SAVING_THROW_TYPE_* (e.g. SAVING_THROW_TYPE_ACID )
     */
    public native static synchronized NWEffect effectSavingThrowIncrease(int nSave, int nValue, int nSaveType) throws NWNotInContextException;

    /**
     * Create a See Invisible effect.
     */
    public native static synchronized NWEffect effectSeeInvisible() throws NWNotInContextException;

    /**
     * Create a Silence effect.
     */
    public native static synchronized NWEffect effectSilence() throws NWNotInContextException;

    /**
     * Create a Skill Decrease effect.
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nSkill is invalid.
     */
    public native static synchronized NWEffect effectSkillDecrease(int nSkill, int nValue) throws NWNotInContextException;

    /**
     * Create a Skill Increase effect.
     * @param nSkill SKILL_*
     * @param nValue
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nSkill is invalid.
     */
    public native static synchronized NWEffect effectSkillIncrease(int nSkill, int nValue) throws NWNotInContextException;

    /**
     * Create a Sleep effect
     */
    public native static synchronized NWEffect effectSleep() throws NWNotInContextException;

    /**
     * Create a Slow effect.
     */
    public native static synchronized NWEffect effectSlow() throws NWNotInContextException;

    /**
     * Creates an effect that inhibits spells
     * @param nPercent - percentage of failure
     * @param nSpellSchool - the school of spells affected.
     */
    public native static synchronized NWEffect effectSpellFailure(int nPercent, int nSpellSchool) throws NWNotInContextException;

    /**
     * Create a Spell Immunity effect.
     * There is a known bug with this function. There *must* be a parameter specified
     * when this is called (even if the desired parameter is SPELL_ALL_SPELLS),
     * otherwise an effect of type EFFECT_TYPE_INVALIDEFFECT will be returned.
     * @param nImmunityToSpell SPELL_*
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nImmunityToSpell is
     * invalid.
     */
    public native static synchronized NWEffect effectSpellImmunity(int nImmunityToSpell) throws NWNotInContextException;

    /**
     * Create a Spell Level Absorption effect.
     * @param nMaxSpellLevelAbsorbed maximum spell level that will be absorbed by the
     * effect
     * @param nTotalSpellLevelsAbsorbed maximum number of spell levels that will be
     * absorbed by the effect
     * @param nSpellSchool SPELL_SCHOOL_*
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if:
     * nMaxSpellLevelAbsorbed is not between -1 and 9 inclusive, or nSpellSchool
     * is invalid.
     */
    public native static synchronized NWEffect effectSpellLevelAbsorption(int nMaxSpellLevelAbsorbed, int nTotalSpellLevelsAbsorbed, int nSpellSchool) throws NWNotInContextException;

    /**
     * Create a Spell Resistance Decrease effect.
     */
    public native static synchronized NWEffect effectSpellResistanceDecrease(int nValue) throws NWNotInContextException;

    /**
     * Create a Spell Resistance Increase effect.
     * @param nValue size of spell resistance increase
     */
    public native static synchronized NWEffect effectSpellResistanceIncrease(int nValue) throws NWNotInContextException;

    /**
     * Create a Stun effect
     */
    public native static synchronized NWEffect effectStunned() throws NWNotInContextException;

    /**
     * Create a Summon Creature effect.  The creature is created and placed into the
     * caller's party/faction.
     * @param sCreatureResref Identifies the creature to be summoned
     * @param nVisualEffectId VFX_*
     * @param fDelaySeconds There can be delay between the visual effect being played, and the
     * creature being added to the area
     * @param bUseAppearAnimation should this creature play it's "appear" animation when it is
     * summoned. If zero, it will just fade in somewhere near the target.  If the value is 1
     * it will use the appear animation, and if it's 2 it will use appear2 (which doesn't exist for most creatures)
     */
    public native static synchronized NWEffect effectSummonCreature(String sCreatureResref, int nVisualEffectId, float fDelaySeconds, boolean bUseAppearAnimation) throws NWNotInContextException;

    /**
     * Create a Swarm effect.
     * @param bLooping If this is TRUE, for the duration of the effect when one creature
     * created by this effect dies, the next one in the list will be created.  If
     * the last creature in the list dies, we loop back to the beginning and
     * sCreatureTemplate1 will be created, and so on...
     * @param sCreatureTemplate1
     * @param sCreatureTemplate2
     * @param sCreatureTemplate3
     * @param sCreatureTemplate4
     */
    public native static synchronized NWEffect effectSwarm(boolean bLooping, String sCreatureTemplate1, String sCreatureTemplate2, String sCreatureTemplate3, String sCreatureTemplate4) throws NWNotInContextException;

    /**
     * Create a Temporary Hitpoints effect.
     * @param nHitPoints a positive integer
     * * Returns an effect of type EFFECT_TYPE_INVALIDEFFECT if nHitPoints < 0.
     */
    public native static synchronized NWEffect effectTemporaryHitpoints(int nHitPoints) throws NWNotInContextException;

    /**
     * Create a Time Stop effect.
     */
    public native static synchronized NWEffect effectTimeStop() throws NWNotInContextException;

    /**
     * Create a True Seeing effect.
     */
    public native static synchronized NWEffect effectTrueSeeing() throws NWNotInContextException;

    /**
     * Create a Turn Resistance Decrease effect.
     * @param nHitDice a positive number representing the number of hit dice for the
     * /  decrease
     */
    public native static synchronized NWEffect effectTurnResistanceDecrease(int nHitDice) throws NWNotInContextException;

    /**
     * Create a Turn Resistance Increase effect.
     * @param nHitDice a positive number representing the number of hit dice for the
     * increase
     */
    public native static synchronized NWEffect effectTurnResistanceIncrease(int nHitDice) throws NWNotInContextException;

    /**
     * Create a Turned effect.
     * Turned effects are supernatural by default.
     */
    public native static synchronized NWEffect effectTurned() throws NWNotInContextException;

    /**
     * Create an Ultravision effect.
     */
    public native static synchronized NWEffect effectUltravision() throws NWNotInContextException;

    /**
     * * Create a Visual Effect that can be applied to an object.
     * @param nVisualEffectId
     * @param bMissEffect if this is TRUE, a random vector near or past the target will
     * be generated, on which to play the effect
     */
    public native static synchronized NWEffect effectVisualEffect(int nVisualEffectId, boolean bMissEffect) throws NWNotInContextException;

    /**
     * End the currently running game, play sEndMovie then return all players to the
     * game's main menu.
     */
    public native static synchronized void endGame(String sEndMovie) throws NWNotInContextException;

    /**
     * Make oTarget run sScript and then return execution to the calling script.
     * If sScript does not specify a compiled script, nothing happens.
     */
    public native static synchronized void executeScript(String sScript, NWObject oTarget) throws NWNotInContextException;

    /**
     * Expose/Hide the entire map of oArea for oPlayer.
     * @param oArea The area that the map will be exposed/hidden for.
     * @param oPlayer The player the map will be exposed/hidden for.
     * @param bExplored TRUE/FALSE. Whether the map should be completely explored or hidden.
     */
    public native static synchronized void exploreAreaForPlayer(NWObject oArea, NWObject oPlayer, boolean bExplored) throws NWNotInContextException;

    /**
     * Force all the characters of the players who are currently in the game to
     * be exported to their respective directories i.e. LocalVault/ServerVault/ etc.
     */
    public native static synchronized void exportAllCharacters() throws NWNotInContextException;

    /**
     * Force the character of the player specified to be exported to its respective directory
     * i.e. LocalVault/ServerVault/ etc.
     */
    public native static synchronized void exportSingleCharacter(NWObject oPlayer) throws NWNotInContextException;

    /**
     * Set the subtype of eEffect to Extraordinary and return eEffect.
     * (Effects default to magical if the subtype is not set)
     * Extraordinary effects are removed by resting, but not by dispel magic
     */
    public native static synchronized NWEffect extraordinaryEffect(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Fades the screen for the given creature/player from black to regular screen
     * @param oCreature creature controlled by player that should fade from black
     */
    public native static synchronized void fadeFromBlack(NWObject oCreature, float fSpeed) throws NWNotInContextException;

    /**
     * Fades the screen for the given creature/player from regular screen to black
     * @param oCreature creature controlled by player that should fade to black
     */
    public native static synchronized void fadeToBlack(NWObject oCreature, float fSpeed) throws NWNotInContextException;

    /**
     * Convert fFeet into a number of meters.
     */
    public native static synchronized float feetToMeters(float fFeet) throws NWNotInContextException;

    /**
     * Find the position of sSubstring inside sString
     * @param nStart The character position to start searching at (from the left end of the string).
     * * Return value on error: -1
     */
    public native static synchronized int findSubString(String sString, String sSubString, int nStart) throws NWNotInContextException;

    /**
     * Convert fFloat into the nearest integer.
     */
    public native static synchronized int floatToInt(float fFloat) throws NWNotInContextException;

    /**
     * Convert fFloat into a string.
     * @param nWidth should be a value from 0 to 18 inclusive.
     * @param nDecimals should be a value from 0 to 9 inclusive.
     */
    public native static synchronized String floatToString(float fFloat, int nWidth, int nDecimals) throws NWNotInContextException;

    /**
     * Display floaty text above the specified creature.
     * The text will also appear in the chat buffer of each player that receives the
     * floaty text.
     * @param nStrRefToDisplay String ref (therefore text is translated)
     * @param oCreatureToFloatAbove
     * @param bBroadcastToFaction If this is TRUE then only creatures in the same faction
     * as oCreatureToFloatAbove
     * will see the floaty text, and only if they are within range (30 metres).
     */
    public native static synchronized void floatingTextStrRefOnCreature(int nStrRefToDisplay, NWObject oCreatureToFloatAbove, boolean bBroadcastToFaction) throws NWNotInContextException;

    /**
     * Display floaty text above the specified creature.
     * The text will also appear in the chat buffer of each player that receives the
     * floaty text.
     * @param sStringToDisplay String
     * @param oCreatureToFloatAbove
     * @param bBroadcastToFaction If this is TRUE then only creatures in the same faction
     * as oCreatureToFloatAbove
     * will see the floaty text, and only if they are within range (30 metres).
     */
    public native static synchronized void floatingTextStringOnCreature(String sStringToDisplay, NWObject oCreatureToFloatAbove, boolean bBroadcastToFaction) throws NWNotInContextException;

    /**
     * Instantly gives this creature the benefits of a rest (restored hitpoints, spells, feats, etc..)
     */
    public native static synchronized void forceRest(NWObject oCreature) throws NWNotInContextException;

    /**
     * Do a Fortitude Save check for the given DC
     * @param oCreature
     * @param nDC Difficulty check
     * @param nSaveType SAVING_THROW_TYPE_*
     * @param oSaveVersus
     * Returns: 0 if the saving throw roll failed
     * Returns: 1 if the saving throw roll succeeded
     * Returns: 2 if the target was immune to the save type specified
     * Note: If used within an Area of Effect Object Script (On Enter, OnExit, OnHeartbeat), you MUST pass
     * GetAreaOfEffectCreator() into oSaveVersus!!
     */
    public native static synchronized int fortitudeSave(NWObject oCreature, int nDC, int nSaveType, NWObject oSaveVersus) throws NWNotInContextException;

    /**
     * Gets a value from a 2DA file on the server and returns it as a string
     * avoid using this function in loops
     * @param s2DA the name of the 2da file, 16 chars max
     * @param sColumn the name of the column in the 2da
     * @param nRow the row in the 2da
     * * returns an empty string if file, row, or column not found
     */
    public native static synchronized String get2DAString(String s2DA, String sColumn, int nRow) throws NWNotInContextException;

    /**
     * If oObject is a creature, this will return that creature's armour class
     * If oObject is an item, door or placeable, this will return zero.
     * @param nForFutureUse this parameter is not currently used
     * * Return value if oObject is not a creature, item, door or placeable: -1
     */
    public native static synchronized int getAC(NWObject oObject) throws NWNotInContextException;

    /**
     * Gets the current AI Level that the creature is running at.
     * Returns one of the following:
     * AI_LEVEL_INVALID, AI_LEVEL_VERY_LOW, AI_LEVEL_LOW, AI_LEVEL_NORMAL, AI_LEVEL_HIGH, AI_LEVEL_VERY_HIGH
     */
    public native static synchronized int getAILevel(NWObject oTarget) throws NWNotInContextException;

    /**
     * Returns the ability modifier for the specified ability
     * Get oCreature's ability modifier for nAbility.
     * @param nAbility ABILITY_*
     * @param oCreature
     */
    public native static synchronized int getAbilityModifier(int nAbility, NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the ability score of type nAbility for a creature (otherwise 0)
     * @param oCreature the creature whose ability score we wish to find out
     * @param nAbility ABILITY_*
     * @param bBaseAbilityScore if set to true will return the base ability score without
     * bonuses (e.g. ability bonuses granted from equipped items).
     * Return value on error: 0
     */
    public native static synchronized int getAbilityScore(NWObject oCreature, int nAbility, boolean bBaseAbilityScore) throws NWNotInContextException;

    /**
     * Gets the status of ACTION_MODE_* modes on a creature.
     */
    public native static synchronized boolean getActionMode(NWObject oCreature, int nActionMode) throws NWNotInContextException;

    /**
     * Get oCreature's age.
     * * Returns 0 if oCreature is invalid.
     */
    public native static synchronized int getAge(NWObject oCreature) throws NWNotInContextException;

    /**
     * Return an ALIGNMENT_* constant to represent oCreature's good/evil alignment
     * * Return value if oCreature is not a valid creature: -1
     */
    public native static synchronized int getAlignmentGoodEvil(NWObject oCreature) throws NWNotInContextException;

    /**
     * Return an ALIGNMENT_* constant to represent oCreature's law/chaos alignment
     * * Return value if oCreature is not a valid creature: -1
     */
    public native static synchronized int getAlignmentLawChaos(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get oCreature's animal companion creature type
     * (ANIMAL_COMPANION_CREATURE_TYPE_*).
     * * Returns ANIMAL_COMPANION_CREATURE_TYPE_NONE if oCreature is invalid or does
     * not currently have an animal companion.
     */
    public native static synchronized int getAnimalCompanionCreatureType(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get oCreature's animal companion's name.
     * * Returns "" if oCreature is invalid, does not currently
     * have an animal companion or if the animal companion's name is blank.
     */
    public native static synchronized String getAnimalCompanionName(NWObject oTarget) throws NWNotInContextException;

    /**
     * returns the appearance type of the specified creature.
     * * returns a constant APPEARANCE_TYPE_* for valid creatures
     * * returns APPEARANCE_TYPE_INVALID for non creatures/invalid creatures
     */
    public native static synchronized int getAppearanceType(NWObject oCreature) throws NWNotInContextException;

    /**
     * Returns the current arcane spell failure factor of a creature
     */
    public native static synchronized int getArcaneSpellFailure(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the area that oTarget is currently in
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getArea(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the area's object ID from lLocation.
     */
    public native static synchronized NWObject getAreaFromLocation(NWLocation lLocation) throws NWNotInContextException;

    /**
     * This returns the creator of oAreaOfEffectObject.
     * * Returns OBJECT_INVALID if oAreaOfEffectObject is not a valid Area of Effect object.
     */
    public native static synchronized NWObject getAreaOfEffectCreator(NWObject oAreaOfEffectObject) throws NWNotInContextException;

    /**
     * Gets the size of the area.
     * @param nAreaDimension The area dimension that you wish to determine.
     * AREA_HEIGHT
     * AREA_WIDTH
     * @param oArea The area that you wish to get the size of.
     * Returns: The number of tiles that the area is wide/high, or zero on an error.
     * If no valid area (or object) is specified, it uses the area of the caller.
     * If an object other than an area is specified, will use the area that the object is currently in.
     */
    public native static synchronized int getAreaSize(int nAreaDimension, NWObject oArea) throws NWNotInContextException;

    /**
     * Get the associate of type nAssociateType belonging to oMaster.
     * @param nAssociateType ASSOCIATE_TYPE_*
     * @param nMaster
     * @param nTh Which associate of the specified type to return
     * * Returns OBJECT_INVALID if no such associate exists.
     */
    public native static synchronized NWObject getAssociate(int nAssociateType, NWObject oMaster, int nTh) throws NWNotInContextException;

    /**
     * Returns the associate type of the specified creature.
     * @param Returns ASSOCIATE_TYPE_NONE if the creature is not the associate of anyone.
     */
    public native static synchronized int getAssociateType(NWObject oAssociate) throws NWNotInContextException;

    /**
     * Get the attack target of oCreature.
     * This only works when oCreature is in combat.
     */
    public native static synchronized NWObject getAttackTarget(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the target that the caller attempted to attack @param this should be used in
     * conjunction with GetAttackTarget(). This value is set every time an attack is
     * made, and is reset at the end of combat.
     * * Returns OBJECT_INVALID if the caller is not a valid creature.
     */
    public native static synchronized NWObject getAttemptedAttackTarget() throws NWNotInContextException;

    /**
     * Get the target at which the caller attempted to cast a spell.
     * This value is set every time a spell is cast and is reset at the end of
     * combat.
     * * Returns OBJECT_INVALID if the caller is not a valid creature.
     */
    public native static synchronized NWObject getAttemptedSpellTarget() throws NWNotInContextException;

    /**
     * Returns the base attach bonus for the given creature.
     */
    public native static synchronized int getBaseAttackBonus(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the base item type (BASE_ITEM_*) of oItem.
     * * Returns BASE_ITEM_INVALID if oItem is an invalid item.
     */
    public native static synchronized int getBaseItemType(NWObject oItem) throws NWNotInContextException;

    /**
     * Get the last blocking door encountered by the caller of this function.
     * * Returns OBJECT_INVALID if the caller is not a valid creature.
     */
    public native static synchronized NWObject getBlockingDoor() throws NWNotInContextException;

    /**
     * Get the current calendar day.
     */
    public native static synchronized int getCalendarDay() throws NWNotInContextException;

    /**
     * Get the current calendar month.
     */
    public native static synchronized int getCalendarMonth() throws NWNotInContextException;

    /**
     * Get the current calendar year.
     */
    public native static synchronized int getCalendarYear() throws NWNotInContextException;

    /**
     * This will read a float from the  specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized float getCampaignFloat(String sCampaignName, String sVarName, NWObject oPlayer) throws NWNotInContextException;

    /**
     * This will read an int from the  specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized int getCampaignInt(String sCampaignName, String sVarName, NWObject oPlayer) throws NWNotInContextException;

    /**
     * This will read a location from the  specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized NWLocation getCampaignLocation(String sCampaignName, String sVarName, NWObject oPlayer) throws NWNotInContextException;

    /**
     * This will read a string from the  specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized String getCampaignString(String sCampaignName, String sVarName, NWObject oPlayer) throws NWNotInContextException;

    /**
     * This will read a vector from the  specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized NWVector getCampaignVector(String sCampaignName, String sVarName, NWObject oPlayer) throws NWNotInContextException;

    /**
     * Get the level at which this creature cast it's last spell (or spell-like ability)
     * * Return value on error, or if oCreature has not yet cast a spell: 0;
     */
    public native static synchronized int getCasterLevel(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get oCreature's challenge rating.
     * * Returns 0.0 if oCreature is invalid.
     */
    public native static synchronized float getChallengeRating(NWObject oCreature) throws NWNotInContextException;

    /**
     * A creature can have up to three classes.  This function determines the
     * creature's class (CLASS_TYPE_*) based on nClassPosition.
     * @param nClassPosition 1, 2 or 3
     * @param oCreature
     * * Returns CLASS_TYPE_INVALID if the oCreature does not have a class in
     * nClassPosition (i.e. a single-class creature will only have a value in
     * nClassLocation=1) or if oCreature is not a valid creature.
     */
    public native static synchronized int getClassByPosition(int nClassPosition, NWObject oCreature) throws NWNotInContextException;

    /**
     * Use this in a trigger's OnClick event script to get the object that last
     * clicked on it.
     * This is identical to GetEnteringObject.
     * GetClickingObject() should not be called from a placeable's OnClick event,
     * instead use GetPlaceableLastClickedBy();
     */
    public native static synchronized NWObject getClickingObject() throws NWNotInContextException;

    /**
     * Get the Color of oObject from the color channel specified.
     * @param oObject the object from which you are obtaining the color.
     * Can be a creature that has color information (i.e. the playable races).
     * @param nColorChannel The color channel that you want to get the color value of.
     * COLOR_CHANNEL_SKIN
     * COLOR_CHANNEL_HAIR
     * COLOR_CHANNEL_TATTOO_1
     * COLOR_CHANNEL_TATTOO_2
     * * Returns -1 on error.
     */
    public native static synchronized int getColor(NWObject oObject, int nColorChannel) throws NWNotInContextException;

    /**
     * Determine whether oTarget's action stack can be modified.
     */
    public native static synchronized boolean getCommandable(NWObject oTarget) throws NWNotInContextException;

    /**
     * returns the model number being used for the body part and creature specified
     * The model number returned is for the body part when the creature is not wearing
     * armor (i.e. whether or not the creature is wearing armor does not affect
     * the return value).
     * Note: Only works on part based creatures, which is typically restricted to
     * the playable races (unless some new part based custom content has been
     * added to the module).
     * 
     * returns CREATURE_PART_INVALID if used on a non-creature object,
     * or if the creature does not use a part based model.
     * 
     * @param nPart (CREATURE_PART_*)
     * CREATURE_PART_RIGHT_FOOT
     * CREATURE_PART_LEFT_FOOT
     * CREATURE_PART_RIGHT_SHIN
     * CREATURE_PART_LEFT_SHIN
     * CREATURE_PART_RIGHT_THIGH
     * CREATURE_PART_LEFT_THIGH
     * CREATURE_PART_PELVIS
     * CREATURE_PART_TORSO
     * CREATURE_PART_BELT
     * CREATURE_PART_NECK
     * CREATURE_PART_RIGHT_FOREARM
     * CREATURE_PART_LEFT_FOREARM
     * CREATURE_PART_RIGHT_BICEP
     * CREATURE_PART_LEFT_BICEP
     * CREATURE_PART_RIGHT_SHOULDER
     * CREATURE_PART_LEFT_SHOULDER
     * CREATURE_PART_RIGHT_HAND
     * CREATURE_PART_LEFT_HAND
     * CREATURE_PART_HEAD
     */
    public native static synchronized int getCreatureBodyPart(int nCreaturePart, NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the size (CREATURE_SIZE_*) of oCreature.
     */
    public native static synchronized int getCreatureSize(NWObject oCreature) throws NWNotInContextException;

    /**
     * Returns the default package selected for this creature to level up with
     * @param returns PACKAGE_INVALID if error occurs
     */
    public native static synchronized int getCreatureStartingPackage(NWObject oCreature) throws NWNotInContextException;

    /**
     * returns the Tail type of the creature specified.
     * CREATURE_TAIL_TYPE_NONE
     * CREATURE_TAIL_TYPE_LIZARD
     * CREATURE_TAIL_TYPE_BONE
     * CREATURE_TAIL_TYPE_DEVIL
     * returns CREATURE_TAIL_TYPE_NONE if used on a non-creature object,
     * if the creature has no Tail, or if the creature can not have its
     * Tail type changed in the toolset.
     */
    public native static synchronized int getCreatureTailType(NWObject oCreature) throws NWNotInContextException;

    /**
     * returns the Wing type of the creature specified.
     * CREATURE_WING_TYPE_NONE
     * CREATURE_WING_TYPE_DEMON
     * CREATURE_WING_TYPE_ANGEL
     * CREATURE_WING_TYPE_BAT
     * CREATURE_WING_TYPE_DRAGON
     * CREATURE_WING_TYPE_BUTTERFLY
     * CREATURE_WING_TYPE_BIRD
     * returns CREATURE_WING_TYPE_NONE if used on a non-creature object,
     * if the creature has no wings, or if the creature can not have its
     * wing type changed in the toolset.
     */
    public native static synchronized int getCreatureWingType(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the current action (ACTION_*) that oObject is executing.
     */
    public native static synchronized int getCurrentAction(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the current hitpoints of oObject
     * * Return value on error: 0
     */
    public native static synchronized int getCurrentHitPoints(NWObject oObject) throws NWNotInContextException;

    /**
     * Returns the current movement rate factor
     * of the cutscene 'camera man'.
     * NOTE: This will be a value between 0.1, 2.0 (10%-200%)
     */
    public native static synchronized float getCutsceneCameraMoveRate(NWObject oCreature) throws NWNotInContextException;

    /**
     * Gets the current cutscene state of the player specified by oCreature.
     * Returns TRUE if the player is in cutscene mode.
     * Returns FALSE if the player is not in cutscene mode, or on an error
     * (such as specifying a non creature object).
     */
    public native static synchronized boolean getCutsceneMode(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the amount of damage of type nDamageType that has been dealt to the caller.
     * @param nDamageType DAMAGE_TYPE_*
     */
    public native static synchronized int getDamageDealtByType(int nDamageType) throws NWNotInContextException;

    /**
     * Returns the defensive casting mode of the specified creature.
     * @param oCreature
     * * Returns a constant DEFENSIVE_CASTING_MODE_*
     */
    public native static synchronized int getDefensiveCastingMode(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the name of oCreature's deity.
     * * Returns "" if oCreature is invalid (or if the deity name is blank for
     * oCreature).
     */
    public native static synchronized String getDeity(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the description of oObject.
     * @param oObject the object from which you are obtaining the description.
     * Can be a creature, item, placeable, door, trigger or module object.
     * @param bOriginalDescription  if set to true any new description specified via a SetDescription scripting command
     * is ignored and the original object's description is returned instead.
     * @param bIdentified If oObject is an item, setting this to TRUE will return the identified description,
     * setting this to FALSE will return the unidentified description. This flag has no
     * effect on objects other than items.
     */
    public native static synchronized String getDescription(NWObject oObject, boolean bOriginalDescription, boolean bIdentifiedDescription) throws NWNotInContextException;

    /**
     * Returns the detection mode of the specified creature.
     * @param oCreature
     * * Returns a constant DETECT_MODE_*
     */
    public native static synchronized int getDetectMode(NWObject oCreature) throws NWNotInContextException;

    /**
     * Gets the length of the specified wavefile, in seconds
     * Only works for sounds used for dialog.
     */
    public native static synchronized float getDialogSoundLength(int nStrRef) throws NWNotInContextException;

    /**
     * Get the distance in metres between oObjectA and oObjectB.
     * * Return value if either object is invalid: 0.0f
     */
    public native static synchronized float getDistanceBetween(NWObject oObjectA, NWObject oObjectB) throws NWNotInContextException;

    /**
     * Get the distance between lLocationA and lLocationB.
     */
    public native static synchronized float getDistanceBetweenLocations(NWLocation lLocationA, NWLocation lLocationB) throws NWNotInContextException;

    /**
     * Get the distance from the caller to oObject in metres.
     * * Return value on error: -1.0f
     */
    public native static synchronized float getDistanceToObject(NWObject oObject) throws NWNotInContextException;

    /**
     * returns TRUE if the item CAN be dropped
     * Droppable items will appear on a creature's remains when the creature is killed.
     */
    public native static synchronized boolean getDroppableFlag(NWObject oItem) throws NWNotInContextException;

    /**
     * Get the object that created eEffect.
     * * Returns OBJECT_INVALID if eEffect is not a valid effect.
     */
    public native static synchronized NWObject getEffectCreator(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Get the duration type (DURATION_TYPE_*) of eEffect.
     * * Return value if eEffect is not valid: -1
     */
    public native static synchronized int getEffectDurationType(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Get the spell (SPELL_*) that applied eSpellEffect.
     * * Returns -1 if eSpellEffect was applied outside a spell script.
     */
    public native static synchronized int getEffectSpellId(NWEffect eSpellEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Get the subtype (SUBTYPE_*) of eEffect.
     * * Return value on error: 0
     */
    public native static synchronized int getEffectSubType(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Get the effect type (EFFECT_TYPE_*) of eEffect.
     * * Return value if eEffect is invalid: EFFECT_INVALIDEFFECT
     */
    public native static synchronized int getEffectType(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Determine whether oEncounter is active.
     */
    public native static synchronized int getEncounterActive(NWObject oEncounter) throws NWNotInContextException;

    /**
     * Get the difficulty level of oEncounter.
     */
    public native static synchronized int getEncounterDifficulty(NWObject oEncounter) throws NWNotInContextException;

    /**
     * Get the number of times that oEncounter has spawned so far
     */
    public native static synchronized int getEncounterSpawnsCurrent(NWObject oEncounter) throws NWNotInContextException;

    /**
     * Get the maximum number of times that oEncounter will spawn.
     */
    public native static synchronized int getEncounterSpawnsMax(NWObject oEncounter) throws NWNotInContextException;

    /**
     * The value returned by this function depends on the object type of the caller:
     * 1) If the caller is a door it returns the object that last
     * triggered it.
     * 2) If the caller is a trigger, area of effect, module, area or encounter it
     * returns the object that last entered it.
     * * Return value on error: OBJECT_INVALID
     * When used for doors, this should only be called from the OnAreaTransitionClick
     * event.  Otherwise, it should only be called in OnEnter scripts.
     */
    public native static synchronized NWObject getEnteringObject() throws NWNotInContextException;

    /**
     * Get the object that last left the caller.  This function works on triggers,
     * areas of effect, modules, areas and encounters.
     * * Return value on error: OBJECT_INVALID
     * Should only be called in OnExit scripts.
     */
    public native static synchronized NWObject getExitingObject() throws NWNotInContextException;

    /**
     * Get the direction in which oTarget is facing, expressed as a float between
     * 0.0f and 360.0f
     * * Return value on error: -1.0f
     */
    public native static synchronized float getFacing(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the orientation value from lLocation.
     */
    public native static synchronized float getFacingFromLocation(NWLocation lLocation) throws NWNotInContextException;

    /**
     * Get an integer between 0 and 100 (inclusive) that represents the average
     * good/evil alignment of oFactionMember's faction.
     * * Return value on error: -1
     */
    public native static synchronized int getFactionAverageGoodEvilAlignment(NWObject oFactionMember) throws NWNotInContextException;

    /**
     * Get an integer between 0 and 100 (inclusive) that represents the average
     * law/chaos alignment of oFactionMember's faction.
     * * Return value on error: -1
     */
    public native static synchronized int getFactionAverageLawChaosAlignment(NWObject oFactionMember) throws NWNotInContextException;

    /**
     * Get the average level of the members of the faction.
     * * Return value on error: -1
     */
    public native static synchronized int getFactionAverageLevel(NWObject oFactionMember) throws NWNotInContextException;

    /**
     * Get an integer between 0 and 100 (inclusive) that represents how
     * oSourceFactionMember's faction feels about oTarget.
     * * Return value on error: -1
     */
    public native static synchronized int getFactionAverageReputation(NWObject oSourceFactionMember, NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the average XP of the members of the faction.
     * * Return value on error: -1
     */
    public native static synchronized int getFactionAverageXP(NWObject oFactionMember) throws NWNotInContextException;

    /**
     * Get the object faction member with the highest armour class.
     * * Returns OBJECT_INVALID if oFactionMember's faction is invalid.
     */
    public native static synchronized NWObject getFactionBestAC(NWObject oFactionMember, boolean bMustBeVisible) throws NWNotInContextException;

    /**
     * * Returns TRUE if the Faction Ids of the two objects are the same
     */
    public native static synchronized boolean getFactionEqual(NWObject oFirstObject, NWObject oSecondObject) throws NWNotInContextException;

    /**
     * Get the amount of gold held by oFactionMember's faction.
     * * Returns -1 if oFactionMember's faction is invalid.
     */
    public native static synchronized int getFactionGold(NWObject oFactionMember) throws NWNotInContextException;

    /**
     * Get the player leader of the faction of which oMemberOfFaction is a member.
     * * Returns OBJECT_INVALID if oMemberOfFaction is not a valid creature,
     * or oMemberOfFaction is a member of a NPC faction.
     */
    public native static synchronized NWObject getFactionLeader(NWObject oMemberOfFaction) throws NWNotInContextException;

    /**
     * Get the member of oFactionMember's faction that has taken the fewest hit
     * points of damage.
     * * Returns OBJECT_INVALID if oFactionMember's faction is invalid.
     */
    public native static synchronized NWObject getFactionLeastDamagedMember(NWObject oFactionMember, boolean bMustBeVisible) throws NWNotInContextException;

    /**
     * Get the member of oFactionMember's faction that has taken the most hit points
     * of damage.
     * * Returns OBJECT_INVALID if oFactionMember's faction is invalid.
     */
    public native static synchronized NWObject getFactionMostDamagedMember(NWObject oFactionMember, boolean bMustBeVisible) throws NWNotInContextException;

    /**
     * Get the most frequent class in the faction @param this can be compared with the
     * constants CLASS_TYPE_*.
     * * Return value on error: -1
     */
    public native static synchronized int getFactionMostFrequentClass(NWObject oFactionMember) throws NWNotInContextException;

    /**
     * Get the strongest member of oFactionMember's faction.
     * * Returns OBJECT_INVALID if oFactionMember's faction is invalid.
     */
    public native static synchronized NWObject getFactionStrongestMember(NWObject oFactionMember, boolean bMustBeVisible) throws NWNotInContextException;

    /**
     * Get the weakest member of oFactionMember's faction.
     * * Returns OBJECT_INVALID if oFactionMember's faction is invalid.
     */
    public native static synchronized NWObject getFactionWeakestMember(NWObject oFactionMember, boolean bMustBeVisible) throws NWNotInContextException;

    /**
     * Get the object faction member with the lowest armour class.
     * * Returns OBJECT_INVALID if oFactionMember's faction is invalid.
     */
    public native static synchronized NWObject getFactionWorstAC(NWObject oFactionMember, boolean bMustBeVisible) throws NWNotInContextException;

    /**
     * Get oCreature's familiar creature type (FAMILIAR_CREATURE_TYPE_*).
     * * Returns FAMILIAR_CREATURE_TYPE_NONE if oCreature is invalid or does not
     * currently have a familiar.
     */
    public native static synchronized int getFamiliarCreatureType(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get oCreature's familiar's name.
     * * Returns "" if oCreature is invalid, does not currently
     * have a familiar or if the familiar's name is blank.
     */
    public native static synchronized String getFamiliarName(NWObject oCreature) throws NWNotInContextException;

    /**
     * Gets the fog amount in the area specified.
     * nFogType = nFogType specifies wether the Sun, or Moon fog type is returned.
     * Valid values for nFogType are FOG_TYPE_SUN or FOG_TYPE_MOON.
     * If no valid area (or object) is specified, it uses the area of caller.
     * If an object other than an area is specified, will use the area that the object is currently in.
     */
    public native static synchronized int getFogAmount(int nFogType, NWObject oArea) throws NWNotInContextException;

    /**
     * Gets the fog color in the area specified.
     * nFogType specifies wether the Sun, or Moon fog type is returned.
     * Valid values for nFogType are FOG_TYPE_SUN or FOG_TYPE_MOON.
     * If no valid area (or object) is specified, it uses the area of caller.
     * If an object other than an area is specified, will use the area that the object is currently in.
     */
    public native static synchronized int getFogColor(int nFogType, NWObject oArea) throws NWNotInContextException;

    /**
     * returns the footstep type of the creature specified.
     * The footstep type determines what the creature's footsteps sound
     * like when ever they take a step.
     * returns FOOTSTEP_TYPE_INVALID if used on a non-creature object, or if
     * used on creature that has no footstep sounds by default (e.g. Will-O'-Wisp).
     */
    public native static synchronized int getFootstepType(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get oTarget's base fortitude saving throw value (this will only work for
     * creatures, doors, and placeables).
     * * Returns 0 if oTarget is invalid.
     */
    public native static synchronized int getFortitudeSavingThrow(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the game difficulty (GAME_DIFFICULTY_*).
     */
    public native static synchronized int getGameDifficulty() throws NWNotInContextException;

    /**
     * Get the gender of oCreature.
     */
    public native static synchronized int getGender(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the creature that is going to attack oTarget.
     * Note: This value is cleared out at the end of every combat round and should
     * not be used in any case except when getting a "going to be attacked" shout
     * from the master creature (and this creature is a henchman)
     * * Returns OBJECT_INVALID if oTarget is not a valid creature.
     */
    public native static synchronized NWObject getGoingToBeAttackedBy(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the amount of gold possessed by oTarget.
     */
    public native static synchronized int getGold(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the gold piece value of oItem.
     * * Returns 0 if oItem is not a valid item.
     */
    public native static synchronized int getGoldPieceValue(NWObject oItem) throws NWNotInContextException;

    /**
     * Get an integer between 0 and 100 (inclusive) to represent oCreature's
     * Good/Evil alignment
     * (100=good, 0=evil)
     * * Return value if oCreature is not a valid creature: -1
     */
    public native static synchronized int getGoodEvilValue(NWObject oCreature) throws NWNotInContextException;

    /**
     * returns the Hardness of a Door or Placeable object.
     * @param oObject a door or placeable object.
     * returns -1 on an error or if used on an object that is
     * neither a door nor a placeable object.
     */
    public native static synchronized int getHardness(NWObject oObject) throws NWNotInContextException;

    /**
     * Determine whether oCreature has nFeat, and nFeat is useable.
     * @param nFeat FEAT_*
     * @param oCreature
     */
    public native static synchronized boolean getHasFeat(int nFeat, NWObject oCreature) throws NWNotInContextException;

    /**
     * @param nFeat FEAT_*
     * @param oObject
     * * Returns TRUE if oObject has effects on it originating from nFeat.
     */
    public native static synchronized boolean getHasFeatEffect(int nFeat, NWObject oObject) throws NWNotInContextException;

    /**
     * Determine whether oObject has an inventory.
     * * Returns TRUE for creatures and stores, and checks to see if an item or placeable object is a container.
     * * Returns FALSE for all other object types.
     */
    public native static synchronized boolean getHasInventory(NWObject oObject) throws NWNotInContextException;

    /**
     * Determine whether oCreature has nSkill, and nSkill is useable.
     * @param nSkill SKILL_*
     * @param oCreature
     */
    public native static synchronized boolean getHasSkill(int nSkill, NWObject oCreature) throws NWNotInContextException;

    /**
     * Determines the number of times that oCreature has nSpell memorised.
     * @param nSpell SPELL_*
     * @param oCreature
     */
    public native static synchronized boolean getHasSpell(int nSpell, NWObject oCreature) throws NWNotInContextException;

    /**
     * Determines whether oObject has any effects applied by nSpell
     * @param nSpell SPELL_*
     * @param oObject
     * * The spell id on effects is only valid if the effect is created
     * when the spell script runs. If it is created in a delayed command
     * then the spell id on the effect will be invalid.
     */
    public native static synchronized boolean getHasSpellEffect(int nSpell, NWObject oObject) throws NWNotInContextException;

    /**
     * Get the henchman belonging to oMaster.
     * * Return OBJECT_INVALID if oMaster does not have a henchman.
     * -nNth: Which henchman to return.
     */
    public native static synchronized NWObject getHenchman(NWObject oMaster, int nNth) throws NWNotInContextException;

    /**
     * Get the number of hitdice for oCreature.
     * * Return value if oCreature is not a valid creature: 0
     */
    public native static synchronized int getHitDice(NWObject oCreature) throws NWNotInContextException;

    /**
     * Determined whether oItem has been identified.
     */
    public native static synchronized boolean getIdentified(NWObject oItem) throws NWNotInContextException;

    /**
     * Get the immortal flag on a creature
     */
    public native static synchronized boolean getImmortal(NWObject oTarget) throws NWNotInContextException;

    /**
     * returns TRUE if the item is flagged as infinite.
     * @param oItem an item.
     * The infinite property affects the buying/selling behavior of the item in a store.
     * An infinite item will still be available to purchase from a store after a player
     * buys the item (non-infinite items will disappear from the store when purchased).
     */
    public native static synchronized boolean getInfiniteFlag(NWObject oItem) throws NWNotInContextException;

    /**
     * get the item that caused the caller's OnInventoryDisturbed script to fire.
     * * Returns OBJECT_INVALID if the caller is not a valid object.
     */
    public native static synchronized NWObject getInventoryDisturbItem() throws NWNotInContextException;

    /**
     * Get the type of disturbance (INVENTORY_DISTURB_*) that caused the caller's
     * OnInventoryDisturbed script to fire.  This will only work for creatures and
     * placeables.
     */
    public native static synchronized int getInventoryDisturbType() throws NWNotInContextException;

    /**
     * Returns AREA_ABOVEGROUND if the area oArea is above ground, AREA_UNDERGROUND otherwise.
     * Returns AREA_INVALID, on an error.
     */
    public native static synchronized boolean getIsAreaAboveGround(NWObject oArea) throws NWNotInContextException;

    /**
     * This will return TRUE if the area is flagged as either interior or underground.
     */
    public native static synchronized boolean getIsAreaInterior(NWObject oArea) throws NWNotInContextException;

    /**
     * Returns AREA_NATURAL if the area oArea is natural, AREA_ARTIFICIAL otherwise.
     * Returns AREA_INVALID, on an error.
     */
    public native static synchronized boolean getIsAreaNatural(NWObject oArea) throws NWNotInContextException;

    /**
     * Is this creature able to be disarmed? (checks disarm flag on creature, and if
     * the creature actually has a weapon equipped in their right hand that is droppable)
     */
    public native static synchronized boolean getIsCreatureDisarmable(NWObject oCreature) throws NWNotInContextException;

    /**
     * * Returns TRUE if oCreature is the Dungeon Master.
     * Note: This will return FALSE if oCreature is a DM Possessed creature.
     * To determine if oCreature is a DM Possessed creature, use GetIsDMPossessed()
     */
    public native static synchronized boolean getIsDM(NWObject oCreature) throws NWNotInContextException;

    /**
     * Returns TRUE if the creature oCreature is currently possessed by a DM character.
     * Returns FALSE otherwise.
     * Note: GetIsDMPossessed() will return FALSE if oCreature is the DM character.
     * To determine if oCreature is a DM character use GetIsDM()
     */
    public native static synchronized boolean getIsDMPossessed(NWObject oCreature) throws NWNotInContextException;

    /**
     * * Returns TRUE if it is currently dawn.
     */
    public native static synchronized boolean getIsDawn() throws NWNotInContextException;

    /**
     * * Returns TRUE if it is currently day.
     */
    public native static synchronized boolean getIsDay() throws NWNotInContextException;

    /**
     * * Returns TRUE if oCreature is a dead NPC, dead PC or a dying PC.
     */
    public native static synchronized boolean getIsDead(NWObject oCreature) throws NWNotInContextException;

    /**
     * @param oTargetDoor
     * @param nDoorAction DOOR_ACTION_*
     * * Returns TRUE if nDoorAction can be performed on oTargetDoor.
     */
    public native static synchronized boolean getIsDoorActionPossible(NWObject oTargetDoor, int nDoorAction) throws NWNotInContextException;

    /**
     * * Returns TRUE if it is currently dusk.
     */
    public native static synchronized boolean getIsDusk() throws NWNotInContextException;

    /**
     * * Returns TRUE if eEffect is a valid effect. The effect must have been applied to
     * * an object or else it will return FALSE
     */
    public native static synchronized boolean getIsEffectValid(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * * Returns TRUE if oCreature was spawned from an encounter.
     */
    public native static synchronized boolean getIsEncounterCreature(NWObject oCreature) throws NWNotInContextException;

    /**
     * * Returns TRUE if oSource considers oTarget as an enemy.
     */
    public native static synchronized boolean getIsEnemy(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * * Returns TRUE if oSource considers oTarget as a friend.
     */
    public native static synchronized boolean getIsFriend(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * @param oCreature
     * @param nImmunityType IMMUNITY_TYPE_*
     * @param oVersus if this is specified, then we also check for the race and
     * alignment of oVersus
     * * Returns TRUE if oCreature has immunity of type nImmunity versus oVersus.
     */
    public native static synchronized boolean getIsImmune(NWObject oCreature, int nImmunityType, NWObject oVersus) throws NWNotInContextException;

    /**
     * * Returns TRUE if oCreature is in combat.
     */
    public native static synchronized boolean getIsInCombat(NWObject oCreature) throws NWNotInContextException;

    /**
     * Is this creature in the given subarea? (trigger, area of effect object, etc..)
     * This function will tell you if the creature has triggered an onEnter event,
     * not if it is physically within the space of the subarea
     */
    public native static synchronized boolean getIsInSubArea(NWObject oCreature, NWObject oSubArea) throws NWNotInContextException;

    /**
     * if the item property is valid this will return true
     */
    public native static synchronized boolean getIsItemPropertyValid(NWItemProperty ipProperty) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * * Returns TRUE if oObject is listening for something
     */
    public native static synchronized boolean getIsListening(NWObject oObject) throws NWNotInContextException;

    /**
     * * Returns TRUE if oSource considers oTarget as neutral.
     */
    public native static synchronized boolean getIsNeutral(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * * Returns TRUE if it is currently night.
     */
    public native static synchronized boolean getIsNight() throws NWNotInContextException;

    /**
     * * Returns TRUE if oObject is a valid object.
     */
    public native static synchronized boolean getIsObjectValid(NWObject oObject) throws NWNotInContextException;

    /**
     * * Returns TRUE if oObject (which is a placeable or a door) is currently open.
     */
    public native static synchronized boolean getIsOpen(NWObject oObject) throws NWNotInContextException;

    /**
     * * Returns TRUE if oCreature is a Player Controlled character.
     */
    public native static synchronized boolean getIsPC(NWObject oCreature) throws NWNotInContextException;

    /**
     * @param oPlaceable
     * @param nPlaceableAction PLACEABLE_ACTION_*
     * * Returns TRUE if nPlacebleAction is valid for oPlaceable.
     */
    public native static synchronized boolean getIsPlaceableObjectActionPossible(NWObject oPlaceable, int nPlaceableAction) throws NWNotInContextException;

    /**
     * * Returns TRUE if oCreature is of a playable racial type.
     */
    public native static synchronized boolean getIsPlayableRacialType(NWObject oCreature) throws NWNotInContextException;

    /**
     * This will return TRUE if the creature running the script is a familiar currently
     * possessed by his master.
     * returns FALSE if not or if the creature object is invalid
     */
    public native static synchronized boolean getIsPossessedFamiliar(NWObject oCreature) throws NWNotInContextException;

    /**
     * Determine whether oSource has a friendly reaction towards oTarget, depending
     * on the reputation, PVP setting and (if both oSource and oTarget are PCs),
     * oSource's Like/Dislike setting for oTarget.
     * Note: If you just want to know how two objects feel about each other in terms
     * of faction and personal reputation, use GetIsFriend() instead.
     * * Returns TRUE if oSource has a friendly reaction towards oTarget
     */
    public native static synchronized boolean getIsReactionTypeFriendly(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * Determine whether oSource has a Hostile reaction towards oTarget, depending
     * on the reputation, PVP setting and (if both oSource and oTarget are PCs),
     * oSource's Like/Dislike setting for oTarget.
     * Note: If you just want to know how two objects feel about each other in terms
     * of faction and personal reputation, use GetIsEnemy() instead.
     * * Returns TRUE if oSource has a hostile reaction towards oTarget
     */
    public native static synchronized boolean getIsReactionTypeHostile(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * Determine whether oSource has a neutral reaction towards oTarget, depending
     * on the reputation, PVP setting and (if both oSource and oTarget are PCs),
     * oSource's Like/Dislike setting for oTarget.
     * Note: If you just want to know how two objects feel about each other in terms
     * of faction and personal reputation, use GetIsNeutral() instead.
     * * Returns TRUE if oSource has a neutral reaction towards oTarget
     */
    public native static synchronized boolean getIsReactionTypeNeutral(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * * Returns TRUE if oCreature is resting.
     */
    public native static synchronized boolean getIsResting(NWObject oCreature) throws NWNotInContextException;

    /**
     * ***********************  END OF ITEM PROPERTY FUNCTIONS  **************************
     * 
     * Returns true if 1d20 roll + skill rank is greater than or equal to difficulty
     * @param oTarget the creature using the skill
     * @param nSkill the skill being used
     * @param nDifficulty Difficulty class of skill
     */
    public native static synchronized boolean getIsSkillSuccessful(NWObject oTarget, int nSkill, int nDifficulty) throws NWNotInContextException;

    /**
     * Note: Only placeables, doors and triggers can be trapped.
     * * Returns TRUE if oObject is trapped.
     */
    public native static synchronized boolean getIsTrapped(NWObject oObject) throws NWNotInContextException;

    /**
     * * Returns TRUE if the weapon equipped is capable of damaging oVersus.
     */
    public native static synchronized boolean getIsWeaponEffective(NWObject oVersus, boolean bOffHand) throws NWNotInContextException;

    /**
     * Get the Armour Class of oItem.
     * * Return 0 if the oItem is not a valid item, or if oItem has no armour value.
     */
    public native static synchronized int getItemACValue(NWObject oItem) throws NWNotInContextException;

    /**
     * Use this in an OnItemActivated module script to get the item that was activated.
     */
    public native static synchronized NWObject getItemActivated() throws NWNotInContextException;

    /**
     * Use this in an OnItemActivated module script to get the item's target.
     */
    public native static synchronized NWObject getItemActivatedTarget() throws NWNotInContextException;

    /**
     * Use this in an OnItemActivated module script to get the location of the item's
     * target.
     */
    public native static synchronized NWLocation getItemActivatedTargetLocation() throws NWNotInContextException;

    /**
     * Use this in an OnItemActivated module script to get the creature that
     * activated the item.
     */
    public native static synchronized NWObject getItemActivator() throws NWNotInContextException;

    /**
     * Queries the current value of the appearance settings on an item. The parameters are
     * identical to those of CopyItemAndModify().
     */
    public native static synchronized int getItemAppearance(NWObject oItem, int nType, int nIndex) throws NWNotInContextException;

    /**
     * Returns charges left on an item
     * @param oItem item to query
     */
    public native static synchronized int getItemCharges(NWObject oItem) throws NWNotInContextException;

    /**
     * Returns TRUE if the item is cursed and cannot be dropped
     */
    public native static synchronized boolean getItemCursedFlag(NWObject oItem) throws NWNotInContextException;

    /**
     * Determines whether oItem has nProperty.
     * @param oItem
     * @param nProperty ITEM_PROPERTY_*
     * * Returns FALSE if oItem is not a valid item, or if oItem does not have
     * nProperty.
     */
    public native static synchronized boolean getItemHasItemProperty(NWObject oItem, int nProperty) throws NWNotInContextException;

    /**
     * Get the object which is in oCreature's specified inventory slot
     * @param nInventorySlot INVENTORY_SLOT_*
     * @param oCreature
     * * Returns OBJECT_INVALID if oCreature is not a valid creature or there is no
     * item in nInventorySlot.
     */
    public native static synchronized NWObject getItemInSlot(int nInventorySlot, NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the object possessed by oCreature with the tag sItemTag
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getItemPossessedBy(NWObject oCreature, String sItemTag) throws NWNotInContextException;

    /**
     * Get the possessor of oItem
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getItemPossessor(NWObject oItem) throws NWNotInContextException;

    /**
     * Returns the Cost Table number of the item property. See the 2DA files for value definitions.
     */
    public native static synchronized int getItemPropertyCostTable(NWItemProperty iProp) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * Returns the Cost Table value (index of the cost table) of the item property.
     * See the 2DA files for value definitions.
     */
    public native static synchronized int getItemPropertyCostTableValue(NWItemProperty iProp) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * will return the duration type of the item property
     */
    public native static synchronized int getItemPropertyDurationType(NWItemProperty ip) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * Returns the Param1 number of the item property. See the 2DA files for value definitions.
     */
    public native static synchronized int getItemPropertyParam1(NWItemProperty iProp) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * Returns the Param1 value of the item property. See the 2DA files for value definitions.
     */
    public native static synchronized int getItemPropertyParam1Value(NWItemProperty iProp) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * Returns the SubType number of the item property. See the 2DA files for value definitions.
     */
    public native static synchronized int getItemPropertySubType(NWItemProperty iProperty) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * will return the item property type (ie. holy avenger)
     */
    public native static synchronized int getItemPropertyType(NWItemProperty ip) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * Returns stack size of an item
     * @param oItem item to query
     */
    public native static synchronized int getItemStackSize(NWObject oItem) throws NWNotInContextException;

    /**
     * Get the experience assigned in the journal editor for szPlotID.
     */
    public native static synchronized int getJournalQuestExperience(String szPlotID) throws NWNotInContextException;

    /**
     * Get the feedback message that will be displayed when trying to unlock the object oObject.
     * @param oObject a door or placeable.
     * Returns an empty string "" on an error or if the game's default feedback message is being used
     */
    public native static synchronized String getKeyRequiredFeedback(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the last command (ASSOCIATE_COMMAND_*) issued to oAssociate.
     */
    public native static synchronized int getLastAssociateCommand(NWObject oAssociate) throws NWNotInContextException;

    /**
     * Get the attack mode (COMBAT_MODE_*) of oCreature's last attack.
     * This only works when oCreature is in combat.
     */
    public native static synchronized int getLastAttackMode(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the attack type (SPECIAL_ATTACK_*) of oCreature's last attack.
     * This only works when oCreature is in combat.
     */
    public native static synchronized int getLastAttackType(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the last attacker of oAttackee.  This should only be used ONLY in the
     * OnAttacked events for creatures, placeables and doors.
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getLastAttacker(NWObject oAttackee) throws NWNotInContextException;

    /**
     * Use this in an OnClosed script to get the object that closed the door or placeable.
     * * Returns OBJECT_INVALID if the caller is not a valid door or placeable.
     */
    public native static synchronized NWObject getLastClosedBy() throws NWNotInContextException;

    /**
     * Get the last object that damaged oObject
     * * Returns OBJECT_INVALID if the passed in object is not a valid object.
     */
    public native static synchronized NWObject getLastDamager(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the last object that disarmed the trap on the caller.
     * * Returns OBJECT_INVALID if the caller is not a valid placeable, trigger or
     * door.
     */
    public native static synchronized NWObject getLastDisarmed() throws NWNotInContextException;

    /**
     * Get the last object that disturbed the inventory of the caller.
     * * Returns OBJECT_INVALID if the caller is not a valid creature or placeable.
     */
    public native static synchronized NWObject getLastDisturbed() throws NWNotInContextException;

    /**
     * Get the last object that was sent as a GetLastAttacker(), GetLastDamager(),
     * GetLastSpellCaster() (for a hostile spell), or GetLastDisturbed() (when a
     * creature is pickpocketed).
     * Note: Return values may only ever be:
     * 1) A Creature
     * 2) Plot Characters will never have this value set
     * 3) Area of Effect Objects will return the AOE creator if they are registered
     * as this value, otherwise they will return INVALID_OBJECT_ID
     * 4) Traps will not return the creature that set the trap.
     * 5) This value will never be overwritten by another non-creature object.
     * 6) This value will never be a dead/destroyed creature
     */
    public native static synchronized NWObject getLastHostileActor(NWObject oVictim) throws NWNotInContextException;

    /**
     * Get the object that killed the caller.
     */
    public native static synchronized NWObject getLastKiller() throws NWNotInContextException;

    /**
     * Get the last object that locked the caller.
     * * Returns OBJECT_INVALID if the caller is not a valid door or placeable.
     */
    public native static synchronized NWObject getLastLocked() throws NWNotInContextException;

    /**
     * Get the last creature that opened the caller.
     * * Returns OBJECT_INVALID if the caller is not a valid door, placeable or store.
     */
    public native static synchronized NWObject getLastOpenedBy() throws NWNotInContextException;

    /**
     * Get the last PC that has rested in the module.
     */
    public native static synchronized NWObject getLastPCRested() throws NWNotInContextException;

    /**
     * Gets the last player character to cancel from a cutscene.
     */
    public native static synchronized NWObject getLastPCToCancelCutscene() throws NWNotInContextException;

    /**
     * Use this in an OnPerception script to get the object that was perceived.
     * * Returns OBJECT_INVALID if the caller is not a valid creature.
     */
    public native static synchronized NWObject getLastPerceived() throws NWNotInContextException;

    /**
     * Use this in an OnPerception script to determine whether the object that was
     * perceived was heard.
     */
    public native static synchronized boolean getLastPerceptionHeard() throws NWNotInContextException;

    /**
     * Use this in an OnPerception script to determine whether the object that was
     * perceived has become inaudible.
     */
    public native static synchronized boolean getLastPerceptionInaudible() throws NWNotInContextException;

    /**
     * Use this in an OnPerception script to determine whether the object that was
     * perceived was seen.
     */
    public native static synchronized boolean getLastPerceptionSeen() throws NWNotInContextException;

    /**
     * Use this in an OnPerception script to determine whether the object that was
     * perceived has vanished.
     */
    public native static synchronized boolean getLastPerceptionVanished() throws NWNotInContextException;

    /**
     * Use this in an OnPlayerDeath module script to get the last player that died.
     */
    public native static synchronized NWObject getLastPlayerDied() throws NWNotInContextException;

    /**
     * Use this in an OnPlayerDying module script to get the last player who is dying.
     */
    public native static synchronized NWObject getLastPlayerDying() throws NWNotInContextException;

    /**
     * Use this in an OnRespawnButtonPressed module script to get the object id of
     * the player who last pressed the respawn button.
     */
    public native static synchronized NWObject getLastRespawnButtonPresser() throws NWNotInContextException;

    /**
     * Determine the type (REST_EVENTTYPE_REST_*) of the last rest event (as
     * returned from the OnPCRested module event).
     */
    public native static synchronized int getLastRestEventType() throws NWNotInContextException;

    /**
     * Use this in a conversation script to get the person with whom you are conversing.
     * * Returns OBJECT_INVALID if the caller is not a valid creature.
     */
    public native static synchronized NWObject getLastSpeaker() throws NWNotInContextException;

    /**
     * This is for use in a "Spell Cast" script, it gets the ID of the spell that
     * was cast.
     */
    public native static synchronized int getLastSpell() throws NWNotInContextException;

    /**
     * Returns the class that the spellcaster cast the
     * spell as.
     * @param Returns CLASS_TYPE_INVALID if the caster has
     * no valid class (placeables, etc...)
     */
    public native static synchronized int getLastSpellCastClass() throws NWNotInContextException;

    /**
     * This is for use in a "Spell Cast" script, it gets who cast the spell.
     * The spell could have been cast by a creature, placeable or door.
     * * Returns OBJECT_INVALID if the caller is not a creature, placeable or door.
     */
    public native static synchronized NWObject getLastSpellCaster() throws NWNotInContextException;

    /**
     * Use this in a SpellCast script to determine whether the spell was considered
     * harmful.
     * * Returns TRUE if the last spell cast was harmful.
     */
    public native static synchronized boolean getLastSpellHarmful() throws NWNotInContextException;

    /**
     * Get the last trap detected by oTarget.
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getLastTrapDetected(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the last object that unlocked the caller.
     * * Returns OBJECT_INVALID if the caller is not a valid door or placeable.
     */
    public native static synchronized NWObject getLastUnlocked() throws NWNotInContextException;

    /**
     * Get the last object that used the placeable object that is calling this function.
     * * Returns OBJECT_INVALID if it is called by something other than a placeable or
     * a door.
     */
    public native static synchronized NWObject getLastUsedBy() throws NWNotInContextException;

    /**
     * Get the last weapon that oCreature used in an attack.
     * * Returns OBJECT_INVALID if oCreature did not attack, or has no weapon equipped.
     */
    public native static synchronized NWObject getLastWeaponUsed(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get an integer between 0 and 100 (inclusive) to represent oCreature's
     * Law/Chaos alignment
     * (100=law, 0=chaos)
     * * Return value if oCreature is not a valid creature: -1
     */
    public native static synchronized int getLawChaosValue(NWObject oCreature) throws NWNotInContextException;

    /**
     * Determine the levels that oCreature holds in nClassType.
     * @param nClassType CLASS_TYPE_*
     * @param oCreature
     */
    public native static synchronized int getLevelByClass(int nClassType, NWObject oCreature) throws NWNotInContextException;

    /**
     * A creature can have up to three classes.  This function determines the
     * creature's class level based on nClass Position.
     * @param nClassPosition 1, 2 or 3
     * @param oCreature
     * * Returns 0 if oCreature does not have a class in nClassPosition
     * (i.e. a single-class creature will only have a value in nClassLocation=1)
     * or if oCreature is not a valid creature.
     */
    public native static synchronized int getLevelByPosition(int nClassPosition, NWObject oCreature) throws NWNotInContextException;

    /**
     * In an onConversation script this gets the number of the string pattern
     * matched (the one that triggered the script).
     * * Returns -1 if no string matched
     */
    public native static synchronized int getListenPatternNumber() throws NWNotInContextException;

    /**
     * Get oObject's local float variable sVarName
     * * Return value on error: 0.0f
     */
    public native static synchronized float getLocalFloat(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Get oObject's local integer variable sVarName
     * * Return value on error: 0
     */
    public native static synchronized int getLocalInt(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Get oObject's local location variable sVarname
     */
    public native static synchronized NWLocation getLocalLocation(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Get oObject's local object variable sVarName
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getLocalObject(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Get oObject's local string variable sVarName
     * * Return value on error: ""
     */
    public native static synchronized String getLocalString(NWObject oObject, String sVarName) throws NWNotInContextException;

    /**
     * Get the location of oObject.
     */
    public native static synchronized NWLocation getLocation(NWObject oObject) throws NWNotInContextException;

    /**
     * * Returns TRUE if a specific key is required to open the lock on oObject.
     */
    public native static synchronized boolean getLockKeyRequired(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the tag of the key that will open the lock on oObject.
     */
    public native static synchronized String getLockKeyTag(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the DC for locking oObject.
     */
    public native static synchronized int getLockLockDC(NWObject oObject) throws NWNotInContextException;

    /**
     * * Returns TRUE if the lock on oObject is lockable.
     */
    public native static synchronized boolean getLockLockable(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the DC for unlocking oObject.
     */
    public native static synchronized int getLockUnlockDC(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the locked state of oTarget, which can be a door or a placeable object.
     */
    public native static synchronized boolean getLocked(NWObject oTarget) throws NWNotInContextException;

    /**
     * Returns the lootable state of a creature.
     */
    public native static synchronized boolean getLootable(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the master of oAssociate.
     */
    public native static synchronized NWObject getMaster(NWObject oAssociate) throws NWNotInContextException;

    /**
     * Get the appropriate matched string (this should only be used in
     * OnConversation scripts).
     * * Returns the appropriate matched string, otherwise returns ""
     */
    public native static synchronized String getMatchedSubstring(int nString) throws NWNotInContextException;

    /**
     * Get the number of string parameters available.
     * * Returns -1 if no string matched (this could be because of a dialogue event)
     */
    public native static synchronized int getMatchedSubstringsCount() throws NWNotInContextException;

    /**
     * Gets the maximum number of henchmen
     */
    public native static synchronized int getMaxHenchmen() throws NWNotInContextException;

    /**
     * Get the maximum hitpoints of oObject
     * * Return value on error: 0
     */
    public native static synchronized int getMaxHitPoints(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the metamagic type (METAMAGIC_*) of the last spell cast by the caller
     * * Return value if the caster is not a valid object: -1
     */
    public native static synchronized int getMetaMagicFeat() throws NWNotInContextException;

    /**
     * Use this in an OnItemAcquired script to get the item that was acquired.
     * * Returns OBJECT_INVALID if the module is not valid.
     */
    public native static synchronized NWObject getModuleItemAcquired() throws NWNotInContextException;

    /**
     * Gets the object that acquired the module item.  May be a creature, item, or placeable
     */
    public native static synchronized NWObject getModuleItemAcquiredBy() throws NWNotInContextException;

    /**
     * Use this in an OnItemAcquired script to get the creatre that previously
     * possessed the item.
     * * Returns OBJECT_INVALID if the item was picked up from the ground.
     */
    public native static synchronized NWObject getModuleItemAcquiredFrom() throws NWNotInContextException;

    /**
     * in an onItemAcquired script, returns the size of the stack of the item
     * that was just acquired.
     * * returns the stack size of the item acquired
     */
    public native static synchronized int getModuleItemAcquiredStackSize() throws NWNotInContextException;

    /**
     * Use this in an OnItemLost script to get the item that was lost/dropped.
     * * Returns OBJECT_INVALID if the module is not valid.
     */
    public native static synchronized NWObject getModuleItemLost() throws NWNotInContextException;

    /**
     * Use this in an OnItemLost script to get the creature that lost the item.
     * * Returns OBJECT_INVALID if the module is not valid.
     */
    public native static synchronized NWObject getModuleItemLostBy() throws NWNotInContextException;

    /**
     * Get the module's name in the language of the server that's running it.
     * * If there is no entry for the language of the server, it will return an
     * empty string
     */
    public native static synchronized String getModuleName() throws NWNotInContextException;

    /**
     * Get the XP scale being used for the module.
     */
    public native static synchronized int getModuleXPScale() throws NWNotInContextException;

    /**
     * Get oCreature's movement rate.
     * * Returns 0 if oCreature is invalid.
     */
    public native static synchronized int getMovementRate(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the name of oObject.
     * @param bOriginalName  if set to true any new name specified via a SetName scripting command
     * is ignored and the original object's name is returned instead.
     */
    public native static synchronized String getName(NWObject oObject, boolean bOriginalName) throws NWNotInContextException;

    /**
     * Get the creature nearest to oTarget, subject to all the criteria specified.
     * @param nFirstCriteriaType CREATURE_TYPE_*
     * @param nFirstCriteriaValue:
     * -> CLASS_TYPE_* if nFirstCriteriaType was CREATURE_TYPE_CLASS
     * -> SPELL_* if nFirstCriteriaType was CREATURE_TYPE_DOES_NOT_HAVE_SPELL_EFFECT
     * or CREATURE_TYPE_HAS_SPELL_EFFECT
     * -> TRUE or FALSE if nFirstCriteriaType was CREATURE_TYPE_IS_ALIVE
     * -> PERCEPTION_* if nFirstCriteriaType was CREATURE_TYPE_PERCEPTION
     * -> PLAYER_CHAR_IS_PC or PLAYER_CHAR_NOT_PC if nFirstCriteriaType was
     * CREATURE_TYPE_PLAYER_CHAR
     * -> RACIAL_TYPE_* if nFirstCriteriaType was CREATURE_TYPE_RACIAL_TYPE
     * -> REPUTATION_TYPE_* if nFirstCriteriaType was CREATURE_TYPE_REPUTATION
     * For example, to get the nearest PC, use:
     * (CREATURE_TYPE_PLAYER_CHAR, PLAYER_CHAR_IS_PC)
     * @param oTarget We're trying to find the creature of the specified type that is
     * nearest to oTarget
     * @param nNth We don't have to find the first nearest: we can find the Nth nearest...
     * @param nSecondCriteriaType This is used in the same way as nFirstCriteriaType to
     * further specify the type of creature that we are looking for.
     * @param nSecondCriteriaValue This is used in the same way as nFirstCriteriaValue
     * to further specify the type of creature that we are looking for.
     * @param nThirdCriteriaType This is used in the same way as nFirstCriteriaType to
     * further specify the type of creature that we are looking for.
     * @param nThirdCriteriaValue This is used in the same way as nFirstCriteriaValue to
     * further specify the type of creature that we are looking for.
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getNearestCreature(int nFirstCriteriaType, int nFirstCriteriaValue, NWObject oTarget, int nNth, int nSecondCriteriaType, int nSecondCriteriaValue, int nThirdCriteriaType, int nThirdCriteriaValue) throws NWNotInContextException;

    /**
     * Get the creature nearest to lLocation, subject to all the criteria specified.
     * @param nFirstCriteriaType CREATURE_TYPE_*
     * @param nFirstCriteriaValue:
     * -> CLASS_TYPE_* if nFirstCriteriaType was CREATURE_TYPE_CLASS
     * -> SPELL_* if nFirstCriteriaType was CREATURE_TYPE_DOES_NOT_HAVE_SPELL_EFFECT
     * or CREATURE_TYPE_HAS_SPELL_EFFECT
     * -> TRUE or FALSE if nFirstCriteriaType was CREATURE_TYPE_IS_ALIVE
     * -> PERCEPTION_* if nFirstCriteriaType was CREATURE_TYPE_PERCEPTION
     * -> PLAYER_CHAR_IS_PC or PLAYER_CHAR_NOT_PC if nFirstCriteriaType was
     * CREATURE_TYPE_PLAYER_CHAR
     * -> RACIAL_TYPE_* if nFirstCriteriaType was CREATURE_TYPE_RACIAL_TYPE
     * -> REPUTATION_TYPE_* if nFirstCriteriaType was CREATURE_TYPE_REPUTATION
     * For example, to get the nearest PC, use
     * (CREATURE_TYPE_PLAYER_CHAR, PLAYER_CHAR_IS_PC)
     * @param lLocation We're trying to find the creature of the specified type that is
     * nearest to lLocation
     * @param nNth We don't have to find the first nearest: we can find the Nth nearest....
     * @param nSecondCriteriaType This is used in the same way as nFirstCriteriaType to
     * further specify the type of creature that we are looking for.
     * @param nSecondCriteriaValue This is used in the same way as nFirstCriteriaValue
     * to further specify the type of creature that we are looking for.
     * @param nThirdCriteriaType This is used in the same way as nFirstCriteriaType to
     * further specify the type of creature that we are looking for.
     * @param nThirdCriteriaValue This is used in the same way as nFirstCriteriaValue to
     * further specify the type of creature that we are looking for.
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getNearestCreatureToLocation(int nFirstCriteriaType, int nFirstCriteriaValue, NWLocation lLocation, int nNth, int nSecondCriteriaType, int nSecondCriteriaValue, int nThirdCriteriaType, int nThirdCriteriaValue) throws NWNotInContextException;

    /**
     * Get the Nth object nearest to oTarget that is of the specified type.
     * @param nObjectType OBJECT_TYPE_*
     * @param oTarget
     * @param nNth
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getNearestObject(int nObjectType, NWObject oTarget, int nNth) throws NWNotInContextException;

    /**
     * Get the nth Object nearest to oTarget that has sTag as its tag.
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getNearestObjectByTag(String sTag, NWObject oTarget, int nNth) throws NWNotInContextException;

    /**
     * Get the nNth object nearest to lLocation that is of the specified type.
     * @param nObjectType OBJECT_TYPE_*
     * @param lLocation
     * @param nNth
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getNearestObjectToLocation(int nObjectType, NWLocation lLocation, int nNth) throws NWNotInContextException;

    /**
     * Get the trap nearest to oTarget.
     * Note : "trap objects" are actually any trigger, placeable or door that is
     * trapped in oTarget's area.
     * @param oTarget
     * @param bTrapDetected if this is TRUE, the trap returned has to have been detected
     * by oTarget.
     */
    public native static synchronized NWObject getNearestTrapToObject(NWObject oTarget, boolean bTrapDetected) throws NWNotInContextException;

    /**
     * Get the number of stacked items that oItem comprises.
     */
    public native static synchronized int getNumStackedItems(NWObject oItem) throws NWNotInContextException;

    /**
     * Get the nNth object with the specified tag.
     * @param sTag
     * @param nNth the nth object with this tag may be requested
     * * Returns OBJECT_INVALID if the object cannot be found.
     * Note: The module cannot be retrieved by GetObjectByTag(), use GetModule() instead.
     */
    public native static synchronized NWObject getObjectByTag(String sTag, int nNth) throws NWNotInContextException;

    /**
     * Determine whether oSource hears oTarget.
     * NOTE: This *only* works on creatures, as visibility lists are not
     * maintained for non-creature objects.
     */
    public native static synchronized boolean getObjectHeard(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * Determine whether oSource sees oTarget.
     * NOTE: This *only* works on creatures, as visibility lists are not
     * maintained for non-creature objects.
     */
    public native static synchronized boolean getObjectSeen(NWObject oTarget, NWObject oSource) throws NWNotInContextException;

    /**
     * Get the object type (OBJECT_TYPE_*) of oTarget
     * * Return value if oTarget is not a valid object: -1
     */
    public native static synchronized int getObjectType(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the last player chat(text) message that was sent.
     * Should only be called from a module's OnPlayerChat event script.
     * * Returns empty string "" on error.
     * Note: Private tells do not trigger a OnPlayerChat event.
     */
    public native static synchronized String getPCChatMessage() throws NWNotInContextException;

    /**
     * Get the PC that sent the last player chat(text) message.
     * Should only be called from a module's OnPlayerChat event script.
     * * Returns OBJECT_INVALID on error.
     * Note: Private tells do not trigger a OnPlayerChat event.
     */
    public native static synchronized NWObject getPCChatSpeaker() throws NWNotInContextException;

    /**
     * Get the volume of the last player chat(text) message that was sent.
     * Returns one of the following TALKVOLUME_* constants based on the volume setting
     * that the player used to send the chat message.
     * TALKVOLUME_TALK
     * TALKVOLUME_WHISPER
     * TALKVOLUME_SHOUT
     * TALKVOLUME_SILENT_SHOUT (used for DM chat channel)
     * TALKVOLUME_PARTY
     * Should only be called from a module's OnPlayerChat event script.
     * * Returns -1 on error.
     * Note: Private tells do not trigger a OnPlayerChat event.
     */
    public native static synchronized int getPCChatVolume() throws NWNotInContextException;

    /**
     * Get the IP address from which oPlayer has connected.
     */
    public native static synchronized String getPCIPAddress(NWObject oPlayer) throws NWNotInContextException;

    /**
     * Use this to get the item last equipped by a player character in OnPlayerEquipItem..
     */
    public native static synchronized NWObject getPCItemLastEquipped() throws NWNotInContextException;

    /**
     * Use this to get the player character who last equipped an item in OnPlayerEquipItem..
     */
    public native static synchronized NWObject getPCItemLastEquippedBy() throws NWNotInContextException;

    /**
     * Use this to get the item last unequipped by a player character in OnPlayerEquipItem..
     */
    public native static synchronized NWObject getPCItemLastUnequipped() throws NWNotInContextException;

    /**
     * Use this to get the player character who last unequipped an item in OnPlayerUnEquipItem..
     */
    public native static synchronized NWObject getPCItemLastUnequippedBy() throws NWNotInContextException;

    /**
     * Get the last PC that levelled up.
     */
    public native static synchronized NWObject getPCLevellingUp() throws NWNotInContextException;

    /**
     * Get the name of oPlayer.
     */
    public native static synchronized String getPCPlayerName(NWObject oPlayer) throws NWNotInContextException;

    /**
     * Get the public part of the CD Key that oPlayer used when logging in.
     * @param bSinglePlayerCDKey If set to TRUE, the player's public CD Key will
     * be returned when the player is playing in single player mode
     * (otherwise returns an empty string in single player mode).
     */
    public native static synchronized String getPCPublicCDKey(NWObject oPlayer, boolean bSinglePlayerCDKey) throws NWNotInContextException;

    /**
     * Get the PC that is involved in the conversation.
     * * Returns OBJECT_INVALID on error.
     */
    public native static synchronized NWObject getPCSpeaker() throws NWNotInContextException;

    /**
     * Returns the creature's currently set PhenoType (body type).
     */
    public native static synchronized int getPhenoType(NWObject oCreature) throws NWNotInContextException;

    /**
     * returns TRUE if the item CAN be pickpocketed
     */
    public native static synchronized boolean getPickpocketableFlag(NWObject oItem) throws NWNotInContextException;

    /**
     * * Returns TRUE if the illumination for oPlaceable is on
     */
    public native static synchronized int getPlaceableIllumination(NWObject oPlaceable) throws NWNotInContextException;

    /**
     * Get the last object that default clicked (left clicked) on the placeable object
     * that is calling this function.
     * Should only be called from a placeables OnClick event.
     * * Returns OBJECT_INVALID if it is called by something other than a placeable.
     */
    public native static synchronized NWObject getPlaceableLastClickedBy() throws NWNotInContextException;

    /**
     * Determine whether oTarget is a plot object.
     */
    public native static synchronized boolean getPlotFlag(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the PortraitId of oTarget.
     * @param oTarget the object for which you are getting the portrait Id.
     * Returns: The Portrait Id number being used for the object oTarget.
     * The Portrait Id refers to the row number of the Portraits.2da
     * that this portrait is from.
     * If a custom portrait is being used, oTarget is a player object,
     * or on an error returns PORTRAIT_INVALID. In these instances
     * try using GetPortraitResRef() instead.
     */
    public native static synchronized int getPortraitId(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the Portrait ResRef of oTarget.
     * @param oTarget the object for which you are getting the portrait ResRef.
     * Returns: The Portrait ResRef being used for the object oTarget.
     * The Portrait ResRef will not include a trailing size letter.
     */
    public native static synchronized String getPortraitResRef(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the position of oTarget
     * * Return value on error: vector (0.0f, 0.0f, 0.0f)
     */
    public native static synchronized NWVector getPosition(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get the position vector from lLocation.
     */
    public native static synchronized NWVector getPositionFromLocation(NWLocation lLocation) throws NWNotInContextException;

    /**
     * Get the racial type (RACIAL_TYPE_*) of oCreature
     * * Return value if oCreature is not a valid creature: RACIAL_TYPE_INVALID
     */
    public native static synchronized int getRacialType(NWObject oCreature) throws NWNotInContextException;

    /**
     * Use this in spell scripts to get nDamage adjusted by oTarget's reflex and
     * evasion saves.
     * @param nDamage
     * @param oTarget
     * @param nDC Difficulty check
     * @param nSaveType SAVING_THROW_TYPE_*
     * @param oSaveVersus
     */
    public native static synchronized int getReflexAdjustedDamage(int nDamage, NWObject oTarget, int nDC, int nSaveType, NWObject oSaveVersus) throws NWNotInContextException;

    /**
     * Get oTarget's base reflex saving throw value (this will only work for
     * creatures, doors, and placeables).
     * * Returns 0 if oTarget is invalid.
     */
    public native static synchronized int getReflexSavingThrow(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get an integer between 0 and 100 (inclusive) that represents how oSource
     * feels about oTarget.
     * -> 0-10 means oSource is hostile to oTarget
     * -> 11-89 means oSource is neutral to oTarget
     * -> 90-100 means oSource is friendly to oTarget
     * * Returns -1 if oSource or oTarget does not identify a valid object
     */
    public native static synchronized int getReputation(NWObject oSource, NWObject oTarget) throws NWNotInContextException;

    /**
     * returns the template used to create this object (if appropriate)
     * * returns an empty string when no template found
     */
    public native static synchronized String getResRef(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the creature that is currently sitting on the specified object.
     * @param oChair
     * * Returns OBJECT_INVALID if oChair is not a valid placeable.
     */
    public native static synchronized NWObject getSittingCreature(NWObject oChair) throws NWNotInContextException;

    /**
     * Get the number of ranks that oTarget has in nSkill.
     * @param nSkill SKILL_*
     * @param oTarget
     * @param bBaseSkillRank if set to true returns the number of base skill ranks the target
     * has (i.e. not including any bonuses from ability scores, feats, etc).
     * * Returns -1 if oTarget doesn't have nSkill.
     * * Returns 0 if nSkill is untrained.
     */
    public native static synchronized int getSkillRank(int nSkill, NWObject oTarget, boolean bBaseSkillRank) throws NWNotInContextException;

    /**
     * Gets the skybox that is currently displayed in the specified area.
     * Returns:
     * SKYBOX_* constant
     * If no valid area (or object) is specified, it uses the area of caller.
     * If an object other than an area is specified, will use the area that the object is currently in.
     */
    public native static synchronized int getSkyBox(NWObject oArea) throws NWNotInContextException;

    /**
     * Use this in a spell script to get the item used to cast the spell.
     */
    public native static synchronized NWObject getSpellCastItem() throws NWNotInContextException;

    /**
     * This is for use in a Spell script, it gets the ID of the spell that is being
     * cast (SPELL_*).
     */
    public native static synchronized int getSpellId() throws NWNotInContextException;

    /**
     * Returns the spell resistance of the specified creature.
     * @param Returns 0 if the creature has no spell resistance or an invalid
     * creature is passed in.
     */
    public native static synchronized int getSpellResistance(NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the DC to save against for a spell (10 + spell level + relevant ability
     * bonus).  This can be called by a creature or by an Area of Effect object.
     */
    public native static synchronized int getSpellSaveDC() throws NWNotInContextException;

    /**
     * Get the location of the caller's last spell target.
     */
    public native static synchronized NWLocation getSpellTargetLocation() throws NWNotInContextException;

    /**
     * Get the object at which the caller last cast a spell
     * * Return value on error: OBJECT_INVALID
     */
    public native static synchronized NWObject getSpellTargetObject() throws NWNotInContextException;

    /**
     * Find out how nStandardFaction feels about oCreature.
     * @param nStandardFaction STANDARD_FACTION_*
     * @param oCreature
     * Returns -1 on an error.
     * Returns 0-100 based on the standing of oCreature within the faction nStandardFaction.
     * 0-10   :  Hostile.
     * 11-89  :  Neutral.
     * 90-100 :  Friendly.
     */
    public native static synchronized int getStandardFactionReputation(int nStandardFaction, NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the starting location of the module.
     */
    public native static synchronized NWLocation getStartingLocation() throws NWNotInContextException;

    /**
     * Returns the stealth mode of the specified creature.
     * @param oCreature
     * * Returns a constant STEALTH_MODE_*
     */
    public native static synchronized int getStealthMode(NWObject oCreature) throws NWNotInContextException;

    /**
     * returns TRUE if the item is stolen
     */
    public native static synchronized boolean getStolenFlag(NWObject oStolen) throws NWNotInContextException;

    /**
     * Returns the amount of gold a store currently has. -1 indicates it is not using gold.
     * -2 indicates the store could not be located.
     */
    public native static synchronized int getStoreGold(NWObject oidStore) throws NWNotInContextException;

    /**
     * Gets the amount a store charges for identifying an item. Default is 100. -1 means
     * the store will not identify items.
     * -2 indicates the store could not be located.
     */
    public native static synchronized int getStoreIdentifyCost(NWObject oidStore) throws NWNotInContextException;

    /**
     * Gets the maximum amount a store will pay for any item. -1 means price unlimited.
     * -2 indicates the store could not be located.
     */
    public native static synchronized int getStoreMaxBuyPrice(NWObject oidStore) throws NWNotInContextException;

    /**
     * Get the duration (in seconds) of the sound attached to nStrRef
     * * Returns 0.0f if no duration is stored or if no sound is attached
     */
    public native static synchronized float getStrRefSoundDuration(int nStrRef) throws NWNotInContextException;

    /**
     * Get a string from the talk table using nStrRef.
     */
    public native static synchronized String getStringByStrRef(int nStrRef, int nGender) throws NWNotInContextException;

    /**
     * Get nCounter characters from the left end of sString
     * * Return value on error: ""
     */
    public native static synchronized String getStringLeft(String sString, int nCount) throws NWNotInContextException;

    /**
     * Get the length of sString
     * * Return value on error: -1
     */
    public native static synchronized int getStringLength(String sString) throws NWNotInContextException;

    /**
     * Convert sString into lower case
     * * Return value on error: ""
     */
    public native static synchronized String getStringLowerCase(String sString) throws NWNotInContextException;

    /**
     * Get nCount characters from the right end of sString
     * * Return value on error: ""
     */
    public native static synchronized String getStringRight(String sString, int nCount) throws NWNotInContextException;

    /**
     * Convert sString into upper case
     * * Return value on error: ""
     */
    public native static synchronized String getStringUpperCase(String sString) throws NWNotInContextException;

    /**
     * Get the name of oCreature's sub race.
     * * Returns "" if oCreature is invalid (or if sub race is blank for oCreature).
     */
    public native static synchronized String getSubRace(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get nCount characters from sString, starting at nStart
     * * Return value on error: ""
     */
    public native static synchronized String getSubString(String sString, int nStart, int nCount) throws NWNotInContextException;

    /**
     * Get the Tag of oObject
     * * Return value if oObject is not a valid object: ""
     */
    public native static synchronized String getTag(NWObject oObject) throws NWNotInContextException;

    /**
     * Get the color (TILE_MAIN_LIGHT_COLOR_*) for the main light 1 of the tile at
     * lTile.
     * @param lTile the vector part of this is the tile grid (x,y) coordinate of the tile.
     */
    public native static synchronized int getTileMainLight1Color(NWLocation lTile) throws NWNotInContextException;

    /**
     * Get the color (TILE_MAIN_LIGHT_COLOR_*) for the main light 2 of the tile at
     * lTile.
     * @param lTile the vector part of this is the tile grid (x,y) coordinate of the
     * tile.
     */
    public native static synchronized int getTileMainLight2Color(NWLocation lTile) throws NWNotInContextException;

    /**
     * Get the color (TILE_SOURCE_LIGHT_COLOR_*) for the source light 1 of the tile
     * at lTile.
     * @param lTile the vector part of this is the tile grid (x,y) coordinate of the
     * tile.
     */
    public native static synchronized int getTileSourceLight1Color(NWLocation lTile) throws NWNotInContextException;

    /**
     * Get the color (TILE_SOURCE_LIGHT_COLOR_*) for the source light 2 of the tile
     * at lTile.
     * @param lTile the vector part of this is the tile grid (x,y) coordinate of the
     * tile.
     */
    public native static synchronized int getTileSourceLight2Color(NWLocation lTile) throws NWNotInContextException;

    /**
     * returns the resref (TILESET_RESREF_*) of the tileset used to create area oArea.
     * TILESET_RESREF_BEHOLDER_CAVES
     * TILESET_RESREF_CASTLE_INTERIOR
     * TILESET_RESREF_CITY_EXTERIOR
     * TILESET_RESREF_CITY_INTERIOR
     * TILESET_RESREF_CRYPT
     * TILESET_RESREF_DESERT
     * TILESET_RESREF_DROW_INTERIOR
     * TILESET_RESREF_DUNGEON
     * TILESET_RESREF_FOREST
     * TILESET_RESREF_FROZEN_WASTES
     * TILESET_RESREF_ILLITHID_INTERIOR
     * TILESET_RESREF_MICROSET
     * TILESET_RESREF_MINES_AND_CAVERNS
     * TILESET_RESREF_RUINS
     * TILESET_RESREF_RURAL
     * TILESET_RESREF_RURAL_WINTER
     * TILESET_RESREF_SEWERS
     * TILESET_RESREF_UNDERDARK
     * * returns an empty string on an error.
     */
    public native static synchronized String getTilesetResRef(NWObject oArea) throws NWNotInContextException;

    /**
     * Get the current hour.
     */
    public native static synchronized int getTimeHour() throws NWNotInContextException;

    /**
     * Get the current millisecond
     */
    public native static synchronized int getTimeMillisecond() throws NWNotInContextException;

    /**
     * Get the current minute
     */
    public native static synchronized int getTimeMinute() throws NWNotInContextException;

    /**
     * Get the current second
     */
    public native static synchronized int getTimeSecond() throws NWNotInContextException;

    /**
     * Get the total amount of damage that has been dealt to the caller.
     */
    public native static synchronized int getTotalDamageDealt() throws NWNotInContextException;

    /**
     * Get the destination (a waypoint or a door) for a trigger or a door.
     * * Returns OBJECT_INVALID if oTransition is not a valid trigger or door.
     */
    public native static synchronized NWObject getTransitionTarget(NWObject oTransition) throws NWNotInContextException;

    /**
     * @param oTrapObject a placeable, door or trigger
     * * Returns TRUE if oTrapObject is active
     */
    public native static synchronized boolean getTrapActive(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * Get the trap base type (TRAP_BASE_TYPE_*) of oTrapObject.
     * @param oTrapObject a placeable, door or trigger
     */
    public native static synchronized int getTrapBaseType(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * Get the creator of oTrapObject, the creature that set the trap.
     * @param oTrapObject a placeable, door or trigger
     * * Returns OBJECT_INVALID if oTrapObject was created in the toolset.
     */
    public native static synchronized NWObject getTrapCreator(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * Get the DC for detecting oTrapObject.
     * @param oTrapObject a placeable, door or trigger
     */
    public native static synchronized int getTrapDetectDC(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * @param oTrapObject a placeable, door or trigger
     * * Returns TRUE if oTrapObject is detectable.
     */
    public native static synchronized boolean getTrapDetectable(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * @param oTrapObject a placeable, door or trigger
     * @param oCreature
     * * Returns TRUE if oCreature has detected oTrapObject
     */
    public native static synchronized boolean getTrapDetectedBy(NWObject oTrapObject, NWObject oCreature) throws NWNotInContextException;

    /**
     * Get the DC for disarming oTrapObject.
     * @param oTrapObject a placeable, door or trigger
     */
    public native static synchronized int getTrapDisarmDC(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * @param oTrapObject a placeable, door or trigger
     * * Returns TRUE if oTrapObject is disarmable.
     */
    public native static synchronized boolean getTrapDisarmable(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * @param oTrapObject a placeable, door or trigger
     * * Returns TRUE if oTrapObject has been flagged as visible to all creatures.
     */
    public native static synchronized boolean getTrapFlagged(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * Get the tag of the key that will disarm oTrapObject.
     * @param oTrapObject a placeable, door or trigger
     */
    public native static synchronized String getTrapKeyTag(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * @param oTrapObject a placeable, door or trigger
     * * Returns TRUE if oTrapObject is one-shot (i.e. it does not reset itself
     * after firing.
     */
    public native static synchronized boolean getTrapOneShot(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * @param oTrapObject a placeable, door or trigger
     * * Returns TRUE if oTrapObject can be recovered.
     */
    public native static synchronized boolean getTrapRecoverable(NWObject oTrapObject) throws NWNotInContextException;

    /**
     * Get the number of Hitdice worth of Turn Resistance that oUndead may have.
     * This will only work on undead creatures.
     */
    public native static synchronized int getTurnResistanceHD(NWObject oUndead) throws NWNotInContextException;

    /**
     * returns TRUE if the placeable object is usable
     */
    public native static synchronized boolean getUseableFlag(NWObject oObject) throws NWNotInContextException;

    /**
     * This is for use in a user-defined script, it gets the event number.
     */
    public native static synchronized int getUserDefinedEventNumber() throws NWNotInContextException;

    /**
     * Get the first waypoint with the specified tag.
     * * Returns OBJECT_INVALID if the waypoint cannot be found.
     */
    public native static synchronized NWObject getWaypointByTag(String sWaypointTag) throws NWNotInContextException;

    /**
     * * Returns TRUE if oItem is a ranged weapon.
     */
    public native static synchronized boolean getWeaponRanged(NWObject oItem) throws NWNotInContextException;

    /**
     * Gets the current weather conditions for the area oArea.
     * Returns: WEATHER_CLEAR, WEATHER_RAIN, WEATHER_SNOW, WEATHER_INVALID
     * Note: If called on an Interior area, this will always return WEATHER_CLEAR.
     */
    public native static synchronized int getWeather(NWObject oArea) throws NWNotInContextException;

    /**
     * Gets the weight of an item, or the total carried weight of a creature in tenths
     * of pounds (as per the baseitems.2da).
     * @param oTarget the item or creature for which the weight is needed
     */
    public native static synchronized int getWeight(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get oTarget's base will saving throw value (this will only work for creatures,
     * doors, and placeables).
     * * Returns 0 if oTarget is invalid.
     */
    public native static synchronized int getWillSavingThrow(NWObject oTarget) throws NWNotInContextException;

    /**
     * Get oCreature's experience.
     */
    public native static synchronized int getXP(NWObject oCreature) throws NWNotInContextException;

    /**
     * Give nGP gold to oCreature.
     */
    public native static synchronized void giveGoldToCreature(NWObject oCreature, int nGP) throws NWNotInContextException;

    /**
     * Gives nXpAmount to oCreature.
     */
    public native static synchronized void giveXPToCreature(NWObject oCreature, int nXpAmount) throws NWNotInContextException;

    /**
     * Convert nHours into a number of seconds
     * The result will depend on how many minutes there are per hour (game-time)
     */
    public native static synchronized float hoursToSeconds(int nHours) throws NWNotInContextException;

    /**
     * Increment the remaining uses per day for this creature by one.
     * Total number of feats per day can not exceed the maximum.
     * @param oCreature creature to modify
     * @param nFeat constant FEAT_*
     */
    public native static synchronized void incrementRemainingFeatUses(NWObject oCreature, int nFeat) throws NWNotInContextException;

    /**
     * Insert sString into sDestination at nPosition
     * * Return value on error: ""
     */
    public native static synchronized String insertString(String sDestination, String sString, int nPosition) throws NWNotInContextException;

    /**
     * Convert nInteger into a floating point number.
     */
    public native static synchronized float intToFloat(int nInteger) throws NWNotInContextException;

    /**
     * Convert nInteger to hex, returning the hex value as a string.
     * * Return value has the format "0x????????" where each ? will be a hex digit
     * (8 digits in total).
     */
    public native static synchronized String intToHexString(int nInteger) throws NWNotInContextException;

    /**
     * Convert nInteger into a string.
     * * Return value on error: ""
     */
    public native static synchronized String intToString(int nInteger) throws NWNotInContextException;

    /**
     * Determine whether oObject is in conversation.
     */
    public native static synchronized boolean isInConversation(NWObject oObject) throws NWNotInContextException;

    /**
     * Returns Item property AC bonus.  You need to specify the bonus.
     * The bonus should be a positive integer between 1 and 20. The modifier
     * type depends on the item it is being applied to.
     */
    public native static synchronized NWItemProperty itemPropertyACBonus(int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property AC bonus vs. alignment group.  An example of
     * an alignment group is Chaotic, or Good.  You need to specify the
     * alignment group constant(IP_CONST_ALIGNMENTGROUP_*) and the AC bonus.
     * The AC bonus should be an integer between 1 and 20.  The modifier
     * type depends on the item it is being applied to.
     */
    public native static synchronized NWItemProperty itemPropertyACBonusVsAlign(int nIPAlignGroup, int nACBonus) throws NWNotInContextException;

    /**
     * Returns Item property AC bonus vs. Damage type (ie. piercing).  You
     * need to specify the damage type constant(IP_CONST_DAMAGETYPE_*) and the
     * AC bonus.  The AC bonus should be an integer between 1 and 20.  The
     * modifier type depends on the item it is being applied to.
     * NOTE: Only the first 3 damage types may be used here, the 3 basic
     * physical types.
     */
    public native static synchronized NWItemProperty itemPropertyACBonusVsDmgType(int nIPDamageType, int nACBonus) throws NWNotInContextException;

    /**
     * Returns Item property AC bonus vs. Racial group.  You need to specify
     * the racial group constant(IP_CONST_RACIALTYPE_*) and the AC bonus.  The AC
     * bonus should be an integer between 1 and 20.  The modifier type depends
     * on the item it is being applied to.
     */
    public native static synchronized NWItemProperty itemPropertyACBonusVsRace(int nIPRacialType, int nACBonus) throws NWNotInContextException;

    /**
     * Returns Item property AC bonus vs. Specific alignment.  You need to
     * specify the specific alignment constant(IP_CONST_ALIGNMENT_*) and the AC
     * bonus.  The AC bonus should be an integer between 1 and 20.  The
     * modifier type depends on the item it is being applied to.
     */
    public native static synchronized NWItemProperty itemPropertyACBonusVsSAlign(int nAlignment, int nACBonus) throws NWNotInContextException;

    /**
     * Returns Item property ability bonus.  You need to specify an
     * ability constant(IP_CONST_ABILITY_*) and the bonus.  The bonus should
     * be a positive integer between 1 and 12.
     */
    public native static synchronized NWItemProperty itemPropertyAbilityBonus(int nAbility, int nBonus) throws NWNotInContextException;

    /**
     * Returns a generic Additional Item property. You need to specify the Additional property.
     * @param nProperty The item property to create (see iprp_addcost.2da).
     * IP_CONST_ADDITIONAL_*
     * Note: The additional property only affects the cost of the item if you modify the cost in the iprp_addcost.2da.
     */
    public native static synchronized NWItemProperty itemPropertyAdditional(int nAdditionalProperty) throws NWNotInContextException;

    /**
     * Creates an item property that offsets the effect on arcane spell failure
     * that a particular item has. Parameters come from the ITEM_PROP_ASF_* group.
     */
    public native static synchronized NWItemProperty itemPropertyArcaneSpellFailure(int nModLevel) throws NWNotInContextException;

    /**
     * Returns Item property Attack bonus.  You must specify an attack bonus.  The bonus
     * must be an integer between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyAttackBonus(int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property Attack bonus vs. alignment group.  You must specify the
     * alignment group constant(IP_CONST_ALIGNMENTGROUP_*) and the attack bonus.  The
     * bonus must be an integer between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyAttackBonusVsAlign(int nIPAlignGroup, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property attack bonus vs. racial group.  You must specify the
     * racial group constant(IP_CONST_RACIALTYPE_*) and the attack bonus.  The bonus
     * must be an integer between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyAttackBonusVsRace(int nIPRacialType, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property attack bonus vs. a specific alignment.  You must specify
     * the alignment you want the bonus to work against(IP_CONST_ALIGNMENT_*) and the
     * attack bonus.  The bonus must be an integer between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyAttackBonusVsSAlign(int nAlignment, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property attack penalty.  You must specify the attack penalty.
     * The penalty must be a POSITIVE integer between 1 and 5 (ie. 1 = -1).
     */
    public native static synchronized NWItemProperty itemPropertyAttackPenalty(int nPenalty) throws NWNotInContextException;

    /**
     * Returns Item property Bonus Feat.  You need to specify the the feat
     * constant(IP_CONST_FEAT_*).
     */
    public native static synchronized NWItemProperty itemPropertyBonusFeat(int nFeat) throws NWNotInContextException;

    /**
     * Returns Item property Bonus level spell (Bonus spell of level).  You must
     * specify the class constant(IP_CONST_CLASS_*) of the bonus spell(MUST BE a
     * spell casting class) and the level of the bonus spell.  The level of the
     * bonus spell should be an integer between 0 and 9.
     */
    public native static synchronized NWItemProperty itemPropertyBonusLevelSpell(int nClass, int nSpellLevel) throws NWNotInContextException;

    /**
     * Returns Item property saving throw bonus to the base type (ie. will, reflex,
     * fortitude).  You must specify the base type constant(IP_CONST_SAVEBASETYPE_*)
     * to which the user gets the bonus and the bonus that he/she will get.  The
     * bonus must be an integer between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyBonusSavingThrow(int nBaseSaveType, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property saving throw bonus vs. a specific effect or damage type.
     * You must specify the save type constant(IP_CONST_SAVEVS_*) that the bonus is
     * applied to and the bonus that is be applied.  The bonus must be an integer
     * between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyBonusSavingThrowVsX(int nBonusType, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property bonus spell resistance.  You must specify the bonus spell
     * resistance constant(IP_CONST_SPELLRESISTANCEBONUS_*).
     */
    public native static synchronized NWItemProperty itemPropertyBonusSpellResistance(int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property Cast spell.  You must specify the spell constant
     * (IP_CONST_CASTSPELL_*) and the number of uses constant(IP_CONST_CASTSPELL_NUMUSES_*).
     * NOTE: The number after the name of the spell in the constant is the level
     * at which the spell will be cast.  Sometimes there are multiple copies
     * of the same spell but they each are cast at a different level.  The higher
     * the level, the more cost will be added to the item.
     * NOTE: The list of spells that can be applied to an item will depend on the
     * item type.  For instance there are spells that can be applied to a wand
     * that cannot be applied to a potion.  Below is a list of the types and the
     * spells that are allowed to be placed on them.  If you try to put a cast
     * spell effect on an item that is not allowed to have that effect it will
     * not work.
     * NOTE: Even if spells have multiple versions of different levels they are only
     * listed below once.
     * 
     * WANDS:
     * Acid_Splash
     * Activate_Item
     * Aid
     * Amplify
     * Animate_Dead
     * AuraOfGlory
     * BalagarnsIronHorn
     * Bane
     * Banishment
     * Barkskin
     * Bestow_Curse
     * Bigbys_Clenched_Fist
     * Bigbys_Crushing_Hand
     * Bigbys_Forceful_Hand
     * Bigbys_Grasping_Hand
     * Bigbys_Interposing_Hand
     * Bless
     * Bless_Weapon
     * Blindness/Deafness
     * Blood_Frenzy
     * Bombardment
     * Bulls_Strength
     * Burning_Hands
     * Call_Lightning
     * Camoflage
     * Cats_Grace
     * Charm_Monster
     * Charm_Person
     * Charm_Person_or_Animal
     * Clairaudience/Clairvoyance
     * Clarity
     * Color_Spray
     * Confusion
     * Continual_Flame
     * Cure_Critical_Wounds
     * Cure_Light_Wounds
     * Cure_Minor_Wounds
     * Cure_Moderate_Wounds
     * Cure_Serious_Wounds
     * Darkness
     * Darkvision
     * Daze
     * Death_Ward
     * Dirge
     * Dismissal
     * Dispel_Magic
     * Displacement
     * Divine_Favor
     * Divine_Might
     * Divine_Power
     * Divine_Shield
     * Dominate_Animal
     * Dominate_Person
     * Doom
     * Dragon_Breath_Acid
     * Dragon_Breath_Cold
     * Dragon_Breath_Fear
     * Dragon_Breath_Fire
     * Dragon_Breath_Gas
     * Dragon_Breath_Lightning
     * Dragon_Breath_Paralyze
     * Dragon_Breath_Sleep
     * Dragon_Breath_Slow
     * Dragon_Breath_Weaken
     * Drown
     * Eagle_Spledor
     * Earthquake
     * Electric_Jolt
     * Elemental_Shield
     * Endurance
     * Endure_Elements
     * Enervation
     * Entangle
     * Entropic_Shield
     * Etherealness
     * Expeditious_Retreat
     * Fear
     * Find_Traps
     * Fireball
     * Firebrand
     * Flame_Arrow
     * Flame_Lash
     * Flame_Strike
     * Flare
     * Foxs_Cunning
     * Freedom_of_Movement
     * Ghostly_Visage
     * Ghoul_Touch
     * Grease
     * Greater_Magic_Fang
     * Greater_Magic_Weapon
     * Grenade_Acid
     * Grenade_Caltrops
     * Grenade_Chicken
     * Grenade_Choking
     * Grenade_Fire
     * Grenade_Holy
     * Grenade_Tangle
     * Grenade_Thunderstone
     * Gust_of_wind
     * Hammer_of_the_Gods
     * Haste
     * Hold_Animal
     * Hold_Monster
     * Hold_Person
     * Ice_Storm
     * Identify
     * Improved_Invisibility
     * Inferno
     * Inflict_Critical_Wounds
     * Inflict_Light_Wounds
     * Inflict_Minor_Wounds
     * Inflict_Moderate_Wounds
     * Inflict_Serious_Wounds
     * Invisibility
     * Invisibility_Purge
     * Invisibility_Sphere
     * Isaacs_Greater_Missile_Storm
     * Isaacs_Lesser_Missile_Storm
     * Knock
     * Lesser_Dispel
     * Lesser_Restoration
     * Lesser_Spell_Breach
     * Light
     * Lightning_Bolt
     * Mage_Armor
     * Magic_Circle_against_Alignment
     * Magic_Fang
     * Magic_Missile
     * Manipulate_Portal_Stone
     * Mass_Camoflage
     * Melfs_Acid_Arrow
     * Meteor_Swarm
     * Mind_Blank
     * Mind_Fog
     * Negative_Energy_Burst
     * Negative_Energy_Protection
     * Negative_Energy_Ray
     * Neutralize_Poison
     * One_With_The_Land
     * Owls_Insight
     * Owls_Wisdom
     * Phantasmal_Killer
     * Planar_Ally
     * Poison
     * Polymorph_Self
     * Prayer
     * Protection_from_Alignment
     * Protection_From_Elements
     * Quillfire
     * Ray_of_Enfeeblement
     * Ray_of_Frost
     * Remove_Blindness/Deafness
     * Remove_Curse
     * Remove_Disease
     * Remove_Fear
     * Remove_Paralysis
     * Resist_Elements
     * Resistance
     * Restoration
     * Sanctuary
     * Scare
     * Searing_Light
     * See_Invisibility
     * Shadow_Conjuration
     * Shield
     * Shield_of_Faith
     * Silence
     * Sleep
     * Slow
     * Sound_Burst
     * Spike_Growth
     * Stinking_Cloud
     * Stoneskin
     * Summon_Creature_I
     * Summon_Creature_I
     * Summon_Creature_II
     * Summon_Creature_III
     * Summon_Creature_IV
     * Sunburst
     * Tashas_Hideous_Laughter
     * True_Strike
     * Undeaths_Eternal_Foe
     * Unique_Power
     * Unique_Power_Self_Only
     * Vampiric_Touch
     * Virtue
     * Wall_of_Fire
     * Web
     * Wounding_Whispers
     * 
     * POTIONS:
     * Activate_Item
     * Aid
     * Amplify
     * AuraOfGlory
     * Bane
     * Barkskin
     * Barkskin
     * Barkskin
     * Bless
     * Bless_Weapon
     * Bless_Weapon
     * Blood_Frenzy
     * Bulls_Strength
     * Bulls_Strength
     * Bulls_Strength
     * Camoflage
     * Cats_Grace
     * Cats_Grace
     * Cats_Grace
     * Clairaudience/Clairvoyance
     * Clairaudience/Clairvoyance
     * Clairaudience/Clairvoyance
     * Clarity
     * Continual_Flame
     * Cure_Critical_Wounds
     * Cure_Critical_Wounds
     * Cure_Critical_Wounds
     * Cure_Light_Wounds
     * Cure_Light_Wounds
     * Cure_Minor_Wounds
     * Cure_Moderate_Wounds
     * Cure_Moderate_Wounds
     * Cure_Moderate_Wounds
     * Cure_Serious_Wounds
     * Cure_Serious_Wounds
     * Cure_Serious_Wounds
     * Darkness
     * Darkvision
     * Darkvision
     * Death_Ward
     * Dispel_Magic
     * Dispel_Magic
     * Displacement
     * Divine_Favor
     * Divine_Might
     * Divine_Power
     * Divine_Shield
     * Dragon_Breath_Acid
     * Dragon_Breath_Cold
     * Dragon_Breath_Fear
     * Dragon_Breath_Fire
     * Dragon_Breath_Gas
     * Dragon_Breath_Lightning
     * Dragon_Breath_Paralyze
     * Dragon_Breath_Sleep
     * Dragon_Breath_Slow
     * Dragon_Breath_Weaken
     * Eagle_Spledor
     * Eagle_Spledor
     * Eagle_Spledor
     * Elemental_Shield
     * Elemental_Shield
     * Endurance
     * Endurance
     * Endurance
     * Endure_Elements
     * Entropic_Shield
     * Ethereal_Visage
     * Ethereal_Visage
     * Etherealness
     * Expeditious_Retreat
     * Find_Traps
     * Foxs_Cunning
     * Foxs_Cunning
     * Foxs_Cunning
     * Freedom_of_Movement
     * Ghostly_Visage
     * Ghostly_Visage
     * Ghostly_Visage
     * Globe_of_Invulnerability
     * Greater_Bulls_Strength
     * Greater_Cats_Grace
     * Greater_Dispelling
     * Greater_Dispelling
     * Greater_Eagles_Splendor
     * Greater_Endurance
     * Greater_Foxs_Cunning
     * Greater_Magic_Weapon
     * Greater_Owls_Wisdom
     * Greater_Restoration
     * Greater_Spell_Mantle
     * Greater_Stoneskin
     * Grenade_Acid
     * Grenade_Caltrops
     * Grenade_Chicken
     * Grenade_Choking
     * Grenade_Fire
     * Grenade_Holy
     * Grenade_Tangle
     * Grenade_Thunderstone
     * Haste
     * Haste
     * Heal
     * Hold_Animal
     * Hold_Monster
     * Hold_Person
     * Identify
     * Invisibility
     * Lesser_Dispel
     * Lesser_Dispel
     * Lesser_Mind_Blank
     * Lesser_Restoration
     * Lesser_Spell_Mantle
     * Light
     * Light
     * Mage_Armor
     * Manipulate_Portal_Stone
     * Mass_Camoflage
     * Mind_Blank
     * Minor_Globe_of_Invulnerability
     * Minor_Globe_of_Invulnerability
     * Mordenkainens_Disjunction
     * Negative_Energy_Protection
     * Negative_Energy_Protection
     * Negative_Energy_Protection
     * Neutralize_Poison
     * One_With_The_Land
     * Owls_Insight
     * Owls_Wisdom
     * Owls_Wisdom
     * Owls_Wisdom
     * Polymorph_Self
     * Prayer
     * Premonition
     * Protection_From_Elements
     * Protection_From_Elements
     * Protection_from_Spells
     * Protection_from_Spells
     * Raise_Dead
     * Remove_Blindness/Deafness
     * Remove_Curse
     * Remove_Disease
     * Remove_Fear
     * Remove_Paralysis
     * Resist_Elements
     * Resist_Elements
     * Resistance
     * Resistance
     * Restoration
     * Resurrection
     * Rogues_Cunning
     * See_Invisibility
     * Shadow_Shield
     * Shapechange
     * Shield
     * Shield_of_Faith
     * Special_Alcohol_Beer
     * Special_Alcohol_Spirits
     * Special_Alcohol_Wine
     * Special_Herb_Belladonna
     * Special_Herb_Garlic
     * Spell_Mantle
     * Spell_Resistance
     * Spell_Resistance
     * Stoneskin
     * Tensers_Transformation
     * True_Seeing
     * True_Strike
     * Unique_Power
     * Unique_Power_Self_Only
     * Virtue
     * 
     * GENERAL USE (ie. everything else):
     * Just about every spell is useable by all the general use items so instead we
     * will only list the ones that are not allowed:
     * Special_Alcohol_Beer
     * Special_Alcohol_Spirits
     * Special_Alcohol_Wine
     * 
     */
    public native static synchronized NWItemProperty itemPropertyCastSpell(int nSpell, int nNumUses) throws NWNotInContextException;

    /**
     * Returns Item property container reduced weight.  This is used for special
     * containers that reduce the weight of the objects inside them.  You must
     * specify the container weight reduction type constant(IP_CONST_CONTAINERWEIGHTRED_*).
     */
    public native static synchronized NWItemProperty itemPropertyContainerReducedWeight(int nContainerType) throws NWNotInContextException;

    /**
     * Returns Item property damage bonus.  You must specify the damage type constant
     * (IP_CONST_DAMAGETYPE_*) and the amount of damage constant(IP_CONST_DAMAGEBONUS_*).
     * NOTE: not all the damage types will work, use only the following: Acid, Bludgeoning,
     * Cold, Electrical, Fire, Piercing, Slashing, Sonic.
     */
    public native static synchronized NWItemProperty itemPropertyDamageBonus(int nIPDamageType, int nDamage) throws NWNotInContextException;

    /**
     * Returns Item property damage bonus vs. Alignment groups.  You must specify the
     * alignment group constant(IP_CONST_ALIGNMENTGROUP_*) and the damage type constant
     * (IP_CONST_DAMAGETYPE_*) and the amount of damage constant(IP_CONST_DAMAGEBONUS_*).
     * NOTE: not all the damage types will work, use only the following: Acid, Bludgeoning,
     * Cold, Electrical, Fire, Piercing, Slashing, Sonic.
     */
    public native static synchronized NWItemProperty itemPropertyDamageBonusVsAlign(int nIPAlignGroup, int nIPDamageType, int nDamage) throws NWNotInContextException;

    /**
     * Returns Item property damage bonus vs. specific race.  You must specify the
     * racial group constant(IP_CONST_RACIALTYPE_*) and the damage type constant
     * (IP_CONST_DAMAGETYPE_*) and the amount of damage constant(IP_CONST_DAMAGEBONUS_*).
     * NOTE: not all the damage types will work, use only the following: Acid, Bludgeoning,
     * Cold, Electrical, Fire, Piercing, Slashing, Sonic.
     */
    public native static synchronized NWItemProperty itemPropertyDamageBonusVsRace(int nIPRacialType, int nIPDamageType, int nDamage) throws NWNotInContextException;

    /**
     * Returns Item property damage bonus vs. specific alignment.  You must specify the
     * specific alignment constant(IP_CONST_ALIGNMENT_*) and the damage type constant
     * (IP_CONST_DAMAGETYPE_*) and the amount of damage constant(IP_CONST_DAMAGEBONUS_*).
     * NOTE: not all the damage types will work, use only the following: Acid, Bludgeoning,
     * Cold, Electrical, Fire, Piercing, Slashing, Sonic.
     */
    public native static synchronized NWItemProperty itemPropertyDamageBonusVsSAlign(int nAlignment, int nIPDamageType, int nDamage) throws NWNotInContextException;

    /**
     * Returns Item property damage immunity.  You must specify the damage type constant
     * (IP_CONST_DAMAGETYPE_*) that you want to be immune to and the immune bonus percentage
     * constant(IP_CONST_DAMAGEIMMUNITY_*).
     * NOTE: not all the damage types will work, use only the following: Acid, Bludgeoning,
     * Cold, Electrical, Fire, Piercing, Slashing, Sonic.
     */
    public native static synchronized NWItemProperty itemPropertyDamageImmunity(int nIPDamageType, int nImmuneBonus) throws NWNotInContextException;

    /**
     * Returns Item property damage penalty.  You must specify the damage penalty.
     * The damage penalty should be a POSITIVE integer between 1 and 5 (ie. 1 = -1).
     */
    public native static synchronized NWItemProperty itemPropertyDamagePenalty(int nPenalty) throws NWNotInContextException;

    /**
     * Returns Item property damage reduction.  You must specify the enhancment level
     * (IP_CONST_DAMAGEREDUCTION_*) that is required to get past the damage reduction
     * and the amount of HP of damage constant(IP_CONST_DAMAGESOAK_*) will be soaked
     * up if your weapon is not of high enough enhancement.
     */
    public native static synchronized NWItemProperty itemPropertyDamageReduction(int nEnhancement, int nHPSoak) throws NWNotInContextException;

    /**
     * Returns Item property damage resistance.  You must specify the damage type
     * constant(IP_CONST_DAMAGETYPE_*) and the amount of HP of damage constant
     * (IP_CONST_DAMAGERESIST_*) that will be resisted against each round.
     */
    public native static synchronized NWItemProperty itemPropertyDamageResistance(int nIPDamageType, int nHPResist) throws NWNotInContextException;

    /**
     * Returns Item property damage vulnerability.  You must specify the damage type
     * constant(IP_CONST_DAMAGETYPE_*) that you want the user to be extra vulnerable to
     * and the percentage vulnerability constant(IP_CONST_DAMAGEVULNERABILITY_*).
     */
    public native static synchronized NWItemProperty itemPropertyDamageVulnerability(int nIPDamageType, int nVulnerability) throws NWNotInContextException;

    /**
     * Return Item property Darkvision.
     */
    public native static synchronized NWItemProperty itemPropertyDarkvision() throws NWNotInContextException;

    /**
     * Returns Item property decrease Armor Class.  You must specify the armor
     * modifier type constant(IP_CONST_ACMODIFIERTYPE_*) and the armor class penalty.
     * The penalty must be a POSITIVE integer between 1 and 5 (ie. 1 = -1).
     */
    public native static synchronized NWItemProperty itemPropertyDecreaseAC(int nModifierType, int nPenalty) throws NWNotInContextException;

    /**
     * Return Item property decrease ability score.  You must specify the ability
     * constant(IP_CONST_ABILITY_*) and the modifier constant.  The modifier must be
     * a POSITIVE integer between 1 and 10 (ie. 1 = -1).
     */
    public native static synchronized NWItemProperty itemPropertyDecreaseAbility(int nAbility, int nModifier) throws NWNotInContextException;

    /**
     * Returns Item property decrease skill.  You must specify the constant for the
     * skill to be decreased(SKILL_*) and the amount of the penalty.  The penalty
     * must be a POSITIVE integer between 1 and 10 (ie. 1 = -1).
     */
    public native static synchronized NWItemProperty itemPropertyDecreaseSkill(int nSkill, int nPenalty) throws NWNotInContextException;

    /**
     * Returns Item property Enhancement bonus.  You need to specify the
     * enhancement bonus.  The Enhancement bonus should be an integer between
     * 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyEnhancementBonus(int nEnhancementBonus) throws NWNotInContextException;

    /**
     * Returns Item property Enhancement bonus vs. an Alignment group.  You
     * need to specify the alignment group constant(IP_CONST_ALIGNMENTGROUP_*)
     * and the enhancement bonus.  The Enhancement bonus should be an integer
     * between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyEnhancementBonusVsAlign(int nIPAlignGroup, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property Enhancement bonus vs. Racial group.  You need
     * to specify the racial group constant(IP_CONST_RACIALTYPE_*) and the
     * enhancement bonus.  The enhancement bonus should be an integer between
     * 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyEnhancementBonusVsRace(int nIPRacialType, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property Enhancement bonus vs. a specific alignment.  You
     * need to specify the alignment constant(IP_CONST_ALIGNMENT_*) and the
     * enhancement bonus.  The enhancement bonus should be an integer between
     * 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyEnhancementBonusVsSAlign(int nAlignment, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property Enhancment penalty.  You need to specify the
     * enhancement penalty.  The enhancement penalty should be a POSITIVE
     * integer between 1 and 5 (ie. 1 = -1).
     */
    public native static synchronized NWItemProperty itemPropertyEnhancementPenalty(int nPenalty) throws NWNotInContextException;

    /**
     * Returns Item property extra melee damage type.  You must specify the extra
     * melee base damage type that you want applied.  It is a constant(IP_CONST_DAMAGETYPE_*).
     * NOTE: only the first 3 base types (piercing, slashing, & bludgeoning are applicable
     * here.
     * NOTE: It is also only applicable to melee weapons.
     */
    public native static synchronized NWItemProperty itemPropertyExtraMeleeDamageType(int nIPDamageType) throws NWNotInContextException;

    /**
     * Returns Item property extra ranged damage type.  You must specify the extra
     * melee base damage type that you want applied.  It is a constant(IP_CONST_DAMAGETYPE_*).
     * NOTE: only the first 3 base types (piercing, slashing, & bludgeoning are applicable
     * here.
     * NOTE: It is also only applicable to ranged weapons.
     */
    public native static synchronized NWItemProperty itemPropertyExtraRangeDamageType(int nIPDamageType) throws NWNotInContextException;

    /**
     * Returns Item property free action.
     */
    public native static synchronized NWItemProperty itemPropertyFreeAction() throws NWNotInContextException;

    /**
     * Returns Item property haste.
     */
    public native static synchronized NWItemProperty itemPropertyHaste() throws NWNotInContextException;

    /**
     * Returns Item property healers kit.  You must specify the level of the kit.
     * The modifier must be an integer between 1 and 12.
     */
    public native static synchronized NWItemProperty itemPropertyHealersKit(int nModifier) throws NWNotInContextException;

    /**
     * Returns Item property Holy Avenger.
     */
    public native static synchronized NWItemProperty itemPropertyHolyAvenger() throws NWNotInContextException;

    /**
     * Returns Item property immunity to miscellaneous effects.  You must specify the
     * effect to which the user is immune, it is a constant(IP_CONST_IMMUNITYMISC_*).
     */
    public native static synchronized NWItemProperty itemPropertyImmunityMisc(int nIPMiscImmunityType) throws NWNotInContextException;

    /**
     * Returns Item property immunity to spell level.  You must specify the level of
     * which that and below the user will be immune.  The level must be an integer
     * between 1 and 9.  By putting in a 3 it will mean the user is immune to all
     * 3rd level and lower spells.
     */
    public native static synchronized NWItemProperty itemPropertyImmunityToSpellLevel(int nLevel) throws NWNotInContextException;

    /**
     * Returns Item property improved evasion.
     */
    public native static synchronized NWItemProperty itemPropertyImprovedEvasion() throws NWNotInContextException;

    /**
     * Returns Item property keen.  This means a critical threat range of 19-20 on a
     * weapon will be increased to 17-20 etc.
     */
    public native static synchronized NWItemProperty itemPropertyKeen() throws NWNotInContextException;

    /**
     * Returns Item property light.  You must specify the intesity constant of the
     * light(IP_CONST_LIGHTBRIGHTNESS_*) and the color constant of the light
     * (IP_CONST_LIGHTCOLOR_*).
     */
    public native static synchronized NWItemProperty itemPropertyLight(int nBrightness, int nColor) throws NWNotInContextException;

    /**
     * Returns Item property limit use by alignment group.  You must specify the
     * alignment group(s) that you want to be able to use this item(IP_CONST_ALIGNMENTGROUP_*).
     */
    public native static synchronized NWItemProperty itemPropertyLimitUseByAlign(int nIPAlignGroup) throws NWNotInContextException;

    /**
     * Returns Item property limit use by class.  You must specify the class(es) who
     * are able to use this item(IP_CONST_CLASS_*).
     */
    public native static synchronized NWItemProperty itemPropertyLimitUseByClass(int nClass) throws NWNotInContextException;

    /**
     * Returns Item property limit use by race.  You must specify the race(s) who are
     * allowed to use this item(IP_CONST_RACIALTYPE_*).
     */
    public native static synchronized NWItemProperty itemPropertyLimitUseByRace(int nIPRacialType) throws NWNotInContextException;

    /**
     * Returns Item property limit use by specific alignment.  You must specify the
     * alignment(s) of those allowed to use the item(IP_CONST_ALIGNMENT_*).
     */
    public native static synchronized NWItemProperty itemPropertyLimitUseBySAlign(int nAlignment) throws NWNotInContextException;

    /**
     * Returns Item property Massive Critical.  You must specify the extra damage
     * constant(IP_CONST_DAMAGEBONUS_*) of the criticals.
     */
    public native static synchronized NWItemProperty itemPropertyMassiveCritical(int nDamage) throws NWNotInContextException;

    /**
     * Returns Item property Material.  You need to specify the Material Type.
     * @param nMasterialType The Material Type should be a positive integer between 0 and 77 (see iprp_matcost.2da).
     * Note: The Material Type property will only affect the cost of the item if you modify the cost in the iprp_matcost.2da.
     */
    public native static synchronized NWItemProperty itemPropertyMaterial(int nMaterialType) throws NWNotInContextException;

    /**
     * Returns Item property Max range strength modification (ie. mighty).  You must
     * specify the maximum modifier for strength that is allowed on a ranged weapon.
     * The modifier must be a positive integer between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyMaxRangeStrengthMod(int nModifier) throws NWNotInContextException;

    /**
     * Returns Item property monster damage.  You must specify the amount of damage
     * the monster's attack will do(IP_CONST_MONSTERDAMAGE_*).
     * NOTE: These can only be applied to monster NATURAL weapons (ie. bite, claw,
     * gore, and slam).  IT WILL NOT WORK ON NORMAL WEAPONS.
     */
    public native static synchronized NWItemProperty itemPropertyMonsterDamage(int nDamage) throws NWNotInContextException;

    /**
     * Returns Item property no damage.  This means the weapon will do no damage in
     * combat.
     */
    public native static synchronized NWItemProperty itemPropertyNoDamage() throws NWNotInContextException;

    /**
     * Creates an item property that (when applied to a weapon item) causes a spell to be cast
     * when a successful strike is made, or (when applied to armor) is struck by an opponent.
     * @param nSpell uses the IP_CONST_ONHIT_CASTSPELL_* constants
     */
    public native static synchronized NWItemProperty itemPropertyOnHitCastSpell(int nSpell, int nLevel) throws NWNotInContextException;

    /**
     * Returns Item property on hit -> do effect property.  You must specify the on
     * hit property constant(IP_CONST_ONHIT_*) and the save DC constant(IP_CONST_ONHIT_SAVEDC_*).
     * Some of the item properties require a special parameter as well.  If the
     * property does not require one you may leave out the last one.  The list of
     * the ones with 3 parameters and what they are are as follows:
     * ABILITYDRAIN      :nSpecial is the ability it is to drain.
     * constant(IP_CONST_ABILITY_*)
     * BLINDNESS         :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * CONFUSION         :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * DAZE              :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * DEAFNESS          :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * DISEASE           :nSpecial is the type of desease that will effect the victim.
     * constant(DISEASE_*)
     * DOOM              :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * FEAR              :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * HOLD              :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * ITEMPOISON        :nSpecial is the type of poison that will effect the victim.
     * constant(IP_CONST_POISON_*)
     * SILENCE           :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * SLAYRACE          :nSpecial is the race that will be slain.
     * constant(IP_CONST_RACIALTYPE_*)
     * SLAYALIGNMENTGROUP:nSpecial is the alignment group that will be slain(ie. chaotic).
     * constant(IP_CONST_ALIGNMENTGROUP_*)
     * SLAYALIGNMENT     :nSpecial is the specific alignment that will be slain.
     * constant(IP_CONST_ALIGNMENT_*)
     * SLEEP             :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * SLOW              :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     * STUN              :nSpecial is the duration/percentage of effecting victim.
     * constant(IP_CONST_ONHIT_DURATION_*)
     */
    public native static synchronized NWItemProperty itemPropertyOnHitProps(int nProperty, int nSaveDC, int nSpecial) throws NWNotInContextException;

    /**
     * Returns Item property Monster on hit apply effect property.  You must specify
     * the property that you want applied on hit.  There are some properties that
     * require an additional special parameter to be specified.  The others that
     * don't require any additional parameter you may just put in the one.  The
     * special cases are as follows:
     * ABILITYDRAIN:nSpecial is the ability to drain.
     * constant(IP_CONST_ABILITY_*)
     * DISEASE     :nSpecial is the disease that you want applied.
     * constant(DISEASE_*)
     * LEVELDRAIN  :nSpecial is the number of levels that you want drained.
     * integer between 1 and 5.
     * POISON      :nSpecial is the type of poison that will effect the victim.
     * constant(IP_CONST_POISON_*)
     * WOUNDING    :nSpecial is the amount of wounding.
     * integer between 1 and 5.
     * NOTE: Any that do not appear in the above list do not require the second
     * parameter.
     * NOTE: These can only be applied to monster NATURAL weapons (ie. bite, claw,
     * gore, and slam).  IT WILL NOT WORK ON NORMAL WEAPONS.
     */
    public native static synchronized NWItemProperty itemPropertyOnMonsterHitProperties(int nProperty, int nSpecial) throws NWNotInContextException;

    /**
     * Returns Item property Quality. You need to specify the Quality.
     * @param nQuality  The Quality of the item property to create (see iprp_qualcost.2da).
     * IP_CONST_QUALITY_*
     * Note: The quality property will only affect the cost of the item if you modify the cost in the iprp_qualcost.2da.
     */
    public native static synchronized NWItemProperty itemPropertyQuality(int nQuality) throws NWNotInContextException;

    /**
     * Returns Item property reduced saving to base type.  You must specify the base
     * type to which the penalty applies (ie. will, reflex, or fortitude) and the penalty
     * to be applied.  The constant for the base type starts with (IP_CONST_SAVEBASETYPE_*).
     * The penalty must be a POSITIVE integer between 1 and 20 (ie. 1 = -1).
     */
    public native static synchronized NWItemProperty itemPropertyReducedSavingThrow(int nBonusType, int nPenalty) throws NWNotInContextException;

    /**
     * Returns Item property reduced saving throw vs. an effect or damage type.  You must
     * specify the constant to which the penalty applies(IP_CONST_SAVEVS_*) and the
     * penalty to be applied.  The penalty must be a POSITIVE integer between 1 and 20
     * (ie. 1 = -1).
     */
    public native static synchronized NWItemProperty itemPropertyReducedSavingThrowVsX(int nBaseSaveType, int nPenalty) throws NWNotInContextException;

    /**
     * Returns Item property regeneration.  You must specify the regeneration amount.
     * The amount must be an integer between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyRegeneration(int nRegenAmount) throws NWNotInContextException;

    /**
     * Returns Item property skill bonus.  You must specify the skill to which the user
     * will get a bonus(SKILL_*) and the amount of the bonus.  The bonus amount must
     * be an integer between 1 and 50.
     */
    public native static synchronized NWItemProperty itemPropertySkillBonus(int nSkill, int nBonus) throws NWNotInContextException;

    /**
     * Returns Item property special walk.  If no parameters are specified it will
     * automatically use the zombie walk.  This will apply the special walk animation
     * to the user.
     */
    public native static synchronized NWItemProperty itemPropertySpecialWalk(int nWalkType) throws NWNotInContextException;

    /**
     * Returns Item property spell immunity vs. spell school.  You must specify the
     * school to which the user will be immune(IP_CONST_SPELLSCHOOL_*).
     */
    public native static synchronized NWItemProperty itemPropertySpellImmunitySchool(int nIPSpellSchool) throws NWNotInContextException;

    /**
     * Returns Item property spell immunity vs. specific spell.  You must specify the
     * spell to which the user will be immune(IP_CONST_IMMUNITYSPELL_*).
     */
    public native static synchronized NWItemProperty itemPropertySpellImmunitySpecific(int nSpell) throws NWNotInContextException;

    /**
     * Returns Item property Thieves tools.  You must specify the modifier you wish
     * the tools to have.  The modifier must be an integer between 1 and 12.
     */
    public native static synchronized NWItemProperty itemPropertyThievesTools(int nModifier) throws NWNotInContextException;

    /**
     * Returns Item property Trap.  You must specify the trap level constant
     * (IP_CONST_TRAPSTRENGTH_*) and the trap type constant(IP_CONST_TRAPTYPE_*).
     */
    public native static synchronized NWItemProperty itemPropertyTrap(int nTrapLevel, int nIPTrapType) throws NWNotInContextException;

    /**
     * Returns Item property true seeing.
     */
    public native static synchronized NWItemProperty itemPropertyTrueSeeing() throws NWNotInContextException;

    /**
     * Returns Item property turn resistance.  You must specify the resistance bonus.
     * The bonus must be an integer between 1 and 50.
     */
    public native static synchronized NWItemProperty itemPropertyTurnResistance(int nModifier) throws NWNotInContextException;

    /**
     * Returns Item property unlimited ammo.  If you leave the parameter field blank
     * it will be just a normal bolt, arrow, or bullet.  However you may specify that
     * you want the ammunition to do special damage (ie. +1d6 Fire, or +1 enhancement
     * bonus).  For this parmeter you use the constants beginning with:
     * (IP_CONST_UNLIMITEDAMMO_*).
     */
    public native static synchronized NWItemProperty itemPropertyUnlimitedAmmo(int nAmmoDamage) throws NWNotInContextException;

    /**
     * Returns Item property vampiric regeneration.  You must specify the amount of
     * regeneration.  The regen amount must be an integer between 1 and 20.
     */
    public native static synchronized NWItemProperty itemPropertyVampiricRegeneration(int nRegenAmount) throws NWNotInContextException;

    /**
     * Creates a visual effect (ITEM_VISUAL_*) that may be applied to
     * melee weapons only.
     */
    public native static synchronized NWItemProperty itemPropertyVisualEffect(int nEffect) throws NWNotInContextException;

    /**
     * Returns Item property weight increase.  You must specify the weight increase
     * constant(IP_CONST_WEIGHTINCREASE_*).
     */
    public native static synchronized NWItemProperty itemPropertyWeightIncrease(int nWeight) throws NWNotInContextException;

    /**
     * Returns Item property weight reduction.  You need to specify the weight
     * reduction constant(IP_CONST_REDUCEDWEIGHT_*).
     */
    public native static synchronized NWItemProperty itemPropertyWeightReduction(int nReduction) throws NWNotInContextException;

    /**
     * Jump to lDestination.  The action is added to the TOP of the action queue.
     */
    public native static synchronized void jumpToLocation(NWLocation lDestination) throws NWNotInContextException;

    /**
     * Jump to oToJumpTo (the action is added to the top of the action queue).
     */
    public native static synchronized void jumpToObject(NWObject oToJumpTo, boolean bWalkStraightLineToPoint) throws NWNotInContextException;

    /**
     * Levels up a creature using default settings.
     * If successfull it returns the level the creature now is, or 0 if it fails.
     * If you want to give them a different level (ie: Give a Fighter a level of Wizard)
     * you can specify that in the nClass.
     * However, if you specify a class to which the creature no package specified,
     * they will use the default package for that class for their levelup choices.
     * (ie: no Barbarian Savage/Wizard Divination combinations)
     * If you turn on bReadyAllSpells, all memorized spells will be ready to cast without resting.
     * if nPackage is PACKAGE_INVALID then it will use the starting package assigned to that class or just the class package
     */
    public native static synchronized int levelUpHenchman(NWObject oCreature, int nClass, boolean bReadyAllSpells, int nPackage) throws NWNotInContextException;

    /**
     * Returns whether or not there is a direct line of sight
     * between the two objects. (Not blocked by any geometry).
     * 
     * PLEASE NOTE: This is an expensive function and may
     * degrade performance if used frequently.
     */
    public native static synchronized int lineOfSightObject(NWObject oSource, NWObject oTarget) throws NWNotInContextException;

    /**
     * Returns whether or not there is a direct line of sight
     * between the two vectors. (Not blocked by any geometry).
     * 
     * This function must be run on a valid object in the area
     * it will not work on the module or area.
     * 
     * PLEASE NOTE: This is an expensive function and may
     * degrade performance if used frequently.
     */
    public native static synchronized int lineOfSightVector(NWVector vSource, NWVector vTarget) throws NWNotInContextException;

    /**
     * Create a location.
     */
    public native static synchronized NWLocation location(NWObject oArea, NWVector vPosition, float fOrientation) throws NWNotInContextException;

    /**
     * Locks the player's camera direction to its current direction,
     * or unlocks the player's camera direction to enable it to move
     * freely again.
     * Stops the player from being able to rotate the camera direction.
     * @param oPlayer A player object.
     * @param bLocked TRUE/FALSE.
     */
    public native static synchronized void lockCameraDirection(NWObject oPlayer, boolean bLocked) throws NWNotInContextException;

    /**
     * Locks the player's camera distance to its current distance setting,
     * or unlocks the player's camera distance.
     * Stops the player from being able to zoom in/out the camera.
     * @param oPlayer A player object.
     * @param bLocked TRUE/FALSE.
     */
    public native static synchronized void lockCameraDistance(NWObject oPlayer, boolean bLocked) throws NWNotInContextException;

    /**
     * Locks the player's camera pitch to its current pitch setting,
     * or unlocks the player's camera pitch.
     * Stops the player from tilting their camera angle.
     * @param oPlayer A player object.
     * @param bLocked TRUE/FALSE.
     */
    public native static synchronized void lockCameraPitch(NWObject oPlayer, boolean bLocked) throws NWNotInContextException;

    /**
     * Set the subtype of eEffect to Magical and return eEffect.
     * (Effects default to magical if the subtype is not set)
     * Magical effects are removed by resting, and by dispel magic
     */
    public native static synchronized NWEffect magicalEffect(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Change the background day track for oArea to nTrack.
     * @param oArea
     * @param nTrack
     */
    public native static synchronized void musicBackgroundChangeDay(NWObject oArea, int nTrack) throws NWNotInContextException;

    /**
     * Change the background night track for oArea to nTrack.
     * @param oArea
     * @param nTrack
     */
    public native static synchronized void musicBackgroundChangeNight(NWObject oArea, int nTrack) throws NWNotInContextException;

    /**
     * Get the Battle Track for oArea.
     */
    public native static synchronized int musicBackgroundGetBattleTrack(NWObject oArea) throws NWNotInContextException;

    /**
     * Get the Day Track for oArea.
     */
    public native static synchronized int musicBackgroundGetDayTrack(NWObject oArea) throws NWNotInContextException;

    /**
     * Get the Night Track for oArea.
     */
    public native static synchronized int musicBackgroundGetNightTrack(NWObject oArea) throws NWNotInContextException;

    /**
     * Play the background music for oArea.
     */
    public native static synchronized void musicBackgroundPlay(NWObject oArea) throws NWNotInContextException;

    /**
     * Set the delay for the background music for oArea.
     * @param oArea
     * @param nDelay delay in milliseconds
     */
    public native static synchronized void musicBackgroundSetDelay(NWObject oArea, int nDelay) throws NWNotInContextException;

    /**
     * Stop the background music for oArea.
     */
    public native static synchronized void musicBackgroundStop(NWObject oArea) throws NWNotInContextException;

    /**
     * Change the battle track for oArea.
     * @param oArea
     * @param nTrack
     */
    public native static synchronized void musicBattleChange(NWObject oArea, int nTrack) throws NWNotInContextException;

    /**
     * Play the battle music for oArea.
     */
    public native static synchronized void musicBattlePlay(NWObject oArea) throws NWNotInContextException;

    /**
     * Stop the battle music for oArea.
     */
    public native static synchronized void musicBattleStop(NWObject oArea) throws NWNotInContextException;

    /**
     * Changes the current Day/Night cycle for this player to daylight
     * @param oPlayer which player to change the lighting for
     * @param fTransitionTime how long the transition should take
     */
    public native static synchronized void nightToDay(NWObject oPlayer, float fTransitionTime) throws NWNotInContextException;

    /**
     * Open's this creature's inventory panel for this player
     * @param oCreature creature to view
     * @param oPlayer the owner of this creature will see the panel pop up
     * * DM's can view any creature's inventory
     * * Players can view their own inventory, or that of their henchman, familiar or animal companion
     */
    public native static synchronized void openInventory(NWObject oCreature, NWObject oPlayer) throws NWNotInContextException;

    /**
     * Open oStore for oPC.
     * @param nBonusMarkUp is added to the stores default mark up percentage on items sold (-100 to 100)
     * @param nBonusMarkDown is added to the stores default mark down percentage on items bought (-100 to 100)
     */
    public native static synchronized void openStore(NWObject oStore, NWObject oPC, int nBonusMarkUp, int nBonusMarkDown) throws NWNotInContextException;

    /**
     * Play nAnimation immediately.
     * @param nAnimation ANIMATION_*
     * @param fSpeed
     * @param fSeconds
     */
    public native static synchronized void playAnimation(int nAnimation, float fSpeed, float fSeconds) throws NWNotInContextException;

    /**
     * Play sSoundName
     * @param sSoundName TBD - SS
     * This will play a mono sound from the location of the object running the command.
     */
    public native static synchronized void playSound(String sSoundName) throws NWNotInContextException;

    /**
     * This will play a sound that is associated with a stringRef, it will be a mono sound from the location of the object running the command.
     * if nRunAsAction is off then the sound is forced to play intantly.
     */
    public native static synchronized void playSoundByStrRef(int nStrRef, int nRunAsAction) throws NWNotInContextException;

    /**
     * Play a voice chat.
     * @param nVoiceChatID VOICE_CHAT_*
     * @param oTarget
     */
    public native static synchronized void playVoiceChat(int nVoiceChatID, NWObject oTarget) throws NWNotInContextException;

    /**
     * Spawn in the Death GUI.
     * The default (as defined by BioWare) can be spawned in by PopUpGUIPanel, but
     * if you want to turn off the "Respawn" or "Wait for Help" buttons, this is the
     * function to use.
     * @param oPC
     * @param bRespawnButtonEnabled if this is TRUE, the "Respawn" button will be enabled
     * on the Death GUI.
     * @param bWaitForHelpButtonEnabled if this is TRUE, the "Wait For Help" button will
     * be enabled on the Death GUI (Note: This button will not appear in single player games).
     * @param nHelpStringReference
     * @param sHelpString
     */
    public native static synchronized void popUpDeathGUIPanel(NWObject oPC, boolean bRespawnButtonEnabled, boolean bWaitForHelpButtonEnabled, int nHelpStringReference, String sHelpString) throws NWNotInContextException;

    /**
     * Spawn a GUI panel for the client that controls oPC.
     * @param oPC
     * @param nGUIPanel GUI_PANEL_*
     * * Nothing happens if oPC is not a player character or if an invalid value is
     * used for nGUIPanel.
     */
    public native static synchronized void popUpGUIPanel(NWObject oPC, int nGUIPanel) throws NWNotInContextException;

    /**
     * Output a formatted float to the log file.
     * @param nWidth should be a value from 0 to 18 inclusive.
     * @param nDecimals should be a value from 0 to 9 inclusive.
     */
    public native static synchronized void printFloat(float fFloat, int nWidth, int nDecimals) throws NWNotInContextException;

    /**
     * Output nInteger to the log file.
     */
    public native static synchronized void printInteger(int nInteger) throws NWNotInContextException;

    /**
     * Output oObject's ID to the log file.
     */
    public native static synchronized void printObject(NWObject oObject) throws NWNotInContextException;

    /**
     * Output sString to the log file.
     */
    public native static synchronized void printString(String sString) throws NWNotInContextException;

    /**
     * Output vVector to the logfile.
     * @param vVector
     * @param bPrepend if this is TRUE, the message will be prefixed with "PRINTVECTOR:"
     */
    public native static synchronized void printVector(NWVector vVector, boolean bPrepend) throws NWNotInContextException;

    /**
     * Return value on error: 0
     */
    public native static synchronized int random(int nMaxInteger) throws NWNotInContextException;

    /**
     * Generate a random name.
     * nNameType: The type of random name to be generated (NAME_*)
     */
    public native static synchronized String randomName(int nNameType) throws NWNotInContextException;

    /**
     * All clients in oArea will recompute the static lighting.
     * This can be used to update the lighting after changing any tile lights or if
     * placeables with lights have been added/deleted.
     */
    public native static synchronized void recomputeStaticLighting(NWObject oArea) throws NWNotInContextException;

    /**
     * Does a Reflex Save check for the given DC
     * @param oCreature
     * @param nDC Difficulty check
     * @param nSaveType SAVING_THROW_TYPE_*
     * @param oSaveVersus
     * Returns: 0 if the saving throw roll failed
     * Returns: 1 if the saving throw roll succeeded
     * Returns: 2 if the target was immune to the save type specified
     * Note: If used within an Area of Effect Object Script (On Enter, OnExit, OnHeartbeat), you MUST pass
     * GetAreaOfEffectCreator() into oSaveVersus!!
     */
    public native static synchronized int reflexSave(NWObject oCreature, int nDC, int nSaveType, NWObject oSaveVersus) throws NWNotInContextException;

    /**
     * Remove eEffect from oCreature.
     * * No return value
     */
    public native static synchronized void removeEffect(NWObject oCreature, NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Remove oPC from their current party. This will only work on a PC.
     * @param oPC removes this player from whatever party they're currently in.
     */
    public native static synchronized void removeFromParty(NWObject oPC) throws NWNotInContextException;

    /**
     * Remove oHenchman from the service of oMaster, returning them to their original faction.
     */
    public native static synchronized void removeHenchman(NWObject oMaster, NWObject oHenchman) throws NWNotInContextException;

    /**
     * removes an item property from the specified item
     */
    public native static synchronized void removeItemProperty(NWObject oItem, NWItemProperty ipProperty) throws NWNotInContextException, NWInvalidItemPropertyException;

    /**
     * Remove a journal quest entry from oCreature.
     * @param szPlotID the plot identifier used in the toolset's Journal Editor
     * @param oCreature
     * @param bAllPartyMembers If this is TRUE, the entry will be removed from the
     * journal of everyone in the party
     * @param bAllPlayers If this is TRUE, the entry will be removed from the journal of
     * everyone in the world
     */
    public native static synchronized void removeJournalQuestEntry(String szPlotID, NWObject oCreature, boolean bAllPartyMembers, boolean bAllPlayers) throws NWNotInContextException;

    /**
     * Removes oAssociate from the service of oMaster, returning them to their
     * original faction.
     */
    public native static synchronized void removeSummonedAssociate(NWObject oMaster, NWObject oAssociate) throws NWNotInContextException;

    /**
     * Do a Spell Resistance check between oCaster and oTarget, returning TRUE if
     * the spell was resisted.
     * * Return value if oCaster or oTarget is an invalid object: FALSE
     * * Return value if spell cast is not a player spell: @param 1
     * * Return value if spell resisted: 1
     * * Return value if spell resisted via magic immunity: 2
     * * Return value if spell resisted via spell absorption: 3
     */
    public native static synchronized int resistSpell(NWObject oCaster, NWObject oTarget) throws NWNotInContextException;

    /**
     * Restores the number of base attacks back to it's
     * original state.
     */
    public native static synchronized void restoreBaseAttackBonus(NWObject oCreature) throws NWNotInContextException;

    /**
     * Restores the camera mode and position to what they were last time StoreCameraFacing
     * was called.  RestoreCameraFacing can only be called once, and must correspond to a
     * previous call to StoreCameraFacing.
     */
    public native static synchronized void restoreCameraFacing() throws NWNotInContextException;

    /**
     * Use RetrieveCampaign with the given id to restore it.
     * If you specify an owner, the object will try to be created in their repository
     * If the owner can't handle the item (or if it's a creature) it will be created on the ground.
     */
    public native static synchronized NWObject retrieveCampaignObject(String sCampaignName, String sVarName, NWLocation locLocation, NWObject oOwner, NWObject oPlayer) throws NWNotInContextException;

    /**
     * Convert nRounds into a number of seconds
     * A round is always 6.0 seconds
     */
    public native static synchronized float roundsToSeconds(int nRounds) throws NWNotInContextException;

    /**
     * Sends szMessage to all the Dungeon Masters currently on the server.
     */
    public native static synchronized void sendMessageToAllDMs(String szMessage) throws NWNotInContextException;

    /**
     * Send a server message (szMessage) to the oPlayer.
     */
    public native static synchronized void sendMessageToPC(NWObject oPlayer, String szMessage) throws NWNotInContextException;

    /**
     * Send a server message (szMessage) to the oPlayer.
     */
    public native static synchronized void sendMessageToPCByStrRef(NWObject oPlayer, int nStrRef) throws NWNotInContextException;

    /**
     * Sets the current AI Level of the creature to the value specified. Does not work on Players.
     * The game by default will choose an appropriate AI level for
     * creatures based on the circumstances that the creature is in.
     * Explicitly setting an AI level will over ride the game AI settings.
     * The new setting will last until SetAILevel is called again with the argument AI_LEVEL_DEFAULT.
     * AI_LEVEL_DEFAULT  @param Default setting. The game will take over seting the appropriate AI level when required.
     * AI_LEVEL_VERY_LOW @param Very Low priority, very stupid, but low CPU usage for AI. Typically used when no players are in the area.
     * AI_LEVEL_LOW      @param Low priority, mildly stupid, but slightly more CPU usage for AI. Typically used when not in combat, but a player is in the area.
     * AI_LEVEL_NORMAL   @param Normal priority, average AI, but more CPU usage required for AI. Typically used when creature is in combat.
     * AI_LEVEL_HIGH     @param High priority, smartest AI, but extremely high CPU usage required for AI. Avoid using this. It is most likely only ever needed for cutscenes.
     */
    public native static synchronized void setAILevel(NWObject oTarget, int nAILevel) throws NWNotInContextException;

    /**
     * Sets the status of modes ACTION_MODE_* on a creature.
     */
    public native static synchronized void setActionMode(NWObject oCreature, int nActionMode, boolean bStatus) throws NWNotInContextException;

    /**
     * Set the transition bitmap of a player; this should only be called in area
     * transition scripts. This action should be run by the person "clicking" the
     * area transition via AssignCommand.
     * @param nPredefinedAreaTransition:
     * -> To use a predefined area transition bitmap, use one of AREA_TRANSITION_*
     * -> To use a custom, user-defined area transition bitmap, use
     * AREA_TRANSITION_USER_DEFINED and specify the filename in the second
     * parameter
     * @param sCustomAreaTransitionBMP this is the filename of a custom, user-defined
     * area transition bitmap
     */
    public native static synchronized void setAreaTransitionBMP(int nPredefinedAreaTransition, String sCustomAreaTransitionBMP) throws NWNotInContextException;

    /**
     * Initialise oTarget to listen for the standard Associates commands.
     */
    public native static synchronized void setAssociateListenPatterns(NWObject oTarget) throws NWNotInContextException;

    /**
     * Sets the number of base attacks for the specified
     * creatures. The range of values accepted are from
     * 1 to 6
     * Note: This function does not work on Player Characters
     */
    public native static synchronized void setBaseAttackBonus(int nBaseAttackBonus, NWObject oCreature) throws NWNotInContextException;

    /**
     * Set the calendar to the specified date.
     * @param nYear should be from 0 to 32000 inclusive
     * @param nMonth should be from 1 to 12 inclusive
     * @param nDay should be from 1 to 28 inclusive
     * 1) Time can only be advanced forwards; attempting to set the time backwards
     * will result in no change to the calendar.
     * 2) If values larger than the month or day are specified, they will be wrapped
     * around and the overflow will be used to advance the next field.
     * e.g. Specifying a year of 1350, month of 33 and day of 10 will result in
     * the calender being set to a year of 1352, a month of 9 and a day of 10.
     */
    public native static synchronized void setCalendar(int nYear, int nMonth, int nDay) throws NWNotInContextException;

    /**
     * Change the direction in which the camera is facing
     * @param fDirection is expressed as anticlockwise degrees from Due East.
     * (0.0f=East, 90.0f=North, 180.0f=West, 270.0f=South)
     * A value of -1.0f for any parameter will be ignored and instead it will
     * use the current camera value.
     * This can be used to change the way the camera is facing after the player
     * emerges from an area transition.
     * @param nTransitionType CAMERA_TRANSITION_TYPE_*  SNAP will immediately move the
     * camera to the new position, while the other types will result in the camera moving gradually into position
     * Pitch and distance are limited to valid values for the current camera mode:
     * Top Down: Distance = 5-20, Pitch = 1-50
     * Driving camera: Distance = 6 (can't be changed), Pitch = 1-62
     * Chase: Distance = 5-20, Pitch = 1-50
     * *** NOTE *** In NWN:Hordes of the Underdark the camera limits have been relaxed to the following:
     * Distance 1-25
     * Pitch 1-89
     */
    public native static synchronized void setCameraFacing(float fDirection, float fDistance, float fPitch, int nTransitionType) throws NWNotInContextException;

    /**
     * Forces this player's camera to be set to this height. Setting this value to zero will
     * restore the camera to the racial default height.
     */
    public native static synchronized void setCameraHeight(NWObject oPlayer, float fHeight) throws NWNotInContextException;

    /**
     * Set the camera mode for oPlayer.
     * @param oPlayer
     * @param nCameraMode CAMERA_MODE_*
     * * If oPlayer is not player-controlled or nCameraMode is invalid, nothing
     * happens.
     */
    public native static synchronized void setCameraMode(NWObject oPlayer, int nCameraMode) throws NWNotInContextException;

    /**
     * This stores a float out to the specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized void setCampaignFloat(String sCampaignName, String sVarName, float flFloat, NWObject oPlayer) throws NWNotInContextException;

    /**
     * This stores an int out to the specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized void setCampaignInt(String sCampaignName, String sVarName, int nInt, NWObject oPlayer) throws NWNotInContextException;

    /**
     * This stores a location out to the specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized void setCampaignLocation(String sCampaignName, String sVarName, NWLocation locLocation, NWObject oPlayer) throws NWNotInContextException;

    /**
     * This stores a string out to the specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized void setCampaignString(String sCampaignName, String sVarName, String sString, NWObject oPlayer) throws NWNotInContextException;

    /**
     * This stores a vector out to the specified campaign database
     * The database name IS case sensitive and it must be the same for both set and get functions.
     * The var name must be unique across the entire database, regardless of the variable type.
     * If you want a variable to pertain to a specific player in the game, provide a player object.
     */
    public native static synchronized void setCampaignVector(String sCampaignName, String sVarName, NWVector vVector, NWObject oPlayer) throws NWNotInContextException;

    /**
     * Set the color channel of oObject to the color specified.
     * @param oObject the object for which you are changing the color.
     * Can be a creature that has color information (i.e. the playable races).
     * @param nColorChannel The color channel that you want to set the color value of.
     * COLOR_CHANNEL_SKIN
     * COLOR_CHANNEL_HAIR
     * COLOR_CHANNEL_TATTOO_1
     * COLOR_CHANNEL_TATTOO_2
     * @param nColorValue The color you want to set (0-175).
     */
    public native static synchronized void setColor(NWObject oObject, int nColorChannel, int nColorValue) throws NWNotInContextException;

    /**
     * Set whether oTarget's action stack can be modified
     */
    public native static synchronized void setCommandable(boolean bCommandable, NWObject oTarget) throws NWNotInContextException;

    /**
     * Sets the creature's appearance type to the value specified (uses the APPEARANCE_TYPE_XXX constants)
     */
    public native static synchronized void setCreatureAppearanceType(NWObject oCreature, int nAppearanceType) throws NWNotInContextException;

    /**
     * Sets the body part model to be used on the creature specified.
     * The model names for parts need to be in the following format:
     * p<m/f><race letter><phenotype>_<body part><model number>.mdl
     * 
     * @param nPart (CREATURE_PART_*)
     * CREATURE_PART_RIGHT_FOOT
     * CREATURE_PART_LEFT_FOOT
     * CREATURE_PART_RIGHT_SHIN
     * CREATURE_PART_LEFT_SHIN
     * CREATURE_PART_RIGHT_THIGH
     * CREATURE_PART_LEFT_THIGH
     * CREATURE_PART_PELVIS
     * CREATURE_PART_TORSO
     * CREATURE_PART_BELT
     * CREATURE_PART_NECK
     * CREATURE_PART_RIGHT_FOREARM
     * CREATURE_PART_LEFT_FOREARM
     * CREATURE_PART_RIGHT_BICEP
     * CREATURE_PART_LEFT_BICEP
     * CREATURE_PART_RIGHT_SHOULDER
     * CREATURE_PART_LEFT_SHOULDER
     * CREATURE_PART_RIGHT_HAND
     * CREATURE_PART_LEFT_HAND
     * CREATURE_PART_HEAD
     * @param nModelNumber CREATURE_MODEL_TYPE_*
     * CREATURE_MODEL_TYPE_NONE
     * CREATURE_MODEL_TYPE_SKIN (not for use on shoulders, pelvis or head).
     * CREATURE_MODEL_TYPE_TATTOO (for body parts that support tattoos, i.e. not heads/feet/hands).
     * CREATURE_MODEL_TYPE_UNDEAD (undead model only exists for the right arm parts).
     * @param oCreature the creature to change the body part for.
     * Note: Only part based creature appearance types are supported.
     * i.e. The model types for the playable races ('P') in the appearance.2da
     */
    public native static synchronized void setCreatureBodyPart(int nCreaturePart, int nModelNumber, NWObject oCreature) throws NWNotInContextException;

    /**
     * Sets the Tail type of the creature specified.
     * @param nTailType (CREATURE_TAIL_TYPE_*)
     * CREATURE_TAIL_TYPE_NONE
     * CREATURE_TAIL_TYPE_LIZARD
     * CREATURE_TAIL_TYPE_BONE
     * CREATURE_TAIL_TYPE_DEVIL
     * @param oCreature the creature to change the Tail type for.
     * Note: Only two creature model types will support Tails.
     * The MODELTYPE for the part based (playable) races 'P'
     * and MODELTYPE 'T'in the appearance.2da
     */
    public native static synchronized void setCreatureTailType(int nTailType, NWObject oCreature) throws NWNotInContextException;

    /**
     * Sets the Wing type of the creature specified.
     * @param nWingType (CREATURE_WING_TYPE_*)
     * CREATURE_WING_TYPE_NONE
     * CREATURE_WING_TYPE_DEMON
     * CREATURE_WING_TYPE_ANGEL
     * CREATURE_WING_TYPE_BAT
     * CREATURE_WING_TYPE_DRAGON
     * CREATURE_WING_TYPE_BUTTERFLY
     * CREATURE_WING_TYPE_BIRD
     * @param oCreature the creature to change the wing type for.
     * Note: Only two creature model types will support wings.
     * The MODELTYPE for the part based (playable races) 'P'
     * and MODELTYPE 'W'in the appearance.2da
     */
    public native static synchronized void setCreatureWingType(int nWingType, NWObject oCreature) throws NWNotInContextException;

    /**
     * Set the value for a custom token.
     */
    public native static synchronized void setCustomToken(int nCustomTokenNumber, String sTokenValue) throws NWNotInContextException;

    /**
     * Sets the current movement rate factor for the cutscene
     * camera man.
     * NOTE: You can only set values between 0.1, 2.0 (10%-200%)
     */
    public native static synchronized void setCutsceneCameraMoveRate(NWObject oCreature, float fRate) throws NWNotInContextException;

    /**
     * Sets the given creature into cutscene mode.  This prevents the player from
     * using the GUI and camera controls.
     * @param oCreature creature in a cutscene
     * @param bInCutscene TRUE to move them into cutscene, FALSE to remove cutscene mode
     * @param bLeftClickingEnabled TRUE to allow the user to interact with the game world using the left mouse button only.
     * FALSE to stop the user from interacting with the game world.
     * Note: SetCutsceneMode(oPlayer, TRUE) will also make the player 'plot' (unkillable).
     * SetCutsceneMode(oPlayer, FALSE) will restore the player's plot flag to what it
     * was when SetCutsceneMode(oPlayer, TRUE) was called.
     */
    public native static synchronized void setCutsceneMode(NWObject oCreature, boolean bInCutscene, boolean bLeftClickingEnabled) throws NWNotInContextException;

    /**
     * Set the name of oCreature's Deity to sDeity.
     */
    public native static synchronized void setDeity(NWObject oCreature, String sDeity) throws NWNotInContextException;

    /**
     * Set the description of oObject.
     * @param oObject the object for which you are changing the description
     * Can be a creature, placeable, item, door, or trigger.
     * @param sNewDescription the new description that the object will use.
     * @param bIdentified If oObject is an item, setting this to TRUE will set the identified description,
     * setting this to FALSE will set the unidentified description. This flag has no
     * effect on objects other than items.
     * Note: Setting an object's description to "" will make the object
     * revert to using the description it originally had before any
     * SetDescription() calls were made on the object.
     */
    public native static synchronized void setDescription(NWObject oObject, String sNewDescription, boolean bIdentifiedDescription) throws NWNotInContextException;

    /**
     * Sets the droppable flag on an item
     * @param oItem the item to change
     * @param bDroppable TRUE or FALSE, whether the item should be droppable
     * Droppable items will appear on a creature's remains when the creature is killed.
     */
    public native static synchronized void setDroppableFlag(NWObject oItem, boolean bDroppable) throws NWNotInContextException;

    /**
     * Set oEncounter's active state to nNewValue.
     * @param bNewValue TRUE/FALSE
     * @param oEncounter
     */
    public native static synchronized void setEncounterActive(boolean bNewValue, NWObject oEncounter) throws NWNotInContextException;

    /**
     * Set the difficulty level of oEncounter.
     * @param nEncounterDifficulty ENCOUNTER_DIFFICULTY_*
     * @param oEncounter
     */
    public native static synchronized void setEncounterDifficulty(int nEncounterDifficulty, NWObject oEncounter) throws NWNotInContextException;

    /**
     * Set the number of times that oEncounter has spawned so far
     */
    public native static synchronized void setEncounterSpawnsCurrent(int nNewValue, NWObject oEncounter) throws NWNotInContextException;

    /**
     * Set the maximum number of times that oEncounter can spawn
     */
    public native static synchronized void setEncounterSpawnsMax(int nNewValue, NWObject oEncounter) throws NWNotInContextException;

    /**
     * Cause the caller to face fDirection.
     * @param fDirection is expressed as anticlockwise degrees from Due East.
     * DIRECTION_EAST, DIRECTION_NORTH, DIRECTION_WEST and DIRECTION_SOUTH are
     * predefined. (0.0f=East, 90.0f=North, 180.0f=West, 270.0f=South)
     */
    public native static synchronized void setFacing(float fDirection) throws NWNotInContextException;

    /**
     * Cause the caller to face vTarget
     */
    public native static synchronized void setFacingPoint(NWVector vTarget) throws NWNotInContextException;

    /**
     * Sets the fog amount in the area specified.
     * nFogType = FOG_TYPE_* specifies wether the Sun, Moon, or both fog types are set.
     * nFogAmount = specifies the density that the fog is being set to.
     * If no valid area (or object) is specified, it uses the area of caller.
     * If an object other than an area is specified, will use the area that the object is currently in.
     */
    public native static synchronized void setFogAmount(int nFogType, int nFogAmount, NWObject oArea) throws NWNotInContextException;

    /**
     * Sets the fog color in the area specified.
     * nFogType = FOG_TYPE_* specifies wether the Sun, Moon, or both fog types are set.
     * nFogColor = FOG_COLOR_* specifies the color the fog is being set to.
     * The fog color can also be represented as a hex RGB number if specific color shades
     * are desired.
     * The format of a hex specified color would be 0xFFEEDD where
     * FF would represent the amount of red in the color
     * EE would represent the amount of green in the color
     * DD would represent the amount of blue in the color.
     * If no valid area (or object) is specified, it uses the area of caller.
     * If an object other than an area is specified, will use the area that the object is currently in.
     */
    public native static synchronized void setFogColor(int nFogType, int nFogColor, NWObject oArea) throws NWNotInContextException;

    /**
     * Sets the footstep type of the creature specified.
     * Changing a creature's footstep type will change the sound that
     * its feet make when ever the creature makes takes a step.
     * By default a creature's footsteps are detemined by the appearance
     * type of the creature. SetFootstepType() allows you to make a
     * creature use a difference footstep type than it would use by default
     * for its given appearance.
     * @param nFootstepType (FOOTSTEP_TYPE_*):
     * FOOTSTEP_TYPE_NORMAL
     * FOOTSTEP_TYPE_LARGE
     * FOOTSTEP_TYPE_DRAGON
     * FOOTSTEP_TYPE_SoFT
     * FOOTSTEP_TYPE_HOOF
     * FOOTSTEP_TYPE_HOOF_LARGE
     * FOOTSTEP_TYPE_BEETLE
     * FOOTSTEP_TYPE_SPIDER
     * FOOTSTEP_TYPE_SKELETON
     * FOOTSTEP_TYPE_LEATHER_WING
     * FOOTSTEP_TYPE_FEATHER_WING
     * FOOTSTEP_TYPE_DEFAULT @param Makes the creature use its original default footstep sounds.
     * FOOTSTEP_TYPE_NONE
     * @param oCreature the creature to change the footstep sound for.
     */
    public native static synchronized void setFootstepType(int nFootstepType, NWObject oCreature) throws NWNotInContextException;

    /**
     * Set the Fortitude saving throw value of the Door or Placeable object oObject.
     * @param oObject a door or placeable object.
     * @param nFortitudeSave must be between 0 and 250.
     */
    public native static synchronized void setFortitudeSavingThrow(NWObject oObject, int nFortitudeSave) throws NWNotInContextException;

    /**
     * Sets the Hardness of a Door or Placeable object.
     * @param nHardness must be between 0 and 250.
     * @param oObject a door or placeable object.
     * Does nothing if used on an object that is neither
     * a door nor a placeable.
     */
    public native static synchronized void setHardness(int nHardness, NWObject oObject) throws NWNotInContextException;

    /**
     * Set whether oItem has been identified.
     */
    public native static synchronized void setIdentified(NWObject oItem, boolean bIdentified) throws NWNotInContextException;

    /**
     * Set a creature's immortality flag.
     * -oCreature: creature affected
     * -bImmortal: TRUE = creature is immortal and cannot be killed (but still takes damage)
     * FALSE = creature is not immortal and is damaged normally.
     * This scripting command only works on Creature objects.
     */
    public native static synchronized void setImmortal(NWObject oCreature, boolean bImmortal) throws NWNotInContextException;

    /**
     * Sets the Infinite flag on an item
     * @param oItem the item to change
     * @param bInfinite TRUE or FALSE, whether the item should be Infinite
     * The infinite property affects the buying/selling behavior of the item in a store.
     * An infinite item will still be available to purchase from a store after a player
     * buys the item (non-infinite items will disappear from the store when purchased).
     */
    public native static synchronized void setInfiniteFlag(NWObject oItem, boolean bInfinite) throws NWNotInContextException;

    /**
     * Set the destroyable status of the caller.
     * @param bDestroyable If this is FALSE, the caller does not fade out on death, but
     * sticks around as a corpse.
     * @param bRaiseable If this is TRUE, the caller can be raised via resurrection.
     * @param bSelectableWhenDead If this is TRUE, the caller is selectable after death.
     */
    public native static synchronized void setIsDestroyable(boolean bDestroyable, boolean bRaiseable, boolean bSelectableWhenDead) throws NWNotInContextException;

    /**
     * Make oSource into a temporary enemy of oTarget using personal reputation.
     * @param oTarget
     * @param oSource
     * @param bDecays If this is TRUE, the enmity decays over fDurationInSeconds;
     * otherwise it is indefinite.
     * @param fDurationInSeconds This is only used if bDecays is TRUE, it is how long
     * the enmity lasts.
     * Note: If bDecays is TRUE, the personal reputation amount decreases in size
     * over fDurationInSeconds. Enmity will only be in effect as long as
     * (faction reputation + total personal reputation) <= REPUTATION_TYPE_ENEMY.
     */
    public native static synchronized void setIsTemporaryEnemy(NWObject oTarget, NWObject oSource, boolean bDecays, float fDurationInSeconds) throws NWNotInContextException;

    /**
     * oSource will temporarily be friends towards oTarget.
     * bDecays determines whether the personal reputation value decays over time
     * fDurationInSeconds is the length of time that the temporary friendship lasts
     * Make oSource into a temporary friend of oTarget using personal reputation.
     * @param oTarget
     * @param oSource
     * @param bDecays If this is TRUE, the friendship decays over fDurationInSeconds;
     * otherwise it is indefinite.
     * @param fDurationInSeconds This is only used if bDecays is TRUE, it is how long
     * the friendship lasts.
     * Note: If bDecays is TRUE, the personal reputation amount decreases in size
     * over fDurationInSeconds. Friendship will only be in effect as long as
     * (faction reputation + total personal reputation) >= REPUTATION_TYPE_FRIEND.
     */
    public native static synchronized void setIsTemporaryFriend(NWObject oTarget, NWObject oSource, boolean bDecays, float fDurationInSeconds) throws NWNotInContextException;

    /**
     * Make oSource temporarily neutral to oTarget using personal reputation.
     * @param oTarget
     * @param oSource
     * @param bDecays If this is TRUE, the neutrality decays over fDurationInSeconds;
     * otherwise it is indefinite.
     * @param fDurationInSeconds This is only used if bDecays is TRUE, it is how long
     * the neutrality lasts.
     * Note: If bDecays is TRUE, the personal reputation amount decreases in size
     * over fDurationInSeconds. Neutrality will only be in effect as long as
     * (faction reputation + total personal reputation) > REPUTATION_TYPE_ENEMY and
     * (faction reputation + total personal reputation) < REPUTATION_TYPE_FRIEND.
     */
    public native static synchronized void setIsTemporaryNeutral(NWObject oTarget, NWObject oSource, boolean bDecays, float fDurationInSeconds) throws NWNotInContextException;

    /**
     * Sets charges left on an item.
     * @param oItem item to change
     * @param nCharges number of charges.  If value below 0 is passed, # charges will
     * be set to 0.  If value greater than maximum is passed, # charges will
     * be set to maximum.  If the # charges drops to 0 the item
     * will be destroyed.
     */
    public native static synchronized void setItemCharges(NWObject oItem, int nCharges) throws NWNotInContextException;

    /**
     * When cursed, items cannot be dropped
     */
    public native static synchronized void setItemCursedFlag(NWObject oItem, boolean bCursed) throws NWNotInContextException;

    /**
     * Sets stack size of an item.
     * @param oItem item to change
     * @param nSize new size of stack.  Will be restricted to be between 1 and the
     * maximum stack size for the item type.  If a value less than 1 is passed it
     * will set the stack to 1.  If a value greater than the max is passed
     * then it will set the stack to the maximum size
     */
    public native static synchronized void setItemStackSize(NWObject oItem, int nSize) throws NWNotInContextException;

    /**
     * Set the feedback message that is displayed when trying to unlock the object oObject.
     * This will only have an effect if the object is set to
     * "Key required to unlock or lock" either in the toolset
     * or by using the scripting command SetLockKeyRequired().
     * @param oObject a door or placeable.
     * @param sFeedbackMessage the string to be displayed in the player's text window.
     * to use the game's default message, set sFeedbackMessage to ""
     */
    public native static synchronized void setKeyRequiredFeedback(NWObject oObject, String sFeedbackMessage) throws NWNotInContextException;

    /**
     * Set the string for oObject to listen for.
     * Note: this does not set oObject to be listening.
     */
    public native static synchronized void setListenPattern(NWObject oObject, String sPattern, int nNumber) throws NWNotInContextException;

    /**
     * Set whether oObject is listening.
     */
    public native static synchronized void setListening(NWObject oObject, boolean bValue) throws NWNotInContextException;

    /**
     * Set oObject's local float variable sVarName to nValue
     */
    public native static synchronized void setLocalFloat(NWObject oObject, String sVarName, float fValue) throws NWNotInContextException;

    /**
     * Set oObject's local integer variable sVarName to nValue
     */
    public native static synchronized void setLocalInt(NWObject oObject, String sVarName, int nValue) throws NWNotInContextException;

    /**
     * Set oObject's local location variable sVarname to lValue
     */
    public native static synchronized void setLocalLocation(NWObject oObject, String sVarName, NWLocation lValue) throws NWNotInContextException;

    /**
     * Set oObject's local object variable sVarName to nValue
     */
    public native static synchronized void setLocalObject(NWObject oObject, String sVarName, NWObject oValue) throws NWNotInContextException;

    /**
     * Set oObject's local string variable sVarName to nValue
     */
    public native static synchronized void setLocalString(NWObject oObject, String sVarName, String sValue) throws NWNotInContextException;

    /**
     * When set the object can not be opened unless the
     * opener possesses the required key. The key tag required
     * can be specified either in the toolset, or by using
     * the SetLockKeyTag() scripting command.
     * @param oObject a door, or placeable.
     * @param bKeyRequired TRUE/FALSE
     */
    public native static synchronized void setLockKeyRequired(NWObject oObject, boolean bKeyRequired) throws NWNotInContextException;

    /**
     * Set the key tag required to open object oObject.
     * This will only have an effect if the object is set to
     * "Key required to unlock or lock" either in the toolset
     * or by using the scripting command SetLockKeyRequired().
     * @param oObject a door, placeable or trigger.
     * @param sNewKeyTag the key tag required to open the locked object.
     */
    public native static synchronized void setLockKeyTag(NWObject oObject, String sNewKeyTag) throws NWNotInContextException;

    /**
     * Sets the DC for locking the object.
     * @param oObject a door or placeable object.
     * @param nNewLockDC must be between 0 and 250.
     */
    public native static synchronized void setLockLockDC(NWObject oObject, int nNewLockDC) throws NWNotInContextException;

    /**
     * Sets whether or not the object can be locked.
     * @param oObject a door or placeable.
     * @param bLockable TRUE/FALSE
     */
    public native static synchronized void setLockLockable(NWObject oObject, boolean bLockable) throws NWNotInContextException;

    /**
     * Sets the DC for unlocking the object.
     * @param oObject a door or placeable object.
     * @param nNewUnlockDC must be between 0 and 250.
     */
    public native static synchronized void setLockUnlockDC(NWObject oObject, int nNewUnlockDC) throws NWNotInContextException;

    /**
     * Set the locked state of oTarget, which can be a door or a placeable object.
     */
    public native static synchronized void setLocked(NWObject oTarget, boolean bLocked) throws NWNotInContextException;

    /**
     * Sets the lootable state of a *living* NPC creature.
     * This function will *not* work on players or dead creatures.
     */
    public native static synchronized void setLootable(NWObject oCreature, boolean bLootable) throws NWNotInContextException;

    /**
     * Set whether oMapPin is enabled.
     * @param oMapPin
     * @param bEnabled 0=Off, 1=On
     */
    public native static synchronized void setMapPinEnabled(NWObject oMapPin, boolean bEnabled) throws NWNotInContextException;

    /**
     * Sets the maximum number of henchmen
     */
    public native static synchronized void setMaxHenchmen(int nNumHenchmen) throws NWNotInContextException;

    /**
     * Set the XP scale used by the module.
     * @param nXPScale The XP scale to be used. Must be between 0 and 200.
     */
    public native static synchronized void setModuleXPScale(int nXPScale) throws NWNotInContextException;

    /**
     * Set the name of oObject.
     * @param oObject the object for which you are changing the name (a creature, placeable, item, or door).
     * @param sNewName the new name that the object will use.
     * Note: SetName() does not work on player objects.
     * Setting an object's name to "" will make the object
     * revert to using the name it had originally before any
     * SetName() calls were made on the object.
     */
    public native static synchronized void setName(NWObject oObject, String sNewName) throws NWNotInContextException;

    /**
     * Set the last player chat(text) message before it gets sent to other players.
     * @param sNewChatMessage The new chat text to be sent onto other players.
     * Setting the player chat message to an empty string "",
     * will cause the chat message to be discarded
     * (i.e. it will not be sent to other players).
     * Note: The new chat message gets sent after the OnPlayerChat script exits.
     */
    public native static synchronized void setPCChatMessage(String sNewChatMessage) throws NWNotInContextException;

    /**
     * Set the last player chat(text) volume before it gets sent to other players.
     * @param nTalkVolume The new volume of the chat text to be sent onto other players.
     * TALKVOLUME_TALK
     * TALKVOLUME_WHISPER
     * TALKVOLUME_SHOUT
     * TALKVOLUME_SILENT_SHOUT (used for DM chat channel)
     * TALKVOLUME_PARTY
     * TALKVOLUME_TELL (sends the chat message privately back to the original speaker)
     * Note: The new chat message gets sent after the OnPlayerChat script exits.
     */
    public native static synchronized void setPCChatVolume(int nTalkVolume) throws NWNotInContextException;

    /**
     * Sets oPlayer and oTarget to dislike each other.
     */
    public native static synchronized void setPCDislike(NWObject oPlayer, NWObject oTarget) throws NWNotInContextException;

    /**
     * Sets oPlayer and oTarget to like each other.
     */
    public native static synchronized void setPCLike(NWObject oPlayer, NWObject oTarget) throws NWNotInContextException;

    /**
     * Make the corresponding panel button on the player's client start or stop
     * flashing.
     * @param oPlayer
     * @param nButton PANEL_BUTTON_*
     * @param bEnableFlash if this is TRUE nButton will start flashing.  It if is FALSE,
     * nButton will stop flashing.
     */
    public native static synchronized void setPanelButtonFlash(NWObject oPlayer, int nButton, boolean bEnableFlash) throws NWNotInContextException;

    /**
     * Sets the creature's PhenoType (body type) to the type specified.
     * nPhenoType = PHENOTYPE_NORMAL
     * nPhenoType = PHENOTYPE_BIG
     * nPhenoType = PHENOTYPE_CUSTOM* @param The custom PhenoTypes should only ever
     * be used if you have specifically created your own custom content that
     * requires the use of a new PhenoType and you have specified the appropriate
     * custom PhenoType in your custom content.
     * SetPhenoType will only work on part based creature (i.e. the starting
     * default playable races).
     */
    public native static synchronized void setPhenoType(int nPhenoType, NWObject oCreature) throws NWNotInContextException;

    /**
     * Sets the Pickpocketable flag on an item
     * @param oItem the item to change
     * @param bPickpocketable TRUE or FALSE, whether the item can be pickpocketed.
     */
    public native static synchronized void setPickpocketableFlag(NWObject oItem, boolean bPickpocketable) throws NWNotInContextException;

    /**
     * Set the status of the illumination for oPlaceable.
     * @param oPlaceable
     * @param bIlluminate if this is TRUE, oPlaceable's illumination will be turned on.
     * If this is FALSE, oPlaceable's illumination will be turned off.
     * Note: You must call RecomputeStaticLighting() after calling this function in
     * order for the changes to occur visually for the players.
     * SetPlaceableIllumination() buffers the illumination changes, which are then
     * sent out to the players once RecomputeStaticLighting() is called.  As such,
     * it is best to call SetPlaceableIllumination() for all the placeables you wish
     * to set the illumination on, and then call RecomputeStaticLighting() once after
     * all the placeable illumination has been set.
     * * If oPlaceable is not a placeable object, or oPlaceable is a placeable that
     * doesn't have a light, nothing will happen.
     */
    public native static synchronized void setPlaceableIllumination(NWObject oPlaceable, boolean bIlluminate) throws NWNotInContextException;

    /**
     * Set oTarget's plot object status.
     */
    public native static synchronized void setPlotFlag(NWObject oTarget, boolean bPlotFlag) throws NWNotInContextException;

    /**
     * Change the portrait of oTarget to use the Portrait Id specified.
     * @param oTarget the object for which you are changing the portrait.
     * @param nPortraitId The Id of the new portrait to use.
     * nPortraitId refers to a row in the Portraits.2da
     * Note: Not all portrait Ids are suitable for use with all object types.
     * Setting the portrait Id will also cause the portrait ResRef
     * to be set to the appropriate portrait ResRef for the Id specified.
     */
    public native static synchronized void setPortraitId(NWObject oTarget, int nPortraitId) throws NWNotInContextException;

    /**
     * Change the portrait of oTarget to use the Portrait ResRef specified.
     * @param oTarget the object for which you are changing the portrait.
     * @param sPortraitResRef The ResRef of the new portrait to use.
     * The ResRef should not include any trailing size letter ( e.g. po_el_f_09_ ).
     * Note: Not all portrait ResRefs are suitable for use with all object types.
     * Setting the portrait ResRef will also cause the portrait Id
     * to be set to PORTRAIT_INVALID.
     */
    public native static synchronized void setPortraitResRef(NWObject oTarget, String sPortraitResRef) throws NWNotInContextException;

    /**
     * Set the Reflex saving throw value of the Door or Placeable object oObject.
     * @param oObject a door or placeable object.
     * @param nReflexSave must be between 0 and 250.
     */
    public native static synchronized void setReflexSavingThrow(NWObject oObject, int nReflexSave) throws NWNotInContextException;

    /**
     * Changes the sky that is displayed in the specified area.
     * nSkyBox = SKYBOX_* constants (associated with skyboxes.2da)
     * If no valid area (or object) is specified, it uses the area of caller.
     * If an object other than an area is specified, will use the area that the object is currently in.
     */
    public native static synchronized void setSkyBox(int nSkyBox, NWObject oArea) throws NWNotInContextException;

    /**
     * Set how nStandardFaction feels about oCreature.
     * @param nStandardFaction STANDARD_FACTION_*
     * @param nNewReputation 0-100 (inclusive)
     * @param oCreature
     */
    public native static synchronized void setStandardFactionReputation(int nStandardFaction, int nNewReputation, NWObject oCreature) throws NWNotInContextException;

    /**
     * Sets whether this item is 'stolen' or not
     */
    public native static synchronized void setStolenFlag(NWObject oItem, boolean bStolenFlag) throws NWNotInContextException;

    /**
     * Sets the amount of gold a store has. -1 means the store does not use gold.
     */
    public native static synchronized void setStoreGold(NWObject oidStore, int nGold) throws NWNotInContextException;

    /**
     * Sets the amount a store charges for identifying an item. Default is 100. -1 means
     * the store will not identify items.
     */
    public native static synchronized void setStoreIdentifyCost(NWObject oidStore, int nCost) throws NWNotInContextException;

    /**
     * Sets the maximum amount a store will pay for any item. -1 means price unlimited.
     */
    public native static synchronized void setStoreMaxBuyPrice(NWObject oidStore, int nMaxBuy) throws NWNotInContextException;

    /**
     * Set the name of oCreature's sub race to sSubRace.
     */
    public native static synchronized void setSubRace(NWObject oCreature, String sSubRace) throws NWNotInContextException;

    /**
     * Set the main light color on the tile at lTileLocation.
     * @param lTileLocation the vector part of this is the tile grid (x,y) coordinate of
     * the tile.
     * @param nMainLight1Color TILE_MAIN_LIGHT_COLOR_*
     * @param nMainLight2Color TILE_MAIN_LIGHT_COLOR_*
     */
    public native static synchronized void setTileMainLightColor(NWLocation lTileLocation, int nMainLight1Color, int nMainLight2Color) throws NWNotInContextException;

    /**
     * Set the source light color on the tile at lTileLocation.
     * @param lTileLocation the vector part of this is the tile grid (x,y) coordinate of
     * the tile.
     * @param nSourceLight1Color TILE_SOURCE_LIGHT_COLOR_*
     * @param nSourceLight2Color TILE_SOURCE_LIGHT_COLOR_*
     */
    public native static synchronized void setTileSourceLightColor(NWLocation lTileLocation, int nSourceLight1Color, int nSourceLight2Color) throws NWNotInContextException;

    /**
     * Set the time to the time specified.
     * @param nHour should be from 0 to 23 inclusive
     * @param nMinute should be from 0 to 59 inclusive
     * @param nSecond should be from 0 to 59 inclusive
     * @param nMillisecond should be from 0 to 999 inclusive
     * 1) Time can only be advanced forwards; attempting to set the time backwards
     * will result in the day advancing and then the time being set to that
     * specified, e.g. if the current hour is 15 and then the hour is set to 3,
     * the day will be advanced by 1 and the hour will be set to 3.
     * 2) If values larger than the max hour, minute, second or millisecond are
     * specified, they will be wrapped around and the overflow will be used to
     * advance the next field, e.g. specifying 62 hours, 250 minutes, 10 seconds
     * and 10 milliseconds will result in the calendar day being advanced by 2
     * and the time being set to 18 hours, 10 minutes, 10 milliseconds.
     */
    public native static synchronized void setTime(int nHour, int nMinute, int nSecond, int nMillisecond) throws NWNotInContextException;

    /**
     * Sets whether or not the trap is an active trap
     * @param oTrapObject a placeable, door or trigger
     * @param bActive TRUE/FALSE
     * Notes:
     * Setting a trap as inactive will not make the
     * trap disappear if it has already been detected.
     * Call SetTrapDetectedBy() to make a detected trap disappear.
     * To make an inactive trap not detectable call SetTrapDetectable()
     */
    public native static synchronized void setTrapActive(NWObject oTrapObject, boolean bActive) throws NWNotInContextException;

    /**
     * Set the DC for detecting oTrapObject.
     * @param oTrapObject a placeable, door or trigger
     * @param nDetectDC must be between 0 and 250.
     */
    public native static synchronized void setTrapDetectDC(NWObject oTrapObject, int nDetectDC) throws NWNotInContextException;

    /**
     * Sets whether or not the trapped object can be detected.
     * @param oTrapObject a placeable, door or trigger
     * @param bDetectable TRUE/FALSE
     * Note: Setting a trapped object to not be detectable will
     * not make the trap disappear if it has already been detected.
     */
    public native static synchronized void setTrapDetectable(NWObject oTrapObject, boolean bDetectable) throws NWNotInContextException;

    /**
     * Set whether or not the creature oDetector has detected the trapped object oTrap.
     * @param oTrap A trapped trigger, placeable or door object.
     * @param oDetector This is the creature that the detected status of the trap is being adjusted for.
     * @param bDetected A Boolean that sets whether the trapped object has been detected or not.
     */
    public native static synchronized int setTrapDetectedBy(NWObject oTrap, NWObject oDetector, boolean bDetected) throws NWNotInContextException;

    /**
     * Disable oTrap.
     * @param oTrap a placeable, door or trigger.
     */
    public native static synchronized void setTrapDisabled(NWObject oTrap) throws NWNotInContextException;

    /**
     * Set the DC for disarming oTrapObject.
     * @param oTrapObject a placeable, door or trigger
     * @param nDisarmDC must be between 0 and 250.
     */
    public native static synchronized void setTrapDisarmDC(NWObject oTrapObject, int nDisarmDC) throws NWNotInContextException;

    /**
     * Sets whether or not the trapped object can be disarmed.
     * @param oTrapObject a placeable, door or trigger
     * @param bDisarmable TRUE/FALSE
     */
    public native static synchronized void setTrapDisarmable(NWObject oTrapObject, boolean bDisarmable) throws NWNotInContextException;

    /**
     * Set the tag of the key that will disarm oTrapObject.
     * @param oTrapObject a placeable, door or trigger
     */
    public native static synchronized void setTrapKeyTag(NWObject oTrapObject, String sKeyTag) throws NWNotInContextException;

    /**
     * Sets whether or not the trap is a one-shot trap
     * (i.e. whether or not the trap resets itself after firing).
     * @param oTrapObject a placeable, door or trigger
     * @param bOneShot TRUE/FALSE
     */
    public native static synchronized void setTrapOneShot(NWObject oTrapObject, boolean bOneShot) throws NWNotInContextException;

    /**
     * Sets whether or not the trapped object can be recovered.
     * @param oTrapObject a placeable, door or trigger
     */
    public native static synchronized void setTrapRecoverable(NWObject oTrapObject, boolean bRecoverable) throws NWNotInContextException;

    /**
     * Set oPlaceable's useable object status.
     * Note: Only works on non-static placeables.
     */
    public native static synchronized void setUseableFlag(NWObject oPlaceable, boolean bUseableFlag) throws NWNotInContextException;

    /**
     * Set the weather for oTarget.
     * @param oTarget if this is GetModule(), all outdoor areas will be modified by the
     * weather constant. If it is an area, oTarget will play the weather only if
     * it is an outdoor area.
     * @param nWeather WEATHER_*
     * -> WEATHER_USER_AREA_SETTINGS will set the area back to random weather.
     * -> WEATHER_CLEAR, WEATHER_RAIN, WEATHER_SNOW will make the weather go to
     * the appropriate precipitation *without stopping*.
     */
    public native static synchronized void setWeather(NWObject oTarget, int nWeather) throws NWNotInContextException;

    /**
     * Set the Will saving throw value of the Door or Placeable object oObject.
     * @param oObject a door or placeable object.
     * @param nWillSave must be between 0 and 250.
     */
    public native static synchronized void setWillSavingThrow(NWObject oObject, int nWillSave) throws NWNotInContextException;

    /**
     * Sets oCreature's experience to nXpAmount.
     */
    public native static synchronized void setXP(NWObject oCreature, int nXpAmount) throws NWNotInContextException;

    /**
     * Play oSound.
     */
    public native static synchronized void soundObjectPlay(NWObject oSound) throws NWNotInContextException;

    /**
     * Set the position of oSound.
     */
    public native static synchronized void soundObjectSetPosition(NWObject oSound, NWVector vPosition) throws NWNotInContextException;

    /**
     * Set the volume of oSound.
     * @param oSound
     * @param nVolume 0-127
     */
    public native static synchronized void soundObjectSetVolume(NWObject oSound, int nVolume) throws NWNotInContextException;

    /**
     * Stop playing oSound.
     */
    public native static synchronized void soundObjectStop(NWObject oSound) throws NWNotInContextException;

    /**
     * SpawnScriptDebugger() will cause the script debugger to be executed
     * after this command is executed!
     * In order to compile the script for debugging go to Tools->Options->Script Editor
     * and check the box labeled "Generate Debug Information When Compiling Scripts"
     * After you have checked the above box, recompile the script that you want to debug.
     * If the script file isn't compiled for debugging, this command will do nothing.
     * Remove any SpawnScriptDebugger() calls once you have finished
     * debugging the script.
     */
    public native static synchronized void spawnScriptDebugger() throws NWNotInContextException;

    /**
     * Immediately speak a conversation one-liner.
     * @param sDialogResRef
     * @param oTokenTarget This must be specified if there are creature-specific tokens
     * in the string.
     */
    public native static synchronized void speakOneLinerConversation(String sDialogResRef, NWObject oTokenTarget) throws NWNotInContextException;

    /**
     * The caller will immediately speak sStringToSpeak (this is different from
     * ActionSpeakString)
     * @param sStringToSpeak
     * @param nTalkVolume TALKVOLUME_*
     */
    public native static synchronized void speakString(String sStringToSpeak, int nTalkVolume) throws NWNotInContextException;

    /**
     * Causes the object to instantly speak a translated string.
     * (not an action, not blocked when uncommandable)
     * @param nStrRef Reference of the string in the talk table
     * @param nTalkVolume TALKVOLUME_*
     */
    public native static synchronized void speakStringByStrRef(int nStrRef, int nTalkVolume) throws NWNotInContextException;

    /**
     * Shut down the currently loaded module and start a new one (moving all
     * currently-connected players to the starting point.
     */
    public native static synchronized void startNewModule(String sModuleName) throws NWNotInContextException;

    /**
     * Removes any fading or black screen.
     * @param oCreature creature controlled by player that should be cleared
     */
    public native static synchronized void stopFade(NWObject oCreature) throws NWNotInContextException;

    /**
     * Stores the current camera mode and position so that it can be restored (using
     * RestoreCameraFacing())
     */
    public native static synchronized void storeCameraFacing() throws NWNotInContextException;

    /**
     * Stores an object with the given id.
     * NOTE: this command can only be used for storing Creatures and Items.
     * Returns 0 if it failled, 1 if it worked.
     */
    public native static synchronized int storeCampaignObject(String sCampaignName, String sVarName, NWObject oObject, NWObject oPlayer) throws NWNotInContextException;

    /**
     * Convert sNumber into a floating point number.
     */
    public native static synchronized float stringToFloat(String sNumber) throws NWNotInContextException;

    /**
     * Convert sNumber into an integer.
     */
    public native static synchronized int stringToInt(String sNumber) throws NWNotInContextException;

    /**
     * Summon an Animal Companion
     */
    public native static synchronized void summonAnimalCompanion(NWObject oMaster) throws NWNotInContextException;

    /**
     * Summon a Familiar
     */
    public native static synchronized void summonFamiliar(NWObject oMaster) throws NWNotInContextException;

    /**
     * Set the subtype of eEffect to Supernatural and return eEffect.
     * (Effects default to magical if the subtype is not set)
     * Permanent supernatural effects are not removed by resting
     */
    public native static synchronized NWEffect supernaturalEffect(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Use this on an NPC to cause all creatures within a 10-metre radius to stop
     * what they are doing and sets the NPC's enemies within this range to be
     * neutral towards the NPC for roughly 3 minutes. If this command is run on a PC
     * or an object that is not a creature, nothing will happen.
     */
    public native static synchronized void surrenderToEnemies() throws NWNotInContextException;

    /**
     * Take nAmount of gold from oCreatureToTakeFrom.
     * @param nAmount
     * @param oCreatureToTakeFrom If this is not a valid creature, nothing will happen.
     * @param bDestroy If this is TRUE, the caller will not get the gold.  Instead, the
     * gold will be destroyed and will vanish from the game.
     */
    public native static synchronized void takeGoldFromCreature(int nAmount, NWObject oCreatureToTakeFrom, boolean bDestroy) throws NWNotInContextException;

    /**
     * * Returns TRUE if sStringToTest matches sPattern.
     */
    public native static synchronized int testStringAgainstPattern(String sPattern, String sStringToTest) throws NWNotInContextException;

    /**
     * The caller will perform a Melee Touch Attack on oTarget
     * This is not an action, and it assumes the caller is already within range of
     * oTarget
     * * Returns 0 on a miss, 1 on a hit and 2 on a critical hit
     */
    public native static synchronized int touchAttackMelee(NWObject oTarget, boolean bDisplayFeedback) throws NWNotInContextException;

    /**
     * The caller will perform a Ranged Touch Attack on oTarget
     * * Returns 0 on a miss, 1 on a hit and 2 on a critical hit
     */
    public native static synchronized int touchAttackRanged(NWObject oTarget, boolean bDisplayFeedback) throws NWNotInContextException;

    /**
     * Convert nTurns into a number of seconds
     * A turn is always 60.0 seconds
     */
    public native static synchronized float turnsToSeconds(int nTurns) throws NWNotInContextException;

    /**
     * This will cause a Player Creature to unpossess his/her familiar.  It will work if run
     * on the player creature or the possessed familiar.  It does not work in conjunction with
     * any DM possession.
     */
    public native static synchronized void unpossessFamiliar(NWObject oCreature) throws NWNotInContextException;

    /**
     * Create a vector with the specified values for x, y and z
     */
    public native static synchronized NWVector vector(float x, float y, float z) throws NWNotInContextException;

    /**
     * Get the magnitude of vVector; this can be used to determine the
     * distance between two points.
     * * Return value on error: 0.0f
     */
    public native static synchronized float vectorMagnitude(NWVector vVector) throws NWNotInContextException;

    /**
     * Normalize vVector
     */
    public native static synchronized NWVector vectorNormalize(NWVector vVector) throws NWNotInContextException;

    /**
     * Convert vVector to an angle
     */
    public native static synchronized float vectorToAngle(NWVector vVector) throws NWNotInContextException;

    /**
     * Set eEffect to be versus a specific alignment.
     * @param eEffect
     * @param nLawChaos ALIGNMENT_LAWFUL/ALIGNMENT_CHAOTIC/ALIGNMENT_ALL
     * @param nGoodEvil ALIGNMENT_GOOD/ALIGNMENT_EVIL/ALIGNMENT_ALL
     */
    public native static synchronized NWEffect versusAlignmentEffect(NWEffect eEffect, int nLawChaos, int nGoodEvil) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Set eEffect to be versus nRacialType.
     * @param eEffect
     * @param nRacialType RACIAL_TYPE_*
     */
    public native static synchronized NWEffect versusRacialTypeEffect(NWEffect eEffect, int nRacialType) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Set eEffect to be versus traps.
     */
    public native static synchronized NWEffect versusTrapEffect(NWEffect eEffect) throws NWNotInContextException, NWInvalidEffectException;

    /**
     * Does a Will Save check for the given DC
     * @param oCreature
     * @param nDC Difficulty check
     * @param nSaveType SAVING_THROW_TYPE_*
     * @param oSaveVersus
     * Returns: 0 if the saving throw roll failed
     * Returns: 1 if the saving throw roll succeeded
     * Returns: 2 if the target was immune to the save type specified
     * Note: If used within an Area of Effect Object Script (On Enter, OnExit, OnHeartbeat), you MUST pass
     * GetAreaOfEffectCreator() into oSaveVersus!!
     */
    public native static synchronized int willSave(NWObject oCreature, int nDC, int nSaveType, NWObject oSaveVersus) throws NWNotInContextException;

    /**
     * Write sLogEntry as a timestamped entry into the log file
     */
    public native static synchronized void writeTimestampedLogEntry(String sLogEntry) throws NWNotInContextException;

    /**
     * Convert fYards into a number of meters.
     */
    public native static synchronized float yardsToMeters(float fYards) throws NWNotInContextException;

    /**
     * Get all currently online players.
    **/
    public native static synchronized NWObject[] getPCs() throws NWNotInContextException;

    /**
     * Get all effects on the given NWObject.
    **/
    public native static synchronized NWEffect[] getEffects(NWObject oWithEffects) throws NWNotInContextException;

    /**
     * Get all itemproperties on the given NWObject.
    **/
    public native static synchronized NWItemProperty[] getItemProperties(NWObject oItem) throws NWNotInContextException;

    /**
     * Get all items in the given NWObjects inventory.
    **/
    public native static synchronized NWObject[] getItemsInInventory(NWObject oWithInventory) throws NWNotInContextException;

    /**
     * Get all objects in the given area.
    **/
    public native static synchronized NWObject[] getObjectsInArea(NWObject oArea) throws NWNotInContextException;

    /**
     * Get all objects in the given shape.
     * @param nShape SHAPE_*
     * @param fSize
	 * -> If nShape == SHAPE_SPHERE, this is the radius of the sphere
     * -> If nShape == SHAPE_SPELLCYLINDER, this is the length of the cylinder
     * Spell Cylinder's always have a radius of 1.5m.
     * -> If nShape == SHAPE_CONE, this is the widest radius of the cone
     * -> If nShape == SHAPE_SPELLCONE, this is the length of the cone in the
     * direction of lTarget. Spell cones are always 60 degrees with the origin
     * at OBJECT_SELF.
     * -> If nShape == SHAPE_CUBE, this is half the length of one of the sides of
     * the cube
     * @param lTarget This is the centre of the effect, usually GetSpellTargetLocation(),
     * or the end of a cylinder or cone.
     * @param bLineOfSight This controls whether to do a line-of-sight check on the
     * object returned. Line of sight check is done from origin to target object
     * at a height 1m above the ground
     * (This can be used to ensure that spell effects do not go through walls.)
     * @param nObjectFilter This allows you to filter out undesired object types, using
     * bitwise "or".
     * For example, to return only creatures and doors, the value for this
     * parameter would be OBJECT_TYPE_CREATURE | OBJECT_TYPE_DOOR
     * @param vOrigin This is only used for cylinders and cones, and specifies the
     * origin of the effect(normally the spell-caster's position).
    **/
    public native static synchronized NWObject[] getObjectsInShape(int nShapeType, float fSize, NWLocation lTarget, boolean bLineOfSight, int nObjectFilter, NWVector vOrigin) throws NWNotInContextException;

    /**
     * Returns all members of the given objects faction.
    */
    public native static synchronized NWObject[] getFactionMembers(NWObject oMemberOf, boolean bPCOnly) throws NWNotInContextException;

    /**
     * Get all objects within within oPersistentObject.
     * @param oPersistentObject
     * @param nResidentObjectType OBJECT_TYPE_*
     * @param nPersistentZone PERSISTENT_ZONE_ACTIVE. [This could also take the value
     * PERSISTENT_ZONE_FOLLOW, but this is no longer used.]
    **/
    public native static synchronized NWObject[] getObjectsInPersistentObject(NWObject oPersistentObject, int nResidentObjectType, int nPersistentZone) throws NWNotInContextException;
}