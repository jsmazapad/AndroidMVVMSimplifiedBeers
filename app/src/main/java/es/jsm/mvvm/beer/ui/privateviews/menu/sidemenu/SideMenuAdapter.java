package es.jsm.mvvm.beer.ui.privateviews.menu.sidemenu;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;

import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;
import es.jsm.mvvm.beer.ui.privateviews.main.MainActivity;
import es.jsm.mvvm.beer.ui.privateviews.menu.MenuElement;


public class SideMenuAdapter extends BaseRecyclerAdapter<MenuElement, SideMenuElementVH, MenuElement> {

    private final Context context;

    public SideMenuAdapter(Context context, BaseRecyclerViewModel viewModel, LifecycleOwner lifeCycleOwner, NavController navController, int listItemResource) {
        super(context, viewModel, lifeCycleOwner, navController, listItemResource);
        this.context = context;
    }

    @Override
    public SideMenuElementVH instanceViewHolder(View v) {
        return new SideMenuElementVH(v);
    }

    @Override
    public void onBindViewHolder(SideMenuElementVH holder, int position) {
        MenuElement element = viewModel.getElements().getValue().get(position);
        holder.fillViewHolder(element);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                viewModel.onItemSelected(position, navController, v.getContext());
                if (context instanceof MainActivity) {
                    ((MainActivity) context).getDrawerLayout().closeDrawers();
                }

            }
        });

    }
}
