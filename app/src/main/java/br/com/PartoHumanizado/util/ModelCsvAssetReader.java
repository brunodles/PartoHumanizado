package br.com.PartoHumanizado.util;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by bruno on 25/11/14.
 */
public abstract class ModelCsvAssetReader<T> extends CsvAssetReader {

    protected ArrayList<T> objects = new ArrayList<T>();

    protected ModelCsvAssetReader(Context context, String fileName) {
        super(context, fileName);
    }

    @Override
    protected void readSplitedLine(String[] split) {
        T object = getObject(split);
        objects.add(object);
    }

    protected abstract T getObject(String[] split);

    public ArrayList<T> getObjects() {
        return objects;
    }
}