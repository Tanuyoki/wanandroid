package cn.yoki.library.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ActionMenuView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class FragmentHelper {

    private Context context;
    private int containerId;
    private int lastShowIndex = -1;
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public FragmentHelper(Context context, int containerId, FragmentManager fragmentManager, List<Fragment> fragmentList) {
        this.context = context;
        this.containerId = containerId;
        this.fragmentManager = fragmentManager;
        this.fragmentList = fragmentList;

        fragmentTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragmentList) {
            fragmentTransaction.add(containerId, fragment);
        }
        fragmentTransaction.commit();
    }

    public void showFragment(int index) {
        if (index < 0 || index >= fragmentList.size() || lastShowIndex == index) {
            return;
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i ++) {
            Fragment fragment = fragmentList.get(i);
            if (fragment == null) {
                fragment = new Fragment();
                fragmentTransaction.add(containerId, fragment);
            }
            if (i == index) {
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.hide(fragment);
            }
        }

        fragmentTransaction.commit();
        lastShowIndex = index;
    }



}
