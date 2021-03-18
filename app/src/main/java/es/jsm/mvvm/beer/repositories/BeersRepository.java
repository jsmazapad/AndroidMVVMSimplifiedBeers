package es.jsm.mvvm.beer.repositories;

import androidx.lifecycle.MutableLiveData;

import es.jsm.mvvm.beer.BuildConfig;
import es.jsm.mvvm.beer.core.data.network.RetrofitService;
import es.jsm.mvvm.beer.core.data.network.responses.NetworkListResponse;
import es.jsm.mvvm.beer.core.data.repositories.responses.ElementResponse;
import es.jsm.mvvm.beer.core.data.repositories.responses.ListResponse;
import es.jsm.mvvm.beer.data.database.DBHelper;
import es.jsm.mvvm.beer.data.network.BeersApiService;
import es.jsm.mvvm.beer.data.network.BeersDeserializerProvider;
import es.jsm.mvvm.beer.data.network.BeersNetworkErrorTreatment;
import es.jsm.mvvm.beer.data.network.FeedsApiService;
import es.jsm.mvvm.beer.data.network.deserializers.FeedXMEntity;
import es.jsm.mvvm.beer.model.Article;
import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.model.Friend;
import es.jsm.mvvm.beer.model.VehicleLocation;
import es.jsm.mvvm.beer.providers.MenuProvider;
import es.jsm.mvvm.beer.providers.PreferencesProvider;
import es.jsm.mvvm.beer.ui.privateviews.menu.MenuElement;
import retrofit2.Call;

public class BeersRepository {

    private static final BeersApiService beersCatalogService = RetrofitService.createService(BeersApiService.class, new BeersNetworkErrorTreatment(), new BeersDeserializerProvider(), BuildConfig.BASE_URL);
    private static final FeedsApiService beersFeedService = RetrofitService.createService(FeedsApiService.class, new BeersNetworkErrorTreatment(), new BeersDeserializerProvider(), BuildConfig.FEED_URL);
    private static final String FEED_FORMAT = "xml";

    /**
     * Obtiene los elementos del menú de manera reactiva
     *
     * @param responseMutableLiveData livedata donde se setean los elementos
     * @param isMainMenu              Si es true indica que se están solicitando para el menú principal, si es false indica que se solicitan para el menú lateral
     */
    public static void getMenuElements(MutableLiveData<ListResponse<MenuElement>> responseMutableLiveData, boolean isMainMenu) {
        responseMutableLiveData.setValue(new ListResponse<>(MenuProvider.getMenuElements(isMainMenu)));
    }

    /**
     * Obtiene el catálogo de cervezas del servidor
     *
     * @param responseLiveData livedata donde se setean los elementos
     * @param nameFilter       Nombre por el que se desea buscar (puede ser parcial)
     * @param abvFilter        Min grado de alcohol por el que se desea buscar
     */
    public static void getBeers(MutableLiveData<ListResponse<Beer>> responseLiveData, String nameFilter, String abvFilter) {
        if (nameFilter != null) {
            if (nameFilter.trim().equals("")) {
                //Lo seteamos a nulo para que el servicio no lo tenga en cuenta al generar los parámetros de la query
                nameFilter = null;
            } else {
                //Reemplazamos los carácteres de espacio por _
                nameFilter = nameFilter.replace(" ", "_");
            }

            if (abvFilter == null || abvFilter.equals("")) {
                abvFilter = "0.0";
            }
        }
        Call<NetworkListResponse<Beer>> call = beersCatalogService.getBeers(nameFilter, abvFilter);
        call.enqueue(RetrofitService.createListCallBack(Beer.class, responseLiveData));
    }

    /**
     * Obtiene un listado de las cervezas favoritas
     *
     * @param responseLiveData livedata donde se setean los elementos
     */
    public static void getFavoriteBeers(MutableLiveData<ListResponse<Beer>> responseLiveData) {
        responseLiveData.setValue(new ListResponse<Beer>(DBHelper.getBeers()));
    }

    /**
     * Obtiene el registro del listado de favoritos  asociado a una cerveza
     *
     * @param responseLiveData livedata donde se setean los elementos
     * @param beer             cerveza a buscar
     */
    public static void getFavoriteBeer(MutableLiveData<ElementResponse<Beer>> responseLiveData, Beer beer) {
        responseLiveData.setValue(new ElementResponse<>(DBHelper.getBeerById(beer)));
    }

    /**
     * Inserta una cerveza en favoritos
     *
     * @param beer TODO: Como mejora, en próximas versiones hacer uso de livedata para que sea compatible con integración con operaciones de servidor
     */
    public static void insertFavoriteBeer(Beer beer) {
        DBHelper.insertBeer(beer);
    }

    /**
     * Elimina una cerveza en favoritos
     *
     * @param beer TODO: Como mejora, en próximas versiones hacer uso de livedata para que sea compatible con integración con operaciones de servidor
     */
    public static void deleteFavoriteBeer(Beer beer) {
        DBHelper.deleteBeersById(beer);
    }


    /**
     * Obtiene un listado de las cervezas favoritas
     *
     * @param responseLiveData livedata donde se setean los elementos
     */
    public static void getFriends(MutableLiveData<ListResponse<Friend>> responseLiveData) {
        responseLiveData.setValue(new ListResponse<Friend>(DBHelper.getFriends()));
    }

    /**
     * Inserta una cerveza en favoritos
     *
     * @param friend TODO: Como mejora, en próximas versiones hacer uso de livedata para que sea compatible con integración con operaciones de servidor
     */
    public static void insertFriend(Friend friend, MutableLiveData<Boolean> ended) {
        DBHelper.insertFriend(friend, ended);
    }

    /**
     * Devuelve el registro del amigo comparándolo por número de teléfono
     *
     * @param friend TODO: Como mejora, en próximas versiones hacer uso de livedata para que sea compatible con integración con operaciones de servidor
     */
    public static Friend getFriendByPhoneNumber(Friend friend) {
        return DBHelper.getFriendByPhoneNumber(friend);
    }

    /**
     * Elimina una cerveza en favoritos
     *
     * @param friend TODO: Como mejora, en próximas versiones hacer uso de livedata para que sea compatible con integración con operaciones de servidor
     */
    public static void deleteFriend(Friend friend) {
        DBHelper.deleteFriendById(friend);
    }

    public static void getFeeds(MutableLiveData<ListResponse<Article>> responseLiveData) {
        Call<FeedXMEntity> call = beersFeedService.getBeerFeeds(FEED_FORMAT);
        call.enqueue(RetrofitService.createXMLElementToListAutoConversionCallBack(FeedXMEntity.class, responseLiveData));
    }

    /**
     * Devuelve el registro de la localización de un vehículo mediante programación reactiva
     *
     * @param location
     */
    public static void getVehicleLocation(MutableLiveData<VehicleLocation> location) {
        location.setValue(PreferencesProvider.getVehicleLocation());
    }

    /**
     * Almacena el registro de la localización de un vehículo
     *
     * @param location TODO: Como mejora, en próximas versiones hacer uso de livedata para que sea compatible con integración con operaciones de servidor
     */
    public static void setVehicleLocation(VehicleLocation location) {
        PreferencesProvider.setVehicleLocation(location);
    }


}
