#include "NWNXCombat.h"

class AttackData {
    CNWSCombatAttackData *attack_;
	CNWSCreature         *attacker_;

public:
    AttackData(CNWSCombatAttackData *attack = nullptr,
			   CNWSCreature *attacker = nullptr)
		: attack_(attack),
		  attacker_(attacker) {}

    void addCCMessage(CNWCCMessageData *msg);
    void addEffect(CGameEffect *eff, uint32_t creator);
	void addOnHitCastSpells(CNWSCreature *attacker, 
							CNWSObject *target,
							CNWSItem *item,
							bool from_target);

	void addOnHitCastSpell(CNWSCreature *attacker, 
						   CNWSObject *target,
						   CNWItemProperty *ip,
						   uint32_t item_id,
						   bool from_target);

    void addVisual(uint32_t vfx, uint32_t creator);
    void clearSpecialAttack();
    void copyDamage(const DamageResult& dmg);
    void setAttackBonus(uint32_t ab, uint32_t roll);
    void setAttackData(CNWSCombatAttackData *attack, CNWSCreature *attacker) {
		attack_ = attack;
		attacker_ = attacker;
    }

    void setConcealment(uint32_t val);
    void setCoupDeGrace(bool val) {
		attack_->cad_coupdegrace = val;
    }
    void setCriticalResult(uint32_t threat, uint32_t result);
    void setDeflected(bool val);
    void setKillingBlow() {
		attack_->cad_killing_blow = 1;
    }
    void setMissedBy(uint32_t ab);
    void setResult(uint32_t res);
    void setSneaks(bool sneak, bool death);

    uint32_t getAttackBonus();
    uint32_t getAttackRoll();
    uint32_t getAttackType(); 
    int32_t getResult();
    int32_t getSpecialAttack();
    bool isCoupDeGrace();
    bool isCriticalHit();
    bool isHit();
    bool isRanged();
    bool isSneak();
    bool isSpecialAttack();
};
