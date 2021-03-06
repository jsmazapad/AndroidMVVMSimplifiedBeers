package es.jsm.mvvm.beer.ui.privateviews.baseprivate;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.BaseActivity;

public abstract class BasePrivateActivity extends BaseActivity {

    private NavController navController;
    private BasePrivateViewModel viewModel;
    public NavController getNavController() {
        return navController;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        viewModel = new ViewModelProvider(this).get(BasePrivateViewModel.class);
        navController = Navigation.findNavController(this, R.id.nav_public_host_fragment);
    }
}
