package es.jsm.mvvm.beer.ui.privateviews.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.BaseActivity;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerFragment;
import es.jsm.mvvm.beer.databinding.FragmentBeersBinding;
import es.jsm.mvvm.beer.databinding.FragmentNewsBinding;


public class NewsFragment extends BaseRecyclerFragment<FragmentNewsBinding, NewsViewModel> {

    @Override
    protected FragmentNewsBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        FragmentNewsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        return binding;
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new NewsAdapter(getContext(), viewModel, this, ((BaseActivity) this.getActivity()).getNavController(), getListItemResourceId());
    }

    @Override
    protected NewsViewModel getViewModel() {
        return new ViewModelProvider(this).get(NewsViewModel.class);
    }

    @Override
    protected int getListItemResourceId() {
        return R.layout.list_item_article;
    }

}
