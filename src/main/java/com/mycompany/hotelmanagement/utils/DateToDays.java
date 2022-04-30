package main.java.com.mycompany.hotelmanagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateToDays {
    String source, end;

    public DateToDays(String source, String end) {
        this.source = source;
        this.end = end;
    }

    public long numberOfDays() {
        SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        long time_difference, days_difference = 0;
        System.out.println(this.source);
        System.out.println(this.end);
        try {
            Date date1 = obj.parse(this.source + " 11:00:00");
            Date date2 = obj.parse(this.end + " 11:00:00");
            time_difference = date2.getTime() - date1.getTime();
            days_difference = TimeUnit.MILLISECONDS.toDays(time_difference) % 365;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days_difference;
    }

    public static void main(String[] args) {
//        DateToDays date = new DateToDays("1")
    }
}
