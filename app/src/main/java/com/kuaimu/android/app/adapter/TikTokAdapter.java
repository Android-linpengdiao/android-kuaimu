package com.kuaimu.android.app.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baselibrary.Constants;
import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.MsgCache;
import com.baselibrary.utils.ToastUtils;
import com.dkplayer.utils.cache.PreloadManager;
import com.dkplayer.view.LoadingView;
import com.dkplayer.widget.component.TikTokView;
import com.kuaimu.android.app.MyApplication;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.LoginActivity;
import com.kuaimu.android.app.model.VideoDataBean;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.utils.APIUrls;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

@Deprecated
public class TikTokAdapter extends RecyclerView.Adapter<TikTokAdapter.VideoHolder> {

    private Context mContext;
    private List<VideoDataBean> videos;

    public TikTokAdapter(Context context, List<VideoDataBean> videos) {
        this.mContext = context;
        this.videos = videos;
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tik_tok, parent, false);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, int position) {
        final VideoDataBean dataBean = videos.get(position);

        if (dataBean.getTourist() != null) {
            holder.userName.setText(dataBean.getTourist().getName());
            GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), holder.userIcon);
            holder.deleteView.setVisibility(dataBean.getTourist().getId()==getUid()?View.VISIBLE:View.GONE);
        }else {
            holder.deleteView.setVisibility(View.GONE);
        }
        holder.userDesc.setText(dataBean.getDesc());
//        holder.tvLike.setSelected(dataBean.isIs_assist());
//        holder.tvLike.setText(String.valueOf(dataBean.getAssist_num()));
        holder.tvComment.setText(String.valueOf(dataBean.getComment_num()));
        holder.tvShare.setText(String.valueOf(dataBean.getShare_num()));
        GlideLoader.LoderVideoCover(mContext, dataBean.getImg(), holder.thumb);
        holder.userInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view, dataBean);
                }
            }
        });
        holder.ivFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerFollow(holder.ivFollow, dataBean.getTourist_id());
            }
        });
//        holder.tvLike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (getUid(true) > 0) {
//                    homePageVideosAssist(holder.tvLike, dataBean);
//                }
//            }
//        });
        holder.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view, dataBean);
                }
            }
        });
        holder.tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view, dataBean);
                }
            }
        });
