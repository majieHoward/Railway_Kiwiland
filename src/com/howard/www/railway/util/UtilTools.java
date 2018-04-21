package com.howard.www.railway.util;

/**
 * 
 * @ClassName: UtilTools
 * @Description:TODO
 * @author: mayijie
 * @date: 2018年4月19日 下午2:17:17
 * 
 * @Copyright: 2018 https://github.com/majieHoward Inc. All rights reserved.
 */
public class UtilTools {

	public static String removeRepeatedCharSort(final String cs) {
		if (isEmpty(cs))
			return cs;

		return null;
	}

	public static char[] removeRepeatedCharListSort(final String cs) {
		if (isEmpty(cs))
			return new char[0];
		char[] paramChars = removeRepeatedChar(cs).toCharArray();
		char temp;
		for (int i = 0; i < paramChars.length; i++) {
			for (int j = 0; j < paramChars.length - 1 - i; j++) {
				if (paramChars[j] > paramChars[j + 1]) {
					temp = paramChars[j + 1];
					paramChars[j + 1] = paramChars[j];
					paramChars[j] = temp;
				}
			}
		}
		return paramChars;
	}

	public static String removeRepeatedChar(final String cs) {
		if (isEmpty(cs))
			return cs;
		int len = cs.length();
		int k = 0;
		int count = 0;
		StringBuffer str = new StringBuffer();
		char[] c = new char[len];
		for (int i = 0; i < len; i++) {
			c[i] = cs.charAt(i);
		}
		for (int i = 0; i < len; i++) {
			k = i + 1;
			while (k < len - count) {
				if (c[i] == c[k]) {
					for (int j = k; j < len - 1; j++) {
						c[j] = c[j + 1];// 出现重复字母，从k位置开始将数组往前挪位
					}
					count++;// 重复字母出现的次数
					k--;
				}
				k++;
			}
		}
		for (int i = 0; i < len - count; i++) {
			str.append(c[i]);
		}
		return str.toString();
	}

	public static String obtainLetters(final String paramStr) {
		char[] letterChars = obtainLetterChars(paramStr);
		if (letterChars.length > 0) {
			StringBuffer param = new StringBuffer();
			for (char letterChar : letterChars) {
				param.append(letterChar);
			}
			return param.toString();
		}
		return null;
	}

	public static String obtainNumber(final String paramStr) {
		if (isEmpty(paramStr)) {
			return paramStr;
		}
		char[] chs = obtainLetterCharItems(paramStr);
		StringBuffer param = new StringBuffer();
		for (char ch : chs) {
			if (ch >= 48 && ch <= 57) {
				param.append(ch);
			}
		}
		return param.toString();
	}

	public static char[] obtainLetterChars(final String paramStr) {
		if (isEmpty(paramStr)) {
			return null;
		}
		// 字母对应的ASCII码值 65~90 97~122
		// 65 A
		char[] chs = obtainLetterCharItems(paramStr);
		StringBuffer param = new StringBuffer();
		for (char ch : chs) {
			if ((64 < ch && ch < 91) || (ch > 96 && ch < 123)) {
				param.append(ch);
			}
		}
		return param.toString().toCharArray();
	}

	public static char[] obtainLetterCharItems(final String paramStr) {
		if (isEmpty(paramStr)) {
			return null;
		}
		return paramStr.toCharArray();
	}

	public static String removeLastCharacter(final String paramStr){
		char[]  temp=obtainLetterCharItems(paramStr);
		if(temp!=null){
			StringBuffer param=new StringBuffer();
			for(int i=0;i<temp.length-1;i++){
				param.append(String.valueOf(temp[i]));
			}
			return param.toString();
		}
		return null;
	}
	
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
	
	/**
	 * 获得一个字符在一个字符串数组中的小标位置
	 * @param ch
	 * @param chItems
	 * @return
	 */
	public static int getPosition(char ch,char[] chItems) {
		for (int i = 0; i < chItems.length; i++)
			if (chItems[i] == ch)
				return i;
		return -1;
	}
	
	/**
	 * 在一个字符串中的每个字符之间添加连接符号
	 * @param paramStr
	 * @param symbol
	 * @return
	 */
	public static String addSymbolToChar(final String paramStr,final String symbol){
		char[]  temp=obtainLetterCharItems(paramStr);
		if(temp!=null){
			StringBuffer param=new StringBuffer();
			for(int i=0;i<temp.length;i++){
				param.append(String.valueOf(temp[i]));
				if(i!=(temp.length-1)){
					param.append(symbol);
				}
			}
			return param.toString();
		}
		return null;
	}
	
	public static String insertString(char[] a,char[]b,int start,int end){
		StringBuffer param=new StringBuffer();
		if(start==0){
			param.append(b);
			for(int i=end+1;i<a.length;i++){
				param.append(String.valueOf(a[i]));
			}
		}else{
			for(int i=0;i<=start-1;i++){
				param.append(String.valueOf(a[i]));
			}
			param.append(b);
			if(start!=end){
				for(int i=end+1;i<a.length;i++){
					param.append(String.valueOf(a[i]));
				}
			}
		}
		return param.toString();
	}
}
