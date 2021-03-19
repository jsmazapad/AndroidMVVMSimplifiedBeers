package es.jsm.mvvm.beer.ui.privateviews.articledetail;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import es.jsm.mvvm.beer.core.DefaultWebViewClient;
import es.jsm.mvvm.beer.core.ui.loading.LoadingViewModel;
import es.jsm.mvvm.beer.core.utils.ExternalActionsManager;
import es.jsm.mvvm.beer.core.utils.ModalMessage;

public class ArticleDetailViewModel extends LoadingViewModel  {

    private MutableLiveData<String> url = new MutableLiveData<>();

    public ArticleDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public DefaultWebViewClient getWebViewClient() {
        return new DefaultWebViewClient(this.isLoading, this.error);
    }

    public MutableLiveData<String> getUrl() {
        return url;
    }
}