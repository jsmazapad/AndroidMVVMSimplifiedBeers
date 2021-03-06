package es.jsm.mvvm.beer.core.ui;

import android.content.Context;

import es.jsm.mvvm.beer.core.exceptions.BaseException;

/**
 * Interfaz para definir clases que manejen errores en la vista
 */
public interface UIErrorHandler {
     /**
      * Maneja un error mostrando la ventana modal correspondiente y seteándola según parámetros
      * @param exception Excepción de la que se obtiene el mensaje de error
      * @param context Contexto donde se mostrará la ventana
      * @param retryAction Acción de reintentar si procede
      * @param setShowDialogFalse Acción para ventanas que no tienen reintento
      */
     void handleError(BaseException exception, Context context, RetryAction retryAction, SetShowDialogFalse setShowDialogFalse);
}
