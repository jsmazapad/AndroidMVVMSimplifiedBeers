package es.jsm.mvvm.beer.ui.privateviews.friends;

import android.Manifest;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.config.PermissionsConfig;
import es.jsm.mvvm.beer.core.ui.BaseActivity;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerAdapter;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerFragment;
import es.jsm.mvvm.beer.core.utils.ModalMessage;
import es.jsm.mvvm.beer.databinding.FragmentFriendsBinding;

import android.widget.Button;
import android.widget.Spinner;


public class FriendsFragment extends BaseRecyclerFragment<FragmentFriendsBinding, FriendsViewModel> implements
        LoaderManager.LoaderCallbacks<Cursor>{

    Spinner contactSpinner;


    @Override
    protected FragmentFriendsBinding getDataBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        FragmentFriendsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friends, container, false);
        return binding;
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new FriendsAdapter(getContext(), viewModel, this, ((BaseActivity) this.getActivity()).getNavController(), getListItemResourceId(), viewModel);
    }

    @Override
    protected FriendsViewModel getViewModel() {
        return new ViewModelProvider(this).get(FriendsViewModel.class);
    }

    @Override
    protected int getListItemResourceId() {
        return R.layout.list_item_friend;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  super.onCreateView(inflater, container, savedInstanceState);
        //Comprobamos permisos, si no los tiene, los solicitamos
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    PermissionsConfig.CONTACTS_REQUEST_CODE);

        } else {
           //Tiene los permisos, ejecutamos la acción
            // Initializes the loader
            LoaderManager.getInstance(this).initLoader(0, null, this);
        }
        //Suscripción al mediador para los cambios en recycler
        viewModel.getChangeEndedMediator().observe(getViewLifecycleOwner(), result -> {
        });
        //Seteamos los componentes de la vista
        contactSpinner = v.findViewById(R.id.contactsSpinner);
        // Gets a CursorAdapter
        viewModel.initCursor(getActivity());
        contactSpinner.setAdapter(viewModel.getCursorAdapter());
        contactSpinner.setPrompt("Escoja el contacto a añadir:");
        Button addContactButton = v.findViewById(R.id.addFriendButton);
        addContactButton.setOnClickListener((view)->{contactSpinner.performClick();});
        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionsConfig.CONTACTS_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   //Tiene los permisos, ejecutamos la acción
                    // Initializes the loader
                    LoaderManager.getInstance(this).initLoader(0, new Bundle(), this);
                } else {
                    ModalMessage.showError(this.getContext(), getString(R.string.contact_permission_error), null, null, null, null);
                }
                break;
            }
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {


        // Starts the query
        return viewModel.getCursorLoader(getActivity());


    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
       viewModel.loadContactData(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
       viewModel.resetContactData();

    }


}
