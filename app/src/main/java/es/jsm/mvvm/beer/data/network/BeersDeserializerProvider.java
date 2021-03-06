package es.jsm.mvvm.beer.data.network;

import com.google.gson.GsonBuilder;

import es.jsm.mvvm.beer.core.data.network.CustomDeserializerProvider;
import es.jsm.mvvm.beer.core.data.network.responses.NetworkElementResponse;
import es.jsm.mvvm.beer.core.data.network.responses.NetworkListResponse;
import es.jsm.mvvm.beer.data.network.deserializers.ListDeserializer;

import es.jsm.mvvm.beer.model.Beer;

/**
 * Clase que provee de los deserializadores que se emplearán
 * Aquí se registran todos los deserializadores
 */
public class BeersDeserializerProvider implements CustomDeserializerProvider {

    public GsonBuilder registerCustomDeserializers(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(NetworkListResponse.class, new ListDeserializer<Beer>());
        return gsonBuilder;
    }
}
