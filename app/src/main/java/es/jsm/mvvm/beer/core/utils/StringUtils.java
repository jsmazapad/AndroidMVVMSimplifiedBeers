package es.jsm.mvvm.beer.core.utils;

/**
 * Clase de utlidades para Strings
 */
public class StringUtils {
    /**
     * Reemplaza los carácteres diacríticos en un String
     * @param string String con carácteres diacríticos
     * @return String sin caracteres diacríticos
     */
    public static String replaceDiacritic(String string) {

        char oldChars[] = {'á', 'é', 'í', 'ó', 'ú', 'Á', 'É', 'Í', 'Ó', 'Ú'};
        char replacementChars[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

        for (int i = 0; i < oldChars.length; i++) {
            string = string.replace(oldChars[i], replacementChars[i]);
        }

        return string;
    }

}
