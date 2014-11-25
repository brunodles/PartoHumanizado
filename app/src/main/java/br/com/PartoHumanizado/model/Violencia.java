package br.com.PartoHumanizado.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.PartoHumanizado.util.ModelCsvAssetReader;

/**
 * Created by bruno on 25/11/14.
 */
public class Violencia {

    private String titulo;
    private List<String> texts;

    public static List<Violencia> readFromAssets(Context context) {
        CsvAssetReader intervencoesCsvAssetReader = new CsvAssetReader(context);
        intervencoesCsvAssetReader.read();
        return intervencoesCsvAssetReader.getObjects();
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getTexts() {
        return texts;
    }

    public String getTextsAsString() {
        StringBuilder sb = new StringBuilder();
        for (String text : texts)
            sb.append(text)
                    .append("\n");
        return sb.toString();
    }

    private static final class CsvAssetReader extends ModelCsvAssetReader<Violencia> {

        public CsvAssetReader(Context context) {
            super(context, "violencias.csv");
            setSeparatorRegex(";");
        }

        @Override
        protected Violencia getObject(String[] split) {
            Violencia intervencao = new Violencia();
            intervencao.titulo = split[0];
            ArrayList<String> texts = new ArrayList<String>();
            for (int i = 1; i < split.length; i++) {
                texts.add(split[i]);
            }
            intervencao.texts = texts;
            return intervencao;
        }
    }
}
