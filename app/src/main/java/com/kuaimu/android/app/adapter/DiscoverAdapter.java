package com.kuaimu.android.app.adapter;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.Call;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.UserHomeActivity;
import com.kuaimu.android.app.databinding.ItemDiacoverLayoutBinding;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.utils.APIUrls;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DiscoverAdapter extends BaseRecyclerAdapter<DiscoverData.DataBeanX.DataBean, ItemDiacoverLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DiscoverAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_diacover_layout;
    }

    @Override
    protected void onBindItem(final ItemDiacoverLayoutBinding binding, final DiscoverData.DataBeanX.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.tvContent.setText(dataBean.getDesc());
            if (dataBean.getTourist() != null) {
                binding.userName.setText(dataBean.getTourist().getName());
                GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), binding.userIcon);
            }
            binding.tvLike.setSelected(dataBean.isIs_liker());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = CommonUtil.getDuration(mContext, dataBean.getCreated_at(), df.format(new Date()));
            binding.tvTime.setText(time);

            ImageAdapter adapter = new ImageAdapter(mContext);
            binding.imageRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
            GridItemDecoration.Builder builder = new GridItemDecoration.Builder(mContext);
            builder.color(R.color.transparent);
            builder.size(CommonUtil.dip2px(mContext, 3));
            binding.imageRecyclerView.setAdapter(adapter);
            if (!CommonUtil.isBlank(dataBean.getImg())) {
                adapter.refreshData(CommonUtil.stringToList(dataBean.getImg()));
            }

            if (dataBean.getDiscover_comment() != null && dataBean.getDiscover_comment().size() > 0) {
                binding.commentRecyclerView.setVisibility(View.VISIBLE);
                DiscoverCommentAdapter textAdapter = new DiscoverCommentAdapter(mContext);
                binding.commentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                binding.commentRecyclerView.setAdapter(textAdapter);
                if (!CommonUtil.isBlank(dataBean.getDiscover_comment()) && dataBean.getDiscover_comment().size() > 3) {
                    textAdapter.refreshData(dataBean.getDiscover_comment().subList(0, 3));
                } else {
                    textAdapter.refreshData(dataBean.getDiscover_comment());
                }
                binding.tvComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(view, dataBean);
                        }
                    }
                });
            } else {
                binding.commentRecyclerView.setVisibility(View.GONE);
            }

            binding.userIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dataBean.getTourist() != null) {
                        Intent intent = new Intent(mContext, UserHomeActivity.class);
                        intent.putExtra("uid", dataBean.getTourist().getId());
                        mContext.startActivity(intent);
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
            binding.tvLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getUid(true)>0) {
                        discoverLike(binding.tvLike, dataBean);
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

        }

    }

    private void discoverLike(final TextView tvLike, final DiscoverData.DataBeanX.DataBean dataBean) {
        String url = tvLike.isSelected() ? APIUrls.discoverUnLike : APIUrls.discoverLike;
        SendRequest.discoverLike(getUid(), dataBean.getId(), url, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    if (!CommonUtil.isBlank(response)) {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optInt("code") == 200) {
                            dataBean.setIs_liker(!tvLike.isSelected());
                            tvLike.setSelected(dataBean.isIs_liker());
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
}
