package es.jsm.mvvm.beer.ui.privateviews.vehiclelocation;

import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.exceptions.BaseException;
import es.jsm.mvvm.beer.core.ui.location.LocationViewModel;
import es.jsm.mvvm.beer.core.utils.ExternalActionsManager;
import es.jsm.mvvm.beer.core.utils.ModalMessage;
import es.jsm.mvvm.beer.model.VehicleLocation;
import es.jsm.mvvm.beer.repositories.BeersRepository;


public class VehicleLocationViewModel extends LocationViewModel {

    private MutableLiveData<Boolean> requestPermission = new MutableLiveData<>(false);
    private MutableLiveData<String> addressString = new MutableLiveData<>();
    private MutableLiveData<VehicleLocation> vehicleLocation = new MutableLiveData<>();


    public VehicleLocationViewModel(@NonNull Application application) {
        super(application);
        BeersRepository.getVehicleLocation(vehicleLocation);
        if(vehicleLocation.getValue() != null){
            addressString.setValue(vehicleLocation.getValue().getAddress());
        }
    }

    public void locateVehicle(Context context) {
        initLocationServices(context);
    }

    public MutableLiveData<String> getAddressString() {
        return addressString;
    }

    public MutableLiveData<Boolean> getRequestPermission() {
        return requestPermission;
    }


    /**
     * Abre la aplicación de navegación de la app con la ruta almacenada del vehículo
     * @param context
     */
    public void goToNavigationApp(Context context) {
       boolean resultOk = ExternalActionsManager.openNavigationTo(vehicleLocation.getValue().getLatitude(), vehicleLocation.getValue().getLongitude(), context);
       if(!resultOk){
           ModalMessage.showError(context, context.getString(R.string.duplicated_contact_error) , null, null, null, null);

       }
    }


    /**
     * Obtiene mediante geocoding inverso la dirección en base a las coordenadas
     * @param location
     * @param context
     */
    public void getAddressFromLocation(
            LatLng location, final Context context) {

        final GeocoderHandler handler = new GeocoderHandler();

        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                Log.d("Point at AANDGEOCODER", location.latitude + " " + location.longitude);
                Address address = null;
                try {
                    List<Address> list = geocoder.getFromLocation(
                            location.latitude, location.longitude, 1);
                    if (list != null && list.size() > 0) {
                        address = list.get(0);
                        // sending back first address line and locality

                    }
                } catch (IOException e) {
                    Log.e("GEOCODER", "Impossible to connect to Geocoder", e);
                } finally {
                    Message msg = Message.obtain();
                    msg.setTarget(handler);
                    if (address != null) {
                        msg.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("address", address);
                        msg.setData(bundle);
                    } else
                        msg.what = 0;
                    msg.sendToTarget();
                }
            }
        };
        thread.start();
    }

    /**
     * Handler para el geocoder
     */
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            Address result;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    result = bundle.getParcelable("address");
                    if (result != null) {
                        Log.d("Point address handler", result.getThoroughfare() != null ? result.getThoroughfare() : "No hay thoroughfare");
                        Log.d("Point address handler", result.getMaxAddressLineIndex() > 0 ? result.getAddressLine(0) : "NA");
                    } else {
                        Log.d("Point address handler", "No point address");
                    }
                    break;
                default:
                    result = null;
            }
            isLoading.setValue(false);
            VehicleLocationViewModel.this.processAndroidAddressInverseGeocoded(result);
        }
    }


    /**
     * Procesa una dirección obtenida del geocoder
     * @param address
     */
    private void processAndroidAddressInverseGeocoded(Address address) {

        if (address != null) {
            String addressStringComposition = "";
            if(address.getMaxAddressLineIndex()>=0 && address.getAddressLine(0) != null )
            {
                addressStringComposition = address.getAddressLine(0).replace("España", "").replace("Spain", "");
            }else {


                if (address.getLocality() != null) {
                    addressStringComposition += " "+ address.getLocality();
                }

                if (address.getSubAdminArea() != null) {
                    addressStringComposition += ", " + address.getSubAdminArea();
                }
            }
            addressString.setValue(addressStringComposition);
            Location location = getLocation().getValue();
            if(location != null) {
                vehicleLocation.setValue(new VehicleLocation(location.getLatitude(), location.getLongitude(), addressStringComposition));
                BeersRepository.setVehicleLocation(vehicleLocation.getValue());
            }else{
                this.error.setValue(new BaseException(getApplication().getString(R.string.location_after_geocoder_data_error), false));
            }
        } else {
            this.error.setValue(new BaseException(getApplication().getString(R.string.eocoder_data_error), false));

        }
    }


}
