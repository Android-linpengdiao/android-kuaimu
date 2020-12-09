package com.kuaimu.android.app.view;

import android.content.Context;
import android.database.DatabaseUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.baselibrary.utils.BasePopupWindow;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.MyApplication;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.CashPayActivity;
import com.kuaimu.android.app.adapter.CoinAdapter;
import com.kuaimu.android.app.adapter.GiftAdapter;
import com.kuaimu.android.app.databinding.ViewGiftPopupLayoutBinding;
import com.kuaimu.android.app.model.WalletSetData;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import okhttp3.Call;

public class GiftPopupWindow extends BasePopupWindow {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public GiftPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int animationStyle() {
        return R.style.PopupAnimation;
    }

    private WalletSetData.DataBean dataBean;
    @Override
    protected View initContentView() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.view_gift_popup_layout, null, false);
        ViewGiftPopupLayoutBinding binding = DataBindingUtil.bind(contentView);
        View viewLayout = contentView.findViewById(R.id.view_layout);

        GiftAdapter adapter = new GiftAdapter(context);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (object instanceof WalletSetData.DataBean) {
                    dataBean = (WalletSetData.DataBean) object;
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });
        SendRequest.walletSet(MyApplication.getInstance().getUserInfo().getData().getId(),
                new GenericsCallback<WalletSetData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(WalletSetData response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    adapter.refreshData(response.getData());
                } else {
                    ToastUtils.showShort(context, response.getMsg());
                }
            }

        });

        viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return contentView;
    }

    @Override
    protected int initWidth() {
        return CommonUtil.getScreenWidth(context);
    }

    @Override
    protected int initHeight() {
        return -1;
    }
}