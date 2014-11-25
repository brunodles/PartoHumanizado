package br.com.PartoHumanizado.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bruno on 25/11/14.
 */
public abstract class CsvAssetReader {

    private static final String TAG = "CsvAssetReader";

    Context context;
    String fileName;
    String separatorRegex = ",";

    public CsvAssetReader(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public CsvAssetReader setSeparatorRegex(String separatorRegex) {
        this.separatorRegex = separatorRegex;
        return this;
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
            Log.e(TAG, "read ", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    protected void readLine(String line) {
//        onReadLine.readLine(line);
        String[] split = line.split(separatorRegex);
        readSplitedLine(split);
    }

    abstract protected void readSplitedLine(String[] split);
}
