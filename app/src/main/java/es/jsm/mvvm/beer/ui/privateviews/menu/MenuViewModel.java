package es.jsm.mvvm.beer.ui.privateviews.menu;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.List;

import es.jsm.mvvm.beer.core.data.repositories.responses.ListResponse;
import es.jsm.mvvm.beer.core.ui.base.BaseViewModel;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;
import es.jsm.mvvm.beer.repositories.BeersRepository;

public class MenuViewModel extends BaseRecyclerViewModel<MenuElement, MenuElement> {


    public MenuViewModel(Application app) {
        super(app, null);
    }

    @Override
    public List<MenuElement> transformResponse(ListResponse<MenuElement> response) {
        return response.getResultList();
    }

    @Override
    public void onItemSelected(int position, NavController navController, Context c) {
        MenuElement element = this.elements.getValue().get(position);
        if (element.getMenuAction() == null) {
            navController.navigate(element.getNavDestiny());
        } else {
            element.getMenuAction().execute(c, navController);
        }
    }

    @Override
    public void setConstructorParameters(Object... args) {

    }

    @Override
    public void callRepositoryForData() {
        BeersRepository.getMenuElements(apiResponseRepositoryHolder, true);

    }
}