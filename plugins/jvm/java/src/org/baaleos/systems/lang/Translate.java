package org.baaleos.systems.lang;

import java.util.ArrayList;
import java.util.Hashtable;

public class Translate {

	
	private static final Hashtable<String,ILanguage> definedLanguages = new Hashtable<String,ILanguage>();
	public static void setupTranslations(){
		
		definedLanguages.put("ancient",new AncientLanguage());
		definedLanguages.put("inhuman",new InhumanLanguage());
		definedLanguages.put("spanish",new SpanishLanguage());
		
		
		
		
	}
	
	public static String toLanguage(String text, String language){
		return definedLanguages.get(language.toLowerCase()).toLanguage(text);
	}
	
	public static String toEnglish(String text, String UsingLanguage){
		
		return definedLanguages.get(UsingLanguage.toLowerCase()).toEnglish(text);
		
	}
	
}
