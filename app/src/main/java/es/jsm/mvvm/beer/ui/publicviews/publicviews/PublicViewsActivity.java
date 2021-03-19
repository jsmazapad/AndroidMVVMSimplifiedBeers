package es.jsm.mvvm.beer.ui.publicviews.publicviews;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.ui.BaseActivity;

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
