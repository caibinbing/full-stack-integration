package main.java.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) throws ParseException {
		String sTime = "2018-08-20 14:00:00" ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
		try {
			date = sdf.parse(sTime);
		} catch (ParseException ignored) {
			
		}
        assert date != null;
        System.out.println(date.getTime());
		System.out.println(sdf.format(new Date(System.currentTimeMillis())));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));

        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        String newDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String oldDate = "2018-10-18";
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bt=sdf.parse(oldDate);
        Date et=sdf.parse(newDate);
        System.out.println(bt.before(et));


		//java.text.ParseException: Unparseable date: "2018/9/6 0:00:00"
        String newDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String oldDate1 = "2018-10-18  0:00:00";
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bt1=sdf.parse(oldDate1);
        Date et1=sdf.parse(newDate1);
        System.out.println(sdf.format(bt1));
        System.out.println(sdf.format(et1));
        System.out.println(bt1.before(et1));
    }

}
