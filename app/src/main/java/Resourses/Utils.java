package Resourses;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jcapasix on 23/06/18.
 */

public class Utils {

    public static String getDate(String fecha) {
        try {
            SimpleDateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault());
            SimpleDateFormat dateFormatOutput = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            Date date = dateFormatInput.parse(fecha, new ParsePosition(0));
            String returnDate = dateFormatOutput.format(date);
            return returnDate;
        } catch (Exception ex) {
            return "";
        }
    }
}
