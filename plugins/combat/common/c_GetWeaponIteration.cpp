#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetWeaponIteration(CNWSCreature *cre, CNWSItem *it) {
    uint32_t result = 5;
    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    if ( !props ) { return result; }

    uint32_t base   = it ? it->it_baseitem : BASE_ITEM_GLOVES;
    uint32_t wpn    = combat.baseitem_to_weapon(base);

    int32_t style = nwn_GetLocalInt(&cre->obj.obj_vartable,
                                    "pc_style_fighting");

    int32_t is_monk = nwn_Get2daInt(props, "monk", wpn);
    auto monk = CanUseClassAbilities(cre, CLASS_TYPE_MONK);
    if ( monk.first && is_monk > 0 && monk.second >= is_monk ) { 
        result = 3;
    }
    else if ( style == 2 && GetIsWeaponKensei(cre, it) ) {
        result = 3;
    }

    return result;
}
