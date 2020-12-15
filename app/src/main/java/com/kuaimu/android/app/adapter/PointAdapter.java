package com.kuaimu.android.app.adapter;

import android.content.Context;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemOrdersLayoutBinding;
import com.kuaimu.android.app.model.IntegralData;
import com.kuaimu.android.app.model.OrdersData;
import com.kuaimu.android.app.view.OnClickListener;

public class PointAdapter extends BaseRecyclerAdapter<IntegralData.DataBeanXX.DataBeanX.DataBean, ItemOrdersLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public PointAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_orders_layout;
    }

    @Override
    protected void onBindItem(final ItemOrdersLayoutBinding binding, final IntegralData.DataBeanXX.DataBeanX.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.titleTextView.setText(dataBean.getType_name());
            binding.timeTextView.setText(dataBean.getCreated_at());
            binding.priceView.setText("+" + dataBean.getNum() + "积分");
        }

    }
}
