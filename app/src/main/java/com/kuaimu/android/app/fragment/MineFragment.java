package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baselibrary.MessageBus;
import com.baselibrary.UserInfo;
import com.baselibrary.manager.DialogManager;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.EditorActivity;
import com.kuaimu.android.app.activity.MineFansActivity;
import com.kuaimu.android.app.activity.MineFollowActivity;
import com.kuaimu.android.app.activity.MyProductActivity;
import com.kuaimu.android.app.activity.MyVIPActivity;
import com.kuaimu.android.app.activity.SettingsActivity;
import com.kuaimu.android.app.adapter.CustomPagerAdapter;
import com.kuaimu.android.app.databinding.FragmentMineBinding;
import com.kuaimu.android.app.utils.SpringViewNewHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.Call;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "MineFragment";
    private FragmentMineBinding binding;
    private CustomPagerAdapter mainHomePagerAdapter;

    private MineWorkFragment mineWorkFragment;
    private MineLikeFragment mineLikeFragment;

    private int uid = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        setStatusBarHeight(binding.getRoot());
        setStatusBarDarkTheme(true);

        binding.headLoginLayout.userIcon.setOnClickListener(this);
        binding.headLoginLayout.tvVip.setOnClickListener(this);
        binding.headLoginLayout.tvSetting.setOnClickListener(this);
        binding.headLoginLayout.followersView.setOnClickListener(this);
        binding.headLoginLayout.likerView.setOnClickListener(this);
        binding.headLoginLayout.productView.setOnClickListener(this);
        binding.workDeleteView.setOnClickListener(this);
        binding.tvDelete.setOnClickListener(this);
        binding.tvConfirm.setOnClickListener(this);

        binding.springMine.setHeader(new SpringViewNewHeader(getContext()));
        binding.springMine.setType(SpringView.Type.FOLLOW);
        binding.springMine.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                binding.springMine.onFinishFreshAndLoad();
                initView();
                mineWorkFragment.initData(getUid());
                mineLikeFragment.initData(getUid());
            }

            @Override
            public void onLoadmore() {
            }
        });

        uid = getUid();

        initTab();

        EventBus.getDefault().register(this);

        return binding.getRoot();
    }

    private int tag = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMainMessage(MessageBus messageBus) {
        if (messageBus.getCodeType().equals(messageBus.msgId_workSelection)) {
            Log.i(TAG, "getMainMessage: ");
            tag = (int) messageBus.getMessage();
            binding.workDeleteView.setVisibility(View.VISIBLE);
            binding.tvTag.setText(tag == 0 ? "作品 " + getUserInfo().getData().getVideo_num() : "喜欢 " + getUserInfo().getData().getLike_video_num());
        }

    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserInfo().getData() != null) {
            baseInfo();
        } else {
            initView();
        }
        if (getUid() != 0 && uid!=getUid()){
            uid = getUid();
            initView();
            mineWorkFragment.initData(getUid());
            mineLikeFragment.initData(getUid());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (getUserInfo().getData() != null) {
                baseInfo();
            } else {
                initView();
            }
        }
    }

    public void baseInfo() {
        SendRequest.baseInfo(getUserInfo().getData().getId(), new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(UserInfo response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    setUserInfo(response);
                    initView();
                }
            }

        });
    }

    private void initTab() {
        mainHomePagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        mainHomePagerAdapter.addTitle("作品 " + (getUserInfo().getData() != null ? getUserInfo().getData().getVideo_num() : 0));
        mainHomePagerAdapter.addTitle("喜欢 " + (getUserInfo().getData() != null ? getUserInfo().getData().getLike_video_num() : 0));
        mineWorkFragment = MineWorkFragment.newInstance(getUid());
        mineLikeFragment = MineLikeFragment.newInstance(getUid());
        mainHomePagerAdapter.addFragment(mineWorkFragment);
        mainHomePagerAdapter.addFragment(mineLikeFragment);
        binding.viewPager.setAdapter(mainHomePagerAdapter);
        binding.viewPager.setOffscreenPageLimit(1);
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    private void initView() {
        if (getUserInfo().getData() != null) {
            binding.headLoginLayout.headLoginView.setVisibility(View.VISIBLE);

            binding.headLoginLayout.userName.setText(getUserInfo().getData().getName());
            binding.headLoginLayout.touristId.setText("ID：" + getUserInfo().getData().getTourist_id());
            GlideLoader.LoderCircleImage(getActivity(), getUserInfo().getData().getAvatar(), binding.headLoginLayout.userIcon);
            binding.headLoginLayout.userVip.setVisibility(getUserInfo().getData().getIs_vip() == 1 ? View.VISIBLE : View.GONE);
            binding.headLoginLayout.userVip.setImageResource(getUserInfo().getData().getVip_type() == 1 ? R.mipmap.qi : R.mipmap.icon_vip);

            binding.headLoginLayout.label.setVisibility(View.VISIBLE);
            if (!CommonUtil.isBlank(getUserInfo().getData().getPerson_label()) && !CommonUtil.isBlank(getUserInfo().getData().getBus_label())) {
                binding.headLoginLayout.label.setText(getUserInfo().getData().getPerson_label() + "  |  " + getUserInfo().getData().getBus_label());
            } else if (!CommonUtil.isBlank(getUserInfo().getData().getPerson_label())) {
                binding.headLoginLayout.label.setText(getUserInfo().getData().getPerson_label());
            } else if (!CommonUtil.isBlank(getUserInfo().getData().getBus_label())) {
                binding.headLoginLayout.label.setText(getUserInfo().getData().getBus_label());
            } else {
                binding.headLoginLayout.label.setVisibility(View.GONE);
            }
            binding.headLoginLayout.tvFollowers.setText(String.valueOf(getUserInfo().getData().getAttention_num()));
            binding.headLoginLayout.tvLiker.setText(String.valueOf(getUserInfo().getData().getFollower_num()));
            binding.headLoginLayout.tvAssistNum.setText(String.valueOf(getUserInfo().getData().getGood_num()));

        } else {
            binding.headLoginLayout.headLoginView.setVisibility(View.GONE);
        }
        if (mainHomePagerAdapter != null) {
            mainHomePagerAdapter.setPageTitle(0, "作品 " + (getUserInfo().getData() != null ? getUserInfo().getData().getVideo_num() : 0));
            mainHomePagerAdapter.setPageTitle(1, "喜欢 " + (getUserInfo().getData() != null ? getUserInfo().getData().getLike_video_num() : 0));

        }
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_delete:
                DialogManager.showConfirmDialog(getActivity(), "确定要删除该作品？", new DialogManager.Listener() {
                    @Override
                    public void onItemLeft() {

                    }

                    @Override
                    public void onItemRight() {
                        MessageBus.Builder builder = new MessageBus.Builder();
                        MessageBus messageBus = builder
                                .codeType(MessageBus.msgId_workDelete)
                                .message(tag)
                                .build();
                        EventBus.getDefault().post(messageBus);
                        binding.workDeleteView.setVisibility(View.GONE);
                    }
                });
                break;
            case R.id.tv_confirm:
                MessageBus.Builder builder = new MessageBus.Builder();
                MessageBus messageBus = builder
                        .codeType(MessageBus.msgId_workConfirm)
                        .message(tag)
                        .build();
                EventBus.getDefault().post(messageBus);
                binding.workDeleteView.setVisibility(View.GONE);
                break;
            case R.id.user_icon:
                openActivity(EditorActivity.class);
                break;
            case R.id.tv_vip:
                openActivity(MyVIPActivity.class);
                break;
            case R.id.tv_setting:
                openActivity(SettingsActivity.class);
                break;
            case R.id.followers_view:
                bundle.putInt("uid", getUid());
                openActivity(MineFollowActivity.class, bundle);
                break;
            case R.id.liker_view:
                bundle.putInt("uid", getUid());
                openActivity(MineFansActivity.class, bundle);
                break;
            case R.id.product_view:
                bundle.putInt("uid", getUid());
                openActivity(MyProductActivity.class, bundle);
                break;
        }
    }

}
