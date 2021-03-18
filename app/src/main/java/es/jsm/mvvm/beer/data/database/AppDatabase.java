package es.jsm.mvvm.beer.data.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.model.Friend;

/**
 * Clase que representa a la base de datos
 */
@Database(entities = {Beer.class, Friend.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * Obtiene un DAO de la entidad Beer para realizar operaciones con la BD
     * @return
     */
    public abstract BeerDao beerDao();
    public abstract FriendDao friendDao();
}