//        holder.videoView.setOnTouchListener(new LiveClickListener(new LiveClickListener.ClickCallBack() {
//            @Override
//            public void oneClick() {
//                if (onClickListener != null) {
//                    onClickListener.onClick(holder.videoView, dataBean);
//                }
//            }
//
//            @Override
//            public void doubleClick(int w, int y) {
//                if (getUid(true) > 0) {
//                    if (!binding.tvLike.isSelected()) {
//                        homePageVideosAssist(binding.tvLike, dataBean);
//                    }
//                }
//                int liveAnimateImgWidth = 180;
//                ImageView likeImg = new ImageView(mContext);
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(CommonUtil.dip2px(mContext, liveAnimateImgWidth), CommonUtil.dip2px(mContext, liveAnimateImgWidth));
//                params.leftMargin = w - CommonUtil.dip2px(mContext, liveAnimateImgWidth) * 1 / 2;
//                params.topMargin = y - CommonUtil.dip2px(mContext, liveAnimateImgWidth) * 5 / 6;
//                likeImg.setPadding(10, 10, 10, 10);
//                likeImg.setLayoutParams(params);
//                likeImg.setImageResource(R.drawable.likefill_pro);
//                binding.liveAnimateView.addView(likeImg);
//                startAnimatorStyleOne(likeImg);
//            }
//        }));

        holder.mPosition = position;
        String playUrl = null;
        if (dataBean != null && !CommonUtil.isBlank(dataBean.getVideo())) {
            if (dataBean.getVideo().startsWith("http")) {
                playUrl = PreloadManager.getInstance(MyApplication.getInstance()).getPlayUrl(dataBean.getVideo());
            } else {
                playUrl = PreloadManager.getInstance(MyApplication.getInstance()).getPlayUrl(GlideLoader.domain + dataBean.getVideo());
            }
        }
        PreloadManager.getInstance(holder.itemView.getContext()).addPreloadTask(playUrl, position);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VideoHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder.mPosition>=videos.size()){
            return;
        }
        VideoDataBean item = videos.get(holder.mPosition);
        String playUrl = null;
        if (item != null && !CommonUtil.isBlank(item.getVideo())) {
            if (item.getVideo().startsWith("http")) {
                playUrl = PreloadManager.getInstance(MyApplication.getInstance()).getPlayUrl(item.getVideo());
            } else {
                playUrl = PreloadManager.getInstance(MyApplication.getInstance()).getPlayUrl(GlideLoader.domain + item.getVideo());
            }
        }
        PreloadManager.getInstance(holder.itemView.getContext()).removePreloadTask(playUrl);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class VideoHolder extends RecyclerView.ViewHolder {

        private ImageView thumb;
        public TikTokView mTikTokView;
        public int mPosition;
        public FrameLayout mPlayerContainer;
        private TextView userName;
        private TextView userDesc;
        private View userInfoView;
        private ImageView userIcon;
        private ImageView ivFollow;
        private TextView tvLike;
        private TextView tvComment;
        private TextView tvShare;
        private ImageView deleteView;
        public LoadingView loadingView;
        private View liveAnimateView;

        VideoHolder(View itemView) {
            super(itemView);
            mTikTokView = itemView.findViewById(R.id.tiktok_View);
            thumb = mTikTokView.findViewById(R.id.iv_thumb);
            mPlayerContainer = itemView.findViewById(R.id.container);
            userName = itemView.findViewById(R.id.userName);
            userDesc = itemView.findViewById(R.id.userDesc);
            userInfoView = itemView.findViewById(R.id.userInfoView);
            userIcon = itemView.findViewById(R.id.user_icon);
            ivFollow = itemView.findViewById(R.id.ivFollow);
            tvLike = itemView.findViewById(R.id.tv_like);
            tvComment = itemView.findViewById(R.id.tv_comment);
            tvShare = itemView.findViewById(R.id.tv_share);
            deleteView = itemView.findViewById(R.id.deleteView);
            loadingView = itemView.findViewById(R.id.loadingView);
            liveAnimateView = itemView.findViewById(R.id.live_animate_view);
            itemView.setTag(this);
        }
    }


    private void centerFollow(final ImageView ivFollow, int uid) {
        String url = ivFollow.isSelected() ? APIUrls.url_centerUnFollow : APIUrls.url_centerFollow;
        SendRequest.centerFollow(getUserInfo().getData().getId(), uid, url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    if (!CommonUtil.isBlank(response)) {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optInt("code") == 200) {
                            ivFollow.setVisibility(View.INVISIBLE);
                        }
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
                            ToastUtils.showShort(mContext, jsonObject.optString("msg"));
                        }
                    } else {
                        ToastUtils.showShort(mContext, "请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort(mContext, "请求失败");
                }

            }
        });
    }


    /**
     * AnimatorSet实现组合动画
     * AnimatorSet可以指定动画同时或按顺序执行
     */
    private void startAnimatorStyleOne(final ImageView liveAnimateImg) {
        //实现旋转动画
        ObjectAnimator rotationAnimaotr = ObjectAnimator.ofFloat(liveAnimateImg, "rotation", 60f, 0f, 0f);
        //缩放动画
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(liveAnimateImg, "scaleX", 1f, 0.5f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(liveAnimateImg, "scaleY", 1f, 0.5f);
        //透明度动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(liveAnimateImg, "alpha", 0.1f, 1f);
        //然后通过AnimatorSet把几种动画组合起来
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimator).with(scaleYAnimator)
                .with(alphaAnimator);
        //设置动画时间
        animatorSet.setDuration(100);
        //开始动画
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                liveAnimateImg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopAnimatorStyleOne(liveAnimateImg);
                    }
                }, 500);
            }
        });
    }

    private void stopAnimatorStyleOne(final ImageView liveAnimateImg) {
        //缩放动画
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(liveAnimateImg, "scaleX", 0.5f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(liveAnimateImg, "scaleY", 0.5f, 1f);
        //透明度动画
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(liveAnimateImg, "alpha", 1f, 0.1f);
        //然后通过AnimatorSet把几种动画组合起来
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimator).with(scaleYAnimator)
                .with(alphaAnimator);
        //设置动画时间
        animatorSet.setDuration(300);
        //开始动画
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                liveAnimateImg.setVisibility(View.GONE);
            }
        });
    }

    public UserInfo getUserInfo() {
        UserInfo userinfo = (UserInfo) MsgCache.get(mContext).getAsObject(Constants.USER_INFO);
        if (!CommonUtil.isBlank(userinfo)) {
            return userinfo;
        }
        return new UserInfo();
    }

    public int getUid() {
        return getUid(false);
    }

    public int getUid(boolean login) {
        if (getUserInfo().getData() != null) {
            return getUserInfo().getData().getId();
        } else {
            if (login) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
            }
            return 0;
        }
    }
}