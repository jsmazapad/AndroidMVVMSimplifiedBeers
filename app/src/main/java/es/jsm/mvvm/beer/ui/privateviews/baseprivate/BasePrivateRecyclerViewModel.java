package es.jsm.mvvm.beer.ui.privateviews.baseprivate;

import android.app.Application;

import es.jsm.mvvm.beer.core.ui.baserecycler.BaseRecyclerViewModel;

public abstract class BasePrivateRecyclerViewModel<T, ResponseType> extends BaseRecyclerViewModel<T, ResponseType> {

    public BasePrivateRecyclerViewModel(Application app, Object... args) {
        super(app, args);
        errorHandler = new BasePrivateUIErrorHandler();
    }

}
