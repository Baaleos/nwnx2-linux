package org.baaleos.systems.lang;

import java.util.Hashtable;

public interface ILanguage {

	
	public Hashtable<String, String> translationMap();
	public Hashtable<String, String> translationWordMap();
	public String toLanguage(String s);
	public String toEnglish(String s);
}
