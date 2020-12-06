package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baselibrary.manager.DialogManager;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.SharedPreferencesUtils;
import com.baselibrary.utils.ToastUtils;
import com.dkplayer.tiktok.OnViewPagerListener;
import com.dkplayer.tiktok.ViewPagerLayoutManager;
import com.dkplayer.utils.Utils;
import com.dkplayer.utils.cache.PreloadManager;
import com.dkplayer.view.LoadingView;
import com.dkplayer.widget.controller.TikTokController;
import com.dkplayer.widget.render.TikTokRenderViewFactory;
import com.dueeeke.videoplayer.player.VideoView;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.TikTokAdapter;
import com.kuaimu.android.app.databinding.FragmentHomeItemBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.CommentData;
import com.kuaimu.android.app.model.HomeDetail;
import com.kuaimu.android.app.model.HomeWorkData;
import com.kuaimu.android.app.model.VideoDataBean;
import com.kuaimu.android.app.view.CommentListPopupWindow;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.okhttp.utils.APIUrls;
import com.okhttp.utils.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class HomeItemFragment extends VideoBaseFragment implements View.OnClickListener {

    private static final String TAG = "HomeItemFragment";
    private FragmentHomeItemBinding binding;
    private int category_id;

    private TikTokController mController;
    private int mCurPos;
    private List<VideoDataBean> mVideoList = new ArrayList<>();
    private TikTokAdapter mTikTokAdapter;

    public static HomeItemFragment newInstance(int category_id) {
        HomeItemFragment fragment = new HomeItemFragment();
        Bundle args = new Bundle();
        args.putInt("category_id", category_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category_id = getArguments().getInt("category_id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_item, container, false);
        setStatusBarHeight(binding.getRoot());
        setStatusBarDarkTheme(true);

        mVideoView = new VideoView(getActivity());
        //以下只能二选一，看你的需求
        mVideoView.setRenderViewFactory(TikTokRenderViewFactory.create());
        //mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        mVideoView.setLooping(true);
        mController = new TikTokController(getActivity());
        mVideoView.setVideoController(mController);
        initRecyclerView();

        binding.swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchWork();
            }
        });

        return binding.getRoot();
    }

    private void searchWork() {
        binding.swipeRefreshLayout.setRefreshing(true);
        SendRequest.homeCity(category_id, new GenericsCallback<HomeWorkData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(HomeWorkData response, int id) {
                binding.swipeRefreshLayout.setRefreshing(false);
                if (response.getCode() == 200) {
                    mVideoList = response.getData().getData();
                    mTikTokAdapter.refreshData(mVideoList);
                } else {
                    ToastUtils.showShort(getActivity(), response.getMsg());
                }
            }

        });
    }

    private void initRecyclerView() {
        mTikTokAdapter = new TikTokAdapter(getActivity(), mVideoList);
        ViewPagerLayoutManager layoutManager = new ViewPagerLayoutManager(getActivity(), OrientationHelper.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mTikTokAdapter);
        binding.recyclerView.scrollToPosition(0);
        mVideoView.setOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                //监听VideoViewManager释放，重置状态
                if (playState == VideoView.STATE_PREPARED) {
                    if (loadingView != null) {
                        loadingView.setVisibility(View.GONE);
                    }
                }
            }
        });
        mTikTokAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                switch (view.getId()) {
                    case R.id.userInfoView:
                        if (mVideoView != null) {
                            mVideoView.pause();
                        }
                        break;
                    case R.id.tv_like:
//                        if (getUid(true) > 0) {
//                            if (object instanceof VideoDataBean) {
//                                VideoDataBean dataBean = (VideoDataBean) object;
//                                homePageVideosAssist((TextView) view, dataBean);
//                            }
//                        }
                        break;
                    case R.id.tv_comment:
                        if (getUid(true) > 0) {
                            if (object instanceof VideoDataBean) {
                                VideoDataBean dataBean = (VideoDataBean) object;
                                commentListPopupWindow = null;
                                homePageVideosComment(dataBean.getId());
                            }
                        }
                        break;
                    case R.id.tv_share:
                        if (object instanceof VideoDataBean) {
                            VideoDataBean dataBean = (VideoDataBean) object;
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

                        break;
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });
        layoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第index条
                startPlay(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (mCurPos == position) {
                    mVideoView.release();
                    OkHttpUtils.getInstance().cancelTag(APIUrls.worksDetail);
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (mCurPos == position) return;
                startPlay(position);
            }
        });
    }

    private LoadingView loadingView;

    private void startPlay(int position) {
        View itemView = binding.recyclerView.getChildAt(0);
        TikTokAdapter.VideoHolder viewHolder = (TikTokAdapter.VideoHolder) itemView.getTag();
        loadingView = viewHolder.loadingView;
        loadingView.setVisibility(View.VISIBLE);
        mVideoView.release();
        Utils.removeViewFormParent(mVideoView);
        VideoDataBean item = mVideoList.get(position);
        String playUrl = null;
        if (item != null && !CommonUtil.isBlank(item.getVideo())) {
            if (item.getVideo().startsWith("http")) {
                playUrl = PreloadManager.getInstance(getActivity()).getPlayUrl(item.getVideo());
            } else {
                playUrl = PreloadManager.getInstance(getActivity()).getPlayUrl(GlideLoader.domain + item.getVideo());
            }
        }
        Log.i(TAG, "startPlay: " + playUrl);
        mVideoView.setUrl(playUrl);
        mController.addControlComponent(viewHolder.mTikTokView, true);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        mVideoView.start();
        mCurPos = position;
        homeDetail(position);
    }

    private void homeDetail(final int index) {
        SendRequest.worksDetail(getUid(), mVideoList.get(index).getId(), new GenericsCallback<HomeDetail>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(HomeDetail response, int id) {
                if (response.getCode() == 200) {
                    View itemView = binding.recyclerView.getChildAt(0);
                    ImageView ivFollow = itemView.findViewById(R.id.ivFollow);
                    TextView tvLike = itemView.findViewById(R.id.tv_like);
                    TextView tvComment = itemView.findViewById(R.id.tv_comment);
                    TextView tvShare = itemView.findViewById(R.id.tv_share);
                    ImageView deleteView = itemView.findViewById(R.id.deleteView);
                    tvLike.setSelected(response.getData().isIs_assist());
                    tvLike.setText(String.valueOf(response.getData().getAssist_num()));
                    tvComment.setText(String.valueOf(response.getData().getComment_num()));
                    tvShare.setText(String.valueOf(response.getData().getShare_num()));
                    ivFollow.setVisibility(response.getData().isIs_liker() ? View.INVISIBLE : View.VISIBLE);
                    tvLike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (getUid(true) > 0) {
                                homePageVideosAssist(tvLike, response.getData());
                            }

                        }
                    });
                    deleteView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (getUid(true) > 0 && mVideoList.size() > 0) {
                                DialogManager.showConfirmDialog(getActivity(), "确定要删除该作品？", new DialogManager.Listener() {
                                    @Override
                                    public void onItemLeft() {

                                    }

                                    @Override
                                    public void onItemRight() {
                                        delVideo(mVideoList.get(index).getId());
                                    }
                                });
                            }

                        }
                    });
                }
            }
        });
    }

    private void delVideo(int id) {
        SendRequest.delVideo(id, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optInt("code") == 200) {
                        ToastUtils.showShort(getActivity(), "已删除");
//                        mVideoList.remove(position);
//                        if (mVideoList.size() == 0) {
//                            mListener.onVideoFragmentInteraction(0, 0);
//                            return;
//                        }
//                        if (position == mVideoList.size()) {
//                            position -= 1;
//                        }
//                        mTikTokAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showShort(getActivity(), jsonObject.optString("msg"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void homePageVideosAssist(final TextView tvLike, final VideoDataBean dataBean) {
        String url = tvLike.isSelected() ? APIUrls.homeDeleteAssist : APIUrls.homeAssist;
        SendRequest.homePageVideosAssist(getUserInfo().getData().getId(), dataBean.getId(), url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    if (!CommonUtil.isBlank(response)) {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optInt("code") == 200) {
                            dataBean.setAssist_num(tvLike.isSelected() ? dataBean.getAssist_num() - 1 : dataBean.getAssist_num() + 1);
                            tvLike.setText(dataBean.getAssist_num() > 0 ? String.valueOf(dataBean.getAssist_num()) : "赞");
                            tvLike.setSelected(!tvLike.isSelected());
                        } else {
                            ToastUtils.showShort(getActivity(), jsonObject.optString("msg"));
                        }
                    } else {
                        ToastUtils.showShort(getActivity(), "请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort(getActivity(), "请求失败");
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

    private void homePageVideosComment(final int video_id) {
        SendRequest.homeComment(video_id, new GenericsCallback<CommentData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(CommentData response, int id) {
                if (response != null && response.getCode() == 200 && response.getData() != null) {
                    if (commentListPopupWindow != null) {
                        commentListPopupWindow.setCommentData(response.getData().getData());
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
        commentListPopupWindow.setCommentData(commentData.getData().getData());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


}
