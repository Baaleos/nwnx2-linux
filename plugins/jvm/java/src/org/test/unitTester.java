package org.test;

import java.util.Date;
import java.util.Random;

import org.baaleos.systems.lang.AncientLanguage;
import org.baaleos.systems.lang.Apertium;

public class unitTester {

	
	public static String randomPhrase(){
		int i = new Random().nextInt(10);
		String strReturn = "";
		switch(i){
		case 1:
			strReturn = "My name is Jonathan";
			break;
		case 2:
			strReturn = "Can I join your party";
			break;
		
		case 3:
			strReturn = "Where are you from?";
			break;
		case 4:
			strReturn = "Oh, I had no idea you were spanish?!";
			break;
		case 5:
			strReturn = "Do you know Juan Pablo Raba from Agents of Shield? I think he is well tasty.";
			break;
		case 6:
			strReturn = "I may be a little obsessed with him.";
			break;
		case 7:
			strReturn = "I just wish he would notice me!";
			break;
		case 8:
			strReturn = "He won't respond to my twitter or facebook posts. Its like he doesn't even see me.";
			break;
		case 9:
			strReturn = "Sometimes it makes me upset.";
			break;
		case 10:
			strReturn = "My therapist says I should try to not think about him.";
			break;
		}
		return strReturn;
	}
	
	public static void main(String[] args){
		Date dStart = new Date();
		for(int i=0;i<=100000;i++){
			Apertium.getTranslation("en", "es",randomPhrase(), true);
			//System.out.println(spanish);
		}
		Date dEnd = new Date();
		long time = dEnd.getTime()-dStart.getTime();
		System.out.println("Took "+(time/1000)+" seconds to complete");
		
	}
}
