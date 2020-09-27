package com.kuaimu.android.app.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemWorkVideoLayoutBinding;
import com.kuaimu.android.app.model.VideoDataBean;
import com.kuaimu.android.app.view.LiveClickListener;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.utils.APIUrls;

import org.json.JSONObject;

import okhttp3.Call;


public class WorkVideoAdapter extends BaseRecyclerAdapter<VideoDataBean, ItemWorkVideoLayoutBinding> {
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public WorkVideoAdapter(Context context) {
        super(context);
    }

    public VideoDataBean getItem(int position) {
        if (mList != null && mList.size() > 0) {
            return mList.get(position);
        } else {
            return null;
        }
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_work_video_layout;
    }

    @Override
    protected void onBindItem(final ItemWorkVideoLayoutBinding binding, final VideoDataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            if (dataBean.getTourist() != null) {
                binding.userName.setText(dataBean.getTourist().getName());
                GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), binding.userIcon);
            }
            binding.userDesc.setText(dataBean.getDesc());
            binding.tvLike.setSelected(dataBean.isIs_assist());
            binding.tvLike.setText(String.valueOf(dataBean.getAssist_num()));
            binding.tvComment.setText(String.valueOf(dataBean.getComment_num()));
            binding.tvShare.setText(String.valueOf(dataBean.getShare_num()));
            GlideLoader.LoderVideoCover(mContext, dataBean.getImg(), binding.thumbnails);
            binding.userInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view, dataBean);
                    }
                }
            });
            binding.ivFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    centerFollow(binding.ivFollow, dataBean.getTourist_id());
                }
            });
            binding.tvLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getUid(true) > 0) {
                        homePageVideosAssist(binding.tvLike, dataBean);
                    }
                }
            });
            binding.tvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view, dataBean);
                    }
                }
            });
            binding.tvShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view, dataBean);
                    }
                }
            });
            binding.videoView.setOnTouchListener(new LiveClickListener(new LiveClickListener.ClickCallBack() {
                @Override
                public void oneClick() {
                    if (onClickListener != null) {
                        onClickListener.onClick(binding.videoView, dataBean);
                    }
                }

                @Override
                public void doubleClick(int w, int y) {
                    if (getUid(true) > 0) {
                        if (!binding.tvLike.isSelected()) {
                            homePageVideosAssist(binding.tvLike, dataBean);
                        }
                    }
                    int liveAnimateImgWidth = 180;
                    ImageView likeImg = new ImageView(mContext);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(CommonUtil.dip2px(mContext, liveAnimateImgWidth), CommonUtil.dip2px(mContext, liveAnimateImgWidth));
                    params.leftMargin = w - CommonUtil.dip2px(mContext, liveAnimateImgWidth) * 1 / 2;
                    params.topMargin = y - CommonUtil.dip2px(mContext, liveAnimateImgWidth) * 5 / 6;
                    likeImg.setPadding(10, 10, 10, 10);
                    likeImg.setLayoutParams(params);
                    likeImg.setImageResource(R.drawable.likefill_pro);
                    binding.liveAnimateView.addView(likeImg);
                    startAnimatorStyleOne(likeImg);
                }
            }));
        }

    }

    private void centerFollow(final ImageView ivFollow, int uid) {
//        String url = binding.headLoginLayout.tvIsFollow.isSelected() ? APIUrls.url_centerUnFollow : APIUrls.url_centerFollow;
        SendRequest.centerFollow(getUserInfo().getData().getId(), uid, APIUrls.url_centerFollow, new StringCallback() {
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

}
