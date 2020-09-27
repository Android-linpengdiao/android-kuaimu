package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemWorkLayoutBinding;
import com.kuaimu.android.app.model.VideoDataBean;
import com.kuaimu.android.app.view.OnClickListener;


public class HomeWorkAdapter extends BaseRecyclerAdapter<VideoDataBean, ItemWorkLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HomeWorkAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_work_layout;
    }

    @Override
    protected void onBindItem(final ItemWorkLayoutBinding binding, final VideoDataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.title.setText(dataBean.getDesc());
            binding.assist.setText(""+dataBean.getAssist_num());
            binding.isAdvTextView.setVisibility(dataBean.isIs_adv()? View.VISIBLE : View.GONE);
            if (dataBean.getTourist()!=null) {
                binding.userVip.setVisibility(dataBean.getTourist().getIs_vip() == 1 ? View.VISIBLE : View.GONE);
                binding.userVip.setImageResource(dataBean.getTourist().getVip_type() == 1 ? R.mipmap.qi : R.mipmap.icon_vip);
                binding.personCategoryView.setText(dataBean.getTourist().getPerson_label());
                binding.userName.setText(dataBean.getTourist().getName());
                GlideLoader.LoderCircleImage(mContext, dataBean.getTourist().getAvatar(), binding.userIcon);
            }else {
                binding.userVip.setVisibility(View.GONE);
                binding.personCategoryView.setText("");
            }
            GlideLoader.LoderImage(mContext, dataBean.getImg(), binding.cover,5);
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v,position);
                    }
                }
            });
        }

    }
}
