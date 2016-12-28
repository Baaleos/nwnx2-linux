#ifndef NWNX_DAMAGE_H
#define NWNX_DAMAGE_H


#include "NWNXLib.h"

#ifdef __cplusplus
extern "C" {
#endif

int Hook_DamageEffectListHandler (CNWSEffectListHandler *pThis, CGameObject *ob, CGameEffect *effect, int iArg);

#ifdef __cplusplus
}

#include "NWNXBase.h"

class CNWNXDamage:public CNWNXBase {
  public:
    CNWNXDamage ();
    virtual ~ CNWNXDamage ();

    bool OnCreate (gline *nwnxConfig, const char *LogDir = NULL);
    char *OnRequest (char *gameObject, char *Request, char *Parameters);
    unsigned long OnRequestObject (char *gameObject, char *Request);

    // bool OnRelease  ();
};
#endif

#endif /* NWNX_FUNCS_H */

/* vim: set sw=4: */
