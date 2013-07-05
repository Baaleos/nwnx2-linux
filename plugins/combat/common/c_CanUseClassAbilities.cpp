#include "NWNXCombat.h"

extern CNWNXCombat combat;

std::pair<bool, int32_t> CanUseClassAbilities(CNWSCreature *cre, uint32_t cls) {
    CNWSCreatureStats *stats = cre->cre_stats;
    CNWSItem* it;
    int32_t level = nwn_GetLevelByClass(stats, cls);

    if ( cls == CLASS_TYPE_WEAPON_MASTER ) {
        if ( level <= 0 ) { return std::make_pair(false, 0); }

        C2DA *feat_2da = nwn_GetCached2da(WEAPON_FEAT_2DA);
        if ( !feat_2da ) { return std::make_pair(false, 0); }
        
        uint32_t base;
        it = nwn_GetItemInSlot(cre, EQUIPMENT_SLOT_RIGHTHAND);
        if ( !it ) {
            base = BASE_ITEM_GLOVES;
        }
        else { 
            base = it->it_baseitem;
        }

        uint32_t wpn  = combat.baseitem_to_weapon(base);
        int feat = nwn_Get2daInt(feat_2da, "Choice", wpn);

        bool result = ( feat >= 0 && nwn_GetHasFeat(stats, feat) );
        
        return std::make_pair(result, level);
    }
    else if ( cls == CLASS_TYPE_ARCANE_ARCHER ) {
        if ( level <= 0 ) { return std::make_pair(false, 0); }

        it = nwn_GetItemInSlot(cre, EQUIPMENT_SLOT_RIGHTHAND);

        bool result = ( it &&
                        ( it->it_baseitem == BASE_ITEM_LONGBOW ||
                          it->it_baseitem == BASE_ITEM_SHORTBOW ) );

        return std::make_pair(result, level);
    }
    else if ( cls == CLASS_TYPE_MONK ) {
        bool result = ( level > 0                              &&
                        stats->cs_ac_armour_base == 0 &&
                        stats->cs_ac_shield_base == 0 );
        return std::make_pair(result, level);
    }
    else if ( cls == CLASS_TYPE_RANGER ) {
        bool result = false;

        if ( stats->cs_ac_armour_base >= 1 &&
             stats->cs_ac_armour_base <= 3 &&
             stats->cs_ac_shield_base == 0 &&
             level > 1 ) {
            
            result = true;

            it = nwn_GetItemInSlot(cre, EQUIPMENT_SLOT_RIGHTHAND);

            switch (it != NULL ? it->it_baseitem : -1) {
            default: result = true; break;

            case BASE_ITEM_DIREMACE:
            case BASE_ITEM_DOUBLEAXE:
            case BASE_ITEM_DWARVENWARAXE:
            case BASE_ITEM_GREATAXE:
            case BASE_ITEM_GREATSWORD:
            case BASE_ITEM_HALBERD:
            case BASE_ITEM_HEAVYFLAIL:
            case BASE_ITEM_KAMA:
            case BASE_ITEM_QUARTERSTAFF:
            case BASE_ITEM_SCYTHE:
            case BASE_ITEM_TWOBLADEDSWORD:
            case BASE_ITEM_CEP_DOUBLEPICK:
            case BASE_ITEM_CEP_DOUBLESCIMITAR:
            case BASE_ITEM_CEP_FALCHION:
            case BASE_ITEM_CEP_GOAD:
            case BASE_ITEM_CEP_MAUL:
            case BASE_ITEM_CEP_MERCURIALGREATSWORD:
            case BASE_ITEM_CEP_MERCURIALLONGSWORD:
            case BASE_ITEM_CEP_NUNCHAKU:
                result = false;
                break;
            }

            if ( result ) {
                it = nwn_GetItemInSlot(cre, EQUIPMENT_SLOT_LEFTHAND);
                result = !(it != NULL && it->it_baseitem == BASE_ITEM_DWARVENWARAXE);
            }
        }
        return std::make_pair(result, level);
    }
    return std::make_pair(true, level);
}
