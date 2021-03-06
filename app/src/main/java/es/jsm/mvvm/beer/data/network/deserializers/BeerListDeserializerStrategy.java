package es.jsm.mvvm.beer.data.network.deserializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.jsm.mvvm.beer.core.data.network.DeserializerListStrategyInterface;
import es.jsm.mvvm.beer.model.Beer;


/**
 * Estategia de deserialziación para arrays que contengan objetos de la entidad Beer
 */
public class BeerListDeserializerStrategy implements DeserializerListStrategyInterface<Beer> {

    @Override
    public List<Beer> readItems(JsonArray jsonArray) {
        List<Beer> items;
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Type listType = new TypeToken<List<Beer>>(){}.getType();
        items = gson.fromJson(jsonArray, listType);
//
//        for (JsonElement element : jsonArray) {
//            Beer item = gson.fromJson(element.getAsJsonObject(), Beer.class);
//            items.add(item);
//        }

        return items;
    }
}
