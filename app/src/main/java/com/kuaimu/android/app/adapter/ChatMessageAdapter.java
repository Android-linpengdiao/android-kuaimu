package com.kuaimu.android.app.adapter;

import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baselibrary.Constants;
import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.MsgCache;
import com.kuaimu.android.app.MyApplication;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ItemChatMessageReceiveLayoutBinding;
import com.kuaimu.android.app.databinding.ItemChatMessageSendLayoutBinding;
import com.kuaimu.android.app.model.HomeLeaveData;

import java.util.ArrayList;
import java.util.List;


public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HomeLeaveData.DataBean> list = new ArrayList<>();

    public List<HomeLeaveData.DataBean> getList() {
        return list;
    }

    public ChatMessageAdapter(Context context) {
        this.context = context;
    }

    public void refreshData(List<HomeLeaveData.DataBean> list) {
        if (null != list) {
            this.list = list;
            notifyDataSetChanged();
        }
    }

    public void loadMoreData(List<HomeLeaveData.DataBean> list) {
        if (null != list) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case 0:
                ItemChatMessageReceiveLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_chat_message_receive_layout, viewGroup, false);
                return new ReceiveViewHolder(binding);
            case 1:
                ItemChatMessageSendLayoutBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_chat_message_send_layout, viewGroup, false);
                return new SendVieHolder(mBinding);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ReceiveViewHolder) {
            ReceiveViewHolder hotViewHolder = (ReceiveViewHolder) viewHolder;
            hotViewHolder.bind(position);
        } else if (viewHolder instanceof SendVieHolder) {
            SendVieHolder huaTiVieHolder = (SendVieHolder) viewHolder;
            huaTiVieHolder.bind(position);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getTourist_id() == MyApplication.getInstance().getUserInfo().getData().getId()) {
            return 1;
        } else {
            return 0;
        }

    }

    private class ReceiveViewHolder extends RecyclerView.ViewHolder {

        private ItemChatMessageReceiveLayoutBinding binding;

        private ReceiveViewHolder(ItemChatMessageReceiveLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(int i) {
            HomeLeaveData.DataBean data = list.get(i);
            binding.messageView.setText(data.getContent());
            if (data.getTourist()!=null) {
                GlideLoader.LoderCircleImage(context, data.getTourist().getAvatar(), binding.userIcon);
            }
        }
    }


    private class SendVieHolder extends RecyclerView.ViewHolder {

        private ItemChatMessageSendLayoutBinding binding;

        private SendVieHolder(ItemChatMessageSendLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        private void bind(int i) {
            HomeLeaveData.DataBean data = list.get(i);
            binding.messageView.setText(data.getContent());
            if (data.getTourist()!=null) {
                GlideLoader.LoderCircleImage(context, getUserInfo().getData().getAvatar(), binding.userIcon);
            }
        }
    }

    public UserInfo getUserInfo() {
        UserInfo userinfo = (UserInfo) MsgCache.get(context).getAsObject(Constants.USER_INFO);
        if (!CommonUtil.isBlank(userinfo)) {
            return userinfo;
        }
        return new UserInfo();
    }
}
