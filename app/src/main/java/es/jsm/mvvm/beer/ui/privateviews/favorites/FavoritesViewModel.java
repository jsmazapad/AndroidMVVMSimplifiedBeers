package es.jsm.mvvm.beer.ui.privateviews.favorites;

import android.app.Application;
import android.content.Context;

import androidx.navigation.NavController;

import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.repositories.BeersRepository;
import es.jsm.mvvm.beer.ui.privateviews.beers.BeersViewModel;

public class FavoritesViewModel extends BeersViewModel {
    public FavoritesViewModel(Application app) {
        super(app);
        hideSearch = true;
    }
    @Override
    public void callRepositoryForData() {
        BeersRepository.getFavoriteBeers(apiResponseRepositoryHolder);
    }

    @Override
    public void onItemSelected(int position, NavController navController, Context context) {
        if (isLoading.getValue() == null || !isLoading.getValue()) {
            Beer beer = elements.getValue().get(position);
            navController.navigate(FavoritesFragmentDirections.actionNavFavoritesToNavBeerDetail(beer));
        }

    }
}
