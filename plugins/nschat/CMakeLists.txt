include_directories(. ../../talib)

# Add the user's chosen flags to the build for this directory.
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++0x -Wno-return-type")

add_module(nschat NWNXChat plugin-chat
    hook_funcs
    hooks/h_SendServerToPlayerCCMessage
    hooks/h_SendServerToPlayerChat_Party
    hooks/h_SendServerToPlayerChat_Shout
    hooks/h_SendServerToPlayerChat_Talk
    hooks/h_SendServerToPlayerChat_Tell
    hooks/h_SendServerToPlayerChatMessage
    local/l_GetIgnoreMask
    local/l_GetIsIgnoring

    ../../talib/nwn/all
)
