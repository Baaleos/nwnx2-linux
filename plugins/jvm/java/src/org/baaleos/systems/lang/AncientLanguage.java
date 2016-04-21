package org.baaleos.systems.lang;

import java.util.Hashtable;

import org.nwnx.nwnx2.jvm.constants.Base;

public class AncientLanguage extends Language {

	public AncientLanguage(){
		
	}
	
	private Hashtable<String,String> map;
	private Hashtable<String,String> wordMap;
	@Override
	public Hashtable<String, String> translationMap() {
		// TODO Auto-generated method stub
		if(map == null){
			map = new Hashtable<String,String>();
			map.put("a", "ar");
			map.put("b", "be");
			map.put("c", "ka");
			map.put("d", "ded");
			map.put("e", "er");
			map.put("f", "efi");
			map.put("g", "gh'");
			map.put("h", "ohi");
			map.put("i", "ri");
			map.put("j", "gro");
			map.put("k", "sa");
			map.put("l", "le");
			map.put("m", "mo");
			map.put("n", "nag");
			map.put("o", "ios");
			map.put("p", "ps'");
			map.put("q", "qu");
			map.put("r", "io");
			map.put("s", "di");
			map.put("t", "ego");
			map.put("u", "umi");
			map.put("v", "va");
			map.put("w", "wis");
			map.put("x", "xen");
			map.put("y", "y'i");
			map.put("z", "zo");
			map.put(" ", " ");
		}
		return map;
	}

	@Override
	public Hashtable<String, String> translationWordMap() {
		// TODO Auto-generated method stub
		if(wordMap == null){
			wordMap = new Hashtable<String,String>();
			wordMap.put("hello", "londras");
			wordMap.put("god", "razhul");
			wordMap.put("death", "destati");
			wordMap.put("faith", "hesti");
			wordMap.put("love", "veni");
			wordMap.put("danger", "khorus");
			wordMap.put("follow", "kies");
			wordMap.put("me", "lus");
			wordMap.put("my", "ilyra");
			wordMap.put("us", "rous");
			wordMap.put("time", "tempo");
			wordMap.put("second", "secundo");
			wordMap.put("minute", "mina");
			wordMap.put("hour", "ara");
			wordMap.put("day", "cyclio");
			wordMap.put("week", "weisa");
			wordMap.put("month", "leisa");
			wordMap.put("year", "eara");
			wordMap.put("stand", "rano");
			wordMap.put("down", "'rua");
			wordMap.put("up", "tuos'a");
			wordMap.put("snow", "risa");
			wordMap.put("fleece", "flica");
			wordMap.put("lamb", "baba");
			wordMap.put("little", "mito");
			wordMap.put("white", "blanka");
			wordMap.put("potion", "potus");
			wordMap.put("evil", "infernus");
			wordMap.put("good", "agentum");
			wordMap.put("dragon", "dracarys");
			wordMap.put("draconic", "dracrye");
			wordMap.put("elven", "fa'ryn");
			wordMap.put("moon", "luna");
			wordMap.put("sun", "sola");
			wordMap.put("crystal", "krysta");
			wordMap.put("demon", "infernalis");
			wordMap.put("angel", "agris");
			wordMap.put("inside", "interius");
			wordMap.put("burning", "estuans");
			wordMap.put("with", "ira");
			wordMap.put("violent", "vehementi");
			wordMap.put("memory", "memoria");
			wordMap.put("no", "nolus");
			wordMap.put("yes", "axo");
			wordMap.put("teleport", "aporta");
			wordMap.put("portal", "portalis");
			wordMap.put("cursed", "axios");
			wordMap.put("sorry", "remorsa");
			wordMap.put("fear", "incurso");
			wordMap.put("darkness", "nyxiom");
			wordMap.put("light", "lumos");
			wordMap.put("shine", "lumo'ra");
			wordMap.put("name", "noma");
			wordMap.put("is", "sa");
			wordMap.put("heal", "recoupe");
			wordMap.put("kill", "exequay");
			wordMap.put("you", "ouia");
			wordMap.put("power","excidium");
		}
		return wordMap;
	}
}
