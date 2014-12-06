/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

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