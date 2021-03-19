package es.jsm.mvvm.beer.ui.privateviews.news;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;

import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;
import es.jsm.mvvm.beer.model.Article;
import es.jsm.mvvm.beer.model.Beer;


public class NewsAdapter extends BaseRecyclerAdapter<Article, NewsViewHolder, Beer> {

    public NewsAdapter(Context context, BaseRecyclerViewModel viewModel, LifecycleOwner lifeCycleOwner, NavController navController, int listItemResource) {
        super(context, viewModel, lifeCycleOwner, navController, listItemResource);
    }

    @Override
    public NewsViewHolder instanceViewHolder(View v) {
        return new NewsViewHolder(v);
    }
}
