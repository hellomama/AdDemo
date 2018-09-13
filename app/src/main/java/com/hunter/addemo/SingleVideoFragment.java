package com.hunter.addemo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.universalvideoview.UniversalVideoView;

public class SingleVideoFragment extends BaseFragment implements UniversalVideoView.VideoViewCallback{

    public static SingleVideoFragment getInstance(String path)
    {
        SingleVideoFragment fragment = new SingleVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("data",path);
        fragment.setArguments(bundle);

        return fragment;
    }

    private static final String TAG = SingleVideoFragment.class.getSimpleName();
    private UniversalVideoView videoView;
    private ProgressBar progressBar;
    private RelativeLayout videoLayout;
    private String path;
    private int mCurrentPosition = 0;
    private boolean isVisible;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        View view = inflater.inflate(R.layout.fragment_single_video,container,false);
        videoLayout = view.findViewById(R.id.parent);
        videoView = view.findViewById(R.id.video_view);
        progressBar = view.findViewById(R.id.loading);
        return view;
    }

    private void init()
    {
        if (getArguments() != null)
        {
            path = getArguments().getString("data");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isVisible = isVisibleToUser;
        if (videoView != null)
        {
            if (isVisibleToUser)
            {
                videoView.start();
            }else {
                videoView.pause();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initVideo();
    }

    private void initVideo()
    {
        videoView.setVideoViewCallback(this);
        setVideoAreaSize(path);
        videoView.requestFocus();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG,"onCompletion");
                mCurrentPosition = 0;
                videoView.start();
            }
        });
        if (isVisible) {
            videoView.start();
        }
    }

    private void setVideoAreaSize(final String path) {
        videoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = videoLayout.getWidth();
                int cachedHeight = videoLayout.getHeight();
                RelativeLayout.LayoutParams videoLayoutParams = (RelativeLayout.LayoutParams)videoView.getLayoutParams();
                videoLayoutParams.width = width;
                videoLayoutParams.height = cachedHeight;
                videoLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                videoView.setLayoutParams(videoLayoutParams);
                videoView.setVideoURI(Uri.parse("android.resource://" + _activity.getPackageName() + "/" + R.raw.d));

//                videoView.setVideoPath(path);
                videoView.requestFocus();
            }
        });
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {

    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        mCurrentPosition = mediaPlayer.getCurrentPosition();
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        mediaPlayer.seekTo(mCurrentPosition);
        if (progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("videoF","onDestroyView");

    }
}
