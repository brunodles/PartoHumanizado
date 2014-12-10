package br.com.PartoHumanizado.broadcast;

import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;

import br.com.PartoHumanizado.activity.MainActivity;

/**
 * Created by sergio holanda on 10-Dec-14.
 */
public class PushNotificationReceiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        //super.onPushOpen(context, intent);
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
