package es.jsm.mvvm.beer;

import android.app.Application;

import es.jsm.mvvm.beer.data.database.DBHelper;
import es.jsm.mvvm.beer.providers.PreferencesProvider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            DBHelper.initialize(this);
            es.jsm.mvvm.beer.core.utils.PreferenceManager.initialize(this);
            //Si todavía no ha guardado registro de password, lo guarda,
            //Se podría haber hecho la comprobación contra una constante
            //Pero así se ilustra el uso de SharedPreferences
            String password = PreferencesProvider.getPassword();
            if(password == null || "".equals(password)){
                PreferencesProvider.setPassword("1234");
            }
        } catch (Exception e) {
            //No se debe llegar a este punto nunca
            e.printStackTrace();
        }
    }
}
