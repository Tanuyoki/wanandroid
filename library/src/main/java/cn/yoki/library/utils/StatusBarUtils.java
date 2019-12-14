package cn.yoki.library.utils;

import android.graphics.Color;
import android.os.Build;
import android.view.View;

import cn.yoki.library.Toolkit;

public class StatusBarUtils {

    public static void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = Toolkit.getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            Toolkit.getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }



}
