package es.jsm.mvvm.beer.ui.privateviews.beerdetail;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.snackbar.Snackbar;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.data.repositories.responses.ElementResponse;
import es.jsm.mvvm.beer.core.ui.loading.LoadingViewModel;
import es.jsm.mvvm.beer.core.utils.ModalMessage;
import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.repositories.BeersRepository;
import es.jsm.mvvm.beer.ui.privateviews.main.MainActivity;

public class BeerDetailViewModel extends LoadingViewModel implements Observable {

    private enum BeerDetailActions {
        CHECK,
        TOGGLE
    };
    private BeerDetailActions action = BeerDetailActions.CHECK;
    private final MutableLiveData<Beer> beer = new MutableLiveData<>();
    private final MutableLiveData<ElementResponse<Beer>> storedBeerResponseHolder = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFavorite;
    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();


    public BeerDetailViewModel(@NonNull Application application) {
        super(application);
        isFavorite = new MutableLiveData<>(false);
    }

    /**
     * Procesa un elementResponseObtenido de la fuente de permanencia donde se cachean los favoritos
     * @param apiResponse REspuesta
     * @param context Contexto donde se realiza la acción
     */
    public void processStoredResponseData(ElementResponse<Beer> apiResponse, Context context) {
        isLoading.setValue(false);
        if (apiResponse != null) {
            if (apiResponse.getError() == null) {
                if (action.equals(BeerDetailActions.CHECK)) {
                    if (apiResponse.getResultElement() != null) {
                        isFavorite.setValue(true);
                    } else {
                        isFavorite.setValue(false);
                    }
                } else if (action.equals(BeerDetailActions.TOGGLE)) {
                    toggleStoredFavorite(context, apiResponse.getResultElement());
                }
            } else {
                this.error.postValue(apiResponse.getError());
            }
        }
    }

    /**
     * Comprueba el estado inicial del elemento en la fuente de permanencia donde se cachean los favoritos
     */
    public void checkInitialStoredState(){
        action = BeerDetailActions.CHECK;
        isLoading.setValue(true);
        BeersRepository.getFavoriteBeer(storedBeerResponseHolder, beer.getValue());
    }

    public MutableLiveData<Beer> getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer.setValue(beer);
    }
    public LiveData<Boolean> getIsFavorite() {
        return isFavorite;
    }

    public LiveData<ElementResponse<Beer>> getStoredBeerResponseHolder() {
        return storedBeerResponseHolder;
    }

    /**
     * Dispara el proceso para insertar/eliminar el favorito de la fuente de datos donde está cacheado
     */
    public void triggerToggleStoredFavorite(){
        action = BeerDetailActions.TOGGLE;
        isLoading.setValue(true);
        BeersRepository.getFavoriteBeer(storedBeerResponseHolder, beer.getValue());
    }

    /**
     *  realiza el proceso para insertar/eliminar el favorito de la fuente de datos donde está cacheado
     * @param context contexto donde se ejecuta la acción
     * @param storedBeer Referencia al objeto cacheado, si es null es que no está registrado
     */
    public void toggleStoredFavorite(Context context, Beer storedBeer) {

        if (storedBeer == null) {
            BeersRepository.insertFavoriteBeer(beer.getValue());
            isFavorite.setValue(true);

            Toast.makeText(context, (beer.getValue().getName() != null ? beer.getValue().getName() : "") + " guardado en Favoritos", Toast.LENGTH_SHORT).show();
            notifyChange();
        } else {
            ModalMessage.showModalMessage(context, getApplication().getString(R.string.configuration_modal_confirmation_title), getApplication().getString(R.string.configuration_modal_favorite_delete), null, (dialog, which) -> {
                BeersRepository.deleteFavoriteBeer(beer.getValue());
                isFavorite.setValue(false);

                Snackbar.make(((MainActivity)context).getWindow().getDecorView().findViewById(android.R.id.content),(beer.getValue().getName() != null ? beer.getValue().getName() : "")  + " borrado de Favoritos", Snackbar.LENGTH_LONG).setAction(R.string.dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BeersRepository.insertFavoriteBeer(beer.getValue());
                        isFavorite.setValue(true);
                        notifyChange();
                    }
                }).show();
                //Toast.makeText(context, beer.getValue().getName() != null ? beer.getValue().getName() : ""  + " borrado de Favoritos", Toast.LENGTH_SHORT).show();
                notifyChange();
            }, null, (dialog, which)->{});
        }


    }

    @Override
    public void addOnPropertyChangedCallback(
            Observable.OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }


    @Override
    public void removeOnPropertyChangedCallback(
            Observable.OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    public void notifyChange() {
        callbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }






}
