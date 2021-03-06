package es.jsm.mvvm.beer.providers;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import java.io.IOException;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Clase para eliminar los datos de usuario de manera programática
 * sólo debe ser usada durante el desarrollo o en situaciones muy especiales
 */
public class ClearDataProvider {

    public static void clearData(Context context) {
        try {
            if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                ((ActivityManager) context.getSystemService(ACTIVITY_SERVICE))
                        .clearApplicationUserData(); // note: it has a return value!
            } else {
                String packageName = context.getPackageName();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear " + packageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
