package bruno.android.utils.helper;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.lang.reflect.Method;

public abstract class ServiceHelper {

	private static final String TAG = "bruno - WifiManagerHelper";

	public static void wifiManagerStartScan(Context context) {
		Log.d(TAG, "startScan");
		wifiManager(context).startScan();
	}

	public static WifiManager wifiManager(Context context) {
		return (WifiManager) getSystemService(context, Context.WIFI_SERVICE);
	}

	public static NotificationManager notificationManager(Context context) {
		return (NotificationManager) getSystemService(context,
				Context.NOTIFICATION_SERVICE);
	}

	public static AlarmManager alarmManager(Context context) {
		return (AlarmManager) getSystemService(context, Context.ALARM_SERVICE);
	}

	private static Object getSystemService(Context context, String name) {
		return context.getSystemService(name);
	}

	public static String getDeviceUniqueId() {
		String serial = null;

		try {
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method get = c.getMethod("get", String.class);
			serial = (String) get.invoke(c, "ro.serialno");
		} catch (Exception ignored) {
		}
		return serial;
	}
}
