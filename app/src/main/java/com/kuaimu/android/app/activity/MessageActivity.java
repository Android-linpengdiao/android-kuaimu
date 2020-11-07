package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityMessageBinding;
import com.kuaimu.android.app.fragment.CommentFragment;
import com.kuaimu.android.app.fragment.FansFragment;
import com.kuaimu.android.app.fragment.LikeFragment;
import com.kuaimu.android.app.fragment.NoticeFragment;
import com.kuaimu.android.app.utils.ViewUtils;

public class MessageActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private ActivityMessageBinding binding;
    private FragmentManager mFragmentManager;
    public static Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_message);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.radioGroupView.setOnCheckedChangeListener(this);
        initDefaultFragment();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_button_like:
                replaceContentFragment(LikeFragment.class);
                break;
            case R.id.radio_button_fans:
                replaceContentFragment(FansFragment.class);
                break;
            case R.id.radio_button_comment:
                replaceContentFragment(CommentFragment.class);
                break;
            case R.id.radio_button_notice:
                replaceContentFragment(NoticeFragment.class);
                break;
            default:
                break;
        }
    }

    private void initDefaultFragment() {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = ViewUtils.createFragment(LikeFragment.class, true);
        mFragmentManager.beginTransaction().add(R.id.content_frame, mCurrentFragment).commit();
    }

    public Fragment replaceContentFragment(Class<?> mclass) {
        Fragment fragment = ViewUtils.createFragment(mclass, true);
        if (fragment.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(fragment).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.content_frame, fragment).commitAllowingStateLoss();
        }
        mCurrentFragment = fragment;
        return mCurrentFragment;
    }

}
