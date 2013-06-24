#include "NWNXCombat.h"
#include <sstream>
#include "talib/util/timer.h"

extern CNWNXCombat combat;

void Func_SendDamageRoll(CGameObject *ob, char *value) {
    uint32_t equip, crit;

    auto c = combat.get_creature(ob->id);
    if ( c &&
	 sscanf(value, "%d %d", &equip, &crit) == 2 ) {

	uint64_t start = ClockGetTime();

	DamageResult d = c->offense.getDamageVersus(equip);
	if ( crit ) {
	    crit = c->offense.getCritMultiplier(equip);
	}
	else { 
	    crit = 1;
	}

	d.roll(crit);
	uint64_t stop = ClockGetTime();
	combat.Log(3, "Damage roll Time: %d\n", stop-start);

	std::stringstream ss;
	ss << "Damages:\n";
	for ( size_t i = 0; i < 13; ++i ) {
	    ss << "    " 
	       << DamageIndexToString(i)
	       << ": " 
	       << d.damages[i]
	       << '\n';
	}
	ss << "Total: " << d.get_total();

	std::string out = ss.str();
	if ( !out.empty() ) {
	    nwn_SendMessage(5, ob->id, out.c_str(), ob->id);
	}
    }

	 
}
