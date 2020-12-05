package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.UserHomeActivity;
import com.kuaimu.android.app.databinding.ItemFriendsLayoutBinding;
import com.kuaimu.android.app.model.FollowUserData;
import com.kuaimu.android.app.view.OnClickListener;


public class MineFollowAdapter extends BaseRecyclerAdapter<FollowUserData.DataBeanX.DataBean, ItemFriendsLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MineFollowAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_friends_layout;
    }

    @Override
    protected void onBindItem(final ItemFriendsLayoutBinding binding, final FollowUserData.DataBeanX.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            if (dataBean.getTourist()!=null) {
                binding.tvTitle.setText(dataBean.getTourist().getName());
                binding.tvDesc.setText("粉丝：" + dataBean.getTourist().getFan_number());
                binding.tvFollowers.setText("已关注");
                GlideLoader.LoderCircleImage(mContext,dataBean.getTourist().getAvatar(), binding.userIcon);
            }
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, UserHomeActivity.class);
                    intent.putExtra("uid", dataBean.getId());
                    mContext.startActivity(intent);
                }
            });
        }

    }
}
