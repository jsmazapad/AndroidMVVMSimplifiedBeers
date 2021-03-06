package es.jsm.mvvm.beer;

import android.app.Application;

import es.jsm.mvvm.beer.data.database.DBHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            DBHelper.initialize(this);
            es.jsm.mvvm.beer.core.utils.PreferenceManager.initialize(this);
        } catch (Exception e) {
            //Si llegamos a este punto algo ha ido muy mal.
            // En cuanto se acceda al PreferenceManager la app debe cerrarse.
            e.printStackTrace();
        }
    }
}
