package org.nwnx.nwnx2.jvm.constants;

/**
 * This class contains all unique constants beginning with "BASE_ITEM".
 * Non-distinct keys are filtered; only the LAST appearing was
 * kept.
*/
public final class BaseItem {
  private BaseItem() {}

  public final static int AMULET = 19;
  public final static int ARMOR = 16;
  public final static int ARROW = 20;
  public final static int BASTARDSWORD = 3;
  public final static int BATTLEAXE = 2;
  public final static int BELT = 21;
  public final static int BLANK_POTION = 101;
  public final static int BLANK_SCROLL = 102;
  public final static int BLANK_WAND = 103;
  public final static int BOLT = 25;
  public final static int BOOK = 74;
  public final static int BOOTS = 26;
  public final static int BRACER = 78;
  public final static int BULLET = 27;
  public final static int CBLUDGWEAPON = 71;
  public final static int CLOAK = 80;
  public final static int CLUB = 28;
  public final static int CPIERCWEAPON = 70;
  public final static int CRAFTMATERIALMED = 109;
  public final static int CRAFTMATERIALSML = 110;
  public final static int CREATUREITEM = 73;
  public final static int CSLASHWEAPON = 69;
  public final static int CSLSHPRCWEAP = 72;
  public final static int DAGGER = 22;
  public final static int DART = 31;
  public final static int DIREMACE = 32;
  public final static int DOUBLEAXE = 33;
  public final static int DWARVENWARAXE = 108;
  public final static int ENCHANTED_POTION = 104;
  public final static int ENCHANTED_SCROLL = 105;
  public final static int ENCHANTED_WAND = 106;
  public final static int GEM = 77;
  public final static int GLOVES = 36;
  public final static int GOLD = 76;
  public final static int GREATAXE = 18;
  public final static int GREATSWORD = 13;
  public final static int GRENADE = 81;
  public final static int HALBERD = 10;
  public final static int HANDAXE = 38;
  public final static int HEALERSKIT = 39;
  public final static int HEAVYCROSSBOW = 6;
  public final static int HEAVYFLAIL = 35;
  public final static int HELMET = 17;
  public final static int INVALID = 256;
  public final static int KAMA = 40;
  public final static int KATANA = 41;
  public final static int KEY = 65;
  public final static int KUKRI = 42;
  public final static int LARGEBOX = 66;
  public final static int LARGESHIELD = 56;
  public final static int LIGHTCROSSBOW = 7;
  public final static int LIGHTFLAIL = 4;
  public final static int LIGHTHAMMER = 37;
  public final static int LIGHTMACE = 9;
  public final static int LONGBOW = 8;
  public final static int LONGSWORD = 1;
  public final static int MAGICROD = 44;
  public final static int MAGICSTAFF = 45;
  public final static int MAGICWAND = 46;
  public final static int MISCLARGE = 34;
  public final static int MISCMEDIUM = 29;
  public final static int MISCSMALL = 24;
  public final static int MISCTALL = 43;
  public final static int MISCTHIN = 79;
  public final static int MISCWIDE = 68;
  public final static int MORNINGSTAR = 47;
  public final static int POTIONS = 49;
  public final static int QUARTERSTAFF = 50;
  public final static int RAPIER = 51;
  public final static int RING = 52;
  public final static int SCIMITAR = 53;
  public final static int SCROLL = 54;
  public final static int SCYTHE = 55;
  public final static int SHORTBOW = 11;
  public final static int SHORTSPEAR = 58;
  public final static int SHORTSWORD = 0;
  public final static int SHURIKEN = 59;
  public final static int SICKLE = 60;
  public final static int SLING = 61;
  public final static int SMALLSHIELD = 14;
  public final static int SPELLSCROLL = 75;
  public final static int THIEVESTOOLS = 62;
  public final static int THROWINGAXE = 63;
  public final static int TORCH = 15;
  public final static int TOWERSHIELD = 57;
  public final static int TRAPKIT = 64;
  public final static int TRIDENT = 95;
  public final static int TWOBLADEDSWORD = 12;
  public final static int WARHAMMER = 5;
  public final static int WHIP = 111;

