package es.jsm.mvvm.beer.core.ui;

import android.content.Context;

import androidx.annotation.Nullable;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.core.exceptions.BaseException;
import es.jsm.mvvm.beer.core.utils.ModalMessage;

/**
 * Clase Base para el manejador de errores en la capa de UI
 */
public class BaseUIErrorHandler implements UIErrorHandler{

    public void handleError(BaseException exception, Context context, @Nullable  RetryAction retryAction, SetShowDialogFalse setShowDialogFalse){
        if(exception.isRetryable()){
            ModalMessage.showError(context, exception.getMessage() + context.getString(R.string.error_network_ask_retry), null, (dialog, which) -> retryAction.retryAction(), null, (dialog, which) -> { });
        }else{
            ModalMessage.showError(context, exception.getMessage(), null, (dialog, which) -> setShowDialogFalse.setShowDialogFalse() , null,null);
        }
    }
}
