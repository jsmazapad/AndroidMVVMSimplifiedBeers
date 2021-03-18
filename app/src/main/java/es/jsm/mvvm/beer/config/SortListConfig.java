package es.jsm.mvvm.beer.config;

import java.util.Comparator;

import es.jsm.mvvm.beer.model.Beer;
import es.jsm.mvvm.beer.model.Friend;

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

    public final static Comparator<Friend> FRIENDS_COMPARATOR = (o1, o2) -> {
        if (o1.getName() == null){
            return -1;
        }else if(o2.getName() == null){
            return 1;
        }else {
            return o1.getName().compareTo(o2.getName());
        }
    };

}