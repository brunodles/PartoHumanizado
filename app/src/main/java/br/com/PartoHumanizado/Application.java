package br.com.PartoHumanizado;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

import br.com.PartoHumanizado.model.Relato;

/**
 * Created by bruno on 11/11/14.
 */
public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();

      //  ParseObject.registerSubclass(Relato.class);
      //  Parse.initialize(this, "QbxVFyJAXjazPgMfp1WdASduaZP8rKveJ71cnbuF", "1PC4sWWFi1P2lp77JJGzK0jL0bgiGxqPMzYz3I98");
        //ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));
    }
}
