set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

add_definitions( 
    -DTA
)

include_directories(
    .
    ../../talib)

add_module(combat NWNXCombat plugin-combat
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
    common/c_GetWeaponPower

    funcs/f_DumpCombatMods
    funcs/f_GetABVersus
    funcs/f_GetACVersus
    funcs/f_GetCombatInfo
    funcs/f_GetMaxHitPoints
    funcs/f_GetSkillRank
    funcs/f_RegisterModeCombatMod
    funcs/f_SendCombatInfo
    funcs/f_SendDamageRoll

    handles/hndl_EffectEvent
    handles/hndl_ItemPropEvent
    handles/hndl_PluginsLoaded

    hook_functions
    handle_request
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

    combat/Attack
    combat/AttackData

    object/Creature
    object/CreatureOffense
    object/CreatureDefense
    object/SituatedObject

    ../../talib/all
    ../../talib/accumulator/all
    ../../talib/effects/creation

)

target_link_libraries(combat rt)

