package org.test;

import org.baaleos.systems.lang.AncientLanguage;

public class unitTester {

	public static void main(String[] args){
		AncientLanguage a = new AncientLanguage();
		String s = a.toLanguage("Hello my name is Jonathan");
		System.out.println(s);;
	}
}
