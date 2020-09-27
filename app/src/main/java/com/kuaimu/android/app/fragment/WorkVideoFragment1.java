package com.kuaimu.android.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.OrientationHelper;

import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.dkplayer.tiktok.OnViewPagerListener;
import com.dkplayer.tiktok.ViewPagerLayoutManager;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.WorkVideoAdapter;
import com.kuaimu.android.app.databinding.FragmentWorkVideoBinding;
import com.kuaimu.android.app.manager.RecycleViewManager;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.CommentData;
import com.kuaimu.android.app.model.HomeDetail;
import com.kuaimu.android.app.model.VideoDataBean;
import com.kuaimu.android.app.view.CommentListPopupWindow;
import com.kuaimu.android.app.view.LoadingView;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.okhttp.utils.APIUrls;
import com.okhttp.utils.OkHttpUtils;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

public class WorkVideoFragment1 extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "WorkVideoFragment";
    private FragmentWorkVideoBinding binding;
    private OnVideoFragmentInteractionListener mListener;
    private WorkVideoAdapter adapter;
    private List<VideoDataBean> list;

    private ViewPagerLayoutManager mLayoutManager;

    //    private int id;
    private VideoDataBean dataBean;
    private BaseData baseData;
    private int position;

    public static WorkVideoFragment1 newInstance(BaseData baseData, int position) {
        WorkVideoFragment1 fragment = new WorkVideoFragment1();
        Bundle args = new Bundle();
        args.putSerializable("baseData", baseData);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnVideoFragmentInteractionListener {
        //type 0 关闭  1 打开用户主页  2 切换视频更新用户
        void onVideoFragmentInteraction(int type, int uid);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnVideoFragmentInteractionListener) {
            mListener = (OnVideoFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            baseData = (BaseData) getArguments().getSerializable("baseData");
            position = getArguments().getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_work_video, container, false);
        setStatusBarHeight(binding.getRoot());

        adapter = new WorkVideoAdapter(getActivity());
        mLayoutManager = new ViewPagerLayoutManager(getActivity(), OrientationHelper.VERTICAL);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                switch (view.getId()) {
                    case R.id.userInfoView:
                        mListener.onVideoFragmentInteraction(1, 0);
                        break;
                    case R.id.tv_comment:
                        if (getUid(true) > 0) {
                            if (object instanceof VideoDataBean) {
                                dataBean = (VideoDataBean) object;
                                commentListPopupWindow = null;
                                homePageVideosComment(dataBean.getId());
                            }
                        }
                        break;
                    case R.id.tv_share:
                        if (object instanceof VideoDataBean) {
                            dataBean = (VideoDataBean) object;
                            shareView(getActivity(), dataBean.getVideo(), dataBean.getDesc(), dataBean.getDesc(), new OnClickListener() {
                                @Override
                                public void onClick(View view, Object object) {
                                    homeShare(dataBean);
                                }

                                @Override
                                public void onLongClick(View view, Object object) {

                                }
                            });
                        }
                        break;
                    case R.id.videoView:
                        if (mVodPlayer != null) {
                            if (mVodPlayer.isPlaying()) {
                                mVodPlayer.pause();
                                if (videoPlay != null) {
                                    videoPlay.animate().alpha(0.6f).start();
                                }
                            } else {
                                mVodPlayer.resume();
                                if (videoPlay != null) {
                                    videoPlay.animate().alpha(0f).start();
                                }
                            }
                        }
                        break;
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });

        binding.playerBack.setOnClickListener(this);

        initListener();

        if (baseData != null) {
            list = (List<VideoDataBean>) baseData.getData();
            adapter.loadMoreData(list);
            RecycleViewManager.smoothMoveToPosition(binding.recyclerView, position);
        }

        return binding.getRoot();
    }


    @Override
    public void onPause() {
        pause();
        super.onPause();
    }

    private void initListener() {
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                Log.e(TAG, "onInitComplete");
                playVideo(position, false);
                homeDetail(position, false);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                Log.e(TAG, "选中位置:" + position + "  是否是滑动到底部:" + isBottom);
                playVideo(position, isBottom);
                homeDetail(position, false);
            }


        });
    }


    private void releaseVideo(int index) {
        Log.i(TAG, "releaseVideo: ");
        OkHttpUtils.getInstance().cancelTag(APIUrls.homeDetail);
        View itemView = binding.recyclerView.getChildAt(index);
        if (itemView != null) {
            ImageView thumbnails = itemView.findViewById(R.id.thumbnails);
            ImageView videoPlay = itemView.findViewById(R.id.video_play);
            thumbnails.animate().alpha(1).start();
            videoPlay.animate().alpha(0f).start();
        }

    }

    /**
     * SDK player 相关
     */
    private TXVodPlayer mVodPlayer = null;
    private ImageView videoPlay;

    private void playVideo(int position, boolean isBottom) {
        this.position = position;
        mListener.onVideoFragmentInteraction(2, list.get(position).getTourist_id());

        View itemView = binding.recyclerView.getChildAt(0);
        TXCloudVideoView videoView = itemView.findViewById(R.id.videoView);
        videoPlay = itemView.findViewById(R.id.video_play);
        final ImageView thumbnails = itemView.findViewById(R.id.thumbnails);
        final LoadingView loading = itemView.findViewById(R.id.loadingView);

        videoView.setLogMargin(12, 12, 110, 60);
        videoView.showLog(false);

        if (mVodPlayer == null) {
            mVodPlayer = new TXVodPlayer(getActivity());
        }

        mVodPlayer.setVodListener(new ITXVodPlayListener() {
            @Override
            public void onPlayEvent(TXVodPlayer txVodPlayer, int event, Bundle param) {
                if (event != TXLiveConstants.PLAY_EVT_PLAY_PROGRESS) {
//                    String playEventLog = "TXVodPlayer onPlayEvent event: " + event + ", " + param.getString(TXLiveConstants.EVT_DESCRIPTION);
//                    TXCLog.d(TAG, playEventLog);
                }
                switch (event) {
                    case TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED://视频播放开始
//                        mBinding.videoPlayLayout.videoPlay.setSelected(true);
                        loading.setVisibility(View.GONE);
                        thumbnails.animate().alpha(0).setDuration(200).start();

                        break;
                    case TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME://切换软硬解码器后，重新seek位置

                        break;
                    case TXLiveConstants.PLAY_EVT_PLAY_END:

//                        mBinding.videoPlayLayout.videoPlay.setSelected(false);

                        break;
                    case TXLiveConstants.PLAY_EVT_PLAY_PROGRESS:
                        int progress = param.getInt(TXLiveConstants.EVT_PLAY_PROGRESS_MS);
                        int duration = param.getInt(TXLiveConstants.EVT_PLAY_DURATION_MS);

//                        mBinding.videoPlayLayout.currentDuration.setText(CommonUtil.Formatter.formatTime((int) (progress)));
//                        mBinding.videoPlayLayout.totalDuration.setText(CommonUtil.Formatter.formatTime((int) (duration)));
//                        mBinding.videoPlayLayout.progress.setProgress((int) progress);
//                        mBinding.videoPlayLayout.progress.setMax((int) duration);
                        break;
                    case TXLiveConstants.PLAY_EVT_PLAY_BEGIN: {
//                        updatePlayState(SuperPlayerConst.PLAYSTATE_PLAYING);
                        break;
                    }
                    default:
                        break;
                }
                if (event < 0) {// 播放点播文件失败
                    mVodPlayer.stopPlay(true);

                }
            }

            @Override
            public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

            }
        });
        mVodPlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
            }

            @Override
            public void onNetStatus(Bundle bundle) {

            }
        });
        mVodPlayer.setPlayerView(videoView);
        mVodPlayer.setAutoPlay(true);
        mVodPlayer.setLoop(true);
        mVodPlayer.enableHardwareDecode(false); // 是否使用硬解码
        mVodPlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);// player 渲染角度
        mVodPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);//player 渲染模式

        VideoDataBean dataBean = list.get(position);
        Log.i(TAG, "playVideo: " + dataBean.getVideo());
        if (dataBean.getVideo().startsWith("http")) {
            int result = mVodPlayer.startPlay(dataBean.getVideo());
        } else {
            int result = mVodPlayer.startPlay(GlideLoader.domain + dataBean.getVideo());
        }


    }

    private void homeDetail(final int position, boolean isBottom) {
        SendRequest.homeDetail(getUid(), list.get(position).getId(), new GenericsCallback<HomeDetail>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(HomeDetail response, int id) {
                if (response.getCode() == 200) {
                    View itemView = binding.recyclerView.getChildAt(0);
                    ImageView ivFollow = itemView.findViewById(R.id.ivFollow);
                    TextView tvComment = itemView.findViewById(R.id.tv_comment);
                    TextView tvShare = itemView.findViewById(R.id.tv_share);
                    tvComment.setText(String.valueOf(response.getData().getComment_num()));
                    tvShare.setText(String.valueOf(response.getData().getShare_num()));
                    ivFollow.setVisibility(response.getData().isIs_liker() ? View.INVISIBLE : View.VISIBLE);
//                    Log.i(TAG, "onResponse: position "+position);
//                    Log.i(TAG, "onResponse: getShare_num "+response.getData().getShare_num());
//                    list.set(position, response.getData());
//                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void homeShare(VideoDataBean dataBean) {
        SendRequest.homeShare(getUid(), dataBean.getId(), new GenericsCallback<BaseData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(BaseData response, int id) {
                if (response.getCode() == 200) {

                }
            }
        });
    }


    private void pause() {
        if (mVodPlayer != null && mVodPlayer.isPlaying()) {
            mVodPlayer.pause();
            videoPlay.setSelected(false);
        }
        if (videoPlay != null) {
            videoPlay.animate().alpha(0.6f).start();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.player_back:
                mListener.onVideoFragmentInteraction(0, 0);
                break;
        }
    }


    private void homePageVideosComment(final int video_id) {
        SendRequest.homeComment(video_id, new GenericsCallback<CommentData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(CommentData response, int id) {
                if (response != null && response.getCode() == 200 && response.getData() != null) {
                    if (commentListPopupWindow != null) {
                        commentListPopupWindow.setCommentData(response.getData().getComment());
                    } else {
                        CommentView(response, video_id);
                    }
                } else {
                    ToastUtils.showShort(getActivity(), response.getMsg());
                }
            }

        });

    }

    private void homePageVideosCreateComment(final int video_id, String content) {
        SendRequest.homePageVideosCreateComment(getUid(), video_id, content, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optInt("code") == 200) {
                        homePageVideosComment(video_id);
                    } else {
                        ToastUtils.showShort(getActivity(), jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private CommentListPopupWindow commentListPopupWindow;

    private void CommentView(CommentData commentData, final int video_id) {
        commentListPopupWindow = new CommentListPopupWindow(getActivity());
        commentListPopupWindow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                homePageVideosCreateComment(video_id, (String) object);
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });
        commentListPopupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        commentListPopupWindow.setCommentData(commentData.getData().getComment());
    }
}