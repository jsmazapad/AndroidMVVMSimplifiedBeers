package es.jsm.mvvm.beer.binding;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.slider.Slider;

import java.util.List;

import es.jsm.mvvm.beer.providers.ExternalUrlVideoProvider;
import es.jsm.mvvm.beer.providers.ImagesProvider;

public class CustomBindings {

    /**
     * Custom Binding Adapter para mostrar un listado de Strings como textview
     * @param tv
     * @param items
     */
    @BindingAdapter("arrayToText")
    public static void setArrayToText(TextView tv, List<String> items) {
        String textConverted="";
        for (String text:items) {
            textConverted += String.format("- %s \n", text);
        }
        tv.setText(textConverted);
    }

    @BindingAdapter("htmlClearedText")
    public static void htmlClearedText(TextView tv, String text) {
        text = text.replaceAll("<(.*?)\\>"," ");//Removes all items in brackets
        text = text.replaceAll("<(.*?)\\\n"," ");//Must be undeneath
        text = text.replaceFirst("(.*?)\\>", " ");//Removes any connected item to the last bracket
        text = text.replaceAll("&nbsp;"," ");
        text = text.replaceAll("&amp;"," ");
        tv.setText(text);
    }

    /**
     * Custom Binding Adapter para añadir un listener de cambios a Slider de material
     * @param slider
     * @param listener
     */
    @BindingAdapter("onChangeListener")
    public static void setOnChangeListener(Slider slider, Slider.OnChangeListener listener){
        slider.removeOnChangeListener(listener);
        slider.addOnChangeListener(listener);
    }


    /**
     * Custom Binding Adapter para realizar la carga perezosa de una imagen desde una url
     * @param view
     * @param imageUrl
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if(imageUrl != null) {
            ImagesProvider.loadImageForListOrDetail(imageUrl, view, false);
        }
    }

    /**
     * Custom Binding Adapter para cargar una url de un video en un PlayerView
     * @param videoView
     * @param videoPath
     */
    @BindingAdapter("videoPath")
    public static void setVideoPath(PlayerView videoView, String videoPath) {
        ExternalUrlVideoProvider.configureVideoPlayer( videoView,  videoPath);
    }

    /**
     * Custom binding para setear un webclient a un webview
     * @param view
     * @param client
     */
    @BindingAdapter({"setWebViewClient"})
    public static void setWebViewClient(WebView view, WebViewClient client) {
        WebSettings webSettings = view.getSettings();
        webSettings.setDomStorageEnabled(true);
        view.setWebViewClient(client);
    }

    /**
     * Custom binding para cargar una url en un webview
     * @param view
     * @param url
     */
    @BindingAdapter({"loadUrl"})
    public static void loadUrl(WebView view, String url) {
        view.loadUrl(url);
    }
}
