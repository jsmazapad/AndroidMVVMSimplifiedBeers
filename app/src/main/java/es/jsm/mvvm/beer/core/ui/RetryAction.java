package es.jsm.mvvm.beer.core.ui;

/**
 * Interfaz para pasar callback de acción de reintento en métodos de procesamiento de errores
 */
public interface RetryAction {
    void retryAction();
}
