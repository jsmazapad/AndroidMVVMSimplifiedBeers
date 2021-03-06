package es.jsm.mvvm.beer.providers;

import java.util.ArrayList;

import es.jsm.mvvm.beer.config.MenuConfig;
import es.jsm.mvvm.beer.ui.privateviews.menu.MenuElement;

/**
 * Proveedor para elementos del menú
 */
public class MenuProvider {
    /**
     * Devuelve un listado de elementos de menú, dependiendo de si se muestra en el menú principal o lateral
     * @param isMainMenu Si es el menú principal
     * @return Listado de elementos de menú
     */
    public static ArrayList<MenuElement> getMenuElements(boolean isMainMenu) {

        ArrayList<MenuElement> menu = new ArrayList<>();
        for (MenuElement element : MenuConfig.menuElements) {
            if (isMainMenu && element.isOnMainMenu()) {
                menu.add(element);
            } else if (!isMainMenu && element.isOnSideMenu()) {
                menu.add(element);
            }
        }

        return menu;
    }

}
