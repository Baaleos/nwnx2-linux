package org.test;

import org.baaleos.systems.lang.AncientLanguage;
import org.baaleos.systems.lang.Apertium;

public class unitTester {

	public static void main(String[] args){
		String spanish = Apertium.getTranslation("eng", "spa","Hello my name is Samuel", true);
		System.out.println(spanish);
	}
}
