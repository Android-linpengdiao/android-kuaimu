package com.kuaimu.android.app.fragment;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.FragmentFollowBinding;

public class ReleaseFragment extends BaseFragment{

    private FragmentFollowBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follow, container, false);
        setStatusBarHeight(binding.getRoot());
        setStatusBarDarkTheme(true);

        return binding.getRoot();
    }
}
