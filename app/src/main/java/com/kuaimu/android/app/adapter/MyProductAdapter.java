package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.ProductDetailActivity;
import com.kuaimu.android.app.databinding.ItemProductLayoutBinding;
import com.kuaimu.android.app.model.PersonGood;


public class MyProductAdapter extends BaseRecyclerAdapter<PersonGood.DataBean, ItemProductLayoutBinding> {

    public MyProductAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_product_layout;
    }

    @Override
    protected void onBindItem(final ItemProductLayoutBinding binding, final PersonGood.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.title.setText(dataBean.getName());
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) binding.cover.getLayoutParams();
//            layoutParams.height = (CommonUtil.getScreenWidth(mContext) - CommonUtil.dip2px(mContext, 30)) / 2;
//            binding.cover.setLayoutParams(layoutParams);
            GlideLoader.LoderRoundedImage(mContext, dataBean.getImg(), binding.cover, 8);
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProductDetailActivity.class);
                    intent.putExtra("id", dataBean.getId());
                    intent.putExtra("uid", dataBean.getTourist_id());
                    mContext.startActivity(intent);
                }
            });
        }

    }
}
