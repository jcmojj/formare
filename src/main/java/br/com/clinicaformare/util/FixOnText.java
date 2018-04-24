package br.com.clinicaformare.util;

public class FixOnText {

	// Basic Methods
	public static String capitalizeString(String string) {
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
				found = false;
			}
		}
		return String.valueOf(chars);
	}
	public static String capitalizeFirstStringChar(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}
	
	public static String onlyNumberString(String string) {
		return string.replaceAll("[^\\d.]", "");
	}
	
	public static String onlyOneSpace(String string) {
		return string.trim().replaceAll(" +", " ");
	}
	public static String noSpace(String string) {
		return string.trim().replaceAll(" ", "");
	}
	
	public static String trim(String string) {
		return string.trim();
	}
	
	public static String toLowercase(String string) {
		return string.toLowerCase();
	}
	
	public static String toUppercase(String string) {
		return string.toUpperCase();
	}
	
	// Advanced Methods
	public static String withAllWordsFirstCharCapitalized(String string) {
		String withAllWordsFirstCharCapitalized = string.toLowerCase().trim();
		withAllWordsFirstCharCapitalized = onlyOneSpace(withAllWordsFirstCharCapitalized);
		withAllWordsFirstCharCapitalized = capitalizeString(withAllWordsFirstCharCapitalized);
		return withAllWordsFirstCharCapitalized;
	}
	public static String withFirstCharOnStringCapitalized(String string) {
		String withFirstCharOnStringCapitalized = string.toLowerCase().trim();
		withFirstCharOnStringCapitalized = onlyOneSpace(withFirstCharOnStringCapitalized);
		withFirstCharOnStringCapitalized = capitalizeFirstStringChar(withFirstCharOnStringCapitalized);
		return withFirstCharOnStringCapitalized;
	}
	public static String withAllCharsLowerCase(String string) {
		String withAllCharsLowerCase = string.toLowerCase().trim();
		withAllCharsLowerCase = onlyOneSpace(withAllCharsLowerCase);
		return withAllCharsLowerCase;
	}
	public static String withAllCharsUpperCase(String string) {
		String withAllCharsUpperCase = string.toUpperCase().trim();
		withAllCharsUpperCase = onlyOneSpace(withAllCharsUpperCase);
		return withAllCharsUpperCase;
	}
	
	public static String withOnlyNumbersOnString(String string) {
		String withOnlyNumbersOnString = noSpace(string);
		withOnlyNumbersOnString = onlyNumberString(withOnlyNumbersOnString);
		return withOnlyNumbersOnString;
	}
	
	public static String withInternationalCode(String string) {
		System.out.println("withInternationalCode");
		System.out.println("onlyNumberString(string.substring(0,string.length())).length():"+onlyNumberString(string.substring(0,string.length())).length());
		System.out.println("onlyNumberString(string.substring(1,string.length())).length():"+onlyNumberString(string.substring(1,string.length())).length());

		if(string.startsWith("+")) {
			int lenght = onlyNumberString(string.substring(1,string.length())).length() > 3 ? 3 : onlyNumberString(string.substring(1,string.length())).length();
			return "+" + onlyNumberString(string.substring(1,string.length())).substring(0, lenght);
		}
		int lenght = onlyNumberString(string.substring(0,string.length())).length() > 3 ? 3 : onlyNumberString(string.substring(0,string.length())).length();
		return "+" + onlyNumberString(string.substring(0,string.length())).substring(0, lenght);
	}
	
//	public static String allWordsFirstCharCapitalized(String string) {
//		String allWordsFirstCharCapitalized = string.toLowerCase().trim();
//		allWordsFirstCharCapitalized = onlyOneSpace(allWordsFirstCharCapitalized);
//		allWordsFirstCharCapitalized = capitalizeString(allWordsFirstCharCapitalized);
//		return allWordsFirstCharCapitalized;
//	}
	
}
