package com.kuaimu.android.app.view;

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baselibrary.utils.BasePopupWindow;
import com.baselibrary.utils.CommonUtil;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.CommentListAdapter;
import com.kuaimu.android.app.model.CommentData;

import java.util.List;

public class CommentListPopupWindow extends BasePopupWindow {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CommentListPopupWindow(Context context) {
        super(context);
    }

    public void setCommentData(List<CommentData.DataBean.CommentBean> commentDatas) {
        if (commentDatas != null) {
            adapter.refreshData(commentDatas);
            tvCommentNumber.setText("全部评论(" + commentDatas.size() + ")");
        }
    }

    @Override
    protected int animationStyle() {
        return R.style.PopupAnimation;
    }

    private CommentListAdapter adapter;
    private TextView tvCommentNumber;

    @Override
    protected View initContentView() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.view_comment_list_popup_layout, null, false);
        View viewLayout = contentView.findViewById(R.id.view_layout);
        View close = contentView.findViewById(R.id.iv_close);
        RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView);
        TextView tvMessageInput = contentView.findViewById(R.id.tv_message_input);
        tvCommentNumber = contentView.findViewById(R.id.tv_comment_number);
        tvMessageInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                InputDialog inputDialog = new InputDialog((Activity) context);
                inputDialog.setmOnTextSendListener(new InputDialog.OnTextSendListener() {
                    @Override
                    public void onTextSend(String msg) {
                        onClickListener.onClick(v, msg);
                    }
                });
                inputDialog.show();
            }
        });


        adapter = new CommentListAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
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