package es.jsm.mvvm.beer.ui.privateviews.menu.sidemenu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewHolder;
import es.jsm.mvvm.beer.ui.privateviews.menu.MenuElement;


class SideMenuElementViewHolder extends BaseRecyclerViewHolder<MenuElement> {

    private final TextView sideMenuTV;
    private final ImageView sidemenuIV;


    protected SideMenuElementViewHolder(View v) {
        super(v);
        sideMenuTV = v.findViewById(R.id.sideMenuTV);
        sidemenuIV = v.findViewById(R.id.sideMenuIV);
    }

    @Override
    public void fillViewHolder(MenuElement element) {
      
        sideMenuTV.setText(element.getName().toUpperCase());
        sidemenuIV.setImageResource(element.getImageResourceSideMenu());
        sidemenuIV.setColorFilter(ContextCompat.getColor(sidemenuIV.getContext(), element.getColor()));
    }
}
