package com.kuaimu.android.app.adapter;

import android.content.Context;
import android.view.View;

import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemCoinLayoutBinding;
import com.kuaimu.android.app.databinding.ItemGiftLayoutBinding;
import com.kuaimu.android.app.model.GiftData;
import com.kuaimu.android.app.model.WalletSetData;
import com.kuaimu.android.app.view.OnClickListener;


public class GiftAdapter extends BaseRecyclerAdapter<GiftData.DataBean, ItemGiftLayoutBinding> {
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public GiftAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) { //应该在此根viewType 选择不同布局的,但设计图上给的差距不大.就整合成一个布局了
        return R.layout.item_gift_layout;
    }

    @Override
    protected void onBindItem(final ItemGiftLayoutBinding binding, final GiftData.DataBean dataBean, final int position) {
        if (mList != null && mList.size() > 0) {
            binding.nameTextView.setText(dataBean.getTitle());
            binding.walletTokenTextView.setText(dataBean.getWallet_token() + "乐币");
            GlideLoader.LoderImage(mContext, dataBean.getImg(), binding.coverImageView);
            binding.nameTextView.setVisibility(dataBean.getSelected() == 0 ? View.VISIBLE : View.GONE);
            binding.sendTextView.setVisibility(dataBean.getSelected() == 0 ? View.GONE : View.VISIBLE);
            binding.viewLayout.setSelected(dataBean.getSelected() == 0 ? false : true);
            binding.sendTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v, dataBean);
                    }
                }
            });
            binding.viewLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        dataBean.setSelected(dataBean.getSelected() == 0 ? 1 : 0);
                        binding.viewLayout.setSelected(dataBean.getSelected() == 0 ? false : true);
                        for (int i = 0; i < mList.size(); i++) {
                            if (dataBean != mList.get(i)) {
                                mList.get(i).setSelected(0);
                            }
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }

    }
}
