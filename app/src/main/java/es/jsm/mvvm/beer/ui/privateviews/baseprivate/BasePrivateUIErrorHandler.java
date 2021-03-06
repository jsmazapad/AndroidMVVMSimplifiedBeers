package es.jsm.mvvm.beer.ui.privateviews.baseprivate;

import android.content.Context;

import es.jsm.mvvm.beer.core.data.network.exceptions.InvalidSessionException;
import es.jsm.mvvm.beer.core.exceptions.BaseException;
import es.jsm.mvvm.beer.core.ui.BaseUIErrorHandler;
import es.jsm.mvvm.beer.core.ui.RetryAction;
import es.jsm.mvvm.beer.core.ui.SetShowDialogFalse;
import es.jsm.mvvm.beer.core.utils.ModalMessage;
import es.jsm.mvvm.beer.ui.privateviews.main.MainActivity;

public class BasePrivateUIErrorHandler extends BaseUIErrorHandler {

    @Override
    public void handleError(BaseException exception, Context context, RetryAction retryAction, SetShowDialogFalse setShowDialogFalse) {
        if (exception instanceof InvalidSessionException) {
            ModalMessage.showError(context, exception.getMessage(), null, (dialog, which) -> ((MainActivity) context).closeSession(), null, null);
        } else {
            super.handleError(exception, context, retryAction, setShowDialogFalse);
        }
    }
}
