package br.com.PartoHumanizado.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Preferences {

	private SharedPreferences sharedPref;

	public Preferences(Context context) {
		sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	@SuppressWarnings("unchecked")
	public<T> T getValue(String key, T defaultValue) {
		T resultValue = null;
		
		if(defaultValue instanceof Boolean) {
			resultValue = (T) Boolean.valueOf(sharedPref.getBoolean(key, (Boolean)defaultValue));
		} else if(defaultValue instanceof Float){
			resultValue = (T) Float.valueOf(sharedPref.getFloat(key, (Float)defaultValue));
		} else if(defaultValue instanceof Integer){
			resultValue = (T) Integer.valueOf(sharedPref.getInt(key, (Integer)defaultValue));
		} else if(defaultValue instanceof Long) {
			resultValue = (T) Long.valueOf(sharedPref.getLong(key, (Long)defaultValue));
		} else if(defaultValue instanceof String) {
			resultValue = (T) sharedPref.getString(key, (String)defaultValue);
		}

      
		
		return resultValue;
	}
	
	public<T> void setValue(String key, T value) {
		Editor editor = sharedPref.edit();
		
		if(value instanceof Boolean) {
			editor.putBoolean(key, (Boolean)value);
		} else if(value instanceof Float){
			editor.putFloat(key, (Float)value);
		} else if(value instanceof Integer){
			editor.putInt(key, (Integer)value);
		} else if(value instanceof Long) {
			editor.putLong(key, (Long)value);
		} else if(value instanceof String) {
			editor.putString(key, (String)value);
		}
		
		editor.commit();
	}
}
