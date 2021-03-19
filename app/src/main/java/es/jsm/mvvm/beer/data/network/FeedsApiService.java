package es.jsm.mvvm.beer.data.network;

import es.jsm.mvvm.beer.data.network.deserializers.xmlwrapper.FeedXMEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FeedsApiService {

    @GET("CraftBeerAndBrewingMagazine")
    Call<FeedXMEntity> getBeerFeeds(@Query("format") String format);
}
