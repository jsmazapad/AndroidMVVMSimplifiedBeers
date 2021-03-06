package es.jsm.mvvm.beer.ui.privateviews.favorites;

import androidx.lifecycle.ViewModelProvider;

import es.jsm.mvvm.beer.ui.privateviews.beers.BeersFragment;
import es.jsm.mvvm.beer.ui.privateviews.beers.BeersViewModel;

public class FavoritesFragment  extends BeersFragment {

    @Override
    protected BeersViewModel getViewModel() {
        return new ViewModelProvider(this).get(FavoritesViewModel.class);
    }
}
