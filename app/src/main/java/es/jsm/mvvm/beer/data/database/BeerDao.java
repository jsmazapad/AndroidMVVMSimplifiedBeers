package es.jsm.mvvm.beer.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.jsm.mvvm.beer.model.Beer;

/**
 * DAO para realizar operaciones con la BD,
 * a partir de esta interface se autogenera el código necesario
 */
@Dao
public interface BeerDao {

    /**
     * Selecciona todas las entidades de la BD
     * en este caso no realizamos order BY porque queremos un criterio uniforme
     * con otras fuentes de datos y lo ordenamos mediante programación
     * @return
     */
    @Query("SELECT * FROM "+ Beer.TABLE_NAME)
    List<Beer> getBeers();

    /**
     * Selecciona un registro mediante su id (externo)
     * @param id id de la entidad objetivo
     * @return
     */
    @Query("SELECT * FROM "+ Beer.TABLE_NAME + " WHERE id" + " = :id LIMIT 1")
    Beer selectById(int id);

    /**
     * Inserta un registro
     * @param beer
     */
    @Insert
    void insert(Beer beer);

    /**
     * Elimina un registro usando su id (externo)
     * @param id número de registros eliminados
     * @return
     */
    @Query("DELETE FROM " + Beer.TABLE_NAME + " WHERE id = :id")
    int deleteById(int id);
}
