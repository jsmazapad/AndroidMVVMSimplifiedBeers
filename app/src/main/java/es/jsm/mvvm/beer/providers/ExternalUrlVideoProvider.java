package es.jsm.mvvm.beer.providers;

import android.icu.text.UnicodeSetIterator;
import android.net.Uri;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import es.jsm.mvvm.beer.R;

/**
 * Provee de utilidades para reproducir videos de urls externas (no youtube)
 */
public class ExternalUrlVideoProvider {

    /**
     * Configura el reproductor de video
     * @param videoView reproductor a configurar
     * @param videoPath url donde está el video
     */
    public static void configureVideoPlayer(PlayerView videoView, String videoPath) {
        if(videoPath != null) {
            SimpleExoPlayer player = new SimpleExoPlayer.Builder(videoView.getContext()).build();
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(videoView.getContext(),
                    Util.getUserAgent(videoView.getContext(), videoView.getContext().getString(R.string.app_name)));
// This is the MediaSource representing the media to be played.
            MediaSource videoSource =
                    new ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(Uri.parse(videoPath));
// Prepare the player with the source.
            player.prepare(videoSource);
            videoView.setPlayer(player);
        }
    }
}
