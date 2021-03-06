package es.jsm.mvvm.beer.ui.privateviews.menu;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;

import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;

public class MenuAdapter extends BaseRecyclerAdapter<MenuElement, MenuViewHolder, MenuElement> {


    public MenuAdapter(Context context, BaseRecyclerViewModel viewModel, LifecycleOwner lifeCycleOwner, NavController navController, int listItemResource) {
        super(context, viewModel, lifeCycleOwner, navController, listItemResource);
    }

    @Override
    public MenuViewHolder instanceViewHolder(View v) {
        return new MenuViewHolder(v);
    }

}
