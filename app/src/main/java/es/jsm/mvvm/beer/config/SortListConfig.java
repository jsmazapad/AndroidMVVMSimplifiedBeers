package es.jsm.mvvm.beer.config;

import java.util.Comparator;

import es.jsm.mvvm.beer.model.Beer;

public class SortListConfig {

    public final static Comparator<Beer> BEERS_COMPARATOR = new Comparator<Beer>() {
        @Override
        public int compare(Beer o1, Beer o2) {
            if (o1.getName() == null){
                return -1;
            }else if(o2.getName() == null){
                return 1;
            }else {
                return o1.getName().compareTo(o2.getName());
            }
        }
    };

}