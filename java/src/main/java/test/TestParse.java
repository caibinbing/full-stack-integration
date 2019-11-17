package main.java.test;

public class TestParse {

	public static void main(String[] args) {
		//java.lang.NumberFormatException
		String listPrice = "3590.0000" ;
		int price = Integer.parseInt(listPrice) ;
		System.out.println(price);
		double d = Double.parseDouble("0.11a") ;
		System.out.println("d::" + d);
	}

}
