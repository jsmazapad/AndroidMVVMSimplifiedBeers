package es.jsm.mvvm.beer.providers;

import android.content.SharedPreferences;

import es.jsm.mvvm.beer.core.utils.PreferenceManager;

/**
 * Almacen central de preferencias de usuario
 * Hace uso de sharedpreferences
 */
public class PreferencesProvider {

    private final static String CAR_LOCATION = "CAR_LOCATION";
    private final static String PASSWORD = "PASSWORD";


    public static String getCarLocation() {
        String data = PreferenceManager.getSharedPreferences().getString(CAR_LOCATION, "");
        return data;

    }

    public static void setCarLocation(String value) {
        try {
            SharedPreferences.Editor editor = PreferenceManager
                    .getSharedPreferences().edit();
            editor.putString(CAR_LOCATION, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPassword() {
        String data = PreferenceManager.getSharedPreferences().getString(PASSWORD, "");
        return data;

    }

    public static void setPassword(String value) {
        try {
            SharedPreferences.Editor editor = PreferenceManager
                    .getSharedPreferences().edit();
            editor.putString(PASSWORD, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
