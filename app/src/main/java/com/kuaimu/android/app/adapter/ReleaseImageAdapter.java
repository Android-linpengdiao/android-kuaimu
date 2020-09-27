package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemReleaseImageLayoutBinding;
import com.kuaimu.android.app.view.OnClickListener;


public class ReleaseImageAdapter extends BaseRecyclerAdapter<String, ItemReleaseImageLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ReleaseImageAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_release_image_layout;
    }

    @Override
    protected void onBindItem(final ItemReleaseImageLayoutBinding binding, final String str, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.delete.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
            if (position == 0) {
                GlideLoader.LoderDrawable(mContext, R.drawable.picture_upload, binding.image, 8);
            } else {
                GlideLoader.LoderLoadImage(mContext, str, binding.image, 8);
            }
            binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    notifyDataSetChanged();
                }
            });
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view, position);
                    }
                }
            });
        }

    }
}
