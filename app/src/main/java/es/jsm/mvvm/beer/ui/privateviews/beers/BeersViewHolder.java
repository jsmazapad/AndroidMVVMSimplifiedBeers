package es.jsm.mvvm.beer.ui.privateviews.beers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewHolder;
import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.providers.ImagesProvider;


public class BeersViewHolder extends BaseRecyclerViewHolder<Beer> {

    private final TextView titleTV;
    private final TextView taglineTV;
    private final TextView abvTV;
    private final ImageView beerImageIV;

    protected BeersViewHolder(View v) {
        super(v);

        titleTV = v.findViewById(R.id.titleTV);
        taglineTV = v.findViewById(R.id.taglineTV);
        abvTV = v.findViewById(R.id.abvTV);
        beerImageIV = v.findViewById(R.id.beerImageIV);
    }

    @Override
    public void fillViewHolder(Beer beer) {
        titleTV.setText(beer.getName());
        taglineTV.setText(beer.getTagline());
        abvTV.setText(String.format("%sº", beer.getAbv()));
        //Si tiene imagen, intentamos carga perezosa de la misma
        if(beer.getImageUrl() != null && !"".equals(beer.getImageUrl())) {
            ImagesProvider.loadImageForListOrDetail(beer.getImageUrl(), beerImageIV, true);
        }


    }

}
