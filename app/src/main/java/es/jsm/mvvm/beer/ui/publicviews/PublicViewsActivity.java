package es.jsm.mvvm.beer.ui.publicviews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.BaseActivity;
import es.jsm.mvvm.beer.providers.ClearDataProvider;

public class PublicViewsActivity extends BaseActivity<PublicViewsViewModel>  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_public_views);
        viewModel = new ViewModelProvider(this).get(PublicViewsViewModel.class);

    }

    @Override
    protected int getNavigationDiagramResource() {
        return  R.id.nav_public_host_fragment;
    }

}
