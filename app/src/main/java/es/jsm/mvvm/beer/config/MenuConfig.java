package es.jsm.mvvm.beer.config;

import android.content.Context;

import androidx.navigation.NavController;

import java.util.ArrayList;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.utils.ModalMessage;
import es.jsm.mvvm.beer.ui.privateviews.main.MainActivity;
import es.jsm.mvvm.beer.ui.privateviews.menu.MenuAction;
import es.jsm.mvvm.beer.ui.privateviews.menu.MenuElement;

/**
 * Configuración de menú
 */
public class MenuConfig {
    /**
     * elementos que se mostrarán en menú
     */
    public static ArrayList<MenuElement> menuElements = new ArrayList<MenuElement>() {
        {
            add(new MenuElement("Home", "", R.color.favourites, MenuElement.ElementGravity.RIGHT, R.drawable.ic_home, R.drawable.ic_home, R.id.nav_menu, true, false, null));
            add(new MenuElement("Catalogue", "", R.color.catalogue, MenuElement.ElementGravity.LEFT, R.drawable.ic_catalog, R.drawable.ic_catalog, R.id.nav_catalog, true, true, null));
            add(new MenuElement("Favourites", "", R.color.favourites, MenuElement.ElementGravity.RIGHT, R.drawable.ic_fav_beers, R.drawable.ic_fav_beers, R.id.nav_favorites, true, true, null));
            add(new MenuElement("Friends", "", R.color.friends, MenuElement.ElementGravity.LEFT, R.drawable.ic_friends, R.drawable.ic_friends, R.id.nav_friends, true, true, null));
            add(new MenuElement("News", "", R.color.news, MenuElement.ElementGravity.RIGHT, R.drawable.ic_news, R.drawable.ic_news, R.id.nav_news, true, true, null));
            add(new MenuElement("Where is my car", "", R.color.locate_car, MenuElement.ElementGravity.LEFT, R.drawable.ic_car, R.drawable.ic_car, R.id.nav_vehicle_location, true, true, null));
            add(new MenuElement("Bar Quality Test", "", R.color.test_bar, MenuElement.ElementGravity.RIGHT, R.drawable.ic_quality, R.drawable.ic_quality, R.id.nav_quality_test, true, true, null));
            add(new MenuElement("About", "", R.color.about, MenuElement.ElementGravity.LEFT, R.drawable.ic_about, R.drawable.ic_about, R.id.nav_about, true, true, null));
            add(new MenuElement("Close session", "", R.color.close_session, MenuElement.ElementGravity.LEFT, android.R.drawable.ic_lock_power_off, android.R.drawable.ic_lock_power_off, -1, true, false, (context, navController) -> {
                try {
                    ((MainActivity) context).askToCloseSession();
                } catch (ClassCastException exception) {
                    ModalMessage.showError(context, context.getString(R.string.closing_session_error), null, null, null, null);
                }
            }));
        }
    };
}
