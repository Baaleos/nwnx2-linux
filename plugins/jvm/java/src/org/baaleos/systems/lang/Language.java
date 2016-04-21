package org.baaleos.systems.lang;

import java.util.Hashtable;
import java.util.Map;

public class Language implements ILanguage {

	@Override
	public Hashtable<String, String> translationMap() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public String toLanguage(String s) {
		String s1 = "";
		String[] words = s.split(" ");
		for(String sWord : words){
			boolean bUpper = Language.isUpperCase(sWord.charAt(0));
			boolean containsComma = sWord.contains(",");
			String replacement = "";
			boolean isWord = translationWordMap().containsKey(sWord.toLowerCase().substring(0, sWord.length()-1)) || translationWordMap().containsKey(sWord.toLowerCase());;
			if(isWord){
				
				//Translate word
				if(translationWordMap().containsKey(sWord.toLowerCase())){
					replacement = translationWordMap().get(sWord.toLowerCase());
				}else{
					replacement = translationWordMap().get(sWord.toLowerCase().substring(0, sWord.length()-1));
				}
				if(bUpper){
					String first = replacement.substring(0, 1).toUpperCase();
					String rest = replacement.substring(1);
					s1 +=  first+rest;
				}else{
					s1 += replacement;
				}
				if(containsComma){
					s1 += ",";
				}
				s1 += " ";
			}else{
				//Translate characters
				for(char c : sWord.toCharArray())
				{
					if(translationMap().containsKey(String.valueOf(c).toLowerCase())){
						bUpper = Language.isUpperCase(c);
						if(bUpper){
							String first = translationMap().get(String.valueOf(c).toLowerCase()).substring(0, 1).toUpperCase();
							String rest = translationMap().get(String.valueOf(c).toLowerCase()).substring(1);
							s1 +=  first+rest;
						}else{
							s1 += translationMap().get(String.valueOf(c).toLowerCase());
						}
					}else{
						s1 += c;
					}
				}
				s1 += " ";
			}
			
			
			
		}
		
		return s1;
	}

	public static boolean isUpperCase(char ch) {
	    return ch >= 'A' && ch <= 'Z';
	}

	@Override
	public Hashtable<String, String> translationWordMap() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String toEnglish(String s) {
		String s1 = "";
		String[] words = s.split(" ");
		for(String sWord : words){
			boolean bUpper = Language.isUpperCase(sWord.charAt(0));
			boolean containsComma = sWord.contains(",");
			String replacement = "";
			boolean isWord = translationWordMap().containsValue(sWord.toLowerCase().substring(0, sWord.length()-1)) || translationWordMap().containsValue(sWord.toLowerCase());;
			if(isWord){
				
				//Translate word
				if(translationWordMap().containsValue(sWord.toLowerCase())){
					String key= null;
			        String value=sWord.toLowerCase();
			        for(Map.Entry entry: translationWordMap().entrySet()){
			            if(value.equals(entry.getValue())){
			                key = entry.getKey().toString();
			                break; //breaking because its one to one map
			            }
			        }
					replacement = key;

				}else{
					String key= null;
			        String value=sWord.toLowerCase().substring(0, sWord.length()-1);
			        for(Map.Entry entry: translationWordMap().entrySet()){
			            if(value.equals(entry.getValue())){
			                key = entry.getKey().toString();
			                break; //breaking because its one to one map
			            }
			        }
					replacement = key;
				}
				if(bUpper){
					String first = replacement.substring(0, 1).toUpperCase();
					String rest = replacement.substring(1);
					s1 +=  first+rest;
				}else{
					s1 += replacement;
				}
				if(containsComma){
					s1 += ",";
				}
				s1 += " ";
			}else{
				//Translate characters
				for(char c : sWord.toCharArray())
				{
					if(translationMap().containsValue(String.valueOf(c).toLowerCase())){
						bUpper = Language.isUpperCase(c);
						if(bUpper){
							String first = translationMap().get(String.valueOf(c).toLowerCase()).substring(0, 1).toUpperCase();
							String rest = translationMap().get(String.valueOf(c).toLowerCase()).substring(1);
							s1 +=  first+rest;
						}else{
							
							s1 += translationMap().get(String.valueOf(c).toLowerCase());
						}
					}else{
						s1 += c;
					}
				}
				s1 += " ";
			}
			
			
			
		}
		
		return s1;
	}
}
