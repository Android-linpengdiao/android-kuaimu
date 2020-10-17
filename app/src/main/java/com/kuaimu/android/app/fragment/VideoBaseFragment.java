package com.kuaimu.android.app.fragment;


import android.view.View;

import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.kuaimu.android.app.R;

public class VideoBaseFragment<T extends VideoView> extends BaseFragment {

    protected T mVideoView;

    protected int getTitleResId() {
        return R.string.app_name;
    }

    protected int getLayoutResId() {
        return 0;
    }

    protected View getContentView() {
        return null;
    }

    protected boolean enableBack() {
        return true;
    }

    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            if (mVideoView != null) {
                mVideoView.pause();
            }
        }else {
//            if (mVideoView != null) {
//                mVideoView.resume();
//            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (mVideoView != null) {
//            mVideoView.resume();
//        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
    }
}
