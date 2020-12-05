package com.kuaimu.android.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baselibrary.MessageBus;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.SharedPreferencesUtils;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.WorkDetailActivity;
import com.kuaimu.android.app.adapter.MineLikeWorkAdapter;
import com.kuaimu.android.app.databinding.FragmentMineWorkBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.MineLikeWorkData;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import okhttp3.Call;

public class UserLikeFragment extends BaseFragment {

    private static final String TAG = "UserLikeFragment";
    private FragmentMineWorkBinding binding;
    private MineLikeWorkAdapter adapter;
    private MineLikeWorkData mineLikeWorkData;
    private int uid;

    public static UserLikeFragment newInstance(int uid) {
        UserLikeFragment fragment = new UserLikeFragment();
        Bundle args = new Bundle();
        args.putInt("uid", uid);
        fragment.setArguments(args);
        SharedPreferencesUtils.getInstance().setUid(uid);
        return fragment;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            uid = getArguments().getInt("uid");
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_work, container, false);
        setStatusBarHeight(binding.getRoot());
        setStatusBarDarkTheme(true);

        adapter = new MineLikeWorkAdapter(getActivity());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(getActivity());
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(getActivity(), 1));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setVisibility(View.GONE);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (mineLikeWorkData != null) {
                    Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
                    BaseData baseData = new BaseData();
                    baseData.setData(mineLikeWorkData.getData());
                    intent.putExtra("baseData", baseData);
                    intent.putExtra("position", (int) object);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, Object object) {
                MessageBus.Builder builder = new MessageBus.Builder();
                MessageBus messageBus = builder
                        .codeType(MessageBus.msgId_workSelection)
                        .message(1)
                        .build();
                EventBus.getDefault().post(messageBus);
            }
        });
        EventBus.getDefault().register(this);

        uid = SharedPreferencesUtils.getInstance().getUid();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        initData(uid);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMainMessage(MessageBus messageBus) {
        StringBuffer stringBuffer = new StringBuffer();
        if (messageBus.getCodeType().equals(messageBus.msgId_workDelete)) {
            int tag = (int) messageBus.getMessage();
            if (tag == 1) {
                adapter.setSelection(false);
                if (adapter.getList() != null && adapter.getList().size() > 0) {
                    for (int i = 0; i < adapter.getList().size(); i++) {
//                        if (adapter.getList().get(i).getContent().isSelection()) {
//                            stringBuffer.append(adapter.getList().get(i).getContent_id() + ",");
//                        }
                    }
                }
                if (stringBuffer.length() > 0) {
                    deleteAssist(stringBuffer.substring(0, stringBuffer.length() - 1).toString());
                } else {
                    ToastUtils.showShort(getActivity(), "请选择作品");
                }
            }
        } else if (messageBus.getCodeType().equals(messageBus.msgId_workConfirm)) {
            int tag = (int) messageBus.getMessage();
            if (tag == 1) {
                adapter.setSelection(false);
            }
        } else if (messageBus.getCodeType().equals(messageBus.msgId_pageScrolled)) {
            int position = (int) messageBus.getMessage();
            binding.recyclerView.setVisibility(position==1?View.VISIBLE:View.GONE);

        } else if (messageBus.getCodeType().equals(messageBus.msgId_userHomeUid)) {
            int uid = (int) messageBus.getMessage();
//            initData(uid);

        }

    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void initData(int uid) {
        this.uid = uid;
        SendRequest.personAssist(uid, new GenericsCallback<MineLikeWorkData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(MineLikeWorkData response, int id) {
                if (response.getCode() == 200 && response.getData() != null && adapter != null) {
                    mineLikeWorkData = response;
                    adapter.refreshData(response.getData().getData());
                } else {
                    adapter.refreshData(new ArrayList<>());
//                    ToastUtils.showShort(getActivity(), response.getMsg());
                }
            }

        });
    }

    private void deleteAssist(String contentIds) {
        SendRequest.deleteAssist(getUserInfo().getData().getId(), contentIds,
                new GenericsCallback<BaseData>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(BaseData response, int id) {
                        if (response.getCode() == 200) {
                            initData(uid);
                        } else {
                            ToastUtils.showShort(getActivity(), response.getMsg());
                        }
                    }
                });
    }
}