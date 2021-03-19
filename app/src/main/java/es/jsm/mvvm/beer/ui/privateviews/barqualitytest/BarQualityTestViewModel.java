package es.jsm.mvvm.beer.ui.privateviews.barqualitytest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import es.jsm.mvvm.beer.config.GeneralConfig;
import es.jsm.mvvm.beer.core.ui.loading.LoadingViewModel;

public class BarQualityTestViewModel extends LoadingViewModel implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;

    private MutableLiveData<Float> rating = new MutableLiveData<>(0f);
    private MutableLiveData<Boolean> testing = new MutableLiveData<>(false);
    private ArrayList<Float> lightData = new ArrayList<>();
    public BarQualityTestViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Float> getRating() {
        return rating;
    }

    public MutableLiveData<Boolean> getTesting() {
        return testing;
    }

    public void initSensors(Activity act){
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
         sensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
         lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    public void performTest(){
        lightData = new ArrayList<>();
        testing.setValue(true);
        isLoading.setValue(true);
        registerSensorListener();


    }

    public void registerSensorListener(){
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void unregisterSensorListener(){
        sensorManager.unregisterListener(this);
    }


    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float lightInLx = event.values[0];
        // Do something with this sensor data.
        Log.d("LIGHT", lightInLx + " lx");
        if(testing.getValue()){
            lightData.add(lightInLx);
            //Tenemos 15 muestras, finaliza el test
            if(lightData.size() >= GeneralConfig.LIGHT_SENSOR_TEST_SAMPLES_NUM){
                unregisterSensorListener();
                testing.setValue(false);
                isLoading.setValue(false);
                //Calculamos la media
                Float total = 0.0f;
                for(Float value:lightData){
                    total+=value;
                }
                total = total/lightData.size();
                //Convertimos el valor a rating
                if(total >= GeneralConfig.MAX_LIGHT_FOR_SENSOR){
                    rating.setValue(1f);
                }else if(total <= GeneralConfig.MIN_LIGHT_FOR_SENSOR) {
                    rating.setValue(5f);
                }else{
                    rating.setValue(5 - ((total-GeneralConfig.MIN_LIGHT_FOR_SENSOR)/5));
                }

            }
        }
    }



}
