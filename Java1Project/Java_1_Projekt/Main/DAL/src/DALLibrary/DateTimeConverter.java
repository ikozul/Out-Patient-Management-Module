package DALLibrary;

import java.sql.Date;
import java.sql.Time;

public final class DateTimeConverter {
    public final static String DELIMITER =".A.";

    public static String ConsolidateDateTime(Date d, Time t){
        return d.toString()+DELIMITER+t.toString();
    }

    public static Date GetDateFromDTString(String s){
        String[] datetime = s.split(".A.");
        return Date.valueOf(datetime[0]);
    }
    public static Time GetTimeFromDTString(String s){
        String[] datetime = s.split(".A.");
        return Time.valueOf(datetime[1]);
    }
}
