package org.test;

import java.util.Date;
import java.util.Random;

import org.baaleos.systems.lang.AncientLanguage;
import org.baaleos.systems.lang.Apertium;
import org.baaleos.systems.lang.Translate;

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
		/*Date dStart = new Date();
		for(int i=0;i<=100000;i++){
			Apertium.getTranslation("en", "es",randomPhrase(), true);
			//System.out.println(spanish);
		}
		Date dEnd = new Date();
		long time = dEnd.getTime()-dStart.getTime();
		System.out.println("Took "+(time/1000)+" seconds to complete");
		*/
		Translate.setupTranslations();
		String Inhuman = Translate.toLanguage("Hello, my name is Jonny", "Spanish");
		String Inhuman2 = Translate.toLanguage("I shall vanquish all Evil in my lands", "Spanish");
		String Inhuman3 = Translate.toLanguage("The time is 3 o'clock", "Spanish");
		String Inhuman4 = Translate.toLanguage("Demons are a common occurance", "Spanish");
		
		String english = Translate.toEnglish(Inhuman, "Spanish");
		String english2 = Translate.toEnglish(Inhuman2, "Spanish");
		String english3 = Translate.toEnglish(Inhuman3, "Spanish");
		String english4 = Translate.toEnglish(Inhuman4, "Spanish");
		
		System.out.println(Inhuman);
		System.out.println(english);
		
		System.out.println(Inhuman2);
		System.out.println(english2);
		
		System.out.println(Inhuman3);
		System.out.println(english3);
		
		System.out.println(Inhuman4);
		System.out.println(english4);
	}
}
