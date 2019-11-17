package main.java.test;

import java.io.File;
import java.util.regex.Matcher;

public class TestSeparator {

	public static void main(String[] args) {
		System.out.println(File.separator); //输出 "\"  
		System.out.println(Matcher.quoteReplacement(File.separator));  //输出"\\"  
		System.out.println("/"); //输出 "/"  
		System.out.println(Matcher.quoteReplacement("/")); //输出 "/"  
		System.out.println("$"); //输出 "$"   
		System.out.println(Matcher.quoteReplacement("$")); //输出 "\$"  
	}

}
