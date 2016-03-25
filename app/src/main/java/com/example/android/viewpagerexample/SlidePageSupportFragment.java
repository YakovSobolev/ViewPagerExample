package com.example.android.viewpagerexample;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class SlidePageSupportFragment extends Fragment {

    private static final String TAG = SlidePageSupportFragment.class.getSimpleName();

    int pageNumber = 0;  //default

    public void setPageNumber(int num) {
        pageNumber = num;
    }

    /**
     * Called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle) has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once they know their view hierarchy has been completely created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.slide_page, container, false);

        TextView textPageNumber = (TextView) rootView.findViewById(R.id.pagenumber);

        textPageNumber.setText("Page " + (pageNumber + 1));
        Log.i(TAG, "onCreateView() - Page " + pageNumber);

        // show video
        VideoView myVideoView = (VideoView) rootView.findViewById(R.id.myvideoview);
        myVideoView.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        myVideoView.setVideoURI(getViewUri());
        myVideoView.requestFocus();
        myVideoView.start();

        return rootView;
    }

    /**
     * Called when the view previously created by onCreateView() has been detached from the fragment.
     * The next time the fragment needs to be displayed, a new view will be created.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView() - Page " + pageNumber);
    }

    /**
     * Set a hint to the system about whether this fragment's UI is currently visible
     * to the user. This hint defaults to true and is persistent across fragment instance
     * state save and restore.
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    private Uri getViewUri() {

        int videoFile = R.raw.video1;
        if (pageNumber == 1) {
            videoFile = R.raw.video2;
        }
        else if (pageNumber == 2) {
            videoFile = R.raw.video3;
        }

        String filePath = "android.resource://" + getContext().getPackageName() + File.separator + videoFile;
//        Toast.makeText(getContext(), filePath, Toast.LENGTH_SHORT).show();

        Log.i(TAG, "filePath: " + filePath);

        Uri videoUri = Uri.parse(filePath);
        return videoUri;
    }
}

