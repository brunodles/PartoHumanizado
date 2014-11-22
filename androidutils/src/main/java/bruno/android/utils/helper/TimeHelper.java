package bruno.android.utils.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by dev on 02/10/2014.
 */
public class TimeHelper {

    public static final long SECOND_IN_MILIS = 1000;
    public static final long MINUTE_IN_MILIS = 60 * SECOND_IN_MILIS;
    public static final long HOUR_IN_MILIS = 60 * MINUTE_IN_MILIS;
    public static final long DAY_IN_MILIS = 24 * HOUR_IN_MILIS;

    public static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    public static long ZERO_TIME;

    static {
        try {
            ZERO_TIME = timeFormat.parse("00:00:00").getTime();
        } catch (ParseException e) {
        }
    }

    private TimeHelper() {
    }

    public static long secondInMilis(int second) {
        return second * SECOND_IN_MILIS;
    }

    public static long minuteInMilis(int minute) {
        return minute * MINUTE_IN_MILIS;
    }

    public static long hourInMilis(int hour) {
        return hour * HOUR_IN_MILIS;
    }

    public static long dayInMilis(int day) {
        return day * DAY_IN_MILIS;
    }

}
