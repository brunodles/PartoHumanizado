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
