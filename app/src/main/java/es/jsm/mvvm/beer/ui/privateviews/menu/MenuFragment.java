package es.jsm.mvvm.beer.ui.privateviews.menu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.BaseActivity;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerFragment;
import es.jsm.mvvm.beer.databinding.FragmentHomeBinding;

public class MenuFragment extends BaseRecyclerFragment<FragmentHomeBinding, MenuViewModel> {


    @Override
    protected FragmentHomeBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding;

    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new MenuAdapter(getContext(), viewModel, this, ((BaseActivity) this.getActivity()).getNavController(), getListItemResourceId());
    }

    @Override
    protected MenuViewModel getViewModel() {
        return new ViewModelProvider(this).get(MenuViewModel.class);
    }

    @Override
    protected int getListItemResourceId() {
        return R.layout.list_item_menu;
    }
}