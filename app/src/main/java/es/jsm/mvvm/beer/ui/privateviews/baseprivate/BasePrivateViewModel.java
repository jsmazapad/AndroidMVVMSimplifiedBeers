package es.jsm.mvvm.beer.ui.privateviews.baseprivate;

import android.content.Context;

import androidx.lifecycle.ViewModel;

public class BasePrivateViewModel extends ViewModel {

    public void closeSession(Context c) {
        //Si se usa mas de un grafo en el área privada hay que controlar la navegación desde donde se esté
        //Esto sólo es válido para un único grafo de navegación
        ((BasePrivateActivity) c).finish();
    }
}

