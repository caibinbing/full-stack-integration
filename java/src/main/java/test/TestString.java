package main.java.test;

public class TestString {

	public static void main(String[] args) {
		int len1 = testStringLenght("a") ;	//len = 1
		int len2 = testStringLenght("a ") ;	//len = 2
		System.out.println(len1);
		System.out.println(len2);
		
		String ret1 = testSubString("a1b2c3d4") ;
		String ret2 = testSubString(" a1b2c3d4 ") ;
		System.out.println(ret1);	//a1b2c3d
		System.out.println(ret2);	// a1b2c3d4

		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);	//true
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);	//false
	}

	private static int testStringLenght(String raw) {
		return raw.length() ;
	}
	
	private static String testSubString(String raw) {
		return raw.substring(0, raw.length() - 1) ;
	}
}
