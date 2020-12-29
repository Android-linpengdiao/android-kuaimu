package com.kuaimu.android.app.fragment;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baselibrary.MessageBus;
import com.baselibrary.UserInfo;
import com.baselibrary.manager.DialogManager;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.activity.ChatActivity;
import com.kuaimu.android.app.activity.MineFansActivity;
import com.kuaimu.android.app.activity.MineFollowActivity;
import com.kuaimu.android.app.activity.MyProductActivity;
import com.kuaimu.android.app.adapter.MinePagerAdapter;
import com.kuaimu.android.app.adapter.UserPagerAdapter;
import com.kuaimu.android.app.databinding.FragmentWorkUserHomeBinding;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;
import com.okhttp.utils.APIUrls;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import androidx.viewpager.widget.ViewPager;

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

        void onUserFragmentInteractionFollow(boolean follow);
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

        binding.back.setOnClickListener(this);
        binding.back.setVisibility(View.VISIBLE);
//        binding.menuView.setOnClickListener(this);
//        binding.menuView.setVisibility(View.VISIBLE);
        binding.menuTextView.setOnClickListener(this);
        binding.headLoginLayout.tvIsFollow.setVisibility(View.VISIBLE);
        binding.headLoginLayout.tvChat.setOnClickListener(this);
        binding.headLoginLayout.tvChat.setVisibility(View.VISIBLE);
        binding.headLoginLayout.tvVip.setVisibility(View.GONE);
        binding.headLoginLayout.tvSetting.setVisibility(View.GONE);
        binding.headLoginLayout.followersView.setOnClickListener(this);
        binding.headLoginLayout.likerView.setOnClickListener(this);
        binding.headLoginLayout.productView.setOnClickListener(this);

        return binding.getRoot();

    }

    private static final String TAG = "WorkUserHomeFragment";

//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.i(TAG, "onResume: ");
//        initData();
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        Log.i(TAG, "onHiddenChanged: ");
//        initData();
//    }

    public void updateUser(int uid) {
        Log.i(TAG, "updateUser: " + uid);
        this.uid = uid;
        initData();
    }

    public void followUser(boolean follow) {
        binding.headLoginLayout.tvIsFollow.setSelected(follow);
        binding.headLoginLayout.tvIsFollow.setText(binding.headLoginLayout.tvIsFollow.isSelected() ? "已关注TA" : "关注TA");
    }

    private void initData() {
        Log.i(TAG, "initData: " + uid);
        SendRequest.baseInfo(uid, new GenericsCallback<UserInfo>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                initView(null);
            }

            @Override
            public void onResponse(UserInfo response, int id) {
                if (response.getCode() == 200 && response.getData() != null) {
                    initView(response);
                } else {
                    initView(null);
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

        if (userInfo != null && userInfo.getData() != null) {
            binding.headLoginLayout.tvIsFollow.setOnClickListener(this);

            binding.headLoginLayout.userName.setText(userInfo.getData().getName());
            binding.headLoginLayout.touristId.setText("ID：" + userInfo.getData().getTourist_id());
            GlideLoader.LoderCircleImage(getContext(), userInfo.getData().getAvatar(), binding.headLoginLayout.userIcon);

            binding.headLoginLayout.tvIsFollow.setVisibility(getUid() == userInfo.getData().getId() ? View.GONE : View.VISIBLE);
            binding.headLoginLayout.tvChat.setVisibility(getUid() == userInfo.getData().getId() ? View.GONE : View.VISIBLE);

            binding.headLoginLayout.tvFollowers.setText(String.valueOf(userInfo.getData().getFollow_number()));
            binding.headLoginLayout.tvLiker.setText(String.valueOf(userInfo.getData().getFan_number()));

            //1通过 2正在审核 3审核未通过 4未认证
            if (getUserInfo().getData() != null && getUserInfo().getData().getBusiness_auth_status() != 4) {
                binding.menuTextView.setVisibility(View.VISIBLE);
            }else {
                binding.menuTextView.setVisibility(View.GONE);
            }

        } else {
            binding.headLoginLayout.tvIsFollow.setOnClickListener(this);

            binding.headLoginLayout.userName.setText("");
            binding.headLoginLayout.touristId.setText("ID：");
            GlideLoader.LoderCircleImage(getContext(), "http", binding.headLoginLayout.userIcon);
            binding.headLoginLayout.userVip.setVisibility(View.GONE);
            binding.headLoginLayout.tvIsFollow.setVisibility(View.GONE);
            binding.headLoginLayout.tvChat.setVisibility(View.GONE);
            binding.headLoginLayout.label.setText("");
            binding.headLoginLayout.label.setVisibility(View.GONE);
            binding.headLoginLayout.tvFollowers.setText("");
            binding.headLoginLayout.tvLiker.setText("");
            binding.headLoginLayout.tvAssistNum.setText("");
            binding.menuTextView.setVisibility(View.GONE);
        }

        initTab(userInfo);

    }

    private void initTab(UserInfo userInfo) {
        UserPagerAdapter mainHomePagerAdapter = new UserPagerAdapter(getChildFragmentManager());
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

        MessageBus.Builder builder = new MessageBus.Builder();
        MessageBus messageBus = builder
                .codeType(MessageBus.msgId_userHomeUid)
                .message(uid)
                .build();
        EventBus.getDefault().post(messageBus);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.back:
                mListener.onUserFragmentInteraction();
                break;
            case R.id.tv_chat:
                if (getUid(true) > 0) {
                    bundle.putInt("uid", uid);
                    openActivity(ChatActivity.class, bundle);
                }
                break;
            case R.id.tv_is_follow:
                if (getUid(true) > 0) {
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
                                        mListener.onUserFragmentInteractionFollow(binding.headLoginLayout.tvIsFollow.isSelected());
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
                }
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
            case R.id.menuView:
                createMenuPopup();

                break;
            case R.id.menuTextView:


                break;
        }
    }

    private void createMenuPopup() {
//        final PopupWindow popupWindow = new PopupWindow(getActivity());
//        LayoutInflater mInflater = getLayoutInflater();
//        View rootView = mInflater.inflate(R.layout.view_user_menu_layout, null);
//        TextView confirm = rootView.findViewById(R.id.confirm);
//        TextView cancel = rootView.findViewById(R.id.cancel);
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popupWindow.dismiss();
//                DialogManager.showReportDialog(getActivity(), new DialogManager.OnClickListener() {
//                    @Override
//                    public void onClick(View view, Object object) {
//                        SendRequest.report(getUid(), String.valueOf(object), new GenericsCallback(new JsonGenericsSerializator()) {
//                            @Override
//                            public void onError(Call call, Exception e, int id) {
//
//                            }
//
//                            @Override
//                            public void onResponse(Object response, int id) {
//
//                            }
//                        });
//                    }
//                });
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow.setContentView(rootView);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.setWidth(-1);
//        popupWindow.setHeight(-2);
//        popupWindow.setFocusable(true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
//        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            public void onDismiss() {
//            }
//        });


    }
}