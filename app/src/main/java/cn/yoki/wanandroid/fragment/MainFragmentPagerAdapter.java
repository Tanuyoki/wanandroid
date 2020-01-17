package cn.yoki.wanandroid.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public List<Fragment> mListFragment;

    public MainFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> listFragment) {
        super(fm, behavior);
        this.mListFragment = listFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mListFragment == null ? null : mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment == null ? 0 : mListFragment.size();
    }

}
