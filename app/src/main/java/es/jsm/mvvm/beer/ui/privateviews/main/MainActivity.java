package es.jsm.mvvm.beer.ui.privateviews.main;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.utils.ModalMessage;
import es.jsm.mvvm.beer.databinding.ActivityMainBinding;
import es.jsm.mvvm.beer.databinding.NavHeaderMainBinding;
import es.jsm.mvvm.beer.ui.privateviews.baseprivate.BasePrivateActivity;
import es.jsm.mvvm.beer.ui.privateviews.menu.sidemenu.SideMenuAdapter;
import es.jsm.mvvm.beer.ui.privateviews.menu.sidemenu.SideMenuViewModel;


public class MainActivity extends BasePrivateActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private NavController navController;
    private MainViewModel viewModel;
    private SideMenuViewModel sideMenuViewModel;
    private RecyclerView sideMenuRV;
    private DrawerLayout drawerLayout;
    private ImageView closeDrawerIV;
    private Toolbar toolbar;
    private boolean backVisible;


    public NavController getNavController() {
        return navController;
    }

    @Override
    protected int getNavigationDiagramResource() {
        return R.id.nav_host_fragment;
    }

    public MainViewModel getViewModel() {
        return this.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setLifecycleOwner(this);
        NavigationView navView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_menu)
                .setOpenableLayout(drawerLayout)
                .build();

        viewModel.setVersionApp(recoveryVersion(getApplicationContext()));

        //MENU LATERAL
        sideMenuViewModel = new ViewModelProvider(this).get(SideMenuViewModel.class);
        sideMenuViewModel.getApiResponseMediator().observe(this, response -> {
        });
        sideMenuRV = navView.getHeaderView(0).findViewById(R.id.sideMenuRV);
        sideMenuRV.setLayoutManager(new LinearLayoutManager(this));
        sideMenuRV.setAdapter(new SideMenuAdapter(this, sideMenuViewModel, this, navController, R.layout.list_item_side_menu));
        binding.setMainViewModel(sideMenuViewModel);
        binding.setLifecycleOwner(this);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ViewCompat.setLayoutDirection(toolbar, ViewCompat.LAYOUT_DIRECTION_RTL);
        toolbar.setNavigationIcon(R.drawable.hamburger_icon);

        toolbar.setNavigationOnClickListener(v -> {
            manageDrawer();
        });

        closeDrawerIV = navView.getHeaderView(0).findViewById(R.id.closeDrawerIV);
        closeDrawerIV.setClickable(true);
        closeDrawerIV.setOnClickListener(v -> {
                    manageDrawer();
                }
        );


        //FLECHA ATRÁS
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            backVisible = destination.getId() != R.id.nav_menu;
            invalidateOptionsMenu();
        });

        //END FLECHA ATRÁS
        //Binding de cabecera
        NavHeaderMainBinding headerBinding = NavHeaderMainBinding.bind(binding.navView.getHeaderView(0));
        headerBinding.setViewModel(viewModel);
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public String recoveryVersion(Context context) {
        String versionName = "";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = "v" + pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    @Override
    public boolean onSupportNavigateUp() {

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (R.id.nav_menu == navController.getCurrentDestination().getId()) {
            ModalMessage.showModalMessage(this, getString(R.string.main_modal_exit_title), getString(R.string.main_modal_exit_text), null, (dialog, which) -> {
                viewModel.closeSession(this);

            }, getString(R.string.default_modal_cancelButton), null);

        } else {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                super.onBackPressed();
            }
        }
    }

    private void manageDrawer() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            drawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (backVisible) {
            inflater.inflate(R.menu.main, menu);
        }
        toolbar.setNavigationIcon(R.drawable.hamburger_icon_white);

        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.goBack) {
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void closeSession() {
        viewModel.closeSession(this);
    }

}
