package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.UserHomeActivity;
import com.kuaimu.android.app.databinding.ItemFriendsLayoutBinding;
import com.kuaimu.android.app.model.FansUserData;
import com.kuaimu.android.app.view.OnClickListener;


public class MineFansAdapter extends BaseRecyclerAdapter<FansUserData.DataBean, ItemFriendsLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MineFansAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_friends_layout;
    }

    @Override
    protected void onBindItem(final ItemFriendsLayoutBinding binding, final FansUserData.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            if (!CommonUtil.isBlank(dataBean.getTourist())) {
                binding.tvTitle.setText(dataBean.getTourist().getName());
                binding.tvDesc.setText("粉丝：" + dataBean.getFollower_num());
                binding.tvFollowers.setText(dataBean.isBe_attention() ? "已关注" : "关注");
                GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), binding.userIcon);
            }
//            binding.tvFollowers.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onClickListener != null) {
//                        onClickListener.onClick(v, dataBean);
//                    }
//                }
//            });
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean.getTourist() != null) {
                        Intent intent = new Intent(mContext, UserHomeActivity.class);
                        intent.putExtra("uid", dataBean.getTourist().getId());
//                      intent.putExtra("isFollow", dataBean.isAttention());
                        mContext.startActivity(intent);
                    }
                }
            });
        }

    }
}
