package es.jsm.mvvm.beer.core.data.network.exceptions;

import es.jsm.mvvm.beer.core.exceptions.BaseException;

/**
 * Excepción para errores internos de backend
 */
public class InternalBackendException extends BaseException {

    public InternalBackendException(String message) {
        super(message, false);
    }
}