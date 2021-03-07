package es.jsm.mvvm.beer.config;

public class GeneralConfig {

    /**
     * Límite superior del slider de filtro por grado de alcohol (ABV)
     */
    public static final Float MAX_ABV_FOR_SLIDER = 54.9f;
    /**
     * Ancho para descargar imágenes en thumbnails
     */
    public static final int IMAGE_THUMBNAIL_WIDTH = 80;
    /**
     * Alto para descargar imágenes en thumbnails
     */
    public static final int IMAGE_THUMBNAIL_HEIGHT= 80;

    public static final float MIN_LIGHT_FOR_SENSOR= 88;

    public static final float MAX_LIGHT_FOR_SENSOR= 100;

    public static final int LIGHT_SENSOR_TEST_SAMPLES_NUM= 15;
}
