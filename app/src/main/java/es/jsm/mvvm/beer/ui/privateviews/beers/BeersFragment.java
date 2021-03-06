package es.jsm.mvvm.beer.ui.privateviews.beers;

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


public class BeersFragment extends BaseRecyclerFragment<FragmentBeersBinding, BeersViewModel> {

    @Override
    protected FragmentBeersBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        FragmentBeersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beers, container, false);
        return binding;
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new BeersAdapter(getContext(), viewModel, this, ((BaseActivity) this.getActivity()).getNavController(), getListItemResourceId());
    }

    @Override
    protected BeersViewModel getViewModel() {
        return new ViewModelProvider(this).get(BeersViewModel.class);
    }

    @Override
    protected int getListItemResourceId() {
        return R.layout.list_item_beer;
    }

}
