package org.baaleos.systems.lang;

import java.util.Hashtable;

import org.nwnx.nwnx2.jvm.constants.Base;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SpanishLanguage extends Language {

	public SpanishLanguage(){
		
	}
	
	private Hashtable<String,String> map;
	private Hashtable<String,String> wordMap;
	@Override
	public Hashtable<String, String> translationMap() {
		// TODO Auto-generated method stub
		if(map == null){
			map = new Hashtable<String,String>();
			
		}
		return map;
	}

	@Override
	public Hashtable<String, String> translationWordMap() {
		// TODO Auto-generated method stub
		if(wordMap == null){
			wordMap = new Hashtable<String,String>();
			
		}
		return wordMap;
	}
	
	@Override
	public String toLanguage(String text){
		return Apertium.getTranslation("en", "es", text, false);	
	}
	
	@Override
	public String toEnglish(String s) {
		return Apertium.getTranslation("es", "en", s.replace("*", ""), false);
		//throw new NotImplementedException("");
	}
	
}
