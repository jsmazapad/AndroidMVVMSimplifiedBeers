package es.jsm.mvvm.beer.ui.privateviews.vehiclelocation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.model.LatLng;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.config.PermissionsConfig;
import es.jsm.mvvm.beer.core.ui.base.BaseFragment;
import es.jsm.mvvm.beer.databinding.FragmentVehicleLocationBinding;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class VehicleLocationFragment extends BaseFragment<FragmentVehicleLocationBinding, VehicleLocationViewModel> {

    @Override
    protected VehicleLocationViewModel getViewModel() {
        return new ViewModelProvider(this).get(VehicleLocationViewModel.class);
    }

    @Override
    protected FragmentVehicleLocationBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_vehicle_location, container, false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        viewModel.getLocation().removeObservers(this);
        viewModel.getLocation().observe(getViewLifecycleOwner(), location -> {
            if (location != null) {
                LatLng personalPosition = new LatLng(location.getLatitude(), location.getLongitude());
                viewModel.getAddressFromLocation(personalPosition, getContext());
            }
        });
        viewModel.getRequestPermission().removeObservers(this);
        viewModel.getRequestPermission().observe(getViewLifecycleOwner(), request -> {
            if (request != null ? request : false) {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},
                        PermissionsConfig.LOCATION_REQUEST_CODE);
            }
        });

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        viewModel.handleRequestPermissions(getContext(), requestCode, permissions, grantResults);
    }


}
