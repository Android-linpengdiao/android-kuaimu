package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.UserHomeActivity;
import com.kuaimu.android.app.databinding.ItemMessageLayoutBinding;
import com.kuaimu.android.app.model.LikeData;
import com.kuaimu.android.app.view.OnClickListener;


public class LikeAdapter extends BaseRecyclerAdapter<LikeData.DataBeanX.DataBean, ItemMessageLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public LikeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_message_layout;
    }

    @Override
    protected void onBindItem(final ItemMessageLayoutBinding binding, final LikeData.DataBeanX.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.tvDesc.setText("赞了你视频");
            binding.tvTime.setText(dataBean.getUpdated_at());
            if (dataBean.getTourist() != null) {
                binding.tvTitle.setText(dataBean.getTourist().getName());
                GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), binding.userIcon);
            }
            if (dataBean.getVideo() != null) {
                GlideLoader.LoderImage(mContext, dataBean.getVideo().getImg(), binding.cover, 2);
            }
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean.getTourist() != null) {
                        Intent intent = new Intent(mContext, UserHomeActivity.class);
                        intent.putExtra("uid", dataBean.getTourist().getId());
                        intent.putExtra("isFollow", false);
                        mContext.startActivity(intent);
                    }
                }
            });
        }

    }
}
