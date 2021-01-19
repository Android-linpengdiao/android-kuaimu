package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import android.view.View;

import com.baselibrary.MessageBus;
import com.baselibrary.UserInfo;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.MinePagerAdapter;
import com.kuaimu.android.app.adapter.UserPagerAdapter;
import com.kuaimu.android.app.databinding.ActivityUserHomeBinding;
import com.kuaimu.android.app.fragment.UserLikeFragment;
import com.kuaimu.android.app.fragment.UserWorkFragment;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.okhttp.utils.APIUrls;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import androidx.viewpager.widget.ViewPager;

import okhttp3.Call;

public class UserHomeActivity extends BaseActivity implements View.OnClickListener {
    private ActivityUserHomeBinding binding;
    private int uid;
    private boolean isFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_home);
        setStatusBarHeight(binding.getRoot());

        uid = getIntent().getIntExtra("uid", 0);

        binding.menuTextView.setOnClickListener(this);
        binding.back.setOnClickListener(this);
        binding.back.setVisibility(View.VISIBLE);
        binding.headLoginLayout.tvIsFollow.setVisibility(View.VISIBLE);
        binding.headLoginLayout.tvChat.setOnClickListener(this);
        binding.headLoginLayout.tvChat.setVisibility(View.VISIBLE);
        binding.headLoginLayout.tvVip.setVisibility(View.GONE);
        binding.headLoginLayout.tvSetting.setVisibility(View.GONE);
        binding.headLoginLayout.followersView.setOnClickListener(this);
        binding.headLoginLayout.likerView.setOnClickListener(this);
        binding.headLoginLayout.productView.setOnClickListener(this);

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
                                && jsonObject.optJSONObject("data").optBoolean("isLike")) {
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
        GlideLoader.LoderCircleImage(UserHomeActivity.this, userInfo.getData().getAvatar(), binding.headLoginLayout.userIcon);

        binding.headLoginLayout.tvIsFollow.setVisibility(getUid() == userInfo.getData().getId() ? View.GONE : View.VISIBLE);
        binding.headLoginLayout.tvChat.setVisibility(getUid() == userInfo.getData().getId() ? View.GONE : View.VISIBLE);

        //1通过 2正在审核 3审核未通过 4未认证
        if (getUserInfo().getData() != null && userInfo.getData().getBusiness_auth_status() != 4) {
            binding.menuTextView.setVisibility(View.VISIBLE);
        } else {
            binding.menuTextView.setVisibility(View.GONE);
        }

        initTab(userInfo);

    }

    private void initTab(UserInfo userInfo) {
        UserPagerAdapter mainHomePagerAdapter = new UserPagerAdapter(getSupportFragmentManager());
        mainHomePagerAdapter.addFragment("作品 " + (userInfo != null && userInfo.getData() != null ? userInfo.getData().getVideo_num() : 0), UserWorkFragment.newInstance(uid));
        mainHomePagerAdapter.addFragment("喜欢 " + (userInfo != null && userInfo.getData() != null ? userInfo.getData().getLike_video_num() : 0), UserLikeFragment.newInstance(uid));
        binding.viewPager.setAdapter(mainHomePagerAdapter);
        binding.viewPager.setOffscreenPageLimit(1);
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MessageBus.Builder builder = new MessageBus.Builder();
                MessageBus messageBus = builder
                        .codeType(MessageBus.msgId_pageScrolled)
                        .message(position)
                        .build();
                EventBus.getDefault().post(messageBus);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.menuTextView:
                bundle.putInt("uid", uid);
                openActivity(MerchantActivity.class, bundle);

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
                                        ToastUtils.showShort(UserHomeActivity.this, "已关注TA");
                                    }
                                } else {
                                    ToastUtils.showShort(UserHomeActivity.this, jsonObject.optString("msg"));
                                }
                            } else {
                                ToastUtils.showShort(UserHomeActivity.this, "请求失败");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.showShort(UserHomeActivity.this, "请求失败");
                        }

                    }
                });
                break;
            case R.id.followers_view:
                if (getUid(true) > 0) {
                    bundle.putInt("uid", uid);
                    openActivity(MineFollowActivity.class, bundle);
                }
                break;
            case R.id.liker_view:
                if (getUid(true) > 0) {
                    bundle.putInt("uid", uid);
                    openActivity(MineFansActivity.class, bundle);
                }
                break;
            case R.id.product_view:
                bundle.putInt("uid", uid);
                openActivity(MyProductActivity.class, bundle);
                break;
        }
    }
}