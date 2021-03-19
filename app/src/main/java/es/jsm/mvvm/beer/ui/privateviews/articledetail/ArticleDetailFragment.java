package es.jsm.mvvm.beer.ui.privateviews.articledetail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.base.BaseFragment;
import es.jsm.mvvm.beer.databinding.FragmentAboutBinding;
import es.jsm.mvvm.beer.databinding.FragmentArticleDetailBinding;
import es.jsm.mvvm.beer.ui.privateviews.beerdetail.BeerDetailFragmentArgs;

public class ArticleDetailFragment extends BaseFragment<FragmentArticleDetailBinding, ArticleDetailViewModel> {

    @Override
    protected ArticleDetailViewModel getViewModel() {
        ArticleDetailViewModel vm =  new ViewModelProvider(this).get(ArticleDetailViewModel.class);
        vm.getUrl().setValue(ArticleDetailFragmentArgs.fromBundle(getArguments()).getUrl());
        return vm;
    }

    @Override
    protected FragmentArticleDetailBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater, container, savedInstanceState);

        return v;
    }


}
