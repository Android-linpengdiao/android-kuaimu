package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemCommentListLayoutBinding;
import com.kuaimu.android.app.model.CommentData;
import com.kuaimu.android.app.view.OnClickListener;


public class CommentListAdapter extends BaseRecyclerAdapter<CommentData.DataBean.CommentBean, ItemCommentListLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CommentListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_comment_list_layout;
    }

    @Override
    protected void onBindItem(final ItemCommentListLayoutBinding binding, final CommentData.DataBean.CommentBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            if (!CommonUtil.isBlank(dataBean.getTourist())) {
                binding.userName.setText(dataBean.getTourist().getName());
                GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), binding.userIcon);
            }
            binding.tvTime.setText(dataBean.getUpdated_at());
            binding.tvComment.setText(dataBean.getContent());
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v, position);
                    }
                }
            });
        }

    }
}
