package com.travel.ceylontraveler.Methods;

import android.app.Activity;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayerActivity {
    private Activity activity;
    private VideoView videoView;

    public VideoPlayerActivity(Activity activity, VideoView videoView) {
        this.activity = activity;
        this.videoView = videoView;
    }
    public void playVideo(int resourceId) {
        Uri videoUri = Uri.parse("android.resource://" + activity.getPackageName() + "/" + resourceId);
        videoView.setVideoURI(videoUri);
        videoView.start();
        MediaController mediaController = new MediaController(activity);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}
