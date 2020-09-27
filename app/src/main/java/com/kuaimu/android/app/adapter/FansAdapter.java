package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.UserHomeActivity;
import com.kuaimu.android.app.databinding.ItemFansLayoutBinding;
import com.kuaimu.android.app.model.FansData;
import com.kuaimu.android.app.view.OnClickListener;


public class FansAdapter extends BaseRecyclerAdapter<FansData.DataBeanX.DataBean, ItemFansLayoutBinding> {
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public FansAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_fans_layout;
    }

    @Override
    protected void onBindItem(final ItemFansLayoutBinding binding, final FansData.DataBeanX.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.tvDesc.setText("关注了你");
            binding.tvTime.setText(dataBean.getUpdated_at());
            if (dataBean.getTourist()!=null) {
                binding.tvTitle.setText(dataBean.getTourist().getName());
                GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), binding.userIcon);
            }
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean.getTourist()!=null) {
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
