NWNXCombat = {}

NWNXCombat.INFO_INVALID     = 0
NWNXCombat.INFO_ARMOR_CLASS = 0x1
NWNXCombat.INFO_HITPOINTS   = 0x2
NWNXCombat.INFO_CONCEALMENT = 0x4
NWNXCombat.INFO_RESISTANCE  = 0x8
NWNXCombat.INFO_REDUCTION   = 0x10
NWNXCombat.INFO_IMMUNITY    = 0x20


function NWNXCombat.InitializeTables()
   local mod = nwn.GetModule()
   mod:SetLocalString("NWNX!COMBAT!INIT_TABLES", " ")
end

function NWNXCombat.GetMaxHitPoints(obj)
   obj:SetLocalString("NWNX!COMBAT!GETMAXHITPOINTS", " ")
end

function NWNXCombat.GetSkillRank(obj, skill, vs, base)
   vs = vs and vs.id or nwn.OBJECT_INVALID.id
   base = base and 1 or 0

   obj:SetLocalString("NWNX!COMBAT!GETSKILLRANK", string.format("%d %x %d", skill, vs, base))
   return tonumber(obj:GetLocalString("NWNX!COMBAT!GETSKILLRANK"))
end

function NWNXCombat.Log(obj)
   obj:SetLocalString("NWNX!COMBAT!DUMBCOMBATMODS", " ")
end

function NWNXCombat.SendCombatInfo(obj)
   obj:SetLocalString("NWNX!COMBAT!GETCOMBATINFO", " ")
end

function NWNXCombat.SendCombatInfo(obj, flags)
   flags = flags or NWNXCombat.INFO_INVALID
   obj:SetLocalString("NWNX!COMBAT!SENDCOMBATINFO",
		      tostring(flags))
end

function NWNXCombat.Update(obj)
   obj:SetLocalString("NWNX!COMBAT!UPDATE", " ")
end
