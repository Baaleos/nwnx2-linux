#ifndef _NWNX_COMBAT_COMBAT_H_
#define _NWNX_COMBAT_COMBAT_H_

#include "talib/util/timer.h"
#include "NWNXCombat.h"

extern CNWNXCombat combat;

template <typename AttackType>
void ResolveMeleeAttack(CNWSCreature *attacker, CNWSObject *target,
			int32_t attack_count, int32_t anim) {

    if ( !target ) { return; }
    
    uint64_t start = ClockGetTime();
    AttackType attack(attacker, target);

    // If the target is a creature determine its state and any situational modifiers that
    // might come into play.  This only needs to be done once per attack group because
    // the values can't change.
    if ( target->obj_type == OBJECT_TYPE_CREATURE ) {
	attack.resolveTargetState();
	attack.resolveSituations();
    }

// TODO some book keeping...
    
    for ( int i = 0; i < attack_count; ++i ) {
	attack.resolvePreRoll();

	attack.resolveAttackRoll();
	if ( attack.isHit() ) {
	    attack.resolveDamage();
	    attack.resolvePostDamage(false);
	}

	CNWSCreature__ResolveMeleeAnimations(attacker, i, attack_count, target, anim);

	// Attempt to resolve a special attack one was
	// a) Used,  b) The attack is a hit.
	if ( attack.isSpecialAttack() && attack.isHit() ) {
	    attack.resolveSpecialAttack();
	}

	attack.update();
    }

    CNWSCreature__SignalMeleeDamage(attacker, target, attack_count);
    uint64_t stop = ClockGetTime();
    combat.Log(0, "Resolve Melee Attack Timer: Attacks %d, Time: %d\n", attack_count, stop-start);
}

// called by nwnx
template <typename AttackType>
void ResolveRangedAttack(CNWSCreature *attacker, CNWSObject *target,
			 int32_t attack_count, int32_t anim) {

    // Something would have to have gone wrong for this to be
    // true.
    if ( !target ) { return; }
    
    uint64_t start = ClockGetTime();
    AttackType attack(attacker, target);

    // Attack count can be modified if, say, a creature only has less arrows left than attacks
    // or none at all.
    attack_count = attack.resolveAmmo(attack_count);

    if ( attack_count == 0 ) {
	attack.resolveNoAmmo();
	return;
    }
    
    // If the target is a creature detirmine it's state and any situational modifiers that
    // might come into play.  This only needs to be done once per attack group because
    // the values can't change.
    if ( target->obj_type == OBJECT_TYPE_CREATURE ) {
	attack.resolveTargetState();
	attack.resolveSituations();
    }

    for ( int i = 0; i < attack_count; ++i ) {
	attack.resolvePreRoll();

	attack.resolveAttackRoll();
	if ( attack.isHit() ) {
	    attack.resolveDamage();
	    attack.resolvePostDamage(true);
	}
	else {
	    CNWSCreature__ResolveRangedMiss(attacker, target);
	}
	CNWSCreature__ResolveRangedAnimations(attacker, target, anim);

	// Attempt to resolve a special attack one was
	// a) Used,  b) The attack is a hit.
	if ( attack.isSpecialAttack() && attack.isHit() ) {
	    attack.resolveSpecialAttack();
	}

	attack.update();
    }
    
    CNWSCreature__SignalRangedDamage(attacker, target, attack_count);
    attack.resolveAmmo(attack_count, true);
    
    uint64_t stop = ClockGetTime();
    combat.Log(0, "Resolve Range Attack Timer: Attacks %d, Time: %d\n", attack_count, stop-start);
}

#endif // _NWNX_COMBAT_COMBAT_H_
