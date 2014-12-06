/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

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