package cn.yoki.library.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ActionMenuView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class FragmentHelper<T extends Fragment> {

    private Activity activity;
    private int containerId;

    public FragmentHelper(Activity activity, int containerId, List<T> fragmentList) {
        this.activity = activity;
        this.containerId = containerId;
    }






}
