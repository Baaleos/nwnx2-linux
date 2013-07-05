#include "NWNXCombat.h"

extern CNWNXCombat combat;

DamageAmount GetWeaponBaseDamage(CNWSCreature *cre, CNWSItem *it) {    
    C2DA *props = nwn_GetCached2da(WEAPON_PROP_2DA);
    C2DA *feat_2da = nwn_GetCached2da(WEAPON_FEAT_2DA);
    if ( !props || !feat_2da) { return DamageAmount(); }

    int base    = it->it_baseitem;
    int wpn     = combat.baseitem_to_weapon(base);
    int dmgtype = nwn_Get2daInt(props, "BaseDamage", wpn);

    int extra_type = GetIsRangedWeapon(it)
	? ITEM_PROPERTY_EXTRA_RANGED_DAMAGE_TYPE
	: ITEM_PROPERTY_EXTRA_MELEE_DAMAGE_TYPE;

    for ( size_t i = 0; i < it->it_passive_ip_len; ++i ) {
	CNWItemProperty *ip = reinterpret_cast<CNWItemProperty *>(&it->it_passive_ip[i]);
	if ( !ip ) { continue; }
	
	if ( ip->ip_type == extra_type ) {
	    int d = GetDamageFlagFromIPConst(ip->ip_subtype);
	    if ( d != -1 ) { dmgtype |= d; }
	}
    }

    auto roll = DiceRoll(nwn_Get2daInt(props, "Dice", wpn),
			 nwn_Get2daInt(props, "Sides", wpn),
			 nwn_Get2daInt(props, "Bonus", wpn));

    int feat, bonus = 0;
    if ( (feat = nwn_Get2daInt(feat_2da, "SpecLeg", wpn)) > 0 &&
	 nwn_GetHasFeat(cre->cre_stats, feat) ) {
	bonus = 18;
    }
    else if ( (feat = nwn_Get2daInt(feat_2da, "SpecSup", wpn)) > 0 &&
              nwn_GetHasFeat(cre->cre_stats, feat) ) {
	bonus = 12;
    }
    else if ( (feat = nwn_Get2daInt(feat_2da, "SpecEpic", wpn)) > 0 &&
              nwn_GetHasFeat(cre->cre_stats, feat) ) {
	bonus = 8;
    }
    else if ( (feat = nwn_Get2daInt(feat_2da, "Spec", wpn)) > 0 &&
              nwn_GetHasFeat(cre->cre_stats, feat) ) {
	bonus = 4;
    }
    roll.bonus += bonus;

#if TA
    auto canuse = CanUseClassAbilities(cre, CLASS_TYPE_WEAPON_MASTER);
    // WM Ki Strike replacement...
    if ( canuse.first ) {
        roll.bonus += canuse.second / 3;
    }

#endif // TA

    return DamageAmount(dmgtype, roll, false);
}
