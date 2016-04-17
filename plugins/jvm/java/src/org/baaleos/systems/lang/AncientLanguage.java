package org.baaleos.systems.lang;

import java.util.Hashtable;

public class AncientLanguage extends Language {

	public AncientLanguage(){
		System.out.println(translationMap().size());
	}
	
	private Hashtable<String,String> map;
	@Override
	public Hashtable<String, String> translationMap() {
		// TODO Auto-generated method stub
		if(map == null){
			map = new Hashtable<String,String>();
			map.put("a", "ar");
			map.put("b", "bae");
			map.put("c", "ka");
			map.put("d", "ded");
			map.put("e", "ier");
			map.put("f", "efi");
			map.put("g", "gh'");
			map.put("h", "ohi");
			map.put("i", "ri");
			map.put("j", "gro");
			map.put("k", "saa");
			map.put("l", "lu'");
			map.put("m", "mo");
			map.put("n", "nag");
			map.put("o", "osi");
			map.put("p", "psi'");
			map.put("q", "qu");
			map.put("r", "sio");
			map.put("s", "dedi");
			map.put("t", "eego");
			map.put("u", "umi");
			map.put("v", "valar");
			map.put("w", "wis");
			map.put("x", "xen");
			map.put("y", "yu'i");
			map.put("z", "zo");
			map.put(" ", " ");
		}
		return map;
	}

	@Override
	public String toLanguage(String s) {
		String s1 = "";
		for(char c : s.toCharArray())
		{
			String str = String.valueOf(c);
			boolean bUpper = Language.isUpperCase(c);
			if(bUpper){
				String first = map.get(String.valueOf(c).toLowerCase()).substring(0, 1).toUpperCase();
				String rest = map.get(String.valueOf(c).toLowerCase()).substring(1);
				s1 +=  first+rest;
			}else{
				s1 += map.get(String.valueOf(c).toLowerCase());
			}

		}
		return s1;
		
	}

}
