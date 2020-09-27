package com.kuaimu.android.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemImageLayoutBinding;
import com.kuaimu.android.app.utils.DialogUtil;
import com.kuaimu.android.app.view.OnClickListener;

public class ImageAdapter extends BaseRecyclerAdapter<String, ItemImageLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ImageAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_image_layout;
    }

    @Override
    protected void onBindItem(final ItemImageLayoutBinding binding, final String dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            GlideLoader.LoderImage(mContext, dataBean, binding.cover);
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUtil.getInstance().showMoreImageView((Activity) mContext,mList,position);
                }
            });
        }

    }
}
