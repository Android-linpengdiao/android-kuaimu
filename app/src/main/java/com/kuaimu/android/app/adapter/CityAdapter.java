package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.view.View;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemCityListLayoutBinding;
import com.kuaimu.android.app.model.CityData;
import com.kuaimu.android.app.view.OnClickListener;


public class CityAdapter extends BaseRecyclerAdapter<CityData.FirstChildrenBean, ItemCityListLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CityAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_city_list_layout;
    }

    @Override
    protected void onBindItem(final ItemCityListLayoutBinding binding, final CityData.FirstChildrenBean bean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.name.setText(bean.getName());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v, bean.getName());
                    }
                }
            });
        }

    }
}
