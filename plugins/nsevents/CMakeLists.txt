include_directories(. ../../talib)

# Add the user's chosen flags to the build for this directory.
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

add_module(nsevents NWNXEvents plugin-events
    hook_funcs
    hooks/h_AddAttackActions
    hooks/h_AddCastSpellActions
    hooks/h_AIActionPickPocket
    hooks/h_CheckScript
    hooks/h_CNWSObject_dtor
    hooks/h_ExecuteCommand
    hooks/h_HandleReply
    hooks/h_PackCreatureIntoMessage
    hooks/h_PossessFamiliar
    hooks/h_ReportError
    hooks/h_SendServerToPlayerExamineGui_CreatureData
    hooks/h_SendServerToPlayerExamineGui_DoorData
    hooks/h_SendServerToPlayerExamineGui_ItemData
    hooks/h_SendServerToPlayerExamineGui_PlaceableData
    hooks/h_SendServerToPlayerQuickChatMessage
    hooks/h_SetPauseState
    hooks/h_SetPVPPlayerLikesMe
    hooks/h_UseFeat
    hooks/h_UseItem
    hooks/h_UseSkill
    hooks/h_ValidateCharacter

    ../../talib/nwn/all
)
