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
