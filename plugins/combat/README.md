## nwnx_combat

This plugin is a nearly complete replacement of the NWN combat
engine.  It's not meant as a replacement per se for nwnx\_weapons and
nwnx\_defense, tho it is incompatible with and replaces the functions
of both.  The purpose and goal of this plugin is to give people
complete control over the combat engine and so would require
programming modifications in C++.  So nwnx\_weapon and nwnx\_defense
are more user friendly from NWScript.

## Status
* Currently still in alpha stage.

## Dependencies:
* nwnx_effects
* nwnx_items
* A couple new 2da files.
* ???

## Incompatiblities:
* nwnx\_defense and nwnx\_weapons are both incompatible, since
  nwnx_combat subsumes the featues both.

## Differences:
* All OnHitCastSpell properties are applied from a weapon, armor,
  shield when a hit occurs, rather than the default behavior that only
  uses one (the first that it finds).
* The plugin currently does not support effects versus race,
  alignment, etc, etc.  This is a deliberate, but not unchangeable,
  choice.
* All weapon damages on an item and EffectDamageIncrease effects stack and
  are included in the damage roll.  EffectDamageIncrease of
  nonphysical types are not 'unblockable' as they are in the NWN engine.
  If such a feature is desireable, it probably wouldn't be hard to add
  or return the behavior to defaul.
* Probably many more...
