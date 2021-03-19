package es.jsm.mvvm.beer.ui.privateviews.beerdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.ViewModelProvider;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.base.BaseFragment;
import es.jsm.mvvm.beer.databinding.FragmentBeersDetailBinding;



public class BeerDetailFragment extends BaseFragment<FragmentBeersDetailBinding, BeerDetailViewModel> {


    /**
     * Callback para saber cuando hay que regenerar el menú de opciones
     */
    private final Observable.OnPropertyChangedCallback isVisibleChangedCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            getActivity().invalidateOptionsMenu();
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setHasOptionsMenu(true);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        viewModel.getStoredBeerResponseHolder().observe(binding.getLifecycleOwner(), apiResponse ->viewModel.processStoredResponseData(apiResponse, getContext()));
        viewModel.checkInitialStoredState();
        return v;
    }

    @Override
    protected BeerDetailViewModel getViewModel() {
        BeerDetailViewModel vm = new ViewModelProvider(this).get(BeerDetailViewModel.class);
        vm.setBeer(BeerDetailFragmentArgs.fromBundle(getArguments()).getBeer());

        return vm;
    }

    @Override
    protected FragmentBeersDetailBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_beers_detail, container, false);
    }

/*
* Añadimos al viewmodel callbacks para saber cuando invalidar el menú de opciones y pintarlo de nuevo
* Es otro método distinto a liveData
 */
    @Override
    public void onStart() {
        super.onStart();
        binding.getViewModel().addOnPropertyChangedCallback(isVisibleChangedCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.removeOnPropertyChangedCallback(isVisibleChangedCallback);
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fav_menu, menu);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.toggleFavorite).setIcon(binding.getViewModel().getIsFavorite().getValue() ? R.drawable.ic_favorite : R.drawable.ic_favorite_empty);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.toggleFavorite){
            binding.getViewModel().triggerToggleStoredFavorite();
        }

        return super.onOptionsItemSelected(item);
    }
}
