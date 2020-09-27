package com.kuaimu.android.app.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.kuaimu.android.app.R;
import com.liaoinstan.springview.container.BaseHeader;

/**
 * @ClassName: SpringViewNewHeader
 * @Author: Carl
 * @Description: java类作用描述
 * @CreateDate: 2020-08-09 17:27
 * @UpdateUser: Carl
 * @UpdateDate: 2020-08-09 17:27
 * @UpdateRemark: 填写更新说明
 */
public class SpringViewNewFooter extends BaseHeader {
    private Context context;
    private int rotationSrc;
    private TextView footerTitle;
    private ProgressBar footerProgressbar;

    public SpringViewNewFooter(Context context){
        this(context, R.drawable.springview_new_progress);
    }

    public SpringViewNewFooter(Context context,int rotationSrc){
        this.context = context;
        this.rotationSrc = rotationSrc;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.springview_style_footer, viewGroup, true);
        footerTitle = (TextView) view.findViewById(R.id.default_footer_title);
        footerProgressbar = (ProgressBar) view.findViewById(R.id.default_footer_progressbar);
        footerProgressbar.setIndeterminateDrawable(ContextCompat.getDrawable(context,rotationSrc));
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (upORdown) {
            footerTitle.setText("松开载入更多");
        } else {
            footerTitle.setText("查看更多");
        }
    }

    @Override
    public void onStartAnim() {
        footerTitle.setVisibility(View.INVISIBLE);
        footerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        footerTitle.setText("查看更多");
        footerTitle.setVisibility(View.VISIBLE);
        footerProgressbar.setVisibility(View.INVISIBLE);
    }
}
