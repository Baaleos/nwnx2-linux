#include "NWNXCombat.h"

extern CNWNXCombat combat;

uint32_t GetEquipNumFromAttackType(uint32_t type) {
    switch(type) {
    default: return 0;
    case ATTACK_TYPE_ONHAND:   return 0;
    case ATTACK_TYPE_OFFHAND:  return 1;
    case ATTACK_TYPE_UNARMED:  return 2;
    case ATTACK_TYPE_CWEAPON1: return 3;
    case ATTACK_TYPE_CWEAPON2: return 4;
    case ATTACK_TYPE_CWEAPON3: return 5;
    }
}
