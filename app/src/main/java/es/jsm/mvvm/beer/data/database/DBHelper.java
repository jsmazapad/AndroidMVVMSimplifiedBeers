package es.jsm.mvvm.beer.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.model.Friend;

/**
 * Helper para manipular la BD
 * nos abstrae de la tecnología empleada para la capa de datos
 */
public class DBHelper {

    private static DBHelper sharedDBPool;
    private static String DB_NAME = "db_beers";
    private static AppDatabase appDatabase;
    private DBHelper() {}

    public static void initialize(Context context) {
        sharedDBPool= new DBHelper();
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }

    /*
    BEERS
     */


    public static void insertBeer(final Beer beer) {
        if(getBeerById(beer) == null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    appDatabase.beerDao().insert(beer);
                    return null;
                }
            }.execute();
        }
    }

    public static List<Beer> getBeers() {
        return appDatabase.beerDao().getBeers();
    }


    public static Beer getBeerById(Beer beer) {
        return appDatabase.beerDao().selectById(beer.getId());
    }

    public static void deleteBeersById(Beer beer) {
        appDatabase.beerDao().deleteById(beer.getId());
    }

    public static void insertFriend(final Friend friend, MutableLiveData<Boolean> ended) {
        //Es muy dificil evitar duplicados porque puede cambiar el teléfono y el nombre,
        //realmente habría que guardar el id que se obtiene del proveedor de contenido
        // pero como mitigación añadimos esta condición
        if(getFriendByPhoneNumber(friend) == null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    appDatabase.friendDao().insert(friend);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    ended.setValue(true);
                }
            }.execute();
        }
    }

    /*
    FRIENDS
     */

    public static List<Friend> getFriends() {
        return appDatabase.friendDao().getFriends();
    }


    public static Friend getFriendByPhoneNumber(Friend friend) {
        return appDatabase.friendDao().selectByPhone(friend.getPhoneNumber());
    }

    public static void deleteFriendById(Friend friend) {
        appDatabase.friendDao().deleteById(friend.getInternalId());
    }

}
