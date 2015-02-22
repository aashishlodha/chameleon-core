package org.aashish.chameleon.util;

public class NamingConventionHelper {

	public static String toCamelCase(String str){
		StringBuilder strBuilder = new StringBuilder();
		char[] chars = str.toCharArray();
		boolean nextUpper = false;
		for(int i=0;i<chars.length;i++){
			if(nextUpper == true){
				if(chars[i] == '_' || chars[i] == ' '){
					continue;
				}
				strBuilder.append((""+chars[i]).toUpperCase());
				nextUpper = false;
				continue;
			}
			if(chars[i] == '_' || chars[i] == ' '){
				nextUpper = true;
				continue;
			}
			if(Character.isUpperCase(chars[i])){
				strBuilder.append(chars[i]);
				continue;
			}
			strBuilder.append((""+chars[i]).toLowerCase());
		}
		
		return strBuilder.toString();
	}
	
	public static String toPascalCase(String str){
		str = toCamelCase(str);
		char firstChar = str.charAt(0);
		return str.replaceFirst(""+firstChar, ""+Character.toUpperCase(firstChar));
	}
}
