package es.jsm.mvvm.beer.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.ui.privateviews.main.MainActivity;
import es.jsm.mvvm.beer.ui.publicviews.publicviews.PublicViewsActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        Thread mSplashThread = new Thread() {

            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(3000);
                    } catch (InterruptedException e) {
                    } finally {
                        SplashActivity.this.exitSplash();
                    }
                }
            }

        };
        mSplashThread.start();

    }

    private void exitSplash() {
        Intent intent = new Intent(this, PublicViewsActivity.class);
        startActivity(intent);
        finish();
    }
}
