package es.jsm.mvvm.beer.ui.privateviews.about;


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

public class AboutFragment extends BaseFragment<FragmentAboutBinding, AboutViewModel> {

    @Override
    protected AboutViewModel getViewModel() {
        return  new ViewModelProvider(this).get(AboutViewModel.class);
    }

    @Override
    protected FragmentAboutBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater, container, savedInstanceState);

        return v;
    }


}
