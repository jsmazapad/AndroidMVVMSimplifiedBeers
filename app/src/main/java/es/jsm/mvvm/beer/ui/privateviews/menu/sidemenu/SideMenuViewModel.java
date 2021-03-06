package es.jsm.mvvm.beer.ui.privateviews.menu.sidemenu;

import android.app.Application;
import android.content.Context;

import androidx.navigation.NavController;

import java.util.List;

import es.jsm.mvvm.beer.core.data.repositories.responses.ListResponse;
import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;
import es.jsm.mvvm.beer.repositories.BeersRepository;
import es.jsm.mvvm.beer.ui.privateviews.menu.MenuElement;

public class SideMenuViewModel extends BaseRecyclerViewModel<MenuElement, MenuElement> {

    public SideMenuViewModel(Application app) {
        super(app);
    }

    @Override
    public List<MenuElement> transformResponse(ListResponse<MenuElement> response) {
        return response.getResultList();
    }

    @Override
    public void onItemSelected(int position, NavController navController, Context context) {
        MenuElement element = elements.getValue().get(position);

        if (element.getMenuAction() == null) {
            navController.navigate(element.getNavDestiny());
        } else {
            element.getMenuAction().execute(context, navController);
        }
    }

    @Override
    public void setConstructorParameters(Object... args) {

    }

    @Override
    public void callRepositoryForData() {
        BeersRepository.getMenuElements(apiResponseRepositoryHolder, false);
    }


}
