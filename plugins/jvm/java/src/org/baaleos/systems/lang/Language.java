package org.baaleos.systems.lang;

import java.util.Hashtable;

public class Language implements ILanguage {

	@Override
	public Hashtable<String, String> translationMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toLanguage(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean isUpperCase(char ch) {
	    return ch >= 'A' && ch <= 'Z';
	}
}
