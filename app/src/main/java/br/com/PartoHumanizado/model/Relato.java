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

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by sergio holanda on 26-Nov-14.
 */

@ParseClassName("Relato")
public class Relato extends ParseObject {

    public Relato(){

    }

    private final String VIOLENCIA = "violencia";
    private final String CRM_MEDICO = "crm_medico";
    private final String NOME_MEDICO = "nome_medico";
    private final String NOME_VITIMA = "nome_vitima";
    private final String EMAIL = "email";
    private final String INSTITUICAO = "instituicao";
    private final String LATITUDE = "latitude";
    private final String LONGITUDE = "longitude";

    public void setViolencica(String violencia){
        put(VIOLENCIA,violencia);
    }

    public void setCrmMedico(String crmMedico){
        put(CRM_MEDICO,crmMedico);
    }
    public void setNomeMedico(String nomeMedico){
        put(NOME_MEDICO,nomeMedico);
    }
    public void setNomeVitima(String nomeVitima){
        put(NOME_VITIMA,nomeVitima);
    }
    public void setEmail(String email){
        put(EMAIL,email);
    }
    public void setIntituicao(String instituicao){
        put(INSTITUICAO,instituicao);
    }
    public void setLatitude(double latitude){
        put(LATITUDE,latitude);
    }
    public void setLongitude(double longitude){
        put(LONGITUDE,longitude);
    }

    public String getCrmMedico(){
        return getString(CRM_MEDICO);
    }
    public String getNomeMedico(){
        return getString(NOME_MEDICO);
    }
    public String getNomeVitima(){
        return getString(NOME_VITIMA);
    }
    public String getEmail(){
        return getString(EMAIL);
    }
    public String getInstituicao(){
        return getString(INSTITUICAO);
    }
    public double getLatitude(){
        return getDouble(LATITUDE);
    }
    public double getLongitude(){
        return getDouble(LONGITUDE);
    }


}
