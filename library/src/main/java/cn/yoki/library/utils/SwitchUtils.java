package cn.yoki.library.utils;

import android.view.View;
import android.view.View.OnClickListener;

import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.UUID;

import cn.yoki.library.view.TabItemView;

public class SwitchUtils {

    private View[] views;
    private boolean isEnableSwitch;
    private OnSwitchClickListener onSwitchClickListener;

    public SwitchUtils(View... views) {
        this.views = views;
        initView();
    }

    private void initView() {
        int index = 0;
        for (View view: views) {
            view.setTag(index);
            view.setOnClickListener(viewOnClickListener);
            index ++;
        }
    }

    private View.OnClickListener viewOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            for (View view : views) {
                setSwitchState(view, view.getTag().equals(v.getTag()));
            }
        }
    };

    private void setSwitchState(View v, boolean isOn) {
        if (!isEnableSwitch) {
            return;
        }
        if (v instanceof TabItemView) {
            TabItemView tabItemView = (TabItemView) v;
            tabItemView.setSwitchState(isOn);
        }
        if (onSwitchClickListener != null) {
            onSwitchClickListener.onSwitchClick(v, isOn);
        }
    }

    public void enableSwitch() {
        isEnableSwitch = true;
    }

    public void switchView(int index) {
        if (index >= 0 && index < views.length)
            views[index].performClick();
    }

    public void setOnSwitchClickListener(OnSwitchClickListener onViewSwitchClick) {
        this.onSwitchClickListener = onViewSwitchClick;
    }

}
