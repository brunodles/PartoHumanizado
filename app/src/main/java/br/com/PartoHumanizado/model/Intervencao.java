package br.com.PartoHumanizado.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.PartoHumanizado.util.ModelCsvAssetReader;

/**
 * Created by bruno on 25/11/14.
 */
public class Intervencao {

    public String titulo;
    public List<String> texts;

    public static List<Intervencao> readFromAssets(Context context) {
        IntervencoesCsvAssetReader intervencoesCsvAssetReader = new IntervencoesCsvAssetReader(context);
        intervencoesCsvAssetReader.read();
        return intervencoesCsvAssetReader.getObjects();
    }

    private static final class IntervencoesCsvAssetReader extends ModelCsvAssetReader<Intervencao> {

        public IntervencoesCsvAssetReader(Context context) {
            super(context, "intervencoes.csv");
            setSeparatorRegex(";");
        }

        @Override
        protected Intervencao getObject(String[] split) {
            Intervencao intervencao = new Intervencao();
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
