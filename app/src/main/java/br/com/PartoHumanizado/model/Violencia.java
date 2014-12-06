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

import java.util.ArrayList;
import java.util.List;

import br.com.PartoHumanizado.util.ModelCsvAssetReader;

/**
 * Created by bruno on 25/11/14.
 */
public class Violencia {

    private String titulo;
    private List<String> texts;
//    private boolean expanded;

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

//    public boolean isExpanded() {
//        return expanded;
//    }
//
//    public void setExpanded(boolean expanded) {
//        this.expanded = expanded;
//    }

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
