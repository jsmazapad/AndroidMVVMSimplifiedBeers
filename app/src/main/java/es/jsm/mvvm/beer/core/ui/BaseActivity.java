package es.jsm.mvvm.beer.core.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.ui.publicviews.PublicViewsViewModel;

/**
 * Clase base para Activities con jetpack navigation
 */
public abstract class BaseActivity<VM extends ViewModel> extends AppCompatActivity {

    private NavController navController;
    protected VM viewModel;
    public VM getViewModel() {
        return this.viewModel;
    }
    public NavController getNavController() {
        return navController;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);
        navController = Navigation.findNavController(this, getNavigationDiagramResource());
    }

    /**
     * Proporciona el diagrama de navegación que usa el activity
     * @return El recurso de neavegación del activity
     */
    protected abstract int getNavigationDiagramResource();

}
