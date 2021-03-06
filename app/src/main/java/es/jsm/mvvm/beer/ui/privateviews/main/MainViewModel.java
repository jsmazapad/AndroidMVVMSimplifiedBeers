package es.jsm.mvvm.beer.ui.privateviews.main;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;

import es.jsm.mvvm.beer.ui.privateviews.baseprivate.BasePrivateViewModel;


public class MainViewModel extends BasePrivateViewModel {

    private final MutableLiveData<String> versionApp = new MutableLiveData<>();


    public MainViewModel() {


    }

    public void setVisibleLateralMenuLogo(Context context, ImageView imageView) {
        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }

    }

    public MutableLiveData<String> getVersionApp() {
        return versionApp;
    }

    public void setVersionApp(String versionApps) {
        versionApp.setValue(versionApps);
    }


}