  public static String nameOf(int value) {
    if (value == 19) return "BaseItem.AMULET";
    if (value == 16) return "BaseItem.ARMOR";
    if (value == 20) return "BaseItem.ARROW";
    if (value == 3) return "BaseItem.BASTARDSWORD";
    if (value == 2) return "BaseItem.BATTLEAXE";
    if (value == 21) return "BaseItem.BELT";
    if (value == 101) return "BaseItem.BLANK_POTION";
    if (value == 102) return "BaseItem.BLANK_SCROLL";
    if (value == 103) return "BaseItem.BLANK_WAND";
    if (value == 25) return "BaseItem.BOLT";
    if (value == 74) return "BaseItem.BOOK";
    if (value == 26) return "BaseItem.BOOTS";
    if (value == 78) return "BaseItem.BRACER";
    if (value == 27) return "BaseItem.BULLET";
    if (value == 71) return "BaseItem.CBLUDGWEAPON";
    if (value == 80) return "BaseItem.CLOAK";
    if (value == 28) return "BaseItem.CLUB";
    if (value == 70) return "BaseItem.CPIERCWEAPON";
    if (value == 109) return "BaseItem.CRAFTMATERIALMED";
    if (value == 110) return "BaseItem.CRAFTMATERIALSML";
    if (value == 73) return "BaseItem.CREATUREITEM";
    if (value == 69) return "BaseItem.CSLASHWEAPON";
    if (value == 72) return "BaseItem.CSLSHPRCWEAP";
    if (value == 22) return "BaseItem.DAGGER";
    if (value == 31) return "BaseItem.DART";
    if (value == 32) return "BaseItem.DIREMACE";
    if (value == 33) return "BaseItem.DOUBLEAXE";
    if (value == 108) return "BaseItem.DWARVENWARAXE";
    if (value == 104) return "BaseItem.ENCHANTED_POTION";
    if (value == 105) return "BaseItem.ENCHANTED_SCROLL";
    if (value == 106) return "BaseItem.ENCHANTED_WAND";
    if (value == 77) return "BaseItem.GEM";
    if (value == 36) return "BaseItem.GLOVES";
    if (value == 76) return "BaseItem.GOLD";
    if (value == 18) return "BaseItem.GREATAXE";
    if (value == 13) return "BaseItem.GREATSWORD";
    if (value == 81) return "BaseItem.GRENADE";
    if (value == 10) return "BaseItem.HALBERD";
    if (value == 38) return "BaseItem.HANDAXE";
    if (value == 39) return "BaseItem.HEALERSKIT";
    if (value == 6) return "BaseItem.HEAVYCROSSBOW";
    if (value == 35) return "BaseItem.HEAVYFLAIL";
    if (value == 17) return "BaseItem.HELMET";
    if (value == 256) return "BaseItem.INVALID";
    if (value == 40) return "BaseItem.KAMA";
    if (value == 41) return "BaseItem.KATANA";
    if (value == 65) return "BaseItem.KEY";
    if (value == 42) return "BaseItem.KUKRI";
    if (value == 66) return "BaseItem.LARGEBOX";
    if (value == 56) return "BaseItem.LARGESHIELD";
    if (value == 7) return "BaseItem.LIGHTCROSSBOW";
    if (value == 4) return "BaseItem.LIGHTFLAIL";
    if (value == 37) return "BaseItem.LIGHTHAMMER";
    if (value == 9) return "BaseItem.LIGHTMACE";
    if (value == 8) return "BaseItem.LONGBOW";
    if (value == 1) return "BaseItem.LONGSWORD";
    if (value == 44) return "BaseItem.MAGICROD";
    if (value == 45) return "BaseItem.MAGICSTAFF";
    if (value == 46) return "BaseItem.MAGICWAND";
    if (value == 34) return "BaseItem.MISCLARGE";
    if (value == 29) return "BaseItem.MISCMEDIUM";
    if (value == 24) return "BaseItem.MISCSMALL";
    if (value == 43) return "BaseItem.MISCTALL";
    if (value == 79) return "BaseItem.MISCTHIN";
    if (value == 68) return "BaseItem.MISCWIDE";
    if (value == 47) return "BaseItem.MORNINGSTAR";
    if (value == 49) return "BaseItem.POTIONS";
    if (value == 50) return "BaseItem.QUARTERSTAFF";
    if (value == 51) return "BaseItem.RAPIER";
    if (value == 52) return "BaseItem.RING";
    if (value == 53) return "BaseItem.SCIMITAR";
    if (value == 54) return "BaseItem.SCROLL";
    if (value == 55) return "BaseItem.SCYTHE";
    if (value == 11) return "BaseItem.SHORTBOW";
    if (value == 58) return "BaseItem.SHORTSPEAR";
    if (value == 0) return "BaseItem.SHORTSWORD";
    if (value == 59) return "BaseItem.SHURIKEN";
    if (value == 60) return "BaseItem.SICKLE";
    if (value == 61) return "BaseItem.SLING";
    if (value == 14) return "BaseItem.SMALLSHIELD";
    if (value == 75) return "BaseItem.SPELLSCROLL";
    if (value == 62) return "BaseItem.THIEVESTOOLS";
    if (value == 63) return "BaseItem.THROWINGAXE";
    if (value == 15) return "BaseItem.TORCH";
    if (value == 57) return "BaseItem.TOWERSHIELD";
    if (value == 64) return "BaseItem.TRAPKIT";
    if (value == 95) return "BaseItem.TRIDENT";
    if (value == 12) return "BaseItem.TWOBLADEDSWORD";
    if (value == 5) return "BaseItem.WARHAMMER";
    if (value == 111) return "BaseItem.WHIP";
    return "BaseItem.(not found: " + value + ")";
  }

  public static String nameOf(float value) {
    return "BaseItem.(not found: " + value + ")";
  }

  public static String nameOf(String value) {
    return "BaseItem.(not found: " + value + ")";
  }
}
