package es.jsm.mvvm.beer.data.network;

import es.jsm.mvvm.beer.core.data.network.responses.NetworkListResponse;
import es.jsm.mvvm.beer.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Interface con la definición de todas las llamadas al servicio web
 */
public interface BeersApiService {

    /**
     *
     * @param beerName Parámetro de búsqueda por nombre de tipo query (se envía por GET en la url)
     * @param abvMin Parámetro de búsqueda por mínima graduación en alcohol de tipo query (se envía por GET en la url)
     * @return Objeto que representa la llamada al servicio web
     */
    @GET("beers")
    @Headers({"Accept: application/json"})
    Call<NetworkListResponse<Beer>> getBeers(@Query("beer_name") String beerName, @Query("abv_gt") String abvMin);

}
