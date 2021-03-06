package es.jsm.mvvm.beer.ui.privateviews.menu;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

public class MenuElement {

    private final String name;
    private final String subtitle;
    @DrawableRes
    private final int imageResource;
    @DrawableRes
    private final int imageResourceSideMenu;
    @IdRes
    private final int navDestiny;
    @ColorRes
    private final int color;
    private final ElementGravity orientation;
    private final boolean onSideMenu;
    private final boolean onMainMenu;
    private final MenuAction menuAction;

    public MenuElement(String name, String subtitle, int color, ElementGravity orientation, int imageResource, int imageResourceSideMenu, @IdRes int navDestiny, boolean onSideMenu, boolean onMainMenu, @Nullable MenuAction menuAction) {
        this.name = name;
        this.subtitle = subtitle;
        this.color = color;
        this.orientation = orientation;
        this.imageResource = imageResource;
        this.imageResourceSideMenu = imageResourceSideMenu;
        this.navDestiny = navDestiny;
        this.onMainMenu = onMainMenu;
        this.onSideMenu = onSideMenu;
        this.menuAction = menuAction;
    }

    public String getName() {
        return name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getColor() {
        return color;
    }

    public ElementGravity getOrientation() {
        return orientation;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getImageResourceSideMenu() {
        return imageResourceSideMenu;
    }

    public @IdRes
    int getNavDestiny() {
        return navDestiny;
    }

    public boolean isOnSideMenu() {
        return onSideMenu;
    }

    public boolean isOnMainMenu() {
        return onMainMenu;
    }

    public MenuAction getMenuAction() {
        return menuAction;
    }

    public enum ElementGravity {
        LEFT, CENTER, RIGHT
    }
}
