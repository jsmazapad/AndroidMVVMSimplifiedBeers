package es.jsm.mvvm.beer.ui.privateviews.beers;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;

import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;
import es.jsm.mvvm.beer.model.Beer;


public class BeersAdapter extends BaseRecyclerAdapter<Beer, BeersViewHolder, Beer> {

    public BeersAdapter(Context context, BaseRecyclerViewModel viewModel, LifecycleOwner lifeCycleOwner, NavController navController, int listItemResource) {
        super(context, viewModel, lifeCycleOwner, navController, listItemResource);
    }

    @Override
    public BeersViewHolder instanceViewHolder(View v) {
        return new BeersViewHolder(v);
    }
}
