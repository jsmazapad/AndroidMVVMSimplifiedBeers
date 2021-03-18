package es.jsm.mvvm.beer.core.ui.location;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.config.PermissionsConfig;
import es.jsm.mvvm.beer.core.ui.loading.LoadingViewModel;
import es.jsm.mvvm.beer.core.utils.GpsUtils;
import es.jsm.mvvm.beer.core.utils.ModalMessage;


public class LocationViewModel extends LoadingViewModel {

    public LocationViewModel(Application app) {
        super(app);

    }

    //Localización

    private FusedLocationProviderClient mFusedLocationClient;
    private final int locationRequestCode = PermissionsConfig.LOCATION_REQUEST_CODE;
    private final int gpsRequestCode = PermissionsConfig.GPS_REQUEST_CODE;
    private MutableLiveData<Location> location = new MutableLiveData<>();
    private LocationRequest locationRequest;
    protected boolean isGpsEnabled = false;
    private GpsUtils gpsUtils;

    public MutableLiveData<Location> getLocation() {
        return location;
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {

            if (locationResult == null) {
                return;
            }
            int locationsSize = locationResult.getLocations().size();
            if (locationsSize >= 1) {
                setLocation(locationResult.getLocations().get(locationResult.getLocations().size() - 1));

                if (mFusedLocationClient != null) {
                    //Una vez obtenida la ubicación ya no es necesario seguir intentándolo
                    mFusedLocationClient.removeLocationUpdates(locationCallback);
                }

            }
        }

    };

    protected void setLocation(Location location) {
        this.location.setValue(location);
    }


    public void initLocationServices(Context context) {


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(2000);//2 segundos


        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ModalMessage.showModalMessage(context, context.getString(R.string.location_permission_asking_title), context.getString(R.string.location_permission_asking_text), "Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Fragment navHostFragment =  ((AppCompatActivity) context).getSupportFragmentManager().getPrimaryNavigationFragment();
                    if (navHostFragment != null) {
                        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);

                        fragment.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                locationRequestCode);
                    }
                }
            }, "En otro momento", null);


        } else {

            locateUser(context);
        }
    }

    protected void locateUser(Context context) {

        if (gpsUtils == null) {
            gpsUtils = new GpsUtils(context);
        }
        gpsUtils.turnGPSOn(isGPSEnable -> {
            // turn on GPS
            LocationViewModel.this.isGpsEnabled = isGPSEnable;
            if (isGPSEnable) {

                //Cuando pasamos por aquí ya tenemos permiso, no obstante colocamos esta condición para asegurarlo en el acceso
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ModalMessage.showError(context, context.getString(R.string.location_permission_error), null, null, null, null);
                    return;
                }
                mFusedLocationClient.getLocationAvailability().addOnSuccessListener(locationAvailability -> {


                    if (locationAvailability.isLocationAvailable()) {

                        mFusedLocationClient.getLastLocation().addOnSuccessListener((Activity) context, updatedLocation -> {
                            if (updatedLocation != null) {
                                setLocation(updatedLocation);
                            } else {
                                //Si no puede obtener la última ubicación triggea la obtención de ubicación
                                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                            }
                        });

                        mFusedLocationClient.getLastLocation().addOnFailureListener((Activity) context, failure -> {

                            ModalMessage.showError(context, context.getString(R.string.location_error), null, null, null, null);


                        });
                    } else {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                        // ModalMessage.showError(context, context.getString(R.string.location_error), null, null, null, null);

                    }
                });
            }
        });

    }


    public void handleRequestPermissions(Context context, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case locationRequestCode: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLocationServices(context);
                } else {

                    ModalMessage.showError(context, context.getString(R.string.location_permission_error), null, null, null, null);
                }
                break;
            }

        }

    }

    public void handleGpsActivationResult(Context context, int requestCode, int resultCode) {
        switch (requestCode) {
            case gpsRequestCode: {
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    initLocationServices(context);
                }
                break;
            }

        }

    }


}
