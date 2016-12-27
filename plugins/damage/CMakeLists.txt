if (gperf)

add_gperf_target(DamageStrCmds DamageStr DAMAGE STR)
add_gperf_target(DamageObjCmds DamageObj DAMAGE OBJ)

include_directories(.)
add_module(damage NWNXDamage plugin-damage
	HookFunc
	funcs/f_DoDamageCalc
	

)

endif (gperf)
