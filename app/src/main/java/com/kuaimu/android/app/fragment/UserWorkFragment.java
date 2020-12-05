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
import com.kuaimu.android.app.adapter.MineWorkAdapter;
import com.kuaimu.android.app.databinding.FragmentMineWorkBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.MineWorkData;
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

public class UserWorkFragment extends BaseFragment {

    private static final String TAG = "UserWorkFragment";
    private FragmentMineWorkBinding binding;
    private MineWorkAdapter adapter;
    private MineWorkData mineWorkData;
    private int uid;

    public static UserWorkFragment newInstance(int uid) {
        Log.i(TAG, "newInstance: "+uid);
        UserWorkFragment fragment = new UserWorkFragment();
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
//            Log.i(TAG, "onCreate: "+uid);
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_work, container, false);

        adapter = new MineWorkAdapter(getActivity());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(getActivity());
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(getActivity(), 1));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (mineWorkData != null) {
                    Intent intent = new Intent(getActivity(), WorkDetailActivity.class);
                    BaseData baseData = new BaseData();
                    baseData.setData(mineWorkData.getData().getData());
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
                        .message(0)
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
            if (tag == 0) {
                adapter.setSelection(false);
                if (adapter.getList() != null && adapter.getList().size() > 0) {
                    for (int i = 0; i < adapter.getList().size(); i++) {
//                        if (adapter.getList().get(i).isSelection()) {
//                            stringBuffer.append(adapter.getList().get(i).getId() + ",");
//                        }
                    }
                }
                if (stringBuffer.length() > 0) {
                    deleteContent(stringBuffer.substring(0, stringBuffer.length() - 1).toString());
                } else {
                    ToastUtils.showShort(getActivity(), "请选择作品");
                }
            }
        } else if (messageBus.getCodeType().equals(messageBus.msgId_workConfirm)) {
            int tag = (int) messageBus.getMessage();
            if (tag == 0) {
                adapter.setSelection(false);
            }
        } else if (messageBus.getCodeType().equals(messageBus.msgId_pageScrolled)) {
            int position = (int) messageBus.getMessage();
            binding.recyclerView.setVisibility(position == 0 ? View.VISIBLE : View.GONE);

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
        SendRequest.centerSelfWork(uid, 200, 1, new GenericsCallback<MineWorkData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(MineWorkData response, int id) {
                if (response.getCode() == 200 && response.getData() != null && response.getData().getData() != null && adapter != null) {
                    mineWorkData = response;
                    adapter.refreshData(response.getData().getData());
                } else {
                    adapter.refreshData(new ArrayList<>());
//                    ToastUtils.showShort(getActivity(), response.getMsg());
                }
            }

        });
    }

    private void deleteContent(String contentIds) {
        SendRequest.deleteContent(getUserInfo().getData().getId(), contentIds,
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
