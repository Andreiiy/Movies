package com.atatar.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.inputmethodservice.ExtractEditText;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    //public static final String VIDEO_URI = "https://www.radiantmediaplayer.com/media/big-buck-bunny-360p.mp4";
    public static final String VIDEO_URI = "https://firebasestorage.googleapis.com/v0/b/movies-1979a.appspot.com/o/klips%2Flittle-big-tacos_148435.mp4?alt=media&token=3a2842fa-c468-4992-9834-d4d164faf23a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         initPlayer();

    }

    private void initPlayer() {

        playerView = findViewById(R.id.movie_player);
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory datasourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,"BlueScreen"));
        MediaSource viewSource = new ExtractorMediaSource.Factory(datasourceFactory)
                .createMediaSource(Uri.parse(VIDEO_URI));
        simpleExoPlayer.prepare(viewSource);
        //simpleExoPlayer.setPlayWhenReady(true);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}
