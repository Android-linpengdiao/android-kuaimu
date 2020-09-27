package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.ChatActivity;
import com.kuaimu.android.app.activity.NoticeListActivity;
import com.kuaimu.android.app.adapter.MainNoticeAdapter;
import com.kuaimu.android.app.databinding.FragmentNoticeBinding;
import com.kuaimu.android.app.model.LeaveUserData;
import com.kuaimu.android.app.model.NoticeData;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.Call;

public class NoticeFragment extends BaseFragment {
    private static final String TAG = "NoticeFragment";
    private FragmentNoticeBinding binding;
    private MainNoticeAdapter adapter;
    private int uid = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice, container, false);

        adapter = new MainNoticeAdapter(getActivity());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (object instanceof LeaveUserData.DataBean) {
                    LeaveUserData.DataBean dataBean = (LeaveUserData.DataBean) object;
                    if (dataBean.getTourist_id() == getUid()) {
                        if (dataBean.getBe_tourist() != null) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("uid", dataBean.getBe_tourist().getId());
                            openActivity(ChatActivity.class, bundle);
                        }
                    }else {
                        if (dataBean.getTourist() != null) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("uid", dataBean.getTourist().getId());
                            openActivity(ChatActivity.class, bundle);
                        }

                    }
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });

        initData();

        binding.noticeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(NoticeListActivity.class);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUid() != 0 && uid!=getUid()){
            initData();
        }
    }

    private void initData() {
        uid = getUid();
        SendRequest.messageNotice(getUid(), new GenericsCallback<NoticeData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(NoticeData response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    binding.tvDesc.setText(response.getData().size() > 0 ? response.getData().get(0).getDesc() : "");
                    binding.tvTime.setText(response.getData().size() > 0 ? CommonUtil.getMeesageTime(CommonUtil.getStringToDate(response.getData().get(0).getUpdated_at())) : "");
                } else {
                    ToastUtils.showShort(getActivity(), response.getMsg());
                }
            }

        });
        SendRequest.leaveUser(getUid(), new GenericsCallback<LeaveUserData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(LeaveUserData response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    adapter.refreshData(response.getData());
                } else {
                    ToastUtils.showShort(getActivity(), response.getMsg());
                }
            }

        });
    }
}
