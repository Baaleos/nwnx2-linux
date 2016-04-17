package org.baaleos.systems.lang;

import java.util.Hashtable;

public interface ILanguage {

	
	public Hashtable<String, String> translationMap();
	public String toLanguage(String s);
}
