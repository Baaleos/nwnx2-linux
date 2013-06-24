#include "NWNXEvents.h"

extern CNWNXEvents events;

void Hook_SetPVPPlayerLikesMe(CNWSCreature *cre, nwn_objid_t vs, int state, int a) {
    if ( !cre || vs == OBJECT_INVALID ) { return; }

    events.Log(3, "SetPVPPlayerLikesMe: Object: %X, Versus: %X, State: %d, a: %d\n",
	       cre->obj.obj_id, vs, state, a);

    events.FireEvent(cre->obj.obj_id, 
		     EVENT_TYPE_PVP_STATE,
		     state,
		     vs);

    if ( events.event.bypass ) { return; }

    if ( cre->cre_pvp ) {
	for ( uint32_t i = 0; i < cre->cre_pvp->len; ++i ) {
	    CNWSPVPEntry& entry = cre->cre_pvp->data[i];
	    if ( entry.pvp_obj_id == vs ) {
		entry.pvp_like = state;
		break;
	    }
	}
    }

    CNWSCreature *cre_vs = nwn_GetCreatureByID(vs);

    if ( a == 1 && cre_vs && cre_vs->cre_pvp ) {
	for ( uint32_t i = 0; i < cre_vs->cre_pvp->len; ++i ) {
	    CNWSPVPEntry& entry = cre_vs->cre_pvp->data[i];
	    if ( entry.pvp_obj_id == vs ) {
		entry.pvp_like = state;
		break;
	    }
	}	
    }

    if ( state == 1 && cre_vs && !events.suppressForgetAggression ) {
	CNWSCreature__SetAssociatesToForgetAggression(cre_vs, cre->obj.obj_id);	
    }
}

/*
void __cdecl CNWSCreature::SetPVPPlayerLikesMe(CNWSCreature *a1, int a2, int a3, int a4)
{
  int v4; // ebx@1
  int i; // esi@2
  CNWSCreature *v6; // eax@8
  int v7; // ebx@9
  int j; // esi@10
  int v9; // eax@16

  v4 = a1->cre_pvp;
  if ( v4 )
  {
    for ( i = 0; i < *(v4 + 4); ++i )
    {
      if ( *CExoArrayList<CNWSPVPEntry>::__vc(v4, i) == a2 )
      {
        *(CExoArrayList<CNWSPVPEntry>::__vc(v4, i) + 8) = a3;
        break;
      }
    }
  }
  if ( a4 == 1 )
  {
    v6 = CServerExoApp::GetCreatureByGameObjectID(*(g_pAppManager + 1), a2);
    if ( v6 )
    {
      v7 = v6->cre_pvp;
      if ( v7 )
      {
        for ( j = 0; j < *(v7 + 4); ++j )
        {
          if ( *CExoArrayList<CNWSPVPEntry>::__vc(v7, j) == a1->obj.obj_id )
          {
            *(CExoArrayList<CNWSPVPEntry>::__vc(v7, j) + 8) = a3;
            break;
          }
        }
      }
    }
  }
  if ( a3 == 1 )
  {
    v9 = CServerExoApp::GetCreatureByGameObjectID(*(g_pAppManager + 1), a2);
    if ( v9 )
      CNWSCreature::SetAssociatesToForgetAggression(v9, a1->obj.obj_id);
  }
}
*/
