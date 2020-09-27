package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.PagerAdapter;
import com.kuaimu.android.app.databinding.ActivityWorkDetailBinding;
import com.kuaimu.android.app.fragment.WorkUserHomeFragment;
import com.kuaimu.android.app.fragment.WorkVideoFragment;
import com.kuaimu.android.app.model.BaseData;

public class WorkDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener, WorkVideoFragment.OnVideoFragmentInteractionListener, WorkUserHomeFragment.OnUserFragmentInteractionListener {

    private ActivityWorkDetailBinding binding;
    private WorkVideoFragment workVideoFragment;
    private WorkUserHomeFragment workUserHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        BaseData baseData = (BaseData) getIntent().getSerializableExtra("baseData");
        int position = getIntent().getIntExtra("position", 0);

        PagerAdapter mainHomePagerAdapter = new PagerAdapter(getSupportFragmentManager());
        workVideoFragment = WorkVideoFragment.newInstance(baseData, position);
        workUserHomeFragment = WorkUserHomeFragment.newInstance();
        mainHomePagerAdapter.addFragment("视频", workVideoFragment);
        mainHomePagerAdapter.addFragment("用户", workUserHomeFragment);
        binding.viewPager.setAdapter(mainHomePagerAdapter);
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOnPageChangeListener(this);

    }

    @Override
    public void onUserFragmentInteraction() {
        binding.viewPager.setCurrentItem(0);
    }

    @Override
    public void onVideoFragmentInteraction(int type, int uid) {
        if (type == 0) {
            finish();
        } else if (type == 1) {
            binding.viewPager.setCurrentItem(1);
        } else if (type == 2) {
            workUserHomeFragment.updateUser(uid);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (binding.viewPager.getCurrentItem() == 1) {
                binding.viewPager.setCurrentItem(0);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            workVideoFragment.videoPlay(true);
        } else {
            workVideoFragment.videoPlay(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
