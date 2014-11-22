package bruno.android.utils.simple;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

public abstract class SimpleBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = "SimpleBroadcastReceiver";
    private String[] filterActions;

    public SimpleBroadcastReceiver(String... filterActions) {
        this.filterActions = filterActions;
    }

    public void register(Context context) {
        IntentFilter intentFilter = buildFilter();
        try {
            register(context, intentFilter);
        }catch (IllegalArgumentException e){
            Log.w(TAG, "Error registering receiver. Receiver maybe already registered, unregistering to register again.");
            unregister(context);
            register(context, intentFilter);
        }
    }

    private void register(Context context, IntentFilter intentFilter) {
        context.registerReceiver(this, intentFilter);
    }

    private IntentFilter buildFilter() {
        IntentFilter intentFilter = new IntentFilter();
        for (String action:filterActions)
            intentFilter.addAction(action);
        return intentFilter;
    }

    public void unregister(Context context) {
        try {
            context.unregisterReceiver(this);
        }catch (IllegalArgumentException e){
            Log.w(TAG, "Error on unregister: " + e.getLocalizedMessage());
        }catch (Exception e){
            Log.e(TAG, "Error on unregister", e);
        }
    }
}