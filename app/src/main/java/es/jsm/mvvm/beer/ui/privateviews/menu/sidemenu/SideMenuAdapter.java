package es.jsm.mvvm.beer.ui.privateviews.menu.sidemenu;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;

import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;
import es.jsm.mvvm.beer.ui.privateviews.main.MainActivity;
import es.jsm.mvvm.beer.ui.privateviews.menu.MenuElement;


public class SideMenuAdapter extends BaseRecyclerAdapter<MenuElement, SideMenuElementViewHolder, MenuElement> {

    private final Context context;

    public SideMenuAdapter(Context context, BaseRecyclerViewModel viewModel, LifecycleOwner lifeCycleOwner, NavController navController, int listItemResource) {
        super(context, viewModel, lifeCycleOwner, navController, listItemResource);
        this.context = context;
    }

    @Override
    public SideMenuElementViewHolder instanceViewHolder(View v) {
        return new SideMenuElementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SideMenuElementViewHolder holder, int position) {
        MenuElement element = viewModel.getElements().getValue().get(position);
        holder.fillViewHolder(element);
        holder.itemView.setOnClickListener(v -> {

            viewModel.onItemSelected(position, navController, context);
            if (context instanceof MainActivity) {
                ((MainActivity) context).getDrawerLayout().closeDrawers();
            }

        });

    }
}
