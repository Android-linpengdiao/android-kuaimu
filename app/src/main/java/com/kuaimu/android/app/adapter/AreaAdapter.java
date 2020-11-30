package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemAreaListLayoutBinding;
import com.kuaimu.android.app.model.CityData;
import com.kuaimu.android.app.view.OnClickListener;


public class AreaAdapter extends BaseRecyclerAdapter<CityData, ItemAreaListLayoutBinding> {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public AreaAdapter(Context context) {
        super(context);
    }

    public int getLetterPosition(String letter) {
        Integer integer = null;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getName().equals(letter)) {
                integer = i;
            }
        }
        return integer == null ? -1 : integer;
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_area_list_layout;
    }

    private static final String TAG = "AreaAdapter";

    @Override
    protected void onBindItem(final ItemAreaListLayoutBinding binding, final CityData cityData, final int position) {
        if (mList != null && mList.size() > 0) {
            Log.i(TAG, "onBindItem: " + cityData.getName());
            binding.name.setText(cityData.getName());
            if (cityData.getChildren().size() > 0) {
                CityAdapter cityAdapter = new CityAdapter(mContext);
                binding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                binding.recyclerView.setAdapter(cityAdapter);
                cityAdapter.refreshData(cityData.getChildren());
                cityAdapter.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view, Object object) {
                        if (onClickListener != null) {
                            onClickListener.onClick(view, object);
                        }
                    }

                    @Override
                    public void onLongClick(View view, Object object) {

                    }
                });
            }
        }

    }
}
