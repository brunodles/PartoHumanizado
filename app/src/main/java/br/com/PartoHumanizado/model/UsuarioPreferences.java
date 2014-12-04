package br.com.PartoHumanizado.model;

import android.content.Context;

public class UsuarioPreferences extends Preferences {

	public UsuarioPreferences(Context context) {
		super(context);

	}

	private enum ADRESS {

        UF
	}
	
	

	public void setUf(String json){
		this.<String>setValue(ADRESS.UF.toString(), json);
	}
	
	public String getUf(){
		return this.<String>getValue(ADRESS.UF.toString(), "");
	}
}
