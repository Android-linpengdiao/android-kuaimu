package com.kuaimu.android.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.baselibrary.utils.GlideLoader;
import com.bumptech.glide.Glide;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.view.OnClickListener;

import java.util.List;

public class MyImageAdapter extends PagerAdapter {
    private List<String> imageUrls;
    private Activity activity;

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MyImageAdapter(List<String> imageUrls, Activity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final String url = imageUrls.get(position);
        View inflate = activity.getLayoutInflater().inflate(R.layout.item_myimgage, null);
        ImageView mImg = inflate.findViewById(R.id.iv_item_myimg);
        Glide.with(activity).load(GlideLoader.domain + url).into(mImg);
        container.addView(inflate);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view, null);
                }
            }
        });
        mImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onClickListener != null)
                    onClickListener.onLongClick(view, url);
                return true;
            }
        });
        return inflate;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
