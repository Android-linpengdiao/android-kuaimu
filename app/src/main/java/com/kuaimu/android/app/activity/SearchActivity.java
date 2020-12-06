package com.kuaimu.android.app.activity;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.baselibrary.Constants;
import com.baselibrary.MessageBus;
import com.baselibrary.utils.SharedPreferencesUtils;
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
import com.kuaimu.android.app.adapter.RecommendAdapter;
import com.kuaimu.android.app.adapter.SearchHistoryAdapter;
import com.kuaimu.android.app.adapter.SearchTabAdapter;
import com.kuaimu.android.app.adapter.SearchUserTabAdapter;
import com.kuaimu.android.app.databinding.ActivitySearchBinding;
import com.kuaimu.android.app.db.DBManager;
import com.kuaimu.android.app.fragment.SearchResultFragment;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.RecommendData;
import com.kuaimu.android.app.utils.SpringViewNewFooter;
import com.kuaimu.android.app.utils.SpringViewNewHeader;
import com.kuaimu.android.app.view.FlowLayoutManager;
import com.kuaimu.android.app.view.GridItemDecoration;
import com.kuaimu.android.app.view.OnClickListener;
import com.kuaimu.android.app.view.SpaceItemDecoration;
import com.liaoinstan.springview.widget.SpringView;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SearchActivity";
    private ActivitySearchBinding binding;
    private RecommendAdapter adapter;
    private RecommendData recommendData;
    private boolean isRefresh = true;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        setStatusBarHeight(binding.getRoot());
        setStatusBarDarkTheme(true);

        binding.searchBack.setOnClickListener(this);
        binding.searchContentDelete.setOnClickListener(this);

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
                        initSearchView(content);
                    } else {
                        ToastUtils.showShort(SearchActivity.this, "请输入搜索内容");
                    }
                    return true;
                }
                return false;
            }
        });


        adapter = new RecommendAdapter(this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(this);
        builder.color(R.color.transparent);
        builder.size(CommonUtil.dip2px(this, 10));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(builder));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                if (recommendData != null) {
                    Intent intent = new Intent(SearchActivity.this, WorkDetailActivity.class);
                    BaseData baseData = new BaseData();
                    baseData.setData(adapter.getList());
                    intent.putExtra("baseData", baseData);
                    intent.putExtra("position", (int) object);
                    startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });


        binding.springMine.setHeader(new SpringViewNewHeader(this));
        binding.springMine.setFooter(new SpringViewNewFooter(this));
        binding.springMine.setType(SpringView.Type.FOLLOW);
        binding.springMine.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                searchWork(1);
            }

            @Override
            public void onLoadmore() {
                if (recommendData != null && recommendData.getCode() == 200 && recommendData.getData() != null &&
                        recommendData.getData().getCurrent_page() < recommendData.getData().getLast_page()) {
                    isRefresh = false;
                    searchWork(recommendData.getData().getCurrent_page() + 1);
                } else {
                    binding.springMine.onFinishFreshAndLoad();
                    ToastUtils.showShort(SearchActivity.this, "没有更多了");
                }
            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_content_delete:
                binding.etSearch.setText("");
                initSearchView(null);
                break;
        }
    }

    private void initSearchView(String content) {
        if (!CommonUtil.isBlank(content)) {
            this.content = content;
            searchWork(1);
        }else {
            adapter.refreshData(new ArrayList<>());
            binding.emptyView.setVisibility(View.GONE);
        }
    }

    private void searchWork(int currentPage) {
        SendRequest.videoSearch(getUid(), content, currentPage, new GenericsCallback<RecommendData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                binding.springMine.onFinishFreshAndLoad();
            }

            @Override
            public void onResponse(RecommendData response, int id) {
                binding.springMine.onFinishFreshAndLoad();
                if (response.getCode() == 200) {
                    recommendData = response;
                    if (isRefresh) {
                        adapter.refreshData(response.getData().getData());
                    } else {
                        adapter.loadMoreData(response.getData().getData());
                    }
                    binding.emptyView.setVisibility(response.getData().getData().size() > 0 ? View.GONE : View.VISIBLE);
                } else {
                    binding.emptyView.setVisibility(View.VISIBLE);
                }
            }

        });
    }


}
