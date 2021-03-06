package es.jsm.mvvm.beer.core.data.network.exceptions;

import es.jsm.mvvm.beer.core.exceptions.BaseException;

/**
 * Excepción para errores de sesión
 */
public class InvalidSessionException extends BaseException {
    public InvalidSessionException(String generalSessionError) {
        super(generalSessionError, false);
    }
}
