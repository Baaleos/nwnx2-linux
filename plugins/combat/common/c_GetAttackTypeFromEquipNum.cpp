#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetAttackTypeFromEquipNum(uint32_t equip_num) {
    switch(equip_num) {
    default: return ATTACK_TYPE_MISC;
    case 0: return ATTACK_TYPE_ONHAND;
    case 1: return ATTACK_TYPE_OFFHAND;
    case 2: return ATTACK_TYPE_UNARMED;
    case 3: return ATTACK_TYPE_CWEAPON1;
    case 4: return ATTACK_TYPE_CWEAPON2;
    case 5: return ATTACK_TYPE_CWEAPON3;
    }
}
