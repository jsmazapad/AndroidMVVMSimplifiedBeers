package es.jsm.mvvm.beer.core.data.network.exceptions;

import es.jsm.mvvm.beer.core.exceptions.BaseException;

/**
 * Excepción para recursos no encontrados (código 404)
 */
public class NotFoundException extends BaseException {
    public NotFoundException(String notFoundError) {
        super(notFoundError, false);
    }
}
