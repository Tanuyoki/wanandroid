package cn.yoki.wanandroid.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.AccessibleObject;

import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    private static final String PARAM_KEY = "param_key";
    private String mParam;
    private Activity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mParam = getArguments().getString(PARAM_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);

        return root;
    }

//    public static HomeFragment newInstance(String str) {
//        HomeFragment homeFragment = new HomeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(PARAM_KEY, str);
//        homeFragment.setArguments(bundle);
//        return homeFragment;
//    }

}
