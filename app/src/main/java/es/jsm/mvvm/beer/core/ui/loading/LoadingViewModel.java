package es.jsm.mvvm.beer.core.ui.loading;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import es.jsm.mvvm.beer.core.ui.base.BaseViewModel;

/**
 * Clase base para viewModel que provee de la funcionalidad de carga
 */
public abstract class LoadingViewModel extends BaseViewModel {

    protected MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LoadingViewModel(Application app) {
        super(app);
    }


}
