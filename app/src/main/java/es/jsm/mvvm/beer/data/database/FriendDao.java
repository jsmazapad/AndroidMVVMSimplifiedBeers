package es.jsm.mvvm.beer.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.jsm.mvvm.beer.model.Friend;

/**
 * DAO para realizar operaciones con la BD,
 * a partir de esta interface se autogenera el código necesario
 */
@Dao
public interface FriendDao {

    /**
     * Selecciona todas las entidades de la BD
     * en este caso no realizamos order BY porque queremos un criterio uniforme
     * con otras fuentes de datos y lo ordenamos mediante programación
     * @return
     */
    @Query("SELECT * FROM "+ Friend.TABLE_NAME)
    List<Friend> getFriends();

    /**
     * Selecciona un registro mediante su teléfono
     * @param phoneNumber telefono de la entidad objetivo
     * @return
     */
    @Query("SELECT * FROM "+ Friend.TABLE_NAME + " WHERE phoneNumber LIKE" + " :phoneNumber LIMIT 1")
    Friend selectByPhone(String phoneNumber);


    /**
     * Inserta un registro
     * @param friend
     */
    @Insert
    void insert(Friend friend);

    /**
     * Elimina un registro usando su id (externo)
     * @param id
     * @return número de registros eliminados
     */
    @Query("DELETE FROM " + Friend.TABLE_NAME + " WHERE _ID = :id")
    int deleteById(int id);
}
