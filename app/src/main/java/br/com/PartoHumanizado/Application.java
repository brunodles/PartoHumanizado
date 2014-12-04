package br.com.PartoHumanizado;

import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import br.com.PartoHumanizado.model.Relato;
import br.com.PartoHumanizado.model.UsuarioPreferences;
import bruno.android.utils.gps.GpsClient;

/**
 * Created by bruno on 11/11/14.
 */
public class Application extends android.app.Application{

    private final String TAG = "PARTO-HUMANIZADO";



    @Override
    public void onCreate() {
        super.onCreate();



        Parse.initialize(this, "QbxVFyJAXjazPgMfp1WdASduaZP8rKveJ71cnbuF", "1PC4sWWFi1P2lp77JJGzK0jL0bgiGxqPMzYz3I98");
        ParseObject.registerSubclass(Relato.class);
        //ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));
        registerDevicePush();

    }
    private void registerDevicePush(){
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d(TAG, "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e(TAG, "failed to subscribe for push", e);
                }
            }
        });
    }

}