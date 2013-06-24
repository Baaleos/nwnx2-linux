#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetTargetState(CNWSCreature *attacker, CNWSObject *target) {
    uint32_t flags = 0;
    if ( !attacker || !target || 
	 !target->obj_type == OBJECT_TYPE_CREATURE ) { 
	return flags;
    }

    CNWSCreature *tar = reinterpret_cast<CNWSCreature *>(target);

    if ( CNWSCreature__GetBlind(tar) ) {
	flags |= COMBAT_TARGET_STATE_BLIND;
	combat.Log(3, "COMBAT_TARGET_STATE_BLIND\n");
    }

    if ( nwn_GetIsInvisible(attacker, target) ) {
	flags |= COMBAT_TARGET_STATE_ATTACKER_INVIS;
	combat.Log(3, "COMBAT_TARGET_STATE_ATTACKER_INVIS\n");
    }

    if ( nwn_GetIsInvisible(tar, &attacker->obj) ) {
	flags |= COMBAT_TARGET_STATE_INVIS;
	combat.Log(3, "COMBAT_TARGET_STATE_INVIS\n");
    }

    if ( !nwn_GetIsVisible(attacker, target->obj_id) ) {
	flags |= COMBAT_TARGET_STATE_ATTACKER_UNSEEN;
	combat.Log(3, "COMBAT_TARGET_STATE_ATTACKER_UNSEEN\n");
    }

    if ( !nwn_GetIsVisible(tar, attacker->obj.obj_id) ) {
	flags |= COMBAT_TARGET_STATE_UNSEEN;
	combat.Log(3, "COMBAT_TARGET_STATE_UNSEEN\n");
    }

    if ( target->obj_anim == 4  ||
	 target->obj_anim == 87 ||
	 target->obj_anim == 86 ) {
	flags |= COMBAT_TARGET_STATE_MOVING;
	combat.Log(3, "COMBAT_TARGET_STATE_MOVING\n");
    }

    if ( target->obj_anim == 36 ||
	 target->obj_anim == 33 ||
	 target->obj_anim == 32 ||
	 target->obj_anim == 7  ||
	 target->obj_anim == 5 ) {
	flags |= COMBAT_TARGET_STATE_PRONE;
	combat.Log(3, "COMBAT_TARGET_STATE_PRONE\n");
    }

    if ( tar->cre_state == 6 ) { 
	flags |= COMBAT_TARGET_STATE_STUNNED;
	combat.Log(3, "COMBAT_TARGET_STATE_STUNNED\n");
    }
    
    if ( nwn_GetFlanked(tar, attacker) ) {
	flags |= COMBAT_TARGET_STATE_FLANKED;
	combat.Log(3, "COMBAT_TARGET_STATE_FLANKED\n");
    }
    
    if ( nwn_GetFlatFooted(tar) ) {
	flags |= COMBAT_TARGET_STATE_FLATFOOTED;
	combat.Log(3, "COMBAT_TARGET_STATE_FLATFOOTED\n");
    }

    if ( tar->cre_state == 9 || tar->cre_state == 8 ) {
	flags |= COMBAT_TARGET_STATE_ASLEEP;
	combat.Log(3, "COMBAT_TARGET_STATE_ASLEEP\n");
    }

    return flags;
}
