package org.baaleos.systems.lang;

import java.util.Hashtable;

import org.nwnx.nwnx2.jvm.constants.Base;

public class InhumanLanguage extends AncientLanguage {

	public InhumanLanguage(){
		
	}
	
	private Hashtable<String,String> map;
	private Hashtable<String,String> wordMap;
	@Override
	public Hashtable<String, String> translationMap() {
		// TODO Auto-generated method stub
		if(map == null){
			map = super.translationMap();
			
			map.put("a", "ae");
			map.put("b", "t");
			map.put("w", "tsa");
			map.put("x", "ux");
			map.put("y", "yim");
			map.put("z", "zhu");
			map.put(" ", " ");
		}
		return map;
	}

	@Override
	public Hashtable<String, String> translationWordMap() {
		// TODO Auto-generated method stub
		if(wordMap == null){
			wordMap = super.translationWordMap();
			wordMap.put("god", "dios");
			wordMap.put("death", "fries");
			wordMap.put("faith", "fath");
			wordMap.put("love", "lonor");
			wordMap.put("danger", "kreis");
			wordMap.put("follow", "yuno");
			wordMap.put("me", "ae");
			wordMap.put("my", "aer");
			wordMap.put("us", "ares");
			wordMap.put("time", "tempora");
			wordMap.put("second", "cecus");
			wordMap.put("minute", "moni");
			wordMap.put("hour", "aria");
			wordMap.put("day", "solo");
			wordMap.put("week", "naga");
			wordMap.put("month", "montio");
			wordMap.put("year", "aries");
			wordMap.put("stand", "sto");
			wordMap.put("down", "'rio");
			wordMap.put("up", "uni'at");
			wordMap.put("snow", "fell'ra");
			wordMap.put("fleece", "risa");
			wordMap.put("lamb", "meepe'");
			wordMap.put("little", "minam");
			wordMap.put("white", "wies");
			wordMap.put("potion", "pria");
			wordMap.put("evil", "interu");
			wordMap.put("good", "rasia");
			wordMap.put("dragon", "draco");
			wordMap.put("draconic", "dracous");
			wordMap.put("elven", "fa'ryn");
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
			wordMap.put("darkness", "nykto");
			wordMap.put("light", "lumo");
			wordMap.put("shine", "louma");
			wordMap.put("name", "noma");
			wordMap.put("is", "sa");
			wordMap.put("heal", "recoupe");
			wordMap.put("kill", "exequay");
			wordMap.put("you", "ouia");
			wordMap.put("power","exios");
			
		}
		return wordMap;
	}
}
