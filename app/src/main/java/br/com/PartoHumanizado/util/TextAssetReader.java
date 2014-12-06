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

import java.util.Arrays;
import java.util.List;

/**
 * Created by bruno on 27/11/14.
 */
public class TextAssetReader extends AssetReader {

    private static List<String> imageFileExtensions = Arrays.asList("jpg", "jpeg", "png");

    StringBuilder builder;
    String image = null;
    String tittle = null;
    String lineBreaker = "\n";

    public TextAssetReader(Context context, String fileName) {
        super(context, fileName);
        builder = new StringBuilder();
    }

    @Override
    protected void readLine(String line) {
        if (tittle == null) {
            if (image == null && isImage(line))
                image = line;
            else
                tittle = line;
        } else {
            builder.append(line)
                    .append(lineBreaker);
        }
    }

    private boolean isImage(String line) {
        if (line.startsWith("http"))
            return true;
        int indexOf = line.lastIndexOf(".");
        if (indexOf == -1)
            return false;
        String extension = line.substring(indexOf + 1);
//        System.out.println(extension);
        if (imageFileExtensions.contains(extension))
            return true;
        return false;
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

    public String getImage() {
        return image;
    }
}
