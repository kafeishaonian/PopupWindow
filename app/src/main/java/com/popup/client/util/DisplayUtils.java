package com.popup.client.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Hongmingwei on 2017/9/19.
 * Email: 648600445@qq.com
 */
public class DisplayUtils {

    /**
     * dip转换为px
     * @param context
     * @param dip
     * @return
     */
    public static int dipToPx(Context context, float dip) {
        return (int) (dip * getDisplayMetrics(context).density + 0.5f);
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        Display display = getDisplay(context);
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm;
    }
    /**
     * 获取显示设备参数
     *
     * @param context
     * @return
     */
    public static Display getDisplay(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

}
