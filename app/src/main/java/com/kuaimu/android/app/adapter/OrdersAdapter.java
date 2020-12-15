package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.graphics.Color;

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

            /**
             * 成功. type=>消费类型 1充值2/打赏礼物/3收入礼物/4提现 trans_type=>交易类型 1消费 2充值 3 提现 4 收益 wallet_record=>用户金币数
             */

            if (dataBean.getType() == 1){
                binding.titleTextView.setText("金币充值");
                binding.priceView.setText("+" + dataBean.getWallet_record() + "乐币");

                binding.titleTextView.setTextColor(Color.parseColor("#2635EF"));
                binding.priceView.setTextColor(Color.parseColor("#2635EF"));

            }else if (dataBean.getType() == 2){
                binding.titleTextView.setText("打赏礼物");
                binding.priceView.setText("-" + dataBean.getWallet_record() + "乐币");

                binding.titleTextView.setTextColor(Color.parseColor("#333333"));
                binding.priceView.setTextColor(Color.parseColor("#333333"));

            }else if (dataBean.getType() == 3){
                binding.titleTextView.setText("视频收入·礼物");
                binding.priceView.setText("+" + dataBean.getWallet_record() + "乐币");

                binding.titleTextView.setTextColor(Color.parseColor("#2635EF"));
                binding.priceView.setTextColor(Color.parseColor("#2635EF"));

            }else if (dataBean.getType() == 4){
                binding.titleTextView.setText("提现");
                binding.priceView.setText("-" + dataBean.getWallet_record() + "元");

                binding.titleTextView.setTextColor(Color.parseColor("#333333"));
                binding.priceView.setTextColor(Color.parseColor("#333333"));

            }

            binding.timeTextView.setText(dataBean.getCreated_at());

        }

    }
}
