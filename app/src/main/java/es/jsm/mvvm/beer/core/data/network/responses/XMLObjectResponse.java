package es.jsm.mvvm.beer.core.data.network.responses;

import java.util.List;

public interface XMLObjectResponse <E> {
    List<E> getItems();
}
