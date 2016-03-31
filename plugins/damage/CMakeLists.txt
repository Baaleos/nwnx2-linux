if (gperf)

add_gperf_target(DamageStrCmds DamageStr DAMAGE STR)
add_gperf_target(DamageObjCmds SDamageObj DAMAGE OBJ)

include_directories(.)
add_module(damage NWNXDamage plugin-damage
	DamageStrCmds DamageObjCmds

	funcs/f_GetCreator
	funcs/f_GetDuration
	funcs/f_GetDurationRemaining
	funcs/f_GetHasEffect
	funcs/f_GetInteger
	funcs/f_GetSpellId
	funcs/f_GetTrueType
	funcs/f_SetCreator
	funcs/f_SetInteger
	funcs/f_SetSpellId
	funcs/f_SetTrueType
	hooks/h_PushStruct
)

endif (gperf)
