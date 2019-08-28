package com.avenjr.me.me.ui.fragments;


import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.adapters.WelcomeScreenAdapter;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeFragment extends Fragment {

    @BindView(R.id.screen_title)
    TextView screenTitle;

    @BindView(R.id.player_view)
    PlayerView exoPlayerView;

    @BindView(R.id.video_progress_bar)
    ProgressBar videoProgressBar;

    @BindView(R.id.interest_recycler_view)
    RecyclerView recyclerView;

    SimpleExoPlayer simpleExoPlayer;
    private String title;
    String URI = "https://www.radiantmediaplayer.com/media/bbb-360p.mp4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            title = getArguments().getString("screen");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            videoProgressBar.setIndeterminateTintList(ColorStateList.valueOf(getResources().getColor(R.color.appBackgroundDark)));
        }

        screenTitle.setText(title);

        initializeAdapter();
        initializePlayer(URI);

        return view;
    }

    private void initializeAdapter() {
        ArrayList<String> list = new ArrayList<>();
        list.add("sl;df;ldsk");
        list.add("slkfdjlsdkj");
        list.add("skdfsljf");
        list.add("lksejdflkj");
        list.add("sl;df;ldsk");
        list.add("slkfdjlsdkj");
        list.add("skdfsljf");
        list.add("lksejdflkj");
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        WelcomeScreenAdapter adapter = new WelcomeScreenAdapter(this.getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    private void initializePlayer(String URI) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector);

        Uri videoUri = Uri.parse(URI);

        DefaultDataSourceFactory sourceFactory = new DefaultDataSourceFactory(this.getContext(), "video_player");
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(videoUri, sourceFactory, extractorsFactory, null, null);
        exoPlayerView.setPlayer(simpleExoPlayer);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);

        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_BUFFERING){
                    videoProgressBar.setVisibility(View.VISIBLE);
                } else {
                    videoProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
