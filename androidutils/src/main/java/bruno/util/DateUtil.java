package bruno.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	static final long second = 1000;
	static final long minute = 60 * second;
	static final long hour = 60 * minute;
	static final long day = 24 * hour;
	
	public static long minutes(int count){
		return count * minute;
	}
	
	public static long hour(int count){
		return count * hour;
	}
	
	public static Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getStartOfWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getStartOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static boolean greaterThan(Date d1, Date d2){
		if (d1 == null || d2 == null)
			return false;
		return d1.getTime() > d2.getTime();
	}
	
	public static boolean lowerThan(Date d1, Date d2){
		if (d1 == null || d2 == null)
			return false;
		return d1.getTime() < d2.getTime();
	}
	
	public static boolean onSameDay(Date d1, Date d2){
		if (d1 == null || d2 == null)
			return false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String d1f = sdf.format(d1);
		String d2f = sdf.format(d2);
		return d1f.equals(d2f);
	}
}
