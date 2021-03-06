package es.jsm.mvvm.beer.providers;

import android.widget.ImageView;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.config.GeneralConfig;
import es.jsm.mvvm.beer.core.utils.ImageUtils;

/**
 * Provee de méétodos auxiliares para el tratamiento de imágenes
 */
public class ImagesProvider {

     private static final int  PLACEHOLDER_RES_ID = R.drawable.beer_logo;

    /**
     * Carga una imagen de manera perezosa
     * @param url url donde está la imagen
     * @param imageViewTarget ImageView
     * @param isThumbnail Indica si la imagen se mostrará en una vista previa en listado
     */
    public static void loadImageForListOrDetail(String url, ImageView imageViewTarget, boolean isThumbnail) {

        try {
            int width = -1;
            int height = -1;
            if(isThumbnail)
            {
                //TODO REVISAR TAMAÑO THUMBNAILS
                width = GeneralConfig.IMAGE_THUMBNAIL_WIDTH;
                height = GeneralConfig.IMAGE_THUMBNAIL_HEIGHT;

            }

            ImageUtils.lazyloadFromUrl(url, imageViewTarget, PLACEHOLDER_RES_ID, width, height);
        } catch (Exception e) {
            imageViewTarget.setImageResource(PLACEHOLDER_RES_ID);
        }
    }
}
