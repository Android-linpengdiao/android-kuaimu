package com.kuaimu.android.app.adapter;

import android.content.Context;

import com.baselibrary.utils.CommonUtil;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemTextLayoutBinding;
import com.kuaimu.android.app.view.OnClickListener;

public class DiscoverCommentAdapter extends BaseRecyclerAdapter<DiscoverData.DataBeanX.DataBean.DiscoverCommentBean, ItemTextLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DiscoverCommentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_text_layout;
    }

    @Override
    protected void onBindItem(final ItemTextLayoutBinding binding, final DiscoverData.DataBeanX.DataBean.DiscoverCommentBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.contentView.setText(CommonUtil.isBlank(dataBean.getTourist()) ? dataBean.getContent().trim() : dataBean.getTourist().getName() + "：" + dataBean.getContent().trim());
        }

    }
}
