package br.com.PartoHumanizado.util;

import android.content.Context;

/**
 * Created by bruno on 27/11/14.
 */
public class TextAssetReader extends AssetReader {

    StringBuilder builder;
    String tittle = null;
    String lineBreaker = "\n";

    public TextAssetReader(Context context, String fileName) {
        super(context, fileName);
        builder = new StringBuilder();
    }

    @Override
    protected void readLine(String line) {
        if (tittle == null)
            tittle = line;
        else
            builder.append(line)
                    .append(lineBreaker);
    }

    public void setLineBreaker(String lineBreaker) {
        this.lineBreaker = lineBreaker;
    }

    public String getString() {
        return builder.toString();
    }

    public String getTittle() {
        return tittle;
    }
}
