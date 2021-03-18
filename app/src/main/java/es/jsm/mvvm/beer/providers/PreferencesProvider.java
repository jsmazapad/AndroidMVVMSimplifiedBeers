package es.jsm.mvvm.beer.providers;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import es.jsm.mvvm.beer.core.utils.PreferenceManager;
import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.model.VehicleLocation;

/**
 * Almacen central de preferencias de usuario
 * Hace uso de sharedpreferences
 */
public class PreferencesProvider {

    private final static String CAR_LOCATION = "CAR_LOCATION";
    private final static String PASSWORD = "PASSWORD";


    /**
     * Obtiene la localización del vehículo guardada en SharedPreferences
     * @return
     */
    public static VehicleLocation getVehicleLocation() {
        String data = PreferenceManager.getSharedPreferences().getString(CAR_LOCATION, "");
        if (!"".equals(data)) {
            Gson gson = new GsonBuilder()
                    .create();
            return gson.fromJson(data, VehicleLocation.class);
        }else {
            return null;
        }

    }

    public static void setVehicleLocation(VehicleLocation location) {
        try {
            Gson gson = new GsonBuilder()
                    .create();
            String value = gson.toJson(location);
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
