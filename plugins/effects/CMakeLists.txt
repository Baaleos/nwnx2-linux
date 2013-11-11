include_directories(. ../../talib)

set_property(SOURCE ../../talib/nwnsyms.s PROPERTY LANGUAGE C)

# Add the user's chosen flags to the build for this directory.
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O2 -std=gnu++0x -Wno-return-type")

add_module(effects NWNXEffects plugin-effects
    funcs/f_GetEffectImmunity
    funcs/f_LogEffects
    funcs/f_SendEffects
    funcs/f_SetEffectHandler 

    HandleFunc
    hook_funcs
    hooks/h_CExoArrayList_CGameEffect_Insert
    hooks/h_ExecuteCommandApplyEffectOnObject
    hooks/h_OnEffectApplied
    hooks/h_OnEffectRemoved
    hooks/h_RemoveBadEffects

    local/l_GetEffectImmunity
    local/l_GetEffectString

    ../../talib/nwn/all
    ../../talib/accumulator/all
    ../../talib/effects/creation
)

target_link_libraries(effects rt)
