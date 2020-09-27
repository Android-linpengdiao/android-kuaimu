package com.kuaimu.android.app.activity;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.baselibrary.MessageBus;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.NavData;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.PagerAdapter;
import com.kuaimu.android.app.adapter.SearchHistoryAdapter;
import com.kuaimu.android.app.adapter.SearchTabAdapter;
import com.kuaimu.android.app.adapter.SearchUserTabAdapter;
import com.kuaimu.android.app.databinding.ActivitySearchBinding;
import com.kuaimu.android.app.db.DBManager;
import com.kuaimu.android.app.fragment.SearchResultFragment;
import com.kuaimu.android.app.view.FlowLayoutManager;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.kuaimu.android.app.view.SpaceItemDecoration;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.Call;

public class SearchActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private static final String TAG = "SearchActivity";
    private ActivitySearchBinding binding;
    private SearchHistoryAdapter searchHistoryAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private String busLabel = "全部";
    private String personLabel = "全部";
    private int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        setStatusBarHeight(binding.getRoot());

        binding.searchBack.setOnClickListener(this);
        binding.searchContentDelete.setOnClickListener(this);
        binding.deleteSearchHistory.setOnClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, null, R.string.app_name, R.string.app_name);
        binding.drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        intHeaderView();

        List<String> searchHistorys = DBManager.getInstance().querySearchHistory(0);
        binding.searchHistoryRecyclerView.addItemDecoration(new SpaceItemDecoration(CommonUtil.dip2px(this, 10)));
        binding.searchHistoryRecyclerView.setLayoutManager(new FlowLayoutManager());
        searchHistoryAdapter = new SearchHistoryAdapter(this);
        binding.searchHistoryRecyclerView.setAdapter(searchHistoryAdapter);
        searchHistoryAdapter.refreshData(searchHistorys);

        searchHistoryAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                String content = (String) object;
                binding.etSearch.setText(content);
                binding.etSearch.setSelection(content.length());
                initSearchView(content);
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (CommonUtil.isBlank(charSequence.toString())) {
                    initSearchView(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String content = binding.etSearch.getText().toString().trim();
                    if (!CommonUtil.isBlank(content)) {
                        // 隐藏键盘
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        DBManager.getInstance().insertSearchContent(0, content);
                        searchHistoryAdapter.refreshData(DBManager.getInstance().querySearchHistory(0));
                        initSearchView(content);
                    } else {
                        ToastUtils.showShort(SearchActivity.this, "请输入搜索内容");
                    }
                    return true;
                }
                return false;
            }
        });

        binding.searchType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        binding.allTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryNavData != null && categoryNavData.getCode() == 200 && categoryNavData.getData().size() > 0) {
                    binding.allTypeView.setTextColor(getResources().getColor(R.color.titleColor));
                    binding.viewPager.setCurrentItem(0);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    private SearchUserTabAdapter userTabAdapter;
    private SearchTabAdapter tabAdapter;
    private NavData categoryNavData;
    private NavData personNavData;

    private void intHeaderView() {
        binding.navView.setNavigationItemSelectedListener(SearchActivity.this);
        View headerView = binding.navView.getHeaderView(0);
        View tagStatusBar = headerView.findViewById(R.id.tagStatusBar);
        ImageView navBack = headerView.findViewById(R.id.nav_back);
        RecyclerView userTagRecyclerView = headerView.findViewById(R.id.userTagRecyclerView);
        RecyclerView tagRecyclerView = headerView.findViewById(R.id.tagRecyclerView);
        setCustomStatusBarHeight(tagStatusBar);
        navBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        userTabAdapter = new SearchUserTabAdapter(this);
        userTagRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(this, 10));
        userTagRecyclerView.addItemDecoration(new GridItemDecoration(builder));
        userTagRecyclerView.setAdapter(userTabAdapter);
        userTabAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (object instanceof NavData.DataBean) {
                    NavData.DataBean dataBean = (NavData.DataBean) object;
                    personLabel = dataBean.getName();
                    binding.drawerLayout.closeDrawers();
                    MessageBus.Builder builder = new MessageBus.Builder();
                    MessageBus messageBus = builder
                            .codeType(MessageBus.msgId_searchLabel)
                            .message(personLabel)
                            .build();
                    EventBus.getDefault().post(messageBus);
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });

        tabAdapter = new SearchTabAdapter(this);
        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagRecyclerView.setAdapter(tabAdapter);
        tabAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                currentItem = (Integer) object;
                binding.viewPager.setCurrentItem(currentItem);
                binding.drawerLayout.closeDrawers();
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });

        category(1);
        category(2);
    }

    private void initSearchView(String content) {
        binding.searchHistoryView.setVisibility(CommonUtil.isBlank(content) ? View.VISIBLE : View.GONE);
        binding.searchResultView.setVisibility(CommonUtil.isBlank(content) ? View.GONE : View.VISIBLE);
        binding.emptyView.setVisibility(CommonUtil.isBlank(content) ? View.GONE : View.VISIBLE);
        searchWork(content);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_search_history:
                DBManager.getInstance().deleteSearchHistory(0);
                searchHistoryAdapter.refreshData(DBManager.getInstance().querySearchHistory(0));
                break;
            case R.id.search_back:
                finish();
                break;
            case R.id.search_content_delete:
                binding.etSearch.setText("");
                initSearchView(null);
                break;
        }
    }

    private void category(int type) {
        if (type == 1) {
            SendRequest.personCategory(new GenericsCallback<NavData>(new JsonGenericsSerializator()) {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(NavData response, int id) {
                    if (response.getCode() == 200 && response.getData() != null) {
                        NavData.DataBean dataBean = new NavData.DataBean();
                        dataBean.setName("全部");
                        dataBean.setStatus(1);
                        response.getData().add(0, dataBean);
                        personNavData = response;
                        userTabAdapter.refreshData(response.getData());
                    } else {
                        ToastUtils.showShort(SearchActivity.this, response.getMsg());
                    }
                }

            });
        } else if (type == 0 || type == 2) {
            SendRequest.category(new GenericsCallback<NavData>(new JsonGenericsSerializator()) {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(NavData response, int id) {
                    if (response.getCode() == 200 && response.getData() != null) {
                        NavData.DataBean dataBean = new NavData.DataBean();
                        dataBean.setName("全部");
                        dataBean.setStatus(1);
                        response.getData().add(0, dataBean);
                        categoryNavData = response;
                        tabAdapter.refreshData(response.getData());
                        initType();
                    } else {
                        ToastUtils.showShort(SearchActivity.this, response.getMsg());
                    }
                }

            });
        }
    }

    private void initType() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < categoryNavData.getData().size(); i++) {
            pagerAdapter.addFragment(categoryNavData.getData().get(i).getName(), SearchResultFragment.newInstance(categoryNavData.getData().get(i).getName(), personLabel));
        }
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.setOffscreenPageLimit(categoryNavData.getData().size());
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOnPageChangeListener(this);
    }

    private void searchWork(final String content) {
        if (CommonUtil.isBlank(content)) {
            MessageBus.Builder builder = new MessageBus.Builder();
            MessageBus messageBus = builder
                    .codeType(MessageBus.msgId_searchCancel)
                    .build();
            EventBus.getDefault().post(messageBus);
            return;
        }
        if (personNavData != null && personNavData.getCode() == 200 && personNavData.getData().size() > 0) {
            for (int i = 0; i < personNavData.getData().size(); i++) {
                personNavData.getData().get(i).setStatus(0);
            }
            NavData.DataBean dataBean = personNavData.getData().get(0);
            dataBean.setStatus(1);
            personLabel = dataBean.getName();
            userTabAdapter.notifyDataSetChanged();
        }
        if (categoryNavData != null && categoryNavData.getCode() == 200 && categoryNavData.getData().size() > 0) {
            binding.emptyView.setVisibility(categoryNavData.getData().size() > 0 ? View.GONE : View.VISIBLE);
            binding.viewPager.setCurrentItem(0);
            MessageBus.Builder builder = new MessageBus.Builder();
            MessageBus messageBus = builder
                    .codeType(MessageBus.msgId_searchContent)
                    .param1(content)
                    .param2(personLabel)
                    .build();
            EventBus.getDefault().post(messageBus);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (categoryNavData != null && categoryNavData.getCode() == 200 && categoryNavData.getData().size() > 0) {
            NavData.DataBean dataBean = categoryNavData.getData().get(position);
            dataBean.setStatus(dataBean.getStatus() == 0 ? 1 : 0);
            for (int i = 0; i < categoryNavData.getData().size(); i++) {
                if (dataBean != categoryNavData.getData().get(i)) {
                    categoryNavData.getData().get(i).setStatus(0);
                }
            }
            tabAdapter.notifyDataSetChanged();
            binding.allTypeView.setTextColor(position == 0 ? getResources().getColor(R.color.titleColor) : getResources().getColor(R.color.contentColor));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
