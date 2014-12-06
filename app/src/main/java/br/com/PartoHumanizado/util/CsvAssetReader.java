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
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by bruno on 25/11/14.
 */
public abstract class CsvAssetReader extends AssetReader {

    private static final String TAG = "CsvAssetReader";

    String separatorRegex = ",";

    public CsvAssetReader(Context context, String fileName) {
        super(context, fileName);
    }

    public CsvAssetReader setSeparatorRegex(String separatorRegex) {
        this.separatorRegex = separatorRegex;
        return this;
    }

    @Override
    protected void readLine(String line) {
//        String[] split = line.split(separatorRegex);
        String[] strings = splitString(line, separatorRegex);

        readSplitedLine(strings);
    }

    public static String[] splitString(String line, String separatorRegex) {
        ArrayList<String> strings = new ArrayList<String>();

        int lineLength = line.length();
        int start = 0;
        int separetorIndex = 0;
        int separetorRegexLength = separatorRegex.length();
        while ((separetorIndex = line.indexOf(separatorRegex, start)) >= 0) {
//            System.out.printf("substring %s ~ %s\n", start, separetorIndex);
            String substring = line.substring(start, separetorIndex);
//            System.out.printf("colum \"%s\"\n",substring);
            strings.add(substring);
            start = separetorIndex + separetorRegexLength;
//            start = separetorIndex;
        }
        if (start <= lineLength) {
//            System.out.printf("substring %s ~ %s\n", start, separetorIndex);
            String substring = line.substring(start, lineLength);
//            System.out.printf("colum \"%s\"\n",substring);
            strings.add(substring);
            start = separetorIndex + separetorRegexLength;
//            start = separetorIndex;
        }
//        System.out.printf("start %s, index %s\n", start, separetorIndex);
        return strings.toArray(new String[strings.size()]);
    }

    abstract protected void readSplitedLine(String[] split);


    public static void main(String[] args) {
        String[] strings = {
                "a;a;a",
                "b;b;b",
                ";c;c",
                "d;;d",
                "e;e;",
                "f;;"
        };

        System.out.println("begin");
        for (String line : strings) {
            String[] split = splitString(line, ";");
            System.out.printf("%s - %s\n", line, split.length);
            int i = 0;
            for (String s : split)
                System.out.printf(" -%s - %s\n", i++, s);
            System.out.println("");
        }
        System.out.println("end");
    }
}
