package es.jsm.mvvm.beer.ui.privateviews.beers;

import android.app.Application;
import android.content.Context;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;

import com.google.android.material.slider.Slider;

import java.util.Collections;
import java.util.List;

import es.jsm.mvvm.beer.config.GeneralConfig;
import es.jsm.mvvm.beer.config.SortListConfig;
import es.jsm.mvvm.beer.core.data.repositories.responses.ListResponse;
import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.repositories.BeersRepository;
import es.jsm.mvvm.beer.ui.privateviews.baseprivate.BasePrivateRecyclerViewModel;

public class BeersViewModel extends BasePrivateRecyclerViewModel<Beer, Beer> {

    protected boolean hideSearch = false;
    public static final String decimalFormat = "%.1f";
    private Float MaxAbv= GeneralConfig.MAX_ABV_FOR_SLIDER;
    private MutableLiveData<String>  nameFilter;
    private MutableLiveData<Float> abvFilter;
    private Slider.OnChangeListener sliderListener = (slider, value, fromUser) -> {
        abvFilter.setValue(value);
        callRepositoryForData();
    };


    public BeersViewModel(Application app) {
        super(app);

    }

    @Override
    public List<Beer> transformResponse(ListResponse<Beer> response) {
        List<Beer> list = response.getResultList();
        //Ordenamos según criterio de configuración el listado
        Collections.sort(list, SortListConfig.BEERS_COMPARATOR);
        return list;
    }

    @Override
    public void onItemSelected(int position, NavController navController, Context context) {
        if (isLoading.getValue() == null || !isLoading.getValue()) {
            Beer beer = elements.getValue().get(position);
            navController.navigate(BeersFragmentDirections.actionNavBeersToNavBeerDetail(beer));
        }
    }

    @Override
    public void setConstructorParameters(Object... args) {
        nameFilter = new MutableLiveData<>();
        abvFilter = new MutableLiveData<>(0.0f);
    }

    @Override
    public void callRepositoryForData() {

        BeersRepository.getBeers(apiResponseRepositoryHolder, nameFilter.getValue(), String.valueOf(abvFilter.getValue()));
    }


    public boolean isHideSearch() {
        return hideSearch;
    }

    public Float getMaxAbv() {
        return MaxAbv;
    }

    public MutableLiveData<String> getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter.setValue(nameFilter);
    }

    public MutableLiveData<Float> getAbvFilter() {
        return abvFilter;
    }

    public void setAbvFilter(Float abvFilter) {
        this.abvFilter.setValue(abvFilter);
    }

    public Slider.OnChangeListener getSliderListener() {
        return sliderListener;
    }

    public void onFilterTextChanged(CharSequence text) {
        String textValue = text.length() > 0 ? "" + text : null;
        nameFilter.setValue(textValue);
        //Solo se llama al repositorio si hay un cambio con el texto
            callRepositoryForData();
    }


}
