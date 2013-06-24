#include "NWNXSolstice.h"

extern lua_State *L;
extern CNWNXSolstice solstice;

static void local(CNWSObject *obj){
    if(!nl_pushfunction(L, "_SOL_REMOVE_CACHED_OBJECT"))
        return;

    // Push object ID.
    lua_pushinteger(L, obj->obj_id);
    
    if (lua_pcall(L, 1, 0, 0) != 0){
        solstice.Log(0, "Error: _SOL_REMOVE_CACHED_OBJECT : %s\n", lua_tostring(L, -1));
        return;
    }
}

void Hook_CNWSObject_dtor(CNWSObject *obj, int8_t a){
    local(obj);
    CNWSObject_dtor(obj, a);
}

