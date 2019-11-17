package main.java.utils;

import java.util.HashMap;
import java.util.Map;

public class UrlUtils {

	private static String url1 = "http://10.7.121.56:50000/RESTAdapter/";
	private static String url2 = "http://pi.eeka.info:33978/RESTAdapter/";
	private static String url3 = "http://210.75.22.164:33978/RESTAdapter/";
	/*
		0 = http:
		1 = 
		2 = 10.7.121.56:50000
		3 = RESTAdapter
	 */
	public static Map<String , Integer> urlResolution(String url) {
		String[] mainString = url.split("/") ;
		if(null == mainString || mainString.length == 0) {
			System.out.println("mainString is null");
		}
		String subString = mainString[2] ;
		String[] finalString = subString.split(":") ;
		if(null == finalString || finalString.length == 0) {
			System.out.println("finalString is null");
		}
		Map<String , Integer> map = new HashMap<String, Integer>() ;
		map.put(finalString[0], Integer.parseInt(finalString[1])) ;
		return map ;
	}
	
	public static void main(String[] args) {
		Map<String , Integer> map = new HashMap<>() ;
		map = urlResolution(url1) ;
		for(String k : map.keySet()) {
			System.out.println("host = " + k + " ; port = " + map.get(k));
		}
		System.out.println("--------------------------------------------");
		map = urlResolution(url2) ;
		for(String k : map.keySet()) {
			System.out.println("host = " + k + " ; port = " + map.get(k));
		}
		System.out.println("--------------------------------------------");
		map = urlResolution(url3) ;
		for(String k : map.keySet()) {
			System.out.println("host = " + k + " ; port = " + map.get(k));
		}
	}

}
