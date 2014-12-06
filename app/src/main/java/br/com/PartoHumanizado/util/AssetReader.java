/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

package br.com.PartoHumanizado.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bruno on 27/11/14.
 */
public abstract class AssetReader {

    private static final String TAG = "AssetReader";

    Context context;
    String fileName;

    public AssetReader(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public void read() {
        InputStream is = null;
        try {
            is = context.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null)
                readLine(line);

            br.close();
        } catch (IOException e) {
            Log.e(TAG, "Error on read asset", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    protected abstract void readLine(String line);
}
