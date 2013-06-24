int GetMaxLevelLimit ();
int SetMaxLevelLimit (int nLevel); 
int LevelDown(object oPC);
int LevelUp(object oPC);

void DumpSpells(object oCreature){
    SetLocalString(oCreature, "NWNX!LEVELS!DUMPSPELLS", "none");
}

int GetMaxLevelLimit () {
    SetLocalString(GetModule(), "NWNX!LEVELS!GETMAXLEVELLIMIT", "none");
    return StringToInt(GetLocalString(GetModule(), "NWNX!LEVELS!GETMAXLEVELLIMIT"));
}

int SetMaxLevelLimit (int nLevel) {
    SetLocalString(GetModule(), "NWNX!LEVELS!SETMAXLEVELLIMIT", IntToString(nLevel));
    return StringToInt(GetLocalString(GetModule(), "NWNX!LEVELS!SETMAXLEVELLIMIT"));
}

int LevelDown(object oPC){
    SetLocalString(oPC, "NWNX!LEVELS!LEVELDOWN", "1");
    return StringToInt(GetLocalString(oPC, "NWNX!LEVELS!LEVELDOWN"));
}

int LevelUp(object oPC){
    SetLocalString(oPC, "NWNX!LEVELS!LEVELUP", "  ");
    return StringToInt(GetLocalString(oPC, "NWNX!LEVELS!LEVELUP"));
}