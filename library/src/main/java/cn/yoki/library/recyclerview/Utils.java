package cn.yoki.library.recyclerview;

import android.content.Context;

public class Utils {

    public static int dpToPx(Context context, float dip) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        float valueDips = dip;
        int valuePixels = (int) (valueDips * SCALE + 0.5f);
        return valuePixels;
    }
}