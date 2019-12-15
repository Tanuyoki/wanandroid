package cn.yoki.library;

import android.app.Activity;
import android.content.Context;

public class Toolkit {

    private static Activity activity;

    public static void pushActivity(Activity activity) {
        Toolkit.activity = activity;
    }

    public static void popActivity(Activity activity) {
        Toolkit.activity = null;
    }

    public static Activity getActivity() {
        return activity;
    }

}
