package es.jsm.mvvm.beer.ui.privateviews.about;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.loading.LoadingViewModel;
import es.jsm.mvvm.beer.core.utils.ExternalActionsManager;
import es.jsm.mvvm.beer.core.utils.ModalMessage;

public class AboutViewModel extends LoadingViewModel  {

    private String  beerVideoUrl = getApplication().getString(R.string.about_video_url);

    public AboutViewModel(@NonNull Application application) {
        super(application);
    }

    public void goToGithub(Context context){
        boolean result = ExternalActionsManager.openUrlExternal(context.getString(R.string.about_github_url), context);
        if(!result){
            ModalMessage.showError(context, context.getString(R.string.opening_web_browser_error), null, null, null, null);
        }
    }

    public String getBeerVideoUrl(){
        return beerVideoUrl;
    }

    public void setBeerVideoUrl(String beerVideoUrl) {
        this.beerVideoUrl = beerVideoUrl;
    }
}
