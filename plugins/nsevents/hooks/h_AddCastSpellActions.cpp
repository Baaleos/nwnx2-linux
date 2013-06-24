#include "NWNXEvents.h"

extern CNWNXEvents events;

int Hook_AddCastSpellActions(CNWSCreature *cre,
                              uint32_t nSpell,
                              signed int nClassIndex,
                              char nDomainLevel,
                              char nMetaMagic,
                              int a6,
                              Vector vTarget,
                              signed int oTarget,
                              int bTargeted,
                              int a12,
                              int a13,
                              char nProjectilePathType,
                              signed int bInstantSpell,
                              int a16,
                              char a17,
                              char a18)
{
    bool bypass = false;
    
    if (!events.scriptRun && cre){
        //Get spell id, metamagic, and class index
        int subtype = nSpell;
        subtype |= (nMetaMagic & 0xFF) << 16;
        subtype |= (nClassIndex & 0x07) << 24;
        // instant cast flag
        subtype |= (!!bInstantSpell) << 27;

        events.Log(2,
                   "CastSpell: oPC=%08lX, oTarget=%08lX, vTarget=(%f/%f/%f), nSpellId=%d, nMetaMagic=%d, nClassIndex=%d, nFlags=%d\n",
                   cre->obj.obj_id, oTarget, vTarget.x, vTarget.y,
                   vTarget.z, (subtype & 0xFFFF), ((subtype >> 16) & 0xFF),
                   ((subtype >> 24) & 0x07), ((subtype >> 27) & 0x0F));

        events.FireEvent(cre->obj.obj_id, EVENT_TYPE_CAST_SPELL, subtype, oTarget, vTarget);
    }

    if(!events.event.bypass)
        return CNWSCreature__AddCastSpellActions(cre, nSpell,  nClassIndex, nDomainLevel,
                                          nMetaMagic, a6,  vTarget,  oTarget,
                                          bTargeted, a12, a13, nProjectilePathType,
                                          bInstantSpell, a16, a17, a18);
}
