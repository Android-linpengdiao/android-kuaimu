package com.kuaimu.android.app.fragment;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.ChatActivity;
import com.kuaimu.android.app.activity.MineFansActivity;
import com.kuaimu.android.app.activity.MineFollowActivity;
import com.kuaimu.android.app.activity.MyProductActivity;
import com.kuaimu.android.app.adapter.MinePagerAdapter;
import com.kuaimu.android.app.databinding.FragmentWorkUserHomeBinding;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.okhttp.utils.APIUrls;

import org.json.JSONObject;

import okhttp3.Call;

public class WorkUserHomeFragment extends BaseFragment implements View.OnClickListener {

    private FragmentWorkUserHomeBinding binding;
    private OnUserFragmentInteractionListener mListener;
    private int uid;

    public static WorkUserHomeFragment newInstance() {
        WorkUserHomeFragment fragment = new WorkUserHomeFragment();
        Bundle args = new Bundle();
//        args.putInt("uid", uid);
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnUserFragmentInteractionListener {
        void onUserFragmentInteraction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserFragmentInteractionListener) {
            mListener = (OnUserFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            uid = getArguments().getInt("uid");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_work_user_home, container, false);
        setStatusBarHeight(binding.getRoot());

        binding.headLoginLayout.back.setOnClickListener(this);
        binding.headLoginLayout.back.setVisibility(View.VISIBLE);
        binding.headLoginLayout.tvIsFollow.setVisibility(View.VISIBLE);
        binding.headLoginLayout.tvChat.setOnClickListener(this);
        binding.headLoginLayout.tvChat.setVisibility(View.VISIBLE);
        binding.headLoginLayout.tvVip.setVisibility(View.GONE);
        binding.headLoginLayout.settingView.setVisibility(View.GONE);
        binding.headLoginLayout.followersView.setOnClickListener(this);
        binding.headLoginLayout.likerView.setOnClickListener(this);
        binding.headLoginLayout.productView.setOnClickListener(this);

        initData();

        return binding.getRoot();

    }

    public void updateUser(int uid) {
        this.uid = uid;
        initData();
    }

    private void initData() {

        SendRequest.baseInfo(uid, new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(UserInfo response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    initView(response);
                }
            }

        });

        SendRequest.isFollow(getUid(), uid, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    if (!CommonUtil.isBlank(response)) {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.optInt("code") == 200
                                && !CommonUtil.isBlank(jsonObject.optJSONObject("data"))
                                && jsonObject.optJSONObject("data").optBoolean("is_attention")) {
                            binding.headLoginLayout.tvIsFollow.setSelected(!binding.headLoginLayout.tvIsFollow.isSelected());
                            binding.headLoginLayout.tvIsFollow.setText(binding.headLoginLayout.tvIsFollow.isSelected() ? "已关注TA" : "关注TA");
                        } else {

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView(UserInfo userInfo) {

        binding.headLoginLayout.tvIsFollow.setOnClickListener(this);

        binding.headLoginLayout.userName.setText(userInfo.getData().getName());
        binding.headLoginLayout.touristId.setText("ID：" + userInfo.getData().getTourist_id());
        GlideLoader.LoderCircleImage(getContext(), userInfo.getData().getAvatar(), binding.headLoginLayout.userIcon);
        binding.headLoginLayout.userVip.setVisibility(userInfo.getData().getIs_vip() == 1 ? View.VISIBLE : View.GONE);
        binding.headLoginLayout.userVip.setImageResource(userInfo.getData().getVip_type() == 1 ? R.mipmap.qi : R.mipmap.icon_vip);


        binding.headLoginLayout.label.setVisibility(View.VISIBLE);
        if (!CommonUtil.isBlank(userInfo.getData().getPerson_label()) && !CommonUtil.isBlank(userInfo.getData().getBus_label())) {
            binding.headLoginLayout.label.setText(userInfo.getData().getPerson_label() + "  |  " + userInfo.getData().getBus_label());
        } else if (!CommonUtil.isBlank(userInfo.getData().getPerson_label())) {
            binding.headLoginLayout.label.setText(userInfo.getData().getPerson_label());
        } else if (!CommonUtil.isBlank(userInfo.getData().getBus_label())) {
            binding.headLoginLayout.label.setText(userInfo.getData().getBus_label());
        } else {
            binding.headLoginLayout.label.setVisibility(View.GONE);
        }
        binding.headLoginLayout.tvFollowers.setText(String.valueOf(userInfo.getData().getAttention_num()));
        binding.headLoginLayout.tvLiker.setText(String.valueOf(userInfo.getData().getFollower_num()));
        binding.headLoginLayout.tvAssistNum.setText(String.valueOf(userInfo.getData().getGood_num()));

//        binding.headLoginLayout.tvFollowers.setText(getUserInfo().getData().getFollowers() + "");
//        binding.headLoginLayout.tvLiker.setText(getUserInfo().getData().getLiker() + "");

//        binding.headLoginLayout.tvIsFollow.setSelected(isFollow);
//        binding.headLoginLayout.tvIsFollow.setText(isFollow ? "已关注" : "关注");

        initTab(userInfo);

    }

    private void initTab(UserInfo userInfo) {
        MinePagerAdapter mainHomePagerAdapter = new MinePagerAdapter(getChildFragmentManager(), uid);
        mainHomePagerAdapter.addTitle("作品 " + (userInfo.getData() != null ? userInfo.getData().getVideo_num() : 0));
        mainHomePagerAdapter.addTitle("喜欢 " + (userInfo.getData() != null ? userInfo.getData().getLike_video_num() : 0));
        binding.viewPager.setAdapter(mainHomePagerAdapter);
        binding.viewPager.setOffscreenPageLimit(1);
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.back:
                mListener.onUserFragmentInteraction();
                break;
            case R.id.tv_chat:
                bundle.putInt("uid", uid);
                openActivity(ChatActivity.class, bundle);
                break;
            case R.id.tv_is_follow:
                if (CommonUtil.isBlank(uid)) {
                    return;
                }
                String url = binding.headLoginLayout.tvIsFollow.isSelected() ? APIUrls.url_centerUnFollow : APIUrls.url_centerFollow;
                SendRequest.centerFollow(getUserInfo().getData().getId(), uid, url, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            if (!CommonUtil.isBlank(response)) {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.optInt("code") == 200) {
                                    binding.headLoginLayout.tvIsFollow.setSelected(!binding.headLoginLayout.tvIsFollow.isSelected());
                                    binding.headLoginLayout.tvIsFollow.setText(binding.headLoginLayout.tvIsFollow.isSelected() ? "已关注TA" : "关注TA");
                                    if (binding.headLoginLayout.tvIsFollow.isSelected()) {
                                        ToastUtils.showShort(getActivity(), "已关注TA");
                                    }
                                } else {
                                    ToastUtils.showShort(getActivity(), jsonObject.optString("msg"));
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), "请求失败");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.showShort(getActivity(), "请求失败");
                        }

                    }
                });
                break;
            case R.id.followers_view:
                bundle.putInt("uid", uid);
                openActivity(MineFollowActivity.class, bundle);
                break;
            case R.id.liker_view:
                bundle.putInt("uid", uid);
                openActivity(MineFansActivity.class, bundle);
                break;
            case R.id.product_view:
                bundle.putInt("uid", uid);
                openActivity(MyProductActivity.class, bundle);
                break;
        }
    }
}