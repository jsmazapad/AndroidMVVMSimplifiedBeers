package es.jsm.mvvm.beer.core;

import android.os.Build;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

/**
 * Cliente web para su suo por defecto en fragments o activities que usen webviews
 */
public class DefaultWebViewClient extends WebViewClient {

    protected MutableLiveData<Boolean> isLoading;

    public DefaultWebViewClient(MutableLiveData<Boolean> isLoading) {
        this.isLoading = isLoading;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        //isLoading.setValue(true);
        super.onLoadResource(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request,
                                WebResourceError error) {
        isLoading.setValue(false);
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        isLoading.setValue(false);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

        return this.shouldOverrideUrlLoading(view, request.getUrl().toString());
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return false;
    }
}
