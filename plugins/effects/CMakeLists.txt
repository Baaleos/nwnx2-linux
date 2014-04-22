include_directories(. ../../talib)

# Add the user's chosen flags to the build for this directory.
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

add_module(effects NWNXEffects plugin-effects
    funcs/f_LogEffects
    funcs/f_SendEffects

    HandleFunc
    hook_funcs
    hooks/h_CExoArrayList_CGameEffect_Insert
    hooks/h_ExecuteCommandApplyEffectOnObject
    hooks/h_OnApplyModifyNumAttacks
    hooks/h_OnRemoveModifyNumAttacks
    hooks/h_RemoveBadEffects

    local/l_GetEffectString

    ../../talib/nwn/all
)

target_link_libraries(effects rt)
