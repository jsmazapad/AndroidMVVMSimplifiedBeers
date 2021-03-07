package es.jsm.mvvm.beer.ui.privateviews.barqualitytest;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.base.BaseFragment;
import es.jsm.mvvm.beer.databinding.FragmentBarQualityTestBinding;

public class BarQualityTestFragment extends BaseFragment<FragmentBarQualityTestBinding, BarQualityTestViewModel> {

    @Override
    protected BarQualityTestViewModel getViewModel() {
        return  new ViewModelProvider(this).get(BarQualityTestViewModel.class);
    }

    @Override
    protected FragmentBarQualityTestBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_bar_quality_test, container, false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater, container, savedInstanceState);
        viewModel.initSensors(getActivity());
        return v;
    }

    @Override
    public void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        if (viewModel.getTesting().getValue()) {
            viewModel.registerSensorListener();
        }
    }

    @Override
    public void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        viewModel.unregisterSensorListener();
    }

}
