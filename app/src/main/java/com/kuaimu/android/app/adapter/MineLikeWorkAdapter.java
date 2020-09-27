package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemMineWorkLayoutBinding;
import com.kuaimu.android.app.model.VideoDataBean;
import com.kuaimu.android.app.view.OnClickListener;

public class MineLikeWorkAdapter extends BaseRecyclerAdapter<VideoDataBean, ItemMineWorkLayoutBinding> {

    private boolean isSelection = false;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setSelection(boolean selection) {
        isSelection = selection;
        notifyDataSetChanged();
    }

    public MineLikeWorkAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_mine_work_layout;
    }

    @Override
    protected void onBindItem(final ItemMineWorkLayoutBinding binding, final VideoDataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.tvAssist.setText(String.valueOf(dataBean.getAssist_num()));
            GlideLoader.LoderImage(mContext, dataBean.getImg(), binding.cover);
//            binding.selection.setSelected(!CommonUtil.isBlank(dataBean.getContent())?dataBean.getContent().isSelection():false);
//            binding.selection.setVisibility(isSelection ? View.VISIBLE : View.GONE);
//            binding.selection.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (!CommonUtil.isBlank(dataBean.getContent())) {
//                        dataBean.getContent().setSelection(!dataBean.getContent().isSelection());
//                        binding.selection.setSelected(dataBean.getContent().isSelection());
//                        notifyItemChanged(position);
//                    }
//                }
//            });
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v,position);
                    }
                }
            });
            binding.viewLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onClickListener != null) {
//                        isSelection = true;
//                        onClickListener.onLongClick(view, true);
//                        notifyDataSetChanged();
                    }
                    return true;
                }
            });
        }

    }
}
