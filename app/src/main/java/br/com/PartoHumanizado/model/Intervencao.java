package br.com.PartoHumanizado.model;

import android.content.Context;

import java.util.List;

import br.com.PartoHumanizado.util.ModelCsvAssetReader;

/**
 * Created by bruno on 25/11/14.
 */
public class Intervencao {

    private String titulo;
    private String justificativa;
    private String porQueEDesnecessaria;

    public static List<Intervencao> readFromAssets(Context context) {
        CsvAssetReader csvAssetReader = new CsvAssetReader(context);
        csvAssetReader.read();
        return csvAssetReader.getObjects();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public String getPorQueEDesnecessaria() {
        return porQueEDesnecessaria;
    }

    private static final class CsvAssetReader extends ModelCsvAssetReader<Intervencao> {

        public CsvAssetReader(Context context) {
            super(context, "intervencoes.csv");
            setSeparatorRegex(";");
        }

        @Override
        protected Intervencao getObject(String[] split) {
            Intervencao intervencao = new Intervencao();
            intervencao.titulo = split[0];
            intervencao.justificativa = split[1];
            intervencao.porQueEDesnecessaria = split[2];
            return intervencao;
        }
    }
}
