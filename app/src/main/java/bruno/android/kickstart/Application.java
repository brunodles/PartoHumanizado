package bruno.android.PartoHumanizado;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

/**
 * Created by bruno on 11/11/14.
 */
public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();

//        ParseObject.registerSubclass(Model.class);
//        Parse.initialize(this, PARSE_APP_ID, PARSE_CLIENT_KEY);
//        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));
    }
}
