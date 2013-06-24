#ifndef TALIB_ITEMPROP_LIGHT_H
#define TALIB_ITEMPROP_LIGHT_H

struct ItemPropertyLight {
    static int32_t apply(CNWItemProperty* ip, CNWSItem *item, CNWSCreature *cre,
                         int32_t version) {
        return 0;
    }

    static int32_t getCost(CNWItemProperty* ip,
                           int32_t version) {
        return 0;
    }

    static int32_t remove(CNWItemProperty* ip, CNWSItem *item, CNWSCreature *cre,
                          int32_t version) {
        return 0;
    }

    static std::string toString(CNWItemProperty* ip,
                                int32_t version) {
        C2DA *sp_tda = nwn_GetCached2da("iprp_lightcost");
        C2DA *cost_tda = nwn_GetCached2da("iprp_color");
        if ( !sp_tda || !cost_tda ) { return std::string(""); }

        std::stringstream ss;

        ss << "Light: ";
        char *s = nwn_GetStringByStrref(nwn_Get2daInt(sp_tda, "Name", ip->ip_cost_value));
        if (s) { ss << s; free(s); }
        else { ss << "Unkown"; }
	
        ss << ' ';

        s = nwn_GetStringByStrref(nwn_Get2daInt(cost_tda, "Name", ip->ip_param_value));
        if (s) { ss << s; free(s); }
        else { ss << "Unkown"; }

        return ss.str();
    }

    static void update(CNWItemProperty* ip,
                       int32_t version) {}
};

#endif // TALIB_ITEMPROP_LIGHT_H