package com.kuaimu.android.app.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.SharedPreferencesUtils;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.MyApplication;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.adapter.AreaAdapter;
import com.kuaimu.android.app.databinding.ActivityCitySelectionBinding;
import com.kuaimu.android.app.manager.CityManager;
import com.kuaimu.android.app.manager.RecycleViewManager;
import com.kuaimu.android.app.model.CityData;
import com.kuaimu.android.app.view.OnClickListener;
import com.kuaimu.android.app.view.SideLetterBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitySelectionActivity extends BaseActivity implements View.OnClickListener {

    private ActivityCitySelectionBinding mBinding;
    private List<CityData.FirstChildrenBean> areaList;
    private AreaAdapter areaAdapter;
    private List<CityData> sectionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_city_selection);
        setStatusBarDarkTheme(true);

        mBinding.back.setOnClickListener(this);
        mBinding.locationCityView.setOnClickListener(this);

        mBinding.locationCityView.setText(SharedPreferencesUtils.getInstance().getCity());

        mBinding.sidebar.setOverlay(mBinding.letterOverlayTextView);
        mBinding.sidebar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = areaAdapter.getLetterPosition(letter);
                mBinding.areaFirstRecyclerView.setScrollBarSize(position);
                RecycleViewManager.smoothMoveToPosition(mBinding.areaFirstRecyclerView, position);
            }
        });

        areaAdapter = new AreaAdapter(this);
        mBinding.areaFirstRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.areaFirstRecyclerView.setAdapter(areaAdapter);

        areaList = MyApplication.areaList;
        if (areaList == null) {
            areaList = CityManager.getInstance().getAreaFirst();
        }

        String[] sections = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        for (int j = 0; j < sections.length; j++) {
            CityData cityData = new CityData();
            cityData.setName(sections[j]);
            Map<String, CityData.FirstChildrenBean> mapCity = new HashMap<>();
            for (int i = 0; i < areaList.size(); i++) {
                CityData.FirstChildrenBean bean = areaList.get(i);
                if (sections[j].equals(bean.getPinyin())) {
                    mapCity.put(bean.getName(), bean);
                }
            }

            for (Map.Entry<String, CityData.FirstChildrenBean> map : mapCity.entrySet()) {
                cityData.getChildren().add(map.getValue());
            }
            sectionsList.add(cityData);
        }

        areaAdapter.refreshData(sectionsList);
        areaAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, Object object) {
                Intent intent = new Intent();
                intent.putExtra("userAddr", (String) object);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onLongClick(View view, Object object) {

            }
        });
        mBinding.locationCityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
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
        mBinding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String content = mBinding.etSearch.getText().toString().trim();
                    if (!CommonUtil.isBlank(content)) {
                        // 隐藏键盘
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        initSearchView(content);
                    } else {
                        ToastUtils.showShort(CitySelectionActivity.this, "请输入搜索内容");
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private void initSearchView(String content) {
        if (!CommonUtil.isBlank(content)){

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
            case R.id.locationCityView:
                finish();
                break;
        }
    }
}