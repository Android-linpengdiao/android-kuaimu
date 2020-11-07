package com.kuaimu.android.app.adapter;

import android.content.Context;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemOrdersLayoutBinding;
import com.kuaimu.android.app.model.OrdersData;
import com.kuaimu.android.app.view.OnClickListener;

public class OrdersAdapter extends BaseRecyclerAdapter<OrdersData.DataBeanX.DataBean, ItemOrdersLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OrdersAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_orders_layout;
    }

    @Override
    protected void onBindItem(final ItemOrdersLayoutBinding binding, final OrdersData.DataBeanX.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.titleTextView.setText(dataBean.getPurpose() == 1 ? "开通企业会员" : "开通个人会员");
            binding.timeTextView.setText(dataBean.getCreated_at());
            binding.priceView.setText("-" + dataBean.getPrice() + "元");
            binding.vipTimeView.setText("有效值截止" + dataBean.getVip_time());
        }

    }
}
