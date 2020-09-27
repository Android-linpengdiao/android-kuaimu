package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemMainNoticeLayoutBinding;
import com.kuaimu.android.app.model.LeaveUserData;
import com.kuaimu.android.app.view.OnClickListener;


public class MainNoticeAdapter extends BaseRecyclerAdapter<LeaveUserData.DataBean, ItemMainNoticeLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MainNoticeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_main_notice_layout;
    }

    @Override
    protected void onBindItem(final ItemMainNoticeLayoutBinding binding, final LeaveUserData.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            if (dataBean.getTourist_id() == getUid()) {
                if (dataBean.getBe_tourist() != null) {
                    binding.tvTitle.setText(dataBean.getBe_tourist().getName());
                    GlideLoader.LoderCircleImage(mContext, dataBean.getBe_tourist().getAvatar(), binding.userIcon);
                }
            }else {
                if (dataBean.getTourist() != null) {
                    binding.tvTitle.setText(dataBean.getTourist().getName());
                    GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), binding.userIcon);
                }
            }
            binding.tvDesc.setText(dataBean.getContent());
            binding.tvTime.setText(CommonUtil.getMeesageTime(CommonUtil.getStringToDate(dataBean.getUpdated_at())));
            binding.noticeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v, dataBean);
                    }
                }
            });
        }

    }
}
