package es.jsm.mvvm.beer.ui.privateviews.menu;

import android.content.Context;

import androidx.navigation.NavController;

public interface MenuAction {
    void execute(Context context, NavController navController);
}
