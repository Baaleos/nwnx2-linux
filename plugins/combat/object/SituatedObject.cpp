#include "SituatedObject.h"
#include <utility>

// Defense
void SituatedObject::doDamageMods(DamageResult &res) {
    
}

int32_t SituatedObject::doDamageImmunity(CNWSCreature *vs, int32_t amount,
                                         uint16_t dmg_flags, bool no_feedback) {
    return amount;
}

int32_t SituatedObject::doDamageReduction(CNWSCreature *vs, int32_t dmg,
                                          uint8_t dmg_power, bool no_feedback) {
    return dmg;
}

int32_t SituatedObject::doDamageResistance(CNWSCreature *vs, int32_t amount,
                                           uint16_t dmg_flags,
                                           bool no_feedback) {
    return amount;
}

SaveResult SituatedObject::doSavingThrow(uint32_t save_type, uint32_t dc,
                                         uint32_t save_vs_type, const VersusInfo& vs,
                                         bool feedback) {
    return std::make_pair(0, nullptr);
}

uint32_t SituatedObject::getArmorClass(bool dexed, uint32_t situ_flags, bool is_touch) {
    return 0;
}

int32_t SituatedObject::getConcealment(bool is_ranged) {
    return 0;
}

int32_t SituatedObject::getHPCurrent(bool use_temp) {
    return use_temp
        ? obj_->obj_hp_cur + obj_->obj_hp_temp
        : obj_->obj_hp_cur;
}

int32_t SituatedObject::getHPMax() {
    return obj_->obj_hp_max;
}

uint32_t SituatedObject::getHardness() {
    uint32_t result = 0;

    if ( obj_->obj_type == OBJECT_TYPE_DOOR ) {
        result = reinterpret_cast<CNWSDoor*>(obj_)->door_hardness;
    }
    else if ( obj_->obj_type == OBJECT_TYPE_PLACEABLE ) {
        result = reinterpret_cast<CNWSPlaceable*>(obj_)->plc_hardness;
    }
    
    return result;
}

int32_t SituatedObject::getSave(uint32_t save, uint32_t save_vs,
                                Attribute::selector select) {
    return 0;
}

bool SituatedObject::isImmune(uint32_t type) {
    return false;
}

void SituatedObject::modifyDamageImmunity(uint32_t dmg_type, int32_t amount) {

}

