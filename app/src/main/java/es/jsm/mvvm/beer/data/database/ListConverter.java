package es.jsm.mvvm.beer.data.database;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Converter para insertar listas de Strings,
 * Room no las entiende y hay que proporcionarle conversores de datos Custom
 */
public class ListConverter {

    private final static String DELIMITER = "@###@";

    @TypeConverter
    public static List<String> toList(String stringedList) {
        List<String> list = new ArrayList<>();
        if(stringedList != null) {
            list = Arrays.asList(stringedList.split(DELIMITER));
        }
        return list;
    }

    @TypeConverter
    public static String toString(List<String> list) {
        String stringedList = "";
        if(list != null) {
            for(String item:list){
                stringedList+= item + DELIMITER;
            }
        }
        return stringedList;


    }
}
