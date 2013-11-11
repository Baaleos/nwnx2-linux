set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

add_definitions( 
    -DTA
)

include_directories(
    .
    ../../talib)

set(COMMON_SRC
  common.h
  common/c_CanUseClassAbilities
  common/c_GetAttackTypeFromEquipNum
  common/c_GetBaseArmorClass
  common/c_GetBaseConcealment
  common/c_GetBaseSoak
  common/c_GetCombatModeMod
  common/c_GetCombatModFavoredEnemy
  common/c_GetCombatModSituation
  common/c_GetDualWieldPenalty
  common/c_GetEquipNumFromAttackType
  common/c_GetFavoredEnemyMask
  common/c_GetIsMonkWeapon
  common/c_GetIsRangedWeapon
  common/c_GetIsUnarmedWeapon
  common/c_GetIsWeaponIntuitable
  common/c_GetIsWeaponLight
  common/c_GetIsWeaponKensei
  common/c_GetSavingThrowVsBonus
  common/c_GetSkillFeatBonus
  common/c_GetTargetState
  common/c_GetTrainingVsMask
  common/c_GetUnarmedBaseDamage
  common/c_GetWeaponAmmoDamage
  common/c_GetWeaponAttackAbility
  common/c_GetWeaponAttackBonus
  common/c_GetWeaponBaseDamage
  common/c_GetWeaponDamage
  common/c_GetWeaponDamageAbility
  common/c_GetWeaponCritDamage
  common/c_GetWeaponCritMultiplier
  common/c_GetWeaponCritRange
  common/c_GetWeaponIteration
  common/c_GetWeaponPower)

set(FUNCS_SRC
  handle_request
  funcs.h
  funcs/f_DumpCombatMods
  funcs/f_GetABVersus
  funcs/f_GetACVersus
  funcs/f_GetCombatInfo
  funcs/f_GetMaxHitPoints
  funcs/f_GetSkillRank
  funcs/f_RegisterModeCombatMod
  funcs/f_SendCombatInfo
  funcs/f_SendDamageRoll)

set(HANDLES_SRC
  handles.h
  handles/hndl_EffectEvent
  handles/hndl_Event
  handles/hndl_ItemPropEvent
  handles/hndl_PluginsLoaded)

set(HOOKS_SRC
  hooks.h
  hook_functions
  hooks/h_AddAttackOfOpportunity
  hooks/h_GetCreatureDamageDice
  hooks/h_GetCreatureDamageDie
  hooks/h_DoDamageImmunity
  hooks/h_DoDamageReduction
  hooks/h_DoDamageResistance
  hooks/h_EquipItem
  hooks/h_ExecuteCommandDoTouchAttack
  hooks/h_GetArmorClass
  hooks/h_GetArmorClassVersus
  hooks/h_GetCriticalHitMultiplier
  hooks/h_GetCriticalHitRoll
  hooks/h_GetEffectImmunity
  hooks/h_GetIsFlurryWeapon
  hooks/h_GetMaxHitpoints
  hooks/h_GetMeleeAttackBonus
  hooks/h_GetMeleeDamageBonus
  hooks/h_GetRangedAttackBonus
  hooks/h_GetRangedDamageBonus
  hooks/h_GetSkillRank
  hooks/h_GetTotalEffectBonus
  hooks/h_GetUnarmedDamageDice
  hooks/h_GetUnarmedDamageDie
  hooks/h_GetWeaponAttackType
  hooks/h_GetWeaponFinesse
  hooks/h_InitializeNumberOfAttacks
  hooks/h_ResolveDamageShields
  hooks/h_ResolveMeleeAttack
  hooks/h_ResolveRangedAttack
  hooks/h_SavingThrowRoll
  hooks/h_UnequipItem
)

set(OBJECT_SRC
  object/Creature.cpp
  object/Creature.h
  object/CreatureOffense.cpp
  object/CreatureOffense.h
  object/CreatureDefense.cpp
  object/CreatureDefense.h
  object/SituatedObject.cpp
  object/SituatedObject.h
)

set(COMBAT_SRC
  combat/Attack.cpp
  combat/Attack.h
  combat/AttackData.cpp
  combat/AttackData.h
)

add_module(combat NWNXCombat plugin-combat
  ${COMMON_SRC}
  ${FUNCS_SRC}
  ${HANDLES_SRC}
  ${HOOKS_SRC}
  ${OBJECT_SRC}
  ${COMBAT_SRC}

  ../../talib/nwn/all
  ../../talib/accumulator/all
  ../../talib/effects/creation

)

target_link_libraries(combat rt)

