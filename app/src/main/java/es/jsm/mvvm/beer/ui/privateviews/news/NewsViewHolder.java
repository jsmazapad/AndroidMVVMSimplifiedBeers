package es.jsm.mvvm.beer.ui.privateviews.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.binding.CustomBindings;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewHolder;
import es.jsm.mvvm.beer.model.Article;
import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.providers.ImagesProvider;


public class NewsViewHolder extends BaseRecyclerViewHolder<Article> {

    private final TextView titleTV;
    private final TextView descTV;
    private final TextView authorTV;

    protected NewsViewHolder(View v) {
        super(v);

        titleTV = v.findViewById(R.id.titleTV);
        descTV = v.findViewById(R.id.descTV);
        authorTV = v.findViewById(R.id.authorTV);
    }

    @Override
    public void fillViewHolder(Article article) {
        titleTV.setText(article.getTitle());
        CustomBindings.htmlClearedText( descTV,article.getDescription() );
        authorTV.setText(article.getAuthor());
    }

}
