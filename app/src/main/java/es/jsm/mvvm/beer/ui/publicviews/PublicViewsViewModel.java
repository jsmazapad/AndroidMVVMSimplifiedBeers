package es.jsm.mvvm.beer.ui.publicviews;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;

public class PublicViewsViewModel extends AndroidViewModel {

    public PublicViewsViewModel(Application application) {
        super(application);
        Log.d("ok", "ok");
    }

    public void goWebView(Context context) {

    }

}
