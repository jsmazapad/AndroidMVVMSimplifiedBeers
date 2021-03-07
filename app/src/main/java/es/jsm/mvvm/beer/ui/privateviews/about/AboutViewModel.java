package es.jsm.mvvm.beer.ui.privateviews.about;

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
import es.jsm.mvvm.beer.core.utils.ExternalActionsManager;
import es.jsm.mvvm.beer.core.utils.ModalMessage;

public class AboutViewModel extends LoadingViewModel  {

    private String  beerVideoUrl = "https://southernharvesthg.com/wp-content/uploads/Filling-glass-with-beer.mp4";

    public AboutViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToGithub(Context context){
        boolean result = ExternalActionsManager.openUrlExternal("https://github.com/jsmazapad", context);
        if(!result){
            ModalMessage.showError(context, "It was impossible to open your web browser, please verify that you have one installed and try again", null, null, null, null);
        }
    }

    public String getBeerVideoUrl(){
        return beerVideoUrl;
    }

    public void setBeerVideoUrl(String beerVideoUrl) {
        this.beerVideoUrl = beerVideoUrl;
    }
}
