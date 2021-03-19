package es.jsm.mvvm.beer.config;

import java.util.Comparator;
import es.jsm.mvvm.beer.model.Beer;


/**
 * Configuración de criterios de ordenación de la App
 * Se separan y centralizan para poder cambiarlos sin necesidad de ir al viewmodel correspondiente
 */
public class SortListConfig {

    public final static Comparator<Beer> BEERS_COMPARATOR = (o1, o2) -> {
        if (o1.getName() == null){
            return -1;
        }else if(o2.getName() == null){
            return 1;
        }else {
            return o1.getName().compareTo(o2.getName());
        }
    };



}