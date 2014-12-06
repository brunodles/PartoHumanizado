/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

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
