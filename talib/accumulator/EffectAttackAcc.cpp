#include "EffectAttackAcc.h"

bool EffectAttackAcc::is_valid(CGameEffect *eff) {
    int32_t atk_type  = eff->eff_integers[1];
    int32_t race      = eff->eff_integers[2];
    int32_t lawchaos  = eff->eff_integers[3];
    int32_t goodevil  = eff->eff_integers[4];

    bool valid = atk_type == ATTACK_TYPE_MISC || atk_type == attack_type;

    if ( !valid ) { return false; }

    return ( ( lawchaos == 0 && race == 28 && goodevil == 0 ) ||
	     ( lawchaos == vs_.lawchaos &&
	       race == vs_.race         &&
	       goodevil == vs_.goodevil ) );
}

int32_t EffectAttackAcc::get_max(int32_t a, int32_t b){
    return std::max(a, b);
}

int32_t EffectAttackAcc::get_amount(CGameEffect *eff){
    return eff->eff_integers[0];
}

int32_t EffectAttackAcc::get_result(){
    if (!accumulated_) accumulate();
    if (resulted_) return result_;
	    
    int32_t total_bonus = 0;
    int32_t total_penalty = 0;

    for ( size_t i = 0; i < bonus_idx; ++i )
	total_bonus += bonus[i];

    for ( size_t i = 0; i < penalty_idx; ++i )
	total_penalty += penalty[i];

    resulted_ = true;
    result_ =  CLAMP<int32_t>(total_bonus - total_penalty, -20, 20);
    return total_bonus - total_penalty;
}

int32_t EffectAttackAcc::get_subtype(CGameEffect *eff) {
    return 0;
}