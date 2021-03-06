package es.jsm.mvvm.beer.core.utils;

import android.content.Context;
import android.security.KeyChain;
import android.security.KeyChainException;

import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509KeyManager;

/***
 * Gets the credentials associated with an alias and presents them so that they can be used in an ssl context
 * @see KeyChain
 * @see HttpsURLConnection
 * @see <a href="http://android-developers.blogspot.co.uk/2012/03/unifying-key-store-access-in-ics.html">Unifying Key Store Access in ICS</a>
 * @see <a href="https://github.com/android/platform_packages_apps_email/blob/master/emailcommon/src/com/android/emailcommon/utility/SSLUtils.java">SSLUtils.java</a>
 */
public class X509Utilities implements X509KeyManager {
    private final String alias;
    private final X509Certificate[] certChain;
    private final PrivateKey privateKey;

    public static SSLContext setForConnection(HttpsURLConnection con, Context context, String alias) throws CertificateException, KeyManagementException {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Should not happen...", e);
        }

        sslContext.init(new KeyManager[] { fromAlias(context, alias)}, null, null);
        con.setSSLSocketFactory(sslContext.getSocketFactory());
        return sslContext;
    }

    public static X509Utilities fromAlias(Context context, String alias) throws CertificateException {
        X509Certificate[] certChain;
        PrivateKey privateKey;
        try {
            certChain = KeyChain.getCertificateChain(context, alias);
            privateKey = KeyChain.getPrivateKey(context, alias);
        } catch (KeyChainException e) {
            throw new CertificateException(e);
        } catch (InterruptedException e) {
            throw new CertificateException(e);
        }
        if(certChain == null || privateKey == null){
            throw new CertificateException("Can't access certificate from keystore");
        }

        return new X509Utilities(alias, certChain, privateKey);
    }

    public X509Utilities(String alias, X509Certificate[] certChain, PrivateKey privateKey) throws CertificateException {
        this.alias = alias;
        this.certChain = certChain;
        this.privateKey = privateKey;
    }

    @Override
    public String chooseClientAlias(String[] arg0, Principal[] arg1, Socket arg2) {
        return alias;
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        if(this.alias.equals(alias)) return certChain;
        return null;
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        if(this.alias.equals(alias)) return privateKey;
        return null;
    }


    // Methods unused (for client SSLSocket callbacks)
    @Override public final String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        throw new UnsupportedOperationException();
    }

    @Override public final String[] getClientAliases(String keyType, Principal[] issuers) {
        throw new UnsupportedOperationException();
    }

    @Override public final String[] getServerAliases(String keyType, Principal[] issuers) {
        throw new UnsupportedOperationException();
    }
}
