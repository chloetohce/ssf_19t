package sg.edu.nus.iss.ssf_19t.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static long dateToEpoch(Date date) {
        return date.getTime();
    }

    public static Date strToDate(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("E, MM/dd/yyyy");
        return sdf.parse(str);
    }

    public static long strToEpoch(String str) throws ParseException {
        Date d = strToDate(str);
        return dateToEpoch(d);
    }

    public static Date epochToDate(long epoch) {
        return new Date(epoch);
    }
    
}
