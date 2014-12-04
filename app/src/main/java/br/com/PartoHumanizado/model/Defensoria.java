package br.com.PartoHumanizado.model;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import br.com.PartoHumanizado.util.ModelCsvAssetReader;

/**
 * Created by sergio holanda on 03-Dec-14.
 */
public class Defensoria {

    public static List<Defensoria> readFromAssets(Context context) {
        CsvAssetReader csvAssetReader = new CsvAssetReader(context);
        csvAssetReader.read();
        return csvAssetReader.getObjects();
    }
    private String nome;

    private String defensorGeral;

    private String telefone;

    private String endereco;

    private String site;

    private String email;

    private String uf;

    private String cep;

    public String getCep() {
        return cep;
    }

    public String getNome() {
        return nome;
    }

    public String getDefensorGeral() {
        return defensorGeral;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getSite() {
        return site;
    }

    public String getEmail() {
        return email;
    }

    public String getUf() {
        return uf;
    }

    private static final class CsvAssetReader extends ModelCsvAssetReader<Defensoria> {

        public CsvAssetReader(Context context) {
            super(context, "defensorias.csv");
            setSeparatorRegex(";");
        }

        @Override
        protected Defensoria getObject(String[] split) {
            Defensoria defensoria = new Defensoria();

            defensoria.nome = split[0];
            defensoria.defensorGeral = split[1];
            defensoria.endereco = split[2];
            defensoria.cep = split[3];
            defensoria.telefone = split[4];
            defensoria.site = split[5];
            defensoria.email = split[6];
            defensoria.uf = split[7];

            return defensoria;
        }
    }
}
