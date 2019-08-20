package com.avenjr.me.me.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.avenjr.me.me.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{

    @BindView(R.id.sign_in_button)
    RelativeLayout signIn;

    @BindView(R.id.register_button)
    RelativeLayout register;

    @BindView(R.id.main_activity_parent)
    View parentLayout;

    @BindView(R.id.main_screen_player)
    PlayerView player;

    int orientation;
    SimpleExoPlayer playerControl;
    private boolean playWhenReady = true;
    private long playbackPosition = 0;
    private int currentWindow = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orientation = getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main_land);
        }

        ButterKnife.bind(this);

        setUpProgress(parentLayout);
    }

    @OnClick(R.id.sign_in_button)
    public void signIn() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.register_button)
    public void register() {
        Intent intent = new Intent(MainActivity.this, OnboardingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            setupPlayer(player, "welcome.mp4");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            setupPlayer(player, "welcome.mp4");
        }
    }

    private void setupPlayer(PlayerView player, String video) {
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory();
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        playerControl = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        player.setControllerShowTimeoutMs(0);
        player.setPlayer(playerControl);
        player.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse("file:///android_asset/videos/" + video),
                new DefaultDataSourceFactory(this, Util.getUserAgent(this,
                        getString(R.string.app_name))),
                new DefaultExtractorsFactory(), null, null);
        playerControl.prepare(mediaSource);
        playerControl.setPlayWhenReady(true);
        player.hideController();
        player.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);
        playerControl.addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playWhenReady) {
                    if (playbackState == Player.STATE_READY) {
                        playerControl.setPlayWhenReady(true);
                    } else if (playbackState == Player.STATE_ENDED) {
                        playerControl.seekTo(0);
                        playerControl.setPlayWhenReady(true);
                        player.hideController();
                    }
                }
            }
        });
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = playerControl.getCurrentPosition();
            currentWindow = playerControl.getCurrentWindowIndex();
            playWhenReady = playerControl.getPlayWhenReady();
            playerControl.release();
            playerControl = null;
        }
    }
}